package com.bdqn.hr.view;

import com.bdqn.hr.entity.Door;
import com.bdqn.hr.manager.ImgArrManager;
import com.bdqn.hr.manager.MyBitMapManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.provider.OpenableColumns;
import android.view.View;

public class DrawDoorImage extends View{
	public static final int COLNUM=4;
	public static final int W=32;
	public static final int H=32;
	int [][] menImageArr=null;
	Bitmap bitmap=null;
	Door door=null;
	public DrawDoorImage(Context context) {
		super(context);
		bitmap=MyBitMapManager.getBitmapDoor();
		menImageArr=ImgArrManager.menImageArr;
		door=Door.getDoor(context);
	}
	
	public void drawDoor(Canvas canvas){
		if(menImageArr!=null){
		for (int row = 0; row < menImageArr.length; row++) {
			for (int column = 0; column < menImageArr[row].length; column++) {
				// 绘制图像
				int imgIndex = menImageArr[row][column];
				if(imgIndex!=0){
					//获得本整形元素在图片上的行/列
					int sy= imgIndex/COLNUM;
					drawImg(canvas,bitmap,column*W,row*H,0*32,sy*32,32,32);
				}
				//玩家遇到门，则打开门
				if(door.checkDraw){
					if(door.getDoorRow()==row&&door.getDoorCol()==column){
						drawImg(canvas,bitmap,column*W,row*H,door.getCurrdz()*32,door.getKey()*32,32,32);
					}
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

}
