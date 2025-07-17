package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.time.Duration;

public class Topic_02_Selenium_Locator {
    WebDriver driver;

    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Mở trang Register ra
        driver.get("https://demo.nopcommerce.com/register");
    }

    //Code HTML của FirtName textbox
    // <input type="text" data-val="true" data-val-required="First name is required."
    // id="FirstName" name="FirstName">

    // Tên thẻ của element (Tagname HTML): input
    // Tên của thuộc tính (Attribute name): type data-val data-val-required id name
    // Giá trị của thuộc tính (Attribute value): text true First name is required. FirstName FirstName

    @Test
    public void TC_01_ID() {
        // Thao tác element thì đầu tiên tìm được element đó: findElement
        // Find theo cái gì: id/ class/ name/ css/ xpath/...
        // Find tìm thấy element rồi thì action lên element đó: click/senkey/..
        driver.findElement(By.id("FirstName")).sendKeys("Automation");
    }

    @Test
    public void TC_02_Class() {
        // Mở màn hình Search
        driver.get("https://demo.nopcommerce.com/search");

        // Nhập text vào Search textbox
        driver.findElement(By.className("search-text")).sendKeys("mackbook");
    }

    @Test
    public void TC_03_Name() {
        // Click vào Search Advanced checkbox
        driver.findElement(By.name("advs")).click();
    }

    @Test
    public void TC_04_TagName() {
        // Tên thẻ của HTML
        // Tìm tất cả các element giống nhau ( thẻ của component giống nhau)
        // Tất cả các textbox/checkbox/radio/link/button...

        System.out.println(driver.findElements(By.tagName("input")).size());

    }
    @Test
    public void TC_05_LinkText() {
        // Click vào đường link Addresses chuyền vào text tuyệt đối, chứa toàn bộ
        // Chỉ làm việc với element là link và có text
        // Thẻ a và có thuộc tính href
        // Phải lấy hết toàn bộ text không chừa cái nào hết ( lấy tuyệt đối)
        driver.findElement(By.linkText("Addresses")).click();
    }

    @Test
    public void TC_06_PartialLinkText() {
        // Click vào đường link Apply for vendor chuyền vào tương đối, không cần bắt buộc chuyển hết vào chuỗi, sẽ chạy chậm hơn
        // Chỉ làm việc với element là link
        // Có thể lấy toàn bộ text hoặc 1 phần( hay dùng)
        driver.findElement(By.partialLinkText("vendor account")).click();
    }

    @Test
    public void TC_07_Css() {
        // Mở lại trang Register
        driver.get("https://demo.nopcommerce.com/register");

        // 1
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Selenium");

        // 2
        driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("Locator");

        // 3
        driver.findElement(By.cssSelector("input[Name='Email']")).sendKeys("automation@mail.com");
    }

    @Test
    public void TC_08_XPath() {
        // Mở lại trang Register
        driver.get("https://demo.nopcommerce.com/register");

        // 1
        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Selenium");

        // 2
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Locator");

        // 3
        driver.findElement(By.xpath("//label[text()='Email:']/following-sibling::input")).sendKeys("automation@mail.com");
    }

    @Test
    public void TC_09_Relate_Locator(){
        driver.get("https://demo.nopcommerce.com/login");
        // Element/ By A
        By passwordTextboxBy = By.cssSelector("input#Password");

        WebElement passwordTextbox = driver.findElement(By.cssSelector("input#Password"));

        // Element/ By B
        By remmemberMyCheckboxBy = By.cssSelector("input#RememberMe");

        // Element/ By C
        By forgotPasswordLinkBy = By.cssSelector("span.forgot-password");

        // Element/ By D
        By loginButtonBy = By.cssSelector("button.login-button");

        // Element/ By E
        // Phần biệt trên giao diện, trên UI
        WebElement rememberMeLabelText = driver.findElement(RelativeLocator.with(By.tagName("label"))
                .above(loginButtonBy)  // Label đang nằm trên Login button
                .below(passwordTextbox) // Label nằm dưới password Textbox
                .toRightOf(remmemberMyCheckboxBy) // Label nằm bên phải so với RememberMe Checkbox
                .toLeftOf(forgotPasswordLinkBy) // Label nằm bên trái so với Password Link
        );
        // Ít sử dụng trong thực tế
        // 1- Được sử dụng khi không thể định danh Element của chính nó (dựa vào những vị trí bên cạnh/ gần đó)
        // 2- Sử dụng để test GUI (giao diện - position khớp với Design)


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