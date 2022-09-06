package com.qa.demo.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    public WebDriver driver;
    public Properties prop;
    public static String highlight;
    public OptionsManager optionsManager;

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

    /**
     * This method is used to initialize the webdriver
     *
     * @param browserName
     * @return this will return the driver
     */
    public WebDriver init_driver(Properties prop) {
        String browserName = prop.getProperty("browser").trim();

        System.out.println("browser name is : " + browserName);
        highlight = prop.getProperty("highlight");
        optionsManager = new OptionsManager(prop);

        if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
        } else if (browserName.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
        } else {
            System.out.println("please pass the right browser: " + browserName);
        }

        getDriver().manage().window().fullscreen();
        getDriver().manage().deleteAllCookies();

        URL url;

        try {
            url = new URL(prop.getProperty("url"));
            openUrl(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return getDriver();
    }


    /**
     * getdriver(): it will return a thread local copy of the webdriver
     */
    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }


    /**
     * this method is used to initialize the properties
     *
     * @return this will return properties prop reference
     */
    public Properties init_prop() {
        prop = new Properties();
        FileInputStream ip = null;
        try {
            ip = new FileInputStream("./src/test/resources/config/config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }

    /**
     * take screenshot
     */

    public String getScreenshot() {
        File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(src, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }


    /**
     * launch url
     *
     * @param url
     */
    public void openUrl(String url) {
        try {
            if (url == null) {
                throw new Exception("url is null");
            }
        } catch (Exception e) {

        }
        getDriver().get(url);
    }

    public void openUrl(URL url) {
        try {
            if (url == null) {
                throw new Exception("url is null");
            }
        } catch (Exception e) {

        }
        getDriver().navigate().to(url);
    }

}
