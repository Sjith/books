package com.example.dbsample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateProductHelper extends SQLiteOpenHelper {
	
	public CreateProductHelper(Context con) {
		super(con, "dbsample", null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
	}

}