package QL_cua_hang_thue_xe;

/**
 * Lớp cơ sở trừu tượng biểu diễn một phương tiện cho thuê chung.
 * Chứa các thuộc tính cơ bản như thương hiệu, mã số, biển số, giá thuê và trạng thái thuê.
 */
public abstract class PhuongTien {
	private String ThuongHieu;
	private int MaSo;
	private String BienSo;
	private int GiaThue;
	private int Ngay;
	private boolean XeDaThue; // Trạng thái: true - đã có người thuê, false - còn trống
	
	/**
	 * Khởi tạo phương tiện mới với trạng thái mặc định là chưa được thuê.
	 */
	public PhuongTien(String ThuongHieu,int MaSo, String BienSo, int GiaThue) {
		this.ThuongHieu = ThuongHieu;
		this.MaSo = MaSo;
		this.BienSo = BienSo;
		this.GiaThue = GiaThue;
		this.XeDaThue = false; // Mặc định xe mới nhập kho chưa ai thuê
	}
	
	// --- CÁC HÀM GETTER / SETTER ---
	public String getThuongHieu() { return ThuongHieu; }
	
	public int getMaSo() { return MaSo; } 
	
	public String getBienSo() { return BienSo; }
	
	public int getGiaThue() { return GiaThue; }
	
	public int getNgay() { return Ngay; }
	public void setNgay(int ngay) { this.Ngay = ngay; }
	
	public boolean isXeDaThue() { return XeDaThue; }
	public void setXeDaThue(boolean DaThue) { this.XeDaThue = DaThue; }
	
	/**
	 * Phương thức trừu tượng tính tổng tiền thuê xe dựa trên phụ phí riêng của từng loại xe.
	 * Sẽ được các lớp con (XeMay, Oto) ghi đè (Override) lại.
	 */
	public abstract int tinhTienThue();
}
