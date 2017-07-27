package QuanLyBaiDoXe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XeMay extends XeHaiBanh{
	private String BienSo;
	
	public void HienThi() {
		System.out.println("Bien so: " + getBienSo());
		System.out.println("Thoi gian vao: " + getThoiGianVao());
		System.out.println("Thoi gian ra: " + getThoiGianRa());
		System.out.println("Tong tien: " + getTinhTien());
	}
	
	public XeMay(String bienSo, String thoiGianVao, String thoiGianRa) {
		super(thoiGianVao, thoiGianRa);
		BienSo = bienSo;
	}

	public String getBienSo() {
		return BienSo;
	}

	public void setBienSo(String bienSo) {
		BienSo = bienSo;
	}
	
	public Double TinhTienXeMay (Date d1, Date d2) {
		double hieuNgay = d2.getTime() - d1.getTime();
		Double ngay = hieuNgay / 86400000;
		return Math.ceil(ngay) * 3000; 
	}
	
	public void InsertXeMay(Connection conn, SimpleDateFormat format, XeMay xeMay) {
		try {
			String query = "insert into XeMay values(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, xeMay.getBienSo());
			Timestamp dateVao = new Timestamp(format.parse(xeMay.getThoiGianVao()).getTime());
			ps.setTimestamp(2, dateVao);
			/*Date dateUtil = format.parse(xeMay.getThoiGianVao());
			java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
			ps.setDate(2, dateSql);*/
			if(xeMay.getThoiGianRa() != null) {
				Timestamp dateRa = new Timestamp(format.parse(xeMay.getThoiGianRa()).getTime());
				ps.setTimestamp(3, dateRa);
			} else {
				ps.setDate(3, null);
			}
			
			if(xeMay.getTinhTien() == null) {
				ps.setString(4, null);
			} else if (xeMay.getTinhTien() == -1.0){
				ps.setString(4, "Chua xac dinh - Dang xu ly boi thuong");
			} else {
				ps.setString(4, xeMay.getTinhTien().toString());
			}
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
