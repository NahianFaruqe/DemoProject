package com.qa.demo.base;

import java.util.Properties;

import com.qa.demo.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import com.qa.demo.factory.DriverFactory;

public class BaseTest {

    DriverFactory df;
    protected Properties prop;
    WebDriver driver;
    protected HomePage homePage;
    SoftAssert softAssert;


    @Parameters({"browser"})
    @BeforeMethod
    public void setup(String browser) {
        df = new DriverFactory();
        prop = df.init_prop();
        driver = df.init_driver(prop);
        homePage = new HomePage(driver);
        softAssert = new SoftAssert();
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
