package javaSDET;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Topic_01_Data_Type {
    // Kiểu dữ liệu trong Java có 2 nhóm:

    // kiểu dữ liệu nguyên thủy (Primitive Type)
    // Số nguyên: byte - short - int - long
    byte bNumber = 40;
    short sNumber = 3000;
    int iNumber = 1455566665;
    long lNumber = 23345454;

    // Không có phần thập phân: nhân viên trong 1 công ty/ học sinh trong 1 lớp / trường / ...
    // số thực: float - double
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
    // class
FirefoxDriver firefoxDriver = new FirefoxDriver();
Select select = new Select(firefoxDriver.findElement(By.className("")));

    // Interface
WebDriver driver;
JavascriptExecutor javascriptExecutor;

    // Object
Object name = "Automation FC";

    // Array (kiểu nguyên thủy/ kiểu tham chiếu)
    int[] studentAge = {15, 20, 8};
    String Name = "Minh";

    // Collection : List/ Set/ Queue
List<String> studentAddress = new ArrayList<String>();
List<String> studentCity = new LinkedList<>();
List<String> studentPhone = new Vector<>();


    // Khai báo biến để lưu trữ một loại dữ liệu nào đó
    // Accerss Modifier: Phạm vi truy cập (public/ private/ protected/ default)
}
