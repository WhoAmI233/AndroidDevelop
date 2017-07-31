package com.bdqn.hr.view;

import com.bdqn.hr.manager.ImgArrManager;
import com.bdqn.hr.manager.MyBitMapManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

public class DrawGuaiWuImage extends View implements Runnable {
	public static final int COLNUM=4;
	public static final int W=32;
	public static final int H=32;
	int currentIndex;
	int [][] guaiwuImageArr=null;
	Bitmap bitmap=null;
	public DrawGuaiWuImage(Context context) {
		super(context);
		bitmap=MyBitMapManager.getBitmapEnemy();
		guaiwuImageArr=ImgArrManager.gwImageArr;
		new Thread(this).start();
	}
	
	public void drawGuaiWu(Canvas canvas){
		if(guaiwuImageArr!=null){
		for (int row = 0; row < guaiwuImageArr.length; row++) {
			for (int column = 0; column < guaiwuImageArr[row].length; column++) {
				// 绘制图像
				int imgIndex = guaiwuImageArr[row][column];
				if(imgIndex!=0){
				//获得本整形元素在图片上的行/列
				int sy= imgIndex/COLNUM;
				drawImg(canvas,bitmap,column*W,row*H,currentIndex*32,sy*32,32,32);
				}
			}
		}
		}
	}
	public void drawImg(Canvas canvas,Bitmap image,int x,int y,int sx,int sy,int w ,int h){
		Rect src =new Rect();
		src.left=sx;
		src.right=sx+w;
		src.top=sy;
		src.bottom=sy+h;
		Rect dst =new Rect();
		dst.left=x;
		dst.right=x+w;
		dst.top=y;
		dst.bottom=y+h;
		canvas.drawBitmap(image, src, dst, null);
	}
	
	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()){
			try {
				Thread.sleep(200);
				currentIndex++;
				if(currentIndex==4){
					currentIndex=0;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
			}
			postInvalidate();
		}
		
	}

}
