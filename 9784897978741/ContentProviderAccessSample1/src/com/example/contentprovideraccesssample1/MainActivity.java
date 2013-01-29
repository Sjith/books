package com.example.contentprovideraccesssample1;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button insertButton = (Button)findViewById(R.id.insert);
		insertButton.setTag("insert");
		insertButton.setOnClickListener(new ButtonClickListener());
		
		Button selectButton = (Button)findViewById(R.id.select);
		selectButton.setTag("select");
		selectButton.setOnClickListener(new ButtonClickListener());
		
		Button updateButton = (Button)findViewById(R.id.update);
		updateButton.setTag("update");
		updateButton.setOnClickListener(new ButtonClickListener());
		
		Button deleteButton = (Button)findViewById(R.id.delete);
		deleteButton.setTag("delete");
		deleteButton.setOnClickListener(new ButtonClickListener());
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
			Uri uri = Uri.parse("content://com.example.contentprovidersample1");
			TableLayout tablelayout = (TableLayout)findViewById(R.id.list);
			tablelayout.removeAllViews();
			if (tag.equals("insert")) {
				try {
					ContentValues val = new ContentValues();
					val.put("productid", productid.getText().toString());
					val.put("name", name.getText().toString());
					val.put("price", price.getText().toString());
					getContentResolver().insert(uri, val);
					message += "データを登録しました！";
				} catch (Exception e) {
					message = "データ登録に失敗しました！";
					Log.e("ERROR", e.toString());
				}
			} else if (tag.equals("select")) {
				try {
					Cursor cursor = managedQuery(uri, null, null, null, null);
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
						productidtxt.setText(cursor.getString(1));
						TextView nametxt = new TextView(MainActivity.this);
						nametxt.setGravity(Gravity.CENTER_HORIZONTAL);
						nametxt.setText(cursor.getString(1));
						TextView pricetxt = new TextView(MainActivity.this);
						pricetxt.setGravity(Gravity.CENTER_HORIZONTAL);
						pricetxt.setText(cursor.getString(1));
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
			} else if (tag.equals("update")) {
				try {
					ContentValues val = new ContentValues();
					val.put("productid", productid.getText().toString());
					val.put("name", "***" + name.getText().toString() + "***");
					val.put("price", price.getText().toString());
					String condition = "productid = '" + productid.getText().toString() + "'";
					getContentResolver().update(uri, val, condition, null);
					message += "データを更新しました！";
				} catch (Exception e) {
					message = "データ更新に失敗しました！";
					Log.e("ERROR", e.toString());
				}
			} else if (tag.equals("delete")) {
				try {
					String condition = "productid = '" + productid.getText().toString() + "'";
					getContentResolver().delete(uri, condition, null);
					message += "データを削除しました！";
				} catch (Exception e) {
					message = "データを削除しました！";
					Log.e("ERROR", e.toString());
				}
			}
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
