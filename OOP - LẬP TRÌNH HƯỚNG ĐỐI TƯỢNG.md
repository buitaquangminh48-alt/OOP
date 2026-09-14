## 📦 Chương 1: Cấu trúc cơ bản nhất (Thuộc tính và Hành vi)

Một class luôn gồm 2 thành phần chính:

- **Thuộc tính (Attributes/Variables)**: Dữ liệu, đặc điểm của đối
  tượng.

- **Hành vi (Methods/Functions)**: Các hàm xử lý, hành động của đối
  tượng.

`
\#include \<iostream\>

\#include \<string\>

class Robot {

public: _// Tạm thời để public để bên ngoài truy cập được nhé_

  _// 1. Thuộc tính_

  std::string ten;

  int pin;

  _// 2. Hành vi_

  void gioiThieu() {

  std::cout \<\< "Toi la " \<\< ten \<\< ", pin con: " \<\< pin \<\<"%\n";

}

};

int main() {

  Robot r1; _// Tạo ra đối tượng r1 từ bản thiết kế Robot_

  r1.ten = "T-800"; _// Gán dữ liệu_

  r1.pin = 100;

  r1.gioiThieu(); _// Gọi hàm: "Toi la T-800, pin con: 100%"_

}
`

## 🛠️ Chương 2: Constructor (Hàm khởi tạo) và Destructor (Hàm hủy)

Khi bạn viết Robot r1;, làm sao để tự động nạp tên và pin cho nó ngay
lúc vừa sinh ra? Đó là nhiệm vụ của **Constructor**. Còn khi đối tượng
bị xóa khỏi bộ nhớ, **Destructor** sẽ chạy để dọn dẹp.

`
class Robot {

public:

  std::string ten;

  _// CONSTRUCTOR: Trùng tên với Class, không có kiểu trả về, tự chạy khi tạo đối tượng_

  Robot(std::string tenMoi) {

    ten = tenMoi;

    std::cout \<\< ten \<\< " đã được bật nguồn!\n";

  }

  _// DESTRUCTOR: Có dấu ~ ở trước, tự chạy khi đối tượng bị hủy (kết thúc hàm/chương trình)_

  ~Robot() {

    std::cout \<\< ten \<\< " đã bị tắt nguồn và giải phóng!\n";

  }

};
`

## 🔒 Chương 3: Tính đóng gói (Encapsulation) & Từ khóa this

Trong thực tế, bạn không bao giờ muốn người ngoài tự ý sửa đổi thuộc
tính (ví dụ: r1.pin = -999 là vô lý). Bạn phải giấu thuộc tính vào
private và cung cấp các hàm Getter (để đọc) và Setter (để ghi/kiểm tra
dữ liệu).

- **Từ khóa this**: Là một con trỏ trỏ thẳng vào "chính con robot hiện
  tại", dùng để phân biệt khi tên tham số của hàm trùng với tên thuộc
  tính.

`
class Robot {

private: _// Giấu kín dữ liệu bên trong_

  int pin;

public:

  Robot(int pin) {

    this-\>pin = pin; _// this-\>pin là thuộc tính private, còn pin là tham số truyền vào_

  }

  _// SETTER: Cho phép sửa pin nhưng có kiểm tra điều kiện_

  void setPin(int pinMoi) {

    if (pinMoi \>= 0 && pinMoi \<= 100) {

      this-\>pin = pinMoi;

    }

  }

  _// GETTER: Cho phép xem pin chứ không cho sửa trực tiếp_

  int getPin() {

    return this-\>pin;

  }

};
`

## 👥 Chương 4: Từ khóa static (Thành viên dùng chung)

Thông thường, mỗi con robot có một tên và lượng pin riêng. Nhưng nếu bạn
muốn đếm **tổng số robot** đang hoạt động trên thế giới, bạn cần một
biến mà tất cả các đối tượng đều dùng chung. Đó là static.

`
class Robot {

public:

  static int tongSoRobot; _// Biến static: nằm ở class chứ không nằm riêng ở từng đối tượng_

  Robot() {

    tongSoRobot++; _// Cứ tạo 1 con robot thì tăng tổng số lên_

  }

};

_// Khởi tạo giá trị cho biến static (bắt buộc phải viết ngoài class)_

int Robot::tongSoRobot = 0;

int main() {

  Robot a;

  Robot b;

  std::cout \<\< Robot::tongSoRobot; _// In ra 2 (Dùng tên Class:: để gọi trực tiếp)_

}
`

## 🤝 Chương 5: Bạn thân (friend) và Định nghĩa chồng toán tử (Operator Overloading)

