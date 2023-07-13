package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.pages.base.BasePages;

public class LoginPages extends BasePages {
	

	@FindBy(id ="username") WebElement userNameElement;
	@FindBy(id = "password") WebElement passwordElement;
	@FindBy(id = "Login") WebElement loginButton;
	@FindBy(id="error")WebElement errorElement;
	@FindBy(id="rememberUn")WebElement RemembermecheckBox;
	@FindBy(xpath= "//a[@id='forgot_password_link']")WebElement forgotPasswordEle;
	@FindBy(xpath="//div[@id='error']")WebElement errorElement2;
	@FindBy(id= "idcard-identity")WebElement USerNamePopulated;
	
	
	
	public LoginPages(WebDriver driver) {
		
		super(driver);
	}
	public void clearAllTextuseranme(){
		cleartext(userNameElement);
	}
	public void clearAllTextPassword(){
		cleartext(passwordElement);
	}
	
	
	public void enterUsername(String usernamedata) {
		enterText(userNameElement, usernamedata, "username field");
	}
	
	public void enterPassword(String passworddata) {
		enterText(passwordElement, passworddata, "password field");
	}
	
	public WebDriver clickLoginButton() {
		clickElement(loginButton,"login button");
		return driver;
	}
	
	public String getTitleOfThePage() {
		//waitUntilPageLoads();
		return getPageTitle();
	}
	
	public String getErrorTest() {
		return getTextFromWebElement(errorElement,"errorText" );
	}
	
	public void clickcheckBox() {
		if(!RemembermecheckBox.isSelected()) {
		clickElement(RemembermecheckBox,"RememeberMeCheckBox");
		}
	}
    
	public WebDriver clickForgotPassword(){
		clickElement(forgotPasswordEle, "ForgotPasswordLink");
		return driver;
	}
	
	public boolean checkifUsernameisPopulated(String text) {
	    boolean ifusernamepopulated= USerNamePopulated.getText().contains(text);
		if(ifusernamepopulated) {
			log.info("username is populated with the"+ text);
		}
		else {
			log.info("username is not not populated");
		}
		return ifusernamepopulated;
		
	}
	
	public boolean checkIfCheckBoxselected() {
		boolean ifselected= RemembermecheckBox.isSelected();
		return ifselected;
		
	}
	
	public void selectRememeberMeCheckBox() {
		boolean ifselected= RemembermecheckBox.isSelected();
		if(!ifselected) {
		clickElementByJavaScriptExecutor(RemembermecheckBox);	
		}
		
	}
	
	 public WebDriver navigatingfromLoginToHome(String usertext,String pwdtext ) {
		 clearAllTextuseranme();
		 enterUsername(usertext);
		 clearAllTextPassword();
		 enterPassword(pwdtext);
		 clickcheckBox();
		 clickLoginButton();
		 return driver;
		 
	  }
	 
	 public void  navigatingfromLoginToHomeNotReturingdriver(String usertext,String pwdtext ) {
		 clearAllTextuseranme();
		 enterUsername(usertext);
		 clearAllTextPassword();
		 enterPassword(pwdtext);
		 clickcheckBox();
		 clickLoginButton(); 
	 }
	 
	 
	
}
