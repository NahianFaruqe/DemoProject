package com.qa.demo.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.demo.factory.DriverFactory;

public class ElementUtil {

    private WebDriver driver;
    private JavaScriptUtil jsUtil;

    public ElementUtil(WebDriver driver) {
        this.driver = driver;
        jsUtil = new JavaScriptUtil(driver);
    }


    public WebElement getElement(By locator) {
        WebElement element = driver.findElement(locator);
        if (Boolean.parseBoolean(DriverFactory.highlight)) {
            jsUtil.flash(element);
        }
        return element;
    }

    public void doClear(By locator) {
        getElement(locator).clear();
    }

    public void doSendKeys(By locator, String value) {
        doClear(locator);
        getElement(locator).sendKeys(value);
    }

    public void doClick(By locator) {
        getElement(locator).click();
    }


    public String doGetText(By locator) {
        return getElement(locator).getText();
    }


    /**************** wait util for Non-WebElements *****************/

    public String doGetTitleWithFraction(String titleFraction, int timeOut) {
        if (waitForTitleContains(titleFraction, timeOut)) {
            return driver.getTitle();
        }
        return null;
    }


    public boolean waitForTitleContains(String titleFraction, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.titleContains(titleFraction));
    }


}
