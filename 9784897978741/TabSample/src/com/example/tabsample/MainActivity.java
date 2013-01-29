package com.example.tabsample;

import android.app.TabActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		TabHost tabHost = getTabHost();
		LayoutInflater.from(this).inflate(R.layout.activity_main, tabHost.getTabContentView(), true);
		
		TextView textview1 = (TextView)findViewById(R.id.textview1);
		textview1.setBackgroundColor(Color.GREEN);
		
		TextView textview2 = (TextView)findViewById(R.id.textview2);
		textview2.setBackgroundColor(Color.BLUE);
		
		TabSpec tab1 = tabHost.newTabSpec("tab1");
		tab1.setIndicator("タブ1");
		tab1.setContent(R.id.linearlayout1);
		
		TabSpec tab2 = tabHost.newTabSpec("tab2");
		tab2.setIndicator("タブ2", getResources().getDrawable(android.R.drawable.ic_menu_edit));
		tab2.setContent(R.id.textview1);
		
		TabSpec tab3 = tabHost.newTabSpec("tab3");
		tab3.setIndicator("タブ3", getResources().getDrawable(android.R.drawable.ic_menu_search));
		tab3.setContent(R.id.textview2);
		
		tabHost.addTab(tab1);
		tabHost.addTab(tab2);
		tabHost.addTab(tab3);
		
		tabHost.setCurrentTab(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
