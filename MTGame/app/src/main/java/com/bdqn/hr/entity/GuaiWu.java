package com.bdqn.hr.entity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import com.bdqn.hr.manager.MyBitMapManager;

/**����*/
public class GuaiWu  implements Runnable   {
	private  String name="����";		//����
	private  int hp;				//Ѫ��
	private  int gjValue;		//����ֵ
	private  int fyValue;		//����ֵ
	private  int money;		//ɱ����ý��
	private  int exp;			//ɱ����þ���
	private  int currdz;		//����
	private  Bitmap guaiwuMap=null; //����ͼ��
	private int gwIndex,gwRow,gwCol;//�������ڶ�ά����
	private  int gauwuImageIndex=0; //����λ��
	
	public GuaiWu() {
		guaiwuMap=MyBitMapManager.getBitmapEnemy();
		new Thread(this).start();
	}
	public void initGuaiWu(String name,int hp,int gjValue,int fyValue,int money,int exp,int imgIndex){
		this.name=name;
		this.hp=hp;
		this.gjValue=gjValue;
		this.fyValue=fyValue;
		this.money=money;
		this.exp=exp;
		this.gauwuImageIndex=imgIndex;
	}
	public  void drawGuaiWu(Canvas canvas,int left,int top,int w ,int h){
		int fx=gauwuImageIndex;
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
		canvas.drawBitmap(guaiwuMap, src, dst, null);
	}
	//�Ƴ������еĹ���
	public boolean removeGuaiWu(int [][] gwArr){
		if(gwArr[gwRow][gwCol]==gwIndex){
			gwArr[gwRow][gwCol]=0;
			return true;
		}else{
			return false;
		}
	}
	//��ù����������е���Ϣ
	public void setGuaiWuArrInfo(int index,int row,int col){
		 this.gwIndex=index;
		 this.gwRow=row;
		 this.gwCol=col;
	}
	@Override
	public void run() {
		while(true){
			try{
				Thread.sleep(200);
				currdz++;
				if(currdz==4){
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
	public int getCurrdz() {
		return currdz;
	}

	public void setCurrdz(int currdz) {
		this.currdz = currdz;
	}

	public void setGauwuImageIndex(int gauwuImageIndex) {
		this.gauwuImageIndex = gauwuImageIndex;
	}

	public int getGauwuImageIndex() {
		return gauwuImageIndex;
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
	public int getGwRow() {
		return gwRow;
	}
	public void setGwRow(int gwRow) {
		this.gwRow = gwRow;
	}
	public int getGwCol() {
		return gwCol;
	}
	public void setGwCol(int gwCol) {
		this.gwCol = gwCol;
	}
	public void setGwIndex(int gwIndex) {
		this.gwIndex = gwIndex;
	}
	public int getGwIndex() {
		return gwIndex;
	}
}