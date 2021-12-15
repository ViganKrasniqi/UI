package com.polymathlab.tests;

import com.polymathlab.implementation.HeaderImpl;
import com.polymathlab.implementation.PageImpl;
import com.polymathlab.initialize.InitializeUser;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import log.AllureListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * This is the test class where the QA runs tests from
 * All appropriate methods and page objects to run a test should be called in this class
 * By typing the @Test annotation system will recognize from where to run a specific test
 * You can run all tests by Clicking RUN button at the class name
 * You can run a specific test case by Clicking RUN button at the method name
 */
@Listeners(AllureListener.class)
@Feature("Navigation Tests")
public class NavigationTests extends InitializeUser
{
    //Page objects
    HeaderImpl header;
    PageImpl page;

    private String optionName;

    // This method is used to initialize and call methods that are the same in every test method
    private void setupCommonSteps() throws InterruptedException
    {
        header = new HeaderImpl(driver);

        driver.navigate().refresh();

        Thread.sleep(3000);
        header.chooseOptionAndVerify(optionName);
    }

    @Link(name = "T-1", url = "https://jirawebsite.polymathlab.com/T-1")
    @Description("This test case checks if Services page works correctly")
    @Test(priority = 1, description = "Go to Services page")
    public void goToServices() throws InterruptedException
    {
        optionName = "Services";

        setupCommonSteps();
    }

    @Link(name = "T-2", url = "https://jirawebsite.polymathlab.com/T-2")
    @Description("This test case checks if Competencies page works correctly")
    @Test(priority = 2, description = "Go to Competencies page")
    public void gotoCompetencies() throws InterruptedException
    {
        optionName = "Competencies";

        setupCommonSteps();
    }

    @Link(name = "T-3", url = "https://jirawebsite.polymathlab.com/T-3")
    @Description("This test case checks if Blog page works correctly")
    @Test(priority = 3, description = "Go to Blog page")
    public void goToBlog() throws InterruptedException
    {
        optionName = "Blog";

        setupCommonSteps();
    }

    @Link(name = "T-4", url = "https://jirawebsite.polymathlab.com/T-4")
    @Description("This test case checks if About page works correctly")
    @Test(priority = 4, description = "Go to About page")
    public void goToAbout() throws InterruptedException
    {
        optionName = "About";

        setupCommonSteps();
    }

    @Link(name = "T-5", url = "https://jirawebsite.polymathlab.com/T-5")
    @Description("This test case checks if Contact us page works correctly")
    @Test(priority = 5, description = "Go to Contact us page")
    public void goToContactUS() throws InterruptedException
    {
        optionName = "Contact us";

        setupCommonSteps();
    }

}
