# 📦 OOP in Java: Từ Cơ Bản Đến Nâng Cao

Tài liệu này tổng hợp toàn bộ kiến thức Lập trình Hướng đối tượng (OOP) trong **Java**, được thiết kế chuẩn Markdown với syntax highlighting chi tiết để dễ dàng lưu trữ và đọc trên GitHub.

---

## 📦 Chương 1: Cấu trúc cơ bản (Field và Method)

Một class trong Java định nghĩa đối tượng thông qua 2 thành phần:

* **Field (Biến thành viên / Thuộc tính)**: Lưu trữ trạng thái của đối tượng.
* **Method (Phương thức / Hành vi)**: Định nghĩa các thao tác, xử lý của đối tượng.

```java
public class Robot {
    // 1. Fields (Thuộc tính)
    String ten;
    int pin;

    // 2. Methods (Hành vi)
    void gioiThieu() {
        System.out.println("Tôi là " + ten + ", pin còn: " + pin + "%");
    }

    public static void main(String[] args) {
        // Khởi tạo đối tượng r1 trên bộ nhớ Heap
        Robot r1 = new Robot();
        r1.ten = "T-800";
        r1.pin = 100;
        r1.gioiThieu(); // In ra: Tôi là T-800, pin còn: 100%
    }
}

```

---

## 🛠️ Chương 2: Constructor (Hàm khởi tạo) và Quản lý bộ nhớ

Java không sử dụng Destructor thủ công như C++. Việc thu hồi bộ nhớ được đảm nhận tự động bởi **Garbage Collector (GC)**.

* **Constructor**: Hàm trùng tên với class, chạy tự động khi dùng từ khóa `new`.
* **Default Constructor**: Nếu bạn không viết constructor nào, Java sẽ tự tạo một constructor mặc định không tham số.

```java
public class Robot {
    String ten;

    // Constructor có tham số
    public Robot(String tenMoi) {
        this.ten = tenMoi;
        System.out.println(this.ten + " đã được bật nguồn!");
    }

    // Overloading Constructor (Đa năng hóa hàm khởi tạo)
    public Robot() {
        this("Robot Mặc Định"); // Gọi lại constructor trên
    }

    public static void main(String[] args) {
        Robot r1 = new Robot("T-1000");
        Robot r2 = new Robot();
        // Cả r1 và r2 sẽ tự động được Garbage Collector dọn dẹp khi không còn biến nào tham chiếu tới.
    }
}

```

---

## 🔒 Chương 3: Tính đóng gói (Encapsulation) & Access Modifiers

Java hỗ trợ 4 cấp độ truy cập (Access Modifiers) để bảo vệ dữ liệu:

1. `private`: Chỉ truy cập trong cùng Class.
2. *(Default / Package-Private)*: Truy cập trong cùng Package (không cần ghi từ khóa).
3. `protected`: Truy cập trong cùng Package + Các Lớp Con ở package khác.
4. `public`: Truy cập ở mọi nơi.

```java
public class Robot {
    // Giấu kín thuộc tính bằng private
    private int pin;

    public Robot(int pin) {
        setPin(pin); // Sử dụng Setter để kiểm tra ngay từ khi khởi tạo
    }

    // Getter: Cho phép đọc dữ liệu
    public int getPin() {
        return this.pin;
    }

    // Setter: Kiểm tra tính hợp lệ trước khi ghi dữ liệu
    public void setPin(int pinMoi) {
        if (pinMoi >= 0 && pinMoi <= 100) {
            this.pin = pinMoi;
        } else {
            System.out.println("Giá trị pin không hợp lệ!");
        }
    }
}

```

---

## 👥 Chương 4: Từ khóa static (Thành viên cấp Lớp)

Các thành viên `static` thuộc về **Class** chứ không thuộc về từng đối tượng (Instance) riêng lẻ. Dữ liệu static được lưu trữ tại vùng nhớ Metaspace/Method Area và dùng chung cho tất cả các đối tượng.

```java
public class Robot {
    private String ten;
    // Biến static dùng chung để đếm tổng số đối tượng được tạo ra
    public static int tongSoRobot = 0;

    public Robot(String ten) {
        this.ten = ten;
        tongSoRobot++; // Tăng biến đếm chung khi tạo instance mới
    }

    // Phương thức static: Chỉ truy cập được các biến static khác
    public static void hienThiTongSo() {
        System.out.println("Tổng số robot hiện tại: " + tongSoRobot);
    }

    public static void main(String[] args) {
        Robot r1 = new Robot("Alpha");
        Robot r2 = new Robot("Beta");

        // Gọi trực tiếp qua tên Class
        Robot.hienThiTongSo(); // In ra: Tổng số robot hiện tại: 2
    }
}

```

---

## 🧬 Chương 5: Tính kế thừa (Inheritance with `extends`)

Java chỉ hỗ trợ **Đơn kế thừa (Single Inheritance)** đối với Class (một lớp con chỉ có một lớp cha trực tiếp) nhằm tránh lỗi nhập nhằng Diamond Problem.

* Sử dụng từ khóa `extends` để kế thừa.
* Từ khóa `super`: Dùng để gọi Constructor hoặc phương thức của lớp cha.

```java
// Lớp Cha (Base Class)
class PhuongTien {
    protected String thuongHieu;

    public PhuongTien(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public void khoiDong() {
        System.out.println("Đang khởi động phương tiện...");
    }
}

// Lớp Con (Derived Class)
class XeMay extends PhuongTien {
    private boolean coCoYem;

    public XeMay(String thuongHieu, boolean coCoYem) {
        super(thuongHieu); // Bắt buộc gọi Constructor của lớp cha đầu tiên
        this.coCoYem = coCoYem;
    }

    public void ruGa() {
        System.out.println(thuongHieu + " đang nổ máy rầm rộ!");
    }
}

```

