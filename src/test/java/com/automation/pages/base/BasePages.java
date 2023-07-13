package com.automation.pages.base;

import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.utility.Log4JUtility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class BasePages {
	protected  WebDriver driver;
	protected  WebDriverWait wait;
	protected Log4JUtility logObject=Log4JUtility.getInstance();
	protected Logger log;
	
	
	@FindBy(id ="username") WebElement userNameElement;
	@FindBy(id = "password") WebElement passwordElement;
	@FindBy(id = "Login") WebElement loginButton;
	@FindBy(id= "userNavLabel") WebElement UserMenuDropDown;
	@FindBy(xpath="//a[text()= 'Logout']" ) WebElement logout;
	
	
	public void selectinguserMenuDropDown() {
		applyImplicitWait();
		waitUntilElementIsClickable(UserMenuDropDown);
		clickElementByJavaScriptExecutor(UserMenuDropDown);
	}
	
	public BasePages(WebDriver driver) {
		this.driver=driver;
		System.out.println("driver in basePage="+driver);
		PageFactory.initElements(driver, this);
		log=(Logger) logObject.getLogger();
		
	}
	
      

	public WebDriver navigatingfromLoginToHome(String usertext,String pwdtext ) {
		cleartext(userNameElement);
		enterText(userNameElement,usertext, "UsernametextBox");
		cleartext(passwordElement);
		enterText(passwordElement,pwdtext,"PasswordBox");	
		clickElement(loginButton,"loginButton");
		 return driver;
		 
	  }
		public void takingElementIntoViewByJavaScriptExecutor(WebElement element ) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		}
	
	 public void cleartext(WebElement element) {
	    	element.clear();
	    }
	
	 public void enterText(WebElement element, String Data, String obj){
	        if (element.isDisplayed()) {
	            log.info("element displayed");
	            element.clear();
	            element.sendKeys(Data);
	           log.info("password entered in the" + obj+ "field");

	        } else {
	            System.out.println("element not displayed");
	        }

	    }
	 public void enterTextToFile(WebElement element, String Data) {
		 if (element.isDisplayed()) {
	            log.info("element displayed");
	            element.sendKeys(Data);

	        } else {
	            System.out.println("element not displayed");
	        }
	 }
	  public void clickElement(WebElement element, String obj) {
		  if(!element.isSelected()) {
	    	element.click();}	
	    	
	    }
	  
	  public String  getPageTitle() {
	    	String title= driver.getTitle();
	    	return title;
	    	
	    }
	  
	  public String getTextFromWebElement(WebElement element, String obj){
	        if(element.isDisplayed()){
	            return element.getText();
	        }
	        else{
	            log.info("element not displayed");
	            return null;
	        }
	    }
	  
	  public WebDriver selectOptionFromDropDownByVisibleText( WebElement statcDropDownEle,String elementtext, String obj) {
		Select s= new Select(statcDropDownEle);
		s.selectByVisibleText(elementtext);
		log.info(obj+ "selected from dropdown");
		return driver;
		
	  }
	  public void selectFromDropDownByVisibleText(WebElement element, String elementtext, String obj) {
			Select s= new Select(element);
			s.selectByVisibleText(elementtext);
			log.info(obj+ "selected from dropdown");
	  }
	  
	  public WebDriver selectOptionFromDropDownByIndex(WebElement statcDropDownEle,int index, String obj) {
		  Select s= new Select(statcDropDownEle);
		  s.selectByIndex(index);
		  log.info(obj+ "selected from dropdown");
	      return driver;
}

	    public void  applyImplicitWait(){
	        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	    }
	   
	    public  void waituntilAlertIsPresent() {
	        log.info("waiting for alert to be present");
	        wait = new WebDriverWait(driver, 30);
	        wait.until(ExpectedConditions.alertIsPresent());
	    }

	    public  void waitUntilElementIsClickable(WebElement element) {
	        System.out.println("waiting for element to be clickable");
	        wait = new WebDriverWait(driver, 30);
	        wait.until(ExpectedConditions.elementToBeClickable(element));

	    }

	    public void waituntilElementIsSelectable(WebElement element, String obj) {
	        log.info("waiting for Element to get selected ");
	        wait = new WebDriverWait(driver, 30);
	        wait.until(ExpectedConditions.elementToBeSelected(element));
	    }

	    public void fluentWaitforVisibility(WebElement element) {
	        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	                .withTimeout(Duration.ofSeconds(30))
	                .pollingEvery(Duration.ofSeconds(5))
	                .ignoring(NoSuchElementException.class);
	        wait.until(ExpectedConditions.visibilityOf(element));
	    }
	    public  void ExplicitWaitforVisibility(WebElement element, String obj){
	       log.info("waiting for" + obj+ "to be visible");
	        wait = new WebDriverWait(driver, 30);
	        wait.until(ExpectedConditions.visibilityOf(element));
	        
	    }

	    public  void ElementNoInteractable(WebElement element){
	      log.info("wait until element is interacatable");
	        wait = new WebDriverWait(driver, 30);
	        wait.until(ExpectedConditions.elementToBeClickable(element));

	    }

	    public Alert switchToAlert() {
	        waituntilAlertIsPresent();
	        Alert alert = driver.switchTo().alert();
	        log.info("Switched to alert");
	        return alert;
	    }
	   
	    public void switchToFrame(String id) {
	    	driver.switchTo().frame(id)	;
	    }
	    
	  public void clickElementByJavaScriptExecutor(WebElement element) {
	        JavascriptExecutor executor = (JavascriptExecutor) driver;
	        executor.executeScript("arguments[0].click();", element);
	    }
	    
	    public void hoveringMouseToElement(WebElement element){
	    	Actions action = new Actions(driver);
	    	action.moveToElement(element).click().perform();
	    	
	    }
	    
	    public void switchingFromHomeToDeveloperConsoleHandleWindows() {
	    	  Set<String> windows = driver.getWindowHandles(); //[parentid,childid,subchildId]
	          Iterator<String> it = windows.iterator();
	          String parentId = it.next();
	          String childId = it.next();
	          driver.switchTo().window(childId);
	          driver.close();
	          driver.switchTo().window(parentId);
	    }
	    
	    public void switchingFromParentToChildAndThenParentHandle() {
	    	  Set<String> windows = driver.getWindowHandles(); //[parentid,childid,subchildId]
	          Iterator<String> it = windows.iterator();
	          String parentId = it.next();
	          String childId = it.next();
	          driver.switchTo().window(childId);
	          driver.close();
	          driver.switchTo().window(parentId);	
	    }
	    public void switchingFromParentToChildHandle() {
	    	 Set<String> windows = driver.getWindowHandles(); //[parentid,childid,subchildId]
	          Iterator<String> it = windows.iterator();
	          String parentId = it.next();
	          String childId = it.next();
	          driver.switchTo().window(childId);
	          	
	    }
	    public void switchToHometopopupWindowAndClose() {
	    	 Set<String> windows = driver.getWindowHandles(); //[parentid,childid,subchildId]
	          Iterator<String> it = windows.iterator();
	          String parentId = it.next();
	          String childId = it.next();
	          driver.switchTo().window(childId);
	          driver.close();
	          driver.switchTo().window(parentId);
	    	
	    }
	  
	 
		public WebDriver selectingLogoutFromHome() {
			selectinguserMenuDropDown();
			clickElement(logout, "logout");
			return driver;
			
		}
		
		
		
	  
	  

	
	
	

}

