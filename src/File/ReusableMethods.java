package File;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableMethods
{
public static JsonPath rawTojson(Response response)
{
	JsonPath jp = new JsonPath(response.asString());
	return jp;
}

}
