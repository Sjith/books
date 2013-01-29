package com.example.implicitintentsample1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView listview = (ListView)findViewById(R.id.fruitlist);
		listview.setOnItemClickListener(
			new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					ListView listview = (ListView)parent;
					String item = (String)listview.getItemAtPosition(position);
					
					String uriStr = "";
					if (item.equals("Apple")) {
						uriStr += "intentsample://fruit/apple?selecteditem=" + item;
					} else if (item.equals("Banana")) {
						uriStr += "intentsample://fruit/banana?selecteditem=" + item;
					} else if (item.equals("Grape")) {
						uriStr += "intentsample://fruit/grape?selecteditem=" + item;
					} else {
						uriStr += "intentsample://fruitall?selecteditem=all";
					}
					Uri uri = Uri.parse(uriStr);
					
					Intent intent = new Intent(Intent.ACTION_VIEW);
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
