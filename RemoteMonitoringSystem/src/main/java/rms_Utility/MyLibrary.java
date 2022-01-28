package rms_Utility;

import rms_BasePackage.BaseInit;

public class MyLibrary extends BaseInit {
	public ExcelFileReader data;

	/*
	 * @DataProvider public Object[][] gettestData() { return getTestData(data,
	 * "Login"); }
	 */

	public Object[][] getTestData(ExcelFileReader data, String sheetName) {

		data = new ExcelFileReader(
				System.getProperty("user.dir") + "\\src\\main\\resources\\rms_TestData\\TestData.xlsx");

		int rows = data.totalRow(sheetName);
		int cols = data.totalColumn(sheetName);

		Object[][] myData = new Object[rows - 1][cols];

		for (int row = 1; row < rows; row++) {

			for (int col = 0; col < cols; col++) {

				myData[row - 1][col] = data.getData(sheetName, row, col);
			}
		}

		return myData;

		// return data;
	}

}
