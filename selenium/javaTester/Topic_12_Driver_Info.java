package javaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Topic_12_Driver_Info {
WebDriver driver;
    @Test
    public void testDriverInformation() {
        driver = new FirefoxDriver();
      // Ở trên OS nào
      // Browser nào
      // Browser class
      // ID của driver là gì
System.out.println(driver.toString());
if (driver.toString().contains("firefox")){
    // Scroll
}


driver.quit();
    }
}
