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
/**���ڽ���*/
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
        Toast.makeText(this, "�����ֻ��ֱ���Ϊ��"+width+"��"+height, Toast.LENGTH_LONG).show();
        this.setContentView(new GameViewGY(this));
	}
	
}
