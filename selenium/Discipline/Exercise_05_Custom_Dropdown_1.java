package Discipline;

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

import java.time.Duration;
import java.util.List;

public class Exercise_05_Custom_Dropdown_1 {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));
	}

	@Test
	public void TC_01_JQuery() {
	driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

	selectItemDropdown("span#speed-button","ul#speed-menu div","Medium");
	sleepInSecond(3);

	selectItemDropdown("span#files-button","ul#files-menu div","ui.jQuery.js");
	sleepInSecond(3);

	selectItemDropdown("span#number-button","ul#number-menu div","15");
	sleepInSecond(3);

	selectItemDropdown("span#salutation-button","ul#salutation-menu div","Mr.");
	sleepInSecond(3);

	     Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),"Medium");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button>span.ui-selectmenu-text")).getText(),"ui.jQuery.js");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(),"15");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(),"Mr.");


	}

	@Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		selectItemDropdown("i.dropdown.icon","div.item>span.text","Elliot Fu");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Elliot Fu");

		selectItemDropdown("i.dropdown.icon","div.item>span.text","Stevie Feliciano");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Stevie Feliciano");

		selectItemDropdown("i.dropdown.icon","div.item>span.text","Christian");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Christian");
	}
	@Test
	public void TC_03_VueJS() {
	driver.get("https://mikerodham.github.io/vue-dropdowns/");

	selectItemDropdown("li.dropdown-toggle","ul.dropdown-menu a","First Option");
	sleepInSecond(3);
	Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"First Option");

	selectItemDropdown("li.dropdown-toggle","ul.dropdown-menu a","Second Option");
	sleepInSecond(3);
	Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Second Option");

	selectItemDropdown("li.dropdown-toggle","ul.dropdown-menu a","Third Option");
	sleepInSecond(3);
	Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Third Option");

	}

	@Test
	public void TC_04_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		selectItemInEditableDropdown("input.search","div.item span","Afghanistan");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Afghanistan");

		selectItemInEditableDropdown("input.search","div.item span","Aland Islands");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Aland Islands");

	}

	@Test
	public void TC_05_Nopcommerce() {
		driver.get("https://demo.nopcommerce.com/");
		selectItemDropdown("select[name='DateOfBirthDay']","select[name='DateOfBirthDay']>option","18");

		// Dropdown là default nhưng không sử dụng thư viện Select để verify
		selectItemDropdown("select[name='DateOfBirthDay']", "select[name='DateOfBirthDay']>option", "18");
		Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']>option[value='18']")).isSelected());
		sleepInSecond(2);

		selectItemDropdown("select[name='DateOfBirthMonth']", "select[name='DateOfBirthMonth']>option", "September");
		Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']>option[value='9']")).isSelected());
		sleepInSecond(2);

		selectItemDropdown("select[name='DateOfBirthYear']", "select[name='DateOfBirthYear']>option", "1995");
		Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']>option[value='1995']")).isSelected());
		sleepInSecond(2);
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void selectItemDropdown(String parentCss, String childItemCss, String itemTextExpected){
		driver.findElement(By.cssSelector(parentCss)).click();
		sleepInSecond(2);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.
				presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss)));
		for (WebElement item : allItems){
			if (item.getText().equals(itemTextExpected)){
				item.click();
				break;
			}
		}
	}

	public void selectItemInEditableDropdown(String parentCss, String childItemCss, String itemTextExpected){
		driver.findElement(By.cssSelector(parentCss)).clear();
		sleepInSecond(2);
		driver.findElement(By.cssSelector(parentCss)).sendKeys(itemTextExpected);
		sleepInSecond(2);

		List<WebElement> allItems = explicitWait.until(ExpectedConditions.
				presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss)));
		for (WebElement item : allItems){
			if (item.getText().equals(itemTextExpected)){
				item.click();
				break;
			}
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}