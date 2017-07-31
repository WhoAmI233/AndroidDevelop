package com.bdqn.hr.activity;
//javaapk.com
import com.bdqn.hr.view.GameViewGY;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
/**关于界面*/
public class GameGY extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        int width=getWindowManager().getDefaultDisplay().getWidth();
        int height=getWindowManager().getDefaultDisplay().getHeight();
        Toast.makeText(this, "您的手机分辨率为："+width+"×"+height, Toast.LENGTH_LONG).show();
        this.setContentView(new GameViewGY(this));
	}
	
}
