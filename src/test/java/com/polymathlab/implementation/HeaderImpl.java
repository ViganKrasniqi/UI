package com.polymathlab.implementation;

import com.polymathlab.UserActions;
import com.polymathlab.config.PageURL;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static log.LogDemo.info;
import static org.testng.Assert.assertTrue;

/**
 * This class is used to implement user actions of Header in Web Portal
 * Impl classes are used to setup steps that user has to take in order to complete a test case
 * For every Method that you create that does any action, please add logs to describe that action
 * Logs can be found in LogDemo class under Log package.
 * Import the class and use LogDemo.info() method to describe action.
 * example: LogDemo.info("User has successfully logged in");
 * example: --------------------------       User has successfully logged in
 */
public class HeaderImpl
{
    //Page Locators
    private By headerSection = By.tagName("app-header-logged");
    private By optionsHeader = By.className("menu");

                        // ========== GETTERS ==========
    public By getHeaderSection(){ return headerSection; }
    public By getOptionsHeader(){ return optionsHeader; }

    //Page objects
    UserActions user = new UserActions();
    PageURL url = new PageURL();

    private WebDriver driver;
    /**
     * Class constructor
     */
    public HeaderImpl(WebDriver driver){ this.driver = driver; }

                        // ========== METHODS ==========

    @Step("Open header and go to {0} ")
    public void chooseOptionAndVerify(String optionName) throws InterruptedException
    {
        chooseOption(optionName);

        checkUserRedirect(optionName);
    }

    @Step("Open header and go to {0} ")
    public void chooseOption(String optionName) throws InterruptedException
    {
        try {
            if (optionName.equalsIgnoreCase("Contact us")) {
                WebElement option1 = driver.findElement(By.xpath("//a[@class='btn nav-button']"));
                option1.click();
            } else {
                List<WebElement> options = driver.findElement(optionsHeader).findElements(By.tagName("div"));

                for (WebElement option : options) {
                    String optionText = option.findElement(By.tagName("a")).getText();

                    if (optionText.contains(optionName)) {
                        option.click();
                    }
                }
            }
        }
        catch (StaleElementReferenceException elementHasDisappeared)
        {
            return;
        }
    }

    @Step("Verify user is redirected to {0}")
    private void checkUserRedirect(String optionName) throws InterruptedException
    {
        String pageURL;
        pageURL = url.setPageURL(optionName);

        Thread.sleep(6000);
        assertTrue(driver.getCurrentUrl().equalsIgnoreCase(pageURL), "User was NOT redirected to [" + pageURL + "] option!");
        info("User was SUCCESSFULLY redirected to [" + pageURL + "] option");
    }

}
