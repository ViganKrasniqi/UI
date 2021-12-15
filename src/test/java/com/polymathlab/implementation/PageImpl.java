package com.polymathlab.implementation;

import com.polymathlab.UserActions;
import com.polymathlab.config.PageURL;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertEquals;
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
public class PageImpl
{
    //Page Locators

    //Page objects
    UserActions user = new UserActions();

    private WebDriver driver;
    /**
     * Class constructor
     */
    public PageImpl(WebDriver driver){ this.driver = driver; }

                        // ========== METHODS ==========

    @Step("Click link for {0}")
    public void clickIn(String optionName) throws InterruptedException
    {
        Thread.sleep(3000);
        String link = ("//h3[contains(text(),'?')]").replace("?",optionName);

        try {
            WebElement option1 = driver.findElement(By.xpath(link));
            option1.click();
        }
        catch (StaleElementReferenceException elementHasDisappeared)
        {
            return;
        }

        driver.findElement(By.xpath("//h3[contains(text(),'Polymaths And Outliers')")).click();
    }

    @Step("Click icon for {0}")
    public void clickInIcon(String option) throws InterruptedException
    {
        if(option.equalsIgnoreCase("Twitter"))
        {
            driver.findElement(By.xpath("//div[@class='blog-author-component']//li[1]//a[1]//img[1]")).click();
        }

        if(option.equalsIgnoreCase("Linkedin"))
        {
            driver.findElement(By.xpath("//div[@class='blog-author-component']//li[1]//a[1]//img[1]")).click();
        }
    }

    @Step("Click Back on browser")
    public void clickBack() throws InterruptedException
    {
        driver.navigate().back();
        Thread.sleep(3000);
    }

    @Step("Verify content text present: {0}")
    public void verifyContent(String option) throws InterruptedException
    {
        String link = "//p[contains(text(),'?')]";
        link.replace("?", option);
        assertTrue((driver.findElement(By.xpath(link)).isDisplayed()),
                                                "Content text present on page");
    }

//    @Step("Verify content text present on FB post: {0}")
//    public void verifyContentonFB(String option) throws InterruptedException
//    {
//        String link = "//div[contains(text(),'?')]";
//        assertTrue((driver.findElement(By.xpath(link.replace("?", option)))).isDisplayed(),
//                                                "Content text present on page");
//    }

    @Step("Verify that the profile of Visar Gashi on Twitter is present")
    public void verifyTwitterPage() throws InterruptedException
    {
        user.changeWindow(driver);
        String twitterProfile = "https://twitter.com/veesarg";
        assertEquals(driver.getCurrentUrl(), twitterProfile,
                                            "Page is updated to Twitter");
        driver.close();
    }
    @Step("Verify that the profile of Visar Gashi on LinkedIn is present")
    public void verifyLinkedinPage() throws InterruptedException
    {
        user.changeWindow(driver);
        String twitterProfile = "https://www.linkedin.com/in/visargashi/";
        assertEquals(driver.getCurrentUrl(), twitterProfile,
                                            "Page is updated to LinkedIn");
        driver.close();
    }
}
