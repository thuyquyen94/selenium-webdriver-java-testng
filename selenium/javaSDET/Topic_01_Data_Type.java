package javaSDET;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

public class Topic_01_Data_Type {
    // Kiểu dữ liệu trong Java có 2 nhóm:

    // Cách khai báo :
    // Access Modifier: phạm vi truy cập (private/ public/ protected/ default)
    // 1- Access Modifier -Kiểu dữ liệu - Tên biến - Giá trị của biến (ngoài hàm/ trong hàm đều được )
    // Khi gán giá trị (khởi tạo) thì nằm trong dấu nháy đơn ''
    public char cName = 'b';

    // 2.1 - Access Modifier - Tên biến
   private char cAddress;

    // 2.2 - Tên biên - Giá trị gán sau (Hàm)
    // Hàm lúc nào cũng có dấu ()
    public void clickToElement(){
        cAddress = 'c';
    }

    // Convention : Quy ước khi lập trinh
    // Tên biến/ hàm được viết dưới dạng camel case: viết thường chữ đầu tiên, viết hoa chữ tiếp theo
    // name/ city/ phone/ zipCode
    // clickToElement/ getUserName/ getPhoneNumber...


    // 1- kiểu dữ liệu nguyên thủy (Primitive Type)
    // Số nguyên: byte - short - int - long
    // Khi gán giá trị (khởi tạo) thì không nằm trong dấu gì hết.
    byte bNumber = 40;
    short sNumber = 3000;
    int iNumber = 1455566665;
    long lNumber = 23345454;

    // Không có phần thập phân: nhân viên trong 1 công ty/ học sinh trong 1 lớp / trường / ...
    // số thực: float - double
    // Khi gán giá trị (khởi tạo) thì không nằm trong dấu gì hết.
    float fNumber = 9.99f;
    double dNumber = 35.880d;

    // có phần thập phân : điểm số/ lương...
    // ký tự: char
    // đại diện cho 1 kí tự
    char c = 'M';
    // Logic: boolean: dùng để khai báo dữ liệu
boolean status = true;

    // 2- Kiểu dữ liệu tham số (  reference type)
    // String - Chuỗi kí tự
    // Khi gán giá trị (khởi tạo) thì nằm trong nháy đôi ""

    // Class
FirefoxDriver firefoxDriver = new FirefoxDriver();
Select select = new Select(firefoxDriver.findElement(By.className("")));

    // Interface
WebDriver driver;
JavascriptExecutor javascriptExecutor;

    // Object
Object name = "Automation FC";
Object phone = "0905";
Object isDisplayed ="true";

    // Array (kiểu nguyên thủy/ kiểu tham chiếu)
    int[] studentAge = {15, 20, 8};
    String Name = "Minh";

    // Collection : List/ Set/ Queue
List<String> studentAddress = new ArrayList<String>();
List<String> studentCity = new LinkedList<>();
List<String> studentPhone = new Vector<>();

// Map
    Map<String, Integer> zip = new HashMap<String, Integer>();


}
