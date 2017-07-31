package com.bdqn.hr.manager;

import com.bdqn.hr.activity.LoginActivity;
import com.bdqn.hr.activity.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

public class MyBitMapManager extends View {
	private static Bitmap bitmapHC = null;	//ª∫≥ÂÕº
	private static Bitmap bitmapHC2 = null;	//ª∫≥ÂÕº2
	private static Bitmap bitmapImgall = null;	//¥ÛÕº°¢±≥æ∞
	private static Bitmap bitmapWupin = null;	//ŒÔ∆∑Õº
	private static Bitmap bitmapWupinbg = null;	//œ˚œ¢ ”Õº±≥æ∞
	private static Bitmap bitmapMtfontimg = null;	//º¸≈Ãœ¬±≥æ∞
	private static Bitmap bitmapDoor = null;	//√≈
	private static Bitmap bitmapEnemy = null;	//π÷ŒÔ
	private static Bitmap bitmapMyoto = null;	//∆¬∏ÁÕ∑œÒ
	private static Bitmap bitmapMtlodingbg = null;	//ªª≤„ ”Õº±≥æ∞Õº
	private static Bitmap bitmapZhandoubg = null;	//’Ω∂∑ ”Õº±≥æ∞Õº
	private static Bitmap bitmapWinimg = null;	//’Ω∂∑ §¿˚Õº
	private static Bitmap bitmapShibaiimg = null;	//’Ω∂∑ ß∞‹Õº
	private static Bitmap bitmapMyactor = null;	//Ω«…´Õº
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

	/**ª∫≥ÂÕº*/
	public static Bitmap getBitmapHC() {
		return bitmapHC;
	}
	/**¥ÛÕº°¢±≥æ∞*/
	public static Bitmap getBitmapImgall() {
		return bitmapImgall;
	}
	/**ŒÔ∆∑Õº*/
	public static Bitmap getBitmapWupin() {
		return bitmapWupin;
	}
	/**º¸≈Ãœ¬±≥æ∞*/
	public static Bitmap getBitmapMtfontimg() {
		return bitmapMtfontimg;
	}
	/**√≈*/
	public static Bitmap getBitmapDoor() {
		return bitmapDoor;
	}
	/**π÷ŒÔ*/
	public static Bitmap getBitmapEnemy() {
		return bitmapEnemy;
	}
	/**∆¬∏ÁÕ∑œÒ*/
	public static Bitmap getBitmapMyoto() {
		return bitmapMyoto;
	}
	/**ªª≤„ ”Õº±≥æ∞Õº*/
	public static Bitmap getBitmapMtlodingbg() {
		return bitmapMtlodingbg;
	}
	/**’Ω∂∑ ”Õº±≥æ∞Õº*/
	public static Bitmap getBitmapZhandoubg() {
		return bitmapZhandoubg;
	}
	/**’Ω∂∑ §¿˚Õº*/
	public static Bitmap getBitmapWinimg() {
		return bitmapWinimg;
	}
	/**’Ω∂∑ ß∞‹Õº*/
	public static Bitmap getBitmapShibaiimg() {
		return bitmapShibaiimg;
	}
	/**œ˚œ¢ ”Õº±≥æ∞*/
	public static Bitmap getBitmapWupinbg() {
		return bitmapWupinbg;
	}
	/**Ω«…´Õº*/
	public static Bitmap getBitmapMyactor() {
		return bitmapMyactor;
	}
	/**NPCÕº*/
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
