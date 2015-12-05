package com.namleesin.smartalert.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.namleesin.smartalert.R;
import com.namleesin.smartalert.utils.PreferenceMgr;

public class MainActivity extends Activity implements DrawerListener, OnClickListener
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
		initView();
		OpenActivity.startSplashScreenActivity(this);
		
		Button btn = (Button)this.findViewById(R.id.graph);
		btn.setOnClickListener(this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		
		switch(requestCode)
		{
			case MainValue.RES_SPLASH_SCREEN:				
				startActivity();
				break;
			case MainValue.RES_GUIDE_WIZARD:
				new PreferenceMgr(this).setIntValue(PFValue.PRE_INIT_STATE, PFValue.PRE_INIT_GUIDE_OK);				
				startActivity();
				break;
			case MainValue.RES_ALERT_SETTING:
				new PreferenceMgr(this).setIntValue(PFValue.PRE_INIT_STATE, PFValue.PRE_INIT_ALERT_OK);
				startActivity();
				break;
			case MainValue.RES_SPAM_SETTING:
				new PreferenceMgr(this).setIntValue(PFValue.PRE_INIT_STATE, PFValue.PRE_INIT_SUCCESS);
				//OpenActivity.startLikeNotiSettingActivity(this);				
				break;
			case MainValue.RES_LIKE_SETTING:
				//OpenActivity.startGraphActivity(this);				
				break;
			default:
				break;
		}
	}

	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
			case R.id.graph:
				OpenActivity.startGraphActivity(this);
				break;
			default:
				break;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		int id = item.getItemId();
		if (id == R.id.action_settings) 
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onDrawerClosed(View arg0) 
	{
		;
	}

	@Override
	public void onDrawerOpened(View arg0) 
	{
		;
	}

	@Override
	public void onDrawerSlide(View arg0, float arg1) 
	{
		;
	}

	@Override
	public void onDrawerStateChanged(int arg0) 
	{
		;
	}
	
	private void initView()
	{
		ListView menu = (ListView) findViewById(R.id.menu_list);
		
		String[] menu_array = getResources().getStringArray(R.array.menu_str);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu_array);
		menu.setAdapter(adapter);
	}
	
	private void startActivity()
	{
		int initstate = new PreferenceMgr(this).getIntValue(PFValue.PRE_INIT_STATE, PFValue.PRE_INIT_START);
		
		switch(initstate)
		{
			case PFValue.PRE_INIT_START:
				OpenActivity.startGuideDes01Activity(this);
				break;
			case PFValue.PRE_INIT_GUIDE_OK:
				OpenActivity.startAlertSettingActivity(this);
				break;
			case PFValue.PRE_INIT_ALERT_OK:
				OpenActivity.startSpamSettingActivity(this);
				break;
			case PFValue.PRE_INIT_SETTING_OK:
				break;
			case PFValue.PRE_INIT_SUCCESS:
				break;
			default:
				break;
		}		
	}
}
