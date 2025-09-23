package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Topic_02_Selenium_Locator {
    // Khai báo
    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){

        // Khởi tạo biến driver lên
        driver = new FirefoxDriver();

        // Mở app lên đến màn hình login
        driver.get("https://demo.nopcommerce.com/login");
    }

    @Test
    public void TC_01_ID() throws InterruptedException {
        driver.findElement(By.id("small-searchterms")).sendKeys("Macboook");
            Thread.sleep(3000);

        driver.findElement(By.id("FirstName")).sendKeys("Automation");
        Thread.sleep(3000);

            }

    @Test
    public void TC_02_Class() throws InterruptedException {
        // Nó không lấy hết toàn bộ giá trị (nếu có khoảng trắng)
        driver.findElement(By.className("register-next-step-button")).click();
        Thread.sleep(3000);
    }

   // @Test
    public void TC_03_Name(){

    }

    @Test
    public void TC_04_LinkText(){
        // Chỉ làm việc với element là link và có text
        // thẻ a và có thuộc tính href
        // Phải lấy hết toàn bộ text không chừa cái nào hết(tuyệt đối)

        driver.findElement(By.linkText("Register"));

        driver.findElement(By.linkText("Log in"));


    }

    @Test
    public void TC_05_Partial_Link_Text(){
        // Chỉ làm việc với element là link
        // Có thể lấy toàn bộ text or 1 phần (hay dùng)
        driver.findElement(By.partialLinkText("Digital downloads "));

        driver.findElement(By.partialLinkText("Digital"));



    }

    @Test
    public void TC_06_Tagname(){
        // Tên thẻ (HTML)
        // Tìm tất cả các element giống nhau (thẻ của component giống nhau)
        // Tất cả các textbox/ checkbox/ radio/ link/ button...

        driver.findElements(By.tagName("button"));
        driver.findElements(By.tagName("input"));

    }

    @Test
    public void TC_07_Css(){
        driver.findElement(By.cssSelector("input#Company"));
        driver.findElement(By.cssSelector("input[id='Company']"));


    }

    @Test
    public void TC_08_Xpath(){
        driver.findElement(By.xpath("//input[@id='small-searchterms']"));

        driver.findElement(By.xpath("//button[@class='button-1 register-next-step-button']"));

    }

    @Test
    public void TC_09_Relative_Locator(){
        // Element / By A
        By passwordTextbox = By.cssSelector("input#Password");

        // Element / By B
        By rememberCheckboxBy = By.id("RememberMe");

        // Element / By C
        By forgotPasswordLinkBy = By.cssSelector("span.forgot-password");

        // Element / By D
        By loginButtonBy = By.cssSelector("button.login-button");

        // Element / By E
       WebElement rememberMeLabelText =  driver.findElement(RelativeLocator.with(By.tagName("label"))
                .above(loginButtonBy) // Login đang nằm trên Login Button
                .below(passwordTextbox) // Label nằm dưới password textbox
                .toRightOf(rememberCheckboxBy) // Label nằm bên phải so với RememberMe Checkbox
                .toLeftOf(forgotPasswordLinkBy) // Label nằm bên trái so với Forgot Password Link
        );

       // 1- Sử dụng test khi không định danh được element của chính nó (dựa vào những cái vị trí bên cạnh/ gần nó)
        // 2- Sử dụng để test GUI (giao diện - position khớp với design)
}

    // 3- Clean: Delete data test/ account/ close browser...
    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }

}
