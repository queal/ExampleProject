package com.example.xls;

import java.util.Map;

public class RowData {

	private int index;
	private Map<Integer, String> rowDataUnit;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Map<Integer, String> getRowDataUnit() {
		return rowDataUnit;
	}

	public void setRowDataUnit(Map<Integer, String> rowDataUnit) {
		this.rowDataUnit = rowDataUnit;
	}

	@Override
	public String toString() {
		return "RowData [index=" + index + ", rowDataUnit=" + rowDataUnit + "]";
	}

}
