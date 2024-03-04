package webdriver;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_17_Frame_Iframe {
	WebDriver driver;
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_() {
	// Trang A có domain: formsite.com
		driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

		// Chưa iframe - trang B
		// Từ A vào B
		driver.switchTo().frame("frame-one85593366");
		driver.findElement(By.cssSelector("")).click();

        // Từ B vào C
		driver.switchTo().frame("frame-23232");
		driver.findElement(By.cssSelector("")).click();

		// Từ C quay lại B
		// parentFrame sử dụng khi trong iframe có tiếp iframe bên trong đó nữa.
		driver.switchTo().parentFrame();

		// Đang ở B

		// Từ B quay lại A
		driver.switchTo().defaultContent();
	}

	@Test
	public void TC_01_Form_Site() {
		// Trang A
	driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

	driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();
	sleepInSecond(5);

	// Iframe Element
		WebElement formIframe = driver.findElement(By.cssSelector("div#formTemplateContainer>iframe"));

		Assert.assertTrue(formIframe.isDisplayed());

		// Switch vào Frame/ iframe trước khi thao tác vào các element bên trong -> qua trang B
		driver.switchTo().frame(formIframe);

		// Không tìm thấy element (element nằm trong iframe)
		new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
		sleepInSecond(5);

		// Switch ra lại trang A
		driver.switchTo().defaultContent();

		// Thao tác 1 element bên ngoài iframe (trang A)
		driver.findElement(By.cssSelector("nav.header--desktop-floater a.menu-item-login")).click();
		sleepInSecond(5);

    driver.findElement(By.cssSelector("button#login")).click();
	sleepInSecond(3);

	Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(),"Username and password are both required.");

	}


	@Test
	public void TC_02_KynaEnglish() {
	driver.get("https://skills.kynaenglish.vn/");


	// Switch vào iframe chứa FanPage
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content>iframe")));

		// Verify followers number
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(),"177K followers");

		// Switch về trang Default/ Parent chứa iframe
		driver.switchTo().defaultContent();

	// Switch vào iframe của wechat
    driver.switchTo().frame("cs_chat_iframe");

	driver.findElement(By.cssSelector("div.button_bar")).click();
	sleepInSecond(3);

	// Nhập field
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("John Wick");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("01278945662");
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("Đăng kí khóa học JAVA");
		sleepInSecond(3);

      // Switch về trang Default/ Parent chứa iframe
		driver.switchTo().defaultContent();

		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Java");
		sleepInSecond(3);
		driver.findElement(By.cssSelector("button.search-button")).click();
     sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div.content>h4")).getText(),"Lập trình Java trong 4 tuần");

	}

	@Test
	public void TC_03_Frame_HDFC_Bank() {
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
		driver.quit();
	}

}