package Calender;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CalenderTest_KrishnaSakinala {
	
	public static void main(String[] args) {
		
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.hyrtutorials.com/p/calendar-practice.html");
		
		JavascriptExecutor js = (JavascriptExecutor)driver ;
		js.executeScript("document.getElementById('first_date_picker').value='28/09/2023'");
		
	}

}
