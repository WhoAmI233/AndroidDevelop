package com.bdqn.hr.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bdqn.hr.activity.LoadingActivity;
import com.bdqn.hr.activity.NewGame01;
import com.bdqn.hr.activity.R;
import com.bdqn.hr.entity.Actor;
import com.bdqn.hr.entity.GuaiWu;
import com.bdqn.hr.manager.GuaiWuManager;
import com.bdqn.hr.manager.ImgArrManager;

public class TiaoCengAndGWInfoView extends ViewGroup{
	Actor actor=null;
	public int checkTcOrGw;
	Button btn1=null;
	Button btn2=null;
	Button btn3=null;
	Button btn4=null;
	Button btn5=null;
	Button btn6=null;
	Button btn7=null;
	Button btn8=null;
	Button btn9=null;
	Button btn10=null;
	Button btn11=null;
	Button btn12=null;
	Button btn13=null;
	Button btn14=null;
	Button btnClose=null;
	TcAndGWTouchListener tcandgwLis=null;
	public Context context;
	List<GuaiWu> guaiwuTypelist=new ArrayList<GuaiWu>();
	public TiaoCengAndGWInfoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		actor=Actor.getActor(context);
		tcandgwLis=new TcAndGWTouchListener();
		btn1=new Button(context);
		btn2=new Button(context);
		btn3=new Button(context);
		btn4=new Button(context);
		btn5=new Button(context);
		btn6=new Button(context);
		btn7=new Button(context);
		btn8=new Button(context);
		btn9=new Button(context);
		btn10=new Button(context);
		btn11=new Button(context);
		btn12=new Button(context);
		btn13=new Button(context);
		btn14=new Button(context);
		btnClose=new Button(context);
	}
	
	@Override
	protected void onVisibilityChanged(View changedView, int visibility) {
		super.onVisibilityChanged(changedView, visibility);
		if(checkTcOrGw==1 && visibility==View.VISIBLE){
			initBtn();
			btnClose.setTextSize(12);
			btnClose.layout(350, 285, 350+80, 285+50);
		}else if(checkTcOrGw==2 && visibility==View.VISIBLE){
			initBtn();
			getGuaiWuType(ImgArrManager.gwImageArr);
			btnClose.setTextSize(10);
			btnClose.layout(400, 280, 400+50, 280+65);
		}
	}
	public void getGuaiWuType(int [] [] arrImg){
		for (int row = 0; row < arrImg.length; row++) {
			for (int column = 0; column < arrImg[row].length; column++) {
				int imgIndex = arrImg[row][column];
				if(imgIndex>0){
					boolean b=true;
					for(int i=0;i<guaiwuTypelist.size();i++){
						if(imgIndex==guaiwuTypelist.get(i).getGwIndex()){
							b=false;
						}
					}
					if(b){
						GuaiWu guaiwu=GuaiWuManager.getGuaiWu(imgIndex);
						guaiwu.setGwIndex(imgIndex);
						guaiwuTypelist.add(guaiwu);
					}
				}
			}
		}
	}
	public void initBtn(){
		this.btnProperty(btn1, 50, 45, 1,null);
		this.btnProperty(btn2, 50, 105, 2,null);
		this.btnProperty(btn3, 50, 165, 3,null);
		this.btnProperty(btn4, 50, 225, 4,null);
		this.btnProperty(btn5, 50, 285, 5,null);
		this.btnProperty(btn6, 200, 45, 6,null);
		this.btnProperty(btn7, 200, 105, 7,null);
		this.btnProperty(btn8, 200, 165, 8,null);
		this.btnProperty(btn9, 200, 225, 9,null);
		this.btnProperty(btn10, 200, 285, 10,null);
		this.btnProperty(btn11, 350, 45, 11,null);
		this.btnProperty(btn12, 350, 105, 12,null);
		this.btnProperty(btn13, 350, 165, 13,null);
		this.btnProperty(btn14, 350, 225, 14,null);
		this.btnProperty(btnClose, 350, 285, 0,"关闭");
	}
	public void btnProperty(Button btn,int x,int y,int title,String strgb){
		if(btn.getTag()==null){
			btn.setWidth(50);
			btn.setHeight(30);
			btn.setText(""+title);
			btn.setTextSize(14);
			btn.setTextColor(Color.LTGRAY);
			btn.layout(x, y, x+80, y+50);
			btn.setGravity(Gravity.CENTER);
			btn.setBackgroundResource(R.drawable.heishopxxbg);
			btn.setOnTouchListener(tcandgwLis);
			btn.setTag("");
			this.addView(btn);
		}
		if(title<=actor.getMtcengMax()&&checkTcOrGw==1){
			btn.setVisibility(View.VISIBLE);
		}else{
			btn.setVisibility(View.INVISIBLE);
		}
		if(strgb!=null){
			btn.setTextSize(12);
			btn.setText(strgb);
			btn.setVisibility(View.VISIBLE);
		}
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if(checkTcOrGw==2){
			Paint paint=new Paint();
			paint.setTextSize(18);
			paint.setTypeface(Typeface.DEFAULT_BOLD);
			paint.setStyle(Paint.Style.STROKE);
			for(int i=0;i<guaiwuTypelist.size();i++){
				GuaiWu gw=guaiwuTypelist.get(i);
				paint.setColor(Color.YELLOW);
				canvas.drawRect(48, i*35+67, 78, i*35+96, paint);
				gw.drawGuaiWu(canvas, 50, i*35+70, 25, 25);
				paint.setColor(Color.LTGRAY);
				canvas.drawText(""+gw.getHp(), 50+40, i*35+90, paint);
				canvas.drawText(""+gw.getGjValue(), 50+80+25, i*35+90, paint);
				canvas.drawText(""+gw.getFyValue(), 50+80+25+60, i*35+90, paint);
				canvas.drawText(gw.getMoney()+"/"+gw.getExp(), 50+80+25+60+60, i*35+90, paint);
			}
			paint.setTextSize(24);
			canvas.drawText("Hp", 50+40, 55, paint);
			canvas.drawText("攻击", 50+40+60, 55, paint);
			canvas.drawText("防御", 50+40+60+60, 55, paint);
			canvas.drawText("金/经", 50+40+60+60+60, 55, paint);
			
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
	}
	
	class TcAndGWTouchListener implements OnTouchListener{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			Button btn=(Button)v;
			if(event.getAction()==0){
				v.setBackgroundResource(R.drawable.huishopxxbg);
				btn.setTextColor(Color.YELLOW);
			}else if(event.getAction()==1){
				v.setBackgroundResource(R.drawable.heishopxxbg);
				btn.setTextColor(Color.LTGRAY);
			}
			if(!"关闭".equals(btn.getText())&&event.getAction()==1){
				int mtceng=Integer.parseInt(""+btn.getText());
				actor.setMtceng(mtceng-1);
				Actor.loutiFlag=false;
				Intent intent=new Intent();
				intent.setClass(context, LoadingActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				context.startActivity(intent);
				TiaoCengAndGWInfoView.this.setVisibility(View.INVISIBLE);
			}else if("关闭".equals(btn.getText())&&event.getAction()==1){
				TiaoCengAndGWInfoView.this.setVisibility(View.INVISIBLE);
				NewGame01.keyFlag=true;
			}
			return false;
		}
		
	}
}
