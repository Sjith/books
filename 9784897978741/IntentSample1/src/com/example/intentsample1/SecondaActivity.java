package com.example.intentsample1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class SecondaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seconda);
		
		Intent data = getIntent();
		Bundle extras = data.getExtras();
		String disp_pict = extras != null ? extras.getString("SELECTED_PICT") : "";
		
		ImageView image = (ImageView)findViewById(R.id.fruitimage);
		
		if (disp_pict.equals("Apple")) {
			image.setImageResource(R.drawable.apple);
		}
		if (disp_pict.equals("Banana")) {
			image.setImageResource(R.drawable.banana);
		}
		if (disp_pict.equals("Grape")) {
			image.setImageResource(R.drawable.grape);
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
		getMenuInflater().inflate(R.menu.activity_seconda, menu);
		return true;
	}

}
