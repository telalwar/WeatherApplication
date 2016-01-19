package com.example.weatherapp;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.widget.ImageView;

public class JsonParser_Weather {
		static String strTempC;
	static String strCityName;
	static JSONObject jArray, jObjSimpleForCat;
	static JSONArray jObjForCastDay;
	private static String[] high = new String[4];
	private static String[] low = new String[4];
	private static String[] conditions = new String[4];
	private static String[] iconURL = new String[4];
	

	public static RecordTemp parse_json(String str) {

		RecordTemp saveData = new RecordTemp();

		try {
			JSONObject jObj = new JSONObject(str);
			JSONObject temp = jObj.getJSONObject("current_observation");
			strTempC = temp.getString("temp_c");
			saveData.setMaxTempDay1(strTempC);
			JSONObject location = jObj.getJSONObject("current_observation")
					.getJSONObject("display_location");
			strCityName = location.getString("full");
			saveData.setCity(strCityName);
			jArray = jObj.getJSONObject("forecast");
			jObjSimpleForCat = jArray.getJSONObject("simpleforecast");
			jObjForCastDay = jObjSimpleForCat.getJSONArray("forecastday");

			JSONObject jsonObjToIterat = new JSONObject();

			for (int i = 0; i < 4; i++) {
				jsonObjToIterat = jObjForCastDay.getJSONObject(i);
				high[i] = jsonObjToIterat.getString("high");
				low[i] = jsonObjToIterat.getString("low");
				conditions[i] = jsonObjToIterat.getString("conditions");
				iconURL[i] = jsonObjToIterat.getString("icon_url");

			}
			JSONObject jObj1 = new JSONObject(high[0]);
			JSONObject jObj2 = new JSONObject(low[0]);
			String strDay1High = jObj1.getString("celsius");
			String strDay1Low = jObj2.getString("celsius");
			saveData.setCondition1(conditions[0]);
			saveData.setMaxTempDay1(strDay1High);
			saveData.setMinTempDay1(strDay1Low);
			saveData.setMaxTempDay1_f(jObj1.getString("fahrenheit"));
			saveData.setMinTempDay1_f(jObj2.getString("fahrenheit"));

			saveData.setCondition2(conditions[1]);
			JSONObject jObj3 = new JSONObject(high[1]);
			JSONObject jObj4 = new JSONObject(low[1]);
			String strDay2High = jObj3.getString("celsius");
			String strDay2Low = jObj4.getString("celsius");
			saveData.setMaxTempDay2(strDay2High);
			saveData.setMinTempDay2(strDay2Low);
			saveData.setMaxTempDay2_f(jObj3.getString("fahrenheit"));
			saveData.setMinTempDay2_f(jObj4.getString("fahrenheit"));


			JSONObject jObj5 = new JSONObject(high[2]);
			JSONObject jObj6 = new JSONObject(low[2]);
			String strDay3High = jObj5.getString("celsius");
			String strDay3Low = jObj6.getString("celsius");
			saveData.setCondition3(conditions[2]);
			saveData.setMaxTempDay3(strDay3High);
			saveData.setMinTempDay3(strDay3Low);
			saveData.setMaxTempDay3_f(jObj5.getString("fahrenheit"));
			saveData.setMinTempDay3_f(jObj6.getString("fahrenheit"));
			
			JSONObject jObj7 = new JSONObject(high[3]);
			JSONObject jObj8 = new JSONObject(low[3]);
			String strDay4High = jObj7.getString("celsius");
			String strDay4Low = jObj8.getString("celsius");
			saveData.setCondition4(conditions[3]);
			saveData.setMaxTempDay4(strDay4High);
			saveData.setMinTempDay4(strDay4Low);
			saveData.setMaxTempDay4_f(jObj7.getString("fahrenheit"));
			saveData.setMinTempDay4_f(jObj6.getString("fahrenheit"));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		saveData.iconData1 = new JsonParser_Weather().getImage(iconURL[0]);
		saveData.iconData2 = new JsonParser_Weather().getImage(iconURL[1]);
		saveData.iconData3 = new JsonParser_Weather().getImage(iconURL[2]);
		saveData.iconData4 = new JsonParser_Weather().getImage(iconURL[3]);

		// System.out.println("Image condition"+imageConditions[0]);
		return saveData;
	}

	public byte[] getImage(String url) {
		HttpURLConnection con = null;
		InputStream is = null;
		try {
			/*
			 * con = (HttpURLConnection) (new URL(url)).openConnection();
			 * con.setRequestMethod("GET"); con.setDoInput(true);
			 * con.setDoOutput(true); con.connect();
			 * 
			 * // Let's read the response is = con.getInputStream();
			 */
			is = new URL(url).openStream();
			byte[] buffer = new byte[1024];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			while (is.read(buffer) != -1)
				baos.write(buffer);

			return baos.toByteArray();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (Throwable t) {
			}
			try {
				con.disconnect();
			} catch (Throwable t) {
			}
		}

		return null;

	}
}
