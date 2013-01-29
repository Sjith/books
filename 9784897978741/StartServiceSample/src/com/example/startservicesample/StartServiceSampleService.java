package com.example.startservicesample;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

public class StartServiceSampleService extends Service {
	private Timer timer = null;
	private int countTime;
	private int stopTime;
	
	public StartServiceSampleService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		Toast.makeText(this, "サービスを起動します", Toast.LENGTH_SHORT).show();
		
		timer = new Timer();
		countTime = 0;
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		
		Toast.makeText(this, "サービスを開始します。", Toast.LENGTH_SHORT).show();
		
		timer.schedule(task, 10000, 10000);
		
		Bundle bundle = intent.getExtras();
		stopTime = Integer.parseInt(bundle.getString("STOPTIME"));
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		Toast.makeText(this, "サービスを終了します。", Toast.LENGTH_SHORT).show();
		
		timer.cancel();
		timer.purge();
	}
	
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			Toast.makeText(StartServiceSampleService.this, (String)msg.obj, Toast.LENGTH_SHORT).show();
		}
	};
	
	private TimerTask task = new TimerTask() {
		@Override
		public void run() {
			countTime += 10;
			
			if (countTime / 60 == stopTime) {
				stopSelf();
			} else {
				handler.sendMessage(
					Message.obtain(
						handler,
						0,
						countTime / 60 + "分" + countTime % 60 + "秒経過しました！"
					)
				);
			}
		}
	};
}
