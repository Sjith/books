package com.example.dbmondai1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBMondaiHelper extends SQLiteOpenHelper {
	
	public DBMondaiHelper(Context context) {
		super(context, "dbmondai", null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {}

}
