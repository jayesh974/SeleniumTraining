package BootstrapDropdown;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BootstrapDemo {
	
	public static void main(String[] args) {
		
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.get("https://www.jquery-az.com/boots/demo.php?ex=63.0_2");
		
		driver.findElement(By.cssSelector("[type=button]")).click();
		
		List<WebElement> list = driver.findElements(By.xpath("//ul[contains(@class,'multiselect-container')]//li//a//label"));
		System.out.println(list.size());
	}

}
