package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_04_Run_On_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test
	public void TC_01_Run_Chrome() {
	//chrome
		System.setProperty("WebDriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("https://demo.nopcommerce.com/");
		
	}

	@Test
	public void TC_02_Run_FireFox() {
		//Firefox
        System.setProperty("WebDriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	    driver = new FirefoxDriver();
	    
        driver.get("https://demo.nopcommerce.com/");
		
	}

	@Test
	public void TC_03_Run_Edge() {
		// Edge
		 System.setProperty("WebDriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		 driver = new EdgeDriver();
		 
		 driver.get("https://demo.nopcommerce.com/");
			
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