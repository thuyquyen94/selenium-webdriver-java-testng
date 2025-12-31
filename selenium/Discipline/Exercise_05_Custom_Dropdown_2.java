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

public class Exercise_05_Custom_Dropdown_2 {
	WebDriver driver;

	WebDriverWait explicitWait;

	@BeforeClass
	public void initialBrowser() {
		driver = new FirefoxDriver();

		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test
	public void TC_01_Jquery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		selectItemInCustomDropdown("span#speed-button", "ul#speed-menu>li>div", "Slower");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slower");

		selectItemInCustomDropdown("span#number-button", "ul#number-menu>li>div", "19");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "19");


		selectItemInCustomDropdown("span#salutation-button", "ul#salutation-menu>li>div", "Prof.");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Prof.");

		selectItemInCustomDropdown("span#files-button","ul#files-menu>li>div", "Some other file with a very long option text");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button>span.ui-selectmenu-text")).getText(), "Some other file with a very long option text");


		// Dự án thực tế: 1 hàm để thao tác lên dropdonwn chỉ dùng cho 1 site/ app
		// Không dùng cho nhiều application khác nhau
		// Cơ chế của dropdown giống nhau- có chung hành vi như nhau

	 }

	@Test
	public void TC_02_React() {
	driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
	selectItemInCustomDropdown("div.dropdown", "div.item>span", "Matt");
	Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Matt");

		selectItemInCustomDropdown("div.dropdown", "div.item>span", "Stevie Feliciano");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Stevie Feliciano");


	}



	@Test
	public void TC_03_Vue() {
	driver.get("https://mikerodham.github.io/vue-dropdowns/");

	    selectItemInCustomDropdown("li.dropdown-toggle","ul.dropdown-menu>li>a","First Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"First Option");

		selectItemInCustomDropdown("li.dropdown-toggle","ul.dropdown-menu>li>a","Third Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Third Option");

		selectItemInCustomDropdown("li.dropdown-toggle","ul.dropdown-menu>li>a","Second Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Second Option");


	}

	@Test
	public void TC_04_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		enterItemInCustomDropdown("input.search","div.item>span","American Samoa");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"American Samoa");

		enterItemInCustomDropdown("input.search","div.item>span","Aruba");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Aruba");

	}


	private void selectItemInCustomDropdown (String parentCss, String childCss, String textItem) {
		// Hành vi để thao tác lên dropdown
		// 1- Chờ cho dropdown có thể thao tác lên đuơc (clickable)
		// 2- click vào element nào để nó xổ ra cái dropdown ra
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(parentCss))).click();
		sleepInSecond(2);

		// 3- Chờ cho tất cả item được load ra(precense)
		// 4- Tìm cái item nào đúng với mong đợi
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));

		for (WebElement item : allItems) {
			// System.out.println(item.getText());
			if (item.getText().equals(textItem)) {
				// 5- Click lên item đó
				item.click();
				break;
			}
		}
	}

	private void enterItemInCustomDropdown (String parentCss, String childCss, String textItem) {
		// Hành vi để thao tác lên dropdown
		// 1- Chờ cho dropdown có thể nhập được(visible)
		// 2- Senkey vào dropdown
		WebElement dropdownTextbox = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(parentCss)));
		dropdownTextbox.clear();
		dropdownTextbox.sendKeys(textItem);
		sleepInSecond(2);

		// 3- Chờ cho tất cả item được load ra(precense)
		// 4- Tìm cái item nào đúng với mong đợi
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));

		for (WebElement item : allItems) {
			// System.out.println(item.getText());
			if (item.getText().equals(textItem)) {
				// 5- Click lên item đó
				item.click();
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
driver.quit();
	}
}