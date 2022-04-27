package DataProviderInTestng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest1 {

	@Test(dataProvider = "getData3")
	public void test1(Employee emp) {
        System.out.println(emp.getId());
		System.out.println("entering username");
		System.out.println("entering password");
	}

	@DataProvider                     // single dimension
	public String[] getData1() {

		String[] a = { "jayesh1", "sachine1", "shubham1" };
		return a;
	}
	
	@DataProvider                    // two dimension
	public Object[][] getData2() {
		
		return new Object[][] {
				
				{"jayesh2"},
				{2},
				{"shubhan2"}
		};
	}
	
	@DataProvider                     // single dimension
	public Employee[] getData3() {

		return new Employee[] {			
			new Employee("jayesh3" , "123"),
			new Employee("sachin3" , "456"),
			new Employee("shubham3" , "789")			
		};
	}
	
	
	
}
