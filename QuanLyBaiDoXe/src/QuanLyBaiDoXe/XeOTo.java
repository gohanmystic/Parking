package QuanLyBaiDoXe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XeOTo extends XeBonBanh {
	private String BienSo;

	public void HienThi() {
		System.out.println("Bien so: " + getBienSo());
		System.out.println("Thoi gian vao: " + getThoiGianVao());
		System.out.println("Thoi gian ra: " + getThoiGianRa());
		System.out.println("Tinh trang luc vao: " + getTinhTrangLucVao());
		System.out.println("Tinh trang luc ra: " + getTinhTrangLucRa());
		if (getTinhTien() != null) {
			if (getTinhTien() == -1.0) {
				System.out.println("Tong tien: Chua xac dinh - Dang xu ly boi thuong");
			} else {
				System.out.println("Tong tien: " + getTinhTien());
			}
		}

	}

	public XeOTo(String bienSo, String thoiGianVao, String thoiGianRa, String tinhTrangLucVao, String tinhTrangLucRa) {
		super(thoiGianVao, thoiGianRa, tinhTrangLucVao, tinhTrangLucRa);
		BienSo = bienSo;
	}

	public String getBienSo() {
		return BienSo;
	}

	public void setBienSo(String bienSo) {
		BienSo = bienSo;
	}
	
	
	public Double LamTronOTo(Double gio) {
		Double gioCeil = Math.ceil(gio);
		if (gioCeil - gio >= 0.5) {
			return gio.intValue() + 0.5;
		} else {
			return gioCeil;
		}
	}
	
	public Double TinhTienXeOTo (Date d1, Date d2) {
		if(this.getTinhTrangLucRa().equalsIgnoreCase("binh thuong")) {
			double hieuNgay = d2.getTime() - d1.getTime();
			Double ngay = hieuNgay / 86400000;
			Double gio = ngay * 24;
			return LamTronOTo(gio) * 10000;
		} else {
			return -1.0;
		}
	}
	
	public void InsertXeOTo(Connection conn, SimpleDateFormat format, XeOTo xeOTo) {
		try {
			String query = "insert into XeOTo values(?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, xeOTo.getBienSo());
			Timestamp dateVao = new Timestamp(format.parse(xeOTo.getThoiGianVao()).getTime());
			ps.setTimestamp(2, dateVao);
			/*
			 * Date dateUtil = format.parse(xeOTo.getThoiGianVao());
			 * java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
			 * ps.setDate(2, dateSql);
			 */
			ps.setString(3, xeOTo.getTinhTrangLucVao());
			if (xeOTo.getThoiGianRa() != null) {
				Timestamp dateRa = new Timestamp(format.parse(xeOTo.getThoiGianRa()).getTime());
				ps.setTimestamp(4, dateRa);
			} else {
				ps.setDate(4, null);
			}
			ps.setString(5, xeOTo.getTinhTrangLucRa());

			if (xeOTo.getTinhTien() == null) {
				ps.setString(6, null);
			} else if (xeOTo.getTinhTien() == -1.0) {
				ps.setString(6, "Chua xac dinh - Dang xu ly boi thuong");
			} else {
				ps.setString(6, xeOTo.getTinhTien().toString());
			}

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
