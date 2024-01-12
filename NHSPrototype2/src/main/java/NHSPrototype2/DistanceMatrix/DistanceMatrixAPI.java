package uk.ac.bangor.jml20vql.csee.NHSPrototype2.DistanceMatrix;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DistanceMatrixAPI {
	
	private static String apiKey = "xxxxxxxxxxxxxxxxxxxxx";
	
	private float distance = -1f;
	
	
	public void generateDistance(String postcode1, String postcode2) throws Exception {
		
		float metres = Float.valueOf(getDistanceFromGoogle(postcode1, postcode2));
		
		this.distance = metres;
	}
	

	// contacts google, and returns a long value containing the distance in km
	private Long getDistanceFromGoogle(String postcode1, String postcode2) throws Exception {
		
		String response = getResponse(postcode1, postcode2);
		Long distance = parseResponseForDistance(response);
		
		return distance;
	}
	
	private Long parseResponseForDistance(String response) throws ParseException{
		
		Long distance = -1L;
		
		JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(response);
        JSONArray ja = (JSONArray) jo.get("rows");
        
        jo = (JSONObject) ja.get(0);
        ja = (JSONArray) jo.get("elements");
        jo = (JSONObject) ja.get(0);
        
        JSONObject je = (JSONObject) jo.get("distance");
        
        distance = (Long) je.get("value");
        
		return distance;
	}

	
	private String getResponse (String source, String destination) throws Exception{
		
		var url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + source + "&destinations=" + destination + "&key=" + apiKey;
		var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
		var client = HttpClient.newBuilder().build();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		return response.body();
		
		}

	
	public float miles() {
		return (this.distance / 1609.33f);
	}
	
	public float getDistance() {
		return distance;
	}


	public void setDistance(float distance) {
		this.distance = distance;
	}
	
	
	
}
