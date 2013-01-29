package com.example.addresslist;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView listView = (ListView)findViewById(R.id.listView);
		loadAddressData(listView);
	}
	
	private void loadAddressData(ListView listView) {
		Cursor cursor = getContentResolver().query(
			ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null
			);
		startManagingCursor(cursor);
		
		int contact_id = cursor.getColumnIndexOrThrow(Phone.CONTACT_ID);
		int display_name = cursor.getColumnIndexOrThrow(Phone.DISPLAY_NAME);
		int phone_number = cursor.getColumnIndexOrThrow(Phone.DATA);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		while (cursor.moveToNext()) {
			String row = String.format("[%s] %s \n%s",
				cursor.getString(contact_id),
				cursor.getString(display_name),
				cursor.getString(phone_number));
			adapter.add(row);
		}
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
