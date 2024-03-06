package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.List;

public class Topic_22_Wait_02_Find_Element {
	WebDriver driver;

	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		// Implicit Wait
		// Set implicit cho Selenium Ver 4x trở lên
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_FindElement() {

	     // Case 1- Element được tìm thấy chỉ có 1
		// Sẽ không cần chờ hết timeout
		// Tìm thấy sẽ trả về 1 WebElement
		// Qua step tiếp theo
		//System.out.println("Start step: " + getDateTimeNow());
		//driver.findElement(By.cssSelector("input#email"));
		//System.out.println("End step: " + getDateTimeNow());

		// Case 2- Element được tìm thấy nhưng có nhiều hơn 1
		// Sẽ không chờ hết timeout
		// Lấy cái element đầu tiên dù cho có n node
		// Qua step tiếp theo
		//System.out.println("Start step: " + getDateTimeNow());
		//driver.findElement(By.cssSelector("input[type='text'],[type='password']")).sendKeys("quyen@gmail.com");
		//System.out.println("End step: " + getDateTimeNow());

		// Case 3- Element không được tìm thấy
		// Chờ hết timeout đã set (10s)
		// Trong 10s này cứ mỗi nữa s sẽ tìm lại 1 lần
		// Nếu tìm lại mà thấy thì cũng trả về element rồi qua step tiếp theo
		// Nếu không tìm thấy thì đánh fail testcase và throw exception: NoSuchElementException
		// Các step còn lại không chạy được
		System.out.println("Start step: " + getDateTimeNow());
		driver.findElement(By.cssSelector("input[name='reg_email__'"));
		System.out.println("End step: " + getDateTimeNow());
	}

	@Test
	public void TC_02_FindElements() {
		List<WebElement> elementList;
		// Case 1- Element được tìm thấy chỉ có 1
		// Sẽ không cần chờ hết timeout
		// Trả về List Element chứa đúng 1 element
		// Qua step tiếp theo
//        System.out.println("Start step: " + getDateTimeNow());
//        elementList = driver.findElements(By.cssSelector("input#email"));
//        System.out.println("List have: " + elementList.size());
//        System.out.println("End step: " + getDateTimeNow());

		// Case 2- Element được tìm thấy nhưng có nhiều hơn 1
		// Sẽ không chờ hết timeout
		// Trả về List Element chứa nhiều element
		// Qua step tiếp theo
//		System.out.println("Start step: " + getDateTimeNow());
//		driver.findElement(By.cssSelector("input[type='text'],[type='password']")).sendKeys("quyen@gmail.com");
//		System.out.println("List have: " + elementList.size());
//		System.out.println("End step: " + getDateTimeNow());

		// Case 3- Element không được tìm thấy
		// Chờ hết timeout đã set (10s)
		// Trong 10s này cứ mỗi nữa s sẽ tìm lại 1 lần (polling)
		// Nếu trong thời gian tìm lại mà thấy element thì cũng trả về List chứa các element đó
		// Nếu hết thời gian tìm lại mà không thấy thì trả về List rỗng (empty) và không đánh fail testcase
		// Qua step tiếp theo
		System.out.println("Start step: " + getDateTimeNow());
		elementList = driver.findElements(By.cssSelector("input[name='reg_email__']"));
		System.out.println("List have: " + elementList.size());
		System.out.println("End step: " + getDateTimeNow());
	}
	@Test
	public void TC_03_() {
	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
public String getDateTimeNow(){
	Date date = new Date();
	return date.toString();
}
}
