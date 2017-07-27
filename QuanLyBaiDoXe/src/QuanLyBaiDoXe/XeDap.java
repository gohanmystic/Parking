package QuanLyBaiDoXe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XeDap extends XeHaiBanh {
	private String SoXe;

	public void HienThi() {
		System.out.println("So xe: " + SoXe);
		System.out.println("Thoi gian vao: " + getThoiGianVao());
		System.out.println("Thoi gian ra: " + getThoiGianRa());
		System.out.println("Tong tien: " + getTinhTien());
	}

	public XeDap(String soXe, String thoiGianVao, String thoiGianRa) {
		super(thoiGianVao, thoiGianRa);
		SoXe = soXe;
	}

	public String getSoXe() {
		return SoXe;
	}

	public void setSoXe(String soXe) {
		SoXe = soXe;
	}

	public Double TinhTienXeDap(Date d1, Date d2) {
		double hieuNgay = d2.getTime() - d1.getTime();
		Double ngay = hieuNgay / 86400000;
		return Math.ceil(ngay) * 1000;
	}

	public void InsertXeDap(Connection conn, SimpleDateFormat format, XeDap xeDap) {
		try {
			String query = "insert into XeDap values(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, xeDap.getSoXe());
			Timestamp dateVao = new Timestamp(format.parse(xeDap.getThoiGianVao()).getTime());
			ps.setTimestamp(2, dateVao);
			/*
			 * Date dateUtil = format.parse(xeDap.getThoiGianVao());
			 * java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
			 * ps.setDate(2, dateSql);
			 */

			if (xeDap.getThoiGianRa() != null) {
				Timestamp dateRa = new Timestamp(format.parse(xeDap.getThoiGianRa()).getTime());
				ps.setTimestamp(3, dateRa);
			} else {
				ps.setDate(3, null);
			}

			if (xeDap.getTinhTien() == null) {
				ps.setString(4, null);
			} else if (xeDap.getTinhTien() == -1.0) {
				ps.setString(4, "Chua xac dinh - Dang xu ly boi thuong");
			} else {
				ps.setString(4, xeDap.getTinhTien().toString());
			}
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
