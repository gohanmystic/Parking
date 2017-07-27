package QuanLyBaiDoXe;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class XuLy {

	/**
	 * Doc file input1.txt, luu du lieu vao ArrayList<Xe> dsXe
	 * 
	 * @return ArrayList<Xe> dsXe
	 * 
	 */
	public ArrayList<Xe> ReadFileInput1() {
		ArrayList<Xe> dsXe = new ArrayList<>();
		try {
			FileInputStream fis = new FileInputStream("input1.txt");
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader brInput1 = new BufferedReader(isr);

			while (true) {
				String st = brInput1.readLine();
				if (st == null || st.isEmpty())
					break;

				String[] ds = st.split("[;]");
				if (ds[0].equals("0") && CheckDuLieuXeDap(ds)) {
					XeDap xeDap = new XeDap(ds[2], ds[3], null);
					dsXe.add(xeDap);
				} else if (ds[0].equals("2") && CheckDuLieuXeMay(ds)) {
					XeMay xeMay = new XeMay(ds[1], ds[3], null);
					dsXe.add(xeMay);
				} else if (ds[0].equals("4") && CheckDuLieuXeOTo(ds)) {
					XeOTo xeOTo = new XeOTo(ds[1], ds[3], null, ds[4], null);
					dsXe.add(xeOTo);
				}
			}
			brInput1.close();
			return dsXe;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Hien thi tat ca cac xe
	 * 
	 * @param dsXe
	 */
	public void HienThi(ArrayList<Xe> dsXe) {
		for (Xe xe : dsXe) {
			if (xe instanceof XeDap) {
				System.out.println("Xe Dap: ");
				XeDap xeDap = (XeDap) xe;
				xeDap.HienThi();
			} else if (xe instanceof XeOTo) {
				System.out.println("Xe O To: ");
				XeOTo xeOTo = (XeOTo) xe;
				xeOTo.HienThi();
			} else if (xe instanceof XeMay) {
				System.out.println("Xe May: ");
				XeMay xeMay = (XeMay) xe;
				xeMay.HienThi();
			}
		}
	}

	public boolean CheckDuLieuXeDap(String[] ds) {
		if (!ds[1].equalsIgnoreCase("Not Available"))
			return false;
		if (!ds[4].equalsIgnoreCase("Not Available"))
			return false;
		return true;
	}

	public boolean CheckDuLieuXeMay(String[] ds) {
		if (!ds[2].equalsIgnoreCase("Not Available"))
			return false;
		if (!ds[4].equalsIgnoreCase("Not Available"))
			return false;
		return true;
	}

	public boolean CheckDuLieuXeOTo(String[] ds) {
		if (!ds[2].equalsIgnoreCase("Not Available"))
			return false;
		return true;
	}

	/**
	 * Xoa bang trong co so du lieu
	 * 
	 * @param conn
	 * @param table
	 */
	public void DropTable(Connection conn, String table) {
		try {
			String query = "delete from " + table;
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void ReadFileAndInsertIntoDatabase(ArrayList<Xe> dsXe) {
		try {
			ConnectDatabase connDB = new ConnectDatabase();
			connDB.Connection();
			Connection conn = connDB.conn;
			SimpleDateFormat format = new SimpleDateFormat("hh:mm dd/MM/yyyy");
			DropTable(connDB.conn, "XeDap");
			DropTable(connDB.conn, "XeMay");
			DropTable(connDB.conn, "XeOTo");

			for (Xe xe : dsXe) {
				if (xe instanceof XeDap) {
					XeDap xeDap = (XeDap) xe;
					xeDap.InsertXeDap(conn, format, xeDap);
				} else if (xe instanceof XeMay) {
					XeMay xeMay = (XeMay) xe;
					xeMay.InsertXeMay(conn, format, xeMay);
				} else if (xe instanceof XeOTo) {
					XeOTo xeOTo = (XeOTo) xe;
					xeOTo.InsertXeOTo(conn, format, xeOTo);
				}
			}
			System.out.println("Them thanh cong!");
			connDB.conn.close();
		} catch (Exception e) {
			System.out.println("Them that bai!");
			e.printStackTrace();
		}
	}

	public ArrayList<Xe> ReadFileInput2AndUpdateArrayList(ArrayList<Xe> dsXe) {
		try {
			FileInputStream fis = new FileInputStream("input2.txt");
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader brInput2 = new BufferedReader(isr);

			while (true) {
				String st = brInput2.readLine();
				if (st == null || st.isEmpty())
					break;
				String[] ds = st.split("[;]");
				if (ds[0].equals("0") && CheckDuLieuXeDap(ds)) {
					for (Xe xe : dsXe) {
						if (xe instanceof XeDap) {
							if (((XeDap) xe).getSoXe().equals(ds[2])) {
								xe.setThoiGianRa(ds[3]);
								break;
							}
						}
					}
				} else if (ds[0].equals("2") && CheckDuLieuXeMay(ds)) {
					for (Xe xe : dsXe) {
						if (xe instanceof XeMay) {
							if (((XeMay) xe).getBienSo().equals(ds[1])) {
								xe.setThoiGianRa(ds[3]);
								break;
							}
						}
					}
				} else if (ds[0].equals("4") && CheckDuLieuXeOTo(ds)) {
					for (Xe xe : dsXe) {
						if (xe instanceof XeOTo) {
							if (((XeOTo) xe).getBienSo().equals(ds[1])) {
								xe.setThoiGianRa(ds[3]);
								((XeOTo) xe).setTinhTrangLucRa(ds[4]);
								break;
							}
						}
					}
				}
			}
			brInput2.close();
			return dsXe;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Xe> TinhTien(ArrayList<Xe> dsXe) {
		SimpleDateFormat format = new SimpleDateFormat("hh:mm dd/MM/yyyy");
		try {
			for (Xe xe : dsXe) {

				Date d1 = format.parse(xe.getThoiGianVao());
				Date d2 = format.parse(xe.getThoiGianRa());

				if (xe instanceof XeDap) {
					XeDap xeDap = (XeDap) xe;
					xe.setTinhTien(xeDap.TinhTienXeDap(d1, d2));
				} else if (xe instanceof XeMay) {
					XeMay xeMay = (XeMay) xe;
					xe.setTinhTien(xeMay.TinhTienXeMay(d1, d2));
				} else if (xe instanceof XeOTo) {
					XeOTo xeOTo = (XeOTo) xe;
					xe.setTinhTien(xeOTo.TinhTienXeOTo(d1, d2));
				}
			}
			return dsXe;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
