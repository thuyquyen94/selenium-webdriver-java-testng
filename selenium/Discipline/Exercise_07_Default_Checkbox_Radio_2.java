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

public class Exercise_07_Default_Checkbox_Radio_2 {
	WebDriver driver;

	@BeforeClass
	public void initialBrowser() {
		driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Telerik() {

		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		// Scroll xuống thêm 1 đoạn 300 px
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");

	    // Verify checkbox/ radio is enabled/ disabled
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span//input")).isEnabled());
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Towbar preparation']/preceding-sibling::span//input")).isEnabled());

		// Verify checkbox/ radio is selected/ deselected
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span//input")).isSelected());
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Towbar preparation']/preceding-sibling::span//input")).isSelected());

		By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span//input");

		// click select Dual - zone air conditioning
		// Lúc mở page ra không biết đang select/ deselect
		// Trước khi click mình cần kiểm tra trước rồi mới thao tác lên
		// ! trong lập trình mang nghĩa phủ định
		// Nếu như chưa chọn thì mới click
		if (!driver.findElement(dualZoneCheckbox).isSelected()) {
			driver.findElement(dualZoneCheckbox).click();
		}
		Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());

		// deselected  Dual - zone air conditioning (bỏ chọn)
		// Nếu như nó đang chọn thì mình bỏ chọn đi bằng click lại 1 lần nữa
		if (driver.findElement(dualZoneCheckbox).isSelected()) {
			driver.findElement(dualZoneCheckbox).click();
		}
		Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());

		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");

		By twoPetroRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span//input");

		if (!driver.findElement(twoPetroRadio).isSelected()) {
			driver.findElement(twoPetroRadio).click();
		}
		Assert.assertTrue(driver.findElement(twoPetroRadio).isSelected());


	}

	@Test
	public void TC_02_Multiple() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		// Select all checkboxes
		List<WebElement> checkboxes = driver.findElements(By.cssSelector("span.form-checkbox-item>input"));

		// Click all checkboxes
		for (WebElement checkbox : checkboxes){
			if (!checkbox.isSelected()){
				checkbox.click();
			}
		}
		// Verify all checkboxes selected
		for (WebElement checkbox : checkboxes){
			Assert.assertTrue(checkbox.isSelected());
		}
		// Deselect all checkboxes
		for (WebElement checkbox : checkboxes){
			if (checkbox.isSelected()){
				checkbox.click();
			}
		}
		// Verify all checkboxes deselected
		for (WebElement checkbox : checkboxes){
			Assert.assertFalse(checkbox.isSelected());
		}
		// Select 1 in all + verify
		driver.findElement(By.cssSelector("input[value='Ulcerative Colitis']")).click();
		driver.findElement(By.cssSelector("input[value='High Blood Pressure']")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("input[value='Ulcerative Colitis']")).isSelected());
		Assert.assertTrue(driver.findElement(By.cssSelector("input[value='High Blood Pressure']")).isSelected());

		// Select 1 in all + verify
		for (WebElement checkbox : checkboxes){
			if (!checkbox.isSelected() && checkbox.getAttribute("value").equals("Heart Attack")) {
				checkbox.click();
			}
		}
		Assert.assertTrue(driver.findElement(By.cssSelector("input[value='Heart Attack']")).isSelected());
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