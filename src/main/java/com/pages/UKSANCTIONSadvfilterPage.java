package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class UKSANCTIONSadvfilterPage {

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
    private By UKSanctions = By.xpath("//div[text()='UK SANCTIONS']");

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

    // Filter Locators
    private By Designateddate = By.xpath("(//span[text()='Designated date'])[1]");
	private By Designatedate = By.xpath("(//input[@placeholder='DD/MM/YYYY'])[1]");
	private By Idtype = By.xpath("(//span[text()='Id Type'])[1]");
	private By Idtypesearch = By.xpath("(//input[@placeholder='Search id type'])");
	private By Idtypefilter = By.xpath("//input[@id='advance-filter-list-select-all-idNumberTypesList.idType']");
	private By Programsource = By.xpath("(//span[text()='Program Source'])[1]");
	private By Programsourcesearch = By.xpath("(//input[@placeholder='Search program source'])");
	private By Programsourcefilter = By.xpath("//input[@id='advance-filter-list-select-all-sanctionProgramDetailsList.programSource']");
	private By Regimename = By.xpath("(//span[text()='Regime Name'])[1]");
	private By Regimenamesearch = By.xpath("(//input[@placeholder='Search regime name'])");
	private By Regimenamefilter = By.xpath("//input[@id='advance-filter-list-select-all-sanctionProgramDetailsList.programName']");
    private By Type = By.xpath("(//span[text()='Type'])[1]");
	private By TypeSearch = By.xpath("(//input[@placeholder='Search type'])");
	private By Typefilter = By.xpath("//input[@id='advance-filter-list-select-all-entityTypeName']");

    // Action Locators
    private By Apply = By.xpath("(//button[@id='advance-filter-apply-btn'])");
    private By Downloadicon = By.xpath("(//button[@id='regulatory-records-download-btn'])");
    private By Excel = By.xpath("(//span[text()='Excel (.xlsx)'])");
    private By Toaster = By.xpath("//div[text()='Request in progress. Check the Downloads tab for status']");
    private By Tab = By.xpath("(//li[text()='Tab separated (.tsv)'])");
    private By Clearfilter = By.xpath("//button[text()='CLEAR FILTERS']");
    private By Selectall = By.xpath("//label[text()='Select all']");

    // Status Locators
    private By Downloads = By.xpath("//button[@id='simple-tab-1']");
    private By Success = By.xpath("(//div[text()='Success'])[1]");
    private By Failed = By.xpath("(//div[text()='Failed'])[1]");
    private By Activerecord = By.xpath("(//div[text()='Active record download'])[1]");
    private By Download = By.xpath("(//button[@type='button'])[5]");
    private By Refresh = By.xpath("//div[@aria-label='refresh button']");

    WebDriver driver;

    public UKSANCTIONSadvfilterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void facctlist_login_2(String url, String ornm, String usnm, String pswd) throws InterruptedException {
        driver.get(url);
        Thread.sleep(2000);
        boolean logo1 = driver.findElement(Welcomepage).isDisplayed();
        if (logo1) {
            System.out.println("Welcome page is displayed");
        } else {
            System.out.println("Welcome is not displayed");
        }

        driver.findElement(Login).click();
        driver.findElement(Orgname).sendKeys(ornm);
        driver.findElement(Continue1).click();
        Thread.sleep(2000);
        driver.findElement(Username).sendKeys(usnm);
        driver.findElement(Password).sendKeys(pswd);
        driver.findElement(Continue2).click();
        Thread.sleep(5000);

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

    public void navigate_to_regulatory_list_2() throws InterruptedException {
        driver.findElement(Watchlistmenu).click();Thread.sleep(4000);
        driver.findElement(Regulatorylist).click();Thread.sleep(3000);
    }

    public void select_uksanctions_list() throws InterruptedException {
        driver.findElement(Pagination).click();Thread.sleep(2000);
        driver.findElement(Pagevalue).click();Thread.sleep(2000);
        driver.findElement(UKSanctions).click();Thread.sleep(3000);
    }

    public void apply_uksanctions_filter(String designateddate, String idtype, String programname, String regimename, String type) throws InterruptedException {
        
        // ACTIVE TAB
        try {
            boolean logo1 = driver.findElement(Active0).isDisplayed();
            if (logo1 == true) {
                System.out.println("No Data in Active Tab");
            }
        } catch (Exception e) {
            driver.findElement(Active).click();
            Thread.sleep(1000);

            driver.findElement(Filter).click();
            driver.findElement(Idtype).click();
            driver.findElement(Selectall).click();
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
            System.out.println("No Records for ID type filter");
            driver.findElement(Clearfilter).click();
        }

            // driver.findElement(Filter).click();
            // driver.findElement(Designateddate).click();
            // driver.findElement(Designatedate).click();
            // driver.findElement(Designatedate).sendKeys(designateddate);
            // driver.findElement(Apply).click();
            // Thread.sleep(3000);
            // try {
            //     boolean logo10 = driver.findElement(Downloadicon).isDisplayed();
            //     if (logo10 == true) {
            //         driver.findElement(Downloadicon).click();
            //         driver.findElement(Tab).click();
            //         Thread.sleep(4000);
            //         driver.findElement(Clearfilter).click();
            //     }
            // } catch (Exception e1) {
            //     System.out.println("No Records for Designated date filter");
            //     driver.findElement(Clearfilter).click();
            // }

            driver.findElement(Filter).click();
            driver.findElement(Programsource).click();
            driver.findElement(Selectall).click();
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
                System.out.println("No Records for Program source filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Regimename).click();
            driver.findElement(Selectall).click();
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
                System.out.println("Active Tab - No Records for Regime name filter");
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
            // driver.findElement(Designateddate).click();
            // driver.findElement(Designatedate).click();
            // driver.findElement(Designatedate).sendKeys(designateddate);
            driver.findElement(Idtype).click();
            driver.findElement(Idtypesearch).click();
            driver.findElement(Idtypesearch).sendKeys(idtype);
            driver.findElement(Idtypefilter).click();
            driver.findElement(Programsource).click();
            driver.findElement(Programsourcesearch).click();
            driver.findElement(Programsourcesearch).sendKeys(programname);
            driver.findElement(Programsourcefilter).click();
            driver.findElement(Regimename).click();
            driver.findElement(Regimenamesearch).click();
            driver.findElement(Regimenamesearch).sendKeys(regimename);
            driver.findElement(Regimenamefilter).click();
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
            System.out.println("Active Tab");
            System.out.println("No Records for All filter");
            driver.findElement(Clearfilter).click();
        }
        }



        // ERROR TAB
        try {
            boolean logo1 = driver.findElement(Error0).isDisplayed();
            if (logo1 == true) {
                System.out.println("No Data in Active Tab");
            }
        } catch (Exception e) {
            driver.findElement(Active).click();
            Thread.sleep(1000);

            driver.findElement(Filter).click();
            driver.findElement(Idtype).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
                if (logo9 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(3000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("No Records for Id Type filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Designateddate).click();
            driver.findElement(Designatedate).click();
            driver.findElement(Designatedate).sendKeys(designateddate);
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
                System.out.println("No Records for Designated date filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Programsource).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo12 = driver.findElement(Downloadicon).isDisplayed();
                if (logo12 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("No Records for Program source filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Regimename).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo13 = driver.findElement(Downloadicon).isDisplayed();
                if (logo13 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("Active Tab - No Records for Regime name filter");
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

            //ERROR TAB ALL FILTERS
            driver.findElement(Filter).click();
            // driver.findElement(Designateddate).click();
            // driver.findElement(Designatedate).click();
            // driver.findElement(Designatedate).sendKeys(designateddate);
            driver.findElement(Idtype).click();
            driver.findElement(Idtypesearch).click();
            driver.findElement(Idtypesearch).sendKeys(idtype);
            driver.findElement(Idtypefilter).click();
            driver.findElement(Programsource).click();
            driver.findElement(Programsourcesearch).click();
            driver.findElement(Programsourcesearch).sendKeys(programname);
            driver.findElement(Programsourcefilter).click();
            driver.findElement(Regimename).click();
            driver.findElement(Regimenamesearch).click();
            driver.findElement(Regimenamesearch).sendKeys(regimename);
            driver.findElement(Regimenamefilter).click();
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
            System.out.println("Error Tab");
            System.out.println("No Records for All filter");
            driver.findElement(Clearfilter).click();
        }
        }

        // DELETE TAB
        try {
            boolean logo1 = driver.findElement(Delete0).isDisplayed();
            if (logo1 == true) {
                System.out.println("No Data in Active Tab");
            }
        } catch (Exception e) {
            driver.findElement(Active).click();
            Thread.sleep(1000);

            driver.findElement(Filter).click();
            driver.findElement(Idtype).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
                if (logo9 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(3000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("No Records for Id Type filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Designateddate).click();
            driver.findElement(Designatedate).click();
            driver.findElement(Designatedate).sendKeys(designateddate);
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
                System.out.println("No Records for Designated date filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Programsource).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo12 = driver.findElement(Downloadicon).isDisplayed();
                if (logo12 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("No Records for Program source filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Regimename).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo13 = driver.findElement(Downloadicon).isDisplayed();
                if (logo13 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("Active Tab - No Records for Regime name filter");
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

            //DELETE TAB ALL FILTERS
            driver.findElement(Filter).click();
            // driver.findElement(Designateddate).click();
            // driver.findElement(Designatedate).click();
            // driver.findElement(Designatedate).sendKeys(designateddate);
            driver.findElement(Idtype).click();
            driver.findElement(Idtypesearch).click();
            driver.findElement(Idtypesearch).sendKeys(idtype);
            driver.findElement(Idtypefilter).click();
            driver.findElement(Programsource).click();
            driver.findElement(Programsourcesearch).click();
            driver.findElement(Programsourcesearch).sendKeys(programname);
            driver.findElement(Programsourcefilter).click();
            driver.findElement(Regimename).click();
            driver.findElement(Regimenamesearch).click();
            driver.findElement(Regimenamesearch).sendKeys(regimename);
            driver.findElement(Regimenamefilter).click();
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
            System.out.println("Delete Tab");
            System.out.println("No Records for All filter");
            driver.findElement(Clearfilter).click();
        }
        }

        // NEW TAB
        try {
            boolean logo1 = driver.findElement(New0).isDisplayed();
            if (logo1 == true) {
                System.out.println("No Data in Active Tab");
            }
        } catch (Exception e) {
            driver.findElement(Active).click();
            Thread.sleep(1000);

            driver.findElement(Filter).click();
            driver.findElement(Idtype).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
                if (logo9 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(3000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("No Records for Id Type filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Designateddate).click();
            driver.findElement(Designatedate).click();
            driver.findElement(Designatedate).sendKeys(designateddate);
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
                System.out.println("No Records for Designated date filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Programsource).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo12 = driver.findElement(Downloadicon).isDisplayed();
                if (logo12 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("No Records for Program source filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Regimename).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo13 = driver.findElement(Downloadicon).isDisplayed();
                if (logo13 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("Active Tab - No Records for Regime name filter");
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

            //NEW TAB ALL FILTERS
            driver.findElement(Filter).click();
            // driver.findElement(Designateddate).click();
            // driver.findElement(Designatedate).click();
            // driver.findElement(Designatedate).sendKeys(designateddate);
            driver.findElement(Idtype).click();
            driver.findElement(Idtypesearch).click();
            driver.findElement(Idtypesearch).sendKeys(idtype);
            driver.findElement(Idtypefilter).click();
            driver.findElement(Programsource).click();
            driver.findElement(Programsourcesearch).click();
            driver.findElement(Programsourcesearch).sendKeys(programname);
            driver.findElement(Programsourcefilter).click();
            driver.findElement(Regimename).click();
            driver.findElement(Regimenamesearch).click();
            driver.findElement(Regimenamesearch).sendKeys(regimename);
            driver.findElement(Regimenamefilter).click();
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
            System.out.println("New Tab");
            System.out.println("No Records for All filter");
            driver.findElement(Clearfilter).click();
        }
        }
        // AMEND TAB
        try {
            boolean logo1 = driver.findElement(Amend0).isDisplayed();
            if (logo1 == true) {
                System.out.println("No Data in Active Tab");
            }
        } catch (Exception e) {
            driver.findElement(Active).click();
            Thread.sleep(1000);

            driver.findElement(Filter).click();
            driver.findElement(Idtype).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
                if (logo9 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(3000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("No Records for Id Type filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Designateddate).click();
            driver.findElement(Designatedate).click();
            driver.findElement(Designatedate).sendKeys(designateddate);
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
                System.out.println("No Records for Designated date filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Programsource).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo12 = driver.findElement(Downloadicon).isDisplayed();
                if (logo12 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("No Records for Program source filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Regimename).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo13 = driver.findElement(Downloadicon).isDisplayed();
                if (logo13 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("Active Tab - No Records for Regime name filter");
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

            //AMEND TAB ALL FILTERS
            driver.findElement(Filter).click();
            // driver.findElement(Designateddate).click();
            // driver.findElement(Designatedate).click();
            // driver.findElement(Designatedate).sendKeys(designateddate);
            driver.findElement(Idtype).click();
            driver.findElement(Idtypesearch).click();
            driver.findElement(Idtypesearch).sendKeys(idtype);
            driver.findElement(Idtypefilter).click();
            driver.findElement(Programsource).click();
            driver.findElement(Programsourcesearch).click();
            driver.findElement(Programsourcesearch).sendKeys(programname);
            driver.findElement(Programsourcefilter).click();
            driver.findElement(Regimename).click();
            driver.findElement(Regimenamesearch).click();
            driver.findElement(Regimenamesearch).sendKeys(regimename);
            driver.findElement(Regimenamefilter).click();
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
            System.out.println("Amend Tab");
            System.out.println("No Records for All filter");
            driver.findElement(Clearfilter).click();
        }
        }

        // DELTA DELETE TAB
        try {
            boolean logo1 = driver.findElement(DDelete0).isDisplayed();
            if (logo1 == true) {
                System.out.println("No Data in Active Tab");
            }
        } catch (Exception e) {
            driver.findElement(Active).click();
            Thread.sleep(1000);

            driver.findElement(Filter).click();
            driver.findElement(Idtype).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
                if (logo9 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(3000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("No Records for Id Type filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Designateddate).click();
            driver.findElement(Designatedate).click();
            driver.findElement(Designatedate).sendKeys(designateddate);
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
                System.out.println("No Records for Designated date filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Programsource).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo12 = driver.findElement(Downloadicon).isDisplayed();
                if (logo12 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("No Records for Program source filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Regimename).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo13 = driver.findElement(Downloadicon).isDisplayed();
                if (logo13 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("Active Tab - No Records for Regime name filter");
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

            //Delta Delete TAB ALL FILTERS
            driver.findElement(Filter).click();
            // driver.findElement(Designateddate).click();
            // driver.findElement(Designatedate).click();
            // driver.findElement(Designatedate).sendKeys(designateddate);
            driver.findElement(Idtype).click();
            driver.findElement(Idtypesearch).click();
            driver.findElement(Idtypesearch).sendKeys(idtype);
            driver.findElement(Idtypefilter).click();
            driver.findElement(Programsource).click();
            driver.findElement(Programsourcesearch).click();
            driver.findElement(Programsourcesearch).sendKeys(programname);
            driver.findElement(Programsourcefilter).click();
            driver.findElement(Regimename).click();
            driver.findElement(Regimenamesearch).click();
            driver.findElement(Regimenamesearch).sendKeys(regimename);
            driver.findElement(Regimenamefilter).click();
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
            System.out.println("Delta Delete Tab");
            System.out.println("No Records for All filter");
            driver.findElement(Clearfilter).click();
        }
        }

        // STABLE TAB
        try {
            boolean logo1 = driver.findElement(Stable0).isDisplayed();
            if (logo1 == true) {
                System.out.println("No Data in Active Tab");
            }
        } catch (Exception e) {
            driver.findElement(Active).click();
            Thread.sleep(1000);

            driver.findElement(Filter).click();
            driver.findElement(Idtype).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
                if (logo9 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(3000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("No Records for Id Type filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Designateddate).click();
            driver.findElement(Designatedate).click();
            driver.findElement(Designatedate).sendKeys(designateddate);
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
                System.out.println("No Records for Designated date filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Programsource).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo12 = driver.findElement(Downloadicon).isDisplayed();
                if (logo12 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("No Records for Program source filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Regimename).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo13 = driver.findElement(Downloadicon).isDisplayed();
                if (logo13 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("Active Tab - No Records for Regime name filter");
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

            //STABLE TAB ALL FILTERS
            driver.findElement(Filter).click();
            // driver.findElement(Designateddate).click();
            // driver.findElement(Designatedate).click();
            // driver.findElement(Designatedate).sendKeys(designateddate);
            driver.findElement(Idtype).click();
            driver.findElement(Idtypesearch).click();
            driver.findElement(Idtypesearch).sendKeys(idtype);
            driver.findElement(Idtypefilter).click();
            driver.findElement(Programsource).click();
            driver.findElement(Programsourcesearch).click();
            driver.findElement(Programsourcesearch).sendKeys(programname);
            driver.findElement(Programsourcefilter).click();
            driver.findElement(Regimename).click();
            driver.findElement(Regimenamesearch).click();
            driver.findElement(Regimenamesearch).sendKeys(regimename);
            driver.findElement(Regimenamefilter).click();
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
            System.out.println("Stable Tab");
            System.out.println("No Records for All filter");
            driver.findElement(Clearfilter).click();
        }
        }
        // DELTA ERROR TAB
        try {
            boolean logo1 = driver.findElement(DError0).isDisplayed();
            if (logo1 == true) {
                System.out.println("No Data in Active Tab");
            }
        } catch (Exception e) {
            driver.findElement(Active).click();
            Thread.sleep(1000);

            driver.findElement(Filter).click();
            driver.findElement(Idtype).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo9 = driver.findElement(Downloadicon).isDisplayed();
                if (logo9 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(3000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("No Records for Id Type filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Designateddate).click();
            driver.findElement(Designatedate).click();
            driver.findElement(Designatedate).sendKeys(designateddate);
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
                System.out.println("No Records for Designated date filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Programsource).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo12 = driver.findElement(Downloadicon).isDisplayed();
                if (logo12 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("No Records for Program source filter");
                driver.findElement(Clearfilter).click();
            }

            driver.findElement(Filter).click();
            driver.findElement(Regimename).click();
            driver.findElement(Selectall).click();
            driver.findElement(Apply).click();
            Thread.sleep(3000);
            try {
                boolean logo13 = driver.findElement(Downloadicon).isDisplayed();
                if (logo13 == true) {
                    driver.findElement(Downloadicon).click();
                    driver.findElement(Tab).click();
                    Thread.sleep(4000);
                    driver.findElement(Clearfilter).click();
                }
            } catch (Exception e1) {
                System.out.println("Active Tab - No Records for Regime name filter");
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

            //DELTA ERROR TAB ALL FILTERS
            driver.findElement(Filter).click();
            // driver.findElement(Designateddate).click();
            // driver.findElement(Designatedate).click();
            // driver.findElement(Designatedate).sendKeys(designateddate);
            driver.findElement(Idtype).click();
            driver.findElement(Idtypesearch).click();
            driver.findElement(Idtypesearch).sendKeys(idtype);
            driver.findElement(Idtypefilter).click();
            driver.findElement(Programsource).click();
            driver.findElement(Programsourcesearch).click();
            driver.findElement(Programsourcesearch).sendKeys(programname);
            driver.findElement(Programsourcefilter).click();
            driver.findElement(Regimename).click();
            driver.findElement(Regimenamesearch).click();
            driver.findElement(Regimenamesearch).sendKeys(regimename);
            driver.findElement(Regimenamefilter).click();
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
            System.out.println("Delta Error Tab");
            System.out.println("No Records for All filter");
            driver.findElement(Clearfilter).click();
        }
        }
    }

    public void check_download_status_2() throws InterruptedException {
        driver.findElement(Downloads).click();
        Thread.sleep(3000);

        try {
            boolean rtype = driver.findElement(Activerecord).isDisplayed();
            if (rtype == true) {
                System.out.println("Active record found - waiting for download to complete");
                
                int maxAttempts = 60;
                boolean downloadSuccess = false;
                
                for (int i = 0; i < maxAttempts; i++) {
                    driver.findElement(Refresh).click();
                    Thread.sleep(3000);
                    
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
