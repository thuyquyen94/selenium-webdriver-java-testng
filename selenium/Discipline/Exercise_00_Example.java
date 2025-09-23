package Discipline;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Exercise_00_Example {

    // 1- Set up : OS/ Browser/ Web/ Page/ Data/ Variable/ Object...
    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();
        driver.get("https://demo.nopcommerce.com");
    }

    // 2- Action/ Execute: Tương tác lên Element nào/ nhập liệu/ verify...
    @Test
    public void TC_01_Register(){

    }
    @Test
    public void TC_02_Login(){

    }

    // 3- Clean: Delete data test/ account/ close browser...
    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}
