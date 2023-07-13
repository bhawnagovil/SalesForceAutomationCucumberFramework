package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.pages.base.BasePages;

public class CheckYourEmail extends BasePages {

	public CheckYourEmail(WebDriver driver) {
		super(driver);
		
	}

	@FindBy(xpath="//a[text()= 'Return to Login']")WebElement ReturnToLogin;
	
	public WebDriver clickReturnToLoginButton() {
		clickElementByJavaScriptExecutor(ReturnToLogin);
		return driver;
	}

	

}
