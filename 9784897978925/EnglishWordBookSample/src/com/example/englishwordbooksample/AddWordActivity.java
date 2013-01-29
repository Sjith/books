package com.example.englishwordbooksample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddWordActivity extends Activity {
	private EnglishWordBookDbUtil dbUtil;
	static final String BTN_SAVE = "save";
	static final String BTN_TOP = "top";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_word);
		
		Button btnSave = (Button)findViewById(R.id.btn_save);
		btnSave.setTag(BTN_SAVE);
		btnSave.setOnClickListener(buttonClickListener);
		
		Button btnTopPage = (Button)findViewById(R.id.btn_toppage);
		btnTopPage.setTag(BTN_TOP);
		btnTopPage.setOnClickListener(buttonClickListener);
		
		dbUtil = new EnglishWordBookDbUtil(this);
	}
	
	private OnClickListener buttonClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Button button = (Button)v;
			if (button.getTag().equals(BTN_SAVE)) {
				AddWordActivity.this.showDialog();
			} else if (button.getTag().equals(BTN_TOP)) {
				finish();
			}
		}
	};
	
	private void showDialog() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(AddWordActivity.this);
		dialog.setTitle(R.string.add_confirm_text);
		dialog.setMessage(R.string.confirm_message_text);
		dialog.setPositiveButton(R.string.yes_text, dialogPositiveButtonClickListener);
		dialog.setNegativeButton(R.string.no_text, dialogNegativeButtonClickListener);
		dialog.show();
	}
	
	private DialogInterface.OnClickListener dialogPositiveButtonClickListener =
		new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				EditText etEnglishword = (EditText)findViewById(R.id.et_englishword);
				EditText etJapaneseword = (EditText)findViewById(R.id.et_japanesehword);
				
				if ((etEnglishword.getText().toString().equals("")) || (etJapaneseword.getText().toString().equals(""))) {
					Toast.makeText(AddWordActivity.this, R.string.input_words_text, Toast.LENGTH_LONG).show();
					return;
				}
				
				WordBean wbn = new WordBean(etEnglishword.getText().toString(), etJapaneseword.getText().toString());
				dbUtil.addWord(wbn);
			}
		};
	
	private DialogInterface.OnClickListener dialogNegativeButtonClickListener = new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
		}
	};
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_word, menu);
		return true;
	}

}
