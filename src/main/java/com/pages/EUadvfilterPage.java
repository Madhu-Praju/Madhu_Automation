package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.mongodb.client.model.Filters;
import com.qea.utils.MongoDBUtil;
import com.qea.utils.configReader;

import java.util.Properties;

public class EUadvfilterPage {

    // Login Locators
    private By Welcomepage = By.xpath("//div[text()='Welcome']");
    private By Login = By.xpath("//button[@aria-label='LOG IN']");
    private By Orgname = By.id("organizationName");
    private By Continue1 = By.xpath("//button[text()='CONTINUE']");
    private By Username = By.id("username");
    private By Password = By.id("password");
    private By Continue2 = By.xpath("//button[text()='Continue']");
    private By Facctlistarrow = By.xpath("(//div[@class='product-card '])");
    private By Facctlistdashboard = By.xpath("//div[text()='Dashboard']");

    // Navigation Locators
    private By Watchlistmenu = By.xpath("(//span[@class='MuiTypography-root MuiTypography-body1 MuiListItemText-primary css-yb0lig'])[5]");
    private By Regulatorylist = By.xpath("(//span[text()='Regulatory list'])");
    private By Pagination = By.xpath("//button[@id='basic-button']");
    private By Pagevalue = By.xpath("//li[text()='100']");
    private By Eu = By.xpath("//div[text()='EU']");

    // Tab and Filter Locators
    private By Active = By.xpath("(//button[@id='simple-tab-0'])[2]");
    private By Active0 = By.xpath("//button[@aria-label='Active (0)']");
    private By Error = By.xpath("(//button[@id='simple-tab-1'])[2]");
    private By Error0 = By.xpath("//button[@aria-label='Error (0)']");
    private By Delete = By.xpath("//button[@id='simple-tab-2']");
    private By Delete0 = By.xpath("//button[@aria-label='Deleted (0)']");
    private By Delta = By.xpath("(//input[@type='checkbox'])[1]");
    private By New = By.xpath("(//button[@id='simple-tab-0'])[2]");
    private By New0 = By.xpath("//button[@aria-label='New (0)']");
    private By Amend = By.xpath("(//button[@id='simple-tab-1'])[2]");
    private By Amend0 = By.xpath("//button[@aria-label='Amended (0)']");
    private By DDelete = By.xpath("//button[@id='simple-tab-2']");
    private By DDelete0 = By.xpath("//button[@aria-label='Deleted (0 )']");
    private By Stable = By.xpath("//button[@id='simple-tab-3']");
    private By Stable0 = By.xpath("//button[@aria-label='Stable (0 )']");
    private By DError = By.xpath("//button[@id='simple-tab-4']");
    private By DError0 = By.xpath("//button[@aria-label='Error (0 )']");
    private By Filter = By.xpath("(//button[@id='record-table-filter-btn'])");

    // OFAC Filter Locators
    private By Cancel = By.xpath("//button[text()='CANCEL']");
    private By Clearall = By.xpath("//button[text()='CLEAR ALL']");
    private By Nofilter = By.xpath("//div[text()='No filters selected']");
    private By Nodata = By.xpath("//h5[text()='No data available']");

    private By Citizencountry = By.xpath("(//span[text()='Citizenship Country'])[1]");
    private By CitizenSearch = By.xpath("(//input[@placeholder='Search citizenship country'])");
    private By Citizenfilter = By.xpath("//input[@id='advance-filter-list-select-all-citizenshipDetailsList.countryName']");
    private By Designateddate = By.xpath("(//span[text()='Designated date'])[1]");
	private By Designatedate = By.xpath("(//input[@placeholder='DD/MM/YYYY'])[1]");
	private By Entrydate = By.xpath("//span[text()='Entry into force date']");
    private By Lastupdate = By.xpath("(//span[text()='Last Updated Date'])[1]");
    private By Startdate = By.xpath("(//input[@name='startDate'])");
    private By Enddate = By.xpath("(//input[@name='endDate'])");
    private By Publicationdate = By.xpath("//span[text()='Publication Date']");
    private By Type = By.xpath("(//span[text()='Type'])[1]");
    private By TypeSearch = By.xpath("(//input[@placeholder='Search type'])");
    private By Typefilter = By.xpath("//input[@id='advance-filter-list-select-all-entityTypeName']");

