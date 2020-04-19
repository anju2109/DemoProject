import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import File.ReusableMethods;
import File.payLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class Basics {
public static void main(String[] args)
{
	//Add Api
	//Given - All Input Details
	//When - Submit the API - resource and http method
	//Then - Validate the response
	
	RestAssured.baseURI="https://rahulshettyacademy.com";
	String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
	.body(payLoad.AddPlace()).when().post("/maps/api/place/add/json")
	.then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP"))
	.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
	
	JsonPath jp = new JsonPath(response); //for parsing json i.e response
	String placeid = jp.getString("place_id"); // because there is no parent of place id if parent is there e.g for lat, location is the parent than path will be location.lat
	System.out.println("Place ID = " + placeid);
	
	//Update Place
	String newddress = "70 Summer walk, USA";
	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
	.body("{\r\n" + 
			"\"place_id\":\""+placeid+"\",\r\n" + 
			"\"address\":\""+newddress+"\",\r\n" + 
			"\"key\":\"qaclick123\"\r\n" + 
			"}").when().put("/maps/api/place/update/json")
	.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
	
	//Get Place
	
	Response getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
	.queryParam("place_id", placeid)
	.when().get("/maps/api/place/get/json")
	.then().log().all().assertThat().statusCode(200).extract().response();
	
	JsonPath jp1 = ReusableMethods.rawTojson(getPlaceResponse);
	String updatedAdd = jp1.getString("address");
	System.out.println("Updated Address = " + updatedAdd);
	
	Assert.assertEquals(newddress, updatedAdd);

}
}
