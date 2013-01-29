package com.example.imosprovider;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class ImosProvider extends ContentProvider {
	private static UriMatcher uriMatcher;
	private ImosDbHelper dbHelper;

	private static final int CODE_MONSTERS = 1;
	private static final int CODE_MONSTER_ID = 2;

	private static HashMap<String, String> projectionMap;
	private static String TABLE_NAME = "monsters";

	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(Monsters.AUTHORITY, "monsters", CODE_MONSTERS);
		uriMatcher.addURI(Monsters.AUTHORITY, "monsters/#", CODE_MONSTER_ID);
		projectionMap = new HashMap<String, String>();
		projectionMap.put(Monsters._ID, Monsters._ID);
		projectionMap.put(Monsters.NAME, Monsters.NAME);
		projectionMap.put(Monsters.RARITY, Monsters.RARITY);
		projectionMap.put(Monsters.ATTACK, Monsters.ATTACK);
		projectionMap.put(Monsters.DEFENCE, Monsters.DEFENCE);
	}

	@Override
	public boolean onCreate() {
		dbHelper = new ImosDbHelper(getContext());
		return true;
	}

	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
		case CODE_MONSTERS:
			return Monsters.CONTENT_TYPE;
		case CODE_MONSTER_ID:
			return Monsters.CONTENT_ITEM_TYPE;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
	}
	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(TABLE_NAME);
		queryBuilder.setProjectionMap(projectionMap);
		switch (uriMatcher.match(uri)) {
		case CODE_MONSTERS:
			break;
		case CODE_MONSTER_ID:
			queryBuilder.appendWhere(Monsters._ID + "=" + ContentUris.parseId(uri));
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor c = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
		return c;
	}
	
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		if (uriMatcher.match(uri) != CODE_MONSTERS) {
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long rowId = db.insert(TABLE_NAME, null, values);
		if (rowId > 0) {
			Uri monsterUri = ContentUris.withAppendedId(Monsters.CONTENT_URI, rowId);
			getContext().getContentResolver().notifyChange(monsterUri, null);
			return uri;
		}
		throw new SQLException("Faild to insert row into: " + uri);
	}
}
