# 📦 OOP in Python: Từ Cơ Bản Đến Nâng Cao

Tài liệu này tổng hợp toàn bộ kiến thức Lập trình Hướng đối tượng (OOP) trong **Python**, được thiết kế chuẩn Markdown với syntax highlighting chi tiết để dễ dàng lưu trữ và đọc trên GitHub.

---

## 📦 Chương 1: Cấu trúc cơ bản (Attributes và Methods)

Trong Python, class định nghĩa đối tượng qua 2 thành phần:

* **Attributes (Thuộc tính)**: Lưu dữ liệu trạng thái của đối tượng, được khởi tạo chủ yếu bên trong hàm `__init__`.
* **Methods (Phương thức)**: Các hàm xử lý hành động, **bắt buộc** nhận `self` làm tham số đầu tiên đại diện cho chính đối tượng đó.

```python
class Robot:
    # 1. Hàm khởi tạo và tạo thuộc tính
    def __init__(self, ten: str, pin: int):
        self.ten = ten
        self.pin = pin

    # 2. Phương thức hành vi
    def gioi_thieu(self):
        print(f"Tôi là {self.ten}, pin còn: {self.pin}%")


if __name__ == "__main__":
    # Khởi tạo đối tượng r1
    r1 = Robot("T-800", 100)
    r1.gioi_thieu()  # In ra: Tôi là T-800, pin còn: 100%

```

---

## 🛠️ Chương 2: Hàm khởi tạo `__init__` và Quản lý bộ nhớ `__del__`

Python quản lý bộ nhớ hoàn toàn tự động nhờ cơ chế **Reference Counting** và **Garbage Collector (GC)**.

* **`__init__`**: Magic method đóng vai trò là Constructor, tự động gọi khi instance mới được tạo.
* **`__del__`**: Magic method đóng vai trò là Destructor, chạy khi số lượng biến tham chiếu tới đối tượng bằng 0.

```python
class Robot:
    def __init__(self, ten: str):
        self.ten = ten
        print(f"{self.ten} đã được khởi tạo!")

    def __del__(self):
        print(f"{self.ten} đã bị xóa khỏi bộ nhớ!")


# Kiểm tra chu kỳ sống của đối tượng
r1 = Robot("T-1000")
del r1  # In ra: T-1000 đã bị xóa khỏi bộ nhớ!

```

---

## 🔒 Chương 3: Tính đóng gói (Encapsulation) & Decorator `@property`

Python áp dụng triết lý *"We are all consenting adults here"*. Ngôn ngữ không chặn cứng việc truy cập private mà dùng **quy ước đặt tên** (Naming Conventions):

* `name`: Public (truy cập tự do).
* `_name`: Protected (chỉ dùng nội bộ trong class và lớp con).
* `__name`: Private (Name Mangling - Python đổi tên thành `_ClassName__name` để hạn chế truy cập ngoài).

```python
class Robot:
    def __init__(self, pin: int):
        self.__pin = pin  # Private attribute

    # Getter bằng decorator @property
    @property
    def pin(self) -> int:
        return self.__pin

    # Setter bằng decorator @<attribute>.setter
    @pin.setter
    def pin(self, pin_moi: int):
        if 0 <= pin_moi <= 100:
            self.__pin = pin_moi
        else:
            print("Giá trị pin không hợp lệ!")


r = Robot(50)
r.pin = 80  # Gọi setter tự động
print(r.pin)  # In ra: 80 (Gọi getter tự động)

```

---

## 👥 Chương 4: Class Attributes, Class Methods & Static Methods

Python phân biệt rõ 3 loại phương thức thông qua decorator:

1. **Instance Method**: Nhận `self` làm tham số đầu tiên, làm việc với dữ liệu của từng đối tượng.
2. **Class Method (`@classmethod`)**: Nhận `cls` làm tham số đầu tiên, làm việc với dữ liệu chung của Class.
3. **Static Method (`@staticmethod`)**: Không nhận `self` hay `cls`, hoạt động như hàm độc lập được nhóm vào Class.

```python
class Robot:
    tong_so_robot = 0  # Class Attribute (Biến dùng chung)

    def __init__(self, ten: str):
        self.ten = ten
        Robot.tong_so_robot += 1

    @classmethod
    def lay_tong_so(cls):
        print(f"Tổng số robot: {cls.tong_so_robot}")

    @staticmethod
    def kiem_tra_hop_le_ten(ten: str) -> bool:
        return len(ten) > 0


r1 = Robot("Alpha")
r2 = Robot("Beta")

Robot.lay_tong_so()  # In ra: Tổng số robot: 2
print(Robot.kiem_tra_hop_le_ten("T-800"))  # In ra: True

```

---

## 🧬 Chương 5: Tính kế thừa & Hàm `super()`

Sử dụng cú pháp `class Derived(Base):` để thực hiện kế thừa. Hàm `super()` được dùng để gọi phương thức từ lớp cha.

```python
class PhuongTien:
    def __init__(self, thuong_hieu: str):
        self.thuong_hieu = thuong_hieu

    def khoi_dong(self):
        print("Phương tiện đang khởi động...")


class XeMay(PhuongTien):
    def __init__(self, thuong_hieu: str, phan_khoi: int):
        super().__init__(thuong_hieu)  # Gọi constructor của lớp cha
        self.phan_khoi = phan_khoi

    def ru_ga(self):
        print(f"{self.thuong_hieu} {self.phan_khoi}cc đang nổ máy rầm rộ!")


xm = XeMay("Yamaha", 155)
xm.khoi_dong()  # Gọi hàm lớp cha
xm.ru_ga()      # Gọi hàm lớp con

```

