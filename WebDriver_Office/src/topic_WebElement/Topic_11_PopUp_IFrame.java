package topic_WebElement;

import java.util.List;
import java.util.Set;
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
//		FirefoxOptions options = new FirefoxOptions();
//		options.addArguments("--lang=vi");
//		driver = new FirefoxDriver(options);
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
	
	
	public void TC_05_WindowTab() throws InterruptedException {
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
		
		//GetWindow Handle
		String parentWindow = driver.getWindowHandle();
		System.out.println("Parent windows" + parentWindow);
		//0642d3ed-501a-47e1-8b33-35c3faf29466
		
		System.out.println("Step 04 - Click footer link");
		//Facebook link
		findXpath("//a[@href='https://www.facebook.com/kyna.vn']").click();
		switchToWindowByTitle("Kyna.vn - Home | Facebook");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Home | Facebook");
		Thread.sleep(3000);
		
		driver.switchTo().window(parentWindow);
		//Youtube link 
		
		findXpath("//a[@href='https://www.youtube.com/user/kynavn']").click();
		Thread.sleep(3000);
		switchToWindowByTitle("Kyna.vn - YouTube");	
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - YouTube");
		Thread.sleep(3000);
		
		driver.switchTo().window(parentWindow);
		
		//Zalo link
		findXpath("//a[@href='https://zalo.me/1985686830006307471']").click();
		switchToWindowByTitle("Kyna.vn");	
		Assert.assertEquals(driver.getTitle(), "Kyna.vn");
		Thread.sleep(3000);
		
		Set<String> allWindows = driver.getWindowHandles();
		System.out.println("All windows = "+ allWindows);
		
		System.out.println("Step 06 - Back to parent page and close all child tabs");
		closeAllWindowsWithoutParent(parentWindow);

		
	}
	
	@Test
	public void TC_06_WindowsTab() throws InterruptedException {
		driver.get("http://live.demoguru99.com/index.php/");
		System.out.println("Step 02 - Click Mobile tab ");
		findXpath("//a[text()='Mobile']").click();
		
		System.out.println("Step 03 - Add Sony product and compare ");
		findXpath("//a[text()='Sony Xperia']//parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']").click();	
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")).isDisplayed());
		
		System.out.println("Step 04 - Add Sam Sung product and compare ");
		findXpath("//a[text()='Samsung Galaxy']//parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']").click();	
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
		
		System.out.println("Step 05 - Click to Compare button ");
		findXpath("//button[@title='Compare']").click();
		Thread.sleep(2000);
		
		System.out.println("Step 06 - Switch to 2 new windows ");
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		
		System.out.println("Step 07 - Verify title new window");
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Sony Xperia']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Samsung Galaxy']")).isDisplayed());
		
		System.out.println("Step 08 - Close tab and back to Parent window");
		//closeAllWindowsWithoutParent("Products Comparison List - Magento Commerce");
		findXpath("//button[@title='Close Window']").click();
		Thread.sleep(2000);
		
		switchToWindowByTitle("Mobile");
		
		System.out.println("Step 09 - Click 'Clear All' link and accept alert");
		findXpath("//a[text()='Clear All']").click();
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
			
		System.out.println("Step 10 - Verify message dispayed");
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());
		
		
	}
	//Switch to child Windows (only 2 windows)
	public void switchToWindowByID(String parentID) {
		//Lấy ra tất cả ID đang có
		Set<String> allWindows = driver.getWindowHandles();
		
		//Dùng vòng lặp duyệt qua từng ID (for each)
		for(String runWindow:allWindows) {
			
			//Kiểm tra các ID mà khác parentID thì switch qua
			if(!runWindow.equals(parentID)) {
				
				//Switch qua tab/ window đó
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	//Switch to child Windows (greater than 2 windows and title of pages are unique)
	public void switchToWindowByTitle(String expectedTitle) {
		//Lấy ra tất cả ID đang có
		Set<String> allWindows = driver.getWindowHandles();
		
		//Dùng vòng lặp duyệt qua từng ID (for each)
		for(String runWindow:allWindows) {
			//Switch vào từng ID trước
			driver.switchTo().window(runWindow);
			
			//Get ra title đang có
			String currentWin = driver.getTitle();
			
			//Title nào mà bằng vs title mình expected thì break khỏi vòng lặp
			if(currentWin.equals(expectedTitle)) {
				//thoát khỏi vòng lặp
				break;
			}
		}
	}
	//Close all windows without parent window
	public boolean closeAllWindowsWithoutParent(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		
		for(String runWindow:allWindows) {
			// Nếu ID nào khác thì switch qua 
			if(!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				//Close tab
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
		if(driver.getWindowHandles().size()==1)
			return true;
		else
			return false;
	}
	
	public WebElement findXpath(String xpathlocator) {
		return driver.findElement(By.xpath(xpathlocator));
			
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}