package com.example.idlemonster;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView monsterList = (ListView)findViewById(R.id.monster_list);
		loadListData(monsterList, "SELECT id,name FROM monsters");
		
		ListView specialSkillList = (ListView)findViewById(R.id.special_skill_list);
		loadListData(specialSkillList, "SELECT id,name FROM special_skills");
	}
	
	private void loadListData(ListView view, String sql) {
		ArrayAdapter<String> simpleAdapter = new ArrayAdapter<String>(
			this, android.R.layout.simple_expandable_list_item_1);
		view.setAdapter(simpleAdapter);
		
		ImosDbHelper dbHelper = new ImosDbHelper(this);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor c = db.rawQuery(sql, null);
		c.moveToFirst();
		for (int i = 0; i < c.getCount(); i++) {
			simpleAdapter.add(c.getString(0) + ":" + c.getString(1));
			c.moveToNext();
		}
		c.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
