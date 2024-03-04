package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_11_Checkbox_Radio {
	WebDriver driver;
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	 @Test
	public void TC_01_Default_Telerik_Checkbox() {
		 driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

		 By rearSideCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");
		 By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");

		 // Chọn 2 checkbox này
		 // Case 1: Nếu như app này mở ra mà checkbox đã được chọn thì sao
		 checkToElement(rearSideCheckbox);

		 // Case 2: Nếu như app này mở ra mà checkbox chưa được chọn
		 checkToElement(dualZoneCheckbox);

		 // Verify checkbox đã được chọn thành công
		 Assert.assertTrue(driver.findElement(rearSideCheckbox).isSelected());
		 Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());

		 // Bỏ chọn 2 checkbox này
		 uncheckToElement(rearSideCheckbox);
		 uncheckToElement(dualZoneCheckbox);

		 // Verify checkbox đã được bỏ chọn thành công
		 Assert.assertFalse(driver.findElement(rearSideCheckbox).isSelected());
		 Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());
	 }

	 @Test
	public void TC_02_Default_Telerik_Radio() {
		 driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

		 By twoPetrolRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
		 By twoDieselRadio = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input");

		 // Click chọn 1 trong 2
		 checkToElement(twoPetrolRadio);

		 // Verify
		 Assert.assertTrue(driver.findElement(twoPetrolRadio).isSelected());
		 Assert.assertFalse(driver.findElement(twoDieselRadio).isSelected());

		 checkToElement(twoDieselRadio);

		 // Verify
		 Assert.assertFalse(driver.findElement(twoPetrolRadio).isSelected());
		 Assert.assertTrue(driver.findElement(twoDieselRadio).isSelected());
	 }

	@Test
	public void TC_03_Select_All_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));
		// Vì có 29 dùng vòng lặp kiểm tra từng ông, bốc từng ông vô checkbox
		
		// Chọn hết tất cả các checkbox trong màn hình đó
		for (WebElement checkbox : allCheckboxes) {
			if (!checkbox.isSelected()) {
				checkbox.click();
				// sleepInSecond(1);
			}
		}
		
		// Verify hết tất cả checkbox, cũng dùng vòng lặp
		for (WebElement checkbox : allCheckboxes) {
			Assert.assertTrue(checkbox.isSelected());
			
		}
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		
		allCheckboxes = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));
		
		// Chọn 1 checkbox/ radio nào đó trong tất cả các checkbox/ radio
		for (WebElement checkbox : allCheckboxes) {
			if (checkbox.getAttribute("value").equals("Heart Attack") && !checkbox.isSelected()) {
				checkbox.click();
				// sleepInSecond(1);
			}
		}
		
		// Verify hết tất cả các checkbox
		for (WebElement checkbox : allCheckboxes) {
			if (checkbox.getAttribute("value").equals("Heart Attack")) {
			Assert.assertTrue(checkbox.isSelected());
			}else {
				Assert.assertFalse(checkbox.isSelected());
			}
		}
		
	
	}
	
	@Test
	public void TC_04_Custom_Google_Docs() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		 By CanThoRadio = By.xpath("//div[@aria-label='Cần Thơ']");
		 By QuangNamCheckbox = By.xpath("//div[@aria-label='Quảng Nam']");
		 By QuangBinhCheckbox = By.xpath("//div[@aria-label='Quảng Bình']");

		 
		// Verify radio is not selected
		 
		Assert.assertEquals(driver.findElement(CanThoRadio).getAttribute("aria-checked"), "false");
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='false']")).isDisplayed());
		
		driver.findElement(CanThoRadio).click();
		sleepInSecond(1);
		
		// Verify radio is selected
		
		Assert.assertEquals(driver.findElement(CanThoRadio).getAttribute("aria-checked"), "true");

		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='true']")).isDisplayed());

		driver.findElement(QuangNamCheckbox).click();
		driver.findElement(QuangBinhCheckbox).click();
		sleepInSecond(1);
		
		Assert.assertEquals(driver.findElement(QuangNamCheckbox).getAttribute("aria-checked"), "true");
		Assert.assertEquals(driver.findElement(QuangBinhCheckbox).getAttribute("aria-checked"), "true");

		
		
		
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

	public void checkToElement(By byXpath) {
		// Nếu như element chưa được chọn thì mới click
		if (!driver.findElement(byXpath).isSelected()) {
			driver.findElement(byXpath).click();
			sleepInSeconds(2);
		}
	}

	public void uncheckToElement(By byXpath) {
		// Nếu như element được chọn rồi thì vào click lần nữa cho nó thành bỏ chọn
		if (driver.findElement(byXpath).isSelected()) {
			driver.findElement(byXpath).click();
			sleepInSeconds(2);
		}
	}

	public void sleepInSeconds(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
 
 
 
 
 
 
 