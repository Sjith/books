package com.example.helloandroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(
        	new OnClickListener() {
        		public void onClick(View v) {
        			Log.i("Hello", "トースト表示前です。");
        			Toast.makeText(getApplicationContext(), "ボタンが押されました。", Toast.LENGTH_SHORT).show();
        			Log.i("Hello", "トースト表示後です。");
        		}
        	}
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
