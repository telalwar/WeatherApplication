package com.example.weatherapp;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DefaultWeather extends Activity {
	final static String Base_URL = "http://api.wunderground.com/api/4ada42b0bb3f3d93/conditions/forecast10day/q/38.90156555,-77.05078125.json";
	TextView latitute, longitude, day1, day2, day3, location,day4;
	private LocationManager locationManager;
	private String Locationprovider;
	ImageView imageView1,imageView2,imageView3,imageView4;
	String data = "";
	String unit;
	String PREFS_NAME = "weather_prefs";
	int daysSelected;
	 protected TextView _percentField;
	 ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		 daysSelected = settings.getInt("Days", 3);
		 unit=settings.getString("Unit", "C");
		 String strI = "" + daysSelected;
		/*Toast toast = Toast.makeText(getApplicationContext(),
				strI, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.show();
		
*/		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_default_weather);
		latitute = (TextView) findViewById(R.id.latitude);
		longitude = (TextView) findViewById(R.id.Longitute);
		location = (TextView) findViewById(R.id.Location);
		
		day1 = (TextView) findViewById(R.id.day1);
		imageView1 = (ImageView) findViewById(R.id.imageView1);
		imageView2 = (ImageView) findViewById(R.id.imageView2);
		imageView3 = (ImageView) findViewById(R.id.imageView3);
		imageView4 = (ImageView) findViewById(R.id.imageView4);
		day2 = (TextView) findViewById(R.id.day2);
		day3 = (TextView) findViewById(R.id.day3);
		day4 = (TextView) findViewById(R.id.day4);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		Locationprovider = locationManager.getBestProvider(criteria, false);
		Location Currentlocation = locationManager
				.getLastKnownLocation(Locationprovider);

		if (Currentlocation != null) {
			// System.out.println("Provider " + Locationprovider +
			// " has been selected.");
			onLocationChanged(Currentlocation);
		} else {
			/*
			 * latitute.setText("Location not available");
			 * longitude.setText("Location not available");
			 */
			latitute.setText("Latitute :38.90156555");
			longitude.setText("Longitude:-77.05078125");
		}
		GetCurrentWeather asyntaskToGetTemp = new GetCurrentWeather();

		asyntaskToGetTemp.execute(Base_URL);
	}

	private class GetCurrentWeather extends AsyncTask<String, Void, Weather> {
		RecordTemp recordTemp = new RecordTemp();
		/*@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressDialog = new ProgressDialog(getBaseContext());
		    progressDialog.setMessage("Loading. Please Wait");
		    progressDialog.setIndeterminate(false);
		    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		    progressDialog.setCancelable(true);
		    progressDialog.show();
		}
		*/
		
		protected Weather doInBackground(String... params) {
			// TODO Auto-generated method stub
			data = ((new httpClientClassToGetTemp())
					.getweatherDetails(params[0]));
			// System.out.println("Data................"+data);
			recordTemp = JsonParser_Weather.parse_json(data);
			
            
			return null;
		}
				

		protected void onPostExecute(Weather weather) {
			if (recordTemp.iconData1 != null && recordTemp.iconData1.length > 0) {
                Bitmap img = BitmapFactory.decodeByteArray(recordTemp.iconData1, 0, recordTemp.iconData1.length);
                imageView1.setImageBitmap(img);
        }
			if (recordTemp.iconData2 != null && recordTemp.iconData2.length > 0) {
                Bitmap img1 = BitmapFactory.decodeByteArray(recordTemp.iconData2, 0, recordTemp.iconData2.length);
                imageView2.setImageBitmap(img1);
        }
			if (recordTemp.iconData3 != null && recordTemp.iconData3.length > 0) {
                Bitmap img2 = BitmapFactory.decodeByteArray(recordTemp.iconData3, 0, recordTemp.iconData3.length);
                imageView3.setImageBitmap(img2);
        }
			
			
			location.setText("City :" + recordTemp.getCity());
			if(unit.equalsIgnoreCase("C")){
			day1.setText("Day 1 : MaxTemp:" + recordTemp.getMaxTempDay1() + unit+"  "
					+ "MinTemp:" + recordTemp.getMinTempDay1() + "C "
					+ "Condition :" + recordTemp.getCondition1());
			day2.setText("Day 2 : MaxTemp:" + recordTemp.getMaxTempDay2() + "C "
					+ "MinTemp:" + recordTemp.getMinTempDay2() + "C "
					+ "Condition :" + recordTemp.getCondition2());
			day3.setText("Day 3 : MaxTemp:" + recordTemp.getMaxTempDay3() + "C "
					+ "MinTemp:" + recordTemp.getMinTempDay3() + "C "
					+ "Condition :" + recordTemp.getCondition3());
			}
			if(unit.equalsIgnoreCase("F")){
				day1.setText("Day 1 : MaxTemp:" + recordTemp.getMaxTempDay1_f() + "F  "
						+ "MinTemp:" + recordTemp.getMinTempDay1_f() + "F "
						+ "Condition :" + recordTemp.getCondition1());
				day2.setText("Day 2 : MaxTemp:" + recordTemp.getMaxTempDay2_f() + "F "
						+ "MinTemp:" + recordTemp.getMinTempDay2_f() + "F "
						+ "Condition :" + recordTemp.getCondition2());
				day3.setText("Day 3 : MaxTemp:" + recordTemp.getMaxTempDay3_f() + "F "
						+ "MinTemp:" + recordTemp.getMinTempDay3_f() + "F "
						+ "Condition :" + recordTemp.getCondition3());
				}
			day4.setVisibility(day4.GONE);
			
			if(daysSelected==4){
				day4.setVisibility(day4.VISIBLE);
				if(unit.equalsIgnoreCase("C")){
				day4.setText("Day 4 : MaxTemp:" + recordTemp.getMaxTempDay4() + "C "
						+ "MinTemp:" + recordTemp.getMinTempDay4() + "C  "
						+ "Condition :" + recordTemp.getCondition4());
				}
				if(unit.equalsIgnoreCase("F")){
					day4.setText("Day 4 : MaxTemp:" + recordTemp.getMaxTempDay4_f() + "F "
							+ "MinTemp:" + recordTemp.getMinTempDay4_f() + "F  "
							+ "Condition :" + recordTemp.getCondition4());
				}
				if (recordTemp.iconData4 != null && recordTemp.iconData4.length > 0) {
	                Bitmap img3 = BitmapFactory.decodeByteArray(recordTemp.iconData4, 0, recordTemp.iconData4.length);
	                imageView4.setImageBitmap(img3);
	        }
			}	
			// progressDialog.dismiss();
		}


		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.default_weather, menu);

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.zipCodeSearch:
			// do your own thing here
			Intent intent = new Intent(DefaultWeather.this,
					SearchWeatherByZip.class);
			startActivity(intent);
			return true;
		case R.id.action_settings_default:	
			Intent intent1 = new Intent(DefaultWeather.this,
					Setting.class);
			startActivity(intent1);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void onLocationChanged(Location location1) {
		double lat = location1.getLatitude();
		double lng = location1.getLongitude();
		// latitute.setText(String.valueOf(lat));
		// longitude.setText(String.valueOf(lng));
		latitute.setText("38.90156555");
		longitude.setText("-77.05078125");
		Geocoder geoCoder1 = new Geocoder(getBaseContext(), Locale.getDefault());
		try {
			String add = "";
			List<Address> addresses;
			addresses = geoCoder1.getFromLocation(lat, lng, 1);
			if (addresses.size() > 0) {
				for (int i = 0; i < addresses.get(0).getMaxAddressLineIndex(); i++)
					add += addresses.get(0).getAddressLine(i) + "\n";
			}
			location.setText(String.valueOf(add));
			Bundle extras = getIntent().getExtras();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
