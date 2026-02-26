package com.qea.utils;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Image;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.kernel.colors.ColorConstants;

import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Simple Report Consolidator that reads from allure-results and creates a single PDF/Word report
 * File naming: Tagname<RandomNumber>.pdf or .docx
 * 
 * @author Facctum Test Automation Team
 */
public class SimpleReportConsolidator {
    
    private static final String ALLURE_RESULTS_DIR = "target/allure-results";
    private static final String SCREENSHOTS_DIR = "screenshots";
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * Generate consolidated report from allure results
     * @param tagName Tag name from test execution
     * @return Path to generated report
     */
    public static String generateConsolidatedReport(String tagName) {
        try {
            String format = ConsolidatedReportConfig.getReportFormat();
            String cleanTagName = cleanTagName(tagName);
            String fileName = generateFileName(cleanTagName);
            String baseReportDir = ConsolidatedReportConfig.getReportDirectory();
            
            // Determine the appropriate subdirectory based on tag name
            String reportDir = determineReportDirectory(baseReportDir, cleanTagName);
            
            // Create report directory
            Files.createDirectories(Paths.get(reportDir));
            
            // Collect data from allure results
            List<TestResultData> testResults = collectAllureTestResults();
            List<String> screenshots = collectScreenshots();
            
            String reportPath;
            if ("WORD".equals(format)) {
                reportPath = generateWordReport(reportDir, fileName, testResults, screenshots, cleanTagName);
            } else {
                reportPath = generatePDFReport(reportDir, fileName, testResults, screenshots, cleanTagName);
            }
            
            System.out.println("📄 Consolidated report generated: " + reportPath);
            return reportPath;
            
        } catch (Exception e) {
            System.err.println("❌ Failed to generate consolidated report: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Simple test result data class
     */
    private static class TestResultData {
        String name;
        String status;
        String startTime;
        String endTime;
        long duration;
        List<String> attachments = new ArrayList<>();
        
        TestResultData(String name, String status) {
            this.name = name;
            this.status = status;
            this.startTime = LocalDateTime.now().format(DISPLAY_FORMAT);
            this.endTime = this.startTime;
        }
    }
    
    /**
     * Collect test results from allure results directory
     */
    private static List<TestResultData> collectAllureTestResults() {
        List<TestResultData> results = new ArrayList<>();
        
        try {
            Path allureResultsPath = Paths.get(ALLURE_RESULTS_DIR);
            if (!Files.exists(allureResultsPath)) {
                System.out.println("Allure results directory not found: " + ALLURE_RESULTS_DIR);
                return results;
            }
            
            // Find all result JSON files
            List<Path> jsonFiles = Files.walk(allureResultsPath)
                .filter(path -> path.toString().endsWith("-result.json"))
                .sorted()
                .collect(java.util.stream.Collectors.toList());
            
            for (Path jsonFile : jsonFiles) {
                try {
                    String content = new String(Files.readAllBytes(jsonFile));
                    TestResultData result = parseTestResult(content);
                    if (result != null) {
                        results.add(result);
                    }
                } catch (Exception e) {
                    System.err.println("Failed to parse JSON file: " + jsonFile + " - " + e.getMessage());
                }
            }
            
            System.out.println("📊 Collected " + results.size() + " test results from Allure");
            
        } catch (Exception e) {
            System.err.println("Failed to collect Allure test results: " + e.getMessage());
        }
        
        return results;
    }
    
    /**
     * Parse test result from JSON content
     */
    private static TestResultData parseTestResult(String jsonContent) {
        try {
            // Simple JSON parsing using regex (avoiding external JSON libraries)
            String name = extractJsonValue(jsonContent, "name");
            String status = extractJsonValue(jsonContent, "status");
            String start = extractJsonValue(jsonContent, "start");
            String stop = extractJsonValue(jsonContent, "stop");
            
            if (name != null && status != null) {
                TestResultData result = new TestResultData(name, status);
                
                if (start != null && stop != null) {
                    try {
                        long startTime = Long.parseLong(start);
                        long stopTime = Long.parseLong(stop);
                        result.duration = stopTime - startTime;
                        result.startTime = new java.util.Date(startTime).toString();
                        result.endTime = new java.util.Date(stopTime).toString();
                    } catch (Exception e) {
                        // Use default times if parsing fails
                    }
                }
                
                // Extract attachments
                result.attachments = extractAttachments(jsonContent);
                
                return result;
            }
        } catch (Exception e) {
            System.err.println("Failed to parse test result: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Extract JSON value using regex
     */
    private static String extractJsonValue(String json, String key) {
        try {
            Pattern pattern = Pattern.compile("\"" + key + "\"\\s*:\\s*\"([^\"]+)\"");
            Matcher matcher = pattern.matcher(json);
            if (matcher.find()) {
                return matcher.group(1);
            }
            
            // Try numeric value
            pattern = Pattern.compile("\"" + key + "\"\\s*:\\s*([0-9]+)");
            matcher = pattern.matcher(json);
            if (matcher.find()) {
                return matcher.group(1);
            }
        } catch (Exception e) {
            // Ignore parsing errors
        }
        return null;
    }
    
    /**
     * Extract attachment information from JSON
     */
    private static List<String> extractAttachments(String jsonContent) {
        List<String> attachments = new ArrayList<>();
        try {
            // Look for attachment sources in the JSON
            Pattern pattern = Pattern.compile("\"source\"\\s*:\\s*\"([^\"]+)\"");
            Matcher matcher = pattern.matcher(jsonContent);
            while (matcher.find()) {
                String source = matcher.group(1);
                if (source.endsWith(".png") || source.endsWith(".txt") || source.endsWith(".json")) {
                    String fullPath = ALLURE_RESULTS_DIR + "/" + source;
                    if (Files.exists(Paths.get(fullPath))) {
                        attachments.add(fullPath);
                    }
                }
            }
        } catch (Exception e) {
            // Ignore parsing errors
        }
        return attachments;
    }
    
    /**
     * Collect screenshots from screenshots directory with enhanced naming
     */
    private static List<String> collectScreenshots() {
        List<String> screenshots = new ArrayList<>();
        try {
            Path screenshotsPath = Paths.get(SCREENSHOTS_DIR);
            if (Files.exists(screenshotsPath)) {
                List<Path> pngFiles = Files.walk(screenshotsPath)
                    .filter(path -> path.toString().endsWith(".png"))
                    .sorted()
                    .collect(java.util.stream.Collectors.toList());
                
                // Process screenshots with enhanced naming
                int stepCounter = 1;
                for (Path pngFile : pngFiles) {
                    String originalPath = pngFile.toString();
                    String originalFileName = pngFile.getFileName().toString();
                    
                    // Generate friendly name using ScreenshotManager
                    String friendlyName = ScreenshotManager.generateFriendlyScreenshotName(
                        originalFileName, "TestExecution", stepCounter++);
                    
                    // Add both original and friendly name for reference
                    screenshots.add(originalPath + "|" + friendlyName);
                }
                
                System.out.println("📸 Collected " + screenshots.size() + " screenshots with enhanced naming");
            }
        } catch (Exception e) {
            System.err.println("Failed to collect screenshots: " + e.getMessage());
        }
        return screenshots;
    }
    
    /**
     * Generate PDF report
     */
    private static String generatePDFReport(String reportDir, String fileName, 
                                          List<TestResultData> testResults, 
                                          List<String> screenshots, 
                                          String tagName) throws IOException {
        String filePath = reportDir + "/" + fileName + ".pdf";
        
        try (FileOutputStream fos = new FileOutputStream(filePath);
             PdfWriter writer = new PdfWriter(fos);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {
            
            // Title
            document.add(new Paragraph("Facctum Test Automation Report")
                .setFontSize(20)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20));
            
            // Test execution summary
            document.add(new Paragraph("Test Execution Summary")
                .setFontSize(16)
                .setBold()
                .setMarginTop(20));
            
            document.add(new Paragraph("Tag: " + tagName));
            document.add(new Paragraph("Execution Time: " + LocalDateTime.now().format(DISPLAY_FORMAT)));
            document.add(new Paragraph("Total Tests: " + testResults.size()));
            
            long passedCount = testResults.stream().filter(r -> "passed".equals(r.status)).count();
            long failedCount = testResults.stream().filter(r -> "failed".equals(r.status)).count();
            long skippedCount = testResults.stream().filter(r -> "skipped".equals(r.status)).count();
            
            document.add(new Paragraph("Passed: " + passedCount).setFontColor(ColorConstants.GREEN));
            document.add(new Paragraph("Failed: " + failedCount).setFontColor(ColorConstants.RED));
            document.add(new Paragraph("Skipped: " + skippedCount).setFontColor(ColorConstants.ORANGE));
            
            // Test details
            for (TestResultData result : testResults) {
                document.add(new Paragraph("\n" + "=".repeat(50)));
                document.add(new Paragraph("Test: " + result.name)
                    .setFontSize(14)
                    .setBold());
                document.add(new Paragraph("Status: " + result.status)
                    .setFontColor(getStatusColor(result.status)));
                document.add(new Paragraph("Start Time: " + result.startTime));
                document.add(new Paragraph("End Time: " + result.endTime));
                document.add(new Paragraph("Duration: " + result.duration + "ms"));
                
                // Add test attachments
                if (!result.attachments.isEmpty()) {
                    document.add(new Paragraph("\nAttachments:").setBold());
                    int attachmentCounter = 1;
                    for (String attachment : result.attachments) {
                        if (attachment.endsWith(".png")) {
                            try {
                                Image img = new Image(ImageDataFactory.create(attachment));
                                img.setMaxWidth(400);
                                img.setMaxHeight(300);
                                document.add(img);
                                
                                // Generate friendly name for attachment
                                String attachmentFileName = Paths.get(attachment).getFileName().toString();
                                String friendlyName = ScreenshotManager.generateFriendlyScreenshotName(
                                    attachmentFileName, result.name, attachmentCounter++);
                                document.add(new Paragraph("Screenshot: " + friendlyName));
                            } catch (Exception e) {
                                document.add(new Paragraph("Screenshot not available: " + attachment));
                            }
                        } else {
                            document.add(new Paragraph("Attachment: " + Paths.get(attachment).getFileName()));
                        }
                    }
                }
            }
            
            // Additional screenshots with friendly names
            if (!screenshots.isEmpty() && ConsolidatedReportConfig.includeScreenshots()) {
                document.add(new Paragraph("\n" + "=".repeat(50)));
                document.add(new Paragraph("Additional Screenshots")
                    .setFontSize(16)
                    .setBold());
                
                for (String screenshotInfo : screenshots) {
                    try {
                        // Parse screenshot info (originalPath|friendlyName)
                        String[] parts = screenshotInfo.split("\\|");
                        String originalPath = parts[0];
                        String friendlyName = parts.length > 1 ? parts[1] : Paths.get(originalPath).getFileName().toString();
                        
                        Image img = new Image(ImageDataFactory.create(originalPath));
                        img.setMaxWidth(400);
                        img.setMaxHeight(300);
                        document.add(img);
                        document.add(new Paragraph("Screenshot: " + friendlyName));
                    } catch (Exception e) {
                        String originalPath = screenshotInfo.split("\\|")[0];
                        document.add(new Paragraph("Screenshot not available: " + originalPath));
                    }
                }
            }
            
            // Environment information
            if (ConsolidatedReportConfig.includeEnvironmentInfo()) {
                addEnvironmentInfo(document);
            }
        }
        
        return filePath;
    }
    
    /**
     * Generate Word document report
     */
    private static String generateWordReport(String reportDir, String fileName, 
                                           List<TestResultData> testResults, 
                                           List<String> screenshots, 
                                           String tagName) throws IOException {
        String filePath = reportDir + "/" + fileName + ".docx";
        
        try (XWPFDocument document = new XWPFDocument();
             FileOutputStream out = new FileOutputStream(filePath)) {
            
            // Title
            XWPFParagraph title = document.createParagraph();
            title.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun = title.createRun();
            titleRun.setText("Facctum Test Automation Report");
            titleRun.setBold(true);
            titleRun.setFontSize(20);
            
            // Test execution summary
            XWPFParagraph summary = document.createParagraph();
            XWPFRun summaryRun = summary.createRun();
            summaryRun.setText("Test Execution Summary");
            summaryRun.setBold(true);
            summaryRun.setFontSize(16);
            summaryRun.addBreak();
            summaryRun.addBreak();
            
            summaryRun.setText("Tag: " + tagName);
            summaryRun.addBreak();
            summaryRun.setText("Execution Time: " + LocalDateTime.now().format(DISPLAY_FORMAT));
            summaryRun.addBreak();
            summaryRun.setText("Total Tests: " + testResults.size());
            summaryRun.addBreak();
            
            long passedCount = testResults.stream().filter(r -> "passed".equals(r.status)).count();
            long failedCount = testResults.stream().filter(r -> "failed".equals(r.status)).count();
            long skippedCount = testResults.stream().filter(r -> "skipped".equals(r.status)).count();
            
            summaryRun.setText("Passed: " + passedCount);
            summaryRun.addBreak();
            summaryRun.setText("Failed: " + failedCount);
            summaryRun.addBreak();
            summaryRun.setText("Skipped: " + skippedCount);
            summaryRun.addBreak();
            
            // Test details
            for (TestResultData result : testResults) {
                XWPFParagraph testPara = document.createParagraph();
                XWPFRun testRun = testPara.createRun();
                testRun.addBreak();
                testRun.setText("=" + "=".repeat(50));
                testRun.addBreak();
                testRun.setText("Test: " + result.name);
                testRun.setBold(true);
                testRun.addBreak();
                testRun.setBold(false);
                testRun.setText("Status: " + result.status);
                testRun.addBreak();
                testRun.setText("Start Time: " + result.startTime);
                testRun.addBreak();
                testRun.setText("End Time: " + result.endTime);
                testRun.addBreak();
                testRun.setText("Duration: " + result.duration + "ms");
                testRun.addBreak();
                
                // Add attachments information with friendly names
                if (!result.attachments.isEmpty()) {
                    testRun.addBreak();
                    testRun.setText("Attachments:");
                    testRun.setBold(true);
                    testRun.addBreak();
                    testRun.setBold(false);
                    int attachmentCounter = 1;
                    for (String attachment : result.attachments) {
                        if (attachment.endsWith(".png")) {
                            // Generate friendly name for screenshots
                            String attachmentFileName = Paths.get(attachment).getFileName().toString();
                            String friendlyName = ScreenshotManager.generateFriendlyScreenshotName(
                                attachmentFileName, result.name, attachmentCounter++);
                            testRun.setText("- Screenshot: " + friendlyName);
                        } else {
                            testRun.setText("- " + Paths.get(attachment).getFileName());
                        }
                        testRun.addBreak();
                    }
                }
            }
            
            document.write(out);
        }
        
        return filePath;
    }
    
    /**
     * Generate file name with tag and random number
     */
    private static String generateFileName(String tagName) {
        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000; // 6-digit random number
        return tagName + "_" + randomNumber;
    }
    
    /**
     * Clean tag name to be file-safe
     */
    private static String cleanTagName(String tagName) {
        if (tagName == null || tagName.isEmpty()) return "UnknownTag";
        
        // Remove @ symbol if present and clean special characters
        return tagName.replaceAll("[@#$%^&*()\\[\\]{}|\\\\:;\"'<>?/]", "")
                     .replaceAll("\\s+", "_")
                     .replaceAll("_+", "_")
                     .replaceAll("^_|_$", ""); // Remove leading/trailing underscores
    }
    
    /**
     * Get color for status (PDF)
     */
    private static com.itextpdf.kernel.colors.Color getStatusColor(String status) {
        switch (status.toLowerCase()) {
            case "passed":
                return ColorConstants.GREEN;
            case "failed":
                return ColorConstants.RED;
            case "skipped":
                return ColorConstants.ORANGE;
            default:
                return ColorConstants.BLACK;
        }
    }
    
    /**
     * Add environment information to PDF document
     */
    private static void addEnvironmentInfo(Document document) {
        document.add(new Paragraph("\n" + "=".repeat(50)));
        document.add(new Paragraph("Environment Information")
            .setFontSize(16)
            .setBold());
        
        document.add(new Paragraph("OS: " + System.getProperty("os.name")));
        document.add(new Paragraph("Java Version: " + System.getProperty("java.version")));
        document.add(new Paragraph("User: " + System.getProperty("user.name")));
        document.add(new Paragraph("Working Directory: " + System.getProperty("user.dir")));
        document.add(new Paragraph("Report Generated: " + LocalDateTime.now().format(DISPLAY_FORMAT)));
    }
    
    /**
     * Determine the appropriate report directory based on tag name
     * @param baseReportDir Base report directory
     * @param tagName Cleaned tag name
     * @return Full path to the appropriate report subdirectory
     */
    private static String determineReportDirectory(String baseReportDir, String tagName) {
        // Check if it's a Surefire-related report
        if (tagName.toLowerCase().contains("surefire") || 
            tagName.toLowerCase().contains("suite") ||
            tagName.toLowerCase().contains("testng") ||
            tagName.toLowerCase().contains("maven")) {
            return baseReportDir + "/Surefire_Suite";
        } else {
            // For all other tags, use Tag_Based_Reports directory
            return baseReportDir + "/Tag_Based_Reports";
        }
    }
}
