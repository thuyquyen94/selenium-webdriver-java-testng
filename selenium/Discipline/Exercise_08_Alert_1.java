package Discipline;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Exercise_08_Alert_1 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test
	public void TC_01_() {
	
	}

	@Test
	public void TC_02_() {
		
	}
	@Test
	public void TC_03_() {
	
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