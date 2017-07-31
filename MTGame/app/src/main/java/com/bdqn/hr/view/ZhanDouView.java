package com.bdqn.hr.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.bdqn.hr.entity.Actor;
import com.bdqn.hr.entity.GuaiWu;
import com.bdqn.hr.manager.GuaiWuManager;
import com.bdqn.hr.manager.MyBitMapManager;
import com.bdqn.hr.manager.SoundManager;

public class ZhanDouView  extends View {
	Actor actor=null;
    public	GuaiWu guaiWu=null;
	Paint paint=null;
	//ս�����ڱ���
	Bitmap bitmapBg=null;
	//ս��ʤ����ʧ�ܣ�ͼ��
	Bitmap winBitmap=null;
	Bitmap shibaiBitmap=null;
	public Handler handler=null;
//	Canvas canvas=null;
	//����͸����
	int alpha=0;
	//ս��״̬  0 ����ս������  1 ��ʼս��  20 ����ս����ͼ������ʤ��������ڣ�21 ����ʤ����� 
	// 30 ����ս����ͼ������ʧ�ܽ������  31����ʧ�ܽ��   4 ��Handler���Ƴ���ս��Run
	public	int visiIndex=4;
	//��¼���ս��ǰѪ��
	int actorHp=0;
	//����ս�������Ϊtrue��ҹ�����false�����﹥��
	boolean zhandousx=true;
	//����
	private ZhanDouView(Context context) {
		super(context);
	}
	public ZhanDouView(Context context, AttributeSet attrs) {
		super(context, attrs);
		actor=Actor.getActor(context);
		paint=new Paint();
		bitmapBg=MyBitMapManager.getBitmapZhandoubg();
		winBitmap=MyBitMapManager.getBitmapWinimg();
		shibaiBitmap=MyBitMapManager.getBitmapShibaiimg();
	}
	 
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas); 
		if(visiIndex==0){
			alpha+=30;
			paint.setAlpha(alpha);
			canvas.drawBitmap(bitmapBg, 0, 0, paint);
			visiIndex=alpha>200?1:0; 
			if(alpha>200){
				drawActorAndGuaiWuInfo(canvas);
				handler.removeCallbacks(runnable);
				handler.postDelayed(runnable, 500);
			}
		}else if(visiIndex==1){
			canvas.drawBitmap(bitmapBg, 0, 0, null);
			drawActorAndGuaiWuInfo(canvas);
		}else if(visiIndex==20){
			//����ս����ͼ������ʤ���������
			alpha-=30;
			paint.setAlpha(alpha);
			canvas.drawBitmap(bitmapBg, 0, 0, paint);
			paint.setAlpha(255-alpha);
			canvas.drawBitmap(winBitmap, 0, 50, paint);
			if(alpha<120){
				drawWinInfo(canvas);
			}
			if(alpha<30){
				handler.removeCallbacks(runnable);
				visiIndex=21;
				handler.postDelayed(runnable, 1500);
			}
		}else  if(visiIndex==21){
			canvas.drawBitmap(winBitmap, 0, 50, null);
			drawWinInfo(canvas);
		}else if(visiIndex==30){
			//����ս����ͼ������ʧ�ܽ������
			alpha-=30;
			paint.setAlpha(alpha);
			canvas.drawBitmap(bitmapBg, 0, 0, paint);
			paint.setAlpha(255-alpha);
			canvas.drawBitmap(shibaiBitmap, 0, 50, paint);
			if(alpha<30){
				handler.removeCallbacks(runnable);
				visiIndex=31;
				handler.postDelayed(runnable, 2500);
			}
		}else if(visiIndex==31){
			canvas.drawBitmap(shibaiBitmap, 0, 50, null);
		}
	}
	public void drawWinInfo(Canvas canvas){
		paint.setTextSize(28);
		canvas.drawText(guaiWu.getMoney()+"",322 , 125, paint);
		canvas.drawText(guaiWu.getExp()+"",322 , 195, paint);
	}
	public void drawActorAndGuaiWuInfo(Canvas canvas){
		//����ͷ��
		actor.drawActor(canvas, 350, 80, 64,64 );
		guaiWu.drawGuaiWu(canvas, 60, 75, 64, 64);
		paint.setColor(Color.WHITE);
		paint.setTextSize(16);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		//����Ѫ��
		canvas.drawText(actor.getHp()+"",265 , 90, paint);
		canvas.drawText(guaiWu.getHp()+"",170 , 90, paint);
		//���ƹ���ֵ
		canvas.drawText(actor.getGjValue()+"",265 , 115, paint);
		canvas.drawText(guaiWu.getGjValue()+"",170 , 115, paint);
		//���Ʒ���ֵ
		canvas.drawText(actor.getFyValue()+"",265 , 140, paint);
		canvas.drawText(guaiWu.getFyValue()+"",170 , 140, paint);
		paint.setTypeface(Typeface.SANS_SERIF);
		paint.setTextSize(28);
		//��������
		canvas.drawText(actor.getName(),350 , 200, paint);
		canvas.drawText(guaiWu.getName(),50 , 200, paint);
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
			this.actorHp=actor.getHp();
		}else if(visibility==View.INVISIBLE){
			System.out.println("ս���������أ�");
			visiIndex=4;
		}
	}
	//�򵥵����˵��۵�ս��
	public void zhandou(){
		int actorHp=actor.getHp();
		int guwuHp=guaiWu.getHp();
		
		if(this.zhandousx){
			int actorSh=actor.getGjValue()-guaiWu.getFyValue();
			if(actorSh>0){
				if(guwuHp-actorSh<=0){
					//Ӯ��
					visiIndex=20;
					alpha=270;
					return;
				}
				guaiWu.setHp(guwuHp-actorSh);
			}
		//	SoundManager.getMediaPlayerYinXiao(1).start();
			this.zhandousx=false;
		}else{
			int guaiwuSh=guaiWu.getGjValue()-actor.getFyValue();
			if(guaiwuSh>0){
				if(actorHp-guaiwuSh<=0){
					//����
					visiIndex=30;
					return;
				}
				actor.setHp(actorHp-guaiwuSh);
			}
			this.zhandousx=true;
		//	SoundManager.getMediaPlayerYinXiao(2).start();
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
				}else if(visiIndex==1){
					zhandou();
					handler.postDelayed(runnable, 500);
				}else if(visiIndex==20){
					handler.postDelayed(runnable, 200);
				}else if(visiIndex==21){
					handler.postDelayed(runnable, 500);
					//����ʤ����� 	���ӽ�ң����� ����ʤ��������͸� activity
					actor.setMoney(actor.getMoney()+guaiWu.getMoney());
					actor.setExp(actor.getExp()+guaiWu.getExp());
					Message message=handler.obtainMessage();
					message.arg1=1;
					handler.sendMessage(message);
					System.out.println("��ս��ʤ���Ľ�����͸� activity   ........");
					invisiblethis(); 
				}else if(visiIndex==30){
					handler.postDelayed(runnable, 200);
				}else if(visiIndex==31){
					handler.postDelayed(runnable, 500);
					//����ʧ�ܽ�� ����10%��Һ;��� ����ʧ�ܽ�����͸� activity
					actor.setHp(actorHp);
					actor.setMoney(actor.getMoney()-actor.getMoney()/10);
					actor.setExp(actor.getExp()-actor.getExp()/10);
					Message message=handler.obtainMessage();
					message.arg1=2;
					handler.sendMessage(message);
					System.out.println("��ս��ʧ�ܵĽ�����͸� activity   ........");
					invisiblethis();
				}else	if(visiIndex==4){
					handler.removeCallbacks(runnable);
					Log.i("ս����ͼ", "Handler���Ƴ�runnable������");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			postInvalidate();
		}
	};
}
