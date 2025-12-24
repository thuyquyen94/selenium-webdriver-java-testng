package Discipline;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.time.Duration;
import java.util.Random;

public class Exercise_03_Textbox_Textarea_2 {
	WebDriver driver;
	String firstName, lastName, emailAddress, password, fullName;
	Random rand;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		rand = new Random();

		firstName = "Automation";
		lastName = "Testing" ;
		fullName = firstName + " " + lastName;
		emailAddress = "AutomationTesting" + rand.nextInt(99999) + "@gmail.com";
		password = "123456";
	}

	@Test
	public void TC_01_TechPanda() throws InterruptedException {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//div[@class='col2-set']//span[text()='Create an Account']")).click();

		driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
		driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("input#password")).sendKeys(password);
		driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);
		driver.findElement(By.xpath("//div[@class='buttons-set']//span[text()='Register']")).click();

		// Tuyệt đối
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),
				("Thank you for registering with Main Website Store."));

		String contactInformationText = driver.findElement(By.xpath(
				"//h3[text()='Contact Information']/parent::div[@class='box-title']/following-sibling::div/p")).getText();
		System.out.printf(contactInformationText);

		//Tương đối
        Assert.assertTrue(contactInformationText.contains(fullName) && contactInformationText.contains(emailAddress)); // Full name + Email

		driver.findElement(By.xpath("//h3[text()='Contact Information']/following-sibling::a")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#lastname")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getAttribute("value"), emailAddress);

		// Product Review
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();

		driver.findElement(By.xpath("//h2/a[text()='Samsung Galaxy']")).click();

		driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();

		driver.findElement(By.cssSelector("input[id='Quality 1_1']")).click();

		driver.findElement(By.cssSelector("textarea[id='review_field']")).sendKeys(
				"Good application\nPretty easy to navigate. ");

		driver.findElement(By.cssSelector("input#summary_field")).sendKeys("Good Phone");

		driver.findElement(By.cssSelector("input#nickname_field")).clear();
		driver.findElement(By.cssSelector("input#nickname_field")).sendKeys("AutomationFC");

		driver.findElement(By.xpath("//button[@title='Submit Review']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "Your review has been accepted for moderation.");



		// Logout
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']/a")).click();

		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		Thread.sleep(5000);

		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/logoutSuccess/");




	  }


	@AfterClass
	public void cleanBrowser(){
		driver.quit();
	}
}