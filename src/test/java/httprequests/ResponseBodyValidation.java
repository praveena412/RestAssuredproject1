package httprequests;


import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;


public class ResponseBodyValidation {
	
//@Test
void ResponseBodytest()
{
	RequestSpecification req=RestAssured.given();
	req.baseUri("https://reqres.in/");
	req.basePath("api/users?page=2");
	
	Response res=req.get();
	System.out.println(res.getStatusCode());
	
ResponseBody rb=res.getBody();
String rs=rb.asString();

JsonPath data=rb.jsonPath();
String firstname=data.get("data[1].first_name");
System.out.println(firstname);

}


@Test
void ResponseBodytest1()
{
	Response res=RestAssured
	.given()
	
	.when()
	   .get("https://reqres.in/api/users?page=2");
	
	int asc=res.getStatusCode();
	System.out.println(asc);
	Assert.assertEquals(asc,200);
	String name=res.jsonPath().get("data[0].first_name").toString();
	System.out.println(name);
	
}
	

	
	
	
	
	
	
	
	
	
	
}