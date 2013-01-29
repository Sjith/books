package com.example.apple;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Uri uri = getIntent().getData();
		if (uri != null) {
			String fruitname = uri.getQueryParameter("selecteditem");
			TextView fruittext = (TextView)findViewById(R.id.fruitname);
			fruittext.setText(fruitname);
		}
		
		Button button = (Button)findViewById(R.id.backbutton);
		button.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					finish();
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
