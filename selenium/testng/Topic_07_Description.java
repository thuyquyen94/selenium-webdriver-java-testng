package testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Description {

    @BeforeClass(alwaysRun = true)
    public void init() {
        System.out.println("Pre-Condition for bellow testcases");
    }

    @Test(description = "JIRA#44599 - Search new Users")
    // Hiển thị description trong Log / Report HTML
    public void Priority_01_SearchWithDate(){

    }
    @Test(enabled = false)
    public void Priority_02_SearchWithBilling() {

    }

    @Test
    public void Priority_03_SearchWithProduct() {

    }
@AfterClass(alwaysRun = true)
    public void after(){
        System.out.println("Post-Condition for above testcases");
}
}
