package com.example.contentprovidersample1;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {
	CreateProductHelper helper = null;
	SQLiteDatabase db = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button insertButton = (Button)findViewById(R.id.insert);
		insertButton.setTag("insert");
		insertButton.setOnClickListener(new ButtonClickListener());
		
		Button updateButton = (Button)findViewById(R.id.update);
		updateButton.setTag("update");
		updateButton.setOnClickListener(new ButtonClickListener());
		
		Button deleteButton = (Button)findViewById(R.id.delete);
		deleteButton.setTag("delete");
		deleteButton.setOnClickListener(new ButtonClickListener());
		
		Button selectButton = (Button)findViewById(R.id.select);
		selectButton.setTag("select");
		selectButton.setOnClickListener(new ButtonClickListener());
		
		helper = new CreateProductHelper(MainActivity.this);
	}
	
	class ButtonClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			String tag = (String)v.getTag();
			String message = "";
			TextView label = (TextView)findViewById(R.id.message);
			EditText productid = (EditText)findViewById(R.id.productid);
			EditText name = (EditText)findViewById(R.id.name);
			EditText price = (EditText)findViewById(R.id.price);
			TableLayout tablelayout = (TableLayout)findViewById(R.id.list);
			tablelayout.removeAllViews();
			db = helper.getWritableDatabase();
			if (tag.equals("insert")) {
				try {
					String sql = "CREATE TABLE product ("
							+ "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
							+ "productid TEXT NOT NULL, "
							+ "name TEXT NOT NULL, "
							+ "price INTEGER DEFAULT(0))";
					db.execSQL(sql);
					message = "テーブルを作成しました！\n";
				} catch (Exception e) {
					message = "テーブルは作成されています！\n";
					Log.e("ERROR", e.toString());
				}
				
				try {
					db.beginTransaction();
					ContentValues val = new ContentValues();
					val.put("productid", productid.getText().toString());
					val.put("name", name.getText().toString());
					val.put("price", price.getText().toString());
					db.insert("product", null, val);
					db.setTransactionSuccessful();	// commit
					db.endTransaction();
					message += "データを登録しました！";
				} catch (Exception e) {
					message = "データ登録に失敗しました！";
					Log.e("ERROR", e.toString());
				}
			} else if (tag.equals("update")) {
				try {
					String condition = null;
					if (productid != null && !productid.equals("")) {
						condition = "productid = '" + productid.getText().toString() + "'";
					}
					db.beginTransaction();
					ContentValues val = new ContentValues();
					val.put("name", name.getText().toString());
					val.put("price", price.getText().toString());
					db.update("product", val, condition, null);
					db.setTransactionSuccessful();
					db.endTransaction();
					message = "データを更新しました！";
				} catch (Exception e) {
					message = "データ更新に失敗しました！";
					Log.e("ERROR", e.toString());
				}
			} else if (tag.equals("delete")) {
				try {
					String condition = null;
					if (productid != null && !productid.equals("")) {
						condition = "productid = '" + productid.getText().toString() + "'";
					}
					db.beginTransaction();
					db.delete("product", condition, null);
					db.setTransactionSuccessful();
					db.endTransaction();
					message = "データを削除しました！";
				} catch (Exception e) {
					message = "データ削除に失敗しました！";
					Log.e("ERROR", e.toString());
				}
			} else if (tag.equals("select")) {
				try {
					db = helper.getReadableDatabase();
					String columns[] = {"productid", "name", "price"};
					Cursor cursor = db.query("product", columns, null, null, null, null, "productid");
					tablelayout.setStretchAllColumns(true);
					TableRow headrow = new TableRow(MainActivity.this);
					TextView headtxt1 = new TextView(MainActivity.this);
					headtxt1.setText("商品ID");
					headtxt1.setGravity(Gravity.CENTER_HORIZONTAL);
					headtxt1.setWidth(60);
					TextView headtxt2 = new TextView(MainActivity.this);
					headtxt2.setText("商品名");
					headtxt2.setGravity(Gravity.CENTER_HORIZONTAL);
					headtxt2.setWidth(100);
					TextView headtxt3 = new TextView(MainActivity.this);
					headtxt3.setText("価格");
					headtxt3.setGravity(Gravity.CENTER_HORIZONTAL);
					headtxt3.setWidth(60);
					headrow.addView(headtxt1);
					headrow.addView(headtxt2);
					headrow.addView(headtxt3);
					tablelayout.addView(headrow);
					
					while (cursor.moveToNext()) {
						TableRow row = new TableRow(MainActivity.this);
						TextView productidtxt = new TextView(MainActivity.this);
						productidtxt.setGravity(Gravity.CENTER_HORIZONTAL);
						productidtxt.setText(cursor.getString(0));
						TextView nametxt = new TextView(MainActivity.this);
						nametxt.setGravity(Gravity.CENTER_HORIZONTAL);
						nametxt.setText(cursor.getString(1));
						TextView pricetxt = new TextView(MainActivity.this);
						pricetxt.setGravity(Gravity.CENTER_HORIZONTAL);
						pricetxt.setText(cursor.getString(2));
						row.addView(productidtxt);
						row.addView(nametxt);
						row.addView(pricetxt);
						tablelayout.addView(row);
						message = "データを取得しました！";
					}
				} catch (Exception e) {
					message = "データ取得に失敗しました！";
					Log.e("ERROR", e.toString());
				}
			}
			
			db.close();
			label.setText(message);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
