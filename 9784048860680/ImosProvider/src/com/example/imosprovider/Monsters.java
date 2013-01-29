package com.example.imosprovider;

import android.net.Uri;
import android.provider.BaseColumns;

public class Monsters implements BaseColumns {
	// コンテントプロバイダ識別用の情報
	public static final String AUTHORITY = "com.example.imposprovider";
	// URI定数
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/monsters");
	// CONTENT_URIのMIMEタイプ(モンスター全体)
	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.imos.monsters";
	// CONTENT_URIのMIMEタイプ(個々のモンスター)
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.imos.monsters";

	// 以下、コンテントプロバイダ使用時の列指定。imos_dbのmonstersに対応
	
	public static final String _ID = "id";
	public static final String NAME = "name";
	public static final String RARITY = "rarity";
	public static final String ATTACK = "attack";
	public static final String DEFENCE = "defence";
}
