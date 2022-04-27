package DataProviderInTestng;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest2 {

	@Test(dataProvider = "getData1")
	public void test1(Employee emp) {
		System.out.println(emp.getId());
		System.out.println(emp.getName());		
	}

	@Test(dataProvider = "getData1")
	public void test2(String username) {
		System.out.println(username);
	}

	@DataProvider
	public Object[] getData1(Method m) {

		if (m.getName().equalsIgnoreCase("test1")) {

			return new Employee[] {

					new Employee("jayesh3", "123"),
					new Employee("sachin3", "456"),
					new Employee("shubham3", "789") 
					};

		} else if (m.getName().equalsIgnoreCase("test2")) {
			return new Object[] { "jayesh1", "sachine1", "shubham1" };			
		}
		return null;
	}

}
