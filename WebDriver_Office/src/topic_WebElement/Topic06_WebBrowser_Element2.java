package topic_WebElement;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic06_WebBrowser_Element2 {
	WebDriver driver;

	By emailTextboxBy = By.xpath("//input[@id='mail']");
	By under18AgeRadioBy = By.xpath("//input[@id='under_18']");
	By educationTxtAreaBy = By.xpath("//textarea[@id='edu']");
	By jobRole01DropdownBy = By.xpath("//select[@id='job1']");
	By jobRole02DropdownBy = By.xpath("//select[@id='job1']/option[1]");
	By interestsDevelopmentCheckboxBy = By.xpath("//input[@id='development']");
	By slider01By = By.xpath("//input[@id='slider-1']");
	By passwordTextboxBy = By.xpath("//input[@id='password']");
	By ageRadioDisabledBy = By.xpath("//input[@id='radio-disabled']");
	By biographyBy = By.xpath("//textarea[@id='bio']");
	By jobRole03DropdownBy = By.xpath("//select[@id='job3']");
	By interestChexkboxDisabledBy = By.xpath("//input[@id='check-disbaled']");
	By slider02DisabledBy = By.xpath("//input[@id='slider-2']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println("Step 01 - Open Url");
		driver.get("https://automationfc.github.io/basic-form/index.html");

	}

	@Test
	public void TC_01_Verify_Element_isDisplayed() {
		System.out.println("Step 02 - Verify Email, Age, Education displayed");
		WebElement emailTextbox = driver.findElement(emailTextboxBy);
		Assert.assertTrue(emailTextbox.isDisplayed());
		if (emailTextbox.isDisplayed()) {
			emailTextbox.clear();
			emailTextbox.sendKeys("Automation Testing");
		}

		WebElement under18AgeRadio = driver.findElement(under18AgeRadioBy);
		Assert.assertTrue(under18AgeRadio.isDisplayed());
		if (under18AgeRadio.isDisplayed()) {
			under18AgeRadio.click();
		}

		if (isElementDisplayed(educationTxtAreaBy)) {
			sendKeyToElement(under18AgeRadioBy, "Automation Testing");
		}
	}

	@Test
	public void TC_02_Verify_Element_isEnabled() {
		System.out.println(
				"Step 02 - Verify Email, Age, Education, Job Role 01/02, Interests (Developmemt) Checkbox, Slider01 displayed");
		if (isElementEnabled(emailTextboxBy)) {
			System.out.println("Element [" + emailTextboxBy + "] is enabled");
		} else {
			System.out.println("Element [" + emailTextboxBy + "] is disabled");
		}
		Assert.assertTrue(isElementEnabled(under18AgeRadioBy));
		Assert.assertTrue(isElementEnabled(educationTxtAreaBy));
		Assert.assertTrue(isElementEnabled(jobRole01DropdownBy));
		Assert.assertTrue(isElementEnabled(jobRole02DropdownBy));
		Assert.assertTrue(isElementEnabled(interestsDevelopmentCheckboxBy));
		Assert.assertTrue(isElementEnabled(slider01By));
		Assert.assertFalse(isElementEnabled(passwordTextboxBy));
		Assert.assertFalse(isElementEnabled(ageRadioDisabledBy));
		Assert.assertFalse(isElementEnabled(biographyBy));
		Assert.assertFalse(isElementEnabled(jobRole03DropdownBy));
		Assert.assertFalse(isElementEnabled(interestChexkboxDisabledBy));
		Assert.assertFalse(isElementEnabled(slider02DisabledBy));

	}

	@Test
	public void TC_03_Verify_Element_isSelected() {
		driver.navigate().refresh();

		System.out.println("Step 02 - Click age(under 18), Interest(Development) checkbox");
		clickElement(under18AgeRadioBy);
		clickElement(interestsDevelopmentCheckboxBy);

		System.out.println("Step 03 - Verify Element is Selected");
		Assert.assertTrue(isElementSelected(under18AgeRadioBy));
		Assert.assertTrue(isElementSelected(interestsDevelopmentCheckboxBy));

		System.out.println("Step 04 - Unselected Interest(Development) checkbox");
		clickElement(interestsDevelopmentCheckboxBy);

		System.out.println("Step 05 - Verify Element is Selected");
		Assert.assertTrue(isElementSelected(under18AgeRadioBy));
		Assert.assertFalse(isElementSelected(interestsDevelopmentCheckboxBy));
	}

	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element [" + by + "] is enabled");
			return true;
		} else {
			System.out.println("Element [" + by + "] is disabled");
			return false;
		}
	}

	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isElementSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element [" + by + "] is selected");
			return true;
		} else {
			System.out.println("Element [" + by + "] is unselected");
			return false;
		}
	}

	public void sendKeyToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.sendKeys(value);

	}

	public void clickElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
