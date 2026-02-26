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
import java.util.stream.Collectors;

/**
 * Consolidated Report Generator for Test Execution
 * Creates single PDF or Word document with all test results, screenshots, and attachments
 * 
 * @author Facctum Test Automation Team
 */
public class ConsolidatedReportGenerator {
    
    private static final String ALLURE_RESULTS_DIR = "target/allure-results";
    private static final String SCREENSHOTS_DIR = "screenshots";
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    private static String currentTagName = "UnknownTag";
    private static List<TestResult> testResults = new ArrayList<>();
    private static List<String> attachmentPaths = new ArrayList<>();
    private static String reportFormat = "PDF"; // Default format
    
    /**
     * Test Result data class
     */
    public static class TestResult {
        public String testName;
        public String status;
        public String startTime;
        public String endTime;
        public String duration;
        public String description;
        public List<String> steps;
        public List<String> screenshots;
        public String errorMessage;
        
        public TestResult(String testName) {
            this.testName = testName;
            this.steps = new ArrayList<>();
            this.screenshots = new ArrayList<>();
            this.startTime = LocalDateTime.now().format(DISPLAY_FORMAT);
        }
    }
    
    /**
     * Initialize report generator with tag name
     * @param tagName Tag name from the test execution
     * @param format Report format (PDF or WORD)
     */
    public static void initializeReport(String tagName, String format) {
        if (!ConsolidatedReportConfig.isEnabled()) {
            System.out.println("📊 Consolidated Report is disabled in configuration");
            return;
        }
        
        currentTagName = cleanTagName(tagName);
        reportFormat = format.toUpperCase();
        testResults.clear();
        attachmentPaths.clear();
        createReportsDirectory();
        
        System.out.println("📊 Consolidated Report Generator initialized for tag: " + currentTagName);
        System.out.println("📄 Report format: " + reportFormat);
        System.out.println(ConsolidatedReportConfig.getConfigSummary());
    }
    
    /**
     * Initialize report generator with tag name (uses configured format)
     * @param tagName Tag name from the test execution
     */
    public static void initializeReport(String tagName) {
        String configuredFormat = ConsolidatedReportConfig.getReportFormat();
        initializeReport(tagName, configuredFormat);
    }
    
    /**
     * Add test result to the consolidated report
     * @param testName Name of the test
     * @param status Test status (PASSED, FAILED, SKIPPED)
     * @param description Test description
     * @param duration Test duration
     */
    public static void addTestResult(String testName, String status, String description, String duration) {
        TestResult result = new TestResult(testName);
        result.status = status;
        result.description = description;
        result.duration = duration;
        result.endTime = LocalDateTime.now().format(DISPLAY_FORMAT);
        
        testResults.add(result);
        System.out.println("📝 Test result added: " + testName + " - " + status);
    }
    
    /**
     * Add step to the current test
     * @param testName Test name
     * @param stepDescription Step description
     */
    public static void addTestStep(String testName, String stepDescription) {
        testResults.stream()
            .filter(result -> result.testName.equals(testName))
            .findFirst()
            .ifPresent(result -> {
                result.steps.add(stepDescription);
                System.out.println("📋 Step added to " + testName + ": " + stepDescription);
            });
    }
    
    /**
     * Add screenshot to the current test
     * @param testName Test name
     * @param screenshotPath Path to screenshot
     */
    public static void addScreenshot(String testName, String screenshotPath) {
        testResults.stream()
            .filter(result -> result.testName.equals(testName))
            .findFirst()
            .ifPresent(result -> {
                result.screenshots.add(screenshotPath);
                System.out.println("📸 Screenshot added to " + testName + ": " + screenshotPath);
            });
    }
    
    /**
     * Add error message to a test
     * @param testName Test name
     * @param errorMessage Error message
     */
    public static void addErrorMessage(String testName, String errorMessage) {
        testResults.stream()
            .filter(result -> result.testName.equals(testName))
            .findFirst()
            .ifPresent(result -> {
                result.errorMessage = errorMessage;
                System.out.println("❌ Error message added to " + testName);
            });
    }
    
