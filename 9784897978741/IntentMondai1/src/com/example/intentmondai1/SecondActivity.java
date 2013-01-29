package com.example.intentmondai1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		Bundle extra = getIntent().getExtras();
		String name = extra.getString("NAME");
		String address = extra.getString("ADDRESS");
		String month = extra.getString("MONTH");
		String day = extra.getString("DAY");
		String gender = extra.getString("GENDER");
		String apple = extra.getString("APPLE");
		String orange = extra.getString("ORANGE");
		String peach = extra.getString("PEACH");
		
		TextView inputName = (TextView)findViewById(R.id.name);
		TextView inputAddress = (TextView)findViewById(R.id.address);
		TextView inputMonth = (TextView)findViewById(R.id.month);
		TextView inputDay = (TextView)findViewById(R.id.day);
		TextView inputGender = (TextView)findViewById(R.id.gender);
		TextView inputApple = (TextView)findViewById(R.id.apple);
		TextView inputOrange = (TextView)findViewById(R.id.orange);
		TextView inputPeach = (TextView)findViewById(R.id.peach);
		
		inputName.setText(name);
		inputAddress.setText(address);
		inputMonth.setText(month);
		inputDay.setText(day);
		inputGender.setText(gender);
		if (apple != null) inputApple.setText(apple);
		if (orange != null) inputOrange.setText(orange);
		if (peach != null) inputPeach.setText(peach);
		
		Button button = (Button)findViewById(R.id.backbutton);
		button.setOnClickListener(
			new OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			}
		);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_second, menu);
		return true;
	}

}
