package com.example.filesample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private final static String FILE_NAME = "FileSampleFile";

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
			String str = "";
			TextView label = (TextView)findViewById(R.id.message);
			if (tag.equals("save")) {
				EditText name = (EditText)findViewById(R.id.name);
				EditText score = (EditText)findViewById(R.id.score);
				
				try {
					FileOutputStream stream = openFileOutput(FILE_NAME, MODE_APPEND);
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(stream));
					String hantei = "不合格";
					if (Integer.parseInt(score.getText().toString()) >= 210) {
						hantei = "合格";
					}
					out.write(name.getText().toString() + "," + score.getText().toString() + "," + hantei);
					out.newLine();
					out.close();
					str = "保存しました！";
				} catch (Exception e) {
					str = "データ保存に失敗しました！";
				}
			} else if (tag.equals("display")) {
				try {
					FileInputStream stream = openFileInput(FILE_NAME);
					BufferedReader in = new BufferedReader(new InputStreamReader(stream));
					String line = "";
					while ((line = in.readLine()) != null) {
						str += line + "\n";
					}
					in.close();
				} catch (Exception e) {
					str = "データ取得に失敗しました！";
				}
			} else if (tag.equals("delete")) {
				try {
					deleteFile(FILE_NAME);
					str = "削除しました！";
				} catch (Exception e) {
					str = "データ削除に失敗しました！";
				}
			}
			label.setText(str);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
