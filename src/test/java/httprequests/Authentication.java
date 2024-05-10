package httprequests;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Authentication {
	
	//@Test(priority=1)
	void basicAuth()
	{
		RestAssured
		.given()
		    .auth().basic("pravi","pravi123")
		    
		 .when()
		     .get("https://httpbin.org/basic-auth/pravi/pravi123")
		     
		  .then()
		     .statusCode(200)
		     .log().all();
	}
	
	//@Test(priority=2)
	void basicAuthPreemptive()
	{
		RestAssured
		.given()
		    .auth().preemptive().basic("pravi","pravi123")
		    
		.when()
		    .get("https://httpbin.org/basic-auth/pravi/pravi123")
		 
		 .then()
		     .statusCode(200)
		     .log().all();
	}
	
	//@Test(priority=3)
	void digestAuth()
	{
		RestAssured
		   .given()
		      .auth().digest("praveena","praveena123")
		      
		   .when()
		       .get("https://httpbin.org/digest-auth/undefined/praveena/praveena123")
		       
		   .then()
		      .statusCode(200)
		      .log().all();
		   
	}
	
	//@Test(priority=4)
	void digestAuth2()
	{
		RequestSpecification req=RestAssured.given();
		req.baseUri("https://httpbin.org/digest-auth");
		req.basePath("undefined/praveena/praveena123");
		
	    Response res=req.auth().digest("praveena","praveena123").get();
	    int sc=res.statusCode();
	   System.out.println(sc);
	   Assert.assertEquals(sc,200);
	   
	 }
	
	//@Test 
	void GithubBearerToken()
	{
		String Token="Bearer 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";
		
		RestAssured
		  .given()
             .headers("Authorization",Token)
             
          .when()
             .get("https://api.github.com/user/repos")
           
          .then()
             .statusCode(200)
             .log().all();
      }
	
	//@Test
	void GmailBearerToken() 
	{
		String Token="Bearer 682288cedcb4fdda07f5e8a43c7c5d3af1886f217c8113e461d3ee6da7bbdfd8";
				
		RestAssured
		   .given()
		      .headers("Authorization",Token)
		    .when()
		      .get("https://gorest.co.in/public/v2/users")
		    .then()
		      .statusCode(200)
		      .log().all();
	}
	
	
	@Test
	void gmailBearerToken2() 
	{
		String Token="Bearer 682288cedcb4fdda07f5e8a43c7c5d3af1886f217c8113e461d3ee6da7bbdfd8";
		
		JSONObject data=new JSONObject();
		data.put("name","pravi");
		data.put("email","pravi@gamil.com");
		data.put("gender","female");
		data.put("status","active");
		
		RestAssured
		  .given()
		     .headers("Authorization",Token)
		     .contentType(ContentType.JSON)
		     .body(data.toJSONString())
		  
		  .when()
		    .post("https://gorest.co.in/public/v2/users")
		    
		  .then()
		    .statusCode(201);
	}
	
}
	
	

