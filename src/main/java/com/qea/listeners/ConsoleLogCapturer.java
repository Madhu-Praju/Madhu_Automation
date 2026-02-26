package com.qea.listeners;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.qea.utils.AllureReportManager;

/**
 * Console Log Capturer that intercepts all console outputs and stores them for Allure reporting
 * Captures System.out.println, System.err.println, and logger outputs
 * 
 * This capturer provides:
 * - Real-time console output capture
 * - Automatic attachment to Allure reports
 * - Thread-safe log collection
 * - Timestamped log entries
 * - Structured log formatting
 * 
 * @author Facctum Test Automation Team
 */
public class ConsoleLogCapturer {
    
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    
    // Thread-safe queue to store log entries
    private static final ConcurrentLinkedQueue<LogEntry> logEntries = new ConcurrentLinkedQueue<>();
    
    // Original streams to restore later
    private static PrintStream originalOut;
    private static PrintStream originalErr;
    
    // Custom streams for capturing
    private static CapturingPrintStream capturingOut;
    private static CapturingPrintStream capturingErr;
    
    // State tracking
    private static boolean isCapturing = false;
    private static String currentTestName = "UnknownTest";
    
    /**
     * Start capturing console outputs
     * Should be called at the beginning of test execution
     */
    public static void startCapturing() {
        if (isCapturing) {
            return; // Already capturing
        }
        
        try {
            // Store original streams
            originalOut = System.out;
            originalErr = System.err;
            
            // Create capturing streams
            capturingOut = new CapturingPrintStream(originalOut, LogLevel.INFO);
            capturingErr = new CapturingPrintStream(originalErr, LogLevel.ERROR);
            
            // Replace system streams
            System.setOut(capturingOut);
            System.setErr(capturingErr);
            
            isCapturing = true;
            
            logToOriginal("[CONSOLE CAPTURER] ✅ Console log capturing started", LogLevel.INFO);
            
        } catch (Exception e) {
            logToOriginal("[CONSOLE CAPTURER] ❌ Failed to start console capturing: " + e.getMessage(), LogLevel.ERROR);
        }
    }
    
    /**
     * Stop capturing console outputs and restore original streams
     * Should be called at the end of test execution
     */
    public static void stopCapturing() {
        if (!isCapturing) {
            return; // Not capturing
        }
        
        try {
            // Restore original streams
            System.setOut(originalOut);
            System.setErr(originalErr);
            
            isCapturing = false;
            
            logToOriginal("[CONSOLE CAPTURER] 🛑 Console log capturing stopped", LogLevel.INFO);
            
        } catch (Exception e) {
            logToOriginal("[CONSOLE CAPTURER] ❌ Failed to stop console capturing: " + e.getMessage(), LogLevel.ERROR);
        }
    }
    
    /**
     * Set the current test name for better log organization
     */
    public static void setCurrentTestName(String testName) {
        currentTestName = testName != null ? testName : "UnknownTest";
        addLogEntry("[TEST] Starting test: " + currentTestName, LogLevel.INFO);
    }
    
    /**
     * Get all captured logs as a formatted string
     */
    public static String getCapturedLogs() {
        StringBuilder logs = new StringBuilder();
        logs.append("=== CAPTURED CONSOLE LOGS ===\n");
        logs.append("Test: ").append(currentTestName).append("\n");
        logs.append("Capture Time: ").append(LocalDateTime.now().format(TIMESTAMP_FORMAT)).append("\n");
        logs.append("Total Entries: ").append(logEntries.size()).append("\n\n");
        
        for (LogEntry entry : logEntries) {
            logs.append("[").append(entry.timestamp).append("] ");
            logs.append("[").append(entry.level).append("] ");
            logs.append(entry.message).append("\n");
        }
        
        return logs.toString();
    }
    
    /**
     * Get captured logs filtered by level
     */
    public static String getCapturedLogsByLevel(LogLevel level) {
        StringBuilder logs = new StringBuilder();
        logs.append("=== CAPTURED ").append(level).append(" LOGS ===\n");
        logs.append("Test: ").append(currentTestName).append("\n\n");
        
        for (LogEntry entry : logEntries) {
            if (entry.level == level) {
                logs.append("[").append(entry.timestamp).append("] ");
                logs.append(entry.message).append("\n");
            }
        }
        
        return logs.toString();
    }
    
