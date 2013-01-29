package com.example.implicitintentmodai2;

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
				@Override
				public void onClick(View v) {
					Spinner spinner = (Spinner)findViewById(R.id.selectapli);
					String item = (String)spinner.getSelectedItem();
					
					EditText content = (EditText)findViewById(R.id.content);
					
					Intent intent = new Intent();
					
					String action = "";
					String uri = "";
					if (item.equals("電話")) {
						action = "android.intent.action.DIAL";
						uri = "tel:" + content.getText().toString();
					} else if (item.equals("WEBブラウザ")) {
						action = "android.intent.action.VIEW";
						uri = content.getText().toString();
					} else if (item.equals("地図")) {
						action = "android.intent.action.VIEW";
						uri = "geo:" + content.getText().toString();
					} else if (item.equals("メッセージ")) {
						action = "android.intent.action.VIEW";
						uri = "";
						intent.putExtra("sms_body", content.getText().toString());
						intent.setType("vnd.android-dir/mms-sms");
					}
					
					intent.setAction(action);
					intent.setData(Uri.parse(uri));
					
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
