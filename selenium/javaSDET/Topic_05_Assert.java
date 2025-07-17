package javaSDET;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_05_Assert {
    WebDriver driver;

    @Test
    public void verifyTestNG(){
        driver = new FirefoxDriver();
        driver.get("https://www.facebook.com/");

        // Trong java có rất nhiều thư viện để verify dữ liệu
        // Testing FrameWork (Unit, Intergration, UI Automation Test)
        // JUNit 4/ TestNG/ JUnit 5/ Hamcrest/ AssertJ...

        // Kiểu dữ liệu nhận vào là boolean(true/ false)
        // Khi mong muốn điều kiện trả về là đúng thì dùng AssertTrue để verify
        Assert.assertTrue(driver.getPageSource().contains("Facebook helps you connect and share with the people in your life."));

        // Mong muốn điều kiện trả về là sai thì dùng assertFalse
        Assert.assertFalse(driver.getPageSource().contains("Create new account"));

        // Các hàm trả về KDL boolean
        // Bắt đầu với tiền tố là isXXX
        // WebElement
        driver.findElement(By.id("")).isDisplayed();
        driver.findElement(By.id("")).isEnabled();
        driver.findElement(By.id("")).isSelected();
       new Select(driver.findElement(By.id(""))).isMultiple();

        // Mong đợi điều kiện giống thực tế
        // Actual = Expected
       Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/");

       // Unit Test
        Object name = null;
        Assert.assertNull(name);

    }
}