- **friend**: Cho phép một hàm bên ngoài hoặc một class khác truy cập
  thẳng vào vùng private của class này.

- **Operator Overloading**: Bình thường bạn không thể lấy hai đối tượng
  cộng nhau (r1 + r2). C++ cho phép bạn tự định nghĩa phép toán này sẽ
  làm gì.

`
class Robot {

private:

  int sucManh = 50;

public:

  _// Khai báo hàm "Bác Sĩ" là bạn thân của Robot_

  friend void bacSiKiemTra(Robot r);

  _// Định nghĩa phép cộng (+): Khi 2 robot cộng nhau, cộng sức mạnh của chúng lại_

  int operator+(const Robot& khac) {

    return this-\>sucManh + khac.sucManh;

  }

};

void bacSiKiemTra(Robot r) {

  _// Vì là friend, hàm này sờ được vào biến private 'sucManh' mà không bị báo lỗi!_

  std::cout \<\< "Sức mạnh robot: " \<\< r.sucManh;

}
`

🧬 Chương 6: Các kiểu kế thừa public, private và protected

Trong C++, việc bạn chọn kế thừa public hay private sẽ quyết định **"góc
nhìn"** của thế giới bên ngoài (và của các lớp con cháu sau này) đối với
các thành viên kế thừa từ lớp cha.

Để dễ hiểu, hãy hình dung các quyền truy cập trong lớp cha (Base) như
sau:

- public: Ai cũng thấy.

- protected: Chỉ người trong nhà (Cha và Con) thấy.

- private: Bí mật tuyệt mật của riêng Cha, Con cũng không sờ vào được.

Dưới đây là tác động chi tiết của từng kiểu kế thừa:

## 1. Kế thừa Public (Public Inheritance) - Quan hệ "IS-A"

Đây là kiểu kế thừa phổ biến nhất. Nó giữ nguyên (hoặc thắt chặt nếu gặp
private) quyền truy cập của các thành viên từ lớp cha xuống lớp con.

## Quy tắc chuyển đổi quyền:

- public của Cha \$\rightarrow\$ trở thành public của Con.

- protected của Cha \$\rightarrow\$ trở thành protected của Con.

- private của Cha \$\rightarrow\$ Con **không thể** truy cập trực tiếp.

## Tác động thực tế:

- **Hàm main() và bên ngoài**: Có thể gọi các hàm public của lớp cha
  thông qua đối tượng của lớp con.

- **Ý nghĩa thiết kế**: Thể hiện mối quan hệ **"Con là một bản sao mở
  rộng của Cha"** (ví dụ: XeMáy là một PhươngTiệnGiaoThông).

## 2. Kế thừa Private (Private Inheritance) - Quan hệ "IMPLEMENTED-IN-TERMS-OF"

Kiểu kế thừa này sẽ **biến tất cả** những gì public và protected của cha
thành bí mật riêng tư của con.

## Quy tắc chuyển đổi quyền:

- public của Cha \$\rightarrow\$ trở thành private của Con.

- protected của Cha \$\rightarrow\$ trở thành private của Con.

- private của Cha \$\rightarrow\$ Con **không thể** truy cập trực tiếp.

## Tác động thực tế:

- **Hàm main() và bên ngoài**: Bị chặn đứng hoàn toàn. Bạn **không thể**
  gọi bất kỳ hàm nào của lớp cha thông qua đối tượng lớp con nữa.

- **Đời cháu (Lớp cháu kế thừa từ lớp con)**: Lớp cháu cũng sẽ không thể
  truy cập bất cứ thứ gì của lớp ông nội, vì lớp con đã biến chúng thành
  private rồi.

- **Ý nghĩa thiết kế**: Thể hiện mối quan hệ **"Lớp con được xây dựng
  dựa trên các tính năng của lớp cha"**, nhưng lớp con không muốn thế
  giới bên ngoài biết nó dùng lớp cha để triển khai (Ví dụ: Lớp
  DanhSáchSinhViên kế thừa private từ lớp Mảng, bạn chỉ muốn người ta
  dùng các hàm như ThêmSinhViên(), chứ không muốn họ gọi hàm
  XóaPhầnTửMảng\[0\]).

## Ví dụ Code so sánh trực tiếp

`
\#include \<iostream\>

class Cha {

public:

  void XuatPublic() { std::cout \<\< "Gốc Public\n"; }

protected:

  void XuatProtected() { std::cout \<\< "Gốc Protected\n"; }

};

_// ==================== KẾ THỪA PUBLIC ====================_

class ConPublic : public Cha {

public:

  void ThuNghiem() {

    XuatPublic(); _// HỢP LỆ (vẫn là public)_

    XuatProtected(); _// HỢP LỆ (vẫn là protected)_

  }

};

_// ==================== KẾ THỪA PRIVATE ====================_

class ConPrivate : private Cha {

public:

  void ThuNghiem() {

    XuatPublic(); _// HỢP LỆ (truy cập nội bộ được, giờ nó thành private của Con)_

    XuatProtected(); _// HỢP LỆ (truy cập nội bộ được, giờ nó thành private của Con)_

  }

};

class Chau : public ConPrivate {

  void ThuNghiemChau() {

    _// XuatPublic(); // LỖI! Vì ConPrivate đã đổi nó thành private, lớp Chau không sờ vào được nữa._

  }

};

int main() {

  ConPublic objPublic;

  objPublic.XuatPublic(); _// HỢP LỆ! Gọi bình thường từ hàm main._

  ConPrivate objPrivate;

  _// objPrivate.XuatPublic(); // LỖI BIÊN DỊCH! XuatPublic bây giờ là private đối với objPrivate._

  return 0;

}
`

3\. Kế thừa Protected – Quan hệ “IS-IMPLEMENTED-IN-TERMS-OF”

Kế thừa protected là kiểu kế thừa nằm ở tầm trung, kết hợp giữa public
và private. Nó đóng vai trò như một **bộ lọc thắt chặt**, ép tất cả
những gì công khai của lớp cha thành "chuyện nội bộ" để dành riêng cho
các thế hệ con cháu mai sau.

Để dễ nhớ nhất, hãy xem bảng biến đổi quyền lực của kiểu kế thừa này:

## Quy tắc chuyển đổi quyền của Kế thừa Protected:

- public của Cha \$\rightarrow\$ bị hạ cấp xuống thành protected của
  Con.

- protected của Cha \$\rightarrow\$ giữ nguyên là protected của Con.

- private của Cha \$\rightarrow\$ Con **không thể** truy cập.

## Tác động thực tế (So sánh với Public và Private)

- **Đối với thế giới bên ngoài (hàm main)**: Nó giống hệt private. Hàm
  main bị **chặn hoàn toàn**, không thể gọi bất kỳ hàm nào của lớp cha
  thông qua đối tượng của lớp con.

- **Đối với đời cháu (lớp kế thừa từ lớp con)**: Nó khác private ở điểm
  này. Vì tài sản của cha đã biến thành protected của con, nên lớp cháu
  **vẫn có quyền truy cập và sử dụng tiếp**. (Nếu là kế thừa private,
  lớp con sẽ giữ làm bí mật riêng và lớp cháu bị cấm cửa).

## Ví dụ Code trực quan

`
\#include \<iostream\>

class Ong {

public:

  void DiSanOng() { std::cout \<\< "Di san của Ong\n"; }

};

_// ==================== KẾ THỪA PROTECTED ====================_

class ChaProtected : protected Ong {

  _// DiSanOng() từ public đã biến thành PROTECTED ở đây_

public:

  void TestCha() {

    DiSanOng(); _// HỢP LỆ: Cha vẫn dùng được tài sản của Ong_

  }

};

_// ==================== ĐỜI CHÁU KẾ THỪA TIẾP ====================_

class Chau : public ChaProtected {

public:

  void TestChau() {

    DiSanOng(); _// HỢP LỆ! Vì ở lớp Cha nó là protected, nên Cháu vẫn được xài._

    _// (Nếu ChaProtected mà dùng kế thừa private, dòng này sẽ BỊ LỖI)_

  }

};

int main() {

  ChaProtected objCha;

  _// objCha.DiSanOng();_

  _// LỖI BIÊN DỊCH! Bên ngoài hàm main không được phép truy cập hàm protected._

  return 0;

}
`

## Bảng so sánh tổng hợp cả 3 loại kế thừa

Đây là bức tranh toàn cảnh để bạn nhìn một phát là phân biệt được ngay
cả 3 kiểu:

| **Quyền gốc ở lớp Cha**    | **Kế thừa public**        | **Kế thừa protected**         | **Kế thừa private**     |
| -------------------------- | ------------------------- | ----------------------------- | ----------------------- |
| **public**                 | \$\rightarrow\$ public    | \$\rightarrow\$ **protected** | \$\rightarrow\$ private |
| **protected**              | \$\rightarrow\$ protected | \$\rightarrow\$ **protected** | \$\rightarrow\$ private |
| **private**                | Không thể truy cập        | Không thể truy cập            | Không thể truy cập      |
| _Hàm main() gọi được?_     | **Có**                    | **Không**                     | **Không**               |
| _Lớp Cháu dùng tiếp được?_ | **Có**                    | **Có**                        | **Không**               |

