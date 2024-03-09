package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class Topic_28_Wait_07_Explicit_03 {
	WebDriver driver;
	WebDriverWait explicitWait;

	String projectPath = System.getProperty("user.dir");

	String fightingName = "fighting.jpg";
	String strongName = "strong.jpg";
	String skyName = "sky.jpg";

	String fightingFilePath = projectPath + File.separator + "UploadFiles" + File.separator + fightingName;
	String strongFilePath = projectPath + File.separator + "UploadFiles" + File.separator + strongName;
	String skyFilePath = projectPath + File.separator + "UploadFiles" + File.separator + skyName;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver,Duration.ofSeconds(50));

	}

	@Test
	public void TC_01_AjaxLoading() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		By slectedDateBy = By.cssSelector("span#ctl00_ContentPlaceholder1_Label1");
		Assert.assertEquals(driver.findElement(slectedDateBy).getText(),"No Selected Dates to display.");

		driver.findElement(By.xpath("//a[text()='12']")).click();

		// Wait cho Loading Icon biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));

		Assert.assertEquals(driver.findElement(slectedDateBy).getText(),"Tuesday, March 12, 2024");
	}

	@Test
	public void TC_02_Upload_File() {
		driver.get("https://gofile.io/welcome");

		// Wait + Verify Spinner icon biến mất
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border"))));

		// Wait + Click
		 explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.ajaxLink>button.btn-lg"))).click();

		// Wait + Verify Spinner icon biến mất
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));

		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(fightingFilePath + "\n" + strongFilePath + "\n" + skyFilePath);

		// Wait + Verify Spinner icon biến mất
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));

		// Wait cho Progress Bar biến mất
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress")))));

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink"))).click();

		// Wait + Verify button Play có tại từng hình được upload
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + fightingName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + skyName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + strongName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']"))).isDisplayed());

		// Wait + Verify button Download có tại từng hình được upload
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + fightingName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + skyName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + strongName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());

	}
	@Test
	public void TC_03_Greater_Than_5_Second() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}