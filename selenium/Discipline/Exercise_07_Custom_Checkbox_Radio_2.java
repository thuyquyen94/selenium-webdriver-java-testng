package Discipline;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Exercise_07_Custom_Checkbox_Radio_2 {
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void initialBrowser() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Ubuntu() {
		driver.get("https://login.ubuntu.com/");

		// Thẻ input: dùng để clicK
		// Dung để verify: isSelected()

		// 1- Dùng thẻ input click=> Lỗi
		// Đùng verify => Pass
		// By newUserRadio = By.cssSelector("input#id_new_user");
		// Assert.assertFalse(driver.findElement(newUserRadio).isSelected());

		// 2- Dùng 1 thẻ khác input để click => Pass
		// dùng để verify => Fail
		// Khác input thì hàm isSlected() nó không hổ trợ
		// By newUserRadio= By.cssSelector("label.new-user");
		// driver.findElement(newUserRadio).click();
		// Assert.assertTrue(driver.findElement(newUserRadio).isSelected());

		// 3- Dùng 1 thẻ khác input để click => Pass
		// Dùng thẻ input này để verify => Pass
		// 2locator để define cho 1 element dù thỏa mãn điều kiện nhưng
		// khó cho việc bảo trì và đọc code

		// By newUserRadioLabel = By.cssSelector("label.new-user");
		// 	By newUserRadioInput = By.cssSelector("input#id_new_user");
		// driver.findElement(newUserRadioLabel).click();
		// Assert.assertTrue(driver.findElement(newUserRadioInput).isSelected());

		// 4- Dùng duy nhất thẻ input để click/verify dùng JS Executor
		By newUserRadioInput = By.cssSelector("input#id_new_user");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(newUserRadioInput));
		Assert.assertTrue(driver.findElement(newUserRadioInput).isSelected());

		By termCheckbox = By.cssSelector("input#id_accept_tos");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(termCheckbox));
		Assert.assertTrue(driver.findElement(termCheckbox).isSelected());

	}

	@Test
	public void TC_02_Docs() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

		// Dựa vào trước và sau khi check để verify
		By hcmRadio = By.xpath("//div[@aria-label='Hồ Chí Minh']");
		By quangNoodleCheckbox = By.xpath("//div[@aria-label='Mì Quảng']");

		driver.findElement(hcmRadio).click();
		Assert.assertEquals(driver.findElement(hcmRadio).getAttribute("aria-checked"), "true");

		// Kiểm tra nó chưa được check mới check
		if (driver.findElement(quangNoodleCheckbox).getAttribute("aria-checked").equals("false")){
			driver.findElement(quangNoodleCheckbox).click();
		}
		Assert.assertEquals(driver.findElement(quangNoodleCheckbox).getAttribute("aria-checked"), "true");

	  // Kiểm tra nó uncheck
		if (driver.findElement(quangNoodleCheckbox).getAttribute("aria-checked").equals("true")){
				driver.findElement(quangNoodleCheckbox).click();
		}
		Assert.assertEquals(driver.findElement(quangNoodleCheckbox).getAttribute("aria-checked"), "false");


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
	//  driver.quit();
	}
}