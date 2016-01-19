package com.example.weatherapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class httpClientClassToGetTemp {
	JSONObject jObj;
	String result = "";
	//final static String Base_URL = "http://api.wunderground.com/api/4ada42b0bb3f3d93/conditions/forecast10day/q/";
	//final static String Base_URL = "http://api.wunderground.com/api/4ada42b0bb3f3d93/forecast/lang:";
	//final static String Base_URL="http://api.wunderground.com/api/4ada42b0bb3f3d93/conditions/q/";
	
	public String getweatherDetails(String URL) {
	
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		int statusCode = 0;
		HttpGet httpGet = new HttpGet(URL);
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			statusCode = statusLine.getStatusCode();

			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					 builder.append(line);
				}
			} else {
				return null;
			}
		}  catch (IOException e) {
			System.out.println("Exception in method");
			e.printStackTrace();
		}
		
		// builder.append("sachin"+statusCode);
		result = builder.toString();
		//System.out.println(result);
		/*
		 * try { jObj = new JSONObject(result); } catch (JSONException e) {
		 * Log.e("JSON Parser", "Error parsing data " + e.toString()); }
		 */

		// return JSON String
		return result;

	}
}
