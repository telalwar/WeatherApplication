package com.example.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Setting extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		String PREFS_NAME = "weather_prefs";
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		final SharedPreferences.Editor editor = settings.edit();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Button but = (Button) findViewById(R.id.Nodays);
		Button but2 = (Button) findViewById(R.id.Nodays3);
		Button butCels = (Button) findViewById(R.id.celsius);
		Button butFah = (Button) findViewById(R.id.fahrenheit);
		but.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editor.putInt("Days", 4);
				editor.commit();
				Toast toast = Toast.makeText(getApplicationContext(),
						"Setting has been saved", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();

			}
		});
		but2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editor.putInt("Days", 3);
				editor.commit();
				Toast toast = Toast.makeText(getApplicationContext(),
						"Setting has been saved", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();

			}
		});
		butCels.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editor.putString("Unit","C");
				editor.commit();
				Toast toast = Toast.makeText(getApplicationContext(),
						"Setting has been saved", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();

			}
		});
		butFah.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editor.putString("Unit","F");
				editor.commit();
				Toast toast = Toast.makeText(getApplicationContext(),
						"Setting has been saved", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();

			}
		});
	}

	/*
	 * public void onItemSelected(AdapterView<?> parent, View view, int pos,
	 * long id) { Toast.makeText(parent.getContext(),
	 * "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
	 * Toast.LENGTH_SHORT).show(); }
	 */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// do your own thing here
			Intent intent = new Intent(Setting.this, DefaultWeather.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
