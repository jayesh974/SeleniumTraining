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

public class HandingPaginationUsingForLoop_KrishnaSakinala {
	
	/* Manual Test Case
	 * 1. Open the crome Browser
	 * 2.Naviaget to https://datatables.net/   URL
	 * 3.Capture the all names in Webtables
	 * 4.Print all the names
	 * 5.Comapare the count showing on the WebTable with the captured names count.
 	 */
	
	@Test
	public void PaginationUsingForLoop() throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://datatables.net/");
		
		int paginationSize = driver.findElements(By.cssSelector("#example_paginate>span>a")).size();
		
		List<String> names = new ArrayList<String>();
		
		for(int p=1 ; p<=paginationSize ; p++)
		{
			String paginationSelector = "#example_paginate>span>a:nth-child("+p+")";
			driver.findElement(By.cssSelector(paginationSelector)).click();
			
			List<WebElement> namesElements = driver.findElements(By.cssSelector("#example>tbody>tr>td:nth-child(1)"));
			
			for (WebElement namesElement : namesElements)
			{
				names.add(namesElement.getText());
			}
				
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
