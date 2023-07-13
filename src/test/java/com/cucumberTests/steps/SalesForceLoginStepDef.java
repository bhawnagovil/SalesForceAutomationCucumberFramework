package com.cucumberTests.steps;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.automation.pages.CheckYourEmail;
import com.automation.pages.ForgetPasswordPOM;
import com.automation.pages.HomePages;
import com.automation.pages.LoginPages;
import com.automation.utility.PropertiesUtility;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForceLoginStepDef {
	public LoginPages loginpage;
	public HomePages homepage;
	public ForgetPasswordPOM forgetpw;
	public CheckYourEmail checkemail;
	 public static Logger log;
	 public WebDriver driver;
	 public WebDriverWait wait;
	 public PropertiesUtility pro;
	  public Properties appProp;;
	  public String userId;
	  public String password;
	
	@BeforeAll
	public static void setUpBeforeAllScenarios() {
		log= (Logger) LogManager.getLogger();
	}
	
	@Before
	public void setUpBeforeEachScenario() {
		LaunchBrowser("chrome")	;
	}
	
	

    public void LaunchBrowser(String browsername){
        switch(browsername){
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver= new FirefoxDriver();
                driver.manage().window().maximize();
                break;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions option = new ChromeOptions();
                option.addArguments("--remote-allow-origins=*");
                 driver = new ChromeDriver(option);
                driver.manage().window().maximize();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver= new EdgeDriver();
                driver.manage().window().maximize();
                break;

        }

       log.info(browsername+ "is opened");
    }
    
    @After
    public void tearDown() {
    	driver.close();	
    	}
    
    public void getURL(String url){
        driver.get(url);
        log.info("url enetered in the url field");

    }

    public void  applyImplicitWait(){
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    }
    
    public  void waitUntilElementIsClickable(WebElement element) {
        System.out.println("waiting for element to be clickable");
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(element));

    }
    
    public String  getTitleofPage(WebDriver driver){
    	this.driver= driver;
        String title=driver.getTitle();
        return title;

    }
	@Given("user open Salesforce application")
	public void user_open_salesforce_application() {
		 pro= new PropertiesUtility();
		 appProp  = pro.loadFile("applicationDataProperties");
		String url= appProp.getProperty("Loginurl");
		getURL(url);
		System.out.println("url :"+ url);		
		log.info("url entered in the Address box");
		
	} 
	

	@When("user is on {string}")
	public void user_is_on(String page) {
	   if(page.equalsIgnoreCase("LoginPage"))
		   loginpage = new LoginPages(driver);
	   else if(page.equalsIgnoreCase("HomePage"))
		   homepage= new HomePages(driver);
	   else if(page.equalsIgnoreCase("ForgotPasswordPage"))
		   forgetpw = new   ForgetPasswordPOM(driver);
	   else if(page.equalsIgnoreCase("CheckYourEmailPage"))
		   checkemail= new   CheckYourEmail(driver);
		   
	}

	@When("user enters valid username")
	public void user_enters_valid_username() {
		 pro=new PropertiesUtility();
		 appProp= pro.loadFile("applicationDataProperties");
		 userId=appProp.getProperty("login.valid.userid");
		 password=appProp.getProperty("login.valid.password");
		 loginpage=new LoginPages(driver);
		loginpage.clearAllTextuseranme();
		loginpage.enterUsername(userId);
		
		
	}

	@When("user clicks on login button")
	public void user_clicks_on_login_button() {
		applyImplicitWait();
		loginpage.clickLoginButton();
	}

	@Then("user verify error message1")
	public void user_verify_error_message() {
		String actualError= loginpage.getErrorTest();
		String expectedError=appProp.getProperty("login.valid.error1");
		Assert.assertEquals(actualError, expectedError, "actual and expected errors are not matching");
		log.info("Actual error is matching with the expected error");
	}

	@When("user enters valid username and valid password")
	public void user_enters_valid_username_and_valid_password() {
		 pro=new PropertiesUtility();
		 appProp= pro.loadFile("applicationDataProperties");
		 userId=appProp.getProperty("login.valid.userid");
		 password=appProp.getProperty("login.valid.password");
		 loginpage=new LoginPages(driver);
		loginpage.clearAllTextuseranme();
		loginpage.enterUsername(userId);
		loginpage.clearAllTextPassword();
		loginpage.enterPassword(password); 
	}

	@Then("verify Home Page Title")
	public void verify_home_page_title() {
		String actualTitle= getTitleofPage(driver);
		System.out.println(actualTitle);
		String expectedTitle= appProp.getProperty("login.valid.HomePageTitle");
		Assert.assertEquals(actualTitle, expectedTitle, "expected and actual titles are not matching");	
		log.info("Actual title matching with the expected title");
		
	}

	@When("click remember me checkbox")
	public void click_remember_me_checkbox() {
		loginpage.selectRememeberMeCheckBox();
	}

	@When("user click logout")
	public void user_click_logout() {
		driver= homepage.selectLogoutElementfromDropDown();}
		//loginpage.checkifUsernameisPopulated(userId);
	
	

	@Then("username is displayed in the username field")
	public void username_is_displayed_in_the_username_field() {
		 pro=new PropertiesUtility();
		 appProp= pro.loadFile("applicationDataProperties");
		 userId=appProp.getProperty("login.valid.userid");
		Assert.assertTrue(loginpage.checkifUsernameisPopulated(userId),"username field is not populated");
		log.info("test passed username field is populated");
	}

	@When("user clicks the Forgotpassword link")
	public void user_clicks_the_forgotpassword_link() {
		loginpage.clickForgotPassword();
	}

	@When("User clicks returnToLogin")
	public void User_clicks_returnToLogin() {
		driver= checkemail.clickReturnToLoginButton();
		
	}

	

	@Then(" Then Verify user is on LoginPage by verifying the LoginPage title")
	public void Verify_user_is_on_LoginPage_by_verifying_the_LoginPage_title() {
		String expectedpagetitle= appProp.getProperty("loginPageTitle");
		loginpage=new LoginPages(driver);
		String actulTitle=driver.getTitle();
		Assert.assertEquals(actulTitle,expectedpagetitle, "actual and expected title are not matching");
		log.info("Login page title is correct and displayed");
	}

	@When("User enters invalid username and invalid password")
	public void user_enters_invalid_username_and_invalid_password() throws InterruptedException {
		log.info("Checking Error meassage when wrong username and password entered");
		 pro=new PropertiesUtility();
		 appProp= pro.loadFile("applicationDataProperties");
		 String WronguserId=appProp.getProperty("login.invalid.userid");
		 String wrongPassword= appProp.getProperty("login.invalid.password");
		 loginpage=new LoginPages(driver);
		 Thread.sleep(4000);
		 loginpage.navigatingfromLoginToHome(WronguserId,wrongPassword);
	}

	@Then("verfiy error message")
	public void verfiy_error_message() {
		 String actualerror= loginpage.getErrorTest();
		 String expectedError= appProp.getProperty("login.valid.error2");
		 Assert.assertEquals(actualerror, expectedError, "errortext is not matching");
	}


}
