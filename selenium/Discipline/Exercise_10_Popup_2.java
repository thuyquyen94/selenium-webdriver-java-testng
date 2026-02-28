package Discipline;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Exercise_10_Popup_2 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test
	public void TC_01_Kyna() {

	driver.get("https://skills.kynaenglish.vn/dang-nhap");

	driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		By loginPopup = By.cssSelector("div#k-popup-account-login");
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),"Sai tên đăng nhập hoặc mật khẩu");
	}

	@Test
	public void TC_02_Tiki_Not_In_DOM() {
		driver.get("https://tiki.vn/");

		driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
		sleepInSecond(2);

		By loginPopup = By.cssSelector("div.ReactModal__Content");
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

		driver.findElement(By.cssSelector("p.login-with-email")).click();
		sleepInSecond(2);

		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-mess'][1]")).getText(),"Email không được để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-mess'][2]")).getText(),"Mật khẩu không được để trống");
		sleepInSecond(2);

		driver.findElement(By.cssSelector("img.close-img")).click();
		sleepInSecond(2);

		// Popup không hiển thị (không có trong DOM)
		Assert.assertEquals(driver.findElements(loginPopup).size(),0);
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