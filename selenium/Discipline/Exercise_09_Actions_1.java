package Discipline;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Exercise_09_Actions_1 {
	WebDriver driver;
	Actions actions;

	JavascriptExecutor javascriptExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		actions = new Actions(driver);
		javascriptExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test
	public void TC_01_Hover_Tooltip() {
	driver.get("https://automationfc.github.io/jquery-tooltip/");

		WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));
		actions.moveToElement(ageTextbox).perform();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");

	}

	@Test
	public void TC_02_Fahasa() {
		driver.get("https://www.fahasa.com/");

		actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
		actions.moveToElement(driver.findElement(By.xpath("//a[@title='Bách Hóa Online - Lưu Niệm']"))).perform();

		driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Thiết Bị Số - Phụ Kiện Số']")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("ol.breadcrumb strong")).getText(),"THIẾT BỊ SỐ - PHỤ KIỆN SỐ");
		Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Thiết Bị Số - Phụ Kiện Số']")).isDisplayed());

	}
	@Test
	public void TC_03_ClickAndHold() {
	driver.get("https://automationfc.github.io/jquery-selectable/");

	 List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
	 Assert.assertEquals(allNumbers.size(),20);

	 actions.clickAndHold(allNumbers.get(0)).pause(2000)
			 .moveToElement(allNumbers.get(14))
			 .release().perform();
	 sleepInSecond(3);

	 List<String> allNumberTextExpected = new ArrayList<String>();
	    allNumberTextExpected.add("1");
		allNumberTextExpected.add("2");
		allNumberTextExpected.add("3");
		allNumberTextExpected.add("5");
		allNumberTextExpected.add("6");
		allNumberTextExpected.add("7");
		allNumberTextExpected.add("9");
		allNumberTextExpected.add("10");
		allNumberTextExpected.add("11");
		allNumberTextExpected.add("13");
		allNumberTextExpected.add("14");
		allNumberTextExpected.add("15");

		List<WebElement> allNumbersSelected = driver.findElements(By.cssSelector("li.ui-state-default.ui-selected"));
		Assert.assertEquals(allNumbersSelected.size(),12);

		List<String> allNumberTextActual = new ArrayList<String>();
		for (WebElement element : allNumbersSelected){
			allNumberTextActual.add(element.getText());
		}
		Assert.assertEquals(allNumberTextExpected,allNumberTextActual);
	}

	@Test
	public void TC_04_ClickAndHold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		String osName = System.getProperty("os.name");
		Keys keys;

		if (osName.startsWith("Windows")) {
			keys = Keys.CONTROL;
		} else {
			keys = Keys.COMMAND;
		}

		Keys cmdCtrl = Platform.getCurrent().is(Platform.WINDOWS) ? Keys.COMMAND : Keys.CONTROL;

		List<WebElement> allnumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
		Assert.assertEquals(allnumbers.size(),20);

		// Chọn 1-> 12 theo đủ hàng/ cột
		actions.clickAndHold(allnumbers.get(0)).moveToElement(allnumbers.get(11)).release().perform();

		// Chọn 13-> 15 theo đủ hàng/ cột
		actions.keyDown(Keys.CONTROL).perform(); // Nhấn phím Ctrl xuống (chưa nhả chuột ra)

		actions.click(allnumbers.get(12))
				.click(allnumbers.get(13))
				.click(allnumbers.get(14))
				.keyUp(Keys.CONTROL).perform();
	}

	@Test
	public void TC_05_DoubleClick() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));

		if (driver.toString().contains("firefox")) {
			javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);",doubleClickButton);
		}
		actions.doubleClick(doubleClickButton).perform();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(),"Hello Automation Guys!");
	}

	@Test
	public void TC_06_RightClick() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());
		actions.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		sleepInSecond(3);

		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());
		actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
		sleepInSecond(3);

		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste.context-menu-hover.context-menu-visible")).isDisplayed());
		sleepInSecond(3);

		actions.click(driver.findElement(By.cssSelector("li.context-menu-icon-paste.context-menu-hover.context-menu-visible"))).perform();
		sleepInSecond(2);

		driver.switchTo().alert().accept();
		sleepInSecond(3);

		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());

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