package com.bdqn.hr.view;

import com.bdqn.hr.activity.LoginActivity;
import com.bdqn.hr.manager.ImgArrManager;
import com.bdqn.hr.manager.MyBitMapManager;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/** 为适应屏幕 */
public class DrawableGameScreenBg {
	Bitmap bitmap=null;
	Canvas canvas=null;
	Bitmap bitmapBg=null;
	static boolean b=true;
	public DrawableGameScreenBg(){
		bitmap=MyBitMapManager.getBitmapImgall();
		bitmapBg=MyBitMapManager.getBitmapHC2();
		canvas=new Canvas();
		canvas.setBitmap(bitmapBg);
		drawImageByArr2(canvas, bitmap, ImgArrManager.syScreenbgImageArr,32,32,24);
	}
	public Drawable getDrawable(){
		BitmapDrawable bd = new BitmapDrawable(bitmapBg);
		return bd;
	}
	//绘制适应屏幕背景
	public void drawImageByArr2(Canvas canvas, Bitmap image, int[][] arrImg,int width,int height,int rowSize) {
		for (int row = 0; row < arrImg.length; row++) {
			for (int column = 0; column < arrImg[row].length; column++) {
				// 绘制图像
				int imgIndex = arrImg[row][column];
				//System.out.println((LoginActivity.width-800)/2);
				if(imgIndex!=0&&row*32<(32*18+(LoginActivity.height-800)/2)+32){
					//获得本整形元素在图片上的行/列
					int h=0;
					if(row*32>=(32*18+(LoginActivity.height-800)/2)){
						h=(LoginActivity.height-800)/2%32;
					}else{
						h=height;
					}
					int srow= imgIndex/rowSize;
					int scolumn= imgIndex%rowSize;
					drawImg(canvas,image,column*width,row*height,scolumn*width,srow*height,width ,h);
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
}
