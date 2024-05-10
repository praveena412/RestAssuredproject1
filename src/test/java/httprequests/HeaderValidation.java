package httprequests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;



public class HeaderValidation {
	
	@Test
	void headerTest()
	{
	Response res=RestAssured
		.given()
		
		.when()
	       .get("https://reqres.in/api/users/2");
	
	//System.out.println(res.getHeader("content-Type"));
	
	Headers Myheaders=res.getHeaders();
	
	for(Header hd:Myheaders)
	{
		System.out.println(hd.getName()+" = "+hd.getValue());
	}
		   
		   
	}
	

}
