package com.bdqn.hr.manager;

import com.bdqn.hr.entity.WuPin;

public class WuPinManager {
	public static final String TIEGAO="Ìú¸ä";
	public static final String SHIZIJIA="Ê®×Ö¼Ü";
	public static final String LJXZ="Áù½ÇÐÇÕó";
	public static WuPin getWuPin(int wpIndex){
		WuPin wuPin=WuPin.getWuPin();
		switch(wpIndex){
			case 13:
				wuPin.initWuPin("ºì±¦Ê¯", 3, 0, 0, 0, 0, 0, 0, 0,0, false);
				break;
			case 1:
				wuPin.initWuPin("À¶±¦Ê¯", 0, 3, 0, 0, 0, 0, 0, 0,0, false);
				break;
			case 2:
				wuPin.initWuPin("ÂÌ±¦Ê¯", 6, 0, 0, 0, 0, 0, 0, 0,0, false);
				break;
			case 3:
				wuPin.initWuPin("»Æ±¦Ê¯", 0, 6, 0, 0, 0, 0, 0, 0,0, false);
				break;
			case 4:
				wuPin.initWuPin("Ð¡ÓªÑøÒº", 0, 0, 200, 0, 0, 0, 0,0, 0, false);
				break;
			case 5:
				wuPin.initWuPin("ÖÐÓªÑøÒº", 0, 0, 500, 0, 0, 0, 0, 0,0, false);
				break;
			case 6:
				wuPin.initWuPin("´óÓªÑøÒº", 0, 0, 800, 0, 0, 0, 0, 0,0, false);
				break;
			case 7:
				wuPin.initWuPin("³¬´óÓªÑøÒº", 0, 0, 1500, 0, 0, 0, 0, 0,0, false);
				break;
			case 14:
				wuPin.initWuPin("ÖÇ»ÛË®ºø", 0, 0, 0, 0, 0, 0, 0, 100,0, false);
				break;
			case 15:
				wuPin.initWuPin("´óÖÇ»ÛË®ºø", 0, 0, 0, 0, 0, 0, 0, 200,0, false);
				break;
			case 16:
				wuPin.initWuPin("»ÆÔ¿³×", 0, 0, 0, 1, 0, 0, 0, 0,0, false);
				break;
			case 17:
				wuPin.initWuPin("À¶Ô¿³×", 0, 0, 0, 0, 1, 0, 0, 0,0, false);
				break;
			case 18:
				wuPin.initWuPin("ºìÔ¿³×", 0, 0, 0, 0, 0, 1, 0, 0,0, false);
				break;
			case 22:
				wuPin.initWuPin("Ô¿³×ºÐ", 0, 0, 0, 1, 1, 1, 0, 0,0, false);
				break;
			case 28:
				wuPin.initWuPin("ÖÇ»ÛÖ®Êé", 0, 0, 0, 0, 0, 0, 0, 300,0, false);
				break;
			case 30:
				wuPin.initWuPin(LJXZ, 0, 0, 0, 0, 0, 0, 0, 0,0, true);
				break;
			case 31:
				wuPin.initWuPin("Ð¦Á³±Ò", 0, 0, 0, 0, 0, 0, 100, 0,0, false);
				break;
			case 33:
				wuPin.initWuPin(TIEGAO, 0, 0, 0, 0, 0, 0, 0, 0,0, true);
				break;
			case 36:
				wuPin.initWuPin("·ÉÐÐÒí", 0, 0, 0, 0, 0, 0, 0, 0,1, false);
				break;
			case 38:
				wuPin.initWuPin("´ó·ÉÐÐÒí", 0, 0, 0, 0, 0, 0, 0, 0,3, false);
				break;
			case 39:
				wuPin.initWuPin(SHIZIJIA, 0, 0, 0, 0, 0, 0, 0, 0,0, true);
				break;
			case 45:
				wuPin.initWuPin("ÐÂÄê¸£´ü", 20, 0, 2000, 0, 0, 0, 0, 0,0, false);
				break;
			case 48:
				wuPin.initWuPin("Ìú½£", 5, 0, 0, 0, 0, 0, 0, 0,0, false);
				break;
			case 49:
				wuPin.initWuPin("¸Ö½£", 15, 0, 0, 0, 0, 0, 0, 0,0, false);
				break;
			case 50:
				wuPin.initWuPin("´óÌú½£", 30, 0, 0, 0, 0, 0, 0, 0,0, false);
				break;
			case 51:
				wuPin.initWuPin("´ó¸Ö½£", 50, 0, 0, 0, 0, 0, 0, 0,0, false);
				break;
			case 52:
				wuPin.initWuPin("Óî¸ç½£", 100, 0, 0, 0, 0, 0, 0, 0,0, false);
				break;
			case 56:
				wuPin.initWuPin("Æ¤¶Ü", 0, 5, 0, 0, 0, 0, 0, 0,0, false);
				break;
			case 57:
				wuPin.initWuPin("¸Ö¶Ü", 0, 12, 0, 0, 0, 0, 0, 0,0, false);
				break;
			case 58:
				wuPin.initWuPin("¼áÄ¾¶Ü", 0, 25, 0, 0, 0, 0, 0, 0,0, false);
				break;
			case 59:
				wuPin.initWuPin("±¦Ê¯¶Ü", 0, 50, 0, 0, 0, 0, 0, 0,0, false);
				break;
			case 60:
				wuPin.initWuPin("Óî¸ç¶Ü", 0, 100, 0, 0, 0, 0, 0, 0,0, false);
				break;
		}
		return wuPin;
	}
}
