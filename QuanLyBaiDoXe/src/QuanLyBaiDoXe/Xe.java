package QuanLyBaiDoXe;

public abstract class Xe {
	private Long CMND;
	private String HoTen;
	private boolean GioiTinh;
	private String MaSoCanHo;
	private String HangSanXuat;
	private String MauSon;
	private String ThoiGianVao;
	private String ThoiGianRa;
	private Double TinhTien;
	public Xe(String thoiGianVao, String thoiGianRa) {
		super();
		ThoiGianVao = thoiGianVao;
		ThoiGianRa = thoiGianRa;
	}

	public String getThoiGianVao() {
		return ThoiGianVao;
	}

	public void setThoiGianVao(String thoiGianVao) {
		ThoiGianVao = thoiGianVao;
	}

	public String getThoiGianRa() {
		return ThoiGianRa;
	}

	public void setThoiGianRa(String thoiGianRa) {
		ThoiGianRa = thoiGianRa;
	}

	public Double getTinhTien() {
		return TinhTien;
	}

	public void setTinhTien(Double tinhTien) {
		TinhTien = tinhTien;
	}
}
