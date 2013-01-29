package com.example.implicitintentsample2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button button = (Button)findViewById(R.id.button1);
		button.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					EditText content = (EditText)findViewById(R.id.content);
					String forwardstr = content.getText().toString();
					
					Spinner selectapli = (Spinner)findViewById(R.id.selectapli);
					String selecteditem = (String)selectapli.getSelectedItem();
					
					String action = null;
					if (selecteditem.equals("電話")) {
						action = "android.intent.action.DIAL";
						forwardstr = "tel:" + content.getText().toString();
					} else if (selecteditem.equals("WEBブラウザ")) {
						action = "android.intent.action.VIEW";
					} else if (selecteditem.equals("地図")) {
						action = "android.intent.action.VIEW";
						forwardstr = "geo:" + content.getText().toString();
					}
					
					Intent intent = new Intent(action, Uri.parse(forwardstr));
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
