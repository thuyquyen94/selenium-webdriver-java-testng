package Discipline;

import io.netty.handler.codec.DecoderResult;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Exercise_09_Actions_2 {
	WebDriver driver;
	Actions action;
	String osName = System.getProperty("os.name");
	Keys keys;
	JavascriptExecutor javascriptExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();

		javascriptExecutor = (JavascriptExecutor) driver;

		action = new Actions(driver);
		action.moveByOffset(0, 0).perform();

		if (osName.startsWith("Window")){
			keys = Keys.CONTROL;
		}else {
			keys = keys.COMMAND;
		}
	}

	@Test
	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");

		WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));
		action.moveToElement(ageTextbox).perform();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");


	}

	@Test
	public void TC_02_Hover_Myntra() {
		driver.get("https://www.myntra.com/");

		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
		sleepInSecond(3);

		action.click(driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']"))).perform();

		Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(),"Kids Home Bath");

	}
	@Test
	public void TC_03_Hover_Fahasa() {
		driver.get("https://www.fahasa.com/");

		action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
		sleepInSecond(2);

		action.moveToElement(driver.findElement(By.xpath("//span[text()='Sách Giáo Khoa 2025']"))).perform();
		sleepInSecond(2);

		driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='Luyện Thi Môn Toán']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Toán']")).isDisplayed());



	
	}

	@Test
	public void TC_04_Click_And_Hold_Block(){
		driver.get("https://automationfc.github.io/jquery-selectable/");

		List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		Assert.assertEquals(allNumber.size(),30);

		action.clickAndHold(allNumber.get(0)) // Click vào số 1 và giữ chuột
				.moveToElement(allNumber.get(29)) // Di chuyển tới số 4
				.release().perform(); // Nhả chuột trái ra, kết thúc cho sự kiện ClickAndHold
		sleepInSecond(3);

		List<WebElement> allNumberSlected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(allNumberSlected.size(),30);
	}

	@Test
	public void TC_05_Click_And_Hold_Random(){
		driver.get("https://automationfc.github.io/jquery-selectable/");

		List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		Assert.assertEquals(allNumber.size(),30);

		// Nhấn phím Ctrl xuống (chưa nhả ra)
		action.keyDown(keys).perform();

		action.click(allNumber.get(0))
			  .click(allNumber.get(4))
			  .click(allNumber.get(8))
			  .click(allNumber.get(23))
			  .click(allNumber.get(25))
			  .click(allNumber.get(29))
				.pause(Duration.ofSeconds(3))
				.perform();

         // Nhấn phím Ctrl ra
		action.keyUp(keys).perform();

		List<WebElement> allNumberSlected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(allNumberSlected.size(),6);
	}

	@Test
	public void TC_06_Double_Click(){
		driver.get("https://automationfc.github.io/basic-form/index.html");

		WebElement doubleClickButton = (driver.findElement(By.xpath("//button[text()='Double click me']")));
		if (driver.toString().contains("firefox")){
			// scrollIntoView(true) : kéo mép trên của element lên phía trên cùng của viewpoint
			// scrollIntoView(false) : kéo mép dưới của element xuống phía dưới cùng của viewpoint
			javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);",doubleClickButton);
			sleepInSecond(3);
		}
		action.doubleClick(doubleClickButton).perform();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(),"Hello Automation Guys!");
	}


	@Test
	public void TC_07_Right_Click(){
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

		// Click chuột phải vào button
		action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		sleepInSecond(3);

		// Trước khi hover
		By quitContentBy = By.cssSelector("li.context-menu-icon-quit");
		Assert.assertTrue(driver.findElement(quitContentBy).isDisplayed());

		// Hover mouse(Sau khi hover)
		action.moveToElement(driver.findElement(quitContentBy)).perform();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible")).isDisplayed());

		// Click Quit
		action.click(driver.findElement(quitContentBy)).perform();
		sleepInSecond(3);

		driver.switchTo().alert().accept();
		Assert.assertFalse(driver.findElement(quitContentBy).isDisplayed());

	}

	@Test
	public void TC_09_Drag_Drop_HTML4(){
		driver.get("https://automationfc.github.io/kendo-drag-drop/");

		WebElement sourceCircle = driver.findElement(By.cssSelector("div#draggable"));
		WebElement targetCircle = driver.findElement(By.cssSelector("div#droptarget"));

		action.dragAndDrop(sourceCircle,targetCircle).perform();

		Assert.assertEquals(targetCircle.getText(),"You did great!");

		Assert.assertEquals(Color.fromString(targetCircle.getCssValue("background-color"))
				.asHex().toUpperCase(), "#03A9F4");
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