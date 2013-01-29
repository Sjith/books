package com.example.preferencessample;

import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private String getFirstBootTimeText() {
		SharedPreferences preferences = getSharedPreferences("config", Context.MODE_PRIVATE);
		String firstBootTimeText = preferences.getString("FIRST_BOOT", null);
		if (firstBootTimeText == null) {
			firstBootTimeText = new Date().toString();
			Editor editor = preferences.edit();
			editor.putString("FIRST_BOOT", firstBootTimeText);
			editor.commit();
			Toast.makeText(this, "初回起動時刻を保存しました。", Toast.LENGTH_LONG).show();
		}
		return firstBootTimeText;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String firstBootTimeText = getFirstBootTimeText();
		TextView firstBootText = (TextView)findViewById(R.id.first_boot);
		firstBootText.setText("FIRST_BOOT:" + firstBootTimeText);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
