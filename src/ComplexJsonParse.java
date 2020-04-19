import File.payLoad;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse
{
public static void main(String[] args)
{
	JsonPath jp = new JsonPath(payLoad.CourcePrice());
	int count = jp.getInt("courses.size()");
	System.out.println("1. Print No of courses returned by API");
	System.out.println("No of courses: " +count);
	System.out.println();
	
	int totalamount = jp.getInt("dashboard.purchaseAmount");
	System.out.println("2.Print Purchase Amount");
	System.out.println("Purchase Amount: " +totalamount);
	System.out.println();
	
	String titleFirstCourse = jp.get("courses[0].title");
	System.out.println("3. Print Title of the first course");
	System.out.println("Title of the first course: " + titleFirstCourse);
	System.out.println();
	System.out.println("4. Print All course titles and their respective Prices");
	
	for (int i = 0; i < count; i++)
	{
		String Coursetitle = jp.get("courses["+i+"].title");
		System.out.println("Course title: " + Coursetitle);
		
		int CoursePrice = jp.getInt("courses["+i+"].price");
		System.out.println("Course Price: " + CoursePrice);
	}
	
	System.out.println();
	System.out.println("5. Print no of copies sold by RPA Course");

	for (int i = 0; i < count; i++)
	{
		String Coursetitle = jp.get("courses["+i+"].title");
		if (Coursetitle.equalsIgnoreCase("RPA"))
		{
			int RpaCopies = jp.getInt("courses["+i+"].copies");
			System.out.println("RPA Copies: " + RpaCopies);
		}
	}
}
}
