package com.example.weatherapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;

public class Welcome extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		Thread timer;
		timer = new Thread() {
			public void run() {
					try {
						sleep(1000);
					}
					catch (InterruptedException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					finally {
						Intent openStartingPoint = new Intent(Welcome.this,DefaultWeather.class);
						startActivity(openStartingPoint);
					}
			}

		};
		timer.start();
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//ourSong.stop();
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
		return true;
	}

}
