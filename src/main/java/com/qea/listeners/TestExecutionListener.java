package com.qea.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qea.utils.TestSuiteManager;

/**
 * TestNG Listener for handling suite-level and test-level events
 * Integrates with console log capturer and Allure reporting
 * 
 * @author Facctum Test Automation Team
 */
public class TestExecutionListener implements ISuiteListener, ITestListener {
    
    @Override
    public void onStart(ISuite suite) {
        System.out.println("🚀 TestNG Suite Started: " + suite.getName());
        // Suite-level initialization is handled in ApplicationHooks.setupTestSuite()
    }
    
    @Override
    public void onFinish(ISuite suite) {
        System.out.println("🏁 TestNG Suite Finished: " + suite.getName());
        
        // Perform suite-level cleanup
        try {
            TestSuiteManager.cleanupSuite();
        } catch (Exception e) {
            System.err.println("⚠️ Error during suite cleanup: " + e.getMessage());
        }
    }
    
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("▶️ Test Started: " + testName);
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("✅ Test Passed: " + testName);
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("❌ Test Failed: " + testName);
        
        if (result.getThrowable() != null) {
            System.out.println("Failure reason: " + result.getThrowable().getMessage());
        }
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("⏭️ Test Skipped: " + testName);
    }
}