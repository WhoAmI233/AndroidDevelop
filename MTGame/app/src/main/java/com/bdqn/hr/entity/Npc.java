package com.bdqn.hr.entity;

import com.bdqn.hr.manager.ImgArrManager;
import com.bdqn.hr.manager.MyBitMapManager;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Npc implements Runnable    {
	Bitmap bitmap=null;
	static int [][] npcImageArr=null;
	int currentIndex;
	private Npc(){
		bitmap=MyBitMapManager.getBitmapNpcimg();
		new Thread(this).start();
	}
	private static Npc npc=null;
	public static  Npc getNpc() {
		if(npc==null){
			npc=new Npc();
		}
		npcImageArr=ImgArrManager.npcImageArr;
		return npc;
	}
	public void drawNpc(Canvas canvas){
		if(npcImageArr!=null){
		for (int row = 0; row < npcImageArr.length; row++) {
			for (int column = 0; column < npcImageArr[row].length; column++) {
				// 绘制图像
				int imgIndex = npcImageArr[row][column];
				if(imgIndex!=0){
				//获得本整形元素在图片上的行/列
				int sy= imgIndex/3;
				drawImg(canvas,bitmap,column*32,row*32,currentIndex*32,sy*32,32,32);
				}
			}
		}}
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
	public void drawImg(Canvas canvas,int x,int y,int sx,int sy,int w ,int h){
		Rect src =new Rect();
		src.left=sx;
		src.right=sx+32;
		src.top=sy;
		src.bottom=sy+32;
		Rect dst =new Rect();
		dst.left=x;
		dst.right=x+w;
		dst.top=y;
		dst.bottom=y+h;
		canvas.drawBitmap(bitmap, src, dst, null);
	}
	
	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()){
			try {
				Thread.sleep(300);
				currentIndex++;
				if(currentIndex==3){
					currentIndex=0;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
			}
		}
	}
	
}
