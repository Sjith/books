package com.example.tabmondai;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.TabActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends TabActivity implements OnTabChangeListener {
	private static final String FILE_NAME = "TabMondai";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		TabHost tabHost = getTabHost();
		LayoutInflater.from(this).inflate(R.layout.activity_main, tabHost.getTabContentView(), true);
		
		TabSpec tab1 = tabHost.newTabSpec("tab1");
		tab1.setIndicator("設定", getResources().getDrawable(android.R.drawable.ic_menu_add));
		tab1.setContent(R.id.tablelayout1);
		
		TabSpec tab2 = tabHost.newTabSpec("tab2");
		tab2.setIndicator("表示", getResources().getDrawable(android.R.drawable.ic_menu_info_details));
		tab2.setContent(R.id.tablelayout2);
		
		tabHost.addTab(tab1);
		tabHost.addTab(tab2);
		tabHost.setCurrentTab(0);
		
		tabHost.setOnTabChangedListener(this);
		
		Button addButton = (Button)findViewById(R.id.addButton);
		addButton.setOnClickListener(new ButtonClickListener());
	}
	
	@Override
	public void onTabChanged(String tabId) {
		if (tabId.equals("tab2")) {
			TableLayout tablelayout = (TableLayout)findViewById(R.id.tablelayout2);
			tablelayout.removeAllViews();
			tablelayout.setStretchAllColumns(true);
			TextView headtxt1 = new TextView(MainActivity.this);
			headtxt1.setText("商品名");
			headtxt1.setGravity(Gravity.CENTER_HORIZONTAL);
			headtxt1.setWidth(100);
			TextView headtxt2 = new TextView(MainActivity.this);
			headtxt2.setText("価格");
			headtxt2.setGravity(Gravity.CENTER_HORIZONTAL);
			headtxt2.setWidth(60);
			TableRow headrow = new TableRow(MainActivity.this);
			headrow.addView(headtxt1);
			headrow.addView(headtxt2);
			tablelayout.addView(headrow);
			
			try {
				FileInputStream stream = openFileInput(FILE_NAME);
				BufferedReader in = new BufferedReader(new InputStreamReader(stream));
				
				String line = "";
				while ((line = in.readLine()) != null) {
					String lineSplit[] = line.split(",");
					
					TextView nametxt = new TextView(MainActivity.this);
					nametxt.setGravity(Gravity.CENTER_HORIZONTAL);
					nametxt.setText(lineSplit[0]);
					TextView pricetxt = new TextView(MainActivity.this);
					pricetxt.setGravity(Gravity.CENTER_HORIZONTAL);
					pricetxt.setText(lineSplit[1]);
					
					TableRow row = new TableRow(MainActivity.this);
					row.addView(nametxt);
					row.addView(pricetxt);
					tablelayout.addView(row);
				}
				in.close();
			} catch (Exception e) {
				Log.e("ERROR", "file access error");
			}
		}
	}
	
	class ButtonClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			String name = ((EditText)findViewById(R.id.name)).getText().toString();
			String price = ((EditText)findViewById(R.id.price)).getText().toString();
			
			try {
				FileOutputStream stream = openFileOutput(FILE_NAME, MODE_APPEND);
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(stream));
				out.write(name + "," + price);
				out.newLine();
				out.close();
			} catch (Exception e) {
				Log.e("ERROR", "ファイル書き込みに失敗しました");
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
