package com.progressbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.progressbar.f1.ShowWebPageActivity;


public class Fragment1 extends Fragment implements View.OnClickListener{

	private LinearLayout btn1;
	private LinearLayout btn2;
	private LinearLayout btn3;
	private LinearLayout btn4;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=View.inflate(this.getActivity(), R.layout.f1, null);
		setView(v);
		return v;
	}


	private void setView(View v) {
		btn1 = (LinearLayout)v.findViewById(R.id.ll_btn1);
		btn2 = (LinearLayout)v.findViewById(R.id.ll_btn2);
		btn3 = (LinearLayout)v.findViewById(R.id.ll_btn3);
		btn4 = (LinearLayout)v.findViewById(R.id.ll_btn4);
	}



	
	
	public void doOnClick(View v) {
		Intent in=new Intent(getActivity(),ShowWebPageActivity.class);
		switch (v.getId()) {
			case R.id.ll_btn1:
				in.putExtra("url", "http://m.hao123.com/");
				break;
			case R.id.ll_btn2:
				in.putExtra("url", "http://m.hao123.com/");
				break;
			case R.id.ll_btn3:
				in.putExtra("url", "http://m.hao123.com/");
				break;
			case R.id.ll_btn4:
				in.putExtra("url", "http://m.hao123.com/");
				break;
		}
		startActivity(in);
	}
	
	
	
	//------------OnClickListener-------------------------------

	@Override
	public void onClick(View v) {
		
	}
	
	
	
}



