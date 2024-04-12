package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_05_WebBrowser_Commands_Excercise {
	WebDriver driver;
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test
	public void TC_01_Url() {
	driver.get("http://live.techpanda.org/");
	
	// Click vào My Account
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	sleepInSecond(2);
	
	driver.getCurrentUrl();
	Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
	
	// Click vào Create an account
	driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
	sleepInSecond(2);
	Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
	
	}

	@Test
	public void TC_02_Title() {
		driver.get("http://live.techpanda.org/");
		
		// Click vào My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
		
		driver.getCurrentUrl();
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		// Click vào Create an account
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}

	@Test
	public void TC_03_Navigate() {
       driver.get("http://live.techpanda.org/");
		
		// Click vào My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
		
		// Click vào Create an account
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSecond(2);
	    
		// Back lại
		driver.navigate().back();
		sleepInSecond(2);
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		// Forward
		driver.navigate().forward();
		sleepInSecond(2);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}

	@Test
	public void TC_04_Page_Source_HTML() {
        driver.get("http://live.techpanda.org/");
		
		// Click vào My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
		
		// verify page HTML có chứa 1 chuỗi mong muốn không?
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		// Click vào Create an account
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSecond(2);
		
		// verify page HTML có chứa 1 chuỗi mong muốn không?
	    Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
		
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