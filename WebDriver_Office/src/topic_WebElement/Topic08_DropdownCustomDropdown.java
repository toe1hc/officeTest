package topic_WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.Element;

public class Topic08_DropdownCustomDropdown {
	WebDriver driver;
	// Delare variable
		@BeforeClass
		public void beforeClass() {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
					
		}
		
		@Test
		public void TC_03_multiSelectDropdownList() throws InterruptedException {
			driver.get("https://automationfc.github.io/basic-form/index.html");	
			
			Select jobRole02 = new Select(driver.findElement(By.id("job2"))); 
			boolean jobRole02Status = jobRole02.isMultiple();
			Assert.assertTrue(jobRole02Status);
			jobRole02.selectByVisibleText("Manual");
			Thread.sleep(2000);
			jobRole02.selectByVisibleText("Mobile");
			Thread.sleep(2000);
			jobRole02.selectByVisibleText("Security");
			Thread.sleep(2000);
			jobRole02.selectByVisibleText("Intergration");
			
			List <WebElement> optionSelected = jobRole02.getAllSelectedOptions();
			Assert.assertEquals(optionSelected.size(), 4);
			List <String> arraySlected = new ArrayList<String>();
			for(WebElement select: optionSelected) {
				System.out.println(select.getText());
				arraySlected.add(select.getText());
			}
			
			Assert.assertTrue(arraySlected.contains("Manual"));
			Assert.assertTrue(arraySlected.contains("Mobile"));
			Assert.assertTrue(arraySlected.contains("Security"));
			Assert.assertTrue(arraySlected.contains("Intergration"));
			
			jobRole02.deselectAll();
			Thread.sleep(3000);
			
			List<WebElement> optionUnSelected = jobRole02.getAllSelectedOptions();
			Assert.assertEquals(optionUnSelected.size(), 0);		
		}

		@Test
		public void TC_02_htmlDropdownList() {
			driver.get("https://demo.nopcommerce.com");
			System.out.println("TC 02 - Click Register link");	
			driver.findElement(By.xpath("//a[text()='Register']")).click();
			
			//Input Your Personal Details data
			System.out.println("TC 03 - Input data valid");	
			driver.findElement(By.xpath("//input[@value='M']")).isSelected();;
			driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Sunny");;
			driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("My");;
		
			
			Select selectDay = new Select(driver.findElement(By.name("DateOfBirthDay"))); 
			boolean selectDayStatus = selectDay.isMultiple();
			Assert.assertFalse(selectDayStatus);
			
			selectDay.selectByVisibleText("1");
			Assert.assertEquals(32,selectDay.getOptions().size());
			
			Select selectMonth = new Select(driver.findElement(By.name("DateOfBirthMonth"))); 
			selectMonth.selectByValue("5");
			Assert.assertEquals(selectMonth.getFirstSelectedOption().getText(), "May");
			int selectMonthSize = selectMonth.getOptions().size();
			Assert.assertEquals(selectMonthSize, 13);
			
			Select selectYear = new Select(driver.findElement(By.name("DateOfBirthYear"))); 
			selectYear.selectByValue("1980");
			Assert.assertEquals(112, selectYear.getOptions().size());
			
			driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("mysun22111@gmail.com");
			driver.findElement(By.xpath("//input[@id='Company']")).sendKeys("The Sky1");
			driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("333111");
			driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("333111");;
			
			System.out.println("TC 04 - Click Register button");	
			driver.findElement(By.xpath("//input[@id='register-button']")).click();
			
			System.out.println("TC 05 - Verify Home page");	
			Assert.assertTrue(driver.findElement(By.xpath("//a[text()='My account']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Log out']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Your registration completed']")).isDisplayed());
			
			// Print all value and sort
			List<WebElement> allOptions = selectMonth.getOptions();
			List <String> arrayList = new ArrayList<String>();
			for (WebElement option : allOptions) {
				arrayList.add(option.getText());
			}
			for(String text: arrayList) {
				System.out.println(text);
			}
		}
	
		@Test
		public void TC_03_customDropdownList() {
			driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
			
		
		}
		public void selectItemInCustomDropsown(String parentXpath, String allItemXpath, String expectedValueItem) {
			//Click vào dropdpwn cho xổ hết tất cả giá trị ra
			WebElement parentDropdown = driver.findElement(By.xpath(parentXpath));
			
			
			
		}

		@AfterClass
		public void afterClass() {
			driver.quit();
		}

}
