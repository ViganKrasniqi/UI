package com.polymathlab.initialize;

import com.polymathlab.config.Driver;
import com.polymathlab.config.PageURL;
import log.LogDemo;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.polymathlab.config.Driver.hiddenInstance;

/**
 * This class serves to open browser and go to Peer page
 *    CLASS SHOULD BE EXTENDED BY OTHER TEST CLASSES
 *    @BeforeMethod annotation is used to run some code BEFORE @Test runs
 *        In our case it launches browser and goes to Shop page
 *    @AfterMethod annotation is used to run some code AFTER @Test runs
 *        In our case it closes browser after test case is completed
 */
public class InitializeUser
{
    // Page objects
    private Driver user = new Driver();

    public static WebDriver driver;

    @BeforeMethod
    public void initialize()
    {
        PageURL url = new PageURL();

        LogDemo.info("Opening PolymathLabs Page");

        driver = user.initializeInstance();
        driver.get(url.getBaseURL());

        LogDemo.startTestCase();
    }

    @AfterMethod
    public void cleanUp()
    {
        LogDemo.endTestCase();

        driver.quit();

        if (hiddenInstance != null)
            hiddenInstance.quit();

        LogDemo.info("Closing Browser");
    }
}
