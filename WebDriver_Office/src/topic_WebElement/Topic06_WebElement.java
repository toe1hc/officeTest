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

public class Topic06_WebElement {
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
	By interestCheckboxDisabledBy = By.xpath("//input[@id='check-disbaled']");
	By slider02DisabledBy = By.xpath("//input[@id='slider-2']");
	
	By emailTxtboxBy = By.xpath("//input[@id='email']");
	By usernameTxtboxBy = By.xpath("//input[@id='new_username']");
	By passwordTxtboxBy = By.xpath("//input[@id='new_password']");
	By signUpBtnBy = By.xpath("//button[@id='create-account']");
	By receiveMailcheckboxBy = By.xpath("//input[@id='marketing_newsletter']");
	By lowercaseLabelBy = By.xpath("//div[@class='field-wrapper']//li[@class='lowercase-char']");
	By uppercaseLabelBy = By.xpath("//div[@class='field-wrapper']//li[@class='uppercase-char']");
	By numbercaseLabelBy = By.xpath("//div[@class='field-wrapper']//li[@class='number-char']");
	By specialLabelBy = By.xpath("//div[@class='field-wrapper']//li[@class='special-char']");
	By eightminimumLabelBy = By.xpath("//div[@class='field-wrapper']//li[@class='8-char']");
	By h4By = By.xpath("//h4");
	
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
		Assert.assertFalse(isElementEnabled(interestCheckboxDisabledBy));
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

	@Test
	public void TC_04_Register_MailChimp() throws InterruptedException {
		System.out.println("Step 01 - Open Url");
		driver.get("https://login.mailchimp.com/signup/");
		
		System.out.println("Step 02 - Enter Email/Username");
		sendKeyToElement(emailTxtboxBy, "toyenlinh2012@gmail.com");
		sendKeyToElement(usernameTxtboxBy, "hannah");
		
		System.out.println("Step 03 - Verify validate Password");
		sendKeyToElement(passwordTxtboxBy, "Hanah@20");
		Thread.sleep(3000);

//		Assert.assertFalse(isElementEnabled(lowercaseLabelBy));
//		Assert.assertFalse(isElementEnabled(uppercaseLabelBy));
//		Assert.assertFalse(isElementEnabled(numbercaseLabelBy));
//		Assert.assertFalse(isElementEnabled(specialLabelBy));
//		Assert.assertTrue(isElementEnabled(eightminimumLabelBy));
		Assert.assertTrue(isElementDisplayed(h4By));

		System.out.println("Step 04 - Sign Up btn is disabled if Password is invalid");
		Assert.assertTrue(isElementEnabled(signUpBtnBy));	
		
		System.out.println("Step 05- Check checkbox");
		clickElement(receiveMailcheckboxBy);
		Assert.assertTrue(isElementSelected(receiveMailcheckboxBy));

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
			System.out.println("Element [" + by + "] is displayed");
			return true;
		} else {
			System.out.println("Element [" + by + "] is undisplayed");
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
