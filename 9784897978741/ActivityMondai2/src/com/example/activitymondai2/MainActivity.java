package com.example.activitymondai2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Spinner spinner = (Spinner)findViewById(R.id.spinner1);
		spinner.setOnItemSelectedListener(
			new OnItemSelectedListener() {
				public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
					Spinner spinner = (Spinner)parent;
					String item = (String)spinner.getItemAtPosition(position);
					EditText input = (EditText)findViewById(R.id.name);
					Toast.makeText(MainActivity.this, item + "\n" + input.getText() + "В≥Вс", Toast.LENGTH_SHORT).show();
				}
				public void onNothingSelected(AdapterView<?> parent) {
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
