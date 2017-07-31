package com.bdqn.hr.activity;

import com.bdqn.hr.manager.MyBitMapManager;
import com.bdqn.hr.manager.SoundManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**菜单界面*/
public class LoginActivity extends Activity {
    /** Called when the activity is first created. */ 
	public static int width;
	public static int height;
	TextView tvNewGame=null;
	TextView tvHelp=null;
	TextView tvgy=null;
	TextView tvClose=null;
	ImageView iv=null;
	MediaPlayer mediaPlayer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.amain);
        tvNewGame=(TextView)findViewById(R.id.tvNewGame);
        tvHelp=(TextView)findViewById(R.id.tvHelp);
        tvgy=(TextView)findViewById(R.id.tvGY); 
        tvClose=(TextView)findViewById(R.id.tvClose);
        LoginBiz loginbiz= new  LoginBiz();
        tvNewGame.setOnClickListener(loginbiz);
        tvHelp.setOnClickListener(loginbiz);
        tvgy.setOnClickListener(loginbiz); 
        tvClose.setOnClickListener(loginbiz);
        new SoundManager(this);
        mediaPlayer = SoundManager.getMediaPlayerBg();
		if(mediaPlayer.isPlaying()){
			tvHelp.setText("音乐:OFF");
			return;
		}
        {		//此处为适应屏幕
	        width=getWindowManager().getDefaultDisplay().getWidth();
	        height=getWindowManager().getDefaultDisplay().getHeight();
	        ViewGroup vg=(ViewGroup)findViewById(R.id.parentAbs);
	        int cw=width-480;
	        int ch=height-800;
	        vg.setPadding(cw/2, ch/2,cw/2, 0);
	        Log.d("LoginActivity", "自适应屏幕。");
        }
        new MyBitMapManager(this).initBitMap();
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("login activity    destroy............");
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if(keyCode==KeyEvent.KEYCODE_BACK){
			System.exit(0);
		}
		return false;
	}
	class LoginBiz implements OnClickListener  {
    	@Override
    	public void onClick(View v) {
    		if(v==tvNewGame){
    			newGame();
    		}else if(v==tvHelp){
    			helpGame();
    		}else if(v==tvgy){
    			gyGame();
    		}else if(v==tvClose){
    			closeGame();
    		}
    	}
    	public void newGame(){
    		Intent intent=new Intent();
    		intent.setClass(LoginActivity.this, LoadingActivity.class);
    		startActivity(intent);
    	}
    	public void helpGame(){
    		if(mediaPlayer.isPlaying()){
    			mediaPlayer.pause();
    			tvHelp.setText("音乐:ON");
    			return;
    		}
    		tvHelp.setText("音乐:OFF");
    		mediaPlayer.setLooping(true);	//循环播放
    		mediaPlayer.start();
    	}
    	public void gyGame(){
    		Intent intent=new Intent();
    		intent.setClass(LoginActivity.this, GameGY.class);
    		startActivity(intent);
    	}
    	public void closeGame(){
    		System.exit(0);
    	}
    }
}