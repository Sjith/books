package com.example.intentsample2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private final static int SHOSW_CALC = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button button = (Button)findViewById(R.id.nextbutton);
		button.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent(MainActivity.this, SecondActivity.class);
					startActivityForResult(intent, SHOSW_CALC);
				}
			}
		);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == SHOSW_CALC) {
			if (resultCode == RESULT_OK) {
				Bundle extra = data.getExtras();
				String selectedGreeting = extra.getString("SELECTED_GREETING");
				
				EditText input = (EditText)findViewById(R.id.nametext);
				
				Toast.makeText(MainActivity.this, selectedGreeting + "\n" + input.getText() + "В≥Вс", Toast.LENGTH_SHORT).show();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
