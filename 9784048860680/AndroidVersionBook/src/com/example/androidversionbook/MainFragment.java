package com.example.androidversionbook;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {
	private OnWordItemClickListener itemClickListener;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		itemClickListener = (OnWordItemClickListener)activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = (ViewGroup)inflater.inflate(R.layout.fragment_main, container);
		Button button1 = (Button)view.findViewById(R.id.button1);
		Button button2 = (Button)view.findViewById(R.id.button2);
		Button button3 = (Button)view.findViewById(R.id.button3);
		
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDetail(0);
			}
		});
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDetail(1);
			}
		});
		button3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDetail(2);
			}
		});
		
		return view;
	}

	private void showDetail(int id) {
		itemClickListener.onButtonItemClick(id);
	}
	
	public interface OnWordItemClickListener {
		void onButtonItemClick(int id);
	}
}