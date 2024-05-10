package httprequests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestOAuth2 {
	String accessToken;
	@Test
	
	void test0Auth()
	{
		
		String clientid="ARXpxjM92eYJJpBy_Q4OGv2MgNRRiV8j_Yeh2vo8tlgszMiFQUt8PxugoXIllCS5hvk8HZeqTcGsHCjG";
		String secretid="EHq_ylO51qjjwk3w3f0Ry6Ubbtip9L_F9HDMQNIdFxvyHP2YQltlm3dZPIvL0B8_0GA4FpSZ639VscA3";
		
		Response res=RestAssured
		   .given()
		       .auth().preemptive().basic(clientid, secretid)
		       .param("grant_type", "client_credentials")
		       
		    .when()
		       .post("https://api-m.sandbox.paypal.com/v1/oauth2/token");
		
		accessToken=res.getBody().path("access_token");
		System.out.println(accessToken);
}
	
	//https://api-m.sandbox.paypal.com/v1/invoicing/invoices?page=3&page_size=4&total_count_required=true
	
	@Test(dependsOnMethods= {"test0Auth"})
	void test0AuthwithToken()
	{
		Response res=RestAssured
		   .given()
		      .auth()
		      .oauth2(accessToken)
		      .queryParam("page", 3)
		      .queryParam("page_size",4)
		      .queryParam("total_count_required","true")
		   .when()
		      .get("https://api-m.sandbox.paypal.com/v1/invoicing/invoices");
		
		System.out.println(res.statusLine());
		
	}
	
	

}
