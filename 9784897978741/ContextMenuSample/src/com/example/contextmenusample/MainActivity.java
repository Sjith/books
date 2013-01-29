package com.example.contextmenusample;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		List<String> list = new ArrayList<String>();
		list.add("選択1");
		list.add("選択2");
		list.add("選択3");
		
		ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
		
		ListView listview = (ListView)findViewById(R.id.listview);
		listview.setAdapter(adapter);
		
		// コンテキストメニューにリストビューを登録
		registerForContextMenu(listview);
	}
	
	// コンテキストメニュー生成
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		menu.setHeaderTitle("コンテキストメニュー");
		menu.add("メニュー1");
		menu.add("メニュー2");
		menu.add("メニュー3");
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	// コンテキストメニュー選択
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		TextView textview = (TextView)findViewById(R.id.textview);
		textview.setText("コンテキストメニューで選択" + item.getTitle());
		return super.onContextItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
