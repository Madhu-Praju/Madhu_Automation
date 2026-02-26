package APITestFacctView;

import org.json.JSONArray;
import org.json.JSONObject;

import io.restassured.response.Response;

public class parseResultofFacctviewMatch {
	
	
	private static FacctViewMatchCall fm = new FacctViewMatchCall();
	
	
	
	
	
	
	
	
	public static void main(String [] args) {
		


		String respo =fm.responseOFAPI();
		
		System.out.println("Responsee----"+ respo);
		JSONObject obj = new JSONObject(respo);

		System.out.println("message of response"+ obj.get("run_details"));
		JSONArray ja = (JSONArray) obj.get("run_matches");
		System.out.println("message of response...rundetails"+ obj.get("run_matches"));
		JSONArray run_matches = obj.getJSONArray("run_matches");
		System.out.println("message of response_run_matches"+ run_matches);
		
		JSONObject newobj = run_matches.getJSONObject(0);	
		JSONArray query_matches = newobj.getJSONArray("query_matches");
		int count=query_matches.length();
		
		System.out.println("message of length"+ count);
		
		if (count >0) {
			System.out.println("message of length"+ count);
		for(int i=0; i>count;i++)
		{
			System.out.println("from loop"+ count);
			JSONObject newbj = query_matches.getJSONObject(i);
			JSONArray entity_matches = newbj.getJSONArray("entity_matches");
		
		
			System.out.println("message of query_matchesentity_matches   ---i"+ entity_matches);
		}
		}
		
	}
	

}
