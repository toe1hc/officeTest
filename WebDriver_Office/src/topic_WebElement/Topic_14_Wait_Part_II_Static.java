package topic_WebElement;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Wait_Part_II_Static {
	WebDriver driver;
	

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		//Apply để chờ cho element hiển thị -> Tương tác vào
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Apply để chờ cho page được load xong
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_ElementDisplayedOrVisible() throws InterruptedException {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		//1000 ms = 1s
		//1 - Nếu như page được load xong trong 3s thì sẽ mất 2s lãng phí
		//2 - Nếu như page được load xong trong vòng 10s hao8c5 hơn thì bị thiếu thời gian -> Fail testcase
		// Ko flexible
		//Special case: Internet Explorer
		System.out.println("Start sleep - " + getCurrentTime());
		Thread.sleep(3000);
		System.out.println("End sleep -" + getCurrentTime());
		
		driver.findElement(By.id("search_query_top")).sendKeys("Automation");
		
	}


	public String getCurrentTime() {
		Date date = new Date();
		return date.toString();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}