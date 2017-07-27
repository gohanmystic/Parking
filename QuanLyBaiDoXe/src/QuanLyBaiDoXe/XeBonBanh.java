package QuanLyBaiDoXe;

public abstract class XeBonBanh extends Xe {
	private String TinhTrangLucVao;
	private String TinhTrangLucRa;

	public XeBonBanh(String thoiGianVao, String thoiGianRa, String tinhTrangLucVao, String tinhTrangLucRa) {
		super(thoiGianVao, thoiGianRa);
		TinhTrangLucVao = tinhTrangLucVao;
		TinhTrangLucRa = tinhTrangLucRa;
	}

	public String getTinhTrangLucVao() {
		return TinhTrangLucVao;
	}

	public void setTinhTrangLucVao(String tinhTrangLucVao) {
		TinhTrangLucVao = tinhTrangLucVao;
	}

	public String getTinhTrangLucRa() {
		return TinhTrangLucRa;
	}

	public void setTinhTrangLucRa(String tinhTrangLucRa) {
		TinhTrangLucRa = tinhTrangLucRa;
	}
	
}
