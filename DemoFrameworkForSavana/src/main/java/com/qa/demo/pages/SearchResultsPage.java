package com.qa.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.demo.utils.ElementUtil;

public class SearchResultsPage {

    private WebDriver driver;
    private ElementUtil eleUtil;

    private By firstResultLink = By.xpath("(//h2//a//span)[1]");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    public String getFirstResultText() {
        return eleUtil.doGetText(firstResultLink);
    }

}
