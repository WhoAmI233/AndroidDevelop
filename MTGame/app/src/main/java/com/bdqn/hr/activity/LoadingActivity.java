package com.bdqn.hr.activity;

import com.bdqn.hr.view.LoadingView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
/**加载中界面*/
public class LoadingActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        LoadingView.handler=handler;
        setContentView(R.layout.loadingv);
        {		//此处为适应屏幕
	        ViewGroup vg=(ViewGroup)findViewById(R.id.parentloading);
	        int cw=LoginActivity.width-480;
	        int ch=LoginActivity.height-800;
	        vg.setPadding(cw/2, ch/2,cw/2, 0);
	        Log.d("LoadingView", "自适应屏幕。");
        }
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("loading activity  destroy.........."); 
	}

	public void finishThis(){
		Intent intent=new Intent();
		intent.setClass(this, NewGame01.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		//this.finish();
	}
	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(msg.arg1==88){
				finishThis();
			}
		}
	};
}
