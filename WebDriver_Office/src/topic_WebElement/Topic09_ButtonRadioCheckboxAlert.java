package topic_WebElement;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic09_ButtonRadioCheckboxAlert {
	WebDriver driver;
	JavascriptExecutor javascript;
	Alert alert;
	

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		javascript = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
 
	public void TC_01_Button_JS() throws InterruptedException {
		String loginBtn = "//button[@class='fhs-btn-login']";
		String emailTxtBox = "login_username";
		String passwordTxtBox = "login_password";
		
		System.out.println("Step 01 - Open URL");
		driver.get("https://www.fahasa.com/customer/account/create");
		
		System.out.println("Step 02 - Navigate Đăng nhập tab");
		driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();
	
		System.out.println("Step 03 - Verify Đăng nhập button là disabled");
		Assert.assertFalse(isElementEnabled(loginBtn));
		
		System.out.println("Step 04 - Email/Mật khẩu textbox");
		sendKeyToElement(emailTxtBox, "xuxu@gmail.com");
		sendKeyToElement(passwordTxtBox, "xuxu123456");
		
		Thread.sleep(5000);
		
		System.out.println("Step 05 - Verify Đăng nhập button là enabled");
		Assert.assertTrue(isElementEnabled(loginBtn));
		Thread.sleep(2000);
		
		System.out.println("Step 06 - Navigate tab Đăng nhập");
		driver.navigate().refresh();
		driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();
		
		
		
		System.out.println("Step 07 - Remove disabled button Đăng Nhập");
		WebElement enable = driver.findElement(By.xpath("//button[@class='fhs-btn-login']"));
		javascript.executeScript("arguments[0].click();", enable);
		Thread.sleep(2000);
		clickElement(loginBtn);
		System.out.println("Step 08 - Click vào button Đăng nhập");
		System.out.println("Step 09 - Check error message");
	
	}

	public void TC_02_DefaultCheckbox()
	{
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		String dualzoneCheckbox = "//input[@id ='eq5']";

		statusElementSelected(dualzoneCheckbox);
		Assert.assertTrue(isElementSelected(dualzoneCheckbox));
		
		statusElementSelected(dualzoneCheckbox);
		clickElement(dualzoneCheckbox);
		Assert.assertFalse(isElementSelected(dualzoneCheckbox));
			
	}
	public void TC_03_CustomCheckbox()
	{
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		String checkboxInput = "//label[text()='Luggage compartment cover']/preceding-sibling::input";
		clickByJS(checkboxInput);
		statusElementSelected(checkboxInput);
		Assert.assertTrue(isElementSelected(checkboxInput));
	
		
		clickByJS(checkboxInput);	
		statusElementSelected(checkboxInput);
		Assert.assertFalse(isElementSelected(checkboxInput));
	
			
	}
	public void TC_04_CheckElementCheckbox()
	{
		driver.get("https://demo.nopcommerce.com/register");
		String newletterCheckbox = "//input[@id='Newsletter']";
		//Unchecked
		driver.findElement(By.xpath(newletterCheckbox)).click();
		Assert.assertFalse(isElementSelected(newletterCheckbox));
		
		//Check
		driver.findElement(By.xpath(newletterCheckbox)).click();
		Assert.assertTrue(isElementSelected(newletterCheckbox));
		
		//Check
		checkElementCheckbox(newletterCheckbox);
		Assert.assertTrue(isElementSelected(newletterCheckbox));
		checkElementCheckbox(newletterCheckbox);
		Assert.assertTrue(isElementSelected(newletterCheckbox));

		//Uncheck
		uncheckElementCheckbox(newletterCheckbox);
		Assert.assertFalse(isElementSelected(newletterCheckbox));
		uncheckElementCheckbox(newletterCheckbox);
		Assert.assertFalse(isElementSelected(newletterCheckbox));
		
	}
		
	public void TC_05_Handel_Alert() throws InterruptedException
	{
		String accepAlert = "//button[@onclick='jsAlert()']";
		driver.get("https://automationfc.github.io/basic-form/index.html");
		clickElement(accepAlert);
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		String resultActual = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals(resultActual, "You clicked an alert successfully");
		
	}
	
	public void TC_06_Confrim_Alert() throws InterruptedException
	{
		String confrimAlert = "//button[text()='Click for JS Confirm']";
		driver.get("https://automationfc.github.io/basic-form/index.html");
		clickElement(confrimAlert);
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		String resultActual = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals(resultActual, "You clicked: Cancel");
		
	}
	
	public void TC_07_Promt_Alert() throws InterruptedException
	{
		String promtAlert = "//button[text()='Click for JS Prompt']";
		String fullName = "Hannah lovely";
		driver.get("https://automationfc.github.io/basic-form/index.html");
		clickElement(promtAlert);
		alert = driver.switchTo().alert();
		alert.sendKeys(fullName);
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.accept();
		String resultActual = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals(resultActual, "You entered: "+ fullName);
		
	}

	@Test
	public void TC_08_AuthenticationAlert() throws InterruptedException {
		String username = "admin";
		String password = "admin";
		String url="http://the-internet.herokuapp.com/basic_auth";
		url = "http://"+ username + ":" + password + "@the-internet.herokuapp.com/basic_auth";
		driver.get(url);
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
		
	}
	
	public void statusElementSelected(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isSelected()) {
			System.out.println("Element [" + locator + "] is selected");
			
		} else {
			System.out.println("Element [" + locator + "] is unselected");
			
		}
	}
	public boolean isElementEnabled(String locator ) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isEnabled()) {
			System.out.println("Element [" + locator + "] is enabled");
			return true;
		} else {
			System.out.println("Element [" + locator + "] is disabled");
			return false;
		}
	}


	public boolean isElementSelected(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isSelected()) {
			System.out.println("Element [" + locator + "] is selected");
			return true;
		} else {
			System.out.println("Element [" + locator + "] is unselected");
			return false;
		}
	}

	public void clickByJS(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		javascript.executeScript("arguments[0].click();", element);
		

	}

	public void sendKeyToElement(String locator, String value) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.sendKeys(value);

	}

	public void clickElement(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();

	}
	
	public void checkElementCheckbox(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (!element.isSelected()) {
			element.click();	
		}
	}
	public void uncheckElementCheckbox(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isSelected()) {
			element.click();	
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
