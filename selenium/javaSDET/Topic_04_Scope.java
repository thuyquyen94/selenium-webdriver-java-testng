package javaSDET;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Scope {
    // Các biến được khai báo bên ngoài hàm => phạm vi là class
    // Gọi là biến Global (toàn cục)
    WebDriver driver;

    String homePageURL; // Khai báo

    String fullName = "Automation FC"; // Khai báo + Khởi tạo

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
    }

        @Test
      public void TC_01(){
        // Các biến được khai báo trong 1 hàm/ block code => phạm vi cục bộ (Local)
        // Dùng trong hàm được khai báo/ block code được sinh ra
            String homePageURL = "https://www.facebook.com/";
        // Trong 1 hàm nếu như có 2 biến cùng tên (Global/ Local) thì nó sẽ ưu tiên lấy biến local dùng
        // 1 biến Local nếu gọi tới dùng mà chưa được khởi tạo sẽ bị lỗi.
        // Biến local chưa khởi tạo mà gọi ra dùng nó sẽ báo lỗi ngay (compile code)
        driver.get(homePageURL);

        // Nếu trong 1 hàm nếu như có 2 biến cùng tên (Global/ Local) mà mình muốn lấy biến global ra dùng
        // Từ khóa this
        // Biến Global chưa khởi tạo mà gọi ra dùng thì không báo lỗi ở level (compile code)
        // Level runtime sẽ báo lỗi
       driver.get(this.homePageURL);
        }

    @Test
    public void TC_02(){

    }

    @Test
    public void TC_03(){

    }
}
