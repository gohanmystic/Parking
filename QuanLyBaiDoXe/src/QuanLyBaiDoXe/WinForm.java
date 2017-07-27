package QuanLyBaiDoXe;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.border.TitledBorder;

public class WinForm extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinForm frame = new WinForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WinForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				XuLy xl = new XuLy();
				ArrayList<Xe> ds1 = xl.ReadFileInput1();
				ArrayList<Xe> ds2 = xl.ReadFileInput2AndUpdateArrayList(ds1);
				ArrayList<Xe> result = xl.TinhTien(ds2);
				
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("Loai xe");
				model.addColumn("Bien so xe");
				model.addColumn("So ve xe");
				model.addColumn("Thoi gian vao bai");
				model.addColumn("Thoi gian ra bai");
				model.addColumn("Tien thue bai");
				
				Object[] t = new Object[6];
				for (Xe xe : result) {
					if(xe instanceof XeDap) {
						t[0] = "0";
						t[1] = "Not Available";
						t[2] = ((XeDap) xe).getSoXe();
						t[3] = xe.getThoiGianVao();
						t[4] = xe.getThoiGianRa();
						t[5] = xe.getTinhTien();
					}else if (xe instanceof XeMay) {
						t[0] = "2";
						t[1] = ((XeMay) xe).getBienSo();
						t[2] = "Not Available";
						t[3] = xe.getThoiGianVao();
						t[4] = xe.getThoiGianRa();
						t[5] = xe.getTinhTien();
					}else if (xe instanceof XeOTo) {
						t[0] = "4";
						t[1] = ((XeOTo) xe).getBienSo();
						t[2] = "Not Available";
						t[3] = xe.getThoiGianVao();
						t[4] = xe.getThoiGianRa();
						if(xe.getTinhTien() == -1.0) {
							t[5] = "Chua xac dinh - Dang xu ly boi thuong";
						}else{
							t[5] = xe.getTinhTien();
						}
					}
					model.addRow(t);
				}
				table.setModel(model);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(30, 144, 255));
		tabbedPane.setBounds(10, 27, 509, 331);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(32, 178, 170)));
		tabbedPane.addTab("Thong ke chi tiet vao/ra bai do xe", null, scrollPane, null);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
	}
}
