package File;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DynamicJson
{
@Test(dataProvider="booksdata")
public void addBook(String isbn, String aisle)
{
RestAssured.baseURI = "http://216.10.245.166";
Response response = given().log().all().
	header("Content-Type","application/json").
body(payLoad.Addbook(isbn,aisle)).
when().
post("/Library/Addbook.php").
then().log().all().assertThat().statusCode(200).
extract().response();

JsonPath jp = ReusableMethods.rawTojson(response);
String id = jp.getString("ID");
System.out.println("ID = " + id);

//delete Book

String body = "{\n" + 
		"    \"ID\":\""+id+"\"\n" + 
		"}\n" + 
		"";
System.out.println("Body " + body);
given().log().all().
header("Content-Type","application/json").
body(body).when().post("/Library/DeleteBook.php").
then().log().all().assertThat().statusCode(200).
extract().response().asString();

}	

@DataProvider(name="booksdata")
public Object[][] getData()
{
return new Object[][]{{"jwity","9363"},{"odsiad","4255"},{"faisd","566"}};
}

}
