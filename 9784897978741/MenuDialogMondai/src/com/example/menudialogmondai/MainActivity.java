package com.example.menudialogmondai;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;

public class MainActivity extends Activity {
	private static final String FILE_NAME = "MenuDialogFile";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		
		MenuItem item1 = menu.add(0, 0, 0, "設定");
		item1.setIcon(android.R.drawable.ic_menu_add);
		
		MenuItem item2 = menu.add(0, 1, 0, "表示");
		item2.setIcon(android.R.drawable.ic_menu_delete);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			showDatePickerDialog();
			return true;
		case 1:
			SharedPreferences preference = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
			String date = preference.getString("DATE", "ありません");
			showDialog(date);
			return true;
		}
		return true;
	}
	
	public void showDatePickerDialog() {
		Calendar cal = Calendar.getInstance();
		DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				SharedPreferences preference = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
				SharedPreferences.Editor editor = preference.edit();
				editor.putString("DATE", year + "年" + (monthOfYear+1) + "月" + dayOfMonth + "日");
				editor.commit();
			}
		}, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
		dialog.show();
	}
	
	private void showDialog(String text) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
		dialog.setTitle("保存結果");
		dialog.setMessage(text);
		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				MainActivity.this.setResult(Activity.RESULT_OK);
			}
		});
		dialog.create();
		dialog.show();
	}

}
