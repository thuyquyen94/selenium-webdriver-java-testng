package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_06_WebElement_Commands_01 {
	WebDriver driver;
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test
	public void TC_01_WebElement() {
	WebElement element = driver.findElement(By.className(""));
	
	// dùng cho các texbox/ textarea/ dropdown(Editable)
	// Xóa dữ liệu trước khi nhập text
	element.clear();
	
	// dùng cho các texbox/ textarea/ dropdown(Editable)
	// Nhập dữ liệu trước khi nhập text
	element.sendKeys("");
	
	// Dùng để click vào button/ link/ radio/ checkbox/ image...
	element.click();
	
	// String searchAttribute = element.getAttribute("placeholder");
	// Search store
	
	// GUI: Font size/ Font color/ Location...
	element.getCssValue("background-color");
	// #4ab2f1;
	
	// Lấy ra vị trí so với web (bên ngoài)
	element.getLocation();
	
	// Lấy ra kích thước của element (bên trong)
	element.getSize();
	
	// Location + Size
	element.getRect();
	
	// chụp hình lỗi khi testcase fail
	element.getScreenshotAs(OutputType.FILE);
	element.getScreenshotAs(OutputType.BYTES);
	element.getScreenshotAs(OutputType.BASE64);
	
	// Cần lấy ra tên thẻ HTML của element đó. Truyền vào cho 1 locator khác
	driver.findElement(By.id("Email")).getTagName();
	driver.findElement(By.name("Email")).getTagName();
	
	driver.findElement(By.cssSelector("#Email")).getTagName();
	
	//lấy text từ error message/ sucess message/ label/ header...
	element.getText();
	// Please enter your email
	
	// Khi nào dùng gettext, khi nào dùng getattribute
	// Khi cái value mình cần lấy nó nằm bên ngoài dùng gettext
	// Khi cái value mình cần lấy nó nằm bên trong dùng getattribute
	
	// dùng để verify 1 element có hiển thị or không
	// Phạm vi: tất cả element
	Assert.assertTrue(element.isDisplayed());
	Assert.assertFalse(element.isDisplayed());
	
	// dùng để verify 1 element xem có thao tác được hay không
	// Phạm vi: tất cả element
	Assert.assertTrue(element.isEnabled());
	Assert.assertFalse(element.isEnabled());
	
	// dùng để verify 1 element được chọn hay chưa
	// Phạm vi:checkbox/ radio
	Assert.assertTrue(element.isSelected());
	Assert.assertFalse(element.isSelected());
	
	// Các element nằm trong thẻ form
	// tương ứng hành vi của end user là Enter
	element.submit();	
	
	
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