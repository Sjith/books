package com.example.englishwordbooksample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class EnglishWordBookDbUtil {
	private SQLiteOpenHelper helper;
	private SQLiteDatabase db;
	
	private static final String DB_NAME = "wordbook";
	private static final String TABLE_NAME = "wordbook";
	private static final String C_ID = "_id";
	private static final String C_ENGLISH_WORD = "englishword";
	private static final String C_JAPANESE_WORD = "japaneseword";
	
	public EnglishWordBookDbUtil(Context con) {
		helper = new SQLiteOpenHelper(con, DB_NAME, null, 1) {
			@Override
			public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {}
			
			@Override
			public void onCreate(SQLiteDatabase database) {}
		};
		
		try {
			db = helper.getWritableDatabase();
			StringBuilder sql = new StringBuilder();
			sql.append("CREATE TABLE " + TABLE_NAME + "(");
			sql.append(C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,");
			sql.append(C_ENGLISH_WORD + " TEXT NOT NULL,");
			sql.append(C_JAPANESE_WORD + " TEXT NOT NULL");
			sql.append(")");
			db.execSQL(sql.toString());
		} catch (Throwable th) {
			Log.w(getClass().getSimpleName(), "テーブルの作成に失敗しました。", th);
		} finally {
			db.close();
		}
	}
	
	public void addWord(WordBean wbn) {
		try {
			db = helper.getWritableDatabase();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO " + TABLE_NAME + " VALUES(");
			sql.append("NULL,");
			sql.append("'" + wbn.getEnglishword() + "',");
			sql.append("'" + wbn.getJapaneseword() + "'");
			sql.append(")");
			db.execSQL(sql.toString());
		} catch (Throwable th) {
			Log.w(getClass().getSimpleName(), "テーブルへのデータ登録に失敗しました。" ,th);
		} finally {
			db.close();
		}
	}
}
