package com.polymathlab.tests;

import com.polymathlab.implementation.*;
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
@Feature("Other Tests")
public class OtherTests extends InitializeUser
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

    @Link(name = "T-6", url = "https://jirawebsite.polymathlab.com/T-6")
    @Description("This test checks main Blog links")
    @Test(priority = 1, description = "Verify blog links work properly")
    public void verifyBlogLinks() throws InterruptedException
    {
        optionName = "Blog";

        header = new HeaderImpl(driver);

        driver.navigate().refresh();

        Thread.sleep(3000);
        header.chooseOptionAndVerify(optionName);

        page.clickIn("Polymaths And Outliers In The Age Of Digital Knowledge");
        page.verifyContent("Cross-Functional Complexities are Here to Stay");
        page.clickIn("Just keep pushing your branch");
        page.verifyContent("Just keep pushing your branch");
        page.clickBack();
        page.clickIn("Parallel Queries as a new model");
        page.verifyContent("Parallel Queries as a new model");
        page.clickBack();
        page.clickIn("A developer today is bombarded");
        page.verifyContent("A developer today is bombarded");
        page.clickBack();
        page.clickIn("Amazon S3 - 'Block Public Access'");
        page.verifyContent("Amazon S3 - 'Block Public Access'");
        page.clickBack();
        page.clickInIcon("twitter");
        page.verifyTwitterPage();
        page.clickBack();
        page.clickInIcon("linkedin");
        page.verifyLinkedinPage();

    }
}
