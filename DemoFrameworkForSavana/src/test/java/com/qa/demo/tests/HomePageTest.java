package com.qa.demo.tests;

import com.qa.demo.base.BaseTest;
import com.qa.demo.pages.LoginPage;
import com.qa.demo.pages.SearchResultsPage;
import com.qa.demo.utils.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.demo.utils.Constants;
import com.qa.demo.utils.Errors;


public class HomePageTest extends BaseTest {


    @Test(priority = 1)
    public void myDemoTestOne() {
        LoginPage loginPage = homePage.clickOnSignInLink();
        loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage, Constants.LOGIN_ERROR_MESSAGE);
    }

    @Test(priority = 2)
    public void myDemoTestTwo() {
        String header = homePage.getHomePageTitle();
        System.out.println("Home page header is: " + header);
        Assert.assertEquals((header.contains("Amazon.com")), true, Errors.HOME_PAGE_HEADER_NOT_FOUND_ERROR_MESSAGE);
    }


    @DataProvider
    public Object[][] productData() {
        return ExcelUtil.getTestData(Constants.PRODUCT_SHEET_NAME);
		/*
		return new Object[][]{
				{"Oneplus"},
				{"Apple"},
				{"Samsung"},
		};
		*/
    }


    @Test(priority = 3, dataProvider = "productData")
    public void myDemoTestThree(String productName) {
        SearchResultsPage searchResult = homePage.doSearch(productName);
        String firstSearchResultValue = searchResult.getFirstResultText();
        System.out.println("Actual Result : " + firstSearchResultValue);
        System.out.println("Expected Result : " + productName);
        Assert.assertEquals((firstSearchResultValue.contains(productName)), true);
    }


}
