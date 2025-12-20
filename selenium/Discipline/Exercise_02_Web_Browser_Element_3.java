package Discipline;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Exercise_02_Web_Browser_Element_3 {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();
        driver.get("http://live.techpanda.org/");
    }

    @Test
    public void Login_01_Empty_Email_Password(){
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).
                getText(),"This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).
                getText(),"This is a required field.");
    }
    @Test
    public void Login_02_Invalid_Email(){
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.cssSelector("button#send2")).click();

        driver.findElement(By.cssSelector("input#email")).sendKeys("123456@1224445");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).
                getText(),"Please enter a valid email address. For example johndoe@domain.com.");

    }
    @Test
    public void Login_03_Invalid_Password(){
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.cssSelector("button#send2")).click();

        driver.findElement(By.cssSelector("input#email")).sendKeys("automationtest@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("12");
        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).
                getText(),"Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void Login_04_Incorrect_Email_Password(){
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.cssSelector("button#send2")).click();

        driver.findElement(By.cssSelector("input#email")).sendKeys("automationtest@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("12123123123123");
        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).
                getText(),"Invalid login or password.");
    }


    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}