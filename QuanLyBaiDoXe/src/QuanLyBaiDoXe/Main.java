package QuanLyBaiDoXe;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		XuLy xl = new XuLy();
		
		ArrayList<Xe> dsInput1 = xl.ReadFileInput1();
	
		ArrayList<Xe> dsInput2 = xl.ReadFileInput2AndUpdateArrayList(dsInput1);
	
		ArrayList<Xe> dsTinhTien = xl.TinhTien(dsInput2);
		xl.ReadFileAndInsertIntoDatabase(dsTinhTien);
	}
}
