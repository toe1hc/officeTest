package topic_WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_14_Wait_Part_I_Find_Element {
	WebDriver driver;
	List<WebElement> elements;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}


	public void TC_01_Find_Element() throws InterruptedException {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		//Case 01 - Ko tìm thấy element nào hết 
		//driver.findElement(By.xpath("//input[@id='id_order']")).sendKeys("123456");
		//Nếu như đang còn tìm element mà cgu7a hết timeout - element nó xuât hiện thì vẫn pass
		// Trước khi đánh fail: luôn tìm element theo chu kì là 0.5s tìm lại 1 lần cho đến hết timeout của implicit
		//Kết quả: Failed và throw ra exception: No such element
		
		//Case 02 - Tìm thấy duy nhất 1 element (node)
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("email@gmail.com");
		
		//Case 03 - Tìm thấy duy nhất 1 element (>= 2)
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);
		
	}

	@Test
	public void TC_02_FindElements() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		//Case 01 - Ko tìm thấy element nào hết 
		elements = driver.findElements(By.xpath("//input[@id='id_order']"));
		//Kết quả:ko đánh fail test case mà trả về 1 empty list 
		System.out.println("Size of list = " + elements.size());
		Assert.assertTrue(elements.isEmpty());
		Assert.assertEquals(elements.size(), 0);
		
		//Case 02 - Tìm thấy duy nhất 1 element (node)
		elements = driver.findElements(By.xpath("//input[@id='email']"));
		System.out.println("Size of list = " + elements.size());
		Assert.assertFalse(elements.isEmpty());
		Assert.assertEquals(elements.size(), 1);
		elements.get(0).sendKeys("email1@gmail.com");
		
		//Case 03 - Tìm thấy duy nhất 1 element (>= 2)
		elements = driver.findElements(By.xpath("//button[@type='submit']"));
		System.out.println("Size of list = " + elements.size());
		Assert.assertFalse(elements.isEmpty());
		Assert.assertEquals(elements.size(), 4);
	 
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}