package topic_WebElement;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic08_DropdownCustomDropdown {
	WebDriver driver;
	// Delare variable
		@BeforeClass
		public void beforeClass() {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get("https://demo.nopcommerce.com");
		}

		@Test
		public void TC_01_htmlDropdownList() {
			System.out.println("TC 02 - Click Register link");	
			driver.findElement(By.xpath("//a[text()='Register']")).click();
			
			//Input Your Personal Details data
			System.out.println("TC 03 - Input data valid");	
			driver.findElement(By.xpath("//input[@value='M']")).isSelected();;
			driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Sunny");;
			driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("My");;
			
			Select selectDay = new Select(driver.findElement(By.name("DateOfBirthDay"))); 
			selectDay.selectByVisibleText("1");
			Assert.assertEquals(32,selectDay.getOptions().size());
			
			Select selectMonth = new Select(driver.findElement(By.name("DateOfBirthMonth"))); 
			selectMonth.selectByValue("5");
			Assert.assertEquals(13, selectMonth.getOptions().size());
			
			Select selectYear = new Select(driver.findElement(By.name("DateOfBirthYear"))); 
			selectYear.selectByValue("1980");
			Assert.assertEquals(112, selectYear.getOptions().size());
			
			driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("mysun22111@gmail.com");
			driver.findElement(By.xpath("//input[@id='Company']")).sendKeys("The Sky1");
			driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("333111");
			driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("333111");;
			
			System.out.println("TC 04 - Click Register button");	
			driver.findElement(By.xpath("//input[@id='register-button']")).click();
			
			System.out.println("TC 05 - Verify Home page");	
			Assert.assertTrue(driver.findElement(By.xpath("//a[text()='My account']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Log out']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Your registration completed']")).isDisplayed());
			
		}


		@AfterClass
		public void afterClass() {
			driver.quit();
		}

}
