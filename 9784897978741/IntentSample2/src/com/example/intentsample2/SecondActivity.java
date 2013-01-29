package com.example.intentsample2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		Button button = (Button)findViewById(R.id.backbutton);
		button.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					Intent intent = getIntent();
					
					Spinner spinner = (Spinner)findViewById(R.id.greeting);
					String greeting = (String)spinner.getSelectedItem();
					
					intent.putExtra("SELECTED_GREETING", greeting);
					
					setResult(RESULT_OK, intent);
					
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
