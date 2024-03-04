package webdriver;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v85.cachestorage.model.Header;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Topic_12_Alert {
	WebDriver driver;
	WebDriverWait epliciWait;

	String projectLocation = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	By ResultText = By.cssSelector("p#result");
	String username = "admin";
	String password = "admin";

	@BeforeClass
	public void beforeClass() {

		driver = new ChromeDriver();

		epliciWait = new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test
	public void TC_01_Accept_Alert() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	
	driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
	sleepInSecond(2);
	
	// Chờ cho alert present
	// Nếu trong thời gian chờ mà xuất hiện thì tự nó switch vào
	// Nếu hết thời gian chờ mà chưa xuất hiện mới failure

		Alert alert = epliciWait.until(ExpectedConditions.alertIsPresent());
	    sleepInSecond(2);

		alert.accept();
		sleepInSecond(3);
	Assert.assertEquals(driver.findElement(ResultText).getText(), "You clicked an alert successfully");


	}

	@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(2);

		Alert alert = epliciWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "I am a JS Confirm");

		alert.dismiss();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(ResultText).getText(), "You clicked: Cancel");
	}

	@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(2);

		Alert alert = epliciWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "I am a JS prompt");

		String text = "Selenium WebDriver";
		alert.sendKeys(text);
		sleepInSecond(2);

		alert.accept();
		 Assert.assertEquals(driver.findElement(ResultText).getText(), "You entered: " + text);
	}
	
	@Test
	public void TC_04_Authentication_Alert_Pass_URL() {

		// tạo 2 biến để khi chỉnh sửa 1 trong 2 dữ liệu sẽ không ảnh hưởng cái kia.
		// thư viện Alert không sử dụng cho Authentication Alert được, liên quan đến bảo mật
		// Chrome Dev Tool Protocol (CDP) - Chrome/ Edge (Chromium)
		// Cách 1: truyền thẳng user/pass vào url
		driver.get("http://" +username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

		// Cách 2: Từ page A thao tác lên 1 element nó sẽ qua page B (cần thao tác Authen Alert trước)
		driver.get("http://the-internet.herokuapp.com/");

		String authenlinkUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
     // http://the-internet.herokuapp.com/basic_auth

		String[] authenArray = authenlinkUrl.split("//");
		System.out.println(authenArray[0]);
		System.out.println(authenArray[1]);

		driver.get(authenArray[0] + "//" + username + ":" + password + "@" + authenArray[1]);

		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
		@Test
		public void TC_05_Authentication_Alert_AutoIT() throws IOException {
    // Cách 2: Chạy trên Window với AutoIT
    // Thực thi đoạn code autoIT chờ Alert xuất hiện
			//Runtime.getRuntime().exec(new String[]{ projectLocation +  " \\autoIT\\authen_firefox.exe", "admin", "admin"});

			//driver.get("http://the-internet.herokuapp.com/basic_auth");
			//sleepInSecond(5);

		// Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	//Cách 3:
	// Thư viện Alert không sử dụng cho Authentication Alert được
	// Chrome Dev Tool Protocol (CDP) - Chrome/ Edge (Chromium)

	//@Test
	public void TC_06_Authentication_Alert_Selenium_4x() {
		// cách 3:
		// Thư viện Alert không sử dụng cho Authentication Alert được
		// Chrome Dev Tool Protocol (CDP) - Chrome/ Edge (Chromium)
		// Cốc cốc/Opera - Work Around

		// Get DevTool objiect
		// Get DevTool object
		DevTools devTools = ((HasDevTools) driver).getDevTools();

		// Start new session
		devTools.createSession();

		// Enable the Network domain of devtools
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

		// Encode username/ password
		Map<String, Object> headers = new HashMap<String, Object>();
		String basicAuthen = "Basic " + new String(new Base64().encode(String.format("%s:%s", username, password).getBytes()));
		headers.put("Authorization", basicAuthen);

		// Set to Header
		//devTools.send(Network.setExtraHTTPHeaders(new Header((headers)));

		driver.get("https://the-internet.herokuapp.com/basic_auth");

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