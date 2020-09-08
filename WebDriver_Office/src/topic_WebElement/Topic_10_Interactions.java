package topic_WebElement;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
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
	JavascriptExecutor javascriptExecutor;
	String projectPath = System.getProperty("user.dir");
	
	String javascriptPath, jqueryPath;
	
	
	@BeforeClass
	public void beforeClass() {
//		FirefoxProfile profile = new FirefoxProfile();
//		profile.setPreference("dom.webnotifications.enable", false);
//		driver = new FirefoxDriver(profile);

		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\lib2\\chromedriver.exe");
		driver = new ChromeDriver();
		
//		System.out.println(driver.toString());
		action = new Actions(driver);
		
		javascriptExecutor = (JavascriptExecutor) driver;
		
		javascriptPath = projectPath + "\\dragAndDrop\\drag_and_drop_helper.js";
		jqueryPath = projectPath + "\\dragAndDrop\\jquery_load_helper.js";
		
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
		
		Assert.assertTrue(isElementDisplayed("//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='Quit']"));
		
		action.click(findXpath("//span[text()='Quit']")).perform();
		
		Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");
		
		Thread.sleep(3000);
		
		driver.switchTo().alert().accept();
		
		Thread.sleep(3000);
	}
	
	public void TC_06_DragAndDrop() throws InterruptedException {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
		
		WebElement sourceCircle = findXpath("//div[@id='draggable']");
		WebElement targetCircle = findXpath("//div[@id='droptarget']");
		
		action.dragAndDrop(sourceCircle, targetCircle).perform();
		Thread.sleep(3000);
		Assert.assertTrue(isElementDisplayed("//div[@id='droptarget' and text()='You did great!']"));

	}
	
	public void TC_06_DragAndDropHTML() throws InterruptedException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		WebElement sourceCircle = findXpath("//div[@id='draggable']");
		WebElement targetCircle = findXpath("//div[@id='droptarget']");
		
		action.dragAndDrop(sourceCircle, targetCircle).perform();
		Thread.sleep(3000);
		
		// action.clickAndHold(sourceCircle).moveToElement(targetCircle).release(targetCircle).perform();
		
		Assert.assertTrue(isElementDisplayed("//div[@id='droptarget' and text()='You did great!']"));

	}
	
	public void TC_07_Drag_And_Drop_HTML5() throws InterruptedException, IOException {
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");

		String sourceCss = "#column-a";
		String targetCss = "#column-b";

		String java_script = readFile(javascriptPath);

		// Inject Jquery lib to site if check Wappalyzer is not available
		// String jqueryscript = readFile(jqueryPath);
		// javascriptExecutor.executeScript(jqueryscript);

		// A to B
		java_script = java_script + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		javascriptExecutor.executeScript(java_script);
		Thread.sleep(3000);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='B']"));

		// B to A
		javascriptExecutor.executeScript(java_script);
		Thread.sleep(3000);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-b']/header[text()='B']"));
	}
	
	@Test
	public void TC_07_DragDrop_HTML5_Offset() throws InterruptedException, IOException, AWTException {
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");
	
		String sourceXpath = "//div[@id='column-a']";
		String targetXpath = "//div[@id='column-b']";
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
	}
	
	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
	
	public WebElement findXpath(String xpathlocator) {
		return driver.findElement(By.xpath(xpathlocator));
			
	}
	
	public boolean isElementDisplayed(String locator) {
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
