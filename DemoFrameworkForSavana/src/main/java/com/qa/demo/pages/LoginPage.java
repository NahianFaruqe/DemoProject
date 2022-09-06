package com.qa.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.demo.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

    //declare private driver
    private WebDriver driver;
    private ElementUtil eleUtil;

    //page constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    //By locators
    private By emailTextField = By.id("ap_email");
    private By continueButton = By.id("continue");
    private By passwordTextField = By.id("ap_password");
    private By signInButton = By.id("signInSubmit");
    private By incorrectPasswordWarningMessageText = By.xpath("//*[@class='a-alert-content']//span");


    //Page Actions:
    @Step("Enter Email.....")
    public void enterEmail(String email) {
        eleUtil.doSendKeys(emailTextField, email);
    }

    @Step("Enter Password.....")
    public void enterPassword(String password) {
        eleUtil.doSendKeys(passwordTextField, password);
    }


    @Step("Click on Continue Button.....")
    public void clickContinueButton() {
        eleUtil.doClick(continueButton);
    }


    @Step("Click on SignIn Button.....")
    public void clickSignInButton() {
        eleUtil.doClick(signInButton);
    }

    @Step("Fetching Error Message.....")
    public String getErrorMessage() {
        return eleUtil.doGetText(incorrectPasswordWarningMessageText);
    }

    @Step("SignIn with Credentials.....")
    public void doLogin(String userName, String password) {
        enterEmail(userName);
        clickContinueButton();
        enterPassword(password);
        clickSignInButton();
    }


}
