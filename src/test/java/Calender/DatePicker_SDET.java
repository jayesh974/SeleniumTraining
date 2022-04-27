package Calender;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DatePicker_SDET {
	
//	 need a improvement
	
	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.phptravels.net/hotels");
		
		String CheckInmon_year = "January 2022";
		String CheckInday = "30";
		
		String CheckOutmon_year = "May 2022";
		String CheckOutday = "21";
		
		WebElement chekin = driver.findElement(By.cssSelector(".form-group>#checkin"));
		chekin.click();
		
		DatePicker_SDET.selectCheckinDate(driver, CheckInmon_year, CheckInday, 1);
		
//		WebElement checkout = driver.findElement(By.xpath("//input[@id='checkout']"));
//		checkout.click();
//		
//		DatePicker_SDET.selectCheckinDate(driver, CheckOutmon_year, CheckOutday, 2);
			
				
	}
	
	public static void selectCheckinDate(WebDriver driver ,String mon_year,String day, int order)
	{
		while(true)
		{
			String text = driver.findElement(By.xpath("(//div[@class='datepicker-days']//th[@class='switch'])["+order+"]")).getText();
			System.out.println(text);
			
			if(text.contains(mon_year))
			{
				break;
			}
			else
			{
				driver.findElement(By.xpath("(//div[@class='datepicker-days']//th[@class='next'])["+order+"]")).click();
			}		 
		}
//		driver.findElement(By.xpath("(//div[@class=\"datepicker-days\"])["+order+"]//tbody//tr//td[@class='day ' and contains(text(),'"+day+"')]")).click();
		driver.findElement(By.xpath("(//div[@class='datepicker-days'])["+order+"]//tbody//tr//td[contains(@class,'day') and contains(text(),'"+day+"')]")).click();
	}

}
