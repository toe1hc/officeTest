package topic_WebElement;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;


public class Topic_14_Wait_Part_V_Fluent_Wait {
	WebDriver driver;
	FluentWait <WebDriver> fluentDriver;
	FluentWait <WebElement> fluentElement;
	WebElement element;
	long timeout = 5000;
	long interval = 500;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		fluentDriver = new FluentWait<WebDriver>(driver);
		//Apply để chờ cho element hiển thị -> Tương tác vào
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Apply để chờ cho page được load xong
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_Fluent_WebDriver() {
		driver.get("http://demo.guru99.com/");
		
		//Tổng thời gian cần check
		fluentDriver.withTimeout(15, TimeUnit.SECONDS)
		//tần số mỗi giây check 1 lần
		.pollingEvery(200, TimeUnit.MILLISECONDS)
		//nếu gặp ngoại lệ thì bỏ qua
		.ignoring(NoSuchElementException.class);
		
		WebElement submitButton = (WebElement) fluentDriver.until(new Function<WebDriver, WebElement>(){
		@Override
			public WebElement apply(WebDriver driver) {
				System.out.println(new Date());
				return driver.findElement(By.xpath("//input[@name='Login']"));
			}
		});
		
		submitButton.click();
	}

	
	public void TC_02_Fluent_WebElement() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		WebElement countdown = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		fluentElement = new FluentWait<WebElement>(countdown);
		
		//Tổng thời gian cần check
		fluentElement.withTimeout(15, TimeUnit.SECONDS)
		//tần số mỗi giây check 1 lần
		.pollingEvery(1, TimeUnit.SECONDS)
		//nếu gặp ngoại lệ thì bỏ qua
		.ignoring(NoSuchElementException.class)
		//kiểm tra điều kiện
		.until(new Function<WebElement, Boolean>(){
			
			public Boolean apply(WebElement element) {
			//Kiểm tra điều kiện countdown = 00
			boolean flag = element.getText().endsWith("05");
			System.out.println("Time = " + element.getText());
			//return giá trị cho function appy
			return flag;
			
		}
		
		});
			
	}

	@Test
	public void TC_03_Fluent_WebElement() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
//		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
//		
//		WebElement hellowordTxt = driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]"));
//		fluentElement = new FluentWait<WebElement>(hellowordTxt);
//		
//		//Tổng thời gian cần check
//		fluentElement.withTimeout(15, TimeUnit.SECONDS)
//		//tần số mỗi giây check 1 lần
//		.pollingEvery(1, TimeUnit.SECONDS)
//		//nếu gặp ngoại lệ thì bỏ qua
//		.ignoring(NoSuchElementException.class)
//		//kiểm tra điều kiện
//		.until(new Function<WebElement, Boolean>(){
//			
//			public Boolean apply(WebElement element) {
//			//Kiểm tra điều kiện countdown = 00
//			boolean flag = element.isDisplayed();
//			System.out.println("Text = " + element.getText());
//			//return giá trị cho function appy
//			return flag;
//			
//		}
//		
//		});
//		
		waitForElementAndClick(By.xpath("//button[contains(text(),'Start')]"));
		waitForElementAndDisplayed(By.xpath("//div[@id='finish']/h4[contains(text(),'Hello World!')]"));
		
	}
	
	public WebElement waitedElement(By locator) {

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(timeout, TimeUnit.MILLISECONDS)
				.pollingEvery(interval, TimeUnit.MICROSECONDS)
				.ignoring(NoSuchElementException.class);
		
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return element;	
	}
	
	public void waitForElementAndClick(By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(timeout, TimeUnit.MILLISECONDS)
				.pollingEvery(interval, TimeUnit.MICROSECONDS)
				.ignoring(NoSuchElementException.class);
		
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		element.click();;	
	}
	
	public Boolean waitForElementAndDisplayed(By locator) {
		 element = waitedElement(locator);
		FluentWait<WebElement> wait = new FluentWait<WebElement>(element)
				.withTimeout(timeout, TimeUnit.MILLISECONDS)
				.pollingEvery(interval, TimeUnit.MICROSECONDS)
				.ignoring(NoSuchElementException.class);
		
		boolean isDisplayed = wait.until(new Function<WebElement, Boolean>(){
			public Boolean apply(WebElement element){
				boolean flag = element.isDisplayed();
				return flag;
			}
		});
		return isDisplayed;
}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}