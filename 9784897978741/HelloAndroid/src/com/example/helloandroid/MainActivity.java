package com.example.helloandroid;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity {
	private final static int MP = LinearLayout.LayoutParams.MATCH_PARENT;
	private final static int WC = LinearLayout.LayoutParams.WRAP_CONTENT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		//setContentView(R.layout.helloworld);
		
		// LayoutÇÃê∂ê¨
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setLayoutParams(new LinearLayout.LayoutParams(MP, WC));
		setContentView(layout);
		
		// TextViewÇÃê∂ê¨
		TextView textView = new TextView(this);
		textView.setText("Hello World!");
		textView.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
		
		layout.addView(textView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
