package webdriver;

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

public class Topic_22_Wait_Element_Status {
	WebDriver driver;

	WebDriverWait expliciWait;
	By reconfirmEmailTextbox = (By.cssSelector("input[name='reg_email_confirmation__']"));


	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_Visible() {
	driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
	sleepInSecond(2);

	driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("quyen@gmail.com");
	sleepInSecond(2);

	// Điều kiện 1: Element có xuất hiện ở trên UI và có trong cây HTML

	// Tại đúng thời điểm này/ step này thì Confirm Email textbox đang visible/ displayed
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(reconfirmEmailTextbox));

		// Kiểm tra 1 element đang hiện thị
		Assert.assertTrue(driver.findElement(reconfirmEmailTextbox).isDisplayed());
	}

	@Test
	public void TC_02_Invisible_IN_DOM() {
		// Điều kiện 2: Element không hiện trên UI và có trong DOM

		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);

		driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
		sleepInSecond(3);

		// Tại đúng thời điểm này/ step này thì Confirm Email textbox đang invisible
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTextbox));

		// Kiểm tra 1 element không hiển thị
		// Chạy nhanh -> kết quả step passed
		Assert.assertFalse(driver.findElement(reconfirmEmailTextbox).isDisplayed());

	}
	@Test
	public void TC_02_Invisible__NOT_IN_DOM() {
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);

		// Click vào icon close để đóng popup lại
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInSecond(3);

		// Điều kiện 3: Element không hiện trên UI và không có trong DOM
		// Tại đúng thời điểm này/ step này thì Confirm Email textbox đang invisible
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTextbox));

		// Kiểm tra 1 element không hiển thị
		// Chạy lâu -> kết quả step failed
		// Assert.assertFalse(driver.findElement(reconfirmEmailTextbox).isDisplayed());

	}
	@Test
	public void TC_03_Presence() {

		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);

		driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("quyen@gmail.com");
		sleepInSecond(2);

		// Điều kiện 1: Element hiển thị trên UI và có trong DOM (presence chỉ tâm có Trong DOM là nó presence)
		// Tại đúng thời điểm này/ step này thì Confirm Email textbox presence (có trong HTML)
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(reconfirmEmailTextbox));

		driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
		sleepInSecond(3);

		// Điều kiện 2: Element không hiển thị trên UI và có trong DOM
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(reconfirmEmailTextbox));
	}
	@Test
	public void TC_04_Staleness() {
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);

		// Tại thời điểm này element có xuất hiện và mình sẽ findElement
		// Attached to the DOM
		WebElement reconfirmEmail = driver.findElement(reconfirmEmailTextbox);

		// Click vào icon close để đóng popup lại
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInSecond(3);

		// Điều kiện 3: Element không hiện trên UI và không có trong DOM
		// Wait until an element is no longer attached to the DOM
		expliciWait.until(ExpectedConditions.stalenessOf(reconfirmEmail));
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