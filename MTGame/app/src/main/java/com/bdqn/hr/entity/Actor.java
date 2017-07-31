package com.bdqn.hr.entity;

import java.util.HashMap;
import java.util.Map;
import com.bdqn.hr.manager.MyBitMapManager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;

/**���*/
public class Actor extends View implements Runnable {
	private  String name="��ʿ";		//����
	private  int level=1;	//�ȼ�
	private  int hp=1000;			//Ѫ��
	private  int gjValue=50;		//����ֵ
	private  int fyValue=10;		//����ֵ
	private  int x;		//λ��x����
	private  int y;		//λ��y����
	private  int rKey=1;		//��Կ��
	private  int yKey=1;
	private  int bKey=1;
	private  int money=200;	//ӵ�н��
	private  int exp=100;		//ӵ�о���
	private  int fx;		//������Է���    0����  1���� 2���� 3����
	private  int currdz;		//����
	private  Bitmap actorMap=null; //����ͼ��
	private int mtceng; //�����ڵڼ���
	private int mtcengMax=1;//������󵽴����
	public static boolean loutiFlag=false;		//false ������	true ���� (����)
	//������Ʒ 	K=��Ʒ���� ��V ���ڼ���ж�
	public Map<String, String> tsWpMap=new HashMap<String, String>(); 
	
	//����
	private Actor(Context context) {
		super(context);
		actorMap=MyBitMapManager.getBitmapMyactor();
		new Thread(this).start();
	}
	private static Actor actor=null;
	public static  Actor getActor(Context context) {
		if(actor==null){
			actor=new Actor(context);
		}
		return actor;
	}
	
	public  void drawActor(Canvas canvas){
		Rect src=new Rect();
		src.left=currdz*32;
		src.top=fx*32;
		src.right=currdz*32+32;
		src.bottom=fx*32+32;
		Rect dst =new Rect();
		dst.left=x;
		dst.top=y;
		dst.right=x+32;
		dst.bottom=y+32;
		canvas.drawBitmap(actorMap, src, dst, null);
	}
	public  void drawActor(Canvas canvas,int left,int top,int w ,int h){
		fx=0;
		Rect src=new Rect();
		src.left=currdz*32;
		src.top=fx*32;
		src.right=currdz*32+32;
		src.bottom=fx*32+32;
		Rect dst =new Rect();
		dst.left=left;
		dst.top=top;
		dst.right=left+w;
		dst.bottom=top+h;
		canvas.drawBitmap(actorMap, src, dst, null);
	}
	//���������Ϣ(��Ļ����)
	public void drawActorInfo(Canvas canvas){
		Paint paint=new Paint();
		paint.setColor(Color.LTGRAY);
		paint.setTextSize(18);
		canvas.drawText(""+this.hp, 420,635, paint);//HP
		canvas.drawText(""+this.gjValue, 430,670, paint);//GJ
		canvas.drawText(""+this.fyValue, 430,702, paint);//FY
		canvas.drawText(""+this.yKey, 325,780, paint);//YELLO KEY 
		canvas.drawText(""+this.bKey, 385,780, paint);//BLUE KEY
		canvas.drawText(""+this.rKey, 445,780, paint);//RED KEY
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setTextSize(18);
		canvas.drawText("����"+(mtceng+1)+"�㡿", 295,728, paint);
		canvas.drawText("�ȼ�: "+this.level, 390,728, paint);
		canvas.drawText("���: "+this.money, 295,750, paint);
		canvas.drawText("����: "+this.exp, 385,750, paint);
	}
	//��������
	public String addLevel(int addvalue,int subexp){
		if(subexp>0){
			if(this.exp-subexp<0){
				return " ����ֵ������";
			}else{
				this.exp-=subexp;
			}
		}
		if(addvalue>0){
			this.level+=addvalue;
			this.gjValue+=addvalue*4;
			this.fyValue+=addvalue*4;
			this.hp+=addvalue*800;
		}
		return "��ϲ������ "+addvalue+" �� !";
	}
	@Override
	public void run() {
		while(true){
			try{
				Thread.sleep(300);
				currdz++;
				if(currdz>2){
					currdz=0;
				}
			}catch (Exception e) {
				Log.i(Actor.class.toString(), e.getMessage());
			}
			
		}
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGjValue() {
		return gjValue;
	}

	public void setGjValue(int gjValue) {
		this.gjValue = gjValue;
	}

	public int getFyValue() {
		return fyValue;
	}

	public void setFyValue(int fyValue) {
		this.fyValue = fyValue;
	}

	public int getX1() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY1() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getrKey() {
		return rKey;
	}

	public void setrKey(int rKey) {
		this.rKey = rKey;
	}

	public int getyKey() {
		return yKey;
	}

	public void setyKey(int yKey) {
		this.yKey = yKey;
	}

	public int getbKey() {
		return bKey;
	}

	public void setbKey(int bKey) {
		this.bKey = bKey;
	}

	public int getFx() {
		return fx;
	}

	public void setFx(int fx) {
		this.fx = fx;
	}

	public int getCurrdz() {
		return currdz;
	}

	public void setCurrdz(int currdz) {
		this.currdz = currdz;
	}

	public Bitmap getActorMap() {
		return actorMap;
	}

	public void setActorMap(Bitmap actorMap) {
		this.actorMap = actorMap;
	}

	public int getMtceng() {
		return mtceng;
	}

	public void setMtceng(int mtceng) {
		this.mtceng = mtceng;
		if(this.mtceng>this.mtcengMax){
			this.mtcengMax=this.mtceng;
		}
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getHp() {
		return hp;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void setMtcengMax(int mtcengMax) {
		this.mtcengMax = mtcengMax;
	}

	public int getMtcengMax() {
		return mtcengMax;
	}
}
