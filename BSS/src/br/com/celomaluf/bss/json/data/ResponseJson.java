package br.com.celomaluf.bss.json.data;

import java.io.Serializable;
import java.util.List;


public class ResponseJson implements Serializable {

	private static final long serialVersionUID = -4309806121515288990L;
	private List<Row> rows; 
	
	public List<Row> getRows() {
		return rows;
	}
	public void setRows(List<Row> rows) {
		this.rows = rows;
	}
}
