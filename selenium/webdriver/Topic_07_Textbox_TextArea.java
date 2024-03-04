package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_07_Textbox_TextArea {
	WebDriver driver;
	Random rand = new Random();
		String osName = System.getProperty("os.name");
	String employeeID = String.valueOf(rand.nextInt(99999));
	String passportnumber = "111-222-333-444";
	String commentInput = "this is a generated data\nof real people";
	

	@BeforeClass
	public void beforeClass() {


		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test
	public void TC_01_Create_New_Employee() {
	driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	
	driver.findElement(By.name("username")).sendKeys("Admin");
	driver.findElement(By.name("password")).sendKeys("admin123");
	driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
	sleepInSecond(3);
	
	driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");
	driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
	sleepInSecond(3);
	
	driver.findElement(By.name("firstName")).sendKeys("Automation");
	driver.findElement(By.name("lastName")).sendKeys("FC");
	
	WebElement employeeIDTextbox = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"));
	
	employeeIDTextbox.sendKeys(Keys.chord(Keys.CONTROL, "a"));
	employeeIDTextbox.sendKeys(Keys.DELETE);
	employeeIDTextbox.sendKeys(employeeID);
	
	driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div//span")).click();
	sleepInSecond(3);
	
	driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys("afc" + employeeID);
	driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys("Password123!!!");
	driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys("Password123!!!");
    driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
    sleepInSecond(8);
    
    Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "Automation");
    Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"),"FC");
    Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
    
    driver.findElement(By.xpath("//a[text()='Immigration']")).click();
    sleepInSecond(5);
    
    driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
    driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportnumber);
    driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(commentInput);
    driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
    sleepInSecond(7);
    
    driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
    sleepInSecond(3);
    
    Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportnumber);
    Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), commentInput);
    
    driver.findElement(By.cssSelector("p.oxd-userdropdown-name")).click();
    driver.findElement(By.xpath("//a[text()='Logout']")).click();
    sleepInSecond(3);
    
    driver.findElement(By.name("username")).sendKeys("afc" + employeeID);
	driver.findElement(By.name("password")).sendKeys("Password123!!!");
	driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

	sleepInSecond(5);
	
	Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "Automation");
    Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"),"FC");
    Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
    
    driver.findElement(By.xpath("//a[text()='Immigration']")).click();
    sleepInSecond(5);
    
    Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "Automation");
    Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"),"FC");
    Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
    
    driver.findElement(By.xpath("//a[text()='Immigration']")).click();
    sleepInSecond(5);
    
    driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
    sleepInSecond(3);
    
    Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportnumber);
    Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), commentInput);
    
	}

	@Test
	public void TC_02_Verify_Employee() {
		
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
		// driver.quit();
	}
}