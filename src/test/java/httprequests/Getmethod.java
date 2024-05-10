package httprequests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Getmethod {
	int id;
	@Test(priority=1) 
	void getlistUsers() 
	{
		RestAssured
		.given()
		.when()
		  .get("https://reqres.in/api/users?page=2")
	    .then()
		   .statusCode(200);
		   
	}
	
	//@Test(priority=2)
	void createUser() 
	{
		JSONObject data=new JSONObject();
		data.put("name","praveena");
		data.put("job","Engg");
		
		id=RestAssured
		  .given()
		     .header("content-Type","application/json")
		     .contentType(ContentType.JSON)
		     .body(data.toJSONString())
		  .when()
		      .post("https://reqres.in/api/users")
		      .jsonPath().getInt("id");
		   System.out.println(id);
		  
	}
	
	//@Test (priority=3,dependsOnMethods= {"createUser"})
	void updateUser() 
	{
		JSONObject data=new JSONObject();
		data.put("name","praveena");
		data.put("job","Mech Engg");
		
		RestAssured
		  .given()
		     .header("content-Type","application/json")
		     .contentType(ContentType.JSON)
		     .body(data.toJSONString())
		  .when()
		      .put("https://reqres.in/api/users/"+id)
		   .then() 
		       .statusCode(200)
		       .log().all();
		      
	}
	
	//@Test(priority=4)
	void deleteUser()
	{
		RestAssured
		.given()
		.when()
		   .delete("https://reqres.in/api/users/"+id)
		 .then()
		    .statusCode(204)
		    .log().all();
		    
	}

	
	

}
