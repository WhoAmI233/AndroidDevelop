package com.bdqn.hr.entity;

import com.bdqn.hr.manager.WuPinManager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;

/**��*/
public class Door  extends View{
	private  int key=0;		//0�ƣ�1����2�죬3����(���⣬��Ҫ����)
	public boolean checkDraw=false;
	private  int currdz=0;		//����
	private Handler handler=null;
	private int doorIndex,doorRow,doorCol;//��Ʒ���ڶ�ά����
	private boolean checkActorTG;	//��֤����Ƿ�ӵ�п������ŵ�����
	//����
	private Door(Context context) {
		super(context);
	}
	private static Door door=null;
	public static  Door getDoor(Context context) {
		if(door==null){
			door=new Door(context);
		}
		return door;
	}
	public void setHandler(Handler handler) {
		this.handler = handler;
		this.currdz=0;
		this.checkDraw=true;
		this.handler.post(runnable);
	}
	public String getDoorDkStr(Actor actor){
		this.checkActorTG=false;
		if(this.key==0){
			if(actor.getyKey()<=0){
				return "��û�л�Կ�ף�";
			}else{
				actor.setyKey(actor.getyKey()-1);
				this.checkActorTG=true;
				return "���ģ���Կ�� * 1 ��";
			}
		}
		if(this.key==1){
			if(actor.getbKey()<=0){
				return "��û����Կ�ף�";
			}else{
				actor.setbKey(actor.getbKey()-1);
				this.checkActorTG=true;
				return "���ģ���Կ�� * 1 ��";
			}
		}
		if(this.key==2){
			if(actor.getrKey()<=0){
				return "��û�к�Կ�ף�";
			}else{
				actor.setrKey(actor.getrKey()-1);
				this.checkActorTG=true;
				return "���ģ���Կ�� * 1 ��";
			}
		}
		if(this.key==3){
			if(actor.tsWpMap.get(WuPinManager.TIEGAO)==null){
				return "����û�� ��á�"+WuPinManager.TIEGAO+"����";
			}else{
				actor.setyKey(actor.getyKey()-1);
				this.checkActorTG=true;
				return "��ʹ���� ��"+WuPinManager.TIEGAO+"�� ��";
			}
		}
		return "";
	}
	//�Ƴ������е���
	public boolean removeDoor(int [][] menArr){
		if(menArr[doorRow][doorCol]==doorIndex){
			menArr[doorRow][doorCol]=0;
			return true;
		}else{
			return false;
		}
	}
	//������������е���Ϣ
	public void setDoorArrInfo(int index,int row,int col){
		 this.doorIndex=index;
		 this.doorRow=row;
		 this.doorCol=col;
	}
	Runnable runnable=new Runnable() {
		@Override
		public void run() {
			currdz++;
			if(currdz==4){
				checkDraw=false;
				currdz=0;
				Message message=handler.obtainMessage();
				message.arg1=6;
				handler.sendMessage(message);
				System.out.println("���Ŵ򿪵Ľ�����͸� activity   ........");
				handler.removeCallbacks(runnable);
			}else{
				handler.postDelayed(runnable, 300);
			}
		}
	};
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key/4;
	}
	public int getDoorIndex() {
		return doorIndex;
	}
	public void setDoorIndex(int doorIndex) {
		this.doorIndex = doorIndex;
	}
	public int getDoorRow() {
		return doorRow;
	}
	public void setDoorRow(int doorRow) {
		this.doorRow = doorRow;
	}
	public int getDoorCol() {
		return doorCol;
	}
	public void setDoorCol(int doorCol) {
		this.doorCol = doorCol;
	}
	public int getCurrdz() {
		return currdz;
	}
	public void setCurrdz(int currdz) {
		this.currdz = currdz;
	}
	public void setCheckActorTG(boolean checkActorTG) {
		this.checkActorTG = checkActorTG;
	}
	public boolean isCheckActorTG() {
		return checkActorTG;
	}
}
