package QL_cua_hang_thue_xe;

/**
 * Lớp biểu diễn Xe Ô tô, kế thừa từ PhuongTien.
 * Giá thuê ô tô phụ thuộc vào Số ghế và Loại số (Số sàn / Số tự động).
 */
public class Oto extends PhuongTien {
	
	// Định nghĩa tập hợp các loại hộp số cố định
	/* Mục đích dùng enum: Ràng buộc một biến chỉ được mang 1 trong số các giá trị cố định đã liệt kê.*/
	public enum LoaiSo {
		So_San,
		Tu_Dong
	}
	
	private byte SoGhe;
	private LoaiSo loaiso;
	
	public Oto(String ThuongHieu, int MaSo, String BienSo, int GiaThue, byte SoGhe, LoaiSo loaiso) {
		super(ThuongHieu, MaSo, BienSo, GiaThue);
		this.SoGhe = SoGhe;
		this.loaiso = loaiso;
	}
	
	/**
	 * Tính tiền thuê ô tô theo số ghế và hộp số:
	 * - Số sàn 4-5 chỗ: Giá gốc
	 * - Số sàn >= 6 chỗ: Tăng 20%
	 * - Số tự động 4-5 chỗ: Tăng 15%
	 * - Số tự động >= 6 chỗ: Tăng 35%
	 */
	@Override
	public int tinhTienThue() {
		if (loaiso == LoaiSo.So_San) {
			if (SoGhe == 4 || SoGhe == 5) {
				return getGiaThue() * getNgay();
			} else {
				return (int)(getGiaThue() * getNgay() * 1.2);
			}
		} else {
			if (SoGhe == 4 || SoGhe == 5) {
				return(int)(getGiaThue()*getNgay()*1.15);
			} else {
				return(int)(getGiaThue()*getNgay()*1.35);
			}
		}
	}
	
	// --- CÁC HÀM GETTER / SETTER ---
	public byte getSoGhe() { return SoGhe; }
	public void setSoGhe(byte SoGhe) {
		if (SoGhe > 0 && SoGhe <= 50) {
			this.SoGhe = SoGhe;
		}
	}

	public LoaiSo getLoaiso() { return loaiso; }
	public void setLoaiso(LoaiSo loaiso) { this.loaiso = loaiso; }
	
	/**
	 * Lớp hỗ trợ xuất dữ liệu ô tô dạng bảng định dạng cố định.
	 */
	public static class OutputOto {
		public void displayOto(Oto phuongtien) {
			System.out.printf(
					"%-20s %-10s %-20s %-15s %-5s %-10s %-10s%n",
					phuongtien.getThuongHieu(),
					phuongtien.getMaSo(),
					phuongtien.getBienSo(),
					phuongtien.getGiaThue(),
					phuongtien.getNgay(),
					phuongtien.getSoGhe(),
					phuongtien.getLoaiso()
			);
		}
	}
}
