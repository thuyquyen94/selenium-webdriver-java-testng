package webdriver;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait epliciWait;
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	 @Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		// ParentCss là muốn chọn item cho speed dropdown
	selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slower");
	sleepInSecond(2);
	Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slower");
	 
	 selectItemInDropdown("span#speed-button","ul#speed-menu div[role='option']", "Faster");
	 sleepInSecond(2);
	 Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Faster");
	 
	 selectItemInDropdown("span#speed-button","ul#speed-menu div[role='option']", "Slow");
	 sleepInSecond(2);
	 Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slow");
	 
	 selectItemInDropdown("span#salutation-button","ul#salutation-menu div[role='option']", "Prof.");
	 sleepInSecond(2);
	 Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Prof.");
	}	
	
	 @Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemInDropdown("i.dropdown.icon", "span.text", "Jenny Hess");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");
		
		selectItemInDropdown("i.dropdown.icon", "span.text", "Elliot Fu");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Elliot Fu");

		selectItemInDropdown("i.dropdown.icon", "span.text", "Stevie Feliciano");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Stevie Feliciano");

	}

	 @Test
	public void TC_03_VueJS() {
         driver.get("https://mikerodham.github.io/vue-dropdowns/");
         		
		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
		
		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
		
		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Frist Option");
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.cssSelector("ul.dropdown-menu a")).getText(), "First Option");
		
	}
	
	@Test
	public void TC_04_Editable() {
         driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
         
         enterAndselectItemInDropdown("input.search","span.text", "Afghanistan");
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Afghanistan");
		
		enterAndselectItemInDropdown("input.search","span.text", "Belgium");
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Belgium");
		
		enterAndselectItemInDropdown("input.search","span.text", "Australia");
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Australia");
	
	}
	
	public void selectItemInDropdown(String parentCss, String allItemCss, String expectedTextItem) { 
		// Click vào 1 thẻ bất kỳ để làm sao cho nó xổ ra hết các item của dropdown
		driver.findElement(By.cssSelector(parentCss)).click();
		
		// chờ các item load ra thành công
		// Locator phải lấy đại diện cho tất cả các item
		// Lấy thẻ chứa text
		epliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		
		// Đưa hết tất cả item trong dropdown vào 1 List
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));
		
		// Tìm item xem đúng cái đang cần hay không(dùng vòng lặp duyệt qua để tìm)
		for (WebElement tempItem : speedDropdownItems) {
			String itemText = tempItem.getText();
			System.out.println(itemText);
			
			// Kiểm tra text của item đúng với cái mình mong muốn
			if (itemText.trim().equals(expectedTextItem)) {
				// Click vào đúng item đó
				tempItem.click();
				
				// thoát ra khỏi vòng lặp không xét cho các case còn lại nữa
			 break;
	         }
				
			}
	}
	
	public void enterAndselectItemInDropdown(String textboxCss, String allItemCss, String expectedTextItem) { 
		// Nhập expected text item vào - xổ ra tất cả cac item matching
		driver.findElement(By.cssSelector(textboxCss)).clear();
		driver.findElement(By.cssSelector(textboxCss)).sendKeys(expectedTextItem);
		sleepInSecond(1);
		
		// chờ các item load ra thành công
		// Locator phải lấy đại diện cho tất cả các item
		// Lấy thẻ chứa text
		// Đưa hết tất cả item trong dropdown vào 1 List
		List<WebElement> speedDropdownItems = epliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		
		// Tìm item xem đúng cái đang cần hay không(dùng vòng lặp duyệt qua để tìm)
		for (WebElement tempItem : speedDropdownItems) {
			// Kiểm tra text của item đúng với cái mình mong muốn
			if (tempItem.getText().trim().equals(expectedTextItem)) {
				sleepInSecond(1);
				// Click vào đúng item đó
				tempItem.click();
				
				// thoát ra khỏi vòng lặp không xét cho các case còn lại nữa
			 break;
	         }
				
			}
	}
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}