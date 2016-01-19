package com.example.weatherapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Weather extends Activity {
	final static String Base_URL = "http://api.wunderground.com/api/4ada42b0bb3f3d93/conditions/forecast10day/q/";
	//final static String Base_URL="http://api.wunderground.com/api/4ada42b0bb3f3d93/forecast/lang:";
	HttpClient client;
	EditText etZip;
	TextView tvDisplayHigh1, tvCity, tvDsipalyLow1,tvCondition,tvDisplayHigh1_marathi,tvCity_marathi,tvDsipalyLow1_marathi,tvCondition_marathi;
	Button butFind;
	String strZipValue;
	JSONObject jsObj;
	String result = "";
	JSONArray currObv = null;
	String fullNameofCity = "";
	static JSONObject jObj = null;
	String cityTODisplay = "";
	String strTempC = "";
	String strIcon = "";
	String lang = "en";
	String data = "";
	String strZip = "";
	EditText zipcode;
	ImageView imageView1,imageView1_marathi; 
	String strLan;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intentFromSearch = getIntent();
		strLan = intentFromSearch.getStringExtra("com.example.weatherapp.SearchWeatherByLanguage");
		if(strLan.equalsIgnoreCase("MR")){
		setContentView(R.layout.display_weather_marathi);
		}
		else{
			setContentView(R.layout.activity_weather);
		}
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		String URL="";
		strZip = intentFromSearch
				.getStringExtra("com.example.weatherapp.SearchWeatherByZip");
		strLan = intentFromSearch
				.getStringExtra("com.example.weatherapp.SearchWeatherByLanguage");
		
		
		tvDisplayHigh1 = (TextView) findViewById(R.id.tvHigh1);
		tvDsipalyLow1 = (TextView) findViewById(R.id.tvLow1);
		tvCity = (TextView) findViewById(R.id.tvCity);
		tvCondition = (TextView) findViewById(R.id.tvCondition1);
		imageView1 = (ImageView) findViewById(R.id.imageView1);
		
		tvDisplayHigh1_marathi = (TextView) findViewById(R.id.tvHigh1_marathi);
		tvCity_marathi=(TextView)findViewById(R.id.tvCity_marathi);
		tvDsipalyLow1_marathi = (TextView) findViewById(R.id.tvLow1_marathi);
		tvCondition_marathi = (TextView) findViewById(R.id.tvCondition1_marathi);
		imageView1_marathi = (ImageView) findViewById(R.id.imageView1_marathi);
		JSONTempTask asyntaskToGetTemp = new JSONTempTask();
		
		StringBuilder forURL = new StringBuilder();
		forURL.append(Base_URL);
		forURL.append(strZip);
		forURL.append(".json");
		/*forURL.append(Base_URL);
		forURL.append("lang:");
		forURL.append("FR");
		forURL.append("/q/");
		forURL.append(strZip);
		forURL.append(".json");*/
		
		
		
		URL= forURL.toString();
		//System.out.println("This is URL"+URL);
		asyntaskToGetTemp.execute(URL);
	}

	private class JSONTempTask extends AsyncTask<String, Void, Weather> {
		RecordTemp recordTemp = new RecordTemp();
		
		@Override
		/*protected Weather doInBackground(String... params) {
			// TODO Auto-generated method stub
			data = ((new httpClientClassToGetTemp()).getweatherDetails(
					params[0], params[1]));
			recordTemp = JsonParser_Weather.parse_json(data);

			return null;
		}*/
		protected Weather doInBackground(String... params) {
			// TODO Auto-generated method stub
			data = ((new httpClientClassToGetTemp()).getweatherDetails(
					params[0]));
			//System.out.println("data******************"+data);
			recordTemp = JsonParser_Weather.parse_json(data);

			return null;
		}

		protected void onPostExecute(Weather weather) {
			// System.out.println("hiiiiiiii"+recordTemp.getMaxTempDay1());
			if(strLan.equalsIgnoreCase("EN")){
			tvDisplayHigh1.setText("High:" + recordTemp.getMaxTempDay1());
			tvCity.setText("City:" + recordTemp.getCity());
			tvDsipalyLow1.setText("Low:" + recordTemp.getMinTempDay1());
			tvCondition.setText("Condition :"+recordTemp.getCondition1());
			if (recordTemp.iconData1 != null && recordTemp.iconData1.length > 0) {
                Bitmap img = BitmapFactory.decodeByteArray(recordTemp.iconData1, 0, recordTemp.iconData1.length);
                imageView1.setImageBitmap(img);
        }
			}
			else{
				tvDisplayHigh1_marathi.setText(recordTemp.getMaxTempDay1());
				tvCity_marathi.setText(recordTemp.getCity());
				tvDsipalyLow1_marathi.setText(recordTemp.getMinTempDay1());
				tvCondition_marathi.setText(recordTemp.getCondition1());
				if (recordTemp.iconData1 != null && recordTemp.iconData1.length > 0) {
	                Bitmap img = BitmapFactory.decodeByteArray(recordTemp.iconData1, 0, recordTemp.iconData1.length);
	                imageView1_marathi.setImageBitmap(img);
	        }
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weather, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// do your own thing here
			Intent intent = new Intent(Weather.this, SearchWeatherByZip.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
