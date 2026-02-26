package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class dashboardpage extends BasePage {

     // Explicit constructor calling BasePage's constructor
    public dashboardpage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //page elements
    @FindBy(xpath ="//div[@class='task-stats']//div[text()='Total Tasks']")
    private WebElement totalTasks;

    @FindBy(xpath = "//div[contains(@class,'total-card-count')]")
    private WebElement totalTasksCount;
    // Method to get the total tasks count
    public String getTotalTasks() {
        if (elementLib.isElementDisplayed(totalTasks)) {
            return totalTasks.getText();
        } else {
            throw new RuntimeException("Total Tasks element is not displayed.");
        }
    }
    public String getTotalTasksCount() {
        if (elementLib.isElementDisplayed(totalTasksCount)) {
            return totalTasksCount.getText();
        } else {
            throw new RuntimeException("Total Tasks Count element is not displayed.");
        }
    }
}
