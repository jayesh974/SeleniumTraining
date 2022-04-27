package Calender;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DatePicker3_SDET {
	
	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.redbus.in/");		
		
		DatePicker3_SDET.selectDate(driver, "28", "Jan", "2022");
		
		
	}

	public static void selectDate(WebDriver driver,String day,String mon, String yr) 
	{
		driver.findElement(By.cssSelector("#onward_cal")).click();
		
		while(true)
		{
		String monthyr = driver.findElement(By.cssSelector(".monthTitle")).getText();
		
		String[] arr = monthyr.split(" ");
		String month = arr[0];
		String year = arr[1];
		
		if(mon.equalsIgnoreCase(month) && yr.equalsIgnoreCase(year))
		{
			break;
		}
		else
			driver.findElement(By.cssSelector(".next")).click();
		}
		
		List<WebElement> dates = driver.findElements(By.xpath("//table[@class='rb-monthTable first last']//tr//td"));
		
		for (WebElement date : dates) 
		{
			String d = date.getText();
			
			if(d.equalsIgnoreCase(day))
			{
				date.click();
			}
		}
	}

}
