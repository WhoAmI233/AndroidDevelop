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
	//战斗窗口背景
	Bitmap bitmapBg=null;
	//战斗胜利，失败，图像
	Bitmap winBitmap=null;
	Bitmap shibaiBitmap=null;
	public Handler handler=null;
//	Canvas canvas=null;
	//窗口透明度
	int alpha=0;
	//战斗状态  0 出现战斗窗口  1 开始战斗  20 渐隐战斗绘图，渐出胜利结果窗口，21 处理胜利结果 
	// 30 渐隐战斗绘图，渐出失败结果窗口  31处理失败结果   4 从Handler中移除此战斗Run
	public	int visiIndex=4;
	//记录玩家战斗前血量
	int actorHp=0;
	//用于战斗，如果为true玩家攻击，false，怪物攻击
	boolean zhandousx=true;
	//单例
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
			//渐隐战斗绘图，渐出胜利结果窗口
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
			//渐隐战斗绘图，渐出失败结果窗口
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
		//绘制头像
		actor.drawActor(canvas, 350, 80, 64,64 );
		guaiWu.drawGuaiWu(canvas, 60, 75, 64, 64);
		paint.setColor(Color.WHITE);
		paint.setTextSize(16);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		//绘制血量
		canvas.drawText(actor.getHp()+"",265 , 90, paint);
		canvas.drawText(guaiWu.getHp()+"",170 , 90, paint);
		//绘制攻击值
		canvas.drawText(actor.getGjValue()+"",265 , 115, paint);
		canvas.drawText(guaiWu.getGjValue()+"",170 , 115, paint);
		//绘制防御值
		canvas.drawText(actor.getFyValue()+"",265 , 140, paint);
		canvas.drawText(guaiWu.getFyValue()+"",170 , 140, paint);
		paint.setTypeface(Typeface.SANS_SERIF);
		paint.setTextSize(28);
		//绘制名字
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
			System.out.println("战斗窗口隐藏！");
			visiIndex=4;
		}
	}
	//简单的让人蛋疼的战斗
	public void zhandou(){
		int actorHp=actor.getHp();
		int guwuHp=guaiWu.getHp();
		
		if(this.zhandousx){
			int actorSh=actor.getGjValue()-guaiWu.getFyValue();
			if(actorSh>0){
				if(guwuHp-actorSh<=0){
					//赢了
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
					//输了
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
					//处理胜利结果 	增加金币，经验 并将胜利结果发送给 activity
					actor.setMoney(actor.getMoney()+guaiWu.getMoney());
					actor.setExp(actor.getExp()+guaiWu.getExp());
					Message message=handler.obtainMessage();
					message.arg1=1;
					handler.sendMessage(message);
					System.out.println("将战斗胜利的结果发送给 activity   ........");
					invisiblethis(); 
				}else if(visiIndex==30){
					handler.postDelayed(runnable, 200);
				}else if(visiIndex==31){
					handler.postDelayed(runnable, 500);
					//处理失败结果 减少10%金币和经验 并将失败结果发送给 activity
					actor.setHp(actorHp);
					actor.setMoney(actor.getMoney()-actor.getMoney()/10);
					actor.setExp(actor.getExp()-actor.getExp()/10);
					Message message=handler.obtainMessage();
					message.arg1=2;
					handler.sendMessage(message);
					System.out.println("将战斗失败的结果发送给 activity   ........");
					invisiblethis();
				}else	if(visiIndex==4){
					handler.removeCallbacks(runnable);
					Log.i("战斗视图", "Handler已移除runnable。。。");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			postInvalidate();
		}
	};
}
