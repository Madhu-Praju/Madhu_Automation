package com.qea.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import com.qea.utils.SimpleReportConsolidator;

/**
 * TestNG Listener for Consolidated Report Generation
 * Automatically generates consolidated reports at suite completion
 * 
 * @author Facctum Test Automation Team
 */
public class ConsolidatedReportListener implements ITestListener, ISuiteListener {
    
    private static boolean reportGenerated = false;
    private static String currentTag = "GlobalSearch"; // Default tag
    
    @Override
    public void onStart(ISuite suite) {
        System.out.println("📊 Consolidated Report Listener: Suite started - " + suite.getName());
        reportGenerated = false;
        
        // Try to extract tag from suite name or parameters
        String suiteName = suite.getName();
        if (suiteName != null && !suiteName.isEmpty()) {
            currentTag = suiteName;
        }
    }
    
    @Override
    public void onFinish(ISuite suite) {
        if (!reportGenerated) {
            System.out.println("📊 Consolidated Report Listener: Suite finished - " + suite.getName());
            System.out.println("📄 Generating final consolidated report...");
            
            try {
                String reportPath = SimpleReportConsolidator.generateConsolidatedReport(currentTag);
                if (reportPath != null) {
                    System.out.println("✅ Final consolidated report generated: " + reportPath);
                } else {
                    System.out.println("⚠️ Failed to generate consolidated report");
                }
                reportGenerated = true;
            } catch (Exception e) {
                System.err.println("❌ Error generating consolidated report: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("🧪 Test started: " + testName);
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        long duration = result.getEndMillis() - result.getStartMillis();
        
        System.out.println("✅ Test passed: " + testName + " (Duration: " + duration + "ms)");
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        long duration = result.getEndMillis() - result.getStartMillis();
        String errorMessage = result.getThrowable() != null ? result.getThrowable().getMessage() : "Unknown error";
        
        System.out.println("❌ Test failed: " + testName + " (Duration: " + duration + "ms)");
        System.out.println("Error: " + errorMessage);
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String skipReason = result.getThrowable() != null ? result.getThrowable().getMessage() : "Test skipped";
        
        System.out.println("⏭️ Test skipped: " + testName + " - " + skipReason);
    }
}
