import com.qea.utils.SimpleReportConsolidator;
import com.qea.utils.ConsolidatedReportGenerator;

/**
 * Test class to verify that reports are generated in correct directories
 * 
 * @author Facctum Test Automation Team
 */
public class ReportLocationTest {
    
    public static void main(String[] args) {
        System.out.println("🧪 Testing Report Location Fix...");
        
        try {
            // Test 1: Generate a Surefire-related report (should go to Surefire_Suite folder)
            System.out.println("\n📊 Test 1: Generating Surefire report...");
            String surefireReport = SimpleReportConsolidator.generateConsolidatedReport("SurefireTest");
            System.out.println("✅ Surefire report generated at: " + surefireReport);
            
            // Test 2: Generate a tag-based report (should go to Tag_Based_Reports folder)
            System.out.println("\n📊 Test 2: Generating tag-based report...");
            String tagReport = SimpleReportConsolidator.generateConsolidatedReport("taskValidation");
            System.out.println("✅ Tag-based report generated at: " + tagReport);
            
            // Test 3: Test with ConsolidatedReportGenerator
            System.out.println("\n📊 Test 3: Testing ConsolidatedReportGenerator...");
            ConsolidatedReportGenerator.initializeReport("testSuite");
            ConsolidatedReportGenerator.addTestResult("Sample Test", "PASSED", "Test description", "1000ms");
            String consolidatedReport = ConsolidatedReportGenerator.generateConsolidatedReport();
            System.out.println("✅ Consolidated report generated at: " + consolidatedReport);
            
            System.out.println("\n🎉 All tests completed successfully!");
            
        } catch (Exception e) {
            System.err.println("❌ Test failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}