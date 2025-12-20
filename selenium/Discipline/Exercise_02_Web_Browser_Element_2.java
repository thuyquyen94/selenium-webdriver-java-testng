package Discipline;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.time.Duration;

public class Exercise_02_Web_Browser_Element_2 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test
	public void TC_01_Displayed() {
		// is Displayed : kiểm tra bất kỳ 1 element nào hiển thị
		// Hiển thị : có thể nhìn thấy, có kich thước cụ thể

		driver.get("https://automationfc.github.io/basic-form/index.html");

		WebElement EmailTextbox = driver.findElement(By.cssSelector("input#mail"));
		if (EmailTextbox.isDisplayed()) {
			System.out.printf("Element is displayed");
			EmailTextbox.sendKeys("Automation Testing");
		} else {
			System.out.println("Element is not displayed");
		}

		WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));
		if (ageUnder18Radio.isDisplayed()) {
			System.out.println("Age Under 18 Radio is displayed");
			ageUnder18Radio.click();
		} else {
			System.out.println("Age Under 18 Radio is not displayed");
		}

		WebElement EducationTextArea = driver.findElement(By.cssSelector("textarea#edu"));
		if (EducationTextArea.isDisplayed()) {
			System.out.println("Education is displayed");
			ageUnder18Radio.click();
		} else {
			System.out.println("Education is not displayed");
		}

		WebElement user5Text = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		if (user5Text.isDisplayed()) {
			System.out.println("User 5 Text is displayed");
			user5Text.sendKeys("Automation Testing");
		} else {
			System.out.println("User 5 Text is not displayed");
		}
	}

	@Test
	public void TC_02_isEnabled() {
		// is Enabled : kiểm tra bất kỳ 1 element nào có thể tương tác lên được # ngược lại với read-only

		driver.get("https://automationfc.github.io/basic-form/index.html");

		WebElement EmailTextbox = driver.findElement(By.cssSelector("input#mail"));
		if (EmailTextbox.isEnabled()) {
			System.out.printf("Element is enabled");
		} else {
			System.out.println("Element is disabled");
		}

		WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));
		if (ageUnder18Radio.isEnabled()) {
			System.out.println("Age Under 18 Radio is Enabled");
		} else {
			System.out.println("Age Under 18 Radio is disabled");
		}

		WebElement passwordTextbox = driver.findElement(By.cssSelector("input#radio-disabled"));
		if (passwordTextbox.isEnabled()) {
			System.out.println("Password text box is Enabled");
		} else {
			System.out.println("Password text box is disabled");
		}

		WebElement bioGraphyTextArea = driver.findElement(By.cssSelector("textarea#bio"));
		if (bioGraphyTextArea.isEnabled()) {
			System.out.println("Biography textarea is Enabled");
		} else {
			System.out.println("Biography textarea is disabled");
		}
	}

	@Test
	public void TC_03_Selected() {
		//is Selected : Kiểm tra 1 element được chọn thành công (Radio/ checkbox/ Dropdown )

		driver.get("https://automationfc.github.io/basic-form/index.html");

		WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));
		if (ageUnder18Radio.isSelected()){
			System.out.println("Age Under 18 radio is selected");
		} else {
			System.out.println("Age Under 18 radio is de- selected");
		}

		WebElement interestCheckbox = driver.findElement(By.cssSelector("input#development"));
		if (interestCheckbox.isSelected()){
			System.out.println("Interest checkbox is selected");
		} else {
			System.out.println("Interest checkbox is de- selected");
		}

		ageUnder18Radio.click();
		interestCheckbox.click();
		// sleepInSecond(3000);

		if (ageUnder18Radio.isSelected()){
			System.out.println("Age Under 18 radio is selected");
		} else {
			System.out.println("Age Under 18 radio is de- selected");
		}

		if (interestCheckbox.isSelected()){
			System.out.println("Interest checkbox is selected");
		} else {
			System.out.println("Interest checkbox is de- selected");
		}
	}

	@Test
	public void TC_04_MailChimp_Register_Validate() {
		driver.get("https://login.mailchimp.com/signup/");

		driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.cssSelector("input#email")).sendKeys(Keys.TAB);

		// Only number
		driver.findElement(By.id("new_password")).sendKeys("12345");
		driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check not-completed']")).isDisplayed());

		// Only low text
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("testing");
		driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);

		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check not-completed']")).isDisplayed());

		// Only upper text
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("TESTING");
		driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);

		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check not-completed']")).isDisplayed());

		// Only special chars
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("!@@#!!#$");
		driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);

		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check not-completed']")).isDisplayed());

		// contains username
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("automation");
		driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);

		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check not-completed']")).isDisplayed());

		// full
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("testing123@@#!!#$");
		driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);

		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());


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