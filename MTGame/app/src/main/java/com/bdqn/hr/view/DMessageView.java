package com.bdqn.hr.view;

import com.bdqn.hr.manager.MyBitMapManager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class DMessageView extends View {
	Bitmap bitmapBg=null;
 	public Handler handler=null;
	Paint paint=null; 
	private	String title=null;
	private String content="";
	//窗口透明度
	int alpha=0;
	//短消息播放 状态	
	public	int visiIndex=1; 
	public DMessageView(Context context) {
		super(context);
	} 

	public DMessageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		bitmapBg=MyBitMapManager.getBitmapWupinbg();
		paint=new Paint();
	} 

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas); 
		if(visiIndex==0){
			alpha+=30;
			paint.setAlpha(alpha);
			canvas.drawBitmap(bitmapBg, 0, 0, paint);
			visiIndex=alpha>220?1:0; 
			if(alpha>220){
				drawTextInfo(canvas);
				handler.removeCallbacks(runnable);
				handler.postDelayed(runnable, 1500);
			}
		}else if(visiIndex==1){
			canvas.drawBitmap(bitmapBg, 0, 0, null);
			drawTextInfo(canvas);
		}
	}
	public void drawTextInfo(Canvas canvas){
		paint.setColor(Color.LTGRAY);
		if("".equals(this.content)){
			paint.setTextSize(32);
			canvas.drawText(title, 60, 110, paint);
		}else{
			paint.setTextSize(32);
			canvas.drawText(title, 70, 80, paint);
			paint.setTextSize(24);
			canvas.drawText(content, 100, 130, paint);
		}
	}
	@Override
	protected void onVisibilityChanged(View changedView, int visibility) {
		super.onVisibilityChanged(changedView, visibility);
		if(visibility==View.VISIBLE){
			if(this.handler==null){
				return;
			}
			if(this.getVisibility()==0){
				visiIndex=0;  
			}
			this.handler.post(runnable);
			this.alpha=0;
		}else if(visibility==View.INVISIBLE){
			System.out.println("短消息窗口隐藏！");
		}
	}
	public void invisiblethis(){
		this.setVisibility(INVISIBLE);
	}
	Runnable runnable=new Runnable() {
		@Override
		public void run() {
			try{
				if(visiIndex==0){
					handler.postDelayed(runnable, 100);
				}else	if(visiIndex==1){
					invisiblethis();
					handler.removeCallbacks(runnable);
					Message message=handler.obtainMessage();
					message.arg1=5;
					handler.sendMessage(message);
					Log.i("短消息视图", "Handler已移除runnable。。。");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			postInvalidate();
		}
	};
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
