package com.example.weatherapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchWeatherByLanguage_Marathi extends Activity {
	String strZip;
	EditText zipcode;
	CharSequence text;
	Context context;
	int duration;
	String Language="MR";
	EditText etValue_marathi;
	 String srtZip_marathi;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_weather_by_language__marathi);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Button butFind = (Button) findViewById(R.id.butFind_marathi);
		butFind.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 etValue_marathi= (EditText)findViewById(R.id.etValue_marathi);
				 srtZip_marathi = etValue_marathi.getText().toString();
				 if (!srtZip_marathi.isEmpty()) {
						Intent openStartingPoint = new Intent(SearchWeatherByLanguage_Marathi.this, Weather.class);
						openStartingPoint.putExtra("com.example.weatherapp.SearchWeatherByZip",srtZip_marathi);
						openStartingPoint.putExtra("com.example.weatherapp.SearchWeatherByLanguage",Language);
						startActivity(openStartingPoint);

					}
					else {
					context = getApplicationContext();
					text = "Please enter Zip Code";
					duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, text, duration);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
					}
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_weather_by_language__marathi,
				menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// do your own thing here
			Intent intent = new Intent(SearchWeatherByLanguage_Marathi.this, SearchWeatherByZip.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
			
		}
		
	}

}
