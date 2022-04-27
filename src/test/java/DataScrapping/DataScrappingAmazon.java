package DataScrapping;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataScrappingAmazon {
	
	static ArrayList<String> productList ;

	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://www.amazon.com/");

		WebElement searchtextbox = driver.findElement(By.id("twotabsearchtextbox"));
		WebElement searchSubmitBtn = driver.findElement(By.id("nav-search-submit-button"));
		searchtextbox.clear();
		searchtextbox.sendKeys("Samsung Mobile");
		searchSubmitBtn.click();

//		List<WebElement> list = driver.findElements(By.cssSelector("[class ='s-main-slot s-result-list s-search-results sg-row'] h2 a span"));
		List<WebElement> list = driver.findElements(
				By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']//h2//a//span"));
		int size = list.size();
		System.out.println(size);

		WebElement noOfPages = driver.findElement(By.xpath("(//span[@class='s-pagination-strip']//span)[4]"));
         String sizeOfPages = noOfPages.getText();  
		System.out.println(sizeOfPages);
		Integer sizeInInt = Integer.valueOf(sizeOfPages);
		
		 productList = new ArrayList<String>();	

		int count = 0;	
		while (count<=sizeInInt) {
			{
				System.out.println("Page no :" + count);
				list = driver.findElements(By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']//h2//a//span"));				
				for (WebElement product : list) 
				{	
					String pro = product.getText();
					System.out.println(pro); 
					productList.add(pro);
					
				}
				count++;
				if(count==20)
				{
					break;
				}
				driver.findElement(
						By.xpath("//span[@class ='s-pagination-strip']//a[contains(@class,'s-pagination-next ')]")).click();
				Thread.sleep(3000);
				
			}
		}
		
		WritingExcel(productList);

	}
	
	public static void WritingExcel(ArrayList<String> productList ) throws IOException
	{
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Product info");
		
		int rowsize = productList.size();
		
		for(int r=0;r<rowsize ;r++)
		{
			XSSFRow row = sheet.createRow(r);
		
				XSSFCell cell = row.createCell(0);
			
				cell.setCellValue("Product Names with Configuration");
				Object value = productList.get(r);
				
				if(value instanceof String)
					cell.setCellValue((String) value);
				if(value instanceof Integer)
					cell.setCellValue((Integer) value);
				if(value instanceof Boolean)
					cell.setCellValue((Boolean) value);
			}
			
		
         String filePath = "C:\\Users\\Admin\\eclipse-workspace6\\SeleniumTraining\\src\\test\\resources\\ProductDetails.xlsx" ;
		
		FileOutputStream outstream = new FileOutputStream(filePath);
		workbook.write(outstream);
		
		outstream.close();
		
		System.out.println("ProductDetails.xlsx written successfully");
        }		
}

