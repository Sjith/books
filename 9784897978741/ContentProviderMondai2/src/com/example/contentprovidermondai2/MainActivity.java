package com.example.contentprovidermondai2;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {
	TableLayout tablelayout = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tablelayout = (TableLayout)findViewById(R.id.tablelayout);
		
		try {
			Cursor cur = managedQuery(CallLog.Calls.CONTENT_URI, null, null, null, null);
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					String name = cur.getString(cur.getColumnIndex(CallLog.Calls.NUMBER));
					setItems(name);
				}
			} else {
				TextView message = new TextView(this);
				message.setText("データが取得できませんでした。");
				LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearlayout);
				linearLayout.addView(message);
			}
		} catch (Exception e) {
			Log.e("ERROR", e.toString());
		}
	}
	
	private void setItems(String name) {
		TableRow row = new TableRow(this);
		TextView displayName = new TextView(this);
		displayName.setText(name);
		row.addView(displayName);
		tablelayout.addView(row);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
