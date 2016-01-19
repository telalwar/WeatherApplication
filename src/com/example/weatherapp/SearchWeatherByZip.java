package com.example.weatherapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchWeatherByZip extends Activity {
	String strZip = "";
	EditText zipcode;
	Context context;
	int duration;
	CharSequence text;
	String Language="EN";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_weather_by_zip);
		Button butFind = (Button) findViewById(R.id.butFind);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		butFind.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				zipcode = (EditText) findViewById(R.id.etValue);
				strZip = zipcode.getText().toString();
				if (strZip.length()>5) {
					context = getApplicationContext();
					text = "Please enter Valid Zip Code";
					duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, text, duration);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
				}
				else if(strZip.length()<5) {
					context = getApplicationContext();
					text = "Please enter valid Zip Code";
					duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, text, duration);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
					}
				else if(strZip.isEmpty()) {
					context = getApplicationContext();
					text = "Please enter Zip Code";
					duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, text, duration);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
					}
				else {
					Intent openStartingPoint = new Intent(SearchWeatherByZip.this, Weather.class);
					openStartingPoint.putExtra("com.example.weatherapp.SearchWeatherByZip",strZip);
					openStartingPoint.putExtra("com.example.weatherapp.SearchWeatherByLanguage",Language);
					startActivity(openStartingPoint);

				}
				 
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_weather_by_zip, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// do your own thing here
			Intent intent = new Intent(SearchWeatherByZip.this, DefaultWeather.class);
			startActivity(intent);
			return true;
		case R.id.SearchWeatherInMarathi:
			Intent intent1 = new Intent(SearchWeatherByZip.this, SearchWeatherByLanguage_Marathi.class);
			startActivity(intent1);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
		
	}

}
