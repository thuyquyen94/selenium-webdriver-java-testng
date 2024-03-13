package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class Topic_30_Wait_09_Fluent {
	WebDriver driver;
	FluentWait<WebDriver> fluentDriver;

	FluentWait<WebElement> fluentElement;


	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		fluentDriver = new FluentWait<>(driver);

	}

	@Test
	public void TC_01_Cach_1() {
    driver.get("https://automationfc.github.io/dynamic-loading/");

	driver.findElement(By.cssSelector("div#start>button")).click();

	     // Chờ cho HelloWorld text hiển thị trong vòng 10s
		// Setting
		fluentDriver.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(100))
				.ignoring(NoSuchElementException.class);

		// Condition
		fluentDriver.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();
			}
		});
	}

	@Test
	public void TC_02_Cach_2() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Gọi hàm đã viết ra dùng
		waitAndFindElement(By.cssSelector("div#start>button")).click();

		// Chờ cho HelloWorld text hiển thị trong vòng 10s
		// Setting
		// fluentDriver.withTimeout(Duration.ofSeconds(10))
			//	.pollingEvery(Duration.ofMillis(100))
			//	.ignoring(NoSuchElementException.class);

		// Condition: Verify bên ngoài
		String helloText = waitAndFindElement(By.xpath("//div[@id='finish']/h4")).getText();

		Assert.assertEquals(helloText,"Hello World!");
		
	}
	@Test
	public void TC_03_() {
		driver.get("https://automationfc.github.io/fluent-wait/");

		// Design ra 1 element
		WebElement countDownTime = driver.findElement(By.cssSelector("div#javascript_countdown_time"));

		// Khởi tạo fluentElement
		fluentElement = new FluentWait<WebElement>(countDownTime);

		// Setting
		fluentElement.withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofMillis(100))
				.ignoring(NoSuchElementException.class);

		// Condition
		fluentElement.until(new Function<WebElement, Boolean>() {
			@Override
			public Boolean apply(WebElement webElement) {
				String text = webElement.getText();
				System.out.println(text);
				return text.endsWith("00");
			}
		});

	}

	public  WebElement waitAndFindElement(By locator) {
		FluentWait<WebDriver> fluentDriver = new FluentWait<WebDriver>(driver);
		fluentDriver.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofMillis(300))
				.ignoring(NoSuchElementException.class);

		return fluentDriver.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver webDriver) {
				return webDriver.findElement(locator);
			}
		});
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}