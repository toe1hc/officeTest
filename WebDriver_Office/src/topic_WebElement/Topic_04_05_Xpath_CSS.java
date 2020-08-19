/*
 * Hannah's 
 */
package topic_WebElement;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_05_Xpath_CSS {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.guru99.com/");
	}

	@Test
	public void TC_01_LoginWithEmtyEmailAndPassword() {
		/***
		 * Step 01: Truy cập vào trang http://live.guru99.com/ Step 02: Click vào link
		 * "My Account" để tới trang đăng nhâp Step 03: Để trống Username/ Password Step
		 * 04: Click Login button Step 05: Verify error message xuất hiện tại 2 field:
		 * "This is a required field."
		 */
		driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		driver.findElement(By.name("send")).click();
		String errorMsgEmail = driver.findElement(By.xpath(".//*[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(errorMsgEmail, "This is a required field.");
		String errorMsgPassword = driver.findElement(By.xpath(".//*[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(errorMsgPassword, "This is a required field.");

	}

	@Test
	public void TC_02_LoginWithInvalidEmail() {
		/***
		 * Step 01: Truy cập vào trang http://live.guru99.com/ Step 02: Click vào link
		 * "My Account" để tới trang đăng nhâp Step 03: Nhập email invalid:
		 * "123434234@12312.123123" Step 04: Click Login button Step 05: Verify error
		 * message xuất hiện tại 2 field "Please enter a valid email address. For
		 * example johndoe@domain.com."
		 */

		driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("123434234@12312.123123");
		driver.findElement(By.name("send")).click();
		String errorMsgEmail = driver.findElement(By.xpath(".//*[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(errorMsgEmail, "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03_LoginWithPasswordLessThan6Characters() {
		/***
		 * Step 01: Truy cập vào trang http://live.guru99.com/ Step 02: Click vào link
		 * "My Account" để tới trang đăng nhâp Step 03: Nhập email correct and password
		 * incorrect: "automation@gmail.com", "123" Step 04: Click Login button Step 05:
		 * Verify error message xuất hiện tại 2 field: "Please enter 6 or more
		 * characters without leading or trailing spaces."
		 */

		driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("123");
		driver.findElement(By.name("send")).click();
		String errorMsgPwLessThan6Char = driver.findElement(By.xpath(".//*[@id='advice-validate-password-pass']"))
				.getText();
		Assert.assertEquals(errorMsgPwLessThan6Char,
				"Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_04_LoginWithIncorrectPassword() {
		/***
		 * Step 01: Truy cập vào trang http://live.guru99.com/ Step 02: Click vào link
		 * "My Account" để tới trang đăng nhâp Step 03: Nhập email correct and password
		 * incorrect: "automation@gmail.com", "123123123" Step 04: Click Login button
		 * Step 05: Verify error msg xuất hiện: "Invalid login or password."
		 */
		driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("123123123");
		driver.findElement(By.name("send")).click();
		String errorMsgIncorrectPass = driver.findElement(By.cssSelector(".error-msg>ul>li>span")).getText();
		Assert.assertEquals(errorMsgIncorrectPass, "Invalid login or password.");
	}

	@Test
	public void TC_05_LoginWithValidEmailAndPassword() {

		/***
		 * Step 01: Truy cập vào trang http://live.guru99.com/ Step 02: Click vào link
		 * "My Account" để tới trang đăng nhâp Step 03: Nhập email correct and password
		 * correct: "automation@gmail.com", "123123" Step 04: Click Login button Step
		 * 05: Verify các thông tin sau: my account, account dashboard, my applications
		 * newsletter subscription
		 */

		driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("automation_13@gmail.com");
		driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("123123");
		driver.findElement(By.name("send")).click();
		String msgDashBoard = driver.findElement(By.cssSelector(".page-title>h1")).getText();
		Assert.assertEquals(msgDashBoard, "MY DASHBOARD");

		String msgHello = driver.findElement(By.cssSelector(".hello>strong")).getText();
		Assert.assertEquals(msgHello, "Hello, Automation Testing!");

		driver.findElement(By.xpath("//div[@class='box-content']/p[contains(.,'Automation Testing')]")).isDisplayed();
		driver.findElement(By.xpath("//div[@class='box-content']/p[contains(.,'automation_13@gmail.com')]"))
				.isDisplayed();

		System.out.println("Login successfully");

	}

	@Test
	public void TC_06_CreateANewAccount() {
		/**
		 * Step 01: Truy cập vào trang http://live.demoguru99.com/ 
		 * Step 02: Click vào link "My Account" để tới trang đăng nhâp
		 * Step 03: Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản 
		 * Step 04: Nhập thông tin hợp lệ vào tất cả các field: First Name/Last Name/ Email Address/
		 *  Password/Confirm Password
		 * (Lưu ý: Tạo random cho dữ liệu tại field Email Address) 
		 * Step 05: Click REGISTER button 
		 * Step 06: Verify message xuất hiện khi đăng kí thánh công:
		 * "Thank you for registering with Main Website Store. 
		 * Step 07: Logout khỏi hệ thống 
		 * Step 08: Kiểm tra hệ thống navigate về Home page sau khi logout thành công
		 */

		driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		driver.findElement(By.xpath(".//*[@id='login-form']/div/div[1]/div[2]/a")).click();
		driver.findElement(By.xpath(".//*[@id='firstname']")).sendKeys("Hannah");
		driver.findElement(By.xpath(".//*[@id='lastname']")).sendKeys("To");
		String email = null;
		email = getSaltString() + "@gmail.com";
		driver.findElement(By.xpath(".//*[@id='email_address']")).sendKeys(email);
		driver.findElement(By.xpath(".//*[@id='password']")).sendKeys("haanahkawai");
		driver.findElement(By.xpath(".//*[@id='confirmation']")).sendKeys("haanahkawai");

		driver.findElement(By.xpath(".//*[@id='form-validate']/div[2]/button")).click();
		String msgRegister = driver.findElement(By.cssSelector(".success-msg>ul>li>span")).getText();
		Assert.assertEquals(msgRegister, "Thank you for registering with Main Website Store.");

		driver.findElement(By.xpath(".//*[@id='header']/div/div[2]/div/a/span[2]")).click();
		driver.findElement(By.xpath(".//*[@id='header-account']/div/ul/li[5]/a")).click();
		String msgHomePage = driver.findElement(By.className("welcome-msg")).getText();
		Assert.assertEquals(msgHomePage, "DEFAULT WELCOME MSG!");

		System.out.println("Create a new account successfully");

	}
/**
 * 
 * Random Email function
 */
	protected String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}