package rms_Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

	public boolean checkDDListFromUIandExcel(WebDriver driver, String ddName) {
		int cellNum = 0;
		int counter = 0;
		boolean Result = false;
		try {
			// --stored all expected result in array
			ArrayList<String> ExpectedResult = new ArrayList<>();

			// --stored all actual result in array
			ArrayList<String> ActualResult = new ArrayList<>();

			// --Checking total columns of the 0th row in which values are exist
			int totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
			// --Getting column value for 0th row
			for (int i = 0; i < totalCells; i++) {
				String CurrentCellData = sheet.getRow(0).getCell(i).getStringCellValue();
				if (CurrentCellData.equalsIgnoreCase(ddName)) {
					cellNum = i;
					break;
				}
			}

			// --getting total rows of the sheet in which values are exist
			int totalRows = sheet.getPhysicalNumberOfRows();
			// --getting row values
			for (int j = 0; j < totalRows; j++) {
				XSSFCell cell = sheet.getRow(j).getCell(cellNum);
				if (cell == null) {
					break;
				}
				String cellData = sheet.getRow(j).getCell(cellNum).getStringCellValue();
				ExpectedResult.add(cellData);
				wb.close();

				List<WebElement> options = driver.findElements(By.id(cellData));
				for (WebElement ele : options) {
					String optionName = ele.getText();
					ActualResult.add(optionName);
				}

				// ---start comparison
				if (ExpectedResult.size() == ActualResult.size()) {
					for (int i = 0; i < ExpectedResult.size(); i++) {
						if (ExpectedResult.get(i).equals(ActualResult.get(i))) {
							continue;
						} else {
							System.out.println("missmatch between the values==" + "\n" + "Expected=="
									+ ExpectedResult.get(i) + "\n" + "Actual==" + ActualResult.get(i));
							counter++;

						}
					}
					if (counter == 0) {
						System.out.println("Values are matching");
						Result = true;
					} else {
						System.out.println("Values are not matching");
						Result = false;
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result;

	}
}
