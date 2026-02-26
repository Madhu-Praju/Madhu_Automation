import com.qea.utils.SimpleReportConsolidator;

/**
 * Simple test class to verify the consolidated report generation
 */
public class ReportGeneratorTest {
    
    public static void main(String[] args) {
        try {
            System.out.println("🧪 Testing Consolidated Report Generator...");
            
            // Test the simple report consolidator
            String reportPath = SimpleReportConsolidator.generateConsolidatedReport("GlobalSearch");
            
            if (reportPath != null) {
                System.out.println("✅ Report generated successfully: " + reportPath);
            } else {
                System.out.println("❌ Report generation failed");
            }
            
        } catch (Exception e) {
            System.err.println("❌ Error during test: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
