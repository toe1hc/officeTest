package topic_WebElement;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_UploadFile {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	String tulipsPath = projectPath + "\\uploadFiles\\Tulips.jpg";
	String jellyPath = projectPath + "\\uploadFiles\\Jellyfish.jpg";
	String koalaPath = projectPath + "\\uploadFiles\\Koala.jpg";
	String lighthousePath = projectPath + "\\uploadFiles\\Lighthouse.jpg";
	
	String chromeAutoIT = projectPath + "\\uploadAutoIT\\chromeUploadOneTime.exe";
	String firefoxAutoIT = projectPath + "\\uploadAutoIT\\firefoxUploadOneTime.exe";
	
	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\lib2\\geckodriver.exe");
//		driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\lib2\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	
	public void TC_01_SendKeys() throws InterruptedException {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
			
		WebElement uploadFile = findXpath("//input[@type='file']");
		
		//Firefox >= 52 version
		uploadFile.sendKeys(tulipsPath + "\n" + tulipsPath + "\n" + jellyPath + "\n" + koalaPath + "\n" + lighthousePath);
		Thread.sleep(5000);	
		
		List <WebElement> startButtons = findXpaths("//table//button[@class='btn btn-primary start']");
		
		for(WebElement start: startButtons) {
			start.click();		
			Thread.sleep(2000);	
		}
		Assert.assertTrue(findXpath("//p[@class='name']//a[@title='Tulips.jpg']").isDisplayed());
		Assert.assertTrue(findXpath("//p[@class='name']//a[@title='Jellyfish.jpg']").isDisplayed());
		Assert.assertTrue(findXpath("//p[@class='name']//a[@title='Koala.jpg']").isDisplayed());
		Assert.assertTrue(findXpath("//p[@class='name']//a[@title='Lighthouse.jpg']").isDisplayed());
				
	}
	

	public void TC_02_AutoIT() throws InterruptedException, IOException {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
			
		WebElement uploadFile = driver.findElement(By.cssSelector(".fileinput-button"));
		uploadFile.click();
		Thread.sleep(2000);
		
		//Execute runtime file (.exe/ .bat /.msi  .sh/ .jar)
		if(driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] {chromeAutoIT, tulipsPath});
		}else {
			Runtime.getRuntime().exec(new String[] {firefoxAutoIT, tulipsPath});
		
		}
			
		findXpath("//table//button[@class='btn btn-primary start']").click();
	
		Assert.assertTrue(findXpath("//p[@class='name']//a[@title='Tulips.jpg']").isDisplayed());			
	}

	@Test
	public void TC_03_Robot() throws InterruptedException, AWTException {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		
		WebElement uploadFile = driver.findElement(By.cssSelector(".fileinput-button"));
		uploadFile.click();
		Thread.sleep(2000);
		
		StringSelection select = new StringSelection(lighthousePath);
		
		//Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		
		Robot robot = new Robot();
		Thread.sleep(1000);
		
		//Nhan phim ENTER
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		//Nhan phim CTRL + V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
				
		//Nha phim CTRL + V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
	
		//Nhan phim ENTER
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		Thread.sleep(2000);
		
		
	}
	
	
	public WebElement findXpath(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}
	
	public List <WebElement> findXpaths(String xpath) {
		return driver.findElements(By.xpath(xpath));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}