package com.example.startservicesample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button startButton = (Button)findViewById(R.id.startButton);
		startButton.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent(MainActivity.this, StartServiceSampleService.class);
					
					EditText stopcount = (EditText)findViewById(R.id.stopcount);
					intent.putExtra("STOPTIME", stopcount.getText().toString());
					
					startService(intent);
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
