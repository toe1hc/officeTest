package topic_WebElement;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Wait_Part_IV_Mixing {
	WebDriver driver;
	WebDriverWait waitExplicit;

// Delare variable
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		// Explicit Wait (Element status)
//		waitExplicit = new WebDriverWait(driver, 10);

		// Implicit Wait (Find Element/s)
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.manage().window().maximize();

	}

	public void TC_01_Found_Element() {
		driver.get("http://demo.guru99.com/");

		// Implicit
		System.out.println("--------STEP 01 - Start TC_01_Found_Element: " + new Date() + "--------");
		try {
			WebElement emailTxtbox = driver.findElement(By.xpath("//input[@name='emailid']"));
			Assert.assertTrue(emailTxtbox.isDisplayed());

		} catch (NoSuchElementException ex) {
			System.out.println("Switch to catch exception!");
		}
		System.out.println("--------STEP 01 - End TC_01_Found_Element: " + new Date() + "--------");

		// Explicit
		System.out.println("--------STEP 02 - Start TC_02_Found_Element: " + new Date() + "--------");
		try {
			waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='btnLogin']")));
		} catch (NoSuchElementException ex) {
			System.out.println("Switch to catch exception!");
		}

		System.out.println("--------STEP 02 - End TC_02_Found_Element: " + new Date() + "--------");
	}

	@Test
	public void TC_02_Not_Found_Element() {
		// Explicit Wait (Element status)
		waitExplicit = new WebDriverWait(driver, 7);

		// Implicit Wait (Find Element/s)
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("http://demo.guru99.com/");

		// Implicit (Not found element)
		System.out.println("--------STEP 01 - Start TC_01_Implicit: " + new Date() + "--------");
		try {
			WebElement emailTxtbox = driver.findElement(By.xpath("//input[@name='automation-testing']"));
			Assert.assertTrue(emailTxtbox.isDisplayed());
		} catch (NoSuchElementException ex) {
			System.out.println("--STEP 01 - Switch to catch exception!");
			System.out.println(ex.getMessage());
		}
		System.out.println("--------STEP 01 - End TC_01_Implicit: " + new Date() + "--------");

		// Explicit (Not found - tham số là web element)
		System.out.println("--------STEP 02 - Start TC_02_Start Explicit (WebElement): " + new Date() + "--------");
		try {
			WebElement emailTxtbox = waitExplicit.until(ExpectedConditions
					.visibilityOf(driver.findElement(By.xpath("//input[@name='automation-testing']"))));
			Assert.assertTrue(emailTxtbox.isDisplayed());
		} catch (Exception ex) {
			System.out.println("--STEP 02 - Switch to catch exception!");
			System.out.println(ex.getMessage());
		}
		System.out.println("--------STEP 02 - End TC_02_Found_Element: " + new Date() + "--------");

		// Explicit (Not found - tham số là By)
		System.out.println("--------STEP 03 - Start TC_03_Start Explicit: By " + new Date() + "--------");
		try {
			WebElement emailTxtbox = waitExplicit.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='automation-testing']")));
			Assert.assertTrue(emailTxtbox.isDisplayed());
		} catch (Exception ex) {
			System.out.println("--STEP 03 - Switch to catch exception!");
			System.out.println(ex.getMessage());
		}

		System.out.println("--------STEP 03 - End TC_03_Start Explicit: By " + new Date() + "--------");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}