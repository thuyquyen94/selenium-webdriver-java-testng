package Discipline;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;
import java.util.Random;

public class Exercise_04_Default_Dropdown_2 {
	WebDriver driver;
	Select select;

	String firstName, lastName , email, password , companyName ;
	Random rand;

	@BeforeClass
	public void initialBrowser() {
		driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		rand = new Random();

		firstName ="automation";
		lastName ="testing";
		email ="test123456" + rand.nextInt(99999)+ "@gmail.com";
		password =" 123456";
		companyName ="automationFC";

	}

	@Test
	public void TC_01_Facebook_SignUp() {
	driver.get("https://www.facebook.com/reg");

	// Dropdown xuất hiện
		select = new Select(driver.findElement(By.cssSelector("select#day")));
		select.selectByVisibleText("25");
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"25");

		select = new Select(driver.findElement(By.cssSelector("select#month")));
		select.selectByVisibleText("Nov");
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"Nov");

		select = new Select(driver.findElement(By.cssSelector("select#year")));
		select.selectByVisibleText("1926");
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"1926");

	}

	@Test
	public void TC_02_Nopcommerce() {
		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.xpath("//a[@class='ico-register']")).click();

		driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);
		driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
		driver.findElement(By.cssSelector("input#Company")).sendKeys(companyName);
		driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);

		driver.findElement(By.cssSelector("button#register-button")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),"Your registration completed");

		driver.findElement(By.cssSelector("a.ico-login")).click();

		driver.findElement(By.cssSelector("//a[@class='ico-account']")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"), email);


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
	//driver.quit();
	}
}