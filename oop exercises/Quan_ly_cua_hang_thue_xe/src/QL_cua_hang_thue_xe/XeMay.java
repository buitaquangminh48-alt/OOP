package QL_cua_hang_thue_xe;

/**
 * Lớp biểu diễn Xe Máy, kế thừa từ PhuongTien.
 * Giá thuê xe máy phụ thuộc vào Dung tích xi lanh (cc).
 */
public class XeMay extends PhuongTien {
	private int DungTichXiLanh; // Dung tích xi lanh tính bằng cc (VD: 50, 100, 125)
	
	public XeMay(String ThuongHieu, int MaSo, String BienSo, int GiaThue, /*int Ngay*/ int DungTichXiLanh) {
		super(ThuongHieu, MaSo, BienSo, GiaThue/*, Ngay*/); // ❌ LỖI: PhuongTien không có nhận tham số 'Ngay' vs 'GiaThue' nữa!
		this.DungTichXiLanh = DungTichXiLanh;
	}
	
	/**
	 * Tính tiền thuê xe máy theo quy tắc dung tích xi lanh:
	 * - <= 50cc: Giá gốc
	 * - 51cc - 109cc: Tăng 10%
	 * - >= 110cc: Tăng 20%
	 */
	@Override
	public int tinhTienThue() {
		if (DungTichXiLanh <= 50) {
			return getGiaThue() * getNgay();
		} else if (DungTichXiLanh < 110) {
			return (int)(getGiaThue() * 1.1 * getNgay());
		} else {
			return (int)(getGiaThue() * 1.2 * getNgay());
		}
	}
	
	public int getDungTichXiLanh() {
	    return DungTichXiLanh;
	}
	
	/**
	 * Lớp hỗ trợ xuất dữ liệu xe máy dạng bảng định dạng cố định.
	 */
	public static class OutputXeMay {
		public void displayXeMay(XeMay phuongtien) {
			// In thông tin theo dạng bảng với độ rộng cột cố định.
	        System.out.printf(
	        	"%-20s %-10s %-20s %-15s %-5s %-10s%n",
	            phuongtien.getThuongHieu(),
	            phuongtien.getMaSo(),
	            phuongtien.getBienSo(),
	            phuongtien.getGiaThue(),
	            phuongtien.getNgay(),
	            phuongtien.getDungTichXiLanh()
	        );
		}
	}
}
