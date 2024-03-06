package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_23_Wait_03_Implicit {
	WebDriver driver;
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//driver.get("https://automationfc.github.io/dynamic-loading/");
	}

	@Test
	public void TC_01_Equals_5_Second() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.get("https://automationfc.github.io/dynamic-loading/");

	driver.findElement(By.cssSelector("div#start>button")).click();

	Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
	}

	@Test
	public void TC_02_Less_Than_5_Second() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.cssSelector("div#start>button")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
		
	}
	@Test
	public void TC_03_Greater_Than_5_Second() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.cssSelector("div#start>button")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
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