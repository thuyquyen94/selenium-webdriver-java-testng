package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_19_JavascriptExecutor {
	WebDriver driver;

	JavascriptExecutor jsExecutor;
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
	}

	@Test
	public void TC_01_() {
		navigateToUrlByJS("http://live.techpanda.org");
		sleepInSecond(5);

		// get domain bằng JE
		String techPandaDomain = (String) executeForBrowser("return document.domain;");
		// Verify domain
		Assert.assertEquals(techPandaDomain, "live.techpanda.org");

		// Get URL bằng JE
		String homePageURL = (String) executeForBrowser("return document.URL;");
		// Verify URL
		Assert.assertEquals(homePageURL, "http://live.techpanda.org/");

		// Thêm hightlight để hình dùng code chạy như thế nào
		hightlightElement("//a[text()='Mobile']");

		// Open Mobile dùng JE
		clickToElementByJS("//a[text()='Mobile']");

		hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button");

		// Verify đoạn text
		Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));

		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");

		// Verify title
		String customerTitle = (String) executeForBrowser("return document.title;");
		Assert.assertEquals(customerTitle, "Customer Service");

		// Scroll tới element
		scrollToBottomPage();

		// Input Email
		hightlightElement("//input[@id='newsletter']");
		sendkeyToElementByJS("//input[@id='newsletter']", getEmailAddress());

		hightlightElement("//button[@title='Subscribe']");
		clickToElementByJS("//button[@title='Subscribe']");

		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));

		// Chuyển trang
		navigateToUrlByJS("https://www.facebook.com/");
		sleepInSecond(5);

		Assert.assertEquals(executeForBrowser("return document.domain;"),"facebook.com");
	}

	@Test
	public void TC_02_() {
		driver.get("https://sieuthimaymocthietbi.com/account/register");

		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();

		driver.findElement(By.xpath("//input[@id='lastName']"));
		sleepInSecond(3);

		Assert.assertEquals(getElementValidationMessage("//input[@id='lastName']"),"Please fill out this field.");

		driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Automation");
		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
		sleepInSecond(3);

		Assert.assertEquals(getElementValidationMessage("//input[@id='firstName']"), "Please fill out this field.");

		driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Testing");
		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
		sleepInSecond(3);

		Assert.assertEquals(getElementValidationMessage("//input[@id='firstName']"), "Please fill out this field.");

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("aa@bb@cc");
		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
		sleepInSecond(3);

		Assert.assertEquals(getElementValidationMessage("//input[@id='email']"), "Please enter an email address.");
	}

	@Test
	public void TC_03_() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean isExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
		sleepInSecond(3);
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
		sleepInSecond(3);
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
		jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public String getAttributeInDOM(String locator, String attributeName) {
		return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));

	}

	public String getEmailAddress() {
		Random rand = new Random();
		return "kevinlamp" + rand.nextInt(99999) + "@gmail.net";
	}
}




