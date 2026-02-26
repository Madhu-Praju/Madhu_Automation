package com.qea.utils;

import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.util.Units;
import java.io.*;
import java.nio.file.Files;
import java.util.Date;

public class WordReportUtil {
    private static XWPFDocument document;
    private static FileOutputStream out;
    private static String filePath;

    public static void startReport(String testName) throws IOException {
        document = new XWPFDocument();
        filePath = "screenshots/" + testName + "_TestSteps_" + System.currentTimeMillis() + ".docx";
        out = new FileOutputStream(filePath);
        XWPFParagraph title = document.createParagraph();
        XWPFRun run = title.createRun();
        run.setText("Test Steps Report: " + testName);
        run.setBold(true);
        run.setFontSize(16);
    }

    public static void addStep(String stepDescription, String screenshotPath) throws Exception {
        if (document == null) {
            throw new IllegalStateException("WordReportUtil: document is not initialized. Call startReport() before addStep().");
        }
        XWPFParagraph para = document.createParagraph();
        XWPFRun run = para.createRun();
        run.setText(new Date() + " - " + stepDescription);
        run.addBreak();
        if (screenshotPath != null && new File(screenshotPath).exists()) {
            try (InputStream pic = Files.newInputStream(new File(screenshotPath).toPath())) {
                run.addPicture(pic, Document.PICTURE_TYPE_PNG, screenshotPath, Units.toEMU(400), Units.toEMU(250));
            }
        }
        run.addBreak();
    }

    public static void endReport() throws IOException {
        if (document != null && out != null) {
            document.write(out);
            out.close();
            document.close();
        }
    }

    public static String getReportPath() {
        return filePath;
    }
    
    public static boolean isInitialized() {
        return document != null && filePath != null;
    }
}
