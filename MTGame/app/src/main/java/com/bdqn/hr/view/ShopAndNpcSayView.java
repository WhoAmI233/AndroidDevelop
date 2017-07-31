package com.bdqn.hr.view;

import com.bdqn.hr.activity.R;
import com.bdqn.hr.entity.Actor;
import com.bdqn.hr.entity.Npc;
import com.bdqn.hr.manager.ImgArrManager;
import com.bdqn.hr.manager.MyBitMapManager;
import com.bdqn.hr.manager.RenWuManager;
import com.bdqn.hr.manager.ShopManager;
import com.bdqn.hr.manager.WuPinManager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.ToneGenerator;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.BufferType;

public class ShopAndNpcSayView extends ViewGroup {
	Button btnOne=null;
	Button btnTwo=null;
	Button btnThree=null;
	Button btnFour=null;
	public int npcIndex,npcrow,npccol;
	public int shopIndex=1;	//商店
	Npc npc=null;
	Actor actor=null;
	public Handler handler=null;
	public ShopAndNpcSayView(Context context, AttributeSet attrs) {
		super(context, attrs);
		npc=Npc.getNpc();
		actor=Actor.getActor(context);
	}
	
	@Override
	protected void onVisibilityChanged(View changedView, int visibility) {
		// TODO Auto-generated method stub
		super.onVisibilityChanged(changedView, visibility);
		//写的有点乱..............
		if(visibility==View.VISIBLE&&npcIndex/3!=0){
			//获取商店信息
			ShopManager.getShopInfo(shopIndex);
			shopTouchListener stl=new shopTouchListener();
			//如果按钮为null
			if(btnOne==null&&btnTwo==null&&btnThree==null){
				btnOne=new Button(this.getContext());
				btnOne.setText(ShopManager.shopTitle[1]);
				btnOne.layout(75, 120, 405, 170);
				btnOne.setPadding(0, 10, 0, 0);
				btnOne.setGravity(Gravity.CENTER);
				btnOne.setOnTouchListener(stl);
				this.addView(btnOne);
				btnTwo=new Button(this.getContext());
				btnTwo.setText(ShopManager.shopTitle[2]);
				btnTwo.layout(75, 180, 405, 230);
				btnTwo.setPadding(0, 10, 0, 0);
				btnTwo.setGravity(Gravity.CENTER);
				btnTwo.setOnTouchListener(stl);
				this.addView(btnTwo);
				btnThree=new Button(this.getContext());
				btnThree.setText(ShopManager.shopTitle[3]);
				btnThree.layout(75, 240, 405, 290);
				btnThree.setPadding(0, 10, 0, 0);
				btnThree.setGravity(Gravity.CENTER);
				btnThree.setOnTouchListener(stl);
				this.addView(btnThree);
				if(btnFour==null){
					btnFour=new Button(this.getContext());
					btnFour.setText(ShopManager.shopTitle[4]);
					btnFour.layout(75, 300, 405, 350);
					btnFour.setPadding(0, 10, 0, 0);
					btnFour.setGravity(Gravity.CENTER);
					btnFour.setOnTouchListener(stl);
					this.addView(btnFour);
				}else{
					btnFour.setText(ShopManager.shopTitle[4]);
				}
			}else{		//如果按钮对象已创建
				btnOne.setVisibility(View.VISIBLE);
				btnTwo.setVisibility(View.VISIBLE);
				btnThree.setVisibility(View.VISIBLE);
				btnOne.setText(ShopManager.shopTitle[1]);
				btnTwo.setText(ShopManager.shopTitle[2]);
				btnThree.setText(ShopManager.shopTitle[3]);
				btnFour.setText(ShopManager.shopTitle[4]);
			}
			btnbghei();
		}else if(visibility==View.VISIBLE&&npcIndex/3==0){		//如果是任务
			//如果按钮实例已创建、则隐藏按钮
			if(btnOne!=null&&btnTwo!=null){
				btnOne.setVisibility(View.INVISIBLE);
				btnTwo.setVisibility(View.INVISIBLE);
				btnThree.setVisibility(View.INVISIBLE);
			}
			shopTouchListener stl=new shopTouchListener();
			if(btnFour==null){
				btnFour=new Button(this.getContext());
				btnFour.setText("继续");
				btnFour.layout(75, 300, 405, 350);
				btnFour.setPadding(0, 10, 0, 0);
				btnFour.setGravity(Gravity.CENTER);
				btnFour.setOnTouchListener(stl);
				this.addView(btnFour);
			}
			if(!RenWuManager.bool[0]){
				btnFour.setText("接受任务");
			}else{
				if(actor.tsWpMap.get(WuPinManager.SHIZIJIA)==null){
					if(RenWuManager.renwuJD[0]!=3){
						RenWuManager.renwuJD[0]=1;
					}
				}else{
					RenWuManager.renwuJD[0]=2;
					actor.setHp(actor.getHp()+actor.getHp()/3);
					actor.setGjValue(actor.getGjValue()+actor.getGjValue()/3);
					actor.setFyValue(actor.getFyValue()+actor.getFyValue()/3);
					actor.tsWpMap.put(WuPinManager.SHIZIJIA, null);
				}
				btnFour.setText("关闭");
			}
			btnbghei();
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint paint=new Paint();
		paint.setTextSize(20);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setColor(Color.WHITE);
		if(npcIndex/3==0){
			if(RenWuManager.content[0][RenWuManager.renwuJD[0]] !=null){
				String [] strs=RenWuManager.content[0][RenWuManager.renwuJD[0]].split("--");
				for(int i=0;i<strs.length;i++){
					npc.drawImg(canvas,80, 10, 0, npcIndex/3*32, 64, 64);
					canvas.drawText(strs[i], 90, i==0?100:i*25+100, paint);
				}
			}
		}else{
			if(ShopManager.shopTitle !=null){
				String [] strs=ShopManager.shopTitle[0].split("--");
				for(int i=0;i<strs.length;i++){
					npc.drawImg(canvas,80, 10, 0, npcIndex/3*32, 64, 64);
					canvas.drawText(strs[i], 160, i==0?60:i*25+60, paint);
				}
			}
		}
	}
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
	}
	public void invisibilethis(){
		Message message=handler.obtainMessage();
		message.arg1=8;
		handler.sendMessage(message);
		this.setVisibility(View.INVISIBLE);
	}
	public void btnbghei(){
		if(npcIndex/3!=0){
			btnOne.setBackgroundResource(R.drawable.heishopxxbg);
			btnOne.setTextColor(Color.LTGRAY);
			btnTwo.setBackgroundResource(R.drawable.heishopxxbg);
			btnTwo.setTextColor(Color.LTGRAY);
			btnThree.setBackgroundResource(R.drawable.heishopxxbg);
			btnThree.setTextColor(Color.LTGRAY);
		}
		btnFour.setBackgroundResource(R.drawable.heishopxxbg);
		btnFour.setTextColor(Color.LTGRAY);
	}
	class shopTouchListener implements OnTouchListener{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if(event.getAction()==0){
				Button btn=(Button)v;
				btn.setBackgroundResource(R.drawable.huishopxxbg);
				btn.setTextColor(Color.YELLOW);
			}
			if(v==btnOne&&event.getAction()==1){
				if(shopIndex==4){
					actor.addLevel(ShopManager.BuffVale[0],ShopManager.XHValue[0] );		//升级、
				}else if(shopIndex==2){
					if(actor.getMoney()-ShopManager.XHValue[0]>=0){
						actor.setyKey(actor.getyKey()+ShopManager.BuffVale[0]);
						actor.setMoney(actor.getMoney()-ShopManager.XHValue[0]);
					}
				}else if(shopIndex==3||shopIndex==5){
					if(actor.getMoney()-ShopManager.XHValue[0]>=0){
						actor.setHp(actor.getHp()+ShopManager.BuffVale[0]);
						actor.setMoney(actor.getMoney()-ShopManager.XHValue[0]);
					}
				}
			}else if(v==btnTwo&&event.getAction()==1){
				if(shopIndex==4){
					if(actor.getExp()-ShopManager.XHValue[1]>=0){
						actor.setGjValue(actor.getGjValue()+ShopManager.BuffVale[1]);
						actor.setExp(actor.getExp()-ShopManager.XHValue[1]);
					}
				}else if(shopIndex==2){
					if(actor.getMoney()-ShopManager.XHValue[1]>=0){
						actor.setbKey(actor.getbKey()+ShopManager.BuffVale[1]);
						actor.setMoney(actor.getMoney()-ShopManager.XHValue[1]);
					}
				}else if(shopIndex==3||shopIndex==5){
					if(actor.getMoney()-ShopManager.XHValue[1]>=0){
						actor.setGjValue(actor.getGjValue()+ShopManager.BuffVale[1]);
						actor.setMoney(actor.getMoney()-ShopManager.XHValue[1]);
					}
				}
			}else if(v==btnThree&&event.getAction()==1){
				if(shopIndex==4){
					if(actor.getExp()-ShopManager.XHValue[2]>=0){
						actor.setFyValue(actor.getFyValue()+ShopManager.BuffVale[2]);
						actor.setExp(actor.getExp()-ShopManager.XHValue[2]);
					}
				}else if(shopIndex==2){
					if(actor.getMoney()-ShopManager.XHValue[2]>=0){
						actor.setrKey(actor.getrKey()+ShopManager.BuffVale[2]);
						actor.setMoney(actor.getMoney()-ShopManager.XHValue[2]);
					}
				}else if(shopIndex==3||shopIndex==5){
					if(actor.getMoney()-ShopManager.XHValue[2]>=0){
						actor.setFyValue(actor.getFyValue()+ShopManager.BuffVale[2]);
						actor.setMoney(actor.getMoney()-ShopManager.XHValue[2]);
					}
				}
			}
			if(v==btnFour&&event.getAction()==1){
				invisibilethis();
				if(npcIndex/3==0){
					if(!RenWuManager.bool[0]){
						RenWuManager.bool[0]=true;
						ImgArrManager.npcImageArr[npcrow][npccol]=0;
						ImgArrManager.npcImageArr[npcrow][npccol+1]=npcIndex;
					}
					if(RenWuManager.renwuJD[0]==2){
						RenWuManager.renwuJD[0]=3;
					}
				}
			}
			if(event.getAction()==1){
				btnbghei();
			}
			return false;
		}
	}

}