---

## 🔀 Chương 6: Đa kế thừa (Multiple Inheritance) & Algorith MRO

Khác với Java, Python cho phép một lớp kế thừa trực tiếp từ **nhiều lớp cha**. Thứ tự gọi phương thức được quyết định bởi thuật toán **MRO (Method Resolution Order)**.

```python
class A:
    def chu_danh(self):
        print("Lớp A")

class B(A):
    def chu_danh(self):
        print("Lớp B")

class C(A):
    def chu_danh(self):
        print("Lớp C")

# D kế thừa cả B và C (Lỗi Diamond Problem được giải quyết bằng MRO)
class D(B, C):
    pass

d = D()
d.chu_danh()  # In ra: Lớp B (vì B đứng trước C trong định nghĩa class D(B, C))
print(D.__mro__)  # Xem thứ tự ưu tiên truy xuất: D -> B -> C -> A -> object

```

---

## 🎭 Chương 7: Duck Typing & Tính đa hình (Polymorphism)

Python ứng dụng triết lý **Duck Typing**: *"If it walks like a duck and quacks like a duck, it's a duck"*. Tính đa hình trong Python không phụ thuộc vào việc các lớp có chung một lớp cha hay không, miễn là chúng có cùng tên phương thức.

```python
class Dog:
    def make_sound(self):
        print("Gâu Gâu!")

class Cat:
    def make_sound(self):
        print("Meo Meo!")

class RobotPhatAm:
    def make_sound(self):
        print("Beep Boop!")

# Hàm nhận bất kỳ đối tượng nào có phương thức `make_sound`
def phat_am_thanh(doi_tuong):
    doi_tuong.make_sound()

danh_sach = [Dog(), Cat(), RobotPhatAm()]
for item in danh_sach:
    phat_am_thanh(item)  # Lần lượt in ra: Gâu Gâu! -> Meo Meo! -> Beep Boop!

```

---

## 🎭 Chương 8: Lớp trừu tượng (Abstract Base Classes - ABC)

Để bắt buộc các lớp con phải triển khai đầy đủ phương thức trừu tượng, Python cung cấp module chuẩn `abc`.

```python
from abc import ABC, abstractmethod

class Shape(ABC):
    @abstractmethod
    def tinh_dien_tich(self) -> float:
        pass

class HinhVuong(Shape):
    def __init__(self, canh: float):
        self.canh = canh

    # Bắt buộc phải triển khai phương thức trừu tượng này
    def tinh_dien_tich(self) -> float:
        return self.canh ** 2

# s = Shape()  # LỖI! Không thể khởi tạo trực tiếp Abstract Class
hv = HinhVuong(4.0)
print(hv.tinh_dien_tich())  # In ra: 16.0

```

---

## 🪄 Chương 9: Magic Methods (Dunder Methods) & Operator Overloading

Magic methods (các hàm bắt đầu và kết thúc bằng `__`) cho phép bạn tùy chỉnh hành vi mặc định của class như ép kiểu chuỗi, tính độ dài, hoặc nạp chồng các toán tử (+, -, ==,...).

```python
class ToaDo:
    def __init__(self, x: int, y: int):
        self.x = x
        self.y = y

    # Nạp chồng toán tử cộng (+)
    def __add__(self, khac):
        return ToaDo(self.x + khac.x, self.y + khac.y)

    # Tùy chỉnh hiển thị chuỗi khi print()
    def __str__(self):
        return f"ToaDo({self.x}, {self.y})"

    # Nạp chồng toán tử so sánh bằng (==)
    def __eq__(self, khac):
        return self.x == khac.x and self.y == khac.y


p1 = ToaDo(2, 3)
p2 = ToaDo(4, 5)
p3 = p1 + p2  # Tự động gọi p1.__add__(p2)

print(p3)         # In ra: ToaDo(6, 8)
print(p1 == p2)   # In ra: False

```

---

## ⚙️ Chương 10: Dataclasses & Type Hinting (Python Hiện Đại)

Từ Python 3.7+, mô-đun `dataclasses` giúp cắt giảm tối đa boiler-plate code (không cần tự viết `__init__`, `__repr__`, `__eq__` thủ công).

```python
from dataclasses import dataclass
from typing import Optional

@dataclass
class SanPham:
    id: int
    ten: str
    gia: float
    mo_ta: Optional[str] = None  # Gán giá trị mặc định

    def giam_gia(self, phan_tram: float) -> float:
        return self.gia * (1 - phan_tram / 100)


sp1 = SanPham(1, "Bàn phím cơ", 1500000.0)
sp2 = SanPham(1, "Bàn phím cơ", 1500000.0)

print(sp1)              # In ra: SanPham(id=1, ten='Bàn phím cơ', gia=1500000.0, mo_ta=None)
print(sp1 == sp2)       # In ra: True (dataclass tự sinh hàm __eq__ so sánh dữ liệu)
print(sp1.giam_gia(10)) # In ra: 1350000.0

```