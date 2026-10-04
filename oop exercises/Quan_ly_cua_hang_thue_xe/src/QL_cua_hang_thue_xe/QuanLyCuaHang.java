package QL_cua_hang_thue_xe;

import java.util.ArrayList;
import java.util.Scanner;
import QL_cua_hang_thue_xe.XeMay.OutputXeMay;
import QL_cua_hang_thue_xe.Oto.OutputOto;

/**
 * Lớp điều khiển chính (Main Class) chứa giao diện dòng lệnh tương tác người dùng,
 * quản lý dữ liệu kho xe và danh sách xe đã được chọn thuê.
 */
public class QuanLyCuaHang {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// Danh sách lưu trữ các phương tiện mà khách hàng đã chốt thuê thành công
		ArrayList<PhuongTien> danhSachThue = new ArrayList<>();
		
		// Khởi tạo kho dữ liệu xe máy mẫu
		ArrayList<XeMay> khoXe = new ArrayList<>();
		khoXe.add(new XeMay("Honda Wave", 1023, "61A-123.45", 80000, 50));
		khoXe.add(new XeMay("Honda Wave", 1047, "61A-456.78", 80000, 100));
		khoXe.add(new XeMay("Honda Wave", 1088, "61A-789.01", 80000, 125));
		khoXe.add(new XeMay("Honda Vision", 1123, "62A-123.45", 100000, 50));
		khoXe.add(new XeMay("Honda Vision", 1147, "62A-456.78", 100000, 100));
		khoXe.add(new XeMay("Honda Vision", 1188, "62A-789.01", 100000, 125));
		khoXe.add(new XeMay("Yamaha Exciter", 1223, "63A-123.45", 120000, 50));
		khoXe.add(new XeMay("Yamaha Exciter", 1247, "63A-456.78", 120000, 100));
		khoXe.add(new XeMay("Yamaha Exciter", 1288, "63A-789.01", 120000, 125));
		
		// Khởi tạo kho dữ liệu ô tô mẫu
		ArrayList<Oto> khoOto = new ArrayList<>();
		khoOto.add(new Oto("Toyota Vios", 2001, "61A-111.11", 500000, (byte)5, Oto.LoaiSo.So_San));
		khoOto.add(new Oto("Toyota Vios", 2002, "61A-222.22", 500000, (byte)5, Oto.LoaiSo.Tu_Dong));
		khoOto.add(new Oto("Toyota Innova", 2101, "61A-333.33", 700000, (byte)7, Oto.LoaiSo.So_San));
		khoOto.add(new Oto("Toyota Innova", 2102, "61A-444.44", 700000, (byte)7, Oto.LoaiSo.Tu_Dong));
		
