package javaTester;

public class Topic_14_Constructor {

    // là 1 hàm cùng tên với class
    // không có kiểu dữ liệu trả về
    // Trong 1 class có thể có nhiều constructor
    // 1 class nếu không define constructor cụ thể thì nó sẽ có 1 constructor rỗng (default)
    // Nếu mình define thì khi khởi tạo nó bắt buộc phải gọi tới constructor mà mình đã define


    public Topic_14_Constructor(String name) {
        System.out.println(name);
    }

    public static void main(String[] args) {
        Topic_14_Constructor topic = new Topic_14_Constructor("Automation FC");
    }
}
