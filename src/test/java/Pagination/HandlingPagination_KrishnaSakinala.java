package Pagination;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingPagination_KrishnaSakinala {
	
	@Test
	public void HandlingPagination() throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://datatables.net/");
			
		List<WebElement> namesElements = driver.findElements(By.cssSelector("#example>tbody>tr>td:nth-child(1)"));
		
		List<String> names = new ArrayList<String>();
		
		for (WebElement namesElement : namesElements) 
		{
			names.add(namesElement.getText());
		}
		
		String nextBtnClassName = driver.findElement(By.cssSelector("#example_next")).getAttribute("class");
	
		while(!nextBtnClassName.contains("disabled"))
		{
			driver.findElement(By.cssSelector("#example_next")).click();
			
			namesElements = driver.findElements(By.cssSelector("#example>tbody>tr>td:nth-child(1)"));
			
			for (WebElement namesElement : namesElements) 
			{
				names.add(namesElement.getText());
			}
			
			nextBtnClassName = driver.findElement(By.cssSelector("#example_next")).getAttribute("class");
		}
		
		for (String name : names) {
			System.out.println(name);
		}
		
		int totalnames = names.size();
		System.out.println("Total no of names "+totalnames);
		
		String displayedCount = driver.findElement(By.cssSelector("#example_info")).getText().split(" ")[5];
		System.out.println("Total no of displayed names count "+displayedCount);
		
		Assert.assertEquals(String.valueOf(totalnames), displayedCount);
		
		Thread.sleep(3000);
		
		driver.quit();
		
	}

}
