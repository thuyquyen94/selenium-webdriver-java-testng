package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_TextArea_restudy {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();

    }


    @Test
    public void TC_01_Textbox_TextArea(){

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
