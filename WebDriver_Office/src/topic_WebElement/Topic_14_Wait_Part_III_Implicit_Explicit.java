package topic_WebElement;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_14_Wait_Part_III_Implicit_Explicit {
	WebDriver driver;
	WebDriverWait waitExplicit;
	String projectPath = System.getProperty("user.dir");
	
	By startButtonBy = By.xpath("//div[@id='start']/button");
	By loadingImageBy = By.xpath("//div[@id='loading']/img");
	By hellowordIdTextBy = By.xpath("//div[@id='finish']/h4");
	

	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\lib2\\chromedriver.exe");
		driver = new ChromeDriver();
		
		waitExplicit = new WebDriverWait(driver, 10);
		
		//Apply để chờ cho element hiển thị -> Tương tác vào
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		//Apply để chờ cho page được load xong
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//Impli + Explicit + Fluent: Flexible
	//- Tìm thấy thì ko cần chờ hết timeout mới step tiếp theo
	// Ko tìm thấy thì cứ lâp lại sau mỗi 0.5s cho đến hết timeout
	
	public void TC_01_Explicit() {
		// Mở app http://the-internet.herokuapp.com/dynamic_loading/2
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		// Chờ cho Start button hiển thị để thao tác
		WebElement startButton = driver.findElement(startButtonBy);
		Assert.assertTrue(startButton.isDisplayed());
		
		// Click vào Start button
		System.out.println("Start click - " + getCurrentTime());
		startButton.click();
		System.out.println("End click - " + getCurrentTime());
		
		//set lại 10s cho implicit wait (3S ko còn ý nghĩa) bị đè
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Helloworld text được hiển thị: Visible (Displayed)
		System.out.println("Start helloword - " + getCurrentTime());
		WebElement helloworldText = driver.findElement(hellowordIdTextBy);
		System.out.println("End helloword - " + getCurrentTime());
		
		System.out.println("Start helloword - " + getCurrentTime());
		Assert.assertTrue(helloworldText.isDisplayed());
		System.out.println("End helloword - " + getCurrentTime());
		
	}
	
	public void TC_02_Explicit_Overide() {
		// Mở app http://the-internet.herokuapp.com/dynamic_loading/2
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		// Chờ cho Start button hiển thị để thao tác
		WebElement startButton = driver.findElement(startButtonBy);
		Assert.assertTrue(startButton.isDisplayed());
		
		// Click vào Start button
		System.out.println("Start click - " + getCurrentTime());
		startButton.click();
		System.out.println("End click - " + getCurrentTime());
		
		//Helloworld text được hiển thị: Visible (Displayed)
		System.out.println("Start helloword - " + getCurrentTime());
		WebElement helloworldText = driver.findElement(hellowordIdTextBy);
		System.out.println("End helloword - " + getCurrentTime());
		
		System.out.println("Start helloword - " + getCurrentTime());
		Assert.assertTrue(helloworldText.isDisplayed());
		System.out.println("End helloword - " + getCurrentTime());
		
	}
	
	public void TC_03_Explicit__Visible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		waitExplicit.until(ExpectedConditions.elementToBeClickable(startButtonBy));
		driver.findElement(startButtonBy).click();
		
		//Wait cho helloword được displayed
		//waitExplicit.until(ExpectedConditions.visibilityOf(driver.findElement(hellowordIdTextBy)));
		System.out.println("Start wait visible - " + getCurrentTime());
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(hellowordIdTextBy));
		System.out.println("End wait visible - " + getCurrentTime());
		
		System.out.println("Start displayed - " + getCurrentTime());
		Assert.assertTrue(driver.findElement(hellowordIdTextBy).isDisplayed());
		System.out.println("End displayed - " + getCurrentTime());
		
		
	}

	public void TC_04_Explicit__InVisible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		waitExplicit.until(ExpectedConditions.elementToBeClickable(startButtonBy));
		driver.findElement(startButtonBy).click();
		
		//Loading icon hiển thị và biến mất sao 5s
		System.out.println("Start wait invisible - " + getCurrentTime());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingImageBy));
		System.out.println("End wait invisible - " + getCurrentTime());
		
		
		//Wait cho helloword được displayed
		//waitExplicit.until(ExpectedConditions.visibilityOf(driver.findElement(hellowordIdTextBy)));
		System.out.println("Start wait visible - " + getCurrentTime());
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(hellowordIdTextBy));
		System.out.println("End wait visible - " + getCurrentTime());
		
		System.out.println("Start displayed - " + getCurrentTime());
		Assert.assertTrue(driver.findElement(hellowordIdTextBy).isDisplayed());
		System.out.println("End displayed - " + getCurrentTime());
		
		
	}

	@Test
	public void TC_05_Date_Time_Picker() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx#");
		
		//In ra ngày được chọn: No Selected Dates to display
		WebElement dateSelectedText = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
		System.out.println("Date selected = " + dateSelectedText.getText());
		Assert.assertEquals(dateSelectedText.getText(), "No Selected Dates to display.");
		
		//Click current day
		driver.findElement(By.xpath("//a[text()='11']")).click();
		
		//Chờ cho loading icon biến mất
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@style, 'position: absolute;')]/div[@class='raDiv']")));
		
		//Check current day = selected
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']/a[(text()='11')]")).isDisplayed());
		
		//Set lại element dateSelectedText
		dateSelectedText = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
		System.out.println("Date selected = " + dateSelectedText.getText());
		Assert.assertEquals(dateSelectedText.getText(), "Friday, September 11, 2020");
		
		
		
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