    // Action Locators
    private By Apply = By.xpath("(//button[@id='advance-filter-apply-btn'])");
    private By Downloadicon = By.xpath("(//button[@id='regulatory-records-download-btn'])");
    private By Excel = By.xpath("(//span[text()='Excel (.xlsx)'])");
    private By Tab = By.xpath("(//li[text()='Tab separated (.tsv)'])");
    private By Toaster = By.xpath("//div[text()='Request in progress. Check the Downloads tab for status']");
    private By Clearfilter = By.xpath("//button[text()='CLEAR FILTERS']");
    private By Selectall = By.xpath("//label[text()='Select all']");

    // Status Locators
    private By Downloads = By.xpath("//button[@id='simple-tab-1']");
    private By Success = By.xpath("(//tr[1]//td)[6]/div");
    private By Download = By.xpath("(//tr[1]//td)[7]/button");
    private By Refresh = By.xpath("//button[@aria-label='refresh button']");
    private By Status = By.xpath("//div[text()='Download started.']");

    private By Prname = By.xpath("//input[@id='advance-filter-list-561-Related']");

    // Pagination and Count Locators
    private By PaginationTotal = By.xpath("(//p[contains(text(),' of ')]//following-sibling::p | //span[contains(text(),' of ')])[last()]");
    private By FilteredCountText = By.xpath("//div[contains(@class,'MuiTablePagination')]//p[last()]");

    WebDriver driver;
    private int uiFilteredCount;

    public EUadvfilterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void facctlist_login_3(String url, String ornm, String usnm, String pswd) throws InterruptedException 
    {
        driver.get(url);Thread.sleep(2000);
        boolean logo1 = driver.findElement(Welcomepage).isDisplayed();
        if (logo1) {
            System.out.println("Welcome page is displayed");
        } else {
            System.out.println("Welcome is not displayed");
        }

        driver.findElement(Login).click();
        driver.findElement(Orgname).sendKeys(ornm);
        driver.findElement(Continue1).click();Thread.sleep(2000);
        driver.findElement(Username).sendKeys(usnm);
        driver.findElement(Password).sendKeys(pswd);
        driver.findElement(Continue2).click();Thread.sleep(5000);

        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        driver.findElement(Facctlistarrow).click();Thread.sleep(5000);

        boolean logo5 = driver.findElement(Facctlistdashboard).isDisplayed();
        if (logo5) {
            System.out.println("Facctlist Dashboard page is displayed");
        } else {
            System.out.println("Facctlist Dashboard page is not displayed");
        }
    }

    public void navigate_to_regulatory_list_3() throws InterruptedException 
    {
        driver.findElement(Watchlistmenu).click();Thread.sleep(2000);
        driver.findElement(Regulatorylist).click();Thread.sleep(1000);
    }

    public void select_eu_list() throws InterruptedException 
    {
        driver.findElement(Pagination).click();Thread.sleep(1000);
        driver.findElement(Pagevalue).click();Thread.sleep(1000);
        driver.findElement(Eu).click();
    }

