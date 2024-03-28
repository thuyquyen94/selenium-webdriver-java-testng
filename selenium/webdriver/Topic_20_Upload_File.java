package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Topic_20_Upload_File {
	WebDriver driver;
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Single_File() {
	driver.get("https://blueimp.github.io/jQuery-File-Upload/");

	    // Cùng 1 file đều chạy được trên MAC và Window
		// File này nằm ở đâu -> đưa về đúng thư mục trong project luôn -> nằm trong thư mục uploadfiles

		// Nếu máy khác dùng OS khác có chạy được không?
		// Fix cứng Path (đường dẫn) thì máy khác chạy không được -> nên dùng đường dẫn tương đối của file đó

		// Define biến By uploadfile
		By uploadBy = By.cssSelector("input[name='files[]']");

		// UploadFile
		driver.findElement(uploadBy).sendKeys(fightingFilePath);
		sleepInSecond(2);

		driver.findElement(uploadBy).sendKeys(strongFilePath);
		sleepInSecond(2);

		driver.findElement(uploadBy).sendKeys(skyFilePath);
		sleepInSecond(2);

		// Verify load thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fightingName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + strongName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + skyName + "']")).isDisplayed());

		List<WebElement> starButtons = driver.findElements(By.cssSelector("td>button.start"));
		for (WebElement button : starButtons){
			button.click();
			sleepInSecond(3);
		}

		// Verify uploaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + fightingName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + strongName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + skyName + "']")).isDisplayed());

	}

	@Test
	public void TC_02_Multiple_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		// Cùng 1 file đều chạy được trên MAC và Windoư
		// File này nằm ở đâu -> đưa về đúng thư mục trong project luôn -> nằm trong thư mục uploadfiles

		// Nếu máy khác dùng OS khác có chạy được không?
		// Fix cứng Path (đường dẫn) thì máy khác chạy không được -> nên dùng đường dẫn tương đối của file đó

		// Define biến By uploadfile
		By uploadBy = By.cssSelector("input[name='files[]']");

		// Upload MutipleFile
		driver.findElement(uploadBy).sendKeys(fightingFilePath + "\n" + strongFilePath + "\n" + skyFilePath);
		sleepInSecond(3);

		// Verify load thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fightingName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + strongName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + skyName + "']")).isDisplayed());

		List<WebElement> starButtons = driver.findElements(By.cssSelector("td>button.start"));
		for (WebElement button : starButtons){
			button.click();
			sleepInSecond(3);
		}

		// Verify uploaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + fightingName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + strongName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + skyName + "']")).isDisplayed());

	}
	@Test
	public void TC_03_() {
	
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