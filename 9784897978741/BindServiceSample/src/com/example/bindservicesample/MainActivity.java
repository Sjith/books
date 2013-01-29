package com.example.bindservicesample;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private BindServiceAIDL bindserviceIf = null;
	private ServiceConnection conn = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button startButton = (Button)findViewById(R.id.startButton);
		startButton.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					Toast.makeText(MainActivity.this, "サービスを開始します", Toast.LENGTH_SHORT).show();
					
					// インテント生成
					Intent intent = new Intent(BindServiceAIDL.class.getName());
					
					// Service接続・切断用オブジェクト生成
					conn = new SampleServiceConnection();
					
					// サービスにバインド
					bindService(intent, conn, BIND_AUTO_CREATE);
				}
			}
		);
		
		Button stopButton = (Button)findViewById(R.id.stopButton);
		stopButton.setOnClickListener(
			new OnClickListener() {
				@Override
				public void onClick(View v) {
					try {
						bindserviceIf.unregisterCallback(bindactivityIf);
					} catch (RemoteException e) {
						Log.e("ERROR", e.getMessage());
					}
					
					Toast.makeText(MainActivity.this, "サービスを終了します。", Toast.LENGTH_SHORT).show();
					
					unbindService(conn);
					Intent intent = new Intent(BindServiceAIDL.class.getName());
					stopService(intent);
				}
			}
		);
	}
	
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			Toast.makeText(MainActivity.this, (String)msg.obj, Toast.LENGTH_SHORT).show();
		}
	};
	
	private BindActivityAIDL bindactivityIf = new BindActivityAIDL.Stub() {
		@Override
		public void displayTime(String msg) throws RemoteException {
			handler.sendMessage(Message.obtain(handler, 0, msg));
		}
	};

	class SampleServiceConnection implements ServiceConnection {
		public void onServiceConnected(ComponentName compName, IBinder binder) {
			bindserviceIf = BindServiceAIDL.Stub.asInterface(binder);
			try {
				bindserviceIf.registerCallback(bindactivityIf);
			} catch (RemoteException e) {
				Log.e("ERROR", e.getMessage());
			}
		}
		
		public void onServiceDisconnected(ComponentName arg0) {
			bindserviceIf = null;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
