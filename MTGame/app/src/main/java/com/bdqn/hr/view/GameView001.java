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
/**��Ϸ����*/
public class GameView001 extends View implements Runnable {
	private Bitmap bitmapBg = null;//�������ϰ���ͼ
	private Bitmap bitmapWp=null;//��Ʒ(����)ͼ
	private Bitmap bitmapHcq=null;//��������
	private Canvas canvas=null;//��ʱ���ƶ���
	private Paint paint=null;//����һ�����ʶ���
	boolean bgPaint=true;//�Ƿ����»��Ʊ���(һ�㱳���ϰ����ֻ����һ�Ρ�)
	Actor actor=null;//���
	Npc npc=null;
	Bitmap mtTextImgbg=null;//��������ı���ͼƬ
	DrawGuaiWuImage guaiWuImage=null;//����ȫ��(��)����
	DrawDoorImage drawDoor=null;	//������
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
			//������Ӧ��Ļ����
			//drawImageByArr2(this.canvas, bitmapBg, ImgArrManager.syScreenbgImageArr,32,32,24);
			//���Ʊ���
			drawImageByArr(this.canvas, bitmapBg, ImgArrManager.bgImageArr,32,32,24);
			//����һ��  ��͸��(ȫ��) �ĺ�ɫͼ ���Ǳ���
			paint.setAlpha(50);
			paint.setStyle(Style.FILL);
			this.canvas.drawRect(0, 0, 480, 800, paint);
			this.canvas.drawBitmap(mtTextImgbg, 0, 608, paint);
			//����bg2ͼ
			drawImageByArr(this.canvas, bitmapBg, ImgArrManager.bg2ImageArr,32,32,24);
			//�����ϰ���ͼ
			drawImageByArr(this.canvas, bitmapBg, ImgArrManager.zawImageArr,32,32,24);
			//����¥��
			drawImageByArr(this.canvas, bitmapBg, ImgArrManager.loutiImageArr, 32, 32, 24);
			bgPaint=false;
			NewGame01.keyFlag=true;
		}
		canvas.drawBitmap(bitmapHcq, 0, 0, null);
		//���������Ϣ
		actor.drawActorInfo(canvas);
		//����ȫ�����
		guaiWuImage.drawGuaiWu(canvas);
		//������
		drawDoor.drawDoor(canvas);
		npc.drawNpc(canvas);
		//�����������
		actor.drawActor(canvas);
		//������Ʒ
		drawImageByArr(canvas, bitmapWp, ImgArrManager.wpImageArr,32,32,4);
	}
	public void drawImageByArr(Canvas canvas, Bitmap image, int[][] arrImg,int width,int height,int rowSize) {
		if(arrImg!=null){
			for (int row = 0; row < arrImg.length; row++) {
				for (int column = 0; column < arrImg[row].length; column++) {
					// ����ͼ��
					int imgIndex = arrImg[row][column];
					if(imgIndex!=0){
					//��ñ�����Ԫ����ͼƬ�ϵ���/��
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
