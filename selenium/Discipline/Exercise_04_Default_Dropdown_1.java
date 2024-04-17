package Discipline;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Exercise_04_Default_Dropdown_1 {
	WebDriver driver;

    String firstName = "ut", lastName = "Nhat", emailAddress = getEmailAddress();

	String companyName = "Auto FC", password = "123456";

	String day = "15", month = "December", year = "1994";

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test
	public void TC_01_Register() {
	driver.get("https://demo.nopcommerce.com/");

	driver.findElement(By.cssSelector("a.ico-register")).click();

	    driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
//		Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
//		day.selectByVisibleText("15");
		//Verify dropdown này là Single không phải Multiple
//		Assert.assertFalse(day.isMultiple());
		// Verify số lượng item trong Dropdown này là 32 item
//		Assert.assertEquals(day.getOptions().size(),32);

		new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText(day);
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);

		driver.findElement(By.cssSelector("button#register-button")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");
	}

	@Test
	public void TC_02_Login() {
		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.cssSelector("a.ico-login")).click();

		driver.findElement(By.cssSelector("input.email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
		driver.findElement(By.cssSelector("button.login-button")).click();
		sleepInSecond(3);

		driver.findElement(By.cssSelector("a.ico-account")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"),firstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"),lastName);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(),day);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(),month);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(),year);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"),emailAddress);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"),companyName);


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
	public String getEmailAddress(){
		Random rad = new Random();
		rad.nextInt(99999);
		return "ut" + rad.nextInt(99999) + "@gmail.net";
	}
}