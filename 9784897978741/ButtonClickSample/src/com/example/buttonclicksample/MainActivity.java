package com.example.buttonclicksample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button = (Button)findViewById(R.id.button1);
		//button.setOnClickListener(new ButtonClickListener());
		
		button.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					EditText input = (EditText)findViewById(R.id.nametext);
					Toast.makeText(MainActivity.this, input.getText(), Toast.LENGTH_SHORT).show();
				}
			}
		);
	}
	
	/*
	class ButtonClickListener implements OnClickListener {
		public void onClick(View v) {
			EditText input = (EditText)findViewById(R.id.nametext);
			Toast.makeText(MainActivity.this, input.getText(), Toast.LENGTH_SHORT).show();
		}
	}
	*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
