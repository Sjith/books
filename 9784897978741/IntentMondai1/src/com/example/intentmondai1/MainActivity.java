package com.example.intentmondai1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button button = (Button)findViewById(R.id.button1);
		button.setOnClickListener(
			new OnClickListener() {
				@Override
				public void onClick(View v) {
					EditText name = (EditText)findViewById(R.id.name);
					EditText address = (EditText)findViewById(R.id.address);
					Spinner month = (Spinner)findViewById(R.id.month);
					Spinner day = (Spinner)findViewById(R.id.day);
					RadioGroup radio = (RadioGroup)findViewById(R.id.gender);
					RadioButton radiobutton = (RadioButton)findViewById(radio.getCheckedRadioButtonId());
					CheckBox applecheck = (CheckBox)findViewById(R.id.applecheck);
					CheckBox orangecheck = (CheckBox)findViewById(R.id.orangecheck);
					CheckBox peachcheck = (CheckBox)findViewById(R.id.peachcheck);
					EditText appleqty = (EditText)findViewById(R.id.appleqty);
					EditText orangeqty = (EditText)findViewById(R.id.orangeqty);
					EditText peachqty = (EditText)findViewById(R.id.peachqty);
					
					Intent intent = new Intent(MainActivity.this, SecondActivity.class);
					intent.putExtra("NAME", name.getText().toString());
					intent.putExtra("ADDRESS", address.getText().toString());
					intent.putExtra("MONTH", month.getSelectedItem().toString());
					intent.putExtra("DAY", day.getSelectedItem().toString());
					intent.putExtra("GENDER", radiobutton.getText().toString());
					if (applecheck.isChecked()) {
						intent.putExtra("APPLE", appleqty.getText().toString());
					}
					if (orangecheck.isChecked()) {
						intent.putExtra("ORANGE", orangeqty.getText().toString());
					}
					if (peachcheck.isChecked()) {
						intent.putExtra("PEACH", peachqty.getText().toString());
					}
					startActivity(intent);
				}
			}
		);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
