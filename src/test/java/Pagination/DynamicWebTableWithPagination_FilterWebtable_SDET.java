package Pagination;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DynamicWebTableWithPagination_FilterWebtable_SDET {

	public static void main(String[] args) {
	  
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://demo.opencart.com/admin/");
		
		WebElement username = driver.findElement(By.id("input-username"));
		username.clear();
		username.sendKeys("demo");
		
		WebElement password = driver.findElement(By.id("input-password"));
		password.clear();
		password.sendKeys("demo");
		
		WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));
		loginBtn.click();
		
		WebElement sales = driver.findElement(By.xpath("//*[@id=\"menu-sale\"]/a"));
		sales.click();
		
		WebElement orders = driver.findElement(By.xpath("(//li[@id='menu-sale']//ul[@id='collapse26']/li/a)[1]"));
		orders.click();
		
//		Table
//		Find the total no of pages in table
		
		String text = driver.findElement(By.xpath("//div[@class='col-sm-6 text-right']")).getText();
		System.out.println(text);
		
		int totol_pages = Integer.valueOf(text.substring(text.indexOf("(")+1,text.indexOf("Pages")-1));
		 System.out.println("Total No of Pages :"+totol_pages);
		 
		for(int p=1 ; p<=10 ; p++)
		{
			WebElement acive_page = driver.findElement(By.xpath("//ul[@class='pagination']//li/span"));
			System.out.println("Active page :"+acive_page.getText());
			acive_page.click();
			
			int row = driver.findElements(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr")).size();
			System.out.println("Total no of rows :"+row);
			
//			Read the all row from each page
			for(int i=1 ; i<=row; i++)
			{
			
				String orderID = driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr["+i+"]//td[2]")).getText();
				String customer = driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr["+i+"]//td[3]")).getText();
				String status = driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr["+i+"]//td[4]")).getText();
				
				if(status.equals("Pending"))
				{
					 System.out.println(orderID+"        "+customer+"        "+status);
				}
			   
			}
			
			String pageno = Integer.toString(p+1);
			driver.findElement(By.xpath("//ul[@class='pagination']//li//a[text()='"+pageno+"']")).click();
						
		}

	}

}
