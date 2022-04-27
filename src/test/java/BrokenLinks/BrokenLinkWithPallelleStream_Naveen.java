package BrokenLinks;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLinkWithPallelleStream_Naveen {
	
	public static void main(String[] args) {
		

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.amazon.com/");
		
		List<WebElement> list = driver.findElements(By.tagName("a"));
		
		System.out.println("No of Links "+list.size());
		
		List<String> urlList = new ArrayList<String>();
		
		for (WebElement e : list)
		{
			String url = e.getAttribute("href");
			urlList.add(url);
		}
		
		long stTime = System.currentTimeMillis();
		urlList.parallelStream().forEach(e -> {
			try {
				checkBrokennLink(e);
			} catch (IOException e1) {
				
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				
				e1.printStackTrace();
			}
		});
		long endTime = System.currentTimeMillis();
		
		System.out.println("Total Time Taken :"+(endTime-stTime));
		
		driver.quit();
	}
	
	public static void checkBrokennLink(String linkurl) throws IOException, InterruptedException
	{
		try {
		URL url = new URL(linkurl);		
		HttpURLConnection httpurlconnection = (HttpURLConnection) url.openConnection();
		Thread.sleep(3000);
		httpurlconnection.connect();
		
		if(httpurlconnection.getResponseCode()>=400)
		{
			System.err.println(linkurl+"-----"+httpurlconnection.getResponseCode()+" is a broken link");
		}
		else {
			System.out.println(linkurl+"-----"+httpurlconnection.getResponseCode());
		}
		}
		catch(Exception e)
		{
			
		}
	}

}
