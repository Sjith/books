package com.example.greeting;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
		button.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					EditText hourtext = (EditText)findViewById(R.id.hourtext);
					
					int hour = 0;
					try {
						hour = Integer.parseInt(hourtext.getText().toString());
					} catch (NumberFormatException e) {
						Toast.makeText(MainActivity.this, "•s³‚È’l‚ª“ü—Í‚³‚ê‚Ü‚µ‚½I", Toast.LENGTH_SHORT).show();
						return;
					}
					
					String greeting = "";
					String uristr = "intentmondai:///";
					if (hour >= 4 && hour <= 12) {
						greeting = "‚¨‚Í‚æ‚¤";
						uristr += "goodmorning";
					} else if (hour >= 13 && hour <= 18) {
						greeting = "‚±‚ñ‚É‚¿‚Í";
						uristr += "goodafternoon";
					} else {
						greeting = "‚±‚ñ‚Î‚ñ‚Í";
						uristr += "goodevening";
					}
					
					Intent intent = new Intent(Intent.ACTION_VIEW);
					Uri uri = Uri.parse(uristr);
					intent.setData(uri);
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
