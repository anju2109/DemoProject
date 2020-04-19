import org.testng.Assert;
import org.testng.annotations.Test;

import File.payLoad;
import io.restassured.path.json.JsonPath;

public class SumValidation
{
	@Test
	public void sumOfcourses()
	{
		JsonPath jp = new JsonPath(payLoad.CourcePrice());
		int count = jp.getInt("courses.size()");
		
		int sum=0;
		System.out.println("6. Verify if Sum of all Course prices matches with Purchase Amount");
		for (int i = 0; i < count; i++)
		{	
			int CoursePrice = jp.getInt("courses["+i+"].price");
			int RpaCopies = jp.getInt("courses["+i+"].copies");
			sum = sum + CoursePrice*RpaCopies;
		}
		System.out.println("Course Price: " + sum);
		
		int totalamount = jp.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum,totalamount);
	}
}
