package Discipline;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.time.Duration;
import java.util.Random;

public class Exercise_06_Button_2 {
	WebDriver driver;
	String email, password;
	Random rand;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		rand = new Random();
		password = "123456";
		email = " nameAutomation" + rand.nextInt(99999) + "@gmail.com";

	}

	@Test
	public void TC_01_() {
		driver.get("https://www.fahasa.com/customer/account/create");

	   driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

	   By loginButton = By.cssSelector("button.fhs-btn-login");

	    // is Enabled: nếu element mà nó enabled thì sẽ trả về true / disable sẽ trả về false
		// Mong đợi disable
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());

		Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase(),"#000000");

		driver.findElement(By.cssSelector("input#login_username")).sendKeys(email);
		driver.findElement(By.cssSelector("input#login_password")).sendKeys(password);
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(loginButton).isEnabled());

		Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase(), "#C92127");

		driver.findElement(loginButton).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div.fhs-login-msg")).getText(),"Số điện thoại/Email hoặc Mật khẩu sai!");


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