## Chương 7. Thành viên Hằng (const trong Class)

Từ khóa const đặt ở cuối một hàm thành viên nhằm cam kết: _"Hàm này chỉ
đọc dữ liệu chứ tuyệt đối không chỉnh sửa bất kỳ thuộc tính nào của
Object."_

`
class TaiKhoan {

private:

  int balance = 5000;

public:

  _// Hàm const: bảo vệ dữ liệu không bị sửa đổi nhầm_

  int xembalance() const {

    _// balance = 0; // LỖI BIÊN DỊCH ngay! Vì hàm const không cho phép sửa biến._

    return balance;

  }

};
`

## ⚙️ Chương 8. Danh sách khởi tạo (Constructor Initialization List)

Thay vì gán giá trị bằng dấu = bên trong thân hàm Constructor, các lập
trình viên C++ chuyên nghiệp luôn dùng **Initialization List** (dấu : ở
sau constructor).

- **Tại sao cần?** Nó giúp khởi tạo biến **trực tiếp** ngay khi vừa cấp
  phát bộ nhớ, bỏ qua bước trung gian giúp tăng tốc độ chạy code, và đây
  là cách duy nhất để khởi tạo các thuộc tính kiểu const hoặc kiểu Tham
  chiếu (&).

`
class ViDu {

private:

  const int id; _// Biến hằng số trong class_

  std::string ten;

public:

  _// Cách viết CHUẨN C++: Dùng danh sách khởi tạo_

  ViDu(int idMoi, std::string tenMoi) : id(idMoi), ten(tenMoi) {

    _// Thân hàm trống không cần viết gì thêm_

  }

};
`

## 💾 Chương 9. Quản lý bộ nhớ: Bộ ba thần thánh (Rule of Three)

Mảnh ghép này giải thích cho câu hỏi ở phần trước: **Tại sao Destructor
cực kỳ quan trọng khi có con trỏ (new/delete)?**

Nếu class của bạn có sử dụng vùng nhớ Heap (con trỏ new), bạn **bắt
buộc** phải tự định nghĩa 3 thứ sau để tránh rò rỉ bộ nhớ (Memory Leak)
và lỗi sập chương trình (Crash):

1.  **Destructor**: Để giải phóng vùng nhớ delete.

2.  **Copy Constructor (Hàm khởi tạo sao chép)**: Tạo bản sao sâu (Deep
    Copy) mới, thay vì copy mỗi cái địa chỉ vùng nhớ.

3.  **Copy Assignment Operator (Toán tử gán sao chép)**: Xử lý khi gán 2
    object có con trỏ cho nhau (a = b).

`
class rowArr {

private:

  int\* ptr;

public:

  rowArr() { ptr = new int\[100\]; } _// Cấp phát vùng nhớ_

  _// 1. Destructor: Không có cái này là bị rò rỉ bộ nhớ ngay!_

  ~rowArr() { delete\[\] ptr; }

  _// 2. Copy Constructor: Đảm bảo khi copy sang object mới, tạo hẳn vùng nhớ mới độc lập_

  rowArr(const rowArr& nguon) {

    ptr = new int\[100\];

    _// Copy từng phần tử từ nguon.ptr sang ptr..._

  }

};
`

## 🪄 Chương 10. Hàm ảo (virtual) và Đa hình (Polymorphism)

Dù lúc nãy bạn bảo bỏ qua kế thừa công khai, nhưng có một cơ chế cực kỳ
đỉnh cao gắn liền với class mà không thể không nhắc tới: **Virtual
Function**.

Nếu không có từ khóa virtual, C++ sẽ gọi hàm dựa trên **kiểu dữ liệu của
con trỏ** lúc biên dịch, chứ không nhìn vào **đối tượng thực tế** lúc
chạy.

`
class ConVat {

public:

  virtual void keu() { std::cout \<\< "Tiếng kêu chung chung...\n"; }

};

class ConMeo : public ConVat {

public:

  void keu() override { std::cout \<\< "Meo Meo!\n"; } _// override để đè lên hàm cha_

};

int main() {

  ConVat\* v = new ConMeo(); _// Con trỏ kiểu ConVat nhưng giữ đối tượng ConMeo_

  v-\>keu(); _// Kết quả: "Meo Meo!" nhờ có từ khóa virtual!_

  _// (Nếu không có 'virtual' ở lớp cha, nó sẽ in ra "Tiếng kêu chung chung...")_

  delete v;

}
`
