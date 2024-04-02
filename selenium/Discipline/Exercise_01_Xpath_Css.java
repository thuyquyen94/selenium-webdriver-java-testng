package Discipline;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Exercise_01_Xpath_Css {
	WebDriver driver;
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Register_01_Empty_Data() {
	driver.get("https://alada.vn/tai-khoan/dang-ky.html");

	// Action
	driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

	// Verify
		Assert.assertEquals(driver.findElement(By.cssSelector("label#txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại. ");

	}

	@Test
	public void TC_02_Register_02_Invalid_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Action
		driver.findElement(By.id("txtFirstname")).sendKeys("John Wick");
		driver.findElement(By.id("txtEmail")).sendKeys("123@2456@789");
		driver.findElement(By.id("txtCEmail")).sendKeys("123@456@789");
		driver.findElement(By.id("txtPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtPhone")).sendKeys("0702456789");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		// Verify
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
	}
	@Test
	public void TC_03_Register_03_Incorrect_Confirm_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Action
		driver.findElement(By.id("txtFirstname")).sendKeys("John Wick");
		driver.findElement(By.id("txtEmail")).sendKeys("JohnWick@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("123@456@789");
		driver.findElement(By.id("txtPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtPhone")).sendKeys("0702456789");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		// Verify
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");

	}


	@Test
	public void TC_04_Register_04_Invalid_Password(){
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Action
		driver.findElement(By.id("txtFirstname")).sendKeys("John Wick");
		driver.findElement(By.id("txtEmail")).sendKeys("ut@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("ut@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("1234");
		driver.findElement(By.id("txtCPassword")).sendKeys("1234");
		driver.findElement(By.id("txtPhone")).sendKeys("0702456789");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		// verify
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");

	}
	@Test
	public void TC_05_Register_05_Incorrect_Confirm_Password(){
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Action
		driver.findElement(By.id("txtFirstname")).sendKeys("John Wick");
		driver.findElement(By.id("txtEmail")).sendKeys("ut@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("ut@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
		driver.findElement(By.id("txtPhone")).sendKeys("0702456789");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		// verify
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu bạn nhập không khớp");
	}
	@Test
	public void TC_06_Register_06_Invalid_Phone_Number(){
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Action -1
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		driver.findElement(By.id("txtFirstname")).sendKeys("John Wick");
		driver.findElement(By.id("txtEmail")).sendKeys("ut@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("ut@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtPhone")).sendKeys("070274568");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		 // Verify - 1
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

		// Action - 2 : Phone lớn hơn 11 kí tự
		driver.findElement(By.id("txtFirstname")).sendKeys("John Wick");
		driver.findElement(By.id("txtEmail")).sendKeys("ut@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("ut@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("070274568");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		// Verify - 2
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

		// Action - 3
		driver.findElement(By.id("txtFirstname")).sendKeys("John Wick");
		driver.findElement(By.id("txtEmail")).sendKeys("ut@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("ut@gmail.com");
	    driver.findElement(By.id("txtPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("7894321");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		// Verify - 3

		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");


	}
	
	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}