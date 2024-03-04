package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_06_WebElement_Commands_02 {
	WebDriver driver;
	Random Rand;
	String osName = System.getProperty("os.name");
    String emailAdress, firtName, LastName, FullName, Password;
    
	By emailtextbox = By.id("mail");
	By ageUnder18Radio = By.cssSelector("#under_18");
	By EducationTextArea = By.cssSelector("#edu");
	By NameUser5Text = By.xpath("//h5[text()='Name: User5']");
	By Passwordtextbox = By.cssSelector("#disable_password");
	By BiographyTextArea = By.cssSelector("#bio");
	By Developmentcheckbox = By.cssSelector("#development");
	
	@BeforeClass
	public void beforeClass() {
        Rand = new Random();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		emailAdress = "automation" + Rand.nextInt(9999) + "@gmail.com";
		firtName = "Automation";
		LastName = "Fan";
		FullName = firtName + " " + LastName;
		Password = "12345678";
		
	}

	@Test
	public void Login_01_Empty_Email_And_Password() {
			driver.get("http://live.techpanda.org/");
			// Click vào My Account
			driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
			sleepInSecond(2);
			
			driver.findElement(By.id("send2")).click();
			sleepInSecond(2);
			
			Assert.assertEquals(driver.findElement(By.cssSelector("#advice-required-entry-email")).getText(), "This is a required field.");
			Assert.assertEquals(driver.findElement(By.cssSelector("#advice-required-entry-pass")).getText(), "This is a required field.");
			
	}
			@Test
			public void Login_02_Invalid_Email() {
			driver.get("http://live.techpanda.org/");
			// Click vào My Account
		    driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
			sleepInSecond(2);
			
			driver.findElement(By.id("email")).sendKeys("123434234@1232.123123");
			driver.findElement(By.id("pass")).sendKeys("123456");
			
			driver.findElement(By.id("send2")).click();
			sleepInSecond(2);
					
			Assert.assertEquals(driver.findElement(By.cssSelector("#advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");	
	}
			
	        @Test
			public void Login_03_Pasword_Less_Than_6chars() {
	        	driver.get("http://live.techpanda.org/");
				// Click vào My Account
			    driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
				sleepInSecond(2);
				
				driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
				driver.findElement(By.id("pass")).sendKeys("123");
				
				driver.findElement(By.id("send2")).click();
				sleepInSecond(2);
						
				Assert.assertEquals(driver.findElement(By.cssSelector("#advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	        	
			}
			
			@Test
			public void Login_04_Incorrect_Email() {
				driver.get("http://live.techpanda.org/");
				// Click vào My Account
			    driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
				sleepInSecond(2);
				
				driver.findElement(By.id("email")).sendKeys(emailAdress);
				driver.findElement(By.id("pass")).sendKeys("123456");
				
				driver.findElement(By.id("send2")).click();
				sleepInSecond(2);
						
				Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(), "Invalid login or password.");		
			}
			
			@Test
			public void Login_05_Create_New_Account () {
				driver.get("http://live.techpanda.org/");
				// Click vào My Account
			    driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
				sleepInSecond(2);
				
				driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
				sleepInSecond(2);
				 
				driver.findElement(By.id("firstname")).sendKeys(firtName);
				driver.findElement(By.id("lastname")).sendKeys(LastName);
				driver.findElement(By.id("email_address")).sendKeys(emailAdress);
				driver.findElement(By.id("Password")).sendKeys(Password);
				driver.findElement(By.id("confirmation")).sendKeys(Password);
				
				driver.findElement(By.xpath("//div[@class='footer']//button[@title='Register']")).click();
				
				Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"Thank you for registering with Main Website Store.");
				
				String ContactInformationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
				System.out.println(ContactInformationText);
				
				Assert.assertTrue(ContactInformationText.contains(FullName));
				Assert.assertTrue(ContactInformationText.contains(emailAdress));
				
				driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
				driver.findElement(By.xpath("//a[@title='Log Out']")).click();
				
				Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
			}
			
			@Test
			public void Login_06_Login_Valid_Infor() {
				driver.get("http://live.techpanda.org/");
				// Click vào My Account
			    driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
				sleepInSecond(1);
				
				driver.findElement(By.id("email")).sendKeys(emailAdress);
				driver.findElement(By.id("pass")).sendKeys(Password);
				
				driver.findElement(By.id("send2")).click();
				sleepInSecond(1);
				
				String ContactInformationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
				System.out.println(ContactInformationText);
				
				Assert.assertTrue(ContactInformationText.contains(FullName));
				Assert.assertTrue(ContactInformationText.contains(emailAdress));
				
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