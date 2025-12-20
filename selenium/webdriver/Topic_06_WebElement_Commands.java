package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;

public class Topic_06_WebElement_Commands {
	// Chứa các hàm để tương tác với Browser
	WebDriver driver;

	WebElement element;

	@BeforeClass
	public void initialBrowser() throws MalformedURLException {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://demo.nopcommerce.com/login?returnUrl=%2Fcustomer%2Finfo");
	}

	@Test
	public void TC_01_WebElement() {
		// Dùng 1 lần
		driver.findElement(By.xpath("")).click();
		// Dùng nhiều lần (khai báo biến)
		element = driver.findElement(By.xpath(""));

		// Dùng để click lên element dạng : Button/ checkbox/radio/ link/ image...
		element.click();

		// Nhập liệu các element dạng: textbox/ textarea/ dropdown
		// Xóa dữ liệu trước khi senkey
		element.clear();
		element.sendKeys("AutomatioFC");
		element.sendKeys(Keys.ENTER);

		//Selenium support
		driver.findElement(By.cssSelector("div.login-page")).findElement(By.cssSelector("div.customer-blocks"))
				.findElement(By.id("Email"));
		// Bản rút gọn
		// Tìm từ node cha vào node con
		driver.findElements(By.cssSelector("div.login-page div.customer-blocks input#Email"));

		// Form (element nào là thẻ form hoặc nằm trong thẻ form) SingnUp/ Login/ Search...
		// Hành vi giống phím Enter(send request lên server)
		driver.findElement(By.id("Email")).sendKeys("");
		driver.findElement(By.id("Password")).sendKeys("");
		driver.findElement(By.id("Password")).submit();

		// Áp dụng cho tất cả các loại Element\
		// Kiêm tra element có hiển thị hay không
		// Size > 0: width/ height > 0
		// Nhìn thấy/ thao tác được
		element.isDisplayed();
		Assert.assertTrue(element.isDisplayed());
		Assert.assertFalse(element.isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.id("")).isDisplayed());

		// Áp dụng cho duy nhất 3 loại : checkbox/ radio/ dropdown (cho việc chọn thành công)
		// Kiểm tra 1 element đã được chọn rô hay chưa chọn
		element.isSelected();
		Assert.assertTrue(driver.findElement(By.id("")).isSelected());
		Assert.assertFalse(driver.findElement(By.id("")).isSelected());

		// Áp dụng cho tất cả các loại
		// Kiểm tra 1 element có bị disable hay không (read only)
		element.isEnabled();
		Assert.assertTrue(driver.findElement(By.id("")).isEnabled());
		Assert.assertFalse(driver.findElement(By.id("")).isEnabled());

		// Dùng test GUI: font color, font size, position, location..
		element.getCssValue("background-color");
		driver.findElement(By.id("")).getCssValue("background-color");
		driver.findElement(By.id("")).getCssValue("font-size");
		driver.findElement(By.id("")).getCssValue("text-transform");

		// Áp dụng cho element chưa text (Link/ Button/ Header/ Label..
		// Dùng rất nhiều
		element.getText();

		// Lấy thuộc tính của element
		element.getAttribute("placeholder");

		// Kiểm tra chiều rộng + cao?
		// get size element
		Dimension dimensionElement = element.getSize();
		// get size browser
		Dimension dimensionBrowser = driver.manage().window().getSize();

		// Vị trí của element so với viewpoint
		Point pointElement =  element.getLocation();
		Point pointBrowser = driver.manage().window().getPosition();

		// Location + Size
		Rectangle rectangle = element.getRect();
		//Size
		rectangle.getWidth();
		rectangle.getHeight();
		rectangle.getDimension();
		// Location
		rectangle.getY();
		rectangle.getX();
		rectangle.getPoint();

		// Lấy ra cái thẻ html của elment đó
		// Từ id/ class/ name/ css/ xpath có thể truy ra ngược lại tagname HTML
		element.getTagName();
		driver.findElement(By.cssSelector("#firstname")).getTagName();  // input
		driver.findElement(By.id("firstname")).getTagName();    // input
		driver.findElement(By.className("form-instructions")).getTagName(); // p

		// Tab Accesibility/ Properties trong Elements
		element.getAccessibleName();
		driver.findElement(By.id("")).getAccessibleName();
		element.getDomAttribute("");
		driver.findElement(By.id("")).getDomAttribute("checked");
		element.getDomProperty("");
		driver.findElement(By.id("")).getDomProperty("baseURI");
		driver.findElement(By.id("")).getDomProperty("outerHTML");

		// Chụp hình bị lỗi và lưu dưới dạng nào
		// BYTE
		// FILE (Lưu thành 1 hình có kích thước ở trong ổ cứng: .png/ .jpg/..)
		// BASE64 (Hình dạng text)
		driver.findElement(By.cssSelector("address.copyright")).getScreenshotAs(OutputType.FILE);
		driver.findElement(By.cssSelector("address.copyright")).getScreenshotAs(OutputType.BASE64); //*
		driver.findElement(By.cssSelector("address.copyright")).getScreenshotAs(OutputType.BYTES);

		// Trả về 1 element khớp vs điều kiện
		WebElement fullNameTextbox = driver.findElement(By.id(""));

		// Trả về nhiều element khớp vs điều kiện
		List<WebElement> textboxes = driver.findElements(By.cssSelector(""));

		// Dropdown (default/ custom)
		Select select = new Select(driver.findElement(By.id("")));

		// HTML Element
		// <input type="text" id="firstname" name="firstname" value=""
		// title="First Name" maxlength="255" class="input-text required-entry">
		driver.findElement(By.id("")).getAttribute("title"); //*
		driver.findElement(By.id("")).getAttribute("type");
		driver.findElement(By.id("")).getAttribute("id");




		// Shadow Element (JavascriptExecutor)
		driver.findElement(By.id("")).getShadowRoot();



		// Element A -> đầu ra của nó là tagname của element A
		String formListTag = driver.findElement(By.xpath("//*[@class='form-list']")).getTagName();

		// Đầu vào của Element B sẽ nhận tagname của element A là tham số
		driver.findElement(By.xpath("//div[@class='remember-me-popup']/preceding-sibling::" + formListTag));

		driver.findElement(By.cssSelector("address.copyright")).getText(); //**
		// © 2015 Magento Demo Store. All Rights Reserved.




	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}