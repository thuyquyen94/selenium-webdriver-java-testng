package javaSDET;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Topic_11_List {

    @Test
    public void testList() {
        // ArrayList - cần truy xuất dữ liệu(query)
        // LinkedList - thêm sửa xóa
        List<String> studentName = new ArrayList<String>();
        studentName.add("Võ Văn Thưởng");
        studentName.add("Ngô Tất Tố");
        studentName.add("Chu Thanh Hoa");
        // 3 element trong 1 List
        System.out.println(studentName.size());

        System.out.println(studentName.get(0));
        System.out.println(studentName.get(1));
        System.out.println(studentName.get(2));

    }
}
