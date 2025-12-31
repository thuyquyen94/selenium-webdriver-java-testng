package javaSDET;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_6_1_Wait {
    WebDriver driver;
    WebDriverWait explicitWait;
    FluentWait<WebDriver> fluentWait; // Java Generic

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        // Explicit Wait
        explicitWait= new WebDriverWait(driver, Duration.ofSeconds(30));

        // Implicit Wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Fluent Wait
        fluentWait = new FluentWait<WebDriver>(driver);
    }

    @Test
    public void TC_01_() {
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));

        // Nội bộ của testcase 1 thôi
        fluentWait = new FluentWait<WebDriver>(driver);

    }


    @Test
    public void TC_02_() throws InterruptedException {
       explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));

    }

}
