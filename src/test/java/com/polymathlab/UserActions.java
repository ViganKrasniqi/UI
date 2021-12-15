package com.polymathlab;

import com.polymathlab.config.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static log.LogDemo.info;

/**
 * This class contains methods that the user has to use frequently.
 * Example: You want to wait for an element to be displayed in page.
 */
public class UserActions
{
    // Page objects
    private Driver user = new Driver();

    /*
     * This method switches from a current window you are in, to the other opened window
     * Or it switches from current tab to another one
     */
    public void changeWindow (WebDriver driver)
    {
        String currentWindow = driver.getWindowHandle();
        String newWindow = null;

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(currentWindow)) {
                newWindow = window;
            }
        }
        driver.switchTo().window(newWindow);
    }

}
