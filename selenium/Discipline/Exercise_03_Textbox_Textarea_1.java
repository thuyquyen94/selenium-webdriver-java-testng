package Discipline;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Exercise_03_Textbox_Textarea_1 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test
	public void Login_01_Empty_Email_Password() {
	driver.get("http://live.techpanda.org/index.php/");

	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	sleepInSecond(3);

	driver.findElement(By.cssSelector("button#send2")).click();

	Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(),"This is a required field.");
	Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(),"This is a required field.");
	}

	@Test
	public void Login_02_Invalid_Email() {
		driver.get("http://live.techpanda.org/index.php/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);

		driver.findElement(By.cssSelector("input#email")).sendKeys("12345@123");
		driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#send2")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");


	}
	@Test
	public void Login_03_Invalid_Password() {
		driver.get("http://live.techpanda.org/index.php/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);

		driver.findElement(By.cssSelector("input#email")).sendKeys("autofc@gmail.com");
		driver.findElement(By.cssSelector("input#pass")).sendKeys("123");
		driver.findElement(By.cssSelector("button#send2")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
	}
	@Test
	public void Login_04_Incorrect_Email_Password() {
		driver.get("http://live.techpanda.org/index.php/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);

		driver.findElement(By.cssSelector("input#email")).sendKeys("autofc@gmail.com");
		driver.findElement(By.cssSelector("input#pass")).sendKeys("123123123");
		driver.findElement(By.cssSelector("button#send2")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(),"Invalid login or password.");
		sleepInSecond(5);

		driver.findElement(By.cssSelector("input#email")).clear();
		driver.findElement(By.cssSelector("input#pass")).clear();
		driver.findElement(By.cssSelector("input#email")).sendKeys("123@45321");
		driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");
		sleepInSecond(4);

		driver.findElement(By.cssSelector("button#send2")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(),"Invalid login or password.");

	}
	@Test
	public void Login_05_Success() {
		driver.get("http://live.techpanda.org/index.php/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);

		/// Register
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(2);

		String firstName ="Nhat";
		String lastName = "FC";
		String emailAddress = getEmailAddress();
		String password = "123456789";
		String fullName = firstName + " " + lastName;

		driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
		driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("input#password")).sendKeys(password);
		driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);
		driver.findElement(By.cssSelector("button[title='Register']")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(), "Hello, " + fullName + "!");

		String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInfo.contains(fullName));
		Assert.assertTrue(contactInfo.contains(emailAddress));

		// Logout
		driver.findElement(By.cssSelector("a.skip-account")).click();
		sleepInSecond(3);
		driver.findElement(By.cssSelector("a[title='Log Out']")).click();
		sleepInSecond(5);

		// Login
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		driver.findElement(By.cssSelector("input#email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("input#pass")).sendKeys(password);

		driver.findElement(By.cssSelector("button#send2")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(), "Hello, " + fullName + "!");

		contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInfo.contains(fullName));
		Assert.assertTrue(contactInfo.contains(emailAddress));

		// Verify Account
		driver.findElement(By.xpath("//a[text()='Account Information']")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getAttribute("value"),firstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#lastname")).getAttribute("value"),lastName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getAttribute("value"),emailAddress);

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
		return "automation" + rad.nextInt(99999) + "@gmail.com";
	}
}