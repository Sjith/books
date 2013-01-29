package com.example.idlemonster;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ImosDbHelper extends SQLiteOpenHelper {
	
	private static final String DB_NAME = "imos_db";
	private static final int DB_VERSION = 3;
	
	private static final String CREATE_TABLE_MONSTERS = "CREATE TABLE IF NOT EXISTS monsters ( "
		+ "id INTEGER PRIMARY KEY, "
		+ "name TEXT, "
		+ "rarity TEXT, "	// レア度
		+ "attack INTEGER NOT NULL, "
		+ "defence INTEGER NOT NULL )";
	private static final String CREATE_TABLE_SPECIAL_SKILLS = "CREATE TABLE IF NOT EXISTS special_skills ( "
		+ "id INTEGER PRIMARY KEY, "
		+ "monster_id REFERENCES monsters(_id), "
		+ "name TEXT )";
	private static final String CREATE_TABLE_PLAYER_MONSTERS = "CREATE TABLE IF NOT EXISTS player_monsters ( "
		+ "player_id INTEGER, "
		+ "monster_id INTEGER, "
		+ "having_number INTEGER NOT NULL, "
		+ "PRIMARY KEY (player_id, monster_id)) ";

	public ImosDbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	private void setUpMasterDb(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_MONSTERS);
		String insertMonster = "INSERT INTO monsters(id,name,attack,defence) VALUES(?,?,?,?)";
		db.execSQL(insertMonster, new Object[] { 1, "ねこむすめ", 1000, 600 });
		db.execSQL(insertMonster, new Object[] { 2, "すなかけばばあ", 880, 1360 });
		db.execSQL(insertMonster, new Object[] { 3, "ろくろくび", 2300, 1680 });
		
		db.execSQL(CREATE_TABLE_SPECIAL_SKILLS);
		String insertSpecialSkill = "INSERT INTO special_skills(id,monster_id,name) VALUES(?,?,?)";
		db.execSQL(insertSpecialSkill, new Object[] { 1, 1, "ひっかき" });
		db.execSQL(insertSpecialSkill, new Object[] { 2, 2, "すなかけ" });
		db.execSQL(insertSpecialSkill, new Object[] { 3, 2, "たつまき" });
		db.execSQL(insertSpecialSkill, new Object[] { 4, 3, "ぬけくび" });
	}
	
	private void migrateTransactionDb(SQLiteDatabase db, int oldVersion) {
		if (oldVersion < 1) {
			db.execSQL(CREATE_TABLE_PLAYER_MONSTERS);
		}
		if (oldVersion < 2) {
			// DB_VERSION=2 では、トランザクション系テーブルの更新は無し
		}
		if (oldVersion < 3) {
			db.execSQL("ALTER TABLE player_monsters ADD COLUMN first_get_time TEXT");
			db.execSQL("CREATE INDEX pm_idx1 ON player_monsters(first_get_time)");
		}
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		setUpMasterDb(db);
		migrateTransactionDb(db, 0);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE monsters");
		db.execSQL("DROP TABLE special_skills");
		setUpMasterDb(db);
		migrateTransactionDb(db, oldVersion);
	}
}
