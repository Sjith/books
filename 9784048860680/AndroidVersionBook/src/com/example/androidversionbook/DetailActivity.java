package com.example.androidversionbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends Activity {
	private TextView jaNameText = null;
	private TextView verNameText = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		jaNameText = (TextView)findViewById(R.id.textplatformJaName);
		verNameText = (TextView)findViewById(R.id.textplatformApiLevel);
		
		Intent i = getIntent();
		int param = i.getIntExtra("ID", 0);
		
		showData(param);
	}
	
	private void showData(int param) {
		String jaName = getResources().getStringArray(R.array.platformJaName)[param];
		jaNameText.setText(jaName);
		String verName = getResources().getStringArray(R.array.platformApiLevel)[param];
		verNameText.setText(verName);
	}

}
