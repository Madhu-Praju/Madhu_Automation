package mytestrunner;
 
import org.testng.annotations.Listeners;
 
import com.qea.listeners.ConsolidatedReportListener;
import com.qea.listeners.TestExecutionListener;
 
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.qameta.allure.testng.AllureTestNg;
 
@Listeners({AllureTestNg.class, ConsolidatedReportListener.class, TestExecutionListener.class})
@CucumberOptions(
        features = {"src/test/resources/AppFeature/"},
        glue = {"stepDefenition", "AppHooks"},
        tags = "@OFACFilterDBValidation",
        //tags = "@Applyingoffilter",
        //tags = "@OFACADVANCEFILTER",
        //tags = "@UKSANCTIONSADVANCEFILTER",
        

        plugin = {"pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "html:target/cucumber-report.html",
                "json:target/cucumber-report.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        monochrome = true,
        publish = false
)
public class TestRunner extends AbstractTestNGCucumberTests {
    // This class will be used by TestNG to run the Cucumber features
}