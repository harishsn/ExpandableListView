package com.example.CustomExpandableListView;



import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class BaseActivity extends ActionBarActivity {
	
	public static int Color = 0xff3F9FE0;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		Drawable colorDrawable = new ColorDrawable(Color);
		Drawable bottomDrawable = getResources().getDrawable(R.drawable.actionbar_bottom);
		LayerDrawable ld = new LayerDrawable(new Drawable[] { colorDrawable, bottomDrawable });
		
		
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
			getActionBar().setDisplayUseLogoEnabled(false);
			getActionBar().setBackgroundDrawable(ld);
			
		}else{
			getSupportActionBar().setDisplayUseLogoEnabled(false);
			getSupportActionBar().setBackgroundDrawable(ld);
			
		}
	}
		
		
	}

