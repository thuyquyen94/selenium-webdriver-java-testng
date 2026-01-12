package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_12_JS_Authentication_Alert {
    WebDriver driver;
    WebDriverWait explicitWait;
    String username = "admin";
    String password = "admin";

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }


    @Test
    public void TC_01_Accept_Alert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Alert alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(),"I am a JS Alert");
        alert.accept();
        // Verify khi đã thao tác với alert thành công
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked an alert successfully");

    }
    @Test
    public void TC_02_Confirm_Alert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        // Vừa wait cho cái alert present vừa switch qua
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"I am a JS Confirm");
        alert.dismiss();

        // Verify khi đã thao tác với alert thành công
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked: Cancel");

    }

    @Test
    public void TC_03_Prompt_Alert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        // Vừa wait cho cái alert present vừa switch qua
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"I am a JS prompt");

        String vaule = "AutomationFC";
       alert.sendKeys(vaule);
        alert.accept();
        // Verify khi đã thao tác với alert thành công
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You entered: " + vaule);
    }

    @Test
    public void TC_04_Authentication_URL(){
        // http/ https:// + username + : + password + @ URL
        driver.get("http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");

        Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(),
                "Congratulations! You must have the proper credentials.");
    }


    @Test
    public void TC_05_Authentication_Navigate(){
        // http/ https:// + username + : + password + @ URL
        driver.get("http://the-internet.herokuapp.com/");

        String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).
                getAttribute("href");

        driver.get(getAuthenticationURL(basicAuthenLink, username, password));

        Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(),
                "Congratulations! You must have the proper credentials.");
    }

    public String getAuthenticationURL (String link, String username, String password){
        String [] linkArray = link.split("//");
      return linkArray[0] + "//" + username + ":" + password + "@" + linkArray[1];

    }


    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @AfterClass
    public void cleanBrowser(){
       // driver.quit();
    }
}