		// Vòng lặp giao diện Menu chính
		while (true) {
			// Menu lựa chọn
			System.out.println("========================================");
			System.out.println("	🏍️ CỬA HÀNG CHO THUÊ XE 🚗		");
			System.out.println("========================================");
			System.out.println("");
			System.out.println("");
			System.out.println("Xin chào quý khách!");
			System.out.println("Bạn muốn chọn loại xe nào?");
			System.out.println("");
			System.out.println("[1] 🏍️ Xe máy");
			System.out.println("[2] 🚗 Ô tô");
			System.out.println("[0] Thoát");
			System.out.println("");
			System.out.println("Lựa chọn:");
			
			int chonLoaiXe = sc.nextInt();
			int tongTienXeMay = 0;
			int tongTienOto = 0;
			
			// --- XỬ LÝ CHO THUÊ XE MÁY ---
			if (chonLoaiXe == 1) {
				System.out.println("Bạn muốn thuê bao nhiêu xe máy?");
				System.out.print("Số lượng: "); 
				int soLuongXeMay = sc.nextInt();
				
				// In danh sách các xe máy đang sẵn có trong kho
				System.out.println("========== XE MÁY ==========");
				OutputXeMay outputxemay = new OutputXeMay();
				System.out.printf("%-20s %-10s %-20s %-15s %-5s %-10s%n",
						"Thương hiệu",
						"Mã số",
						"Biển số",
						"Giá thuê",
						"Ngày",
						"Dung tích");
				for (XeMay xemay : khoXe) {
					outputxemay.displayXeMay(xemay);
				}
				
				// Lặp lại số lần chọn xe tương ứng với số lượng khách yêu cầu
				for (int i = 1; i <= soLuongXeMay; i++) {
					System.out.println("Nhập mã xe thứ " + i + ": ");
					int chonMaSoXeMay = sc.nextInt();
					
					// ❌ LỖI 1: Các biến ThuongHieu, MaSo, BienSo,... chưa được khai báo ở đâu cả.
				    //XeMay xemay = new XeMay(ThuongHieu, MaSo, BienSo, GiaThue, Ngay); 
				    
				    // ❌ LỖI 2: getMaSo() là phương thức của instance, không gọi qua tên lớp XeMay.getMaSo() được.
				    // ❌ LỖI 3: Câu lệnh if chưa viết xong (thiếu dấu ngoặc nhọn `{}` và nội dung).
				    //if (chonMaSo == XeMay.getMaSo())
				    	
					
					
					// Tìm đối tượng xe trong kho khớp với mã vừa nhập
					XeMay xeDuocChon = null;
					for (XeMay xm : khoXe) {
						if (chonMaSoXeMay == xm.getMaSo()) {
							xeDuocChon = xm;
							break;
						}
					}
					
					if (xeDuocChon != null) {
						// Kiểm tra nếu xe đã có người thuê trước đó
						if (xeDuocChon.isXeDaThue()) {
							System.out.println("Mã số xe này đã được thuê, vui lòng nhập mã số xe khác!");
							i--; // Giảm chỉ số lặp để cho phép người dùng chọn lại xe thứ i
						} else {
							xeDuocChon.setXeDaThue(true); // Đánh dấu xe đã được thuê
							danhSachThue.add(xeDuocChon); // Thêm xe được chọn vào danh sách thuê
							
							System.out.println("Bạn muốn thuê trong bao nhiêu ngày?");
							System.out.print("Số ngày: ");
							int soNgayThue = sc.nextInt();
							xeDuocChon.setNgay(soNgayThue); // Cập nhật số ngày thuê
							
							System.out.println("-> Đã thêm xe " + xeDuocChon.getThuongHieu() + " (" + soNgayThue + " ngày thuê)\n" +
					                   "   Mã số: " + xeDuocChon.getMaSo() + "\n" +
					                   "   Biển số: " + xeDuocChon.getBienSo() + "\n" +
					                   "   Dung tích: " + xeDuocChon.getDungTichXiLanh() + " cc\n" +
					                   "-> Vào danh sách thuê thành công!\n");
					        
					        // In ra tiền thuê của từng chiếc xe máy
					        int tienXeMay = xeDuocChon.tinhTienThue();
					        System.out.print("-> Tiền thuê chiếc xe là: " + tienXeMay + "đ\n");
					        
					        // Tính tổng tiền thuê những chiếc xe máy
					        tongTienXeMay += tienXeMay;
						}
					} else {
						System.out.println("Không có mã số xe này trong kho xe!");
						i--; // Giảm chỉ số lặp để chọn lại lượt lỗi
					}
				}
				
			// In ra tổng tiền thuê những chiếc xe máy
			System.out.println("Tổng tiền thuê " + soLuongXeMay + " chiếc xe máy là: " + tongTienXeMay);
				
			// --- XỬ LÝ CHO THUÊ Ô TÔ ---
			} else if (chonLoaiXe == 2) {
				System.out.println("Bạn muốn thuê bao nhiêu xe ô tô?");
				System.out.println("Số lượng: ");
				int soLuongOto = sc.nextInt();
				
				// In danh sách các xe ô tô đang sẵn có trong kho
				System.out.println("========== Ô TÔ ==========");
				OutputOto outputoto = new OutputOto();
				System.out.printf(
						"%-20s %-10s %-20s %-15s %-5s %-10s %-10s%n",
						"Thương hiệu",
						"Mã số",
						"Biển số",
						"Giá thuê",
						"Ngày",
						"Số ghế",
						"Loại số"
				);
				for (Oto oto : khoOto) {
					outputoto.displayOto(oto);
				}
				
				// Lặp lại số lần chọn ô tô
				for (int i = 1; i <= soLuongOto; i++) {
					System.out.println("Nhập mã xe thứ " + i + ": ");
					int chonMaSoOto = sc.nextInt();
					
					// Tìm ô tô khớp mã số
					Oto otoDuocChon = null;
					for (Oto oto : khoOto) {
						if (chonMaSoOto == oto.getMaSo()) {
							otoDuocChon = oto;
							break;
						}
					}
					
					if(otoDuocChon != null) {
						if (otoDuocChon.isXeDaThue()) {
							System.out.println("Mã số ô tô này đã được thuê, vui lòng nhập lại mã khác!");
							i--;
						} else {
							otoDuocChon.setXeDaThue(true); // Đánh dấu ô tô đã được thuê
							danhSachThue.add(otoDuocChon); // Thêm ô tô vào danh sách thuê
							
							System.out.println("Bạn muốn thuê ô tô bao nhiêu ngày?");
							System.out.println("Số ngày: ");
							int soNgayThueOto = sc.nextInt();
							otoDuocChon.setNgay(soNgayThueOto);
							
							System.out.println("-> Đã thêm xe " + otoDuocChon.getThuongHieu() + " (" + soNgayThueOto + " ngày thuê)\n" +
					                   "   Mã số: " + otoDuocChon.getMaSo() + "\n" +
					                   "   Biển số: " + otoDuocChon.getBienSo() + "\n" +
					                   "   Số ghế: " + otoDuocChon.getSoGhe() + " ghế\n" +
					                   "   Loại số: " + otoDuocChon.getLoaiso() + "\n" +
					                   "-> Vào danh sách thuê thành công!\n");
							
							//In ra tiền thuê của từng chiếc ô tô
							int tienOto = otoDuocChon.tinhTienThue();
					        System.out.print("-> Tiền thuê chiễc xe là: " + tienOto + "đ\n");
					        
					        //Tính tổng tiền thuê của từng chiếc ô tô
					        tongTienOto += tienOto;
						}
					} else {
						System.out.println("Không tìm thấy mã xe ô tô này trong kho, vui lòng nhập lại mã khác!");
						i--;
					}
					
				}
			
			// In ra tổng tiền thuê những chiếc ô tô
			System.out.println("Tổng tiền thuê " + soLuongOto + " chiếc xe máy là: " + tongTienOto);
			
			// --- THOÁT HOẶC XỬ LÝ LỰA CHỌN SAI ---
			} else if (chonLoaiXe == 0) {
				System.out.println("Bạn đã thoát chương trình!");
				break;
			} else {
				System.out.println("Lựa chọn không hợp lệ!");
				continue;
			}
		}
		sc.close();
	}
}
