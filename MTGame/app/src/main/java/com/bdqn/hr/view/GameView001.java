package com.bdqn.hr.view;

import com.bdqn.hr.activity.NewGame01;
import com.bdqn.hr.entity.Actor;
import com.bdqn.hr.entity.Npc;
import com.bdqn.hr.manager.ImgArrManager;
import com.bdqn.hr.manager.MyBitMapManager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
/**游戏画面*/
public class GameView001 extends View implements Runnable {
	private Bitmap bitmapBg = null;//背景和障碍物图
	private Bitmap bitmapWp=null;//物品(道具)图
	private Bitmap bitmapHcq=null;//缓冲区、
	private Canvas canvas=null;//临时绘制对象
	private Paint paint=null;//创建一个画笔对象
	boolean bgPaint=true;//是否重新绘制背景(一般背景障碍物等只绘制一次。)
	Actor actor=null;//玩家
	Npc npc=null;
	Bitmap mtTextImgbg=null;//按键下面的背景图片
	DrawGuaiWuImage guaiWuImage=null;//绘制全体(动)怪物
	DrawDoorImage drawDoor=null;	//绘制门
	public GameView001(Context context) {
		super(context);
	}
	public GameView001(Context context, AttributeSet attrs) {
		super(context, attrs);
		bitmapBg =MyBitMapManager.getBitmapImgall();
		mtTextImgbg=MyBitMapManager.getBitmapMtfontimg();
		bitmapWp=MyBitMapManager.getBitmapWupin();
		bitmapHcq=MyBitMapManager.getBitmapHC();
		canvas=new Canvas();
		canvas.setBitmap(bitmapHcq);
		paint=new Paint();
		canvas.drawRect(0, 0, 480, 800, paint);
		actor=Actor.getActor(context);
		npc=Npc.getNpc();
		guaiWuImage=new DrawGuaiWuImage(context);
		drawDoor=new DrawDoorImage(context);
		new Thread(this).start();
		}
	@Override
	protected void onDraw(Canvas canvas) {
		if(bgPaint){
			//绘制适应屏幕背景
			//drawImageByArr2(this.canvas, bitmapBg, ImgArrManager.syScreenbgImageArr,32,32,24);
			//绘制背景
			drawImageByArr(this.canvas, bitmapBg, ImgArrManager.bgImageArr,32,32,24);
			//绘制一个  半透明(全屏) 的黑色图 覆盖背景
			paint.setAlpha(50);
			paint.setStyle(Style.FILL);
			this.canvas.drawRect(0, 0, 480, 800, paint);
			this.canvas.drawBitmap(mtTextImgbg, 0, 608, paint);
			//绘制bg2图
			drawImageByArr(this.canvas, bitmapBg, ImgArrManager.bg2ImageArr,32,32,24);
			//绘制障碍物图
			drawImageByArr(this.canvas, bitmapBg, ImgArrManager.zawImageArr,32,32,24);
			//绘制楼梯
			drawImageByArr(this.canvas, bitmapBg, ImgArrManager.loutiImageArr, 32, 32, 24);
			bgPaint=false;
			NewGame01.keyFlag=true;
		}
		canvas.drawBitmap(bitmapHcq, 0, 0, null);
		//绘制玩家信息
		actor.drawActorInfo(canvas);
		//绘制全体怪物
		guaiWuImage.drawGuaiWu(canvas);
		//绘制门
		drawDoor.drawDoor(canvas);
		npc.drawNpc(canvas);
		//绘制玩家人物
		actor.drawActor(canvas);
		//绘制物品
		drawImageByArr(canvas, bitmapWp, ImgArrManager.wpImageArr,32,32,4);
	}
	public void drawImageByArr(Canvas canvas, Bitmap image, int[][] arrImg,int width,int height,int rowSize) {
		if(arrImg!=null){
			for (int row = 0; row < arrImg.length; row++) {
				for (int column = 0; column < arrImg[row].length; column++) {
					// 绘制图像
					int imgIndex = arrImg[row][column];
					if(imgIndex!=0){
					//获得本整形元素在图片上的行/列
					int srow= imgIndex/rowSize;
					int scolumn= imgIndex%rowSize;
					drawImg(canvas,image,column*width,row*height,scolumn*width,srow*height,width ,height);
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
		// TODO Auto-generated method stub
		while (!Thread.currentThread().isInterrupted()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
			}
			postInvalidate();
		}
	}

}
