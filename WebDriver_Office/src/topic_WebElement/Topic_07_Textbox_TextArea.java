package topic_WebElement;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic07_Textbox_TextArea {
	WebDriver driver;
	String customerID;
	String username = "mngr265852";
	String password = "ybAqYpu";

	By newCustormer = By.xpath("//a[text()='New Customer']");
	// Input in New Customer(user)/Output (server) data
	String customerName = "Hannah Olala";
	String gender = "female";
	String dateOfBirth = "1994-01-06";
	String address = "255 Los Angeles";
	String city = "California";
	String state = "US";
	String pin = "800000";
	String phone = "0919251925";
	String email = "hannah" + randomNumber() + "@hotmail.com";

	// Input Edit Customer
	String addressEdit = "200 Street Food";
	String cityEdit = "New York";
	String stateEdit = "UK";
	String pinEdit = "900000";
	String phoneEdit = "0955251929";
	String emailEdit = "hannah" + randomNumber() + "@gmail.com";

	// Locate for New/Edit Customer form
	By nameTxtbox = By.name("name");
	By genderRadio = By.xpath("//input[@value='f']");
	By genderTxtbox = By.name("gender");
	By dateOfBirthTxtbox = By.name("dob");
	By addressTexta = By.name("addr");
	By cityTxtbox = By.name("city");
	By stateTxtbox = By.name("state");
	By pinTxtbox = By.name("pinno");
	By phoneTxtbox = By.name("telephoneno");
	By emailTxtbox = By.name("emailid");
	By passwordxtbox = By.name("password");

	// Delare variable
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/V4/");
		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();

		String homepageWelcomeMsg = driver.findElement(By.tagName("marquee")).getText();
		Assert.assertEquals(homepageWelcomeMsg, "Welcome To Manager's Page of Guru99 Bank");

		Assert.assertTrue(
				driver.findElement(By.xpath("//tr[@class='heading3']//td[text()='Manger Id : " + username + "']"))
						.isDisplayed());

	}

	@Test
	public void TC_01_New_Customer() {
		clickElement(newCustormer);
		// Input data to New Customer form
		driver.findElement(nameTxtbox).sendKeys(customerName);
		driver.findElement(genderRadio).click();
		driver.findElement(dateOfBirthTxtbox).sendKeys(dateOfBirth);
		sendKeyToElement(addressTexta, address);
		driver.findElement(cityTxtbox).sendKeys(city);
		driver.findElement(stateTxtbox).sendKeys(state);
		driver.findElement(pinTxtbox).sendKeys(pin);
		driver.findElement(phoneTxtbox).sendKeys(phone);
		driver.findElement(emailTxtbox).sendKeys(email);
		driver.findElement(passwordxtbox).sendKeys(password);
		driver.findElement(By.name("sub")).click();

		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='heading3'and text()='Customer Registered Successfully!!!']"))
						.isDisplayed());

		// Verify output data = input data
		Assert.assertEquals(customerName,
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText());
		Assert.assertEquals(gender,
				driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText());
		Assert.assertEquals(dateOfBirth,
				driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText());
		Assert.assertEquals(address,
				driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());
		Assert.assertEquals(city, driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText());
		Assert.assertEquals(state,
				driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText());
		Assert.assertEquals(pin, driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText());
		Assert.assertEquals(phone,
				driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText());
		Assert.assertEquals(email,
				driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText());

		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println("Customer ID at New Customer form" + customerID);
	}

	@Test
	public void TC_02_Edit_Customer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();

		driver.findElement(By.name("cusid")).sendKeys(customerID);
		System.out.println("Customer ID at Edit Customer form" + customerID);

		driver.findElement(By.name("AccSubmit")).click();
		
		// Verify Name/Gender/DOB is disable fields
		Assert.assertFalse(driver.findElement(nameTxtbox).isEnabled());
		Assert.assertFalse(driver.findElement(genderTxtbox).isEnabled());
		Assert.assertFalse(driver.findElement(dateOfBirthTxtbox).isEnabled());
		
		//Verify output at Edit Customer form = input at New Customer form
		Assert.assertEquals(customerName, driver.findElement(nameTxtbox).getAttribute("value"));
		Assert.assertEquals(gender, driver.findElement(genderTxtbox).getAttribute("value"));
		Assert.assertEquals(dateOfBirth, driver.findElement(dateOfBirthTxtbox).getAttribute("value"));
		Assert.assertEquals(address, driver.findElement(addressTexta).getText());
		Assert.assertEquals(city, driver.findElement(cityTxtbox).getAttribute("value"));
		Assert.assertEquals(state, driver.findElement(stateTxtbox).getAttribute("value"));
		Assert.assertEquals(pin, driver.findElement(pinTxtbox).getAttribute("value"));
		Assert.assertEquals(phone, driver.findElement(phoneTxtbox).getAttribute("value"));
		Assert.assertEquals(email, driver.findElement(emailTxtbox).getAttribute("value"));
		
		//Edit data at Edit Customer form
		driver.findElement(addressTexta).clear();
		driver.findElement(addressTexta).sendKeys(addressEdit);
		driver.findElement(cityTxtbox).clear();
		driver.findElement(cityTxtbox).sendKeys(cityEdit);
		driver.findElement(stateTxtbox).clear();
		driver.findElement(stateTxtbox).sendKeys(stateEdit);
		driver.findElement(pinTxtbox).clear();
		driver.findElement(pinTxtbox).sendKeys(pinEdit);
		driver.findElement(phoneTxtbox).clear();
		driver.findElement(phoneTxtbox).sendKeys(phoneEdit);
		driver.findElement(emailTxtbox).clear();
		driver.findElement(emailTxtbox).sendKeys(emailEdit);
		driver.findElement(By.name("sub")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3'and text()='Customer details updated Successfully!!!']")).isDisplayed());
		
		//Verify output data = input data
		Assert.assertEquals(customerID,
				driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText());
		Assert.assertEquals(customerName,
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText());
		Assert.assertEquals(gender,
				driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText());
		Assert.assertEquals(dateOfBirth,
				driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText());
		Assert.assertEquals(addressEdit,
				driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());
		Assert.assertEquals(cityEdit, driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText());
		Assert.assertEquals(stateEdit,
				driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText());
		Assert.assertEquals(pinEdit, driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText());
		Assert.assertEquals(phoneEdit,
				driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText());
		Assert.assertEquals(emailEdit,
				driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText());

	}

	public void sendKeyToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.sendKeys(value);

	}

	public void clickElement(By by) {
		WebElement element = driver.findElement(by);

		element.click();
	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(100000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