---

## 🪄 Chương 6: Tính đa hình (Polymorphism) & Từ khóa `final`

Tính đa hình cho phép một biến kiểu lớp cha tham chiếu đến đối tượng của các lớp con và thực thi đúng phương thức tương ứng tại thời điểm chạy (Runtime).

* **`@Override`**: Đánh dấu phương thức ghi đè từ lớp cha.
* **Từ khóa `final**`:
* `final` biến: Biến trở thành hằng số (không thể thay đổi giá trị).


* `final` phương thức: Chống ghi đè (cannot be overridden).
* `final` class: Chống kế thừa (cannot be extended).



```java
class ConVat {
    public void keu() {
        System.out.println("Tiếng kêu chung chung...");
    }
}

class ConMeo extends ConVat {
    @Override
    public void keu() {
        System.out.println("Meo Meo!");
    }
}

class ConCho extends ConVat {
    @Override
    public void keu() {
        System.out.println("Gâu Gâu!");
    }
}

public class Main {
    public static void main(String[] args) {
        // Đa hình runtime (Upcasting)
        ConVat v1 = new ConMeo();
        ConVat v2 = new ConCho();

        v1.keu(); // In ra: Meo Meo!
        v2.keu(); // In ra: Gâu Gâu!
    }
}

```

---

## 🎭 Chương 7: Lớp trừu tượng (Abstract Class) & Interface

Để đạt được **Tính trừu tượng (Abstraction)**, Java cung cấp `Abstract Class` và `Interface`.

| Đặc điểm | Abstract Class | Interface |
| --- | --- | --- |
| **Từ khóa** | `abstract class` | `interface` |
| **Kế thừa** | Đơn kế thừa (`extends`) | Đa kế thừa giao diện (`implements` nhiều interface) |
| **Thuộc tính** | Có thể chứa mọi loại biến | Mặc định là `public static final` (hằng số) |
| **Phương thức** | Chứa cả abstract & concrete method | Chứa abstract method (từ Java 8 có thêm `default` & `static` method) |

```java
// Interface định nghĩa chuẩn kết nối
interface SạcDuoc {
    void sacPin(); // Mặc định là public abstract
}

// Abstract class đóng vai trò làm khung xương
abstract class ThietBiDien Tu {
    protected String ten;

    public ThietBiDienTu(String ten) {
        this.ten = ten;
    }

    // Abstract method: Không có thân hàm
    public abstract void batNguồn();
}

// Lớp con triển khai đầy đủ
class DienThoai extends ThietBiDienTu implements SạcDuoc {
    public DienThoai(String ten) {
        super(ten);
    }

    @Override
    public void batNguồn() {
        System.out.println(ten + " hiển thị màn hình chào mừng.");
    }

    @Override
    public void sacPin() {
        System.out.println(ten + " đang sạc nhanh 65W...");
    }
}

```

---

## 📦 Chương 8: Package & Quản lý phạm vi dự án

`Package` giúp nhóm các lớp có liên quan lại với nhau để tránh xung đột tên và quản lý quyền truy cập tốt hơn.

```java
// Khai báo package ở ngay dòng đầu tiên của file
package com.example.robotics;

// Import lớp từ package khác
import java.util.ArrayList;
import java.util.List;

public class RobotManager {
    private List<String> danhSachRobot = new ArrayList<>();

    public void themRobot(String name) {
        danhSachRobot.add(name);
    }
}

```

---

## ⚠️ Chương 9: Xử lý ngoại lệ trong OOP (Exception Handling)

Xử lý ngoại lệ giúp chương trình không bị crash đột ngột khi gặp lỗi runtime. Java chia làm 2 loại ngoại lệ:

1. **Checked Exception**: Bắt buộc xử lý tại thời điểm biên dịch (dùng `try-catch` hoặc `throws`).
2. **Unchecked Exception (RuntimeException)**: Lỗi xảy ra khi chạy (ví dụ: `NullPointerException`, `ArrayIndexOutOfBoundsException`).

```java
// Ngoại lệ tùy chỉnh (Custom Exception)
class PinYeuException extends Exception {
    public PinYeuException(String message) {
        super(message);
    }
}

class Robot {
    private int pin = 5;

    public void hoatDong() throws PinYeuException {
        if (pin < 10) {
            throw new PinYeuException("Pin quá yếu (<10%), không thể hoạt động!");
        }
        System.out.println("Robot đang làm việc...");
    }
}

public class Main {
    public static void main(String[] args) {
        Robot r = new Robot();
        try {
            r.hoatDong();
        } catch (PinYeuException e) {
            System.err.println("Lỗi: " + e.getMessage());
        } finally {
            System.out.println("Dọn dẹp tài nguyên và đóng tác vụ.");
        }
    }
}

```

---

## 🔄 Chương 10: Generics & Collections Framework

`Generics` cho phép bạn truyền **Kiểu dữ liệu như một tham số**, giúp code an toàn hơn về mặt kiểu dữ liệu (Type-Safety) ngay ở thời điểm biên dịch.

```java
// Class Generics tổng quát
class BoLuuTru<T> {
    private T duLieu;

    public void luu(T duLieu) {
        this.duLieu = duLieu;
    }

    public T layOut() {
        return this.duLieu;
    }
}

public class Main {
    public static void main(String[] args) {
        // Lưu trữ kiểu String
        BoLuuTru<String> chuoi = new BoLuuTru<>();
        chuoi.luu("Xin chào Java OOP!");
        System.out.println(chuoi.layOut());

        // Lưu trữ kiểu Integer
        BoLuuTru<Integer> so = new BoLuuTru<>();
        so.luu(100);
        System.out.println("Số lượng: " + so.layOut());
    }
}

```

---