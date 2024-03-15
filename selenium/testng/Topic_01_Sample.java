package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class Topic_01_Sample {

    // Unit Test : Testing trên chính những dòng code, test những đơn vị code
    @Test
    public void testGetRandomNumber(){
        Topic_01_Sample sample = new Topic_01_Sample();
        int randomNumner = sample.getRamdomNumber();
        Assert.assertTrue(randomNumner >=0 && randomNumner < 1000000);
    }

    // Component
    private int getRamdomNumber(){
        return new Random().nextInt(10000);
    }
}
