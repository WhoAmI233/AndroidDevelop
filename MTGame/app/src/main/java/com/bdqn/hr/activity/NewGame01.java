package com.bdqn.hr.activity;

import com.bdqn.hr.entity.Actor;
import com.bdqn.hr.entity.Door;
import com.bdqn.hr.entity.GuaiWu;
import com.bdqn.hr.entity.WuPin;
import com.bdqn.hr.manager.GuaiWuManager;
import com.bdqn.hr.manager.ImgArrManager;
import com.bdqn.hr.manager.RenWuManager;
import com.bdqn.hr.manager.WuPinManager;
import com.bdqn.hr.view.DMessageView;
import com.bdqn.hr.view.DrawableGameScreenBg;
import com.bdqn.hr.view.ShopAndNpcSayView;
import com.bdqn.hr.view.TiaoCengAndGWInfoView;
import com.bdqn.hr.view.ZhanDouView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.Button;

/**游戏界面*/
public class NewGame01 extends Activity {
	Button btnTop=null;
	Button btnDown=null;
	Button btnLeft=null;
	Button btnRight=null;
	Button btnMove=null;
	Button btntiaoceng=null;
	Button btnGuaiWuInfo=null;
	View absLayout=null;
	ZhanDouView zhandouView=null;//战斗视图
	DMessageView dMessageView=null;
	ShopAndNpcSayView shopandnpc=null;
	TiaoCengAndGWInfoView tcandgwinfo=null;
	Actor actor=null;//玩家
	GuaiWu guaiWu=null;//遇到的怪物
	WuPin wuPin=null;//遇到的物品
	Door door=null; //遇到的 门
	int amx=0,amy=0;//根据按键人物(用于)移动范围
	Thread runMove=null;//移动线程
	int ydIndex=0,indexRow=0,indexColumn=0;//遇到NPC或GuaiWu (结果，二维数组下标) 
	public static boolean keyFlag=false; //按键是否有效 (是否战斗中)			初始化时无效，背景绘制完后有效。
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		actor=Actor.getActor(this);
        //得到 本层信息
        ImgArrManager.getZAWInfo(actor.getMtceng());
        //获得玩家位置
        actor.setX(ImgArrManager.acX);
        actor.setY(ImgArrManager.acY);
		setContentView(R.layout.bnewgameone);
		{		//此处为适应屏幕
	        ViewGroup vg=(ViewGroup)findViewById(R.id.parentAbszdView);
	        int cw=LoginActivity.width-480;
	        int ch=LoginActivity.height-800;
	        vg.setPadding(cw/2, ch/2,cw/2, 0);
	        Log.d("NewGame001", "自适应屏幕。");
	        vg.setBackgroundColor(Color.LTGRAY);
	        vg.setBackgroundDrawable(new DrawableGameScreenBg().getDrawable());
        }
		btnTop=(Button)findViewById(R.id.btntop);
		btnDown=(Button)findViewById(R.id.btndown);
		btnLeft=(Button)findViewById(R.id.btnleft);
		btnRight=(Button)findViewById(R.id.btnright);
		absLayout=(View)findViewById(R.id.btnlayout);
		btnMove=(Button)findViewById(R.id.kongzhimove);
		btntiaoceng=(Button)findViewById(R.id.btntiaoceng);
		btnGuaiWuInfo=(Button)findViewById(R.id.btnguaiwuinfo);
		zhandouView=(ZhanDouView)findViewById(R.id.zhandouview);
		shopandnpc=(ShopAndNpcSayView)findViewById(R.id.shopandnpc);
		tcandgwinfo=(TiaoCengAndGWInfoView)findViewById(R.id.tcandgwinfo);
		Ontouch ontouch=new Ontouch();
		btnTop.setOnTouchListener(ontouch);
		btnDown.setOnTouchListener(ontouch);
		btnLeft.setOnTouchListener(ontouch);
		btnRight.setOnTouchListener(ontouch);
		btnMove.setOnTouchListener(ontouch);
		btntiaoceng.setOnTouchListener(ontouch);
		btnGuaiWuInfo.setOnTouchListener(ontouch);
		//重新绘制按钮
		//	btnTop.draw(canvas);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("newGame001  destroy..........."); 
	}

	//控件Move
	public void moveView(View v,int left,int top,int px,int py){
		v.layout(left-px,top-py, left-px+v.getWidth(), top-py+v.getHeight());
	}
	//判断是否触碰障碍物
	public boolean checkZAW(int [][] zaw,int ax,int ay){
		int x=actor.getX1();
		int y=actor.getY1();
		for (int row = 0; row < zaw.length; row++) {
			for (int column = 0; column < zaw[row].length; column++) {
				int value = zaw[row][column];
				if(value!=0){
					int zx= column*32;
					int zy= row*32;
					if(x+ax==zx&&y+ay==zy){
						this.indexRow=row;
						this.indexColumn=column;
						this.ydIndex=value;
						return false;
						}
					}
			}
		}
		return true;
	}
	public int chechBCZAW(int ax,int ay){
		//System.out.println("判断 是否 CurrentTime  "+System.currentTimeMillis());
		//如果障碍物 为null的时候则直接通过验证 
		if(ImgArrManager.zawImageArr==null || checkZAW(ImgArrManager.zawImageArr,ax,ay) ){
			if(ImgArrManager.npcImageArr==null || checkZAW(ImgArrManager.npcImageArr,ax,ay)){
				if(ImgArrManager.gwImageArr==null || checkZAW(ImgArrManager.gwImageArr,ax,ay) ){
					if(ImgArrManager.wpImageArr==null || checkZAW(ImgArrManager.wpImageArr, ax, ay)){
						if(ImgArrManager.menImageArr==null || checkZAW(ImgArrManager.menImageArr, ax, ay)){
							if(ImgArrManager.loutiImageArr==null || checkZAW(ImgArrManager.loutiImageArr, ax, ay)){
								//System.out.println("判断 通过  "+System.currentTimeMillis());	
								return 0;//通过
							}else{
								return 6;//遇到楼梯
							}
						}else{
							return 5;//遇到门
						}
					}else{
						return 4;//遇到物品
					}
				}else{
					return 3;//遇到怪物
				}
			}else{
				return 2;//遇到NPC
			}
		}else{
			return 1;	//遇到障碍物
		}
	}
	public void checkResult(int result,int ax,int ay){
		switch(result){
		case 0:
			//通过
			if(this.amx!=0||this.amy!=0){
				break;
			}
			this.amx=ax;
			this.amy=ay;
			getRunMove().start();
			break;
		case 1:
		//	Log.i("人物移动", "遇到障碍物。。。");
		//	SoundManager.getMediaPlayerYinXiao(3).start();
			break;
		case 2:
			//遇到NPC
			shopandnpc.npcIndex=ydIndex;
			shopandnpc.shopIndex=ydIndex/3+1;
			if(ydIndex/3==0){
				shopandnpc.npcrow=indexRow;
				shopandnpc.npccol=indexColumn;
				RenWuManager.getRWContent(1);
			}
			shopandnpc.setVisibility(View.VISIBLE);
			shopandnpc.handler=handler;
			keyFlag=false;//使按键无效
			break;
		case 3:
			this.guaiWu=GuaiWuManager.getGuaiWu(ydIndex);
			this.guaiWu.setGuaiWuArrInfo(ydIndex, indexRow, indexColumn);
			zhandouView.guaiWu=guaiWu;
			zhandouView=(ZhanDouView)findViewById(R.id.zhandouview);
			zhandouView.handler=this.handler;//共用一个handler主要用于消息收发
			zhandouView.setVisibility(View.VISIBLE);
			keyFlag=false;//使按键无效
			break;
		case 4:
			//遇到物品
		//	SoundManager.getMediaPlayerYinXiao(5).start();
			this.wuPin=WuPinManager.getWuPin(ydIndex);
			this.wuPin.setWPArrInfo(ydIndex, indexRow, indexColumn);
			String title=this.wuPin.getWpNameStr(this.actor);
			String content=this.wuPin.getWpInfo();
			dMessageView=(DMessageView)findViewById(R.id.dmessageview);
			dMessageView.setTitle(title);
			dMessageView.setContent(content);
			dMessageView.handler=this.handler;
			dMessageView.setVisibility(View.VISIBLE);
			if(!this.wuPin.removeWuPin(ImgArrManager.wpImageArr)){
				System.out.println("物品移除异常！");
			}
			keyFlag=false;
			break;
		case 5:
			//遇到门
			door=Door.getDoor(this);
			door.setKey(ydIndex);
			door.setDoorArrInfo(ydIndex, indexRow, indexColumn);
			String titlea= door.getDoorDkStr(this.actor);
			dMessageView=(DMessageView)findViewById(R.id.dmessageview);
			dMessageView.setTitle(titlea);
			dMessageView.setContent("");
			dMessageView.handler=this.handler;
			dMessageView.setVisibility(View.VISIBLE);
			if(!door.isCheckActorTG())
				return ;
			if(!this.door.removeDoor(ImgArrManager.menImageArr)){
				System.out.println("门移除异常！");
			}
			door.setHandler(this.handler);
			keyFlag=false;
			break;
		case 6:
			//遇到楼梯
			if(this.ydIndex>=16&&this.ydIndex<=19){
				Actor.loutiFlag=false;
				actor.setMtceng(actor.getMtceng()+1);
				Intent intent=new Intent();
				intent.setClass(this, LoadingActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				keyFlag=false;
			}else if (this.ydIndex>=40&&this.ydIndex<=43) {
				Actor.loutiFlag=true;
				actor.setMtceng(actor.getMtceng()-1);
				Intent intent=new Intent();
				intent.setClass(this, LoadingActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				keyFlag=false;
			}
			break;
		}
	}
	
	class Ontouch  implements OnTouchListener{
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if(event.getAction()==0){
				v.setBackgroundResource(R.drawable.yfxbtnbg);
			}else if(event.getAction()==1){
				v.setBackgroundResource(R.drawable.fxbtnbg);
			}
			if(!keyFlag){
				handler.removeCallbacks(onclickBtn);	//移除Runnable
				System.out.println("按键无效。。");
				return false;
			}
			//点击换层按钮
			if(v==btntiaoceng&&event.getAction()==1){
//				if(actor.tsWpMap.get(WuPinManager.LJXZ)!=null){
					tcandgwinfo.checkTcOrGw=1;
					tcandgwinfo.context=NewGame01.this;
					tcandgwinfo.setVisibility(View.VISIBLE);
					keyFlag=false;
//				}else{
//					dMessageView=(DMessageView)findViewById(R.id.dmessageview);
//					dMessageView.setTitle("还没有获得:【"+WuPinManager.LJXZ+"】");
//					dMessageView.setContent("");
//					dMessageView.handler=NewGame01.this.handler;
//					dMessageView.setVisibility(View.VISIBLE);
//					keyFlag=false;
//				}
				return false;
			}
			//点击怪物按钮
			if(v==btnGuaiWuInfo&&event.getAction()==1){
//				if(actor.tsWpMap.get(WuPinManager.LJXZ)!=null){
					tcandgwinfo.checkTcOrGw=2;
					tcandgwinfo.context=NewGame01.this;
					tcandgwinfo.setVisibility(View.VISIBLE);
					keyFlag=false;
//				}else{
//					dMessageView=(DMessageView)findViewById(R.id.dmessageview);
//					dMessageView.setTitle("还没有获得:【"+WuPinManager.LJXZ+"】");
//					dMessageView.setContent("");
//					dMessageView.handler=NewGame01.this.handler;
//					dMessageView.setVisibility(View.VISIBLE);
//					keyFlag=false;
//				}
				return false;
			}
			//按下0，按下移动2，松开1
			if(v!=btnMove&&v!=btntiaoceng&&v!=btnGuaiWuInfo){
				if(event.getAction()==0){
					onclickBtn.v=v;
					handler.post(onclickBtn);
				}else if(event.getAction()==1){
					handler.removeCallbacks(onclickBtn);
				}
			}
			//按下不放
			if(event.getAction()==2&&v==btnMove) {
				moveView(absLayout,(int)event.getRawX(),(int)event.getRawY(),v.getLeft()+(v.getWidth()/2),v.getTop()+(v.getHeight()/2));
			}
			return false;
		}
	}
	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if(msg.arg1==1){
				System.out.println("胜利！NewGame01 已收到！");
				//胜利后移除怪物
				if(!guaiWu.removeGuaiWu(ImgArrManager.gwImageArr)){
					System.out.println("战斗胜利后移除怪物...异常！");
				} 
				keyFlag=true;
			}else if(msg.arg1==2) {
				System.out.println("失败！NewGame01 已收到！");
				keyFlag=true;
			}else if(msg.arg1==5){
				System.out.println("短消息视图关闭、NewGame01 已收到！");
				keyFlag=true;
			}else if(msg.arg1==6){
				System.out.println("门已打开、NewGame01 已收到！");
				keyFlag=true;
			}else if(msg.arg1==8){
				System.out.println("商店窗口关闭、NewGame01 已收到！");
				keyFlag=true;
			}
			super.handleMessage(msg);
		}
	};
	OnclickBtn onclickBtn=new OnclickBtn();
	class OnclickBtn implements Runnable {
		int result=0;
		int ax=0;
		int ay=0;
		View v=null;
		@Override
		public void run() {
				if(!keyFlag){
					handler.removeCallbacks(onclickBtn);	//移除Runnable
					return;
				}
				actorCheckZAWMove();
				handler.postDelayed(onclickBtn, 80);
		}
		public void actorCheckZAWMove(){
			if(v==btnTop){
				ax=0;ay=-32;
				result= chechBCZAW(ax,ay);
				actor.setFx(3);
			}else if(v==btnDown) {
				ax=0;ay=+32;
				result= chechBCZAW(ax,ay);
				actor.setFx(0);
			}else if(v==btnLeft) {
				ax=-32;ay=0;
				result= chechBCZAW(ax,ay);
				actor.setFx(1);
			}else if(v==btnRight) {
				ax=+32;ay=0;
				result= chechBCZAW(ax,ay);
				actor.setFx(2);
			}
			checkResult(result,ax,ay);
		}
	}
	class RunMove implements Runnable{
		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					Thread.sleep(80);
					int speedx=0,speedy=0;
					speedx=amx>0?8:-8;
					speedy=amy>0?8:-8;
					if(amx!=0){
						amx-=speedx;
						actor.setX(actor.getX1()+speedx);
					}
					if(amy!=0){
						amy-=speedy;
						actor.setY(actor.getY()+speedy);
					}
					if(amy==0&&amx==0){
						runMove.interrupt();
						runMove=null;
					}
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}
	public Thread getRunMove() {
		if(runMove==null){
			//人物移动线程
			runMove=new Thread(new RunMove());
		}
		return runMove;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			Intent intent=new Intent();
			intent.setClass(this, LoginActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}
		return false;
	}
}