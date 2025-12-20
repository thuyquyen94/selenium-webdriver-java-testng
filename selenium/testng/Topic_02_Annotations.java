package testng;

import org.testng.Assert;
import org.testng.annotations.*;

public class Topic_02_Annotations {
    public  static void main(String[]arg){
        // 3 hàm chính để kiểm tra tính đúng đắn của dữ liệu
        boolean gender = 3>5;

        // Kiểm tra dữ liệu nó phải ĐÚNG
        Assert.assertTrue(gender);

        // Kiểm tra dữ liệu nó phải SAI
        Assert.assertFalse(3>5);

        // Kiểm tra dữ liệu nó bằng với mong đợi
        // Kiểm tra dữ liệu giống nhau
        // Giá trị của dữ liệu bằng nhau
        Assert.assertEquals(5,6);

    }
    // 1 Annotations bắt buộc phải gán vào 1 hàm nào đó

    @BeforeClass
    public void beforeClass(){
        System.out.println("Before Class");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("After Class");
    }

    @BeforeGroups
    public void beforeGroup(){
        System.out.println("Before Group");
    }

    @AfterGroups
    public void afterGroup(){
        System.out.println("After Group");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Before Method");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("After Method");
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("After Suite");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Before Test");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("After Test");
    }

    @Test
    public void test01() {
        System.out.println("Test 01");
    }

    @Test
    public void test02() {
        System.out.println("Test 02");
    }

    @Test
    public void test03() {
        System.out.println("Test 03");
    }
}
