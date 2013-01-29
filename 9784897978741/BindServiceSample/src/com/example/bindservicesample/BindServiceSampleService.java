package com.example.bindservicesample;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

public class BindServiceSampleService extends Service {
	private final RemoteCallbackList<BindActivityAIDL> callbackList
		= new RemoteCallbackList<BindActivityAIDL>();
	private Timer timer = null;
	private int countTime;
	
	public BindServiceSampleService() {
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
		timer = new Timer();
		countTime = 0;
		
		timer.schedule(task, 10000, 10000);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		if (BindServiceAIDL.class.getName().equals(intent.getAction())) {
			return serviceCallbackIf;
		}
		return null;
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		timer.cancel();
		timer.purge();
		return super.onUnbind(intent);
	}
	
	private BindServiceAIDL.Stub serviceCallbackIf = new BindServiceAIDL.Stub() {
		public void registerCallback(BindActivityAIDL callback) {
			callbackList.register(callback);
		}
		public void unregisterCallback(BindActivityAIDL callback) {
			callbackList.unregister(callback);
		}
	};
	
	private TimerTask task = new TimerTask() {
		@Override
		public void run() {
			countTime += 10;
			
			int n = callbackList.beginBroadcast();
			for (int i = 0; i < n; i++) {
				try {
					callbackList.getBroadcastItem(i).displayTime(
						countTime / 60 + "•ª" + countTime % 60 + "•bŒo‰ß‚µ‚Ü‚µ‚½I"
					);
				} catch (RemoteException e) {
					Log.e("ERROR", e.getMessage());
				}
			}
			callbackList.finishBroadcast();
		}
	};
}
