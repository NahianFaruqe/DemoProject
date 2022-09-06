package com.qa.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.demo.utils.Constants;
import com.qa.demo.utils.ElementUtil;

public class HomePage {

    private WebDriver driver;
    private ElementUtil eleUtil;

    private By searchBox = By.id("twotabsearchtextbox");
    private By searchButton = By.id("nav-search-submit-button");
    private By signInLink = By.id("nav-link-accountList-nav-line-1");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    public String getHomePageTitle() {
        return eleUtil.doGetTitleWithFraction(Constants.HOME_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
    }

    public void enterTextInSearchBox(String searchText) {
        eleUtil.doSendKeys(searchBox, searchText);
    }


    public void clickSearchButton() {
        eleUtil.doClick(searchButton);
    }


    public SearchResultsPage doSearch(String productName) {
        System.out.println("searching the product: " + productName);
        enterTextInSearchBox(productName);
        clickSearchButton();
        return new SearchResultsPage(driver);
    }

    public LoginPage clickOnSignInLink() {
        eleUtil.doClick(signInLink);
        return new LoginPage(driver);
    }

}
