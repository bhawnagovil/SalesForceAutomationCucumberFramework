package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.pages.base.BasePages;

public class HomePages extends BasePages {
	
	//public HomePage h;
	
	public @FindBy(xpath= "//span[@id='userNavLabel']") WebElement UserMenuDropDown;
	public @FindBy(xpath= "//a[text()= 'Logout']")WebElement Logout;
	

	public HomePages(WebDriver driver) {
		super(driver);
		
	}
	

	public WebDriver selectLogoutElementfromDropDown() {
		waitUntilElementIsClickable(UserMenuDropDown);
		selectinguserMenuDropDown();
		clickElement(Logout, getPageTitle());
		return driver;
	}
	
	public WebDriver selectMYProfileElementfromDropDown(String text) {
		selectOptionFromDropDownByVisibleText(UserMenuDropDown, text, "My Profile");
		
		return driver;
	}
	public WebDriver selectingLogoutFromHome() {
		selectinguserMenuDropDown();
		clickElement(Logout, "logout");
		return driver;
		
	}
	
	
	
	
	
	
	
	

	
	
}
