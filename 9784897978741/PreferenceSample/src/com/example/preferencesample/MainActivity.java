package com.example.preferencesample;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String FILE_NAME = "PreferenceSampleFile";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button saveButton = (Button)findViewById(R.id.save);
		saveButton.setTag("save");
		saveButton.setOnClickListener(new ButtonClickListener());
		
		Button displayButton = (Button)findViewById(R.id.display);
		displayButton.setTag("display");
		displayButton.setOnClickListener(new ButtonClickListener());
		
		Button deleteButton = (Button)findViewById(R.id.delete);
		deleteButton.setTag("delete");
		deleteButton.setOnClickListener(new ButtonClickListener());
	}
	
	public class ButtonClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			String tag = (String)v.getTag();
			SharedPreferences preference = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
			TextView message = (TextView)findViewById(R.id.message);
			if (tag.equals("save")) {
				SharedPreferences.Editor editor = preference.edit();
				Spinner fontarray = (Spinner)findViewById(R.id.font);
				String font = (String)fontarray.getSelectedItem();
				CheckBox italic = (CheckBox)findViewById(R.id.italic);
				CheckBox bold = (CheckBox)findViewById(R.id.bold);
				String check = "通常";
				if (italic.isChecked()) {
					check = "斜体";
				}
				if (bold.isChecked()) {
					if (italic.isChecked()) {
						check += "|太字";
					} else {
						check = "太字";
					}
				}
				editor.putString("FONT", font);
				editor.putString("STYLE", check);
				editor.commit();
				
				message.setText("保存しました！");
			} else if (tag.equals("display")) {
				String font = preference.getString("FONT", "見つかりません");
				String style = preference.getString("STYLE", "見つかりません");
				
				Typeface fonttype = Typeface.DEFAULT;
				if (font.equals("明朝体")) {
					fonttype = Typeface.SERIF;
				} else if (font.equals("ゴシック体")) {
					fonttype = Typeface.SANS_SERIF;
				} else if (font.equals("等幅フォント")) {
					fonttype = Typeface.MONOSPACE;
				}
				
				int styleflag = Typeface.NORMAL;
				if (style.equals("斜体")) {
					styleflag = Typeface.ITALIC;
				} else if (style.equals("太字")) {
					styleflag = Typeface.BOLD;
				} else if (style.equals("斜体|太字")) {
					styleflag = Typeface.BOLD_ITALIC;
				}
				
				message.setText("Preference Sample\n" + "フォント:" + font + "\nスタイル:" + style);
				message.setTypeface(Typeface.create(fonttype, styleflag));
			} else if (tag.endsWith("delete")) {
				SharedPreferences.Editor editor = preference.edit();
				editor.clear();
				editor.commit();
				message.setText("削除しました！");
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
