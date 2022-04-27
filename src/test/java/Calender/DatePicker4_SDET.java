package Calender;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DatePicker4_SDET {
	
	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.dummyticket.com/dummy-ticket-for-visa-application/");
		
		driver.findElement(By.cssSelector("#dob")).click();
		DatePicker4_SDET.HandlingDropdown(driver,driver.findElement(By.xpath("//select[@class='ui-datepicker-month']")),"Oct");	
		Thread.sleep(2000);
		DatePicker4_SDET.HandlingDropdown(driver, driver.findElement(By.xpath("//select[@class='ui-datepicker-year']")), "1990");
		DatePicker4_SDET.selectDate(driver, "29");
		
	}
	
	public static void HandlingDropdown(WebDriver driver ,WebElement  xpath ,String value)
	{
		Select dropdown = new Select(xpath);	
		dropdown.selectByVisibleText(value);
		
	}
	
	public static void selectDate(WebDriver driver, String date)
	{
		List<WebElement> allDates = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//td[contains(@data-event,'click')]"));
		
		for (WebElement d : allDates)
		{
			String text = d.getText();
			
			if(text.equals(date))
			{
				d.click();
			}
		}
	}

}
