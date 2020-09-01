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

public class Topic_11_PopUp_IFrame {
	WebDriver driver;
// Delare variable
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_Fixed_Pop_Up() throws InterruptedException {
		driver.get("https://www.zingpoll.com/");
		findXpath("//a[@id='Loginform']").click();
		
		boolean loginpopUp = findXpath("//div[@id='Login']//div[@class='modal-content']").isDisplayed();
		
		System.out.println("Login pop up displayed = " + loginpopUp);
		Assert.assertTrue(loginpopUp);
		
		findXpath("button[@onclick='ResetForm()']").click();
		
		
	}

	@Test
	public void TC_02_Fixed_Pop_Up() throws InterruptedException {
		driver.get("https://bni.vn/");	
		
		boolean loginpopUp = findXpath("//div[@id='sg-popup-content-wrapper-1236']").isDisplayed();
		
		System.out.println("Login pop up displayed = " + loginpopUp);
		Assert.assertTrue(loginpopUp);
		
		findXpath("//img[@class='sgpb-popup-close-button-1']").click();
	
			
	}


	
	public WebElement findXpath(String xpathlocator) {
		return driver.findElement(By.xpath(xpathlocator));
			
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}