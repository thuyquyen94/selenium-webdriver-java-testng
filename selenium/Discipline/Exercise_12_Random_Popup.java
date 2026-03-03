package Discipline;

import com.sun.source.tree.IfTree;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Exercise_12_Random_Popup {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_VNK_Edu(){
        driver.get("https://vnk.edu.vn/");

        By marketingPopupBy = By.cssSelector("div.popmake-content");

        // Hiển thị close đi rồi action tiếp
        if (driver.findElements(marketingPopupBy).size() > 0
            && driver.findElements(marketingPopupBy).get(0).isDisplayed()){
            System.out.println(".......GO TO IF........");
            driver.findElement(By.cssSelector("div.popmake-content~button")).click();
            sleepInSecond(2);
        }

        // Không hiển thị thì action tiếp
        // Để chắc chắn nó có thể qua step tiếp theo được
        System.out.println(".......IGNORE IF.......");
        driver.findElement(By.xpath("//ul[@id='mega-menu-primary']//a[text()='Liên hệ']")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div.title-content>h1")).isDisplayed());

    }
    @Test
    public void TC_02_DEHIEU(){
        driver.get("https://dehieu.vn/");

        By contentPopupBy = By.cssSelector("div.modal-content");
        // Hiển thị close đi rồi action tiếp
        if (driver.findElements(contentPopupBy).size() > 0
                && driver.findElements(contentPopupBy).get(0).isDisplayed()){
            System.out.println(".......GO TO IF........");
            driver.findElement(By.cssSelector("div.modal-content Button.close")).click();
            sleepInSecond(2);
        }

        // Không hiển thị thì action tiếp
        // Để chắc chắn nó có thể qua step tiếp theo được
        System.out.println(".......IGNORE IF.......");

        driver.findElement(By.cssSelector("input.search-form")).sendKeys("Khóa học Lập dự toán M&E");
        driver.findElement(By.cssSelector("button.header-search")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.course-item-detail a")).
                getAttribute("tittle"),"Khóa học Lập dự toán M&E");



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
        driver.quit();
    }
}
