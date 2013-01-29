package com.example.dialogsample;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends Activity {
	TextView label = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		label = (TextView)findViewById(R.id.label_dialogtext);
		
		Button dialogButton = (Button)findViewById(R.id.dialogButton);
		dialogButton.setTag("dialog");
		dialogButton.setOnClickListener(new ButtonClickListener());
		
		Button textDialogButton = (Button)findViewById(R.id.textDialogButton);
		textDialogButton.setTag("textDialog");
		textDialogButton.setOnClickListener(new ButtonClickListener());
		
		Button singleSelectDialogButton = (Button)findViewById(R.id.singleSelectDialogButton);
		singleSelectDialogButton.setTag("singleSelectDialog");
		singleSelectDialogButton.setOnClickListener(new ButtonClickListener());
		
		Button datePickerDialogButton = (Button)findViewById(R.id.datePickerDialogButton);
		datePickerDialogButton.setTag("datePickerDialog");
		datePickerDialogButton.setOnClickListener(new ButtonClickListener());
		
		Button timePickerDialogButton = (Button)findViewById(R.id.timePickerDialogButton);
		timePickerDialogButton.setTag("timePickerDialog");
		timePickerDialogButton.setOnClickListener(new ButtonClickListener());
		
		Button progressDialogButton = (Button)findViewById(R.id.progressDialogButton);
		progressDialogButton.setTag("progressDialog");
		progressDialogButton.setOnClickListener(new ButtonClickListener());
	}
	
	class ButtonClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			String tag = (String)v.getTag();
			if (tag.equals("dialog")) {
				showDialog();
			} else if (tag.equals("textDialog")) {
				showTextDialog();
			} else if (tag.equals("singleSelectDialog")) {
				showSingleSelectDialog();
			} else if (tag.equals("datePickerDialog")) {
				showDatePickerDialog();
			} else if (tag.equals("timePickerDialog")) {
				showTimePickerDialog();
			} else if (tag.equals("progressDialog")) {
				showProgressDialog();
			}
		}
	}
	
	private void showDialog() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
		dialog.setTitle("通常ダイアログ");
		dialog.setMessage("選択してください。");
		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				label.setText("通常ダイアログ:OKが選択されました。");
			}
		});
		dialog.setNegativeButton("NG", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				label.setText("通常ダイアログ:NGが選択されました。");
			}
		});
		dialog.show();
	}
	
	public void showTextDialog() {
		final EditText editText = new EditText(MainActivity.this);
		AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
		dialog.setTitle("テキストダイアログ");
		dialog.setMessage("テキストを入力してください。");
		dialog.setView(editText);
		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				label.setText("テキストダイアログ:" + editText.getText().toString() + "が入力されました。");
			}
		});
		dialog.show();
	}
	
	final String[] items = new String[]{"もも", "うめ", "さくら"};
	int which = 0;
	public void showSingleSelectDialog() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
		dialog.setTitle("単一選択ダイアログ");
		dialog.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int _which) {
				Log.d("DEBUG", "which = " + _which);
				which = _which;
			}
		});
		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int _which) {
				String selected = items[which];
				label.setText("単一選択ダイアログ:" + selected + "が入力されました。");
			}
		});
		dialog.show();
	}
	
	public void showDatePickerDialog() {
		Calendar cal = Calendar.getInstance();
		DatePickerDialog dialog = new DatePickerDialog(MainActivity.this
			, new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
					label.setText("日付選択ダイアログ:" + year + "年" + (monthOfYear+1) + "月" + dayOfMonth + "日");
				}
			}
			, cal.get(Calendar.YEAR)
			, cal.get(Calendar.MONTH)
			, cal.get(Calendar.DAY_OF_MONTH)
		);
		dialog.show();
	}
	
	public void showTimePickerDialog() {
		TimePickerDialog dialog = new TimePickerDialog(MainActivity.this
			, new TimePickerDialog.OnTimeSetListener() {
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					label.setText("時間選択ダイアログ:" + hourOfDay + "時" + minute + "分");
				}
			}
			, 0
			, 0
			, true
		);
		dialog.show();
	}
	
	ProgressDialog dialog;
	public void showProgressDialog() {
		dialog = new ProgressDialog(MainActivity.this);
		dialog.setTitle("プログレスバーダイアログ");
		dialog.setMessage("しばらくお待ちください・・・");
		dialog.setIndeterminate(false);
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		//dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		dialog.setMax(100);
		dialog.setCancelable(false);
		dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
			}
		});
		
		dialog.show();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for (int i = 0; i < dialog.getMax(); i++) {
						dialog.setProgress(i);
						Thread.sleep(100);
					}
				} catch (Exception e) {}
				dialog.dismiss();
			}
		}).start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