    public void apply_eu_filter(String citizenship, String designatedate, String startdate, String enddate, String type, String citi) throws InterruptedException 
    {
        //ACTIVE TAB
        try {
            boolean logo1 = driver.findElement(Active0).isDisplayed();
            if(logo1 == true){
                System.out.println("No Data in Active Tab");
            }
            } 
            catch(Exception e) 
            {
            driver.findElement(Active).click();Thread.sleep(1000);

            driver.findElement(Filter).click();Thread.sleep(2000);
            driver.findElement(Cancel).click();Thread.sleep(2000);
                try 
                {
                boolean logo12 = driver.findElement(Filter).isDisplayed();
                if(logo12 == true)
                    {
                    System.out.println("Close Button is working");
                    }
                } 
                catch (Exception e5) 
                {
                System.out.println("Close Button not Working");
                }
            driver.findElement(Filter).click();Thread.sleep(2000);
            driver.findElement(Citizencountry).click();
            driver.findElement(Selectall).click();
            driver.findElement(Clearall).click();
            driver.findElement(Apply).click();Thread.sleep(3000);
                try
                {
                boolean logo11 = driver.findElement(Nofilter).isDisplayed();
                if(logo11 == true)
                    {
                    System.out.println("Clear All Button is Working");
                    System.out.println("No filter is added");
                    }
                }
                catch(Exception e4)
                {
                System.out.println("Filter is working without selecting");
                }

            driver.findElement(Filter).click();Thread.sleep(2000);
            driver.findElement(Citizencountry).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();Thread.sleep(3000);

            // --- Inline: Capture UI count and validate against MongoDB ---
            int uiCount = -1;
                try 
                {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                String allText = (String) js.executeScript("return document.body.innerText;");
                java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("(\\d+)\\s*-\\s*(\\d+)\\s+of\\s+(\\d+)");
                java.util.regex.Matcher matcher = pattern.matcher(allText);
                String lastCount = null;
                while (matcher.find()) {
                    lastCount = matcher.group(3);
                }
                if (lastCount != null) {
                    uiCount = Integer.parseInt(lastCount);
                }
                } catch (Exception ex) 
                {
                System.out.println("[apply_ofac_filter] Error capturing UI count: " + ex.getMessage());
                }
                System.out.println("[apply_ofac_filter] UI Filtered Count (Address Select All): " + uiCount);

            // Fetch count from MongoDB and compare
            long dbCount = -1;
            MongoDBUtil mongoUtil = new MongoDBUtil();
                try 
                {
                mongoUtil.connect();
                configReader confReader = new configReader();
                java.util.Properties prop = confReader.init_pop();
                String collectionName = prop.getProperty("mongo.collection.ruleclass", "facctumRegulatoryList");

                java.util.List<java.util.Map<String, String>> results = mongoUtil.findDocuments(collectionName,
                    Filters.and(
                        Filters.eq("listName", "EU"),
                        Filters.eq("statusId", 2000),
                        Filters.exists("citizenshipDetailsList", true)
                    ),
                    java.util.Arrays.asList("primaryName")
                );
                dbCount = results.size();
                System.out.println("[apply_ofac_filter] MongoDB Count (EU Active with ID Type): " + dbCount);
                } finally 
                {
                mongoUtil.disconnect();
                }

                if (uiCount >= 0 && uiCount == (int) dbCount) {
                System.out.println("[apply_ofac_filter] PASSED - UI count (" + uiCount + ") matches DB count (" + dbCount + ")");
                } else {
                System.out.println("[apply_ofac_filter] FAILED - UI count (" + uiCount + ") vs DB count (" + dbCount + ")");
                }
            // --- End inline DB validation ---

                try {
                boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
                if (logo9 == true) 
                {
                driver.findElement(Downloadicon).click();
                driver.findElement(Tab).click();Thread.sleep(4000);
                    try {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Excel).click();Thread.sleep(8000);
                    boolean logo = driver.findElement(Toaster).isDisplayed();Thread.sleep(2000);
                    if (logo == true)
                        {
                        System.out.println("Request is in progress");
                        driver.findElement(Clearfilter).click();
                        }   
                    } catch (Exception e2) 
                    {
                    System.out.println("Download is started for same filter");
                    }
                }
                    } 
                    catch (Exception e1) 
                    {
            System.out.println("Active Tab - No Records for ID Type filter");
            driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Designateddate).click();
            driver.findElement(Designatedate).click();
            driver.findElement(Designatedate).sendKeys(designatedate);
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo10 = driver.findElement(Downloadicon).isDisplayed();
                if (logo10 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("Active Tab - No Records for Designated date filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Entrydate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Enddate).click();
            driver.findElement(Enddate).sendKeys(enddate);
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
                boolean logo12 = driver.findElement(Downloadicon).isDisplayed();
                if (logo12 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("Active Tab - No Records for Entry Into force date filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Lastupdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Enddate).click();
            driver.findElement(Enddate).sendKeys(enddate);
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
                boolean logo13 = driver.findElement(Downloadicon).isDisplayed();
                if (logo13 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("Active Tab - No Records for Last Update date filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Publicationdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Enddate).click();
            driver.findElement(Enddate).sendKeys(enddate);
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
                boolean logo13 = driver.findElement(Downloadicon).isDisplayed();
                if (logo13 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("Active Tab - No Records for Publication date filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Type).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo14 = driver.findElement(Downloadicon).isDisplayed();
                if (logo14 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("Active Tab - No Records for Type filter");
                driver.findElement(Clearfilter).click();
            }

            //ACTIVE TAB ALL FILTERS
            driver.findElement(Filter).click();
            driver.findElement(Designateddate).click();
            driver.findElement(Designatedate).click();
            driver.findElement(Designatedate).sendKeys(designatedate);
            driver.findElement(Citizencountry).click();
            driver.findElement(CitizenSearch).click();
            driver.findElement(CitizenSearch).sendKeys(citi);Thread.sleep(2000);
            // Ref Data check By Entering Wrong Input
            try{
                boolean logo10 = driver.findElement(Nodata).isDisplayed();
                if (logo10 == true)
                {
                    System.out.println("Entered data is not in Ref Data");
                     driver.findElement(CitizenSearch).clear();
                }
            }
            catch(Exception e3){
                // No Data in Ref Catch
            }
            driver.findElement(Citizencountry).click();
            driver.findElement(CitizenSearch).click();
            driver.findElement(CitizenSearch).sendKeys(citizenship);
            driver.findElement(Entrydate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Lastupdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Publicationdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Type).click();
            driver.findElement(TypeSearch).click();
            driver.findElement(TypeSearch).sendKeys(type);
            driver.findElement(Typefilter).click();
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
            boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
            if (logo9 == true) {
                driver.findElement(Downloadicon).click();
                driver.findElement(Tab).click();Thread.sleep(4000);
                driver.findElement(Clearfilter).click();   
            }
        } catch (Exception e1) {
            System.out.println("Active Tab - No Records for All filter");
            driver.findElement(Clearfilter).click();
        }
        }

        // ERROR TAB
        try {
            boolean logo1 = driver.findElement(Error0).isDisplayed();
            if (logo1 == true) {
                System.out.println("No Data in Error Tab");
            }
        } catch (Exception e) {
            driver.findElement(Error).click();Thread.sleep(1000);

            driver.findElement(Filter).click();
            driver.findElement(Citizencountry).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
                boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
                if (logo9 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(3000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(Error).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Error Tab - No Records for Citizenship Country filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(Error).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Designateddate).click();
            driver.findElement(Designatedate).click();
            driver.findElement(Designatedate).sendKeys(designatedate);
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo10 = driver.findElement(Downloadicon).isDisplayed();
                if (logo10 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(Error).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Error Tab - No Records for Designated date filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(Error).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Entrydate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Enddate).click();
            driver.findElement(Enddate).sendKeys(enddate);
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
                boolean logo12 = driver.findElement(Downloadicon).isDisplayed();
                if (logo12 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(Error).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Error Tab - No Records for Entry in to force date filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(Error).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Lastupdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Enddate).click();
            driver.findElement(Enddate).sendKeys(enddate);
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
                boolean logo13 = driver.findElement(Downloadicon).isDisplayed();
                if (logo13 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(Error).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Error Tab - Active Tab - No Records for Last update date filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(Error).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Publicationdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Enddate).click();
            driver.findElement(Enddate).sendKeys(enddate);
            driver.findElement(Apply).click();Thread.sleep(3000);
            
            try {
                boolean logo14 = driver.findElement(Downloadicon).isDisplayed();
                if (logo14 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(Error).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Error Tab - Active Tab - No Records for Publication date filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(Error).click();Thread.sleep(1000);
            }
            driver.findElement(Filter).click();
            driver.findElement(Type).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo14 = driver.findElement(Downloadicon).isDisplayed();
                if (logo14 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(Delete).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Error Tab - No Records for Type filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(Delete).click();Thread.sleep(1000);
            }

            //ERROR TAB ALL FILTERS
            driver.findElement(Citizencountry).click();
            driver.findElement(CitizenSearch).click();
            driver.findElement(CitizenSearch).sendKeys(citizenship);
            driver.findElement(Designateddate).click();
            driver.findElement(Designatedate).click();
            driver.findElement(Designatedate).sendKeys(designatedate);
            driver.findElement(Entrydate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Lastupdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Publicationdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Type).click();
            driver.findElement(TypeSearch).click();
            driver.findElement(TypeSearch).sendKeys(type);
            driver.findElement(Typefilter).click();
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
            boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
            if (logo9 == true) {
                driver.findElement(Downloadicon).click();
                driver.findElement(Tab).click();Thread.sleep(4000);
                driver.findElement(Clearfilter).click(); 
                driver.findElement(Error).click();Thread.sleep(1000);  
            }
        } catch (Exception e1) {
            System.out.println("Error Tab - No Records for All filter");
            driver.findElement(Clearfilter).click();
            driver.findElement(Error).click();Thread.sleep(1000);
        }
        }

        // DELETE TAB
        try {
            boolean logo1 = driver.findElement(Delete0).isDisplayed();
            if (logo1 == true) {
                System.out.println("No Data in Delete Tab");
            }
        } catch (Exception e) {
            driver.findElement(Delete).click();Thread.sleep(1000);

            driver.findElement(Filter).click();
            driver.findElement(Citizencountry).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
                boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
                if (logo9 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(3000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(Delete).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Delete Tab - No Records for Citizenship country filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(Delete).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Designateddate).click();
            driver.findElement(Designatedate).click();
            driver.findElement(Designatedate).sendKeys(designatedate);
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo10 = driver.findElement(Downloadicon).isDisplayed();
                if (logo10 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(Error).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Delete Tab - No Records for Designated date filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(Delete).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Entrydate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Enddate).click();
            driver.findElement(Enddate).sendKeys(enddate);
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
                boolean logo12 = driver.findElement(Downloadicon).isDisplayed();
                if (logo12 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(Delete).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Delete Tab - No Records for Entry Into force date filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(Delete).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Lastupdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Enddate).click();
            driver.findElement(Enddate).sendKeys(enddate);
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
                boolean logo13 = driver.findElement(Downloadicon).isDisplayed();
                if (logo13 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(Delete).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Delete Tab - No Records for Last update date filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(Delete).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Publicationdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Enddate).click();
            driver.findElement(Enddate).sendKeys(enddate);
            driver.findElement(Apply).click();Thread.sleep(3000);
            
            try {
                boolean logo14 = driver.findElement(Downloadicon).isDisplayed();
                if (logo14 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(Delete).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Delete Tab - Active Tab - No Records for Publication date filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(Delete).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Type).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo14 = driver.findElement(Downloadicon).isDisplayed();
                if (logo14 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(Delete).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Delete Tab - No Records for Type filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(Delete).click();Thread.sleep(2000);
            }

            //DELETE TAB ALL FILTERS
            driver.findElement(Filter).click();
            driver.findElement(Citizencountry).click();
            driver.findElement(CitizenSearch).click();
            driver.findElement(CitizenSearch).sendKeys(citizenship);
            driver.findElement(Designateddate).click();
            driver.findElement(Designatedate).click();
            driver.findElement(Designatedate).sendKeys(designatedate);
            driver.findElement(Entrydate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Lastupdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Publicationdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Type).click();
            driver.findElement(TypeSearch).click();
            driver.findElement(TypeSearch).sendKeys(type);
            driver.findElement(Typefilter).click();
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
            boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
            if (logo9 == true) {
                driver.findElement(Downloadicon).click();
                driver.findElement(Tab).click();Thread.sleep(4000);
                driver.findElement(Clearfilter).click();
                driver.findElement(Delete).click();Thread.sleep(1000);   
            }
        } catch (Exception e1) {
            System.out.println("Delete Tab - No Records for All filter");
            driver.findElement(Clearfilter).click();
            driver.findElement(Delete).click();Thread.sleep(1000);
        }
        }

        driver.findElement(Delta).click();Thread.sleep(2000);


        // NEW TAB
        try {
            boolean logo1 = driver.findElement(New0).isDisplayed();
            if (logo1 == true) {
                System.out.println("No Data in New Tab");
            }
        } catch (Exception e) {
            driver.findElement(Filter).click();
            driver.findElement(Citizencountry).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
                boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
                if (logo9 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(3000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("New Tab - No Records for Citizenship country filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Designateddate).click();
            driver.findElement(Designatedate).click();
            driver.findElement(Designatedate).sendKeys(designatedate);
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
                boolean logo10 = driver.findElement(Downloadicon).isDisplayed();
                if (logo10 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("New Tab - No Records for Designated date filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Entrydate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Enddate).click();
            driver.findElement(Enddate).sendKeys(enddate);
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
                boolean logo12 = driver.findElement(Downloadicon).isDisplayed();
                if (logo12 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("New Tab - No Records for Entry into force date filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Lastupdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Enddate).click();
            driver.findElement(Enddate).sendKeys(enddate);
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
                boolean logo13 = driver.findElement(Downloadicon).isDisplayed();
                if (logo13 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("New Tab - No Records for Last Update date filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Publicationdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Enddate).click();
            driver.findElement(Enddate).sendKeys(enddate);
            driver.findElement(Apply).click();Thread.sleep(3000);
            
            try {
                boolean logo14 = driver.findElement(Downloadicon).isDisplayed();
                if (logo14 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("New Tab - Active Tab - No Records for Publication date filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Type).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo14 = driver.findElement(Downloadicon).isDisplayed();
                if (logo14 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("New Tab - No Records for Type filter");
                driver.findElement(Clearfilter).click();
            }

            //NEW TAB ALL FILTERS
            driver.findElement(Filter).click();
            driver.findElement(Citizencountry).click();
            driver.findElement(CitizenSearch).click();
            driver.findElement(CitizenSearch).sendKeys(citizenship);
            driver.findElement(Designateddate).click();
            driver.findElement(Designatedate).click();
            driver.findElement(Designatedate).sendKeys(designatedate);
            driver.findElement(Entrydate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Lastupdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Publicationdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Type).click();
            driver.findElement(TypeSearch).click();
            driver.findElement(TypeSearch).sendKeys(type);
            driver.findElement(Typefilter).click();
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
            boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
            if (logo9 == true) {
                driver.findElement(Downloadicon).click();
                driver.findElement(Tab).click();Thread.sleep(4000);
                driver.findElement(Clearfilter).click();   
            }
        } catch (Exception e1) {
            System.out.println("New tab - No Records for All filter");
            driver.findElement(Clearfilter).click();
        }
        }
        // AMEND TAB
        try {
            boolean logo1 = driver.findElement(Amend0).isDisplayed();
            if (logo1 == true) {
                System.out.println("No Data in Amend Tab");
            }
        } catch (Exception e) {
            driver.findElement(Filter).click();
            driver.findElement(Citizencountry).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
                boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
                if (logo9 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(3000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("Amend Tab - No Records for Citizenship country filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Designateddate).click();
            driver.findElement(Designatedate).click();
            driver.findElement(Designatedate).sendKeys(designatedate);
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo10 = driver.findElement(Downloadicon).isDisplayed();
                if (logo10 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("Amend Tab - No Records for Designated date filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Entrydate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Enddate).click();
            driver.findElement(Enddate).sendKeys(enddate);
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
                boolean logo12 = driver.findElement(Downloadicon).isDisplayed();
                if (logo12 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(Amend).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Amend Tab - No Records for Entry into force date filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(Amend).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Lastupdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Enddate).click();
            driver.findElement(Enddate).sendKeys(enddate);
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
                boolean logo13 = driver.findElement(Downloadicon).isDisplayed();
                if (logo13 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(Amend).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Amend Tab - No Records for Last update date filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(Amend).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Publicationdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Enddate).click();
            driver.findElement(Enddate).sendKeys(enddate);
            driver.findElement(Apply).click();Thread.sleep(3000);
            
            try {
                boolean logo14 = driver.findElement(Downloadicon).isDisplayed();
                if (logo14 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(Amend).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Amend Tab - Active Tab - No Records for Publication date filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(Amend).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Type).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo14 = driver.findElement(Downloadicon).isDisplayed();
                if (logo14 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(Amend).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Amend Tab - No Records for Type filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(Amend).click();Thread.sleep(1000);
            }

            //AMEND TAB ALL FILTERS
            driver.findElement(Filter).click();
            driver.findElement(Citizencountry).click();
            driver.findElement(CitizenSearch).click();
            driver.findElement(CitizenSearch).sendKeys(citizenship);
            driver.findElement(Designateddate).click();
            driver.findElement(Designatedate).click();
            driver.findElement(Designatedate).sendKeys(designatedate);
            driver.findElement(Entrydate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Lastupdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Publicationdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Type).click();
            driver.findElement(TypeSearch).click();
            driver.findElement(TypeSearch).sendKeys(type);
            driver.findElement(Typefilter).click();
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
            boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
            if (logo9 == true) {
                driver.findElement(Downloadicon).click();
                driver.findElement(Tab).click();Thread.sleep(4000);
                driver.findElement(Clearfilter).click();
                driver.findElement(Amend).click();Thread.sleep(1000);
   
            }
        } catch (Exception e1) {
            System.out.println("Amend Tab - No Records for All filter");
            driver.findElement(Clearfilter).click();
            driver.findElement(Amend).click();Thread.sleep(1000);

        }
        }

        // DELTA DELETE TAB
        try {
            boolean logo1 = driver.findElement(DDelete0).isDisplayed();
            if (logo1 == true) {
                System.out.println("No Data in Delta Delete Tab");
            }
        } catch (Exception e) {
            driver.findElement(DDelete).click();Thread.sleep(1000);

            driver.findElement(Filter).click();
            driver.findElement(Citizencountry).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
                boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
                if (logo9 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(3000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(DDelete).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Delta Delete Tab - No Records for Citizenship country filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(DDelete).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Designateddate).click();
            driver.findElement(Designatedate).click();
            driver.findElement(Designatedate).sendKeys(designatedate);
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo10 = driver.findElement(Downloadicon).isDisplayed();
                if (logo10 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(DDelete).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Delta Delete Tab - No Records for Designated date filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(DDelete).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Entrydate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Enddate).click();
            driver.findElement(Enddate).sendKeys(enddate);
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
                boolean logo12 = driver.findElement(Downloadicon).isDisplayed();
                if (logo12 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(DDelete).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Delta Delete Tab - No Records for Entry into force date filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(DDelete).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Lastupdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Enddate).click();
            driver.findElement(Enddate).sendKeys(enddate);
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
                boolean logo13 = driver.findElement(Downloadicon).isDisplayed();
                if (logo13 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(DDelete).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Delta Delete Tab - No Records for Last update date filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(DDelete).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Publicationdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Enddate).click();
            driver.findElement(Enddate).sendKeys(enddate);
            driver.findElement(Apply).click();Thread.sleep(3000);
            
            try {
                boolean logo14 = driver.findElement(Downloadicon).isDisplayed();
                if (logo14 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(DDelete).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Delta Delete Tab - Active Tab - No Records for Publication date filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(DDelete).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Type).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo14 = driver.findElement(Downloadicon).isDisplayed();
                if (logo14 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(DDelete).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Delta Delete Tab - No Records for Type filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(DDelete).click();Thread.sleep(1000);
            }

            //Delta Delete TAB ALL FILTERS
            driver.findElement(Filter).click();
            driver.findElement(Citizencountry).click();
            driver.findElement(CitizenSearch).click();
            driver.findElement(CitizenSearch).sendKeys(citizenship);
            driver.findElement(Designateddate).click();
            driver.findElement(Designatedate).click();
            driver.findElement(Designatedate).sendKeys(designatedate);
            driver.findElement(Entrydate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Lastupdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Publicationdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Type).click();
            driver.findElement(TypeSearch).click();
            driver.findElement(TypeSearch).sendKeys(type);
            driver.findElement(Typefilter).click();
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
            boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
            if (logo9 == true) {
                driver.findElement(Downloadicon).click();
                driver.findElement(Tab).click();Thread.sleep(4000);
                driver.findElement(Clearfilter).click();  
                driver.findElement(DDelete).click();Thread.sleep(1000);
 
            }
        } catch (Exception e1) {
            System.out.println("Delta Delete - No Records for All filter");
            driver.findElement(Clearfilter).click();
            driver.findElement(DDelete).click();Thread.sleep(1000);

        }
        }

        // STABLE TAB
        try {
            boolean logo1 = driver.findElement(Stable0).isDisplayed();
            if (logo1 == true) {
                System.out.println("No Data in Stable Tab");
            }
        } catch (Exception e) {
            driver.findElement(Stable).click();Thread.sleep(1000);

            driver.findElement(Filter).click();
            driver.findElement(Citizencountry).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
                boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
                if (logo9 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(3000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(Stable).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Stable Tab - No Records for Citizenship filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(Stable).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Designateddate).click();
            driver.findElement(Designatedate).click();
            driver.findElement(Designatedate).sendKeys(designatedate);
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo10 = driver.findElement(Downloadicon).isDisplayed();
                if (logo10 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(Stable).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Stable Tab - No Records for Designated date filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(Stable).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Entrydate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Enddate).click();
            driver.findElement(Enddate).sendKeys(enddate);
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
                boolean logo12 = driver.findElement(Downloadicon).isDisplayed();
                if (logo12 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Stable).click();
                    driver.findElement(Error).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Stable Tab - No Records for Entry in to force date filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(Stable).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Lastupdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Enddate).click();
            driver.findElement(Enddate).sendKeys(enddate);
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
                boolean logo13 = driver.findElement(Downloadicon).isDisplayed();
                if (logo13 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(Stable).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Stable Tab - No Records for Lat update date filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(Stable).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Publicationdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Enddate).click();
            driver.findElement(Enddate).sendKeys(enddate);
            driver.findElement(Apply).click();Thread.sleep(3000);
            
            try {
                boolean logo14 = driver.findElement(Downloadicon).isDisplayed();
                if (logo14 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(Stable).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Stable Tab - Active Tab - No Records for Publication date filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(Stable).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Type).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo14 = driver.findElement(Downloadicon).isDisplayed();
                if (logo14 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(Stable).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Stable Tab - No Records for Type filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(Stable).click();Thread.sleep(1000);
            }

            //STABLE TAB ALL FILTERS
            driver.findElement(Filter).click();
            driver.findElement(Citizencountry).click();
            driver.findElement(CitizenSearch).click();
            driver.findElement(CitizenSearch).sendKeys(citizenship);
            driver.findElement(Designateddate).click();
            driver.findElement(Designatedate).click();
            driver.findElement(Designatedate).sendKeys(designatedate);
            driver.findElement(Entrydate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Lastupdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Publicationdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Type).click();
            driver.findElement(TypeSearch).click();
            driver.findElement(TypeSearch).sendKeys(type);
            driver.findElement(Typefilter).click();
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
            boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
            if (logo9 == true) {
                driver.findElement(Downloadicon).click();
                driver.findElement(Tab).click();Thread.sleep(4000);
                driver.findElement(Clearfilter).click(); 
                driver.findElement(Stable).click();Thread.sleep(1000);  
            }
        } catch (Exception e1) {
            System.out.println("Stable Tab - No Records for All filter");
            driver.findElement(Clearfilter).click();
            driver.findElement(Stable).click();Thread.sleep(1000);
        }
        }
        // DELTA ERROR TAB
        try {
            boolean logo1 = driver.findElement(DError0).isDisplayed();
            if (logo1 == true) {
                System.out.println("No Data in Deleta Error Tab");
            }
        } catch (Exception e) {
            driver.findElement(DError).click();Thread.sleep(1000);

            driver.findElement(Filter).click();
            driver.findElement(Citizencountry).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
                boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
                if (logo9 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(3000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(DError).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Delta Error Tab - No Records for  filter Citizenship country");
                driver.findElement(Clearfilter).click();
                driver.findElement(DError).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Designateddate).click();
            driver.findElement(Designatedate).click();
            driver.findElement(Designatedate).sendKeys(designatedate);
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo10 = driver.findElement(Downloadicon).isDisplayed();
                if (logo10 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(DError).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Delta Error Tab - No Records for Designated date filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(Delete).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Entrydate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Enddate).click();
            driver.findElement(Enddate).sendKeys(enddate);
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
                boolean logo12 = driver.findElement(Downloadicon).isDisplayed();
                if (logo12 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(DError).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Delta Error Tab - No Records for Entry in to force date filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(DError).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Lastupdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Enddate).click();
            driver.findElement(Enddate).sendKeys(enddate);
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
                boolean logo13 = driver.findElement(Downloadicon).isDisplayed();
                if (logo13 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(DError).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Delta Error Tab - No Records for Last update date filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(DError).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Publicationdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Enddate).click();
            driver.findElement(Enddate).sendKeys(enddate);
            driver.findElement(Apply).click();Thread.sleep(3000);
            
            try {
                boolean logo14 = driver.findElement(Downloadicon).isDisplayed();
                if (logo14 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(DError).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Delta Error Tab - No Records for Publication date filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(DError).click();Thread.sleep(1000);
            }

            driver.findElement(Filter).click();
            driver.findElement(Type).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo14 = driver.findElement(Downloadicon).isDisplayed();
                if (logo14 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                    driver.findElement(DError).click();Thread.sleep(1000);
                }
            } catch (Exception e1) {
                System.out.println("Delta Error Tab - No Records for Type filter");
                driver.findElement(Clearfilter).click();
                driver.findElement(DError).click();Thread.sleep(1000);
            }

            //DELTA ERROR TAB ALL FILTERS
            driver.findElement(Filter).click();
            driver.findElement(Citizencountry).click();
            driver.findElement(CitizenSearch).click();
            driver.findElement(CitizenSearch).sendKeys(citizenship);
            driver.findElement(Designateddate).click();
            driver.findElement(Designatedate).click();
            driver.findElement(Designatedate).sendKeys(designatedate);
            driver.findElement(Entrydate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Lastupdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Publicationdate).click();
            driver.findElement(Startdate).click();
            driver.findElement(Startdate).sendKeys(startdate);
            driver.findElement(Type).click();
            driver.findElement(TypeSearch).click();
            driver.findElement(TypeSearch).sendKeys(type);
            driver.findElement(Typefilter).click();
            driver.findElement(Apply).click();Thread.sleep(3000);
            try {
            boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
            if (logo9 == true) {
                driver.findElement(Downloadicon).click();
                driver.findElement(Tab).click();Thread.sleep(4000);
                driver.findElement(Clearfilter).click(); 
                driver.findElement(DError).click();Thread.sleep(1000);  
            }
        } catch (Exception e1) {
            System.out.println("Deleta Error - No Records for All filter");
            driver.findElement(Clearfilter).click();
            driver.findElement(DError).click();Thread.sleep(1000);
        }
        }
    }

    public void check_download_status_3() throws InterruptedException 
    {
        driver.findElement(Downloads).click();Thread.sleep(3000);
        driver.findElement(Refresh).click();Thread.sleep(3000);
        for (int i = 0;i<100;i++) {
            driver.findElement(Refresh).click();Thread.sleep(5000);
            try{
                boolean stat = driver.findElement(Success).isDisplayed();
                if (stat == true){
                    driver.findElement(Download).click();
                    try{
                        boolean logo = driver.findElement(Status).isDisplayed();
                        if (logo == true){
                            System.out.println("Successfully download");
                            break;
                        }
                    }
                    catch(Exception e){
                        // Done
                    }
                }
            }
            catch(Exception e){
                // continue loop till get sucess
            }
            
        }

    }
    
}
