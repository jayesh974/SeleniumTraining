package Calender;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.hc.core5.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalenderTest_Naveen {
	
	public static WebDriver driver;
	
	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
		driver.get("http://seleniumpractise.blogspot.com/2016/08/how-to-handle-calendar-in-selenium.html");	
		
		driver.findElement(By.xpath("//input[@id='datepicker']")).click();
		
		selectDate("29","February","2022");
		
//		selectDate("29","January","2022");
		
	
	}
	
	public static String[] getMonthYrVal(String monthYearVal)
	{
		return monthYearVal.split(" ");
	}
	
	public static void selectDate(String exDay ,String exMon , String exYr)
	{
		
//		if(exMon.equals("February") && Integer.parseInt(exDay)>29)
//		{
//			System.out.println("Wrong date :"+ exMon +" : "+exDay);
//			return ;
//		}
//		
		String monthYearVal = driver.findElement(By.cssSelector(".ui-datepicker-title")).getText();
		System.out.println(monthYearVal);
		
		while(!(getMonthYrVal(monthYearVal)[0].equals(exMon) && getMonthYrVal(monthYearVal)[1].equals(exYr)))
		{
			driver.findElement(By.xpath("//a[@title='Next']")).click();
			monthYearVal = driver.findElement(By.cssSelector(".ui-datepicker-title")).getText();
		}	
		
		try {
			driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']//a[text()='"+exDay+"']")).click();
		}
		catch(Exception e)
		{
			System.out.println("Wrong date :"+ exMon +" : "+exDay);
		}
		
		
	}

}
