package topic_WebElement;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_08_DropdownCustomDropdown {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor javascript;
	Actions action;
	Select select;

//	By numberAllItems = By.xpath("//ul[@id ='number-menu']/li");
	// Delare variable
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 10);
		javascript = (JavascriptExecutor) driver;
		action = new Actions(driver);
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

		List<WebElement> optionSelected = jobRole02.getAllSelectedOptions();
		Assert.assertEquals(optionSelected.size(), 4);
		List<String> arraySlected = new ArrayList<String>();
		for (WebElement select : optionSelected) {
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

		// Input Your Personal Details data
		System.out.println("TC 03 - Input data valid");
		driver.findElement(By.xpath("//input[@value='M']")).isSelected();
		;
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Sunny");
		;
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("My");
		;

		Select selectDay = new Select(driver.findElement(By.name("DateOfBirthDay")));
		boolean selectDayStatus = selectDay.isMultiple();
		Assert.assertFalse(selectDayStatus);

		selectDay.selectByVisibleText("1");
		Assert.assertEquals(32, selectDay.getOptions().size());

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
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("333111");
		;

		System.out.println("TC 04 - Click Register button");
		driver.findElement(By.xpath("//input[@id='register-button']")).click();

		System.out.println("TC 05 - Verify Home page");
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='My account']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Log out']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Your registration completed']")).isDisplayed());

		// Print all value and sort
		List<WebElement> allOptions = selectMonth.getOptions();
		List<String> arrayList = new ArrayList<String>();
		for (WebElement option : allOptions) {
			arrayList.add(option.getText());
		}
		for (String text : arrayList) {
			System.out.println(text);
		}
	}

	@Test
	public void TC_04_JQuery() throws InterruptedException {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		// 5 - Click vào 5 và Kiểm tra item đã được chọn thành công
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id ='number-menu']/li", "5");
		Assert.assertTrue(
				isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']"));
		Thread.sleep(2000);

		// 5 - Click vào 19 và Kiểm tra item đã được chọn thành công
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id ='number-menu']/li", "19");
		Assert.assertTrue(
				isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='19']"));
		Thread.sleep(2000);
	}

	@Test
	public void TC_05_Angular() throws InterruptedException {
		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");

		// 5 - Click vào Football và Kiểm tra item đã được chọn thành công
		selectItemInCustomDropdown("//ejs-dropdownlist[@id='games']", "//ul[@id ='games_options']/li", "Football");
		Thread.sleep(2000);

		// Kiểm tra Football được chọn thành công
		String expectedValue = getTextJS("#games_hidden > option");
		System.out.println("Text = " + expectedValue);
		Assert.assertEquals(expectedValue, "Football");

	}

	@Test
	public void TC_06_React() throws InterruptedException {
		driver.get("https://react.semantic-ui.com/modules/dropdown/");

		// 5 - Click vào Elliot và Kiểm tra item đã được chọn thành công
		selectItemInCustomDropdown(
				"//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']/i",
				"//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']//div[@role='option']/span",
				"Elliot Fu");
		Thread.sleep(2000);

		// Kiểm tra Matt Fu được chọn thành công
		Assert.assertTrue(isElementDisplayed(
				"//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']/div[@class='divider text' and text()='Elliot Fu']"));
		
		// 5 - Click vào Matt và Kiểm tra item đã được chọn thành công
				selectItemInCustomDropdown(
						"//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']/i",
						"//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']//div[@role='option']/span",
						"Matt");
				Thread.sleep(2000);

		// Kiểm tra Elliot Fu được chọn thành công
				Assert.assertTrue(isElementDisplayed(
						"//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']/div[@class='divider text' and text()='Matt']"));
	}

	@Test
	public void TC_07_Editable() throws InterruptedException {
		driver.get("https://react.semantic-ui.com/modules/dropdown/");

		// 5 - Click vào Elliot và Kiểm tra item đã được chọn thành công
		inputItemInCustomDropdown("//div[contains(@class,'search selection')]//i[@class='dropdown icon']","//input[@class='search']", "American Samoa");
		Thread.sleep(2000);

		// Kiểm tra Matt Fu được chọn thành công
		Assert.assertTrue(isElementDisplayed(
				"//div[contains(@class,'search selection')]/div[@class='divider text' and text()='American Samoa']"));
		Thread.sleep(2000);
		
		// 5 - Click vào Elliot và Kiểm tra item đã được chọn thành công
		inputItemInCustomDropdown("//div[contains(@class,'search selection')]//i[@class='dropdown icon']","//input[@class='search']", "Angola");
		Thread.sleep(2000);

		// Kiểm tra Matt Fu được chọn thành công
		Assert.assertTrue(isElementDisplayed(
						"//div[contains(@class,'search selection')]/div[@class='divider text' and text()='Angola']"));
		Thread.sleep(2000);
							
	}
	
	@Test
	public void TC_08_MultipleSelect() throws InterruptedException {
		driver.get("http://multiple-select.wenzhixin.net.cn/examples#basic.html");
		driver.switchTo().frame(0);
		String[] months = {"January","February","March","June","December"};
		// 5 - Click vào Elliot và Kiểm tra item đã được chọn thành công
		selectMultipleInDropdown(".//*[@id='example']/div/div[2]/div/div/button", ".//*[@id='example']/div/div[2]/div/div/div/ul/li", months);		
		Thread.sleep(2000);

		// Kiểm tra Matt Fu được chọn thành công
		checkItemSelected(months);
		Thread.sleep(2000);
			
	}
	
	public void selectItemInCustomDropdown(String parentXpath, String allItemXpath, String expectedValueItem) {
		// 1 - Click vào thẻ chứa dropspwn để nó xổ ra hết tất cả item
		driver.findElement(By.xpath(parentXpath)).click();

		// 2 - Khai báo 1 List WebElement chứa all các items bên trong
		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));

		// 3 - Wail cho tất cả item (List WebElement) được xuất hiện
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));

		System.out.println("Item size = " + allItems.size());

		// 4 - Get text từng item sau đó so sánh vs item mình cần chọn
		for (WebElement item : allItems) {
			String actualItem = item.getText();
			System.out.println("Text = " + actualItem);

			if (actualItem.equals(expectedValueItem)) {
				item.click();
				break;
			}
		}

	}
	public void inputItemInCustomDropdown(String parentXpath, String inputXpath, String expectedText) {
		// 1 - Click vào thẻ chứa dropspwn để nó xổ ra hết tất cả item
		driver.findElement(By.xpath(parentXpath)).click();

		// 2 - Input text vào textbox
		driver.findElement(By.xpath(inputXpath)).sendKeys(expectedText);
		
		//3 - Truyền phím ENTER vào Input text
		action.sendKeys(driver.findElement(By.xpath(inputXpath)),Keys.ENTER).perform();


	}
	
	public void selectMultipleInDropdown(String parentXpath, String allItemXpath, String[] expectedValueItem) throws InterruptedException {
		// 1 - Click vào thẻ chứa dropspwn để nó xổ ra hết tất cả item
		driver.findElement(By.xpath(parentXpath)).click();

		//2 - Chờ cho tất cả giá trị trong drop down load ra thành công
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		
		// 3 - Khai báo 1 List WebElement chứa all các items bên trong
		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
		System.out.println("Tat ca cac phan tu trong dropdown = " + allItems.size());
		
		//Duyệt qua hết tất cả các phần tử cho đến khi thỏa mãn điều kiện
		for(WebElement childElement:allItems) {
			//"January", "February, "March"
			for(String item:expectedValueItem) {
				if(childElement.getText().equals(item)) {
					childElement.click();
					List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
					System.out.println("Item selected=" + itemSelected.size());
					if(expectedValueItem.length == itemSelected.size()) {
						break;
					}
				}
			}
		}
			
			
		
	}
	
	public boolean checkItemSelected(String[]itemSelectedText) {
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		int numberItemSelected = itemSelected.size();
		
		String allItemSelectedText = driver.findElement(By.xpath("//button[@class='ms-choice']/span")).getText();
		System.out.println("Text da chon=" + allItemSelectedText);
		
		if(numberItemSelected <=3 && numberItemSelected > 0) {
			for(String item : itemSelectedText) {
				if(allItemSelectedText.contains(item)) {
					break;
				}
				
			}
			return true;
		}else {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='"+ numberItemSelected + "of 12 selected']")).isDisplayed();
		}
		
	}

	public boolean isElementDisplayed(String xpathLocator) {
		WebElement element = driver.findElement(By.xpath(xpathLocator));
		if (element.isDisplayed()) {
			// System.out.println("Element [" + xpathLocator + "] is displayed");
			return true;
		} else {
			// System.out.println("Element [" + xpathLocator + "] is undisplayed");
			return false;
		}
	}

	public String getTextElement(String xpathLocator) {
		WebElement element = driver.findElement(By.xpath(xpathLocator));
		return element.getText();
	}

	public String getTextJS(String locator) {
		return (String) javascript.executeScript("return document.querySelector('" + locator + "').text");

	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
