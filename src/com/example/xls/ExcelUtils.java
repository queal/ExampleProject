package com.example.xls;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static void writeLine(File file, List<RowData> rowDataList) {
		XSSFWorkbook xwb = null;
		FileOutputStream fos = null;
		try {
			xwb = new XSSFWorkbook();
			fos = new FileOutputStream(file);

			XSSFSheet sheet = xwb.createSheet();

			int rowIndex = 0;

			for (RowData rowData : rowDataList) {
				XSSFRow row = sheet.createRow(rowIndex);

				for (Integer key : rowData.getRowDataUnit().keySet()) {
					XSSFCell cell = row.createCell(key);
					cell.setCellValue(rowData.getRowDataUnit().get(key));
				}
				rowIndex++;
			}

			xwb.write(fos);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		} finally {
			if (xwb != null) {
				try {
					xwb.close();
				} catch (IOException e) {
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
		}

	}

	public static List<RowData> readLine(File file) {
		List<RowData> rowDataList = new ArrayList<RowData>();
		XSSFWorkbook xwb = null;
		try {
			xwb = new XSSFWorkbook(file); // xlsx

			XSSFSheet sheet = xwb.getSheetAt(0);

			for (int rowIndex = sheet.getFirstRowNum(); rowIndex <= sheet
					.getLastRowNum(); rowIndex++) {
				XSSFRow row = sheet.getRow(rowIndex);

				RowData rowData = new RowData();
				Map<Integer, String> rowDataUnit = new HashMap<Integer, String>();

				for (int colIndex = row.getFirstCellNum(); colIndex < row
						.getLastCellNum(); colIndex++) {
					rowDataUnit.put(colIndex, row.getCell(colIndex)
							.getRichStringCellValue().toString());
				}
				rowData.setIndex(rowIndex);
				rowData.setRowDataUnit(rowDataUnit);
				rowDataList.add(rowData);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		} finally {
			if (xwb != null) {
				try {
					xwb.close();
				} catch (IOException e) {
				}
			}
		}

		return rowDataList;
	}

}
