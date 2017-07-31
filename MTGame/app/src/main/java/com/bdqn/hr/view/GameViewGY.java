package com.bdqn.hr.view;

import com.bdqn.hr.activity.GameGY;
import com.bdqn.hr.activity.LoginActivity;
import com.bdqn.hr.manager.MyBitMapManager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;

public class GameViewGY extends View implements Runnable {
	private Paint mPaint=null;
	private Bitmap bitmap=null;
	Matrix matrix=new Matrix();
	public GameViewGY(Context context) {
		super(context);
		mPaint=new Paint();
		bitmap=MyBitMapManager.getBitmapMyoto();
		new Thread(this).start();
		x=LoginActivity.width-bitmap.getWidth()-150;
		y=(LoginActivity.height-bitmap.getHeight())/2;
	}
	int x=0,y=0;
	float angle=0.0f;
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.BLUE);
//		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStyle(Paint.Style.FILL);
		canvas.drawRect(20, 20, 150,150, mPaint);
		mPaint.setColor(Color.YELLOW);
		canvas.drawLine(30, 200, 220, 230, mPaint);
		mPaint.setColor(Color.GREEN);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeWidth(5);
		canvas.drawCircle(100, 300, 60, mPaint);
		
		matrix.reset();
		angle= angle>=360?0:angle;
		
		matrix.setRotate(angle);
		Bitmap bitmap2=Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
		canvas.drawBitmap(bitmap2, x, y, null);
		bitmap2=null;
		
	}
	boolean b=true,b2=true;
	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()){
			try {
				Thread.sleep(100);
				if(x>LoginActivity.width-bitmap.getWidth()-160){
					b=true;
				}
				if(x<100){
					b=false;
				}
				x+= b?-5:5;
				if(y>LoginActivity.height-bitmap.getHeight()-150){
					b2=true;
				}
				if(y<100){
					b2=false;
				}
				y+= b2?-5:5;
				angle+=10;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
			}
			postInvalidate();
		}

	}

	public void setmPaint(Paint mPaint) {
		this.mPaint = mPaint;
	}

	public Paint getmPaint() {
		return mPaint;
	}

}
