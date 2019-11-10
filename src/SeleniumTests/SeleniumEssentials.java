package SeleniumTests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.TestNG;

import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.internal.*;


public class SeleniumEssentials {
	WebDriver driver;
	@BeforeClass
	public void Setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\prasa\\OneDrive\\Documents\\SeleniumDrivers\\chrome\\chromedriver_win32\\chromedriver.exe");
		
	}
	
	@BeforeMethod
	public void LaunchApp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://formy-project.herokuapp.com/switch-window");
		
		Assert.assertEquals(driver.getTitle(), "Formy");
		System.out.println("Title:"+driver.getTitle());
	}
	@Test(groups="swithorAlert")
	public void switchWindow() {
		// Use url "https://formy-project.herokuapp.com/switch-window" for switch window
		//WebDriverWait wait = new WebDriverWait(driver,15);
		//WebElement switchHeading=wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1")));
		//System.out.println("Heading="+switchHeading.getText());
		
		WebElement newtabButton = driver.findElement(By.id("new-tab-button"));
		Assert.assertNotNull(newtabButton, "New tab button not present");
		newtabButton.click();
		
		
		String orgHandle = driver.getWindowHandle();
		for (String handle: driver.getWindowHandles())
		{
			if(!handle.equals(orgHandle)) {
				driver.switchTo().window(handle);
			}
		}
		System.out.println("Switched window Url="+driver.getCurrentUrl());
		
		// Click on Alert
		
	}
	@Test(groups="swithorAlert")
	public void acceptAlert() {
		// Accept the alert
		WebElement alertButton =driver.findElement(By.id("alert-button"));
		alertButton.click();
		
		Alert alert =driver.switchTo().alert();
		System.out.println("Text on alert :"+alert.getText());
		alert.accept();
	}
	
	@Test(groups="Form")
	public void gotoHomePage() {
		WebElement homelogo = driver.findElement(By.id("logo"));
		homelogo.click();
		System.out.println("In gotoHomePage :"+driver.getCurrentUrl());
	}

	
	@Test(priority=1,groups="Form",dependsOnMethods="gotoHomePage")
	public void Go2CompleteForm() throws InterruptedException {
		WebElement homelogo = driver.findElement(By.id("logo"));
		homelogo.click();
		Thread.sleep(5000);
		
		WebElement cpwebForm = driver.findElement(By.cssSelector("body > div > div > li:nth-child(18) > a"));
		cpwebForm.click();
		Thread.sleep(5000);
		WebElement heading_cpform= driver.findElement(By.tagName("h1"));
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(heading_cpform));
		
		
		Assert.assertEquals(heading_cpform.getText(), "Complete Web Form");
	
		System.out.println("heading: "+heading_cpform.getText());
	}
	
	
	@Test(priority=2,groups="Form",dependsOnMethods="Go2CompleteForm")
	public void FilltheForm() throws InterruptedException {
		
		WebElement homelogo = driver.findElement(By.id("logo"));
		homelogo.click();
		Thread.sleep(5000);
		
		WebElement cpwebForm = driver.findElement(By.cssSelector("body > div > div > li:nth-child(18) > a"));
		cpwebForm.click();
		Thread.sleep(5000);
		WebElement heading_cpform= driver.findElement(By.tagName("h1"));
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(heading_cpform));
		
		
		Assert.assertEquals(heading_cpform.getText(), "Complete Web Form");
	
		System.out.println("heading: "+heading_cpform.getText());
		
		WebElement firstName = driver.findElement(By.id("first-name"));
		firstName.sendKeys("Prasad");
		Assert.assertNotNull(firstName, "first name is not entered");
		System.out.println("entered firstname");
		
		WebElement lastName = driver.findElement(By.id("last-name"));
		lastName.sendKeys("Pidaparthy");
		Assert.assertNotNull(lastName, "lastname is not entered");
		System.out.println("entered last name");
		
		WebElement jobTitle = driver.findElement(By.id("job-title"));
		jobTitle.sendKeys("Principal QA Engineer");
		Assert.assertNotNull(jobTitle,"job title is not entered");
		System.out.println("entered jobtitle");
		
		WebElement highEducation = driver.findElement(By.id("radio-button-3"));
		highEducation.click();
		Assert.assertTrue(highEducation.isSelected(), "high education is not entered");
		System.out.println("selected high Education");
		
		WebElement sex = driver.findElement(By.id("checkbox-1"));
		sex.click();
		Assert.assertTrue(sex.isSelected(),"sex is not entered");
		System.out.println("selected sex");
		
		Select select = new Select(driver.findElement(By.id("select-menu")));
		select.selectByValue("4");
		Assert.assertNotNull(select.getFirstSelectedOption(),"value in dropdown not selected");
		System.out.println("selected value ="+select.getFirstSelectedOption().getText());
		
		WebElement datepic = driver.findElement(By.id("datepicker"));
		datepic.click();
		datepic.sendKeys("08/01/1983");
		datepic.sendKeys(Keys.RETURN);
		Assert.assertNotNull(datepic, "date is not entered");
		System.out.println("entered date");
		
		WebElement submit = driver.findElement(By.cssSelector("body > div.container > form > div > div:nth-child(15) > a"));
		submit.click();
		System.out.println("clicked on  submit");

	
		
		
		
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
