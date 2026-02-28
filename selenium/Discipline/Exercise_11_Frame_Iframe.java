package Discipline;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Exercise_11_Frame_Iframe {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// driver = new FirefoxDriver();
		driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Iframe_FormSite() {
		// Trang HTML A
		driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

		driver.findElement(By.cssSelector("img[alt='Campus Safety Survey']")).click();
		sleepInSecond(3);
		// Switch qua iframe có 3 cách:
		// Sử dụng index khi: page hiện tại có nhiều iframe/frame
		// Frame/ iframe dầu tiên sẽ có index = 0
		// Khi thêm mới/ update lại/ xóa bớt đi thì đổi index của các iframe/frame
		// driver.switchTo().frame("0");
		// Sử dụng ID or name: phù hợp với page có frame/iframe có id or name
		// Nếu page không có id/name thì không dùng được
		// driver.switchTo().frame("iframe#frame-one85593366");

		// WebElement: có thể cover được 2 cách trên
		driver.switchTo().frame(driver.findElement(By.cssSelector("div#formTemplateContainer>iframe")));
		// Element thuộc trang HTML B
	    new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
		new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3"))).selectByVisibleText("South Dorm");
		driver.findElement(By.xpath("//label[text()='Male']")).click();
		sleepInSecond(3);
		// Từ B quay lại page A
		driver.switchTo().defaultContent();
		// driver quay lại trang A rồi
		driver.findElement(By.cssSelector("a.menu-item-login.fs-btn--transparent-kashmir")).click();
		driver.findElement(By.cssSelector("button#login")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(),"Username and password are both required.");

	}

	@Test
	public void TC_02_Iframe_Toidicodedao() {
		driver.get("https://toidicodedao.com/");

		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fb_iframe_widget iframe")));

		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Tôi đi code dạo']/parent::div/following-sibling::div[text()]")).getText(),
				"399,457 followers");
	}

	@Test
	public void TC_03_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");

		// Switch vào Frame
		driver.switchTo().frame("login_page");

		driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("luis_suarez");
		sleepInSecond(3);

		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(3);

		// Switch về Page trước đó
		driver.switchTo().defaultContent();

		// Verify password hiển thị
		Assert.assertTrue(driver.findElement(By.cssSelector("input#keyboard")).isDisplayed());

		driver.findElement(By.cssSelector("input#keyboard")).sendKeys("123456789");
		sleepInSecond(3);

	
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