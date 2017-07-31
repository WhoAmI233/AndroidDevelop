package com.bdqn.hr.manager;

import com.bdqn.hr.activity.LoginActivity;
import com.bdqn.hr.activity.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

public class MyBitMapManager extends View {
	private static Bitmap bitmapHC = null;	//����ͼ
	private static Bitmap bitmapHC2 = null;	//����ͼ2
	private static Bitmap bitmapImgall = null;	//��ͼ������
	private static Bitmap bitmapWupin = null;	//��Ʒͼ
	private static Bitmap bitmapWupinbg = null;	//��Ϣ��ͼ����
	private static Bitmap bitmapMtfontimg = null;	//�����±���
	private static Bitmap bitmapDoor = null;	//��
	private static Bitmap bitmapEnemy = null;	//����
	private static Bitmap bitmapMyoto = null;	//�¸�ͷ��
	private static Bitmap bitmapMtlodingbg = null;	//������ͼ����ͼ
	private static Bitmap bitmapZhandoubg = null;	//ս����ͼ����ͼ
	private static Bitmap bitmapWinimg = null;	//ս��ʤ��ͼ
	private static Bitmap bitmapShibaiimg = null;	//ս��ʧ��ͼ
	private static Bitmap bitmapMyactor = null;	//��ɫͼ
	private static Bitmap bitmapNpcimg = null;	//NPC
	private static Bitmap bitmapShopimgbg = null;	//NPC

	public MyBitMapManager(Context context) {
		super(context);
	}
	
	public void initBitMap() {
		if(bitmapHC==null || bitmapImgall==null || bitmapShibaiimg==null){
			System.out.println("load Image begin........");
			bitmapHC = Bitmap.createBitmap(480, 800, Config.ARGB_8888);
			bitmapHC2= Bitmap.createBitmap(LoginActivity.width, LoginActivity.height, Config.ARGB_8888);
			bitmapImgall = ((BitmapDrawable) getResources().getDrawable(R.drawable.imgall)).getBitmap();
			bitmapWupin = ((BitmapDrawable) getResources().getDrawable(R.drawable.wupin)).getBitmap();
			bitmapMtfontimg = ((BitmapDrawable) getResources().getDrawable(R.drawable.mtfontimg)).getBitmap();
			bitmapDoor = ((BitmapDrawable) getResources().getDrawable(R.drawable.door)).getBitmap();
			bitmapEnemy = ((BitmapDrawable) getResources().getDrawable(R.drawable.enemy)).getBitmap();
			bitmapMyoto = ((BitmapDrawable) getResources().getDrawable(R.drawable.myoto)).getBitmap();
			bitmapMtlodingbg = ((BitmapDrawable) getResources().getDrawable(R.drawable.mtlodingbg)).getBitmap();
			bitmapZhandoubg = ((BitmapDrawable) getResources().getDrawable(R.drawable.zhandoubg)).getBitmap();
			bitmapWinimg = ((BitmapDrawable) getResources().getDrawable(R.drawable.winimg)).getBitmap();
			bitmapShibaiimg = ((BitmapDrawable) getResources().getDrawable(R.drawable.shibaiimg)).getBitmap();
			bitmapWupinbg = ((BitmapDrawable) getResources().getDrawable(R.drawable.wupinbg)).getBitmap();
			bitmapMyactor= ((BitmapDrawable) getResources().getDrawable(R.drawable.myactor)).getBitmap();
			bitmapNpcimg= ((BitmapDrawable) getResources().getDrawable(R.drawable.npcimg)).getBitmap();
			System.out.println("load Image end........");
		}
	}

	/**����ͼ*/
	public static Bitmap getBitmapHC() {
		return bitmapHC;
	}
	/**��ͼ������*/
	public static Bitmap getBitmapImgall() {
		return bitmapImgall;
	}
	/**��Ʒͼ*/
	public static Bitmap getBitmapWupin() {
		return bitmapWupin;
	}
	/**�����±���*/
	public static Bitmap getBitmapMtfontimg() {
		return bitmapMtfontimg;
	}
	/**��*/
	public static Bitmap getBitmapDoor() {
		return bitmapDoor;
	}
	/**����*/
	public static Bitmap getBitmapEnemy() {
		return bitmapEnemy;
	}
	/**�¸�ͷ��*/
	public static Bitmap getBitmapMyoto() {
		return bitmapMyoto;
	}
	/**������ͼ����ͼ*/
	public static Bitmap getBitmapMtlodingbg() {
		return bitmapMtlodingbg;
	}
	/**ս����ͼ����ͼ*/
	public static Bitmap getBitmapZhandoubg() {
		return bitmapZhandoubg;
	}
	/**ս��ʤ��ͼ*/
	public static Bitmap getBitmapWinimg() {
		return bitmapWinimg;
	}
	/**ս��ʧ��ͼ*/
	public static Bitmap getBitmapShibaiimg() {
		return bitmapShibaiimg;
	}
	/**��Ϣ��ͼ����*/
	public static Bitmap getBitmapWupinbg() {
		return bitmapWupinbg;
	}
	/**��ɫͼ*/
	public static Bitmap getBitmapMyactor() {
		return bitmapMyactor;
	}
	/**NPCͼ*/
	public static Bitmap getBitmapNpcimg() {
		return bitmapNpcimg;
	}

	public static Bitmap getBitmapShopimgbg() {
		return bitmapShopimgbg;
	}

	public static Bitmap getBitmapHC2() {
		return bitmapHC2;
	}
}
