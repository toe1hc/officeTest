package topic_WebElement;
import java.util.List;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic10_Interactions {
	WebDriver driver;
	Actions action;
	
	@BeforeClass
	public void beforeClass() {
//		FirefoxProfile profile = new FirefoxProfile();
//		profile.setPreference("dom.webnotifications.enable", false);
//		driver = new FirefoxDriver(profile);

		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\lib2\\chromedriver.exe");
		driver = new ChromeDriver();
		
		System.out.println(driver.toString());
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC01_Hover_To_Element() {	
		driver.get("http://www.myntra.com/");
		
//		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()= 'Men']"))).perform();
//		
//		driver.findElement(By.xpath("//a[@class='desktop-categoryLink' and text()='Boxers']")).click();
//		
//		Assert.assertTrue(driver.findElement(By.xpath("//h1[@class='title-title' and text()='Boxers For Men']")).isDisplayed());
		
		
		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()= 'Kids']"))).perform();
		
		driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[@class='title-title' and text()='Kids Home Bath']")).isDisplayed());
		
	}
	
	public void TC02_Click_And_Hold() {	
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List<WebElement> numbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		int numbersSize = numbers.size();
		System.out.println("Size before click/hold = " + numbersSize);
		
		action.clickAndHold(numbers.get(0)).moveToElement(numbers.get(3)).release().perform();
		
		List<WebElement> selectedNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class, 'ui-selected')]"));
		System.out.println("Size after click/hold = " + selectedNumber.size());
		
		Assert.assertEquals(selectedNumber.size(), 4);

	}
	
	public void TC03_Click_And_Hold_Random() throws InterruptedException {	
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List<WebElement> numbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		int numbersSize = numbers.size();
		System.out.println("Size before click/hold = " + numbersSize);
		
		// click 1 -3 - 6 -11
		action.keyDown(Keys.CONTROL).perform();
		numbers.get(0).click();
		numbers.get(2).click();
		numbers.get(5).click();
		numbers.get(10).click();
		action.keyUp(Keys.CONTROL).perform();
		
		List<WebElement> selectedNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class, 'ui-selected')]"));
		System.out.println("Size after click/hold = " + selectedNumber.size());
		
		Assert.assertEquals(selectedNumber.size(), 4);
		
		for(WebElement number:selectedNumber) {
			System.out.println(number.getText());
		}
		
		Thread.sleep(2000);;

	}
	
	public void TC_04_DoubleClick() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		Thread.sleep(3000);
		String actualText = driver.findElement(By.xpath("//p[@id='demo']")).getText();
		Assert.assertEquals(actualText, "Hello Automation Guys!");
	}
	
	public void TC_05_Right_Click() throws InterruptedException {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		action.contextClick(findXpath("//span[text()='right click me']")).perform();
		Thread.sleep(3000);
		
		action.moveToElement(findXpath("//span[text()='Quit']")).perform();
		Thread.sleep(3000);
		
		Assert.assertTrue(isElementDispalyed("//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='Quit']"));
		
		action.click(findXpath("//span[text()='Quit']")).perform();
		
		Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");
		
		Thread.sleep(3000);
		
		driver.switchTo().alert().accept();
		
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_06_DragAndDrop() throws InterruptedException {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
		
		WebElement sourceCircle = findXpath("//div[@id='draggable']");
		WebElement targetCircle = findXpath("//div[@id='droptarget']");
		
		action.dragAndDrop(sourceCircle, targetCircle).perform();
		Thread.sleep(3000);
		Assert.assertTrue(isElementDispalyed("//div[@id='droptarget' and text()='You did great!']"));

	}
	
	@Test
	public void TC_06_DragAndDropHTML() throws InterruptedException {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
		
		WebElement sourceCircle = findXpath("//div[@id='draggable']");
		WebElement targetCircle = findXpath("//div[@id='droptarget']");
		
		action.dragAndDrop(sourceCircle, targetCircle).perform();
		Thread.sleep(3000);
		
		// action.clickAndHold(sourceCircle).moveToElement(targetCircle).release(targetCircle).perform();
		
		Assert.assertTrue(isElementDispalyed("//div[@id='droptarget' and text()='You did great!']"));

	}
	
	public WebElement findXpath(String xpathlocator) {
		return driver.findElement(By.xpath(xpathlocator));
			
	}
	
	public boolean isElementDispalyed(String locator) {
		if(findXpath(locator).isDisplayed())
		return true;
		else
		return false;
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}


}
