package APITestFacctView;

import org.testng.annotations.Test;

import com.github.dockerjava.transport.DockerHttpClient.Request;
// import com.microsoft.schemas.office.x2006.encryption.STHashSize;
import com.mongodb.util.JSON;

import io.cucumber.java.hu.Ha.Has;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import org.hamcrest.Matchers.*;
import org.json.JSONArray;
import org.json.JSONObject;

import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FacctViewMatchCall {
	
	public static String respo=null;
	
	
//	public String tokenGeneration(String cl_ID,String cl_Secret,String audience) {
//		Map<String, String> apibody = new HashMap<>();
//		apibody.put("grant_type", "client_credentials");
//		apibody.put("client_id", cl_ID);
//		apibody.put("client_secret", cl_Secret);
//		apibody.put("audience", audience);
//	
//		JSONObject jo = new JSONObject(apibody);
//		
//	String token=	 given().log().all().accept(ContentType.JSON)
//				.contentType("application/json")
//				.and()
//				.body(jo.toString())
//				.post("https://facctum-quality-assurance.eu.auth0.com/oauth/token")   //hit the post end point
//				.thenReturn().asString();
//	
//	System.out.println("Token response-----"+token);
//		
//		return token;
//	}
	
	
	public String responseOFAPI() {
		
//
//		String token=tokenGeneration("lyk8PRQB478qvbd0zMqZ6HXkaqtSC5Xi","w_fz_EyBbXWYxzBGU3Xaw7C96vAV67codyiU72Bl1xsFnm79eCHIZQ9wAnNIkdY8","https://api-qa-saas.facctum.com");
//		JSONObject obj2 = new JSONObject(token);
//		System.out.println("Token........"+ obj2.get("access_token"));
//	
//		
//		String token2 = '"'+"Bearer "+obj2.get("access_token")+"'";
//		System.out.println("Token2........"+ token2);
		
		Map<String, String> apibody = new HashMap<>();
		apibody.put("entity_type", "P");
		apibody.put("name", "Seema");
	
		JSONObject jo = new JSONObject(apibody);
		
	String	response=	 given().log().all().header("authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IldJcXJlYVZTR1EtMWhLXzNzemRVVCJ9.eyJnZW9pcCI6eyJjaXR5TmFtZSI6IkJlbmdhbHVydSIsImNvbnRpbmVudENvZGUiOiJBUyIsImNvdW50cnlDb2RlIjoiSU4iLCJjb3VudHJ5Q29kZTMiOiJJTkQiLCJjb3VudHJ5TmFtZSI6IkluZGlhIiwibGF0aXR1ZGUiOjEyLjk2MzQsImxvbmdpdHVkZSI6NzcuNTg1NSwic3ViZGl2aXNpb25Db2RlIjoiS0EiLCJzdWJkaXZpc2lvbk5hbWUiOiJLYXJuYXRha2EiLCJ0aW1lWm9uZSI6IkFzaWEvS29sa2F0YSJ9LCJvcmciOnsiZGlzcGxheV9uYW1lIjoiRmFjY3R1bSBzb2x1dGlvbnMiLCJpZCI6Im9yZ19jd3lBdkJaSE5VcWtETG1jIiwibWV0YWRhdGEiOnsiY3JlYXRpb25fdGltZXN0YW1wIjoiVGh1IEFwciAwNCAyMDI0IDA4OjI4OjAzIEdNVCswMDAwIChDb29yZGluYXRlZCBVbml2ZXJzYWwgVGltZSkiLCJjcmVhdG9yX2VtYWlsIjoiYWRtaW5AY2FwLmNvbSJ9LCJuYW1lIjoiZmFjY3R1bSJ9LCJ0ZW5hbnRJZCI6ImZhY2N0dW0iLCJpc3MiOiJodHRwczovL2ZhY2N0dW0tcXVhbGl0eS1hc3N1cmFuY2UuZXUuYXV0aDAuY29tLyIsInN1YiI6Imx5azhQUlFCNDc4cXZiZDB6TXFaNkhYa2FxdFNDNVhpQGNsaWVudHMiLCJhdWQiOiJodHRwczovL2FwaS1xYS1zYWFzLmZhY2N0dW0uY29tIiwiaWF0IjoxNzE1ODU4MzQ0LCJleHAiOjE3MTU5NDQ3NDQsImd0eSI6ImNsaWVudC1jcmVkZW50aWFscyIsImF6cCI6Imx5azhQUlFCNDc4cXZiZDB6TXFaNkhYa2FxdFNDNVhpIiwicGVybWlzc2lvbnMiOltdfQ.PyjutYH6VNX31GglByImzUS3Bmg6kkUW4f1B4LCFqEHRXSo7Xj1izT1UySPYo_tU074A-Xy-EDObTKwgE3dTWp7aP9KTt52Gh8mbDCn9Nr7ImYlnpg1m46RyO0sOm_37c-xoukjS7nW2MIPS8sqw-MSGCK97XeVWHtnLzslvcGIkrjbovce8X94PIJQ-9aTE81_dXjb6qzHV8Qovc31QNqb6096JesiSZNjS2xog5-X9LkKPJml2kFMucJWaYO3hIJiAroMouuIzUFJHEoLHPWZSj2zAr_X7_gsY_j1AcxldfYNY1NQkXEAz3NXa_YnnD2ztAeWat_pfsRzko474nQ")
				.accept(ContentType.JSON)
				.contentType("application/json")
				.and()
				.body(jo.toString())
				.post("https://api-qa-saas.facctum.com/facctview/v1/rapid/match")   //hit the post end point
				.thenReturn().asString();
		
		System.out.println("Token2........"+ respo);

		return response;
	}
	
	@Test
	public void test_numberofcircuit() {
		
		
//		String token=tokenGeneration("lyk8PRQB478qvbd0zMqZ6HXkaqtSC5Xi","w_fz_EyBbXWYxzBGU3Xaw7C96vAV67codyiU72Bl1xsFnm79eCHIZQ9wAnNIkdY8","https://api-qa-saas.facctum.com");
//		JSONObject obj1 = new JSONObject(token);
//		System.out.println("Token........"+ obj1.get("access_token"));
//	
//		
//		String token2 = '"'+"Bearer "+obj1.get("access_token")+'"';
//		System.out.println("Token........"+ token2);
		
		Map<String, String> apibody = new HashMap<>();
		apibody.put("entity_type", "P");
		apibody.put("name", "Seema");
	
		JSONObject jo = new JSONObject(apibody);
		
		System.out.println(jo+"API BODYYY");

	
	Response respo1 = RestAssured.given()
					.header("authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IldJcXJlYVZTR1EtMWhLXzNzemRVVCJ9.eyJnZW9pcCI6eyJjaXR5TmFtZSI6IkJlbmdhbHVydSIsImNvbnRpbmVudENvZGUiOiJBUyIsImNvdW50cnlDb2RlIjoiSU4iLCJjb3VudHJ5Q29kZTMiOiJJTkQiLCJjb3VudHJ5TmFtZSI6IkluZGlhIiwibGF0aXR1ZGUiOjEyLjk2MzQsImxvbmdpdHVkZSI6NzcuNTg1NSwic3ViZGl2aXNpb25Db2RlIjoiS0EiLCJzdWJkaXZpc2lvbk5hbWUiOiJLYXJuYXRha2EiLCJ0aW1lWm9uZSI6IkFzaWEvS29sa2F0YSJ9LCJvcmciOnsiZGlzcGxheV9uYW1lIjoiRmFjY3R1bSBzb2x1dGlvbnMiLCJpZCI6Im9yZ19jd3lBdkJaSE5VcWtETG1jIiwibWV0YWRhdGEiOnsiY3JlYXRpb25fdGltZXN0YW1wIjoiVGh1IEFwciAwNCAyMDI0IDA4OjI4OjAzIEdNVCswMDAwIChDb29yZGluYXRlZCBVbml2ZXJzYWwgVGltZSkiLCJjcmVhdG9yX2VtYWlsIjoiYWRtaW5AY2FwLmNvbSJ9LCJuYW1lIjoiZmFjY3R1bSJ9LCJ0ZW5hbnRJZCI6ImZhY2N0dW0iLCJpc3MiOiJodHRwczovL2ZhY2N0dW0tcXVhbGl0eS1hc3N1cmFuY2UuZXUuYXV0aDAuY29tLyIsInN1YiI6Imx5azhQUlFCNDc4cXZiZDB6TXFaNkhYa2FxdFNDNVhpQGNsaWVudHMiLCJhdWQiOiJodHRwczovL2FwaS1xYS1zYWFzLmZhY2N0dW0uY29tIiwiaWF0IjoxNzE1ODU4MzQ0LCJleHAiOjE3MTU5NDQ3NDQsImd0eSI6ImNsaWVudC1jcmVkZW50aWFscyIsImF6cCI6Imx5azhQUlFCNDc4cXZiZDB6TXFaNkhYa2FxdFNDNVhpIiwicGVybWlzc2lvbnMiOltdfQ.PyjutYH6VNX31GglByImzUS3Bmg6kkUW4f1B4LCFqEHRXSo7Xj1izT1UySPYo_tU074A-Xy-EDObTKwgE3dTWp7aP9KTt52Gh8mbDCn9Nr7ImYlnpg1m46RyO0sOm_37c-xoukjS7nW2MIPS8sqw-MSGCK97XeVWHtnLzslvcGIkrjbovce8X94PIJQ-9aTE81_dXjb6qzHV8Qovc31QNqb6096JesiSZNjS2xog5-X9LkKPJml2kFMucJWaYO3hIJiAroMouuIzUFJHEoLHPWZSj2zAr_X7_gsY_j1AcxldfYNY1NQkXEAz3NXa_YnnD2ztAeWat_pfsRzko474nQ")
					.contentType("application/json")
					.and()
					.body(jo.toString())
					.post("https://api-qa-saas.facctum.com/facctview/v1/rapid/match"); 
	
	System.out.println("response of API........"+ respo1.toString());
	System.out.println("Response code    "+ respo1.getStatusCode());
	System.out.println("API Response........"+ respo1);
	Assert.assertEquals(respo1.getStatusCode(), 200);

	respo = responseOFAPI();
	
	JSONObject obj = new JSONObject(respo);


	System.out.println("message of response"+ obj.get("run_details"));
	JSONArray ja = (JSONArray) obj.get("run_matches");
	System.out.println("message of response for run mat..."+ obj.get("run_matches"));
	JSONArray run_matches = obj.getJSONArray("run_matches");
	System.out.println("message of respons run_matches   "+ run_matches);
	
	JSONObject newobj = run_matches.getJSONObject(0);	
	JSONArray query_matches = newobj.getJSONArray("query_matches");
	int count=query_matches.length();
	
	System.out.println("message of length...."+ count);
	for(int i=0; i>count;i++)
	{
		JSONObject newbj = query_matches.getJSONObject(i);
		JSONArray entity_matches = newbj.getJSONArray("entity_matches");
	
	
		System.out.println("message of query_matchesentity_matches   ---i"+ entity_matches);
	}
	
	//return respo;
	
	}	
	//https://api-stage-saas.facctum.com/facctview/v1/rapid/match
	
	
	
	
	
	

}
