package topic_WebElement;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_JavascriptExecutor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebElement element;
	String projectPath = System.getProperty("user.dir");

	String customerID;
	String username = "mngr265852";
	String password = "ybAqYpu";

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
		// driver = new FirefoxDriver();

		System.setProperty("webdriver.chrome.driver", projectPath + "\\lib2\\chromedriver.exe");
		driver = new ChromeDriver();

		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_JS() throws InterruptedException {
		navigateToUrlByJS("http://live.demoguru99.com/index.php/");

		String liveDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(liveDomain, "live.demoguru99.com");

		String liveUrl = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(liveUrl, "http://live.demoguru99.com/index.php/");

		highlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");

		highlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button");

		String pageInnerText = (String) executeForBrowser("return document.documentElement.innerText");
		Assert.assertTrue(pageInnerText.contains("Samsung Galaxy was added to your shopping cart."));

		clickToElementByJS("//a[text()='Customer Service']");

		String titlePage = (String) executeForBrowser("return document.title");
		Assert.assertTrue(titlePage.contains("Customer Service"));

		scrollToElement("//input[@id='newsletter']");
		Thread.sleep(2000);

		pageInnerText = (String) executeForBrowser("return document.documentElement.innerText");
		Assert.assertTrue(pageInnerText.contains("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));

		navigateToUrlByJS("http://demo.guru99.com/v4/");

		String demoDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(demoDomain, "demo.guru99.com");

	}

	public void TC_02_RemoveAttribute() throws InterruptedException {

		navigateToUrlByJS("http://demo.guru99.com/v4/");

		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();

		String homepageWelcomeMsg = driver.findElement(By.tagName("marquee")).getText();
		Assert.assertEquals(homepageWelcomeMsg, "Welcome To Manager's Page of Guru99 Bank");

		Assert.assertTrue(
				driver.findElement(By.xpath("//tr[@class='heading3']//td[text()='Manger Id : " + username + "']"))
						.isDisplayed());

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		// Input data to New Customer form
		driver.findElement(nameTxtbox).sendKeys(customerName);
		driver.findElement(genderRadio).click();

		// Remove attribute type = 'date' (Date of birth)
		removeAttributeInDOM("//input[@id='dob']", "type");
		Thread.sleep(4000);
		driver.findElement(dateOfBirthTxtbox).sendKeys(dateOfBirth);
		
		driver.findElement(addressTexta).sendKeys(address);
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
	public void TC_03_CreateAnAccount() {
		navigateToUrlByJS("http://live.demoguru99.com/index.php/");
		
		clickToElementByJS("//div[@id='header-account']//a[text()='My Account']");
		
		clickToElementByJS("//span[ text()='Create an Account']");
		
		sendkeyToElementByJS("//input[@id='firstname']", "Thomas");
		sendkeyToElementByJS("//input[@id='lastname']", "Edison");
		sendkeyToElementByJS("//input[@id='email_address']", "thomasedison" + randomNumber() + "@gmail.com");
		sendkeyToElementByJS("//input[@id='password']", "thomas2211");
		sendkeyToElementByJS("//input[@id='confirmation']", "thomas2211");
		
		clickToElementByJS("//button[@title='Register']");
		
		Assert.assertTrue(verifyTextInInnerText("Thank you for registering with Main Website Store."));
		
//		String innerText = (String) executeForBrowser("return document.documentElement.innerText");
//		Assert.assertTrue(innerText.contains("Thank you for registering with Main Website Store."));
		
		//Trick (Invisible)
		clickToElementByJS("//div[@id='header-account']//a[text()='Log Out']");
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']")).isDisplayed());
		
	}

	public Object executeForBrowser(String javaSript) {
		return jsExecutor.executeScript(javaSript);
	}

	public boolean verifyTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(String locator) {
		element = driver.findElement(By.xpath(locator));

		// Lưu trữ giá trị cũ trước khi set
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Ko bị ảnh hưởng tới element khác
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);

	}

	public void clickToElementByJS(String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement(String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void sendkeyToElementByJS(String locator, String value) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
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