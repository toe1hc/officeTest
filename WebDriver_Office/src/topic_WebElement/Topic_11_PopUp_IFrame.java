package topic_WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
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
	public void TC_02_Fixed_Pop_Up() throws InterruptedException {
		driver.get("https://bni.vn/");	
		
		boolean loginpopUp = findXpath("//div[@id='sg-popup-content-wrapper-1236']").isDisplayed();
		
		System.out.println("Login pop up displayed = " + loginpopUp);
		Assert.assertTrue(loginpopUp);
		
		findXpath("//img[@class='sgpb-popup-close-button-1']").click();
	
			
	}
	
	@Test
	public void TC_04_Iframe() throws InterruptedException{
		driver.get("https://kyna.vn/");
		
		Thread.sleep(5000);
		System.out.println("Step 02 - Count popup number");
		List <WebElement> fancyPopup = driver.findElements(By.xpath("//div[@class='fancybox-inner']"));
		System.out.println("Fancy popup number = " + fancyPopup.size());
		
		if(fancyPopup.size() > 0)
		{
			System.out.println("Step 03 - Check popup displayed and close it ");
			Assert.assertTrue(fancyPopup.get(0).isDisplayed());
			driver.findElement(By.cssSelector(".fancybox-close")).click();
		}
		
		
		System.out.println("Step 04 - Switch Facebook iframe");
		//driver.switchTo().frame("//div[@class='fanpage']//iframe");
		driver.switchTo().frame(findXpath("//iframe[contains(@src,'www.facebook.com/kyna.vn')]"));
		
		boolean facebookIframe = driver.findElement(By.cssSelector("#facebook")).isDisplayed();
		System.out.println("Facebook iframe dispalye = " + facebookIframe);
		Assert.assertTrue(facebookIframe);
		
		String facebookLike = findXpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div").getText();
		System.out.println("Facebook likes number = " + facebookLike);
		Assert.assertEquals(facebookLike, "169K likes");
		
		//Switch back Main page
		driver.switchTo().defaultContent();
		
		System.out.println("Step 05 - Switch Webchat iframe");	
		boolean chatBox = findXpath("//div[@id='cs-live-chat']").isDisplayed();
		System.out.println("Chatbox iframe displayed = " + chatBox);
		Assert.assertTrue(chatBox);
		
		//Direct to Chat box
		driver.switchTo().frame("cs_chat_iframe");			
		//findXpath("//div[@class='border_overlay meshim_widget_widgets_BorderOverlay']").click();
		
		System.out.println("Step 06 - Send key to web chat and verify form display");
		WebElement textChat = findXpath("//div[@ng-show='loggedinFirstTime']/textarea");
		textChat.sendKeys("hello");
		Thread.sleep(2000);
		textChat.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
		boolean saveBtn = findXpath("//input[@class='save short wide meshim_widget_widgets_ConnAwareSubmit desktop']").isDisplayed();
		Assert.assertTrue(saveBtn);
		
		//Switch back Main page
		driver.switchTo().defaultContent();
		
		System.out.println(" Step 07 Send key Java and click Search icon");
		findXpath("//input[@id='live-search-bar']").sendKeys("Java");
		findXpath("//button[@class='search-button']").click();
		
		System.out.println(" Step 08 Verify cources list page contains 'Java' key");
		String resultText = findXpath("//span[@class='menu-heading']//h1").getText();
		Assert.assertEquals(resultText, "'Java'");
			
	}
	
	public WebElement findXpath(String xpathlocator) {
		return driver.findElement(By.xpath(xpathlocator));
			
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}