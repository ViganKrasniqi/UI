package com.polymathlab.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.testng.Assert.fail;

/**
 * This class is used to store all URL for our app
 * This class is a config class that sets up config.properties file in resources package.
 */
public class PageURL
{
    public static final String SERVICES = "services";
    public static final String COMPETENCIES = "competencies";
    public static final String BLOG = "blog";
    public static final String ABOUT = "about";
    public static final String CONTACT_US = "contact";

    // Page objects
    private Properties properties;

    /**
     *   Class CONSTRUCTOR
     */
    public PageURL()
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

    public String getBaseURL()
    {
        if (!properties.getProperty("baseURL").equalsIgnoreCase(""))
        {
            return properties.getProperty("baseURL");
        }
        else throw new RuntimeException("BASEURL not specified in the config.properties file.");
    }

    // This method returns app url based on the tabName/optionName user has specified
    public String setPageURL(String pageName)
    {
        String pageURL;

        switch (pageName)
        {
            case "Home":
                pageURL = getBaseURL();
                break;
            case "Services":
                pageURL =  getBaseURL() + SERVICES;
                break;
            case "Competencies":
                pageURL =  getBaseURL() + COMPETENCIES;
                break;
            case "Blog":
                pageURL = getBaseURL() + BLOG;
                break;
            case "About":
                pageURL = getBaseURL() + ABOUT;
                break;
            case "Contact us":
                pageURL = getBaseURL() + CONTACT_US;
                break;
            default:
                pageURL = null;
                break;
        }
        if (pageURL == null) fail("[name] is NULL");

        return pageURL;
    }
}
