package log;

import com.polymathlab.config.Driver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureListener implements ITestListener {

    public AllureListener() {}

    private static String getTestMethodName(ITestResult iTestResult)
    {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveFailureScreenShot(WebDriver driver)
    {
        return (byte[])((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog (String message)
    {
        return message;
    }

    public void onTestFailure(ITestResult iTestResult)
    {
        System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
        Object testClass = iTestResult.getInstance();
        WebDriver driver = Driver.getInstance();
        // Allure screenshot and text log
        if (driver instanceof WebDriver)
        {
            System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
            this.saveFailureScreenShot(driver);
        }
        saveTextLog(getTestMethodName(iTestResult) + "failed and screenshot taken!");
    }
}
