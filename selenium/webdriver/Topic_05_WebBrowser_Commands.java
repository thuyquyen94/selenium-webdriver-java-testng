package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.Logs;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Topic_05_WebBrowser_Commands {
        // Các câu lệnh để thao tác vs Browser
        // driver
        WebDriver driver;
        // Các câu lệnh thao tác vs Element
        // element
        WebElement element;

        @BeforeClass
        public void beforeClass() {
            // Muốn dùng được thì phải khởi tạo
            // Nếu ko khởi tạo sẽ gặp lỗi: NullPointerException

            driver = new FirefoxDriver();// **

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));// **
        }

        @Test
        public void TC_01_Browser() throws MalformedURLException {
            // Mở ra 1 page Url bất kì
            driver.get("https://demo.nopcommerce.com/"); // **

            // Nếu như có 1 tab/ window thì tính năng tương tự quit
            // Nhiều hơn 1 thì nó sẽ đóng cái nó đang active
            driver.close(); // *

            // Đóng browser (ko care bao nhiêu tab/ window)
            driver.quit(); // **

            // 2 hàm này sẽ bị ảnh hưởng timeout của implicitWait
            // findElement/ findElements
            // Nó sẽ đi tìm vs loại By nào và trả về element nếu như được tìm thấy (WebElement)
            // Ko được tìm thấy: Fail tại step này - throw exception: NoSuchElementException
            // Trả về 1 element - nếu như tìm thấy nhiều hơn 1 thì cũng chỉ lấy 1 (thao tác vs cái đầu tiên)
            WebElement emailAddressTextbox = driver.findElement(By.id("email")); // **

            //Nó sẽ đi tìm vs loại By nào và trả về 1 danh sách element nếu như được tìm thấy (List WebElement)
            // Ko được tìm thấy - ko bị fail - trả về 1 List rỗng (0 element)
            List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']")); // **


            // Nếu chỉ dùng 1 lần thì ko khai báo biến
            Assert.assertEquals(driver.getCurrentUrl(), "https://demo.nopcommerce.com/");

            // Lấy ra page source HTML/ CSS/ JS của page hiện tại
            // Verify 1 cách tương đối
            driver.getPageSource();
            Assert.assertTrue(driver.getPageSource().contains("The Apple and Google Play logos are trademarks of their respective owners."));

            // Lấy ra title của page hiện tại
            driver.getTitle();
            // 1- Lưu dữ liệu lại rồi kiểm tra sau
            String homePageTittle = driver.getTitle();
            Assert.assertEquals(homePageTittle, "nopCommerce demo store");
            //2- Kiểm tra trực tiếp bằng cách verify
            Assert.assertEquals(driver.getTitle(),"nopCommerce demo store");

            // Lấy ra URL cuar page hiện tại
            driver.getCurrentUrl(); //
            Assert.assertEquals(driver.getCurrentUrl(),"https://demo.nopcommerce.com/computers");

            // Lấy ra Page Source Code(toàn bộ code HTML của nó)
            driver.getPageSource();
            // Kiểm tra tính tương đối
            Assert.assertTrue(homePageTittle.contains("nopCommerce demo store"));

            // Lấy ra ID của cửa sổ/ tab đang active
            driver.getWindowHandle(); // *
            // Lấý ra tất cả ID của tất cả các tab/window đang có
            driver.getWindowHandles();// *

            // Đi tìm 1 element
            driver.findElement(By.xpath(""));
            // Đi tìm nhiều element
            driver.findElements(By.xpath(""));

            WebDriver.Timeouts timeouts = driver.manage().timeouts();
            // Selenium ver 4
            // Dùng để chờ cho việc tìm element (findelement/findelements)
            timeouts.implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

            // Chờ cho page được load xong
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));

            // Dùng để chờ cho 1 đoạn script được thực thi xong
            // JavascriptExecutor -Js // Set trước khi dùng vs thư viện JavascriptExecutor
            // Inject 1 đoạn code JS vào trong Browser/ Element
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15));

            // Chạy full màn hình (không có taskbar)
            driver.manage().window().fullscreen();

            // Phóng to lên (vẫn còn taskbar)
            driver.manage().window().maximize();

            // Thu nhỏ về Taskbar để chạy  **
            driver.manage().window().minimize();

            // Test GUI
            // Test Responsive (Resolution)
            // Font/Color/ Size/Position...
            driver.manage().window().setSize(new Dimension(1366, 768));
            driver.manage().window().setSize(new Dimension(1920, 1080));
            driver.manage().window().setSize(new Dimension(2560, 1440));
            driver.manage().window().getSize();

            // Set cho browser ở vị trí nào so vs độ phân giải màn hình (run trên màn hình có kích thước bao nhiêu)
            driver.manage().window().setPosition(new Point(0, 0));
            driver.manage().window().getPosition();

            // Lấy hết tất cả cookies: Test Class 01 (Register tài khoản - lưu cookies lại )
           Set<Cookie> cookies = driver.manage().getCookies();// *

            // Xóa hết Cookie
            driver.manage().deleteAllCookies();

            for (Cookie cookie: cookies){
                // Xóa cookie theo thứ tự
                driver.manage().deleteCookie(cookie);
            }

            // Xóa cookie theo tên
            driver.manage().deleteCookieNamed("");

            // Đến 1 Test Class khác 02/03/04.. (Không cần login - set cookie đã có vào đây rồi refresh lại )
            for (Cookie cookie: cookies){
                // Add cookie theo thứ tự
                driver.manage().addCookie(cookie);
            }
            driver.navigate().refresh();// Login thành công


            // Get ra những log ở Dev Tool - Framework
            Logs log = driver.manage().logs();
            LogEntries logEntries = log.get("BROWSER");

            for (LogEntry logEn: logEntries){
                System.out.println(logEn);
            }

            log.getAvailableLogTypes();

            // Điều hướng trang web
            WebDriver.Navigation navigation = driver.navigate();
            // Refresh/F5
            driver.navigate().refresh();
            //Quay lại trang trước đó
            driver.navigate().back();
            // Chuyển tiếp tới trang trước đó
            driver.navigate().forward();
            // Thao tác vs history của web page (back/ forward)
            // Mở URL bất kì
            driver.navigate().to("https://www.facebook.com/");
            driver.navigate().to(new URL("https://www.facebook.com/"));

            // Alert/Window (Tab)/ Frame (iFrame) // *
            WebDriver.TargetLocator targetLocator = driver.switchTo();
            // Alert
            driver.switchTo().alert().accept();
            driver.switchTo().alert().dismiss();
            // Frame/ Iframe
            targetLocator.frame("");
            targetLocator.defaultContent();// Switch về HTML chứa frame trước đó
            //Window
            targetLocator.window("");

            // Switch/ handle frame (iframe) // *
            // Index/ ID (name)/ Element
            driver.switchTo().frame(0);
            driver.switchTo().frame("39493494");
            driver.switchTo().frame(driver.findElement(By.id("")));

            // Lấy ra ID của cửa sổ/ tab đang active *
            driver.getWindowHandle();
           // Lấy ra ID của cửa sổ/ tab đang có
            driver.getWindowHandles();

        }

        @AfterClass
        public void afterClass() {
            driver.quit();
        }
    }