package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class OFACadvfilterPage {

    // Login Locators
    private By Welcomepage = By.xpath("//div[text()='Welcome']");
    private By Login = By.xpath("//button[@aria-label='LOG IN']");
    private By Orgpage = By.xpath("//h1[text()='Welcome']");
    private By Orgname = By.id("organizationName");
    private By Continue1 = By.xpath("//button[text()='CONTINUE']");
    private By Loginpage = By.xpath("//h1[text()='Welcome']");
    private By Username = By.id("username");
    private By Password = By.id("password");
    private By Forgotpass = By.xpath("//a[text()='Forgot password?']");
    private By Continue2 = By.xpath("//button[text()='Continue']");
    private By Facctlistarrow = By.xpath("(//div[@class='product-card '])");
    private By Facctlistdashboard = By.xpath("//div[text()='Dashboard']");

    // Navigation Locators
    private By Watchlistmenu = By.xpath("(//span[@class='MuiTypography-root MuiTypography-body1 MuiListItemText-primary css-yb0lig'])[5]");
    private By Regulatorylist = By.xpath("(//span[text()='Regulatory list'])");
    private By Pagination = By.xpath("//button[@id='basic-button']");
    private By Pagevalue = By.xpath("//li[text()='100']");
    //private By Ofac = By.xpath("//div[text()='OFAC']");
    private By OfacEn = By.xpath("//div[text()='OFAC Enhanced']");

    // Tab and Filter Locators
    private By Active = By.xpath("(//button[@id='simple-tab-0'])[2]");
    private By Active0 = By.xpath("//button[@aria-label='Active (0)']");
    private By Error = By.xpath("(//button[@id='simple-tab-1'])[2]");
    private By Error0 = By.xpath("//button[@aria-label='Error (0)']");
    private By Delete = By.xpath("//button[@id='simple-tab-2']");
    private By Delete0 = By.xpath("//button[@aria-label='Deleted (0)']");
    //private By Delta = By.xpath("(//span[@class='MuiTouchRipple-root css-w0pj6f'])[21]");
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
    private By AddressCountry = By.xpath("(//span[text()='Address country'])[1]");
    private By AddressSearch = By.xpath("(//input[@placeholder='Search address country'])");
    private By Addresfilter = By.xpath("//input[@id='advance-filter-list-select-all-addressDetailsList.countryName']");
    private By Citizencountry = By.xpath("(//span[text()='Citizenship country'])[1]");
    private By CitizenSearch = By.xpath("(//input[@placeholder='Search citizenship country'])");
    private By Citizenfilter = By.xpath("//input[@id='advance-filter-list-select-all-citizenshipDetailsList.countryName']");
    private By Lastupdate = By.xpath("(//span[text()='Last Updated Date'])[1]");
    private By Startdate = By.xpath("(//input[@name='startDate'])");
    private By Enddate = By.xpath("(//input[@name='endDate'])");
    private By Nationalcountry = By.xpath("(//span[text()='Nationality country'])[1]");
    private By NationalSearch = By.xpath("(//input[@placeholder='Search nationality country'])");
    private By Nationalfilter = By.xpath("//input[@id='advance-filter-list-select-all-nationalityDetailsList.countryName']");
    private By Programname = By.xpath("(//span[text()='Program name'])[1]");
    private By ProgramSearch = By.xpath("(//input[@placeholder='Search program name'])");
    private By Programfilter = By.xpath("//input[@id='advance-filter-list-select-all-sanctionProgramDetailsList.programName']");
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
    private By Initiated = By.xpath("//div[text()='Request initiated. Check status at Downloads tab']");
    private By Downloads = By.xpath("//button[@id='simple-tab-1']");
    private By Firstrow = By.xpath("(//tr[@class='MuiTableRow-root table-row css-1a11t6r'])[1]");
    private By Started = By.xpath("(//div[text()='Started'])[1]");
    private By Success = By.xpath("(//tr[1]//td)[6]/div");
    private By Failed = By.xpath("(//div[text()='Failed'])[1]");
    private By Activerecord = By.xpath("(//div[text()='Active record download'])[1]");
    private By Download = By.xpath("(//tr[1]//td)[7]/button");
    private By Refresh = By.xpath("//div[@aria-label='refresh button']");

    private By Prname = By.xpath("//input[@id='advance-filter-list-561-Related']");

    WebDriver driver;

    public OFACadvfilterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void facctlist_login_1(String url, String ornm, String usnm, String pswd) throws InterruptedException {
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

        driver.findElement(Facctlistarrow).click();
        Thread.sleep(5000);

        boolean logo5 = driver.findElement(Facctlistdashboard).isDisplayed();
        if (logo5) {
            System.out.println("Facctlist Dashboard page is displayed");
        } else {
            System.out.println("Facctlist Dashboard page is not displayed");
        }
    }

    public void navigate_to_regulatory_list_1() throws InterruptedException {
        driver.findElement(Watchlistmenu).click();Thread.sleep(4000);
        driver.findElement(Regulatorylist).click();Thread.sleep(3000);
    }

    public void select_ofac_list() throws InterruptedException {
        driver.findElement(Pagination).click();Thread.sleep(2000);
        driver.findElement(Pagevalue).click();Thread.sleep(2000);
        driver.findElement(OfacEn).click();Thread.sleep(3000);
    }

    public void apply_ofac_filter(String address, String citizenship, String startdate, 
            String enddate, String nationality, String program, String type) throws InterruptedException {
          
        //ACTIVE TAB
        try {
            boolean logo1 = driver.findElement(Active0).isDisplayed();
            if(logo1 == true){
                System.out.println("No Data in Active Tab");
            }
        } catch(Exception e) {
            driver.findElement(Active).click();Thread.sleep(1000);

            driver.findElement(Filter).click();
            driver.findElement(AddressCountry).click();
            driver.findElement(Selectall).click();
            // driver.findElement(Programname).click();
            // driver.findElement(Prname).click();


            driver.findElement(Apply).click();Thread.sleep(3000);
        try {
            boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
            if (logo9 == true) {
                driver.findElement(Downloadicon).click();
                driver.findElement(Tab).click();Thread.sleep(3000);
                try {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Excel).click();Thread.sleep(8000);
                    boolean logo = driver.findElement(Toaster).isDisplayed();Thread.sleep(2000);
                    if (logo == true){
                        System.out.println("Request is in progress");
                        driver.findElement(Clearfilter).click();
                    }   
                } catch (Exception e2) {
                    System.out.println("Download is started for same filter");
                }
            }
        } catch (Exception e1) {
            System.out.println("Active Tab");
            System.out.println("No Records for Address filter");
            driver.findElement(Clearfilter).click();
        }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Citizencountry).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo10 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo10 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Active Tab");
        //     System.out.println("No Records for Citizenship filter");
        //     driver.findElement(Clearfilter).click();
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Lastupdate).click();
        //     driver.findElement(Startdate).click();
        //     driver.findElement(Startdate).sendKeys(startdate);
        //     driver.findElement(Enddate).click();
        //     driver.findElement(Enddate).sendKeys(enddate);
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Active Tab");
        //     System.out.println("No Records for last update date filter");
        //     driver.findElement(Clearfilter).click();
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Nationalcountry).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Active Tab");
        //     System.out.println("No Records for National filter");
        //     driver.findElement(Clearfilter).click();
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Programname).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Active Tab");
        //     System.out.println("No Records for Program name filter");
        //     driver.findElement(Clearfilter).click();
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Type).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Active Tab");
        //     System.out.println("No Records for Address filter");
        //     driver.findElement(Clearfilter).click();
        // }
        //     //ACTIVE TAB ALL FILTERS
        //     driver.findElement(Filter).click();
        //     driver.findElement(AddressCountry).click();
        //     driver.findElement(AddressSearch).click();
        //     driver.findElement(AddressSearch).sendKeys(address);
        //     driver.findElement(Addresfilter).click();
        //     driver.findElement(Citizencountry).click();
        //     driver.findElement(CitizenSearch).click();
        //     driver.findElement(CitizenSearch).sendKeys(citizenship);
        //     driver.findElement(Citizenfilter).click();
        //     driver.findElement(Lastupdate).click();
        //     driver.findElement(Startdate).click();
        //     driver.findElement(Startdate).sendKeys(startdate);
        //     driver.findElement(Enddate).click();
        //     driver.findElement(Enddate).sendKeys(enddate);
        //     driver.findElement(Nationalcountry).click();
        //     driver.findElement(NationalSearch).click();
        //     driver.findElement(NationalSearch).sendKeys(nationality);
        //     driver.findElement(Nationalfilter).click();
        //     driver.findElement(Programname).click();
        //     driver.findElement(ProgramSearch).click();
        //     driver.findElement(ProgramSearch).sendKeys(program);
        //     driver.findElement(Programfilter).click();
        //     driver.findElement(Type).click();
        //     driver.findElement(TypeSearch).click();
        //     driver.findElement(TypeSearch).sendKeys(type);
        //     driver.findElement(Typefilter).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Active Tab");
        //     System.out.println("No Records for All filter");
        //     driver.findElement(Clearfilter).click();
        // }
        }


        // //ERROR TAB
        // try {
        //     boolean logo2 = driver.findElement(Error0).isDisplayed();
        //     if(logo2 == true){
        //         System.out.println("No Data in Error Tab");
        //     }
        // } catch(Exception e) {
        //     driver.findElement(Error).click();Thread.sleep(1000);

        //     driver.findElement(Filter).click();
        //     driver.findElement(AddressCountry).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(Error).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Error Tab");
        //     System.out.println("No Records for Address filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(Error).click();Thread.sleep(1000);
        // }
        //     driver.findElement(Filter).click();
        //     driver.findElement(Citizencountry).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(Error).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Error Tab");
        //     System.out.println("No Records for Citizenship filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(Error).click();Thread.sleep(1000);
        // }
        //     driver.findElement(Filter).click();
        //     driver.findElement(Lastupdate).click();
        //     driver.findElement(Startdate).click();
        //     driver.findElement(Startdate).sendKeys(startdate);
        //     driver.findElement(Enddate).click();
        //     driver.findElement(Enddate).sendKeys(enddate);
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(Error).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Error Tab");
        //     System.out.println("No Records for Last update date filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(Error).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Nationalcountry).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(Error).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Error Tab");
        //     System.out.println("No Recors for National country filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(Error).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Programname).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(Error).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Error Tab");
        //     System.out.println("No Records for Program Name filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(Error).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Type).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(Error).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Error Tab");
        //     System.out.println("No Records for type filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(Error).click();Thread.sleep(1000);
        // }
            
        //     //ERROR TAB ALL FILTERS
        //     driver.findElement(Filter).click();
        //     driver.findElement(AddressCountry).click();
        //     driver.findElement(AddressSearch).click();
        //     driver.findElement(AddressSearch).sendKeys(address);
        //     driver.findElement(Addresfilter).click();
        //     driver.findElement(Citizencountry).click();
        //     driver.findElement(CitizenSearch).click();
        //     driver.findElement(CitizenSearch).sendKeys(citizenship);
        //     driver.findElement(Citizenfilter).click();
        //     driver.findElement(Lastupdate).click();
        //     driver.findElement(Startdate).click();
        //     driver.findElement(Startdate).sendKeys(startdate);
        //     driver.findElement(Enddate).click();
        //     driver.findElement(Enddate).sendKeys(enddate);
        //     driver.findElement(Nationalcountry).click();
        //     driver.findElement(NationalSearch).click();
        //     driver.findElement(NationalSearch).sendKeys(nationality);
        //     driver.findElement(Nationalfilter).click();
        //     driver.findElement(Programname).click();
        //     driver.findElement(ProgramSearch).click();
        //     driver.findElement(ProgramSearch).sendKeys(program);
        //     driver.findElement(Programfilter).click();
        //     driver.findElement(Type).click();
        //     driver.findElement(TypeSearch).click();
        //     driver.findElement(TypeSearch).sendKeys(type);
        //     driver.findElement(Typefilter).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Error Tab");
        //     System.out.println("No records for All filter");
        //     driver.findElement(Clearfilter).click();
        // }
        // }

        // //DELETE TAB
        // try {
        //     boolean logo3 = driver.findElement(Delete0).isDisplayed();
        //     if(logo3 == true){
        //         System.out.println("No Data in Delete Tab");
        //     }
        // } catch(Exception e) {
        //     driver.findElement(Delete).click();Thread.sleep(1000);

        //     driver.findElement(Filter).click();
        //     driver.findElement(AddressCountry).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(Delete).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Delete Tab");
        //     System.out.println("No Record for Address filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(Delete).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Citizencountry).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(Delete).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Delete Tab");
        //     System.out.println("No Record for Citizenship filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(Delete).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Lastupdate).click();
        //     driver.findElement(Startdate).click();
        //     driver.findElement(Startdate).sendKeys(startdate);
        //     driver.findElement(Enddate).click();
        //     driver.findElement(Enddate).sendKeys(enddate);
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(Delete).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Delete Tab");
        //     System.out.println("No records for last update date filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(Delete).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Nationalcountry).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(Delete).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Delete Tab");
        //     System.out.println("No Records for National filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(Delete).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Programname).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(Delete).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Delete Tab");
        //     System.out.println("No records for Program name filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(Delete).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Type).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(Delete).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Delete Tab");
        //     System.out.println("No Records for type filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(Delete).click();Thread.sleep(1000);
        // }
            
        //     //DELETE ALL FILTERS
        //     driver.findElement(Filter).click();
        //     driver.findElement(AddressCountry).click();
        //     driver.findElement(AddressSearch).click();
        //     driver.findElement(AddressSearch).sendKeys(address);
        //     driver.findElement(Addresfilter).click();
        //     driver.findElement(Citizencountry).click();
        //     driver.findElement(CitizenSearch).click();
        //     driver.findElement(CitizenSearch).sendKeys(citizenship);
        //     driver.findElement(Citizenfilter).click();
        //     driver.findElement(Lastupdate).click();
        //     driver.findElement(Startdate).click();
        //     driver.findElement(Startdate).sendKeys(startdate);
        //     driver.findElement(Enddate).click();
        //     driver.findElement(Enddate).sendKeys(enddate);
        //     driver.findElement(Nationalcountry).click();
        //     driver.findElement(NationalSearch).click();
        //     driver.findElement(NationalSearch).sendKeys(nationality);
        //     driver.findElement(Nationalfilter).click();
        //     driver.findElement(Programname).click();
        //     driver.findElement(ProgramSearch).click();
        //     driver.findElement(ProgramSearch).sendKeys(program);
        //     driver.findElement(Programfilter).click();
        //     driver.findElement(Type).click();
        //     driver.findElement(TypeSearch).click();
        //     driver.findElement(TypeSearch).sendKeys(type);
        //     driver.findElement(Typefilter).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Delete Tab");
        //     System.out.println("No Records for all filter");
        //     driver.findElement(Clearfilter).click();
        // }
        // }

        // driver.findElement(Delta).click();Thread.sleep(3000);


        // //NEW TAB
        // try {
        //     boolean logo4 = driver.findElement(New0).isDisplayed();
        //     if(logo4 == true){
        //         System.out.println("No Data in New Tab");
        //     }
        // } catch(Exception e) {
        //     driver.findElement(New).click();Thread.sleep(1000);

        //     driver.findElement(Filter).click(); 
        //     driver.findElement(AddressCountry).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(New).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("New Tab");
        //     System.out.println("No Recod for address filter");
        //     driver.findElement(New).click();Thread.sleep(1000);
        // }
        //     driver.findElement(Filter).click();
        //     driver.findElement(Citizencountry).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //      try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(New).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("New Tab");
        //     System.out.println("No Records for citizenship filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(New).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Lastupdate).click();
        //     driver.findElement(Startdate).click();
        //     driver.findElement(Startdate).sendKeys(startdate);
        //     driver.findElement(Enddate).click();
        //     driver.findElement(Enddate).sendKeys(enddate);
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //      try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(New).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("New Tab");
        //     System.out.println("No records for last updatre date filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(New).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Nationalcountry).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //      try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(New).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("New Tab");
        //     System.out.println("No records for national filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(New).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Programname).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //      try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(New).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("New Tab");
        //     System.out.println("No records for program name");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(New).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Type).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //      try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(New).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("New Tab");
        //     System.out.println("No records for type filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(New).click();Thread.sleep(1000);
        // }
            
        //     //NEW ALL FILTERS
        //     driver.findElement(Filter).click();
        //     driver.findElement(AddressCountry).click();
        //     driver.findElement(AddressSearch).click();
        //     driver.findElement(AddressSearch).sendKeys(address);
        //     driver.findElement(Addresfilter).click();
        //     driver.findElement(Citizencountry).click();
        //     driver.findElement(CitizenSearch).click();
        //     driver.findElement(CitizenSearch).sendKeys(citizenship);
        //     driver.findElement(Citizenfilter).click();
        //     driver.findElement(Lastupdate).click();
        //     driver.findElement(Startdate).click();
        //     driver.findElement(Startdate).sendKeys(startdate);
        //     driver.findElement(Enddate).click();
        //     driver.findElement(Enddate).sendKeys(enddate);
        //     driver.findElement(Nationalcountry).click();
        //     driver.findElement(NationalSearch).click();
        //     driver.findElement(NationalSearch).sendKeys(nationality);
        //     driver.findElement(Nationalfilter).click();
        //     driver.findElement(Programname).click();
        //     driver.findElement(ProgramSearch).click();
        //     driver.findElement(ProgramSearch).sendKeys(program);
        //     driver.findElement(Programfilter).click();
        //     driver.findElement(Type).click();
        //     driver.findElement(TypeSearch).click();
        //     driver.findElement(TypeSearch).sendKeys(type);
        //     driver.findElement(Typefilter).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //      try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("New Tab");
        //     System.out.println("No records for all filter");
        //     driver.findElement(Clearfilter).click();
        // }
        // }

        // //AMEND TAB
        // try {
        //     boolean logo5 = driver.findElement(Amend0).isDisplayed();
        //     if(logo5 == true){
        //         System.out.println("No Data in Amend Tab");
        //     }
        // } catch(Exception e) {
        //     driver.findElement(Amend).click();Thread.sleep(1000);

        //     driver.findElement(Filter).click(); 
        //     driver.findElement(AddressCountry).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(Amend).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Amend Tab");
        //     System.out.println("No Records for address filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(Amend).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Citizencountry).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(Amend).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Amend Tab");
        //     System.out.println("No records for citizen filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(Amend).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Lastupdate).click();
        //     driver.findElement(Startdate).click();
        //     driver.findElement(Startdate).sendKeys(startdate);
        //     driver.findElement(Enddate).click();
        //     driver.findElement(Enddate).sendKeys(enddate);
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //    try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(Amend).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Amend Tab");
        //     System.out.println("No records for last update date filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(Amend).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Nationalcountry).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(Amend).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Amend Tab");
        //     System.out.println("No records for national filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(Amend).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Programname).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(Amend).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Amend Tab");
        //     System.out.println("No records for program filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(Amend).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Type).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(Amend).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Amend Tab");
        //     System.out.println("No records for type filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(Amend).click();Thread.sleep(1000);
        // }
            
        //     //AMEND ALL FILTERS
        //     driver.findElement(Filter).click();
        //     driver.findElement(AddressCountry).click();
        //     driver.findElement(AddressSearch).click();
        //     driver.findElement(AddressSearch).sendKeys(address);
        //     driver.findElement(Addresfilter).click();
        //     driver.findElement(Citizencountry).click();
        //     driver.findElement(CitizenSearch).click();
        //     driver.findElement(CitizenSearch).sendKeys(citizenship);
        //     driver.findElement(Citizenfilter).click();
        //     driver.findElement(Lastupdate).click();
        //     driver.findElement(Startdate).click();
        //     driver.findElement(Startdate).sendKeys(startdate);
        //     driver.findElement(Enddate).click();
        //     driver.findElement(Enddate).sendKeys(enddate);
        //     driver.findElement(Nationalcountry).click();
        //     driver.findElement(NationalSearch).click();
        //     driver.findElement(NationalSearch).sendKeys(nationality);
        //     driver.findElement(Nationalfilter).click();
        //     driver.findElement(Programname).click();
        //     driver.findElement(ProgramSearch).click();
        //     driver.findElement(ProgramSearch).sendKeys(program);
        //     driver.findElement(Programfilter).click();
        //     driver.findElement(Type).click();
        //     driver.findElement(TypeSearch).click();
        //     driver.findElement(TypeSearch).sendKeys(type);
        //     driver.findElement(Typefilter).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Amend Tab");
        //     System.out.println("No record for all filter");
        //     driver.findElement(Clearfilter).click();
        // }
        // }


        // //DELTA DELETE TAB
        // try {
        //     boolean logo6 = driver.findElement(DDelete0).isDisplayed();
        //     if(logo6 == true){
        //         System.out.println("No Data in Delta Delete Tab");
        //     }
        // } catch(Exception e) {
        //     driver.findElement(DDelete).click();Thread.sleep(1000);

        //     driver.findElement(Filter).click(); 
        //     driver.findElement(AddressCountry).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(DDelete).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Delta Delete Tab");
        //     System.out.println("No records for address filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(DDelete).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Citizencountry).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(DDelete).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Delta Delete Tab");
        //     System.out.println("No record for citizenship filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(DDelete).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Lastupdate).click();
        //     driver.findElement(Startdate).click();
        //     driver.findElement(Startdate).sendKeys(startdate);
        //     driver.findElement(Enddate).click();
        //     driver.findElement(Enddate).sendKeys(enddate);
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(DDelete).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Delta Delete Tab");
        //     System.out.println("No recoprds for last update date filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(DDelete).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Nationalcountry).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(DDelete).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Delta Delete Tab");
        //     System.out.println("No records for national filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(DDelete).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Programname).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(DDelete).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Delta Delete Tab");
        //     System.out.println("No records for program filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(DDelete).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Type).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(DDelete).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Delta Delete Tab");
        //     System.out.println("No records for type filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(DDelete).click();Thread.sleep(1000);
        // }
            
        //     //DELTA DELETE ALL FILTERS
        //     driver.findElement(Filter).click();
        //     driver.findElement(AddressCountry).click();
        //     driver.findElement(AddressSearch).click();
        //     driver.findElement(AddressSearch).sendKeys(address);
        //     driver.findElement(Addresfilter).click();
        //     driver.findElement(Citizencountry).click();
        //     driver.findElement(CitizenSearch).click();
        //     driver.findElement(CitizenSearch).sendKeys(citizenship);
        //     driver.findElement(Citizenfilter).click();
        //     driver.findElement(Lastupdate).click();
        //     driver.findElement(Startdate).click();
        //     driver.findElement(Startdate).sendKeys(startdate);
        //     driver.findElement(Enddate).click();
        //     driver.findElement(Enddate).sendKeys(enddate);
        //     driver.findElement(Nationalcountry).click();
        //     driver.findElement(NationalSearch).click();
        //     driver.findElement(NationalSearch).sendKeys(nationality);
        //     driver.findElement(Nationalfilter).click();
        //     driver.findElement(Programname).click();
        //     driver.findElement(ProgramSearch).click();
        //     driver.findElement(ProgramSearch).sendKeys(program);
        //     driver.findElement(Programfilter).click();
        //     driver.findElement(Type).click();
        //     driver.findElement(TypeSearch).click();
        //     driver.findElement(TypeSearch).sendKeys(type);
        //     driver.findElement(Typefilter).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Delta Delete Tab");
        //     System.out.println("No records for all filter");
        //     driver.findElement(Clearfilter).click();
        // }
        // }

        // //STABLE TAB
        // try {
        //     boolean logo7 = driver.findElement(Stable0).isDisplayed();
        //     if(logo7 == true){
        //         System.out.println("No Data in Stable Tab");
        //     }
        // } catch(Exception e) {
        //     driver.findElement(Stable).click();Thread.sleep(1000);

        //     driver.findElement(Filter).click();
        //     driver.findElement(AddressCountry).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(Stable).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Stable Tab");
        //     System.out.println("No Record for address filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(Stable).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Citizencountry).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(Stable).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Stable Tab");
        //     System.out.println("No record for citizenship filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(Stable).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Lastupdate).click();
        //     driver.findElement(Startdate).click();
        //     driver.findElement(Startdate).sendKeys(startdate);
        //     driver.findElement(Enddate).click();
        //     driver.findElement(Enddate).sendKeys(enddate);
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(Stable).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Stable Tab");
        //     System.out.println("No records for last update type filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(Stable).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Nationalcountry).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(Stable).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Stable Tab");
        //     System.out.println("No record for national filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(Stable).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Programname).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(Stable).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Stable Tab");
        //     System.out.println("No record for program filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(Stable).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Type).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(Stable).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Stable Tab");
        //     System.out.println("No record for type filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(Stable).click();Thread.sleep(1000);
        // }
            
        //     //STABLE ALL FILTERS
        //     driver.findElement(Filter).click();
        //     driver.findElement(AddressCountry).click();
        //     driver.findElement(AddressSearch).click();
        //     driver.findElement(AddressSearch).sendKeys(address);
        //     driver.findElement(Addresfilter).click();
        //     driver.findElement(Citizencountry).click();
        //     driver.findElement(CitizenSearch).click();
        //     driver.findElement(CitizenSearch).sendKeys(citizenship);
        //     driver.findElement(Citizenfilter).click();
        //     driver.findElement(Lastupdate).click();
        //     driver.findElement(Startdate).click();
        //     driver.findElement(Startdate).sendKeys(startdate);
        //     driver.findElement(Enddate).click();
        //     driver.findElement(Enddate).sendKeys(enddate);
        //     driver.findElement(Nationalcountry).click();
        //     driver.findElement(NationalSearch).click();
        //     driver.findElement(NationalSearch).sendKeys(nationality);
        //     driver.findElement(Nationalfilter).click();
        //     driver.findElement(Programname).click();
        //     driver.findElement(ProgramSearch).click();
        //     driver.findElement(ProgramSearch).sendKeys(program);
        //     driver.findElement(Programfilter).click();
        //     driver.findElement(Type).click();
        //     driver.findElement(TypeSearch).click();
        //     driver.findElement(TypeSearch).sendKeys(type);
        //     driver.findElement(Typefilter).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Stable Tab");
        //     System.out.println("No record for all filter");
        //     driver.findElement(Clearfilter).click();
        // }
        // }


        // //DELTA ERROR TAB
        // try {
        //     boolean logo8 = driver.findElement(DError0).isDisplayed();
        //     if(logo8 == true){
        //         System.out.println("No Data in Delta Error Tab");
        //     }
        // } catch(Exception e) {
        //     driver.findElement(DError).click();Thread.sleep(1000);

        //     driver.findElement(Filter).click();
        //     driver.findElement(AddressCountry).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(DError).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Delta Error Tab");
        //     System.out.println("No record for address filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(DError).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Citizencountry).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(DError).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Delta Error Tab");
        //     System.out.println("No record for citizenship filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(DError).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Lastupdate).click();
        //     driver.findElement(Startdate).click();
        //     driver.findElement(Startdate).sendKeys(startdate);
        //     driver.findElement(Enddate).click();
        //     driver.findElement(Enddate).sendKeys(enddate);
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(DError).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Delta Error Tab");
        //     System.out.println("No record for last update date filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(DError).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Nationalcountry).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(DError).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Delta Error Tab");
        //     System.out.println("No record for national filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(DError).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Programname).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(DError).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("No record for program filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(DError).click();Thread.sleep(1000);
        // }

        //     driver.findElement(Filter).click();
        //     driver.findElement(Type).click();
        //     driver.findElement(Selectall).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();
        //         driver.findElement(DError).click();Thread.sleep(1000);   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Delta Error Tab");
        //     System.out.println("No record for type filter");
        //     driver.findElement(Clearfilter).click();
        //     driver.findElement(DError).click();Thread.sleep(1000);
        // }
            
        //     //DELTA ERROR ALL FILTERS
        //     driver.findElement(Filter).click();
        //     driver.findElement(AddressCountry).click();
        //     driver.findElement(AddressSearch).click();
        //     driver.findElement(AddressSearch).sendKeys(address);
        //     driver.findElement(Addresfilter).click();
        //     driver.findElement(Citizencountry).click();
        //     driver.findElement(CitizenSearch).click();
        //     driver.findElement(CitizenSearch).sendKeys(citizenship);
        //     driver.findElement(Citizenfilter).click();
        //     driver.findElement(Lastupdate).click();
        //     driver.findElement(Startdate).click();
        //     driver.findElement(Startdate).sendKeys(startdate);
        //     driver.findElement(Enddate).click();
        //     driver.findElement(Enddate).sendKeys(enddate);
        //     driver.findElement(Nationalcountry).click();
        //     driver.findElement(NationalSearch).click();
        //     driver.findElement(NationalSearch).sendKeys(nationality);
        //     driver.findElement(Nationalfilter).click();
        //     driver.findElement(Programname).click();
        //     driver.findElement(ProgramSearch).click();
        //     driver.findElement(ProgramSearch).sendKeys(program);
        //     driver.findElement(Programfilter).click();
        //     driver.findElement(Type).click();
        //     driver.findElement(TypeSearch).click();
        //     driver.findElement(TypeSearch).sendKeys(type);
        //     driver.findElement(Typefilter).click();
        //     driver.findElement(Apply).click();Thread.sleep(3000);
        //     try {
        //     boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
        //     if (logo9 == true) {
        //         driver.findElement(Downloadicon).click();
        //         driver.findElement(Tab).click();Thread.sleep(4000);
        //         driver.findElement(Clearfilter).click();   
        //     }
        // } catch (Exception e1) {
        //     System.out.println("Delta Error Tab");
        //     System.out.println("No record for all filer");
        //     driver.findElement(Clearfilter).click();
        // }
        //  }
     }

    public void check_download_status_1() throws InterruptedException {
        driver.findElement(Downloads).click();Thread.sleep(3000);
        driver.findElement(Refresh).click();Thread.sleep(3000);
        for (int i = 0;i<20;i++) {
            driver.findElement(Refresh).click();Thread.sleep(3000);

            
        }


        try {
            boolean rtype = driver.findElement(Activerecord).isDisplayed();
            if (rtype == true) {
                System.out.println("Active record found - waiting for download to complete");

                int maxAttempts = 60;
                boolean downloadSuccess = false;

                for (int i = 0; i < maxAttempts; i++) {
                    driver.findElement(Refresh).click();Thread.sleep(3000);

                    try {
                        boolean successStatus = driver.findElement(Success).isDisplayed();
                        if (successStatus) {
                            System.out.println("Status is Success - downloading file");
                            driver.findElement(Download).click();
                            Thread.sleep(2000);
                            System.out.println("File downloaded successfully");
                            downloadSuccess = true;
                            break;
                        }
                    } catch (Exception e1) {
                        try {
                            boolean failedStatus = driver.findElement(Failed).isDisplayed();
                            if (failedStatus) {
                                System.out.println("Download Failed");
                                break;
                            }
                        } catch (Exception e2) {
                            System.out.println("Download in progress... waiting (" + (i+1) + "/" + maxAttempts + ")");
                        }
                    }
                }

                if (!downloadSuccess) {
                    System.out.println("Download Failed - Timeout reached");
                }
            }
        } catch (Exception e) {
            System.out.println("No active records found");
        }
    }

}
