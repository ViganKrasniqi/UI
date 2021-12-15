package com.polymathlab.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * This class is used to create instances(virtual users)
 * It is also used to create methods to initialize Instance
 * Launching and Setting browser preferences
 * This class is a config class that sets up config.properties file in resources package.
 */
public class Driver
{
    private static WebDriver instance;
    private static WebDriver secondInstance;
    public static WebDriver hiddenInstance;
    public static ThreadLocal usedInstance = new ThreadLocal();

    // Page objects
    private Properties properties;

    /**
     * Class Constructor
     */
    public Driver()
    {
        String propertyFilePath = System.getProperty("user.dir") + "/src/main/resources/config.properties";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(propertyFilePath));
            try {
                properties = new Properties();
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("config.properties not found at [ " + propertyFilePath + " ]");
        }
    }

    public WebDriver initializeInstance()
    {
        if (!properties.getProperty("browser").equalsIgnoreCase(""))
        {
            if (properties.getProperty("browser").equalsIgnoreCase("chrome"))
            {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito");
                if (properties.getProperty("headless").contains("true"))
                {
                    options.addArguments("--headless");
                    options.addArguments("--disable-gpu");
                }
                options.setCapability(ChromeOptions.CAPABILITY, options);
                WebDriverManager.chromedriver().setup();

                instance = new ChromeDriver(options);
                instance.manage().window().setSize(new Dimension(1920, 1080));
//                instance.manage().window().maximize();
                instance.manage().timeouts().implicitlyWait(getFiveSeconds(), TimeUnit.SECONDS);
                usedInstance.set(instance);
                return getInstance();
            }
            else if (properties.getProperty("browser").equalsIgnoreCase("firefox"))
            {
                FirefoxOptions fOptions = new FirefoxOptions();
                fOptions.addPreference("webdriver.log.driver", "OFF");
                fOptions.addArguments("-private");
                if (properties.getProperty("headless").equalsIgnoreCase("true")) fOptions.setHeadless(true);
                WebDriverManager.firefoxdriver().setup();

                instance = new FirefoxDriver(fOptions);
                instance.manage().window().maximize();
                instance.manage().timeouts().implicitlyWait(getFiveSeconds(), TimeUnit.SECONDS);
                usedInstance.set(instance);
                return getInstance();
            }
            else if (properties.getProperty("browser").equalsIgnoreCase("opera"))
            {
                OperaOptions oOptions = new OperaOptions();
                oOptions.addArguments("private");
                WebDriverManager.operadriver().setup();

                instance = new OperaDriver(oOptions);
                instance.manage().window().maximize();
                instance.manage().timeouts().implicitlyWait(getFiveSeconds(), TimeUnit.SECONDS);
                usedInstance.set(instance);
                return getInstance();
            }
            else if (properties.getProperty("browser").equalsIgnoreCase("edge"))
            {
                EdgeOptions options = new EdgeOptions();
                options.setCapability("ms:inPrivate", true);
                WebDriverManager.edgedriver().setup();

                instance = new EdgeDriver(options);
                instance.manage().window().maximize();
                instance.manage().timeouts().implicitlyWait(getFiveSeconds(), TimeUnit.SECONDS);
                usedInstance.set(instance);
                return getInstance();
            }
            else if (properties.getProperty("browser").equalsIgnoreCase("ie"))
            {
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
                WebDriverManager.iedriver().setup();

                instance = new InternetExplorerDriver(ieOptions);
                instance.manage().window().maximize();
                instance.manage().timeouts().implicitlyWait(getTenSeconds(), TimeUnit.SECONDS);
                usedInstance.set(instance);
                return getInstance();
            }
        }
        else throw new RuntimeException("BROWSER not specified in the config.properties file.");
        return null;
    }

    public WebDriver initializeSecondInstance()
    {
        if (!properties.getProperty("browser2").equalsIgnoreCase(""))
        {
            if (properties.getProperty("browser2").equalsIgnoreCase("chrome"))
            {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito");
                if (properties.getProperty("headless").contains("true"))
                {
                    options.addArguments("--headless");
                    options.addArguments("--disable-gpu");
                }
                options.setCapability(ChromeOptions.CAPABILITY, options);
                WebDriverManager.chromedriver().setup();

                secondInstance = new ChromeDriver(options);
                secondInstance.manage().window().maximize();
                secondInstance.manage().timeouts().implicitlyWait(getOneMinute(), TimeUnit.SECONDS);
                return secondInstance;
            }
            else if (properties.getProperty("browser2").equalsIgnoreCase("firefox"))
            {
                FirefoxOptions fOptions = new FirefoxOptions();
                fOptions.addPreference("webdriver.log.driver", "OFF");
                fOptions.addArguments("-private");
                if (properties.getProperty("headless").equalsIgnoreCase("true")) fOptions.setHeadless(true);
                WebDriverManager.firefoxdriver().setup();

                secondInstance = new FirefoxDriver(fOptions);
                secondInstance.manage().window().maximize();
                secondInstance.manage().timeouts().implicitlyWait(getOneMinute(), TimeUnit.SECONDS);
                return secondInstance;
            }
            else if (properties.getProperty("browser").equalsIgnoreCase("opera"))
            {
                OperaOptions oOptions = new OperaOptions();
                oOptions.addArguments("private");
                WebDriverManager.operadriver().setup();

                secondInstance = new OperaDriver(oOptions);
                secondInstance.manage().window().maximize();
                secondInstance.manage().timeouts().implicitlyWait(getOneMinute(), TimeUnit.SECONDS);
                return secondInstance;
            }
            else if (properties.getProperty("browser").equalsIgnoreCase("edge"))
            {
                EdgeOptions options = new EdgeOptions();
                options.setCapability("ms:inPrivate", true);
                WebDriverManager.edgedriver().setup();

                secondInstance = new EdgeDriver(options);
                secondInstance.manage().window().maximize();
                secondInstance.manage().timeouts().implicitlyWait(getOneMinute(), TimeUnit.SECONDS);
                return secondInstance;
            }
            else if (properties.getProperty("browser").equalsIgnoreCase("ie"))
            {
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
                WebDriverManager.iedriver().setup();

                secondInstance = new InternetExplorerDriver(ieOptions);
                secondInstance.manage().window().maximize();
                secondInstance.manage().timeouts().implicitlyWait(getTenSeconds(), TimeUnit.SECONDS);
                usedInstance.set(secondInstance);
                return getInstance();
            }
        }
        else throw new RuntimeException("BROWSER2 not specified in the config.properties file.");
        return null;
    }

    public static WebDriver getInstance()
    {
        return (WebDriver)usedInstance.get();
    }

    // USER WAIT TIME
    public int getFiveSeconds()
    {
        if (!properties.getProperty("fiveSeconds").equalsIgnoreCase(""))
        {
            String wait = properties.getProperty("fiveSeconds");
            return Integer.parseInt(wait);
        }
        else throw new RuntimeException("FIVESECONDS not specified in the config.properties file.");
    }

    public int getTenSeconds()
    {
        if (!properties.getProperty("tenSeconds").equalsIgnoreCase(""))
        {
            String wait = properties.getProperty("tenSeconds");
            return Integer.parseInt(wait);
        }
        else throw new RuntimeException("TENSECONDS not specified in the config.properties file.");
    }

    public int getOneMinute()
    {
        if (!properties.getProperty("oneMinute").equalsIgnoreCase(""))
        {
            String wait = properties.getProperty("oneMinute");
            return Integer.parseInt(wait);
        }
        else throw new RuntimeException("ONEMINUTE not specified in the config.properties file.");
    }

    public int getTwoMinutes()
    {
        if (!properties.getProperty("twoMinutes").equalsIgnoreCase(""))
        {
            String importWait = properties.getProperty("twoMinutes");
            return Integer.parseInt(importWait);
        }
        else throw new RuntimeException("TWOMINUTES not specified in the config.properties file.");
    }

    public int getFiveMinutes()
    {
        if (!properties.getProperty("fiveMinutes").equalsIgnoreCase(""))
        {
            String longWait = properties.getProperty("fiveMinutes");
            return Integer.parseInt(longWait);
        }
        else throw new RuntimeException("FIVEMINUTES not specified in the config.properties file.");
    }
}
