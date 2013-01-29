package com.example.englishwordbooksample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	static final String BTN_CHECK_WORD = "btnCheckWord";
	static final String BTN_ADD_WORD = "btnAddWord";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button btnCheckWord = (Button)findViewById(R.id.btn_checkword);
        btnCheckWord.setTag(BTN_CHECK_WORD);
        btnCheckWord.setOnClickListener(buttonClickListener);
        
        Button btnAddWord = (Button)findViewById(R.id.btn_addword);
        btnAddWord.setTag(BTN_ADD_WORD);
        btnAddWord.setOnClickListener(buttonClickListener);
    }
    
    private OnClickListener buttonClickListener = new OnClickListener() {
    	@Override
    	public void onClick(View v) {
    		Button button = (Button)v;
    		if (button.getTag().equals(BTN_CHECK_WORD)) {
    			Intent intent = new Intent(MainActivity.this, CheckWordActivity.class);
    			startActivity(intent);
    		} else if (button.getTag().equals(BTN_ADD_WORD)) {
    			Intent intent = new Intent(MainActivity.this, AddWordActivity.class);
    			startActivity(intent);
    		}
    	}
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
