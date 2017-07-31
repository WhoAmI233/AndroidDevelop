package com.bdqn.hr.manager;

import android.content.Context;

import com.bdqn.hr.entity.GuaiWu;

public class GuaiWuManager {

	public static GuaiWu getGuaiWu(int imgindex) {
		GuaiWu guaiWu = new GuaiWu();
		int index = imgindex / 4;
		switch (index) {
		case 0:
			guaiWu.initGuaiWu("Ğ¡÷¼÷Ã", 300, 50, 20,50,30, index);
			break;
		case 1:
			guaiWu.initGuaiWu("µ¶¶Ü÷¼÷Ã", 800, 80, 30,80,40, index);
			break;
		case 2:
			guaiWu.initGuaiWu("¿ñ±©÷¼÷Ã", 77, 6, 8,5,8, index);
			break;
		case 3:
			guaiWu.initGuaiWu("¸Ö¼×÷¼÷Ã", 100, 6, 8,5,8, index);
			break;
		case 4:
			guaiWu.initGuaiWu("Ğ¡òùòğ", 150, 15, 3,10,15, index);
			break;
		case 5:
			guaiWu.initGuaiWu("´óòùòğ", 1000, 90, 70,120,70, index);
			break;
		case 6:
			guaiWu.initGuaiWu("ÁÒÑæòùòğ", 2000, 200, 180,200,150, index);
			break;
		case 7:
			guaiWu.initGuaiWu("ºÚÅÛ·¨Ê¦", 100, 6, 8,5,8, index);
			break;
		case 8:
			guaiWu.initGuaiWu("ÇàÅİÅİ", 150, 25, 2,20,15, index);
			break;
		case 9:
			guaiWu.initGuaiWu("ºìÅİÅİ", 100, 15, 2,10,10, index);
			break;
		case 10:
			guaiWu.initGuaiWu("ºÚÅİÅİ", 200, 15, 3,30,10, index);
			break;
		case 11:
			guaiWu.initGuaiWu("´óÁ³Äñ", 100, 6, 8,5,8, index);
			break;
		case 12:
			guaiWu.initGuaiWu("À¶ÅÛÎ×Ê¦", 500, 20, 5,30,20, index);
			break;
		case 13:
			guaiWu.initGuaiWu("ºìÅÛÎ×Ê¦", 100, 6, 8,5,8, index);
			break;
		case 14:
			guaiWu.initGuaiWu("»Ò·¨Ê¦", 100, 6, 8,5,8, index);
			break;
		case 15:
			guaiWu.initGuaiWu("ºì·¨Ê¦", 100, 6, 8,5,8, index);
			break;
		case 16:
			guaiWu.initGuaiWu("ÊŞÈË", 500, 50, 50,100,50, index);
			break;
		case 17:
			guaiWu.initGuaiWu("µ¶¶ÜÊŞÈË", 100, 6, 8,5,8, index);
			break;
		case 18:
			guaiWu.initGuaiWu("Ê¯ÏñÍ·", 2500, 180, 150,120,100, index);
			break;
		case 19:
			guaiWu.initGuaiWu("±ÇÌéÈË", 100, 6, 8,5,8, index);
			break;
		case 20:
			guaiWu.initGuaiWu("Ê¯Í·ÈË", 1000, 80, 60,100,50, index);
			break;
		case 21:
			guaiWu.initGuaiWu("À¶¼×Ê¯ÈË", 100, 6, 8,5,8, index);
			break;
		case 22:
			guaiWu.initGuaiWu("ºì¼×Ê¯ÈË", 100, 6, 8,5,8, index);
			break;
		case 23:
			guaiWu.initGuaiWu("µÁÔô", 100, 6, 8,5,8, index);
			break;
		case 24:
			guaiWu.initGuaiWu("Ä§Íõ", 5000, 300, 220,88,88, index);
			break;
		case 25:
			guaiWu.initGuaiWu("ºìÑÛÀÇÍ·", 100, 6, 8,5,8, index);
			break;
		case 26:
			guaiWu.initGuaiWu("ºüÀê¾«", 100, 6, 8,5,8, index);
			break;
		case 27:
			guaiWu.initGuaiWu("òùòğ¾«", 2500, 190, 160,150,100, index);
			break;
		case 28:
			guaiWu.initGuaiWu("Ğ¡³¬ÈË", 100, 6, 8,5,8, index);
			break;
		case 29:
			guaiWu.initGuaiWu("¾Şòğ³¬ÈË", 3000, 200, 200,300,200, index);
			break;
		case 30:
			guaiWu.initGuaiWu("½£¶Ü³¬ÈË", 100, 6, 8,5,8, index);
			break;
		case 31:
			guaiWu.initGuaiWu("³¬ÈË½Ì¹Ù", 100, 6, 8,5,8, index);
			break;
		case 32:
			guaiWu.initGuaiWu("ºì¼×ÎÀÊ¿", 100, 6, 8,5,8, index);
			break;
		case 33:
			guaiWu.initGuaiWu("Ğ°¶ñµ¶¿Í", 100, 6, 8,5,8, index);
			break;
		case 34:
			guaiWu.initGuaiWu("Ğ°¶ñ´ó·¨Ê¦", 100, 6, 8,5,8, index);
			break;
		case 35:
			guaiWu.initGuaiWu("Ã¨Í·¾«", 100, 6, 8,5,8, index);
			break;
		case 36:
			guaiWu.initGuaiWu("÷¼÷Ã´ó·¨Ê¦", 100, 6, 8,5,8, index);
			break;
		case 37:
			guaiWu.initGuaiWu("½ğ¼×ÊØÎÀ", 1000, 56, 8,5,8, index);
			break;
		case 38:
			guaiWu.initGuaiWu("ÍÁºÀ÷¼÷Ã", 100, 6, 8,5,8, index);
			break;
		case 39:
			guaiWu.initGuaiWu("µ¶¶ÜÖí", 3000, 200, 180,200,150, index);
			break;
		case 40:
			guaiWu.initGuaiWu("ºìÑÛ»ÆÅİÅİ", 100, 6, 8,5,8, index);
			break;
		case 41:
			guaiWu.initGuaiWu("×Ï¼×÷¼÷Ã", 100, 6, 8,5,8, index);
			break;
		case 42:
			guaiWu.initGuaiWu("òùòğÍõ", 100, 6, 8,5,8, index);
			break;
		case 43:
			guaiWu.initGuaiWu("ºÚÊ¯ÏñÍ·", 100, 6, 8,5,8, index);
			break;
		case 44:
			guaiWu.initGuaiWu("ºÚ¼×ÊØÎÀ", 100, 6, 8,5,8, index);
			break;
		case 45:
			guaiWu.initGuaiWu("»Æ¼×ÊØÎÀ", 100, 6, 8,5,8, index);
			break;
		case 46:
			guaiWu.initGuaiWu("Çà¼×ÊØÎÀ", 100, 6, 8,5,8, index);
			break;
		case 47:
			guaiWu.initGuaiWu("À¶¼×½«¾ü", 100, 6, 8,5,8, index);
			break;
		case 48:
			guaiWu.initGuaiWu("Î×Ê¦Íõ", 100, 6, 8,5,8, index);
			break;
		case 49:
			guaiWu.initGuaiWu("ºìÒÂµÁÔô", 100, 6, 8,5,8, index);
			break;
		case 50:
			guaiWu.initGuaiWu("°×±ÇÌéÈË", 100, 6, 8,5,8, index);
			break;
		case 51:
			guaiWu.initGuaiWu("¶¾ÊŞÈË", 100, 6, 8,5,8, index);
			break;
		case 52:
			guaiWu.initGuaiWu("ºì¼×ÊØÎÀ", 100, 6, 8,5,8, index);
			break;
		case 53:
			guaiWu.initGuaiWu("À¶¼×ÊØÎÀ", 100, 6, 8,5,8, index);
			break;
		case 54:
			guaiWu.initGuaiWu("Ğ°¶ñ½Ì»Ê", 100, 6, 8,5,8, index);
			break;
		case 55:
			guaiWu.initGuaiWu("°×É«ÅİÅİ", 60, 12, 8,5,8, index);
			break;
		case 56:
			guaiWu.initGuaiWu("Ê®×Ö¾ü", 100, 6, 8,5,8, index);
			break;
		case 57:
			guaiWu.initGuaiWu("´óµØÊØÎÀ", 100, 6, 8,5,8, index);
			break;
		case 58:
			guaiWu.initGuaiWu("»ğÑæÊØÎÀ", 100, 6, 8,5,8, index);
			break;
		case 59:
			guaiWu.initGuaiWu("Ğ°¶ñ¿şÀÜ", 100, 6, 8,5,8, index);
			break;
		}
		return guaiWu;
	}
}
