package QuanLyBaiDoXe;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDatabase {
	public Connection conn;
	
	public void Connection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://GOHANMYSTIC:1433;databaseName=QuanLyDoXe;user=sa;password=1234";
			
			conn = DriverManager.getConnection(url);
			System.out.println("Da ket noi");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