    /**
     * Generate consolidated report
     * @return Path to generated report file
     */
    public static String generateConsolidatedReport() {
        if (!ConsolidatedReportConfig.isEnabled()) {
            System.out.println("📊 Consolidated Report generation skipped (disabled in configuration)");
            return null;
        }
        
        try {
            // Collect additional data from allure results
            collectAllureData();
            if (ConsolidatedReportConfig.includeScreenshots()) {
                collectScreenshots();
            }
            
            String fileName = generateFileName();
            String reportPath;
            
            if ("WORD".equals(reportFormat)) {
                reportPath = generateWordReport(fileName);
            } else {
                reportPath = generatePDFReport(fileName);
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
     * Generate PDF report
     */
    private static String generatePDFReport(String fileName) throws IOException {
        String baseReportDir = ConsolidatedReportConfig.getReportDirectory();
        String reportDir = determineReportDirectory(baseReportDir, currentTagName);
        String filePath = reportDir + "/" + fileName + ".pdf";
        
        // Ensure the specific subdirectory exists
        Files.createDirectories(Paths.get(reportDir));
        
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
            
            document.add(new Paragraph("Tag: " + currentTagName));
            document.add(new Paragraph("Execution Time: " + LocalDateTime.now().format(DISPLAY_FORMAT)));
            document.add(new Paragraph("Total Tests: " + testResults.size()));
            
            long passedCount = testResults.stream().filter(r -> "PASSED".equals(r.status)).count();
            long failedCount = testResults.stream().filter(r -> "FAILED".equals(r.status)).count();
            long skippedCount = testResults.stream().filter(r -> "SKIPPED".equals(r.status)).count();
            
            document.add(new Paragraph("Passed: " + passedCount).setFontColor(ColorConstants.GREEN));
            document.add(new Paragraph("Failed: " + failedCount).setFontColor(ColorConstants.RED));
            document.add(new Paragraph("Skipped: " + skippedCount).setFontColor(ColorConstants.ORANGE));
            
            // Test details
            for (TestResult result : testResults) {
                document.add(new Paragraph("\n" + "=".repeat(50)));
                document.add(new Paragraph("Test: " + result.testName)
                    .setFontSize(14)
                    .setBold());
                document.add(new Paragraph("Status: " + result.status)
                    .setFontColor(getStatusColor(result.status)));
                document.add(new Paragraph("Description: " + (result.description != null ? result.description : "N/A")));
                document.add(new Paragraph("Start Time: " + result.startTime));
                document.add(new Paragraph("End Time: " + result.endTime));
                document.add(new Paragraph("Duration: " + (result.duration != null ? result.duration : "N/A")));
                
                // Test steps
                if (!result.steps.isEmpty()) {
                    document.add(new Paragraph("\nTest Steps:").setBold());
                    for (int i = 0; i < result.steps.size(); i++) {
                        document.add(new Paragraph((i + 1) + ". " + result.steps.get(i)));
                    }
                }
                
                // Error message
                if (result.errorMessage != null) {
                    document.add(new Paragraph("\nError Details:").setBold().setFontColor(ColorConstants.RED));
                    document.add(new Paragraph(result.errorMessage).setFontColor(ColorConstants.RED));
                }
                
                // Screenshots
                if (!result.screenshots.isEmpty()) {
                    document.add(new Paragraph("\nScreenshots:").setBold());
                    for (String screenshotPath : result.screenshots) {
                        try {
                            if (Files.exists(Paths.get(screenshotPath))) {
                                Image img = new Image(ImageDataFactory.create(screenshotPath));
                                img.setMaxWidth(400);
                                img.setMaxHeight(300);
                                document.add(img);
                                document.add(new Paragraph("Screenshot: " + Paths.get(screenshotPath).getFileName()));
                            }
                        } catch (Exception e) {
                            document.add(new Paragraph("Screenshot not available: " + screenshotPath));
                        }
                    }
                }
            }
            
            // Environment information
            addEnvironmentInfo(document);
        }
        
        return filePath;
    }
    
    /**
     * Generate Word document report
     */
    private static String generateWordReport(String fileName) throws IOException {
        String baseReportDir = ConsolidatedReportConfig.getReportDirectory();
        String reportDir = determineReportDirectory(baseReportDir, currentTagName);
        String filePath = reportDir + "/" + fileName + ".docx";
        
        // Ensure the specific subdirectory exists
        Files.createDirectories(Paths.get(reportDir));
        
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
            
            summaryRun.setText("Tag: " + currentTagName);
            summaryRun.addBreak();
            summaryRun.setText("Execution Time: " + LocalDateTime.now().format(DISPLAY_FORMAT));
            summaryRun.addBreak();
            summaryRun.setText("Total Tests: " + testResults.size());
            summaryRun.addBreak();
            
            long passedCount = testResults.stream().filter(r -> "PASSED".equals(r.status)).count();
            long failedCount = testResults.stream().filter(r -> "FAILED".equals(r.status)).count();
            long skippedCount = testResults.stream().filter(r -> "SKIPPED".equals(r.status)).count();
            
            summaryRun.setText("Passed: " + passedCount);
            summaryRun.addBreak();
            summaryRun.setText("Failed: " + failedCount);
            summaryRun.addBreak();
            summaryRun.setText("Skipped: " + skippedCount);
            summaryRun.addBreak();
            
            // Test details
            for (TestResult result : testResults) {
                XWPFParagraph testPara = document.createParagraph();
                XWPFRun testRun = testPara.createRun();
                testRun.addBreak();
                testRun.setText("=" + "=".repeat(50));
                testRun.addBreak();
                testRun.setText("Test: " + result.testName);
                testRun.setBold(true);
                testRun.addBreak();
                testRun.setBold(false);
                testRun.setText("Status: " + result.status);
                testRun.addBreak();
                testRun.setText("Description: " + (result.description != null ? result.description : "N/A"));
                testRun.addBreak();
                testRun.setText("Start Time: " + result.startTime);
                testRun.addBreak();
                testRun.setText("End Time: " + result.endTime);
                testRun.addBreak();
                testRun.setText("Duration: " + (result.duration != null ? result.duration : "N/A"));
                testRun.addBreak();
                
                // Test steps
                if (!result.steps.isEmpty()) {
                    testRun.addBreak();
                    testRun.setText("Test Steps:");
                    testRun.setBold(true);
                    testRun.addBreak();
                    testRun.setBold(false);
                    for (int i = 0; i < result.steps.size(); i++) {
                        testRun.setText((i + 1) + ". " + result.steps.get(i));
                        testRun.addBreak();
                    }
                }
                
                // Error message
                if (result.errorMessage != null) {
                    testRun.addBreak();
                    testRun.setText("Error Details:");
                    testRun.setBold(true);
                    testRun.addBreak();
                    testRun.setBold(false);
                    testRun.setText(result.errorMessage);
                    testRun.addBreak();
                }
                
                // Screenshots
                if (!result.screenshots.isEmpty()) {
                    testRun.addBreak();
                    testRun.setText("Screenshots:");
                    testRun.setBold(true);
                    testRun.addBreak();
                    testRun.setBold(false);
                    for (String screenshotPath : result.screenshots) {
                        try {
                            if (Files.exists(Paths.get(screenshotPath))) {
                                // Add screenshot as image (if supported)
                                testRun.setText("Screenshot: " + Paths.get(screenshotPath).getFileName());
                                testRun.addBreak();
                            }
                        } catch (Exception e) {
                            testRun.setText("Screenshot not available: " + screenshotPath);
                            testRun.addBreak();
                        }
                    }
                }
            }
            
            document.write(out);
        }
        
        return filePath;
    }
    
    /**
     * Collect data from Allure results
     */
    private static void collectAllureData() {
        try {
            Path allureResultsPath = Paths.get(ALLURE_RESULTS_DIR);
            if (Files.exists(allureResultsPath)) {
                List<Path> jsonFiles = Files.walk(allureResultsPath)
                    .filter(path -> path.toString().endsWith("-result.json"))
                    .collect(Collectors.toList());
                
                for (Path jsonFile : jsonFiles) {
                    try {
                        String content = new String(Files.readAllBytes(jsonFile));
                        // Parse JSON and extract test information
                        // This is a simplified version - you can enhance it with proper JSON parsing
                        String testName = extractTestNameFromJson(content);
                        String status = extractStatusFromJson(content);
                        
                        if (testName != null && status != null) {
                            // Check if test result already exists
                            boolean exists = testResults.stream()
                                .anyMatch(result -> result.testName.equals(testName));
                            
                            if (!exists) {
                                addTestResult(testName, status, "Extracted from Allure results", "N/A");
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("Failed to parse JSON file: " + jsonFile);
                    }
                }
                
                // Collect attachment files
                List<Path> attachments = Files.walk(allureResultsPath)
                    .filter(path -> path.toString().endsWith(".txt") || 
                                  path.toString().endsWith(".png") ||
                                  path.toString().endsWith(".json"))
                    .collect(Collectors.toList());
                
                attachmentPaths = attachments.stream()
                    .map(Path::toString)
                    .collect(Collectors.toList());
                    
            }
        } catch (Exception e) {
            System.err.println("Failed to collect Allure data: " + e.getMessage());
        }
    }
    
    /**
     * Collect screenshots from screenshots directory
     */
    private static void collectScreenshots() {
        try {
            Path screenshotsPath = Paths.get(SCREENSHOTS_DIR);
            if (Files.exists(screenshotsPath)) {
                List<Path> screenshots = Files.walk(screenshotsPath)
                    .filter(path -> path.toString().endsWith(".png"))
                    .collect(Collectors.toList());
                
                // Add screenshots to the most recent test result
                if (!testResults.isEmpty() && !screenshots.isEmpty()) {
                    TestResult lastTest = testResults.get(testResults.size() - 1);
                    for (Path screenshot : screenshots) {
                        lastTest.screenshots.add(screenshot.toString());
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to collect screenshots: " + e.getMessage());
        }
    }
    
    /**
     * Generate file name with tag and random number
     */
    private static String generateFileName() {
        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000; // 6-digit random number
        return currentTagName + "_" + randomNumber;
    }
    
    /**
     * Clean tag name to be file-safe
     */
    private static String cleanTagName(String tagName) {
        if (tagName == null) return "UnknownTag";
        
        // Remove @ symbol if present and clean special characters
        return tagName.replaceAll("[@#$%^&*()\\[\\]{}|\\\\:;\"'<>?/]", "")
                     .replaceAll("\\s+", "_")
                     .replaceAll("_+", "_");
    }
    
    /**
     * Create reports directory
     */
    private static void createReportsDirectory() {
        try {
            String reportDir = ConsolidatedReportConfig.getReportDirectory();
            Files.createDirectories(Paths.get(reportDir));
            System.out.println("📁 Report directory created/verified: " + reportDir);
        } catch (IOException e) {
            System.err.println("Failed to create reports directory: " + e.getMessage());
        }
    }
    
    /**
     * Get color for status (PDF)
     */
    private static com.itextpdf.kernel.colors.Color getStatusColor(String status) {
        switch (status.toUpperCase()) {
            case "PASSED":
                return ColorConstants.GREEN;
            case "FAILED":
                return ColorConstants.RED;
            case "SKIPPED":
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
    }
    
    /**
     * Simple JSON parsing to extract test name
     */
    private static String extractTestNameFromJson(String content) {
        try {
            int nameIndex = content.indexOf("\"name\":");
            if (nameIndex != -1) {
                int startQuote = content.indexOf("\"", nameIndex + 7);
                int endQuote = content.indexOf("\"", startQuote + 1);
                if (startQuote != -1 && endQuote != -1) {
                    return content.substring(startQuote + 1, endQuote);
                }
            }
        } catch (Exception e) {
            // Ignore parsing errors
        }
        return null;
    }
    
    /**
     * Simple JSON parsing to extract status
     */
    private static String extractStatusFromJson(String content) {
        try {
            int statusIndex = content.indexOf("\"status\":");
            if (statusIndex != -1) {
                int startQuote = content.indexOf("\"", statusIndex + 9);
                int endQuote = content.indexOf("\"", startQuote + 1);
                if (startQuote != -1 && endQuote != -1) {
                    return content.substring(startQuote + 1, endQuote);
                }
            }
        } catch (Exception e) {
            // Ignore parsing errors
        }
        return null;
    }
    
    /**
     * Get current tag name
     */
    public static String getCurrentTagName() {
        return currentTagName;
    }
    
    /**
     * Get current test results count
     */
    public static int getTestResultsCount() {
        return testResults.size();
    }
    
    /**
     * Clear all data (for new test runs)
     */
    public static void clearData() {
        testResults.clear();
        attachmentPaths.clear();
        currentTagName = "UnknownTag";
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