    /**
     * Attach captured logs to Allure report
     */
    public static void attachLogsToAllure() {
        try {
            String allLogs = getCapturedLogs();
            if (!allLogs.trim().isEmpty()) {
                AllureReportManager.attachText("Console Logs - " + currentTestName, allLogs);
                
                // Also attach error logs separately if any exist
                String errorLogs = getCapturedLogsByLevel(LogLevel.ERROR);
                if (errorLogs.contains("] ")) { // Check if there are actual error entries
                    AllureReportManager.attachText("Error Logs - " + currentTestName, errorLogs);
                }
                
                logToOriginal("[CONSOLE CAPTURER] 📎 Logs attached to Allure report", LogLevel.INFO);
            }
        } catch (Exception e) {
            logToOriginal("[CONSOLE CAPTURER] ❌ Failed to attach logs to Allure: " + e.getMessage(), LogLevel.ERROR);
        }
    }
    
    /**
     * Clear captured logs (useful between tests)
     */
    public static void clearLogs() {
        logEntries.clear();
        logToOriginal("[CONSOLE CAPTURER] 🧹 Captured logs cleared", LogLevel.INFO);
    }
    
    /**
     * Get the number of captured log entries
     */
    public static int getLogCount() {
        return logEntries.size();
    }
    
    /**
     * Add a log entry to the captured logs
     */
    private static void addLogEntry(String message, LogLevel level) {
        if (message != null && !message.trim().isEmpty()) {
            LogEntry entry = new LogEntry(
                LocalDateTime.now().format(TIMESTAMP_FORMAT),
                level,
                message.trim()
            );
            logEntries.offer(entry);
        }
    }
    
    /**
     * Log directly to original stream (bypassing capture)
     */
    private static void logToOriginal(String message, LogLevel level) {
        try {
            PrintStream target = level == LogLevel.ERROR ? 
                (originalErr != null ? originalErr : System.err) : 
                (originalOut != null ? originalOut : System.out);
            
            target.println("[" + LocalDateTime.now().format(TIMESTAMP_FORMAT) + "] " + message);
        } catch (Exception e) {
            // Fallback to System.out if original stream is not available
            System.out.println("[CONSOLE CAPTURER ERROR] " + message + " | " + e.getMessage());
        }
    }
    
    /**
     * Custom PrintStream that captures output while still displaying it
     */
    private static class CapturingPrintStream extends PrintStream {
        private final PrintStream originalStream;
        private final LogLevel logLevel;
        
        public CapturingPrintStream(PrintStream originalStream, LogLevel logLevel) {
            super(new ByteArrayOutputStream());
            this.originalStream = originalStream;
            this.logLevel = logLevel;
        }
        
        @Override
        public void println(String message) {
            // Display in original stream
            originalStream.println(message);
            
            // Capture for Allure
            addLogEntry(message, logLevel);
        }
        
        @Override
        public void println(Object obj) {
            println(obj != null ? obj.toString() : "null");
        }
        
        @Override
        public void print(String message) {
            originalStream.print(message);
            // Note: Not capturing print() calls, only println() for cleaner logs
        }
        
        @Override
        public void print(Object obj) {
            print(obj != null ? obj.toString() : "null");
        }
        
        @Override
        public PrintStream printf(String format, Object... args) {
            String message = String.format(format, args);
            originalStream.printf(format, args);
            
            // Only capture if it ends with newline
            if (format.endsWith("%n") || format.endsWith("\n")) {
                addLogEntry(message.replace("\n", "").replace("\r", ""), logLevel);
            }
            
            return this;
        }
        
        @Override
        public void flush() {
            originalStream.flush();
        }
        
        @Override
        public void close() {
            originalStream.close();
        }
    }
    
    /**
     * Log entry structure
     */
    private static class LogEntry {
        final String timestamp;
        final LogLevel level;
        final String message;
        
        LogEntry(String timestamp, LogLevel level, String message) {
            this.timestamp = timestamp;
            this.level = level;
            this.message = message;
        }
    }
    
    /**
     * Log levels
     */
    public enum LogLevel {
        INFO, ERROR, DEBUG, WARN
    }
}