package DataProviderInTestng;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderWithExcelTest {


	@Test(dataProvider = "getData")
	public void test1(String username, String password, String fname, String lname) {
		System.out.println(username);
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/excel/testdataFordataproviderDemoTest.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("testing1");

		int rowcount = sheet.getLastRowNum();
		System.out.println(rowcount);
		int columncount = sheet.getRow(0).getLastCellNum();
		System.out.println(columncount);

		Object[][] data = new Object[rowcount][columncount];

		for (int i = 1; i <=rowcount; i++) {
			for (int j = 0; j <columncount; j++) {
				data[i-1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
			}
		}
		return data;
	}
	
	
	@Test(dataProvider = "getData1")
	public void test2(Map<String, String> map) {
		System.out.println(map.get("username"));
	}

	@DataProvider
	public Object[] getData1() throws IOException {

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/excel/testdataFordataproviderDemoTest.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("testing1");

		int rowcount = sheet.getLastRowNum();
		int columncount = sheet.getRow(0).getLastCellNum();

		Object[] data = new Object[rowcount];
		Map<String, String> map;

		for (int i = 1; i <= rowcount; i++) {
			map = new HashMap<>();
			for (int j = 0; j < columncount; j++) {

				String key = sheet.getRow(0).getCell(j).getStringCellValue();
				String value = sheet.getRow(i).getCell(j).getStringCellValue();
				map.put(key, value);
				data[i - 1] = map;
			}
		}
		return data;

	}
	
}
