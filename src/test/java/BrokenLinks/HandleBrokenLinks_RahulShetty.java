package BrokenLinks;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
public class HandleBrokenLinks_RahulShetty {
	
	public static void main(String[] args) throws MalformedURLException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.get("http://www.deadlinkcity.com/");
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		
		List<WebElement> lists = driver.findElements(By.tagName("a"));
		
		int brokenLink = 0;
		
		for(WebElement element : lists)
		{
			String url =element.getAttribute("href");
			if(url==null || url.isEmpty())
			{
				System.out.println("URL is empty");
				continue;
			}
			
			URL link = new URL(url);       // convert the string url into the URL 
			try 
			{
				 HttpURLConnection httpconn =(HttpURLConnection) link.openConnection();     
				httpconn.connect();     // connect the url to server
				
				if(httpconn.getResponseCode()>=400)
				{
					System.err.println(httpconn.getResponseCode()+":"+url+" is Broken Link");
					brokenLink++;	
				}
				else
				{
					System.out.println(httpconn.getResponseCode()+":"+url+" is valid link");
				}
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		
		System.out.println("Number of Broken Link "+ brokenLink );
		driver.quit();
			
	}

}
