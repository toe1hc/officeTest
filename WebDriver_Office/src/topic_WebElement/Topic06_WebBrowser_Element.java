package topic_WebElement;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic06_WebBrowser_Element {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_VerifyUrl() {
		System.out.println("Step 01 - Open Url");
		driver.get("http://live.demoguru99.com/index.php/");

		System.out.println("Step 02 - Click My Account link");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		System.out.println("Step 03 - Verify Login Page Url");
		String loginPage = driver.getCurrentUrl();
		Assert.assertEquals(loginPage, "http://live.demoguru99.com/index.php/customer/account/login/");

		System.out.println("Step 04 - Click 'CREAT AN ACCOUNT' button");
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();

		System.out.println("Step 05 - Verify 'Register Page' Page Url");
		String registerPage = driver.getCurrentUrl();
		Assert.assertEquals(registerPage, "http://live.demoguru99.com/index.php/customer/account/create/");

	}

	@Test
	public void TC_02_VerifyTitle() {
		System.out.println("Step 01 - Open Url");
		driver.get("http://live.demoguru99.com/index.php/");

		System.out.println("Step 02 - Click My Account link");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		System.out.println("Step 03 - Verify Login Page Title");
		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle, "Customer Login");

		System.out.println("Step 04 - Click 'CREAT AN ACCOUNT' button");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		System.out.println("Step 05 - Verify 'Register Page' Page Url Title");
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Create New Customer Account");

	}

	@Test
	public void TC_03_NavigateFunction() {
		System.out.println("Step 01 - Open Url");
		driver.get("http://live.demoguru99.com/index.php/");

		System.out.println("Step 02 - Click My Account link");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		System.out.println("Step 03 - Click 'CREAT AN ACCOUNT' button");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		System.out.println("Step 04 - Verify 'Register Page' Page Url");
		String registerPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(registerPageUrl, "http://live.demoguru99.com/index.php/customer/account/create/");

		System.out.println("Step 05 - Back to Login Page");
		driver.navigate().back();

		System.out.println("Step 06 - Verify Login Page Url");
		String loginPage = driver.getCurrentUrl();
		Assert.assertEquals(loginPage, "http://live.demoguru99.com/index.php/customer/account/login/");

		System.out.println("Step 07 - Forward to Register Page");
		driver.navigate().forward();

		System.out.println("Step 08 - Verify 'Register Page' Page Url Title");
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Create New Customer Account");

	}

	@Test
	public void TC_04_GetPageSourceCode() {
		System.out.println("Step 01 - Open Url");
		driver.get("http://live.demoguru99.com/index.php/");

		System.out.println("Step 02 - Click My Account link");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		System.out.println("Step 03 - Verify Login Page contains 'Login or Create an Account' ");
		String loginPageSource = driver.getPageSource();
		Assert.assertTrue(loginPageSource.contains("Login or Create an Account"));

		System.out.println("Step 04 - Click 'CREAT AN ACCOUNT' button");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		System.out.println("Step 05 - Verify Register Page contains 'Create an Account' ");
		String registerPageSource = driver.getPageSource();
		Assert.assertTrue(registerPageSource.contains("Create an Account"));
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
