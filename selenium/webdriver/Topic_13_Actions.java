package webdriver;

import com.beust.ah.A;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Topic_13_Actions {
	WebDriver driver;
	Actions actions;

	JavascriptExecutor javascriptExecutor;

	@BeforeClass
	public void beforeClass() {
       // Chromium: Chrome/ Edge/ Cốc Cốc/ Opera...
		driver = new FirefoxDriver();
     // Action nó đang giả lập hành vi của Mouse/ Keyboard/ Pen nên khi nó chạy mình không sử dụng các thiết bị này vì
		// nó sẽ bị Conflict
		actions = new Actions(driver);

		javascriptExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Hover_Tooltips() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");

		WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));

		actions.moveToElement(ageTextbox ).perform();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");

	}

	//@Test
	public void TC_02_Hover_Login_Fahasa() {
	driver.get("https://www.fahasa.com/");

	actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
	sleepInSecond(2);

	actions.moveToElement(driver.findElement(By.xpath("//a[@title='Bách Hóa Online - Lưu Niệm']"))).perform();
	sleepInSecond(3);
	
	driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Thiết Bị Số - Phụ Kiện Số']")).click();
	sleepInSecond(3);

	Assert.assertEquals(driver.findElement(By.cssSelector("ol.breadcrumb strong")).getText(),"THIẾT BỊ SỐ - PHỤ KIẾN SỐ");

	Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Thiết Bị Số - Phụ Kiện Số']")).isDisplayed());
	}

	//@Test
	public void TC_03_ClickAndHold() {
    driver.get("https://automationfc.github.io/jquery-selectable/");

	// Tổng các số chưa chọn
	 List<WebElement> allnumbers = driver.findElements(By.cssSelector("li.ui-state-default"));

	 //20 ô
		Assert.assertEquals(allnumbers.size(),20);

		// Chọn theo Block - Ngang/ Dọc
		// Chọn 1=>15
		actions.clickAndHold(allnumbers.get(0)) //Click lên cái số 1 giữ chuột
				.pause(2000) // Dừng lại 2s
				.moveToElement(allnumbers.get(14)) // Di chuyển chuột trái đến số 15
				.release() // Nhả chuột trái ra
				.perform(); // Execute tất cả các Action trên.

		sleepInSecond(3);

		List<String> allNumberTextExpected = new ArrayList<String>();
		allNumberTextExpected.add("1");
		allNumberTextExpected.add("2");
		allNumberTextExpected.add("3");
		allNumberTextExpected.add("4");
		allNumberTextExpected.add("5");
		allNumberTextExpected.add("6");
		allNumberTextExpected.add("7");
		allNumberTextExpected.add("8");
		allNumberTextExpected.add("9");
		allNumberTextExpected.add("10");
		allNumberTextExpected.add("11");
		allNumberTextExpected.add("12");
		allNumberTextExpected.add("13");
		allNumberTextExpected.add("14");
		allNumberTextExpected.add("15");

		// Tổng các số đã chọn
		List<WebElement> allnumbersSelected = driver.findElements(By.cssSelector("li.ui-state-default.ui-selected"));

		Assert.assertEquals(allnumbersSelected.size(),
				12);

		List<String> allNumberTextActual = new ArrayList<String>();
		for (WebElement element: allnumbersSelected) {
			allNumberTextActual.add(element.getText());
		}

		Assert.assertEquals(allnumbersSelected, allNumberTextActual);


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

		// Tổng số chưa chọn
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
	public void TC_05_DoubleClick()	{
driver.get("https://automationfc.github.io/basic-form/index.html");

WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));

// Cần scroll tới element rồi mới double click - chỉ riêng Firefox mới scroll
		if (driver.toString().contains("firefox")){
			// scrollIntoView(true) : kéo mép trên của element lên phía trên cùng của viewpoint
			// scrollIntoView(false) : kéo mép dưới của element xuống phía dưới cùng của viewpoint
			javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);",doubleClickButton);
			sleepInSecond(3);
		}
		actions.doubleClick(doubleClickButton).perform();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(),"Hello Automation Guys!");
	}

	@Test
	public void TC_06_RightClick() {
driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

// Chưa click chuột phải thì nó đang không hiển thị (invisible)
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());

		actions.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		sleepInSecond(3);

// Thực hiện hành vi hover lên element- mới click chuột phải lên các element sẽ được visible
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());

		actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
		sleepInSecond(2);

		// Được cập nhật class của element-kiểm tra sự kiện hover thành công
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste.context-menu-hover.context-menu-visible")).isDisplayed());

		// Click lên Paste
		actions.click(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
		sleepInSecond(3);

		driver.switchTo().alert().accept();
		sleepInSecond(3);

		// Kiểm tra paste không còn hiển thị
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());

	}

	@Test
	public void TC_07_DragDropHTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");

		WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
		WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));

		actions.dragAndDrop(smallCircle, bigCircle).perform();
		sleepInSecond(3);

		Assert.assertEquals(bigCircle.getText(),"You did great!");
     // Verify màu nền
		Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex().toLowerCase(),"#03a9f4");

	}
	//@Test
	public void TC_07_DragDropHTML5_Css() throws IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");

		WebElement columA = driver.findElement(By.cssSelector("div#column-a"));
		WebElement columB = driver.findElement(By.cssSelector("div#column-b"));

		String projectpath = System.getProperty("user.dir");

		String dragAndDropFilePath = projectpath + "/dragAnDrop/drag_and_drop_helper.js";

		String jsContentFile = getContentFile(dragAndDropFilePath);

		jsContentFile = jsContentFile + "$('" + columA + "' ).simulateDragDrop({ dropTarget: '" + columB + "'});";

		// A -> B
     javascriptExecutor.executeScript(jsContentFile);
	 sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"A");
	}

	//@Test
	public void TC_07_DragDropHTML5_Xpath() throws AWTException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");

		dragAndDropHTML5ByXpath("//div[@id='column-a']", "//div[@id='column-b']");
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"A");

		dragAndDropHTML5ByXpath("//div[@id='column-a']", "//div[@id='column-b']");
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"A");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"B");


	}


	public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

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

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
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