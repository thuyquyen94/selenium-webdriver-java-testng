package javaSDET;
import java.util.ArrayList;
import java.util.List;

public class Topic_6_3_For {

    public static void main(String[] args) {
        // Biểu thức vòng lặp(loop)
        int number = 100;

        //for (classic - iteractor lặp)
        for (int i = 0; i < number; i++){
            System.out.println(i);
        }

        // Collection : List/ Set/ Queue/ Map

        List<String> name = new ArrayList<String>();
        name.add("Manual Testing");
        name.add("Automation Testing");
        name.add("Regression Testing");
        name.add("UI Testing");
        name.add("API Testing");
        name.add("Mobile Testing");

        // for-each
        for (String a : name){
            if (a.equals("Automation Testing")){
                System.out.println("Interview");
            }
        }

        int i = 0;
        // white
        while (i < number){
            System.out.println(i);
            i++;
        }

        // do-white
        do {// Action trước
            System.out.println(i);
            i++;
        }while (i < number);
    }


}
