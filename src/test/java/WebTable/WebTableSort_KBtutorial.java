package WebTable;

import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTableSort_KBtutorial {

	public static void main(String[] args) {

		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.get("https://www.w3schools.com/howto/howto_js_sort_table.asp");

		List<WebElement> names = driver.findElements(By.xpath("//table[@id='myTable']//tbody//tr//td[1]"));

		String[] beforesort_name = new String[names.size()];

//		Get the text into the array
		for (int i = 0; i < names.size(); i++) {
			beforesort_name[i] = names.get(i).getText().trim();
		}
		System.out.println("*******Before sortining****** ");
		Print(beforesort_name);

//		sort function
		Arrays.sort(beforesort_name);
		System.out.println("*******After sortining by java****** ");
		Print(beforesort_name);

//		After click on sort btn
		WebElement sortBtn = driver.findElement(By.xpath("//button[@class='ws-btn w3-dark-grey']"));
		sortBtn.click();

		names = driver.findElements(By.xpath("//table[@id='myTable']//tbody//tr//td[1]"));

		String[] aftersort_name = new String[names.size()];

//		Get the text into the array
		for (int i = 0; i < names.size(); i++) {
			aftersort_name[i] = names.get(i).getText().trim();
		}
		System.out.println("******After clicking sorting Btn*****");
		Print(aftersort_name);
		Assert.assertEquals(beforesort_name, aftersort_name);
		System.out.println("Sort verified properly......");

	}

	public static void Print(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

}
