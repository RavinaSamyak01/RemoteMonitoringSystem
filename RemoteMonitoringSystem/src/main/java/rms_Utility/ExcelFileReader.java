package rms_Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileReader {

	static XSSFWorkbook wb;
	XSSFSheet sheet;
	File file;
	String filePath;
	FileInputStream fi;

	public ExcelFileReader(String filePath) {

		this.filePath = filePath;

		try {

			file = new File(filePath);
			fi = new FileInputStream(file);
			wb = new XSSFWorkbook(fi);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int totalRow(String sheetName) {

		sheet = wb.getSheet(sheetName);

		int rows = sheet.getLastRowNum() + 1;
		return rows;

	}

	public int totalColumn(String sheetName) {

		sheet = wb.getSheet(sheetName);
		int cols = sheet.getRow(0).getLastCellNum();
		// cols = cols - 1; //Edited by Tanmay
		// cols = cols + 1;

		return cols;
	}

	public static String getData(String sheetName, int rowNum, int columnNum) {

		XSSFCell cell = wb.getSheet(sheetName).getRow(rowNum).getCell(columnNum);

		 cell.setCellType(CellType.STRING);

		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(columnNum).getStringCellValue();
		return data;

	}

	public void setData(String sheetName, int rowNum, int columnNum, String data) {

		System.out.println("Name of the sheet is=" + sheetName);
		System.out.println("Number of rows are=" + rowNum);
		System.out.println("Number of columns are=" + columnNum);
		System.out.println("Data is=" + data);

		try {

			file = new File(filePath);
			fi = new FileInputStream(file);
			wb = new XSSFWorkbook(fi);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Path of the file is=" + filePath);
		wb.getSheet(sheetName).getRow(rowNum).createCell(columnNum).setCellValue(data);

		try {
			FileOutputStream fo = new FileOutputStream(new File(filePath));

			wb.write(fo);
			wb.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
