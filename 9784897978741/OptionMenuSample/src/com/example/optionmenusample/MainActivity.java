package com.example.optionmenusample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		
		MenuItem item1 = menu.add(0, 0, 0, "item1");
		
		MenuItem item2 = menu.add(0, 1, 0, "item2");
		item2.setIcon(android.R.drawable.ic_menu_search);
		
		MenuItem item3 = menu.add(0, 2, 0, "item3");
		item3.setIcon(android.R.drawable.ic_menu_save);
		
		MenuItem item4 = menu.add(0, 3, 0, "item4");
		item4.setIcon(android.R.drawable.ic_menu_call);
		
		MenuItem item5 = menu.add(0, 4, 0, "item5");
		item5.setIcon(android.R.drawable.ic_menu_camera);
		
		SubMenu item6 = menu.addSubMenu(0, 5, 0, "その他");
		item6.setIcon(android.R.drawable.ic_menu_more);
		item6.add(0, 10, 0, "subitem1");
		item6.add(0, 20, 0, "subitem2");
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			showDialog("メニューアイテム1を選択しました。");
			return true;
		case 1:
			showDialog("メニューアイテム2を選択しました。");
			return true;
		case 2:
			showDialog("メニューアイテム3を選択しました。");
			return true;
		case 3:
			showDialog("メニューアイテム4を選択しました。");
			return true;
		case 4:
			showDialog("メニューアイテム5を選択しました。");
			return true;
		case 10:
			showDialog("サブメニューアイテム1を選択しました。");
			return true;
		case 20:
			showDialog("サブメニューアイテム2を選択しました。");
			return true;
		}
		return true;
	}
	
	private void showDialog(String text) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
		dialog.setTitle("メニューアイテム選択結果");
		dialog.setMessage(text);
		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				MainActivity.this.setResult(Activity.RESULT_OK);
			}
		});
		dialog.create();
		dialog.show();
	}

}
