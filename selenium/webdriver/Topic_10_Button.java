package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_10_Button {
	WebDriver driver;
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		
	By loginButton = By.cssSelector("button.fhs-btn-login");
	
	// Verify login button is Enabled
	driver.findElement(loginButton).isEnabled();
	
	Assert.assertFalse(driver.findElement(loginButton).isEnabled());
	
	String loginButtonbackground = driver.findElement(loginButton).getCssValue("background-image");
	Assert.assertTrue(loginButtonbackground.contains("rgb(224, 224, 224)"));
	
	driver.findElement(By.cssSelector("input#login_username")).sendKeys("0987665544");
	driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456789");
	sleepInSecond(2);
	
	loginButtonbackground = driver.findElement(loginButton).getCssValue("background-color");
	Color loginButtonBackgroundColour = Color.fromString(loginButtonbackground);
	Assert.assertEquals(loginButtonBackgroundColour.asHex().toUpperCase(), "#C92127");
	
	System.out.println(loginButtonbackground);
	}

	@Test
	public void TC_02_Default_Checkbox_Radio() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		// click chọn 1 checkbox
		driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).click();
		
		// click chọn 1 radio
		driver.findElement(By.xpath("//label[contains(text(),\"I don't drink \")]/preceding-sibling::input")).click();
		
		// Verify checkbox/ radio đã chọn rồi
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),\"I don't drink \")]/preceding-sibling::input")).isSelected());

		// checkbox có thể bỏ chọn được
		driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).click();
		
		// Verify các checkbox đã bỏ chọn
		Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).isSelected());
		
		// Radio không thể tự bỏ chọn đươc
		driver.findElement(By.xpath("//label[contains(text(),\"I don't drink \")]/preceding-sibling::input")).click();
        
		// Verify radio vẫn được chọn rồi
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),\"I don't drink \")]/preceding-sibling::input")).isSelected());

		
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