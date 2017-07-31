package com.bdqn.hr.manager;

import android.content.Context;

import com.bdqn.hr.entity.GuaiWu;

public class GuaiWuManager {

	public static GuaiWu getGuaiWu(int imgindex) {
		GuaiWu guaiWu = new GuaiWu();
		int index = imgindex / 4;
		switch (index) {
		case 0:
			guaiWu.initGuaiWu("С����", 300, 50, 20,50,30, index);
			break;
		case 1:
			guaiWu.initGuaiWu("��������", 800, 80, 30,80,40, index);
			break;
		case 2:
			guaiWu.initGuaiWu("������", 77, 6, 8,5,8, index);
			break;
		case 3:
			guaiWu.initGuaiWu("�ּ�����", 100, 6, 8,5,8, index);
			break;
		case 4:
			guaiWu.initGuaiWu("С����", 150, 15, 3,10,15, index);
			break;
		case 5:
			guaiWu.initGuaiWu("������", 1000, 90, 70,120,70, index);
			break;
		case 6:
			guaiWu.initGuaiWu("��������", 2000, 200, 180,200,150, index);
			break;
		case 7:
			guaiWu.initGuaiWu("���۷�ʦ", 100, 6, 8,5,8, index);
			break;
		case 8:
			guaiWu.initGuaiWu("������", 150, 25, 2,20,15, index);
			break;
		case 9:
			guaiWu.initGuaiWu("������", 100, 15, 2,10,10, index);
			break;
		case 10:
			guaiWu.initGuaiWu("������", 200, 15, 3,30,10, index);
			break;
		case 11:
			guaiWu.initGuaiWu("������", 100, 6, 8,5,8, index);
			break;
		case 12:
			guaiWu.initGuaiWu("������ʦ", 500, 20, 5,30,20, index);
			break;
		case 13:
			guaiWu.initGuaiWu("������ʦ", 100, 6, 8,5,8, index);
			break;
		case 14:
			guaiWu.initGuaiWu("�ҷ�ʦ", 100, 6, 8,5,8, index);
			break;
		case 15:
			guaiWu.initGuaiWu("�취ʦ", 100, 6, 8,5,8, index);
			break;
		case 16:
			guaiWu.initGuaiWu("����", 500, 50, 50,100,50, index);
			break;
		case 17:
			guaiWu.initGuaiWu("��������", 100, 6, 8,5,8, index);
			break;
		case 18:
			guaiWu.initGuaiWu("ʯ��ͷ", 2500, 180, 150,120,100, index);
			break;
		case 19:
			guaiWu.initGuaiWu("������", 100, 6, 8,5,8, index);
			break;
		case 20:
			guaiWu.initGuaiWu("ʯͷ��", 1000, 80, 60,100,50, index);
			break;
		case 21:
			guaiWu.initGuaiWu("����ʯ��", 100, 6, 8,5,8, index);
			break;
		case 22:
			guaiWu.initGuaiWu("���ʯ��", 100, 6, 8,5,8, index);
			break;
		case 23:
			guaiWu.initGuaiWu("����", 100, 6, 8,5,8, index);
			break;
		case 24:
			guaiWu.initGuaiWu("ħ��", 5000, 300, 220,88,88, index);
			break;
		case 25:
			guaiWu.initGuaiWu("������ͷ", 100, 6, 8,5,8, index);
			break;
		case 26:
			guaiWu.initGuaiWu("���꾫", 100, 6, 8,5,8, index);
			break;
		case 27:
			guaiWu.initGuaiWu("����", 2500, 190, 160,150,100, index);
			break;
		case 28:
			guaiWu.initGuaiWu("С����", 100, 6, 8,5,8, index);
			break;
		case 29:
			guaiWu.initGuaiWu("������", 3000, 200, 200,300,200, index);
			break;
		case 30:
			guaiWu.initGuaiWu("���ܳ���", 100, 6, 8,5,8, index);
			break;
		case 31:
			guaiWu.initGuaiWu("���˽̹�", 100, 6, 8,5,8, index);
			break;
		case 32:
			guaiWu.initGuaiWu("�����ʿ", 100, 6, 8,5,8, index);
			break;
		case 33:
			guaiWu.initGuaiWu("а�񵶿�", 100, 6, 8,5,8, index);
			break;
		case 34:
			guaiWu.initGuaiWu("а���ʦ", 100, 6, 8,5,8, index);
			break;
		case 35:
			guaiWu.initGuaiWu("èͷ��", 100, 6, 8,5,8, index);
			break;
		case 36:
			guaiWu.initGuaiWu("���ô�ʦ", 100, 6, 8,5,8, index);
			break;
		case 37:
			guaiWu.initGuaiWu("�������", 1000, 56, 8,5,8, index);
			break;
		case 38:
			guaiWu.initGuaiWu("��������", 100, 6, 8,5,8, index);
			break;
		case 39:
			guaiWu.initGuaiWu("������", 3000, 200, 180,200,150, index);
			break;
		case 40:
			guaiWu.initGuaiWu("���ۻ�����", 100, 6, 8,5,8, index);
			break;
		case 41:
			guaiWu.initGuaiWu("�ϼ�����", 100, 6, 8,5,8, index);
			break;
		case 42:
			guaiWu.initGuaiWu("������", 100, 6, 8,5,8, index);
			break;
		case 43:
			guaiWu.initGuaiWu("��ʯ��ͷ", 100, 6, 8,5,8, index);
			break;
		case 44:
			guaiWu.initGuaiWu("�ڼ�����", 100, 6, 8,5,8, index);
			break;
		case 45:
			guaiWu.initGuaiWu("�Ƽ�����", 100, 6, 8,5,8, index);
			break;
		case 46:
			guaiWu.initGuaiWu("�������", 100, 6, 8,5,8, index);
			break;
		case 47:
			guaiWu.initGuaiWu("���׽���", 100, 6, 8,5,8, index);
			break;
		case 48:
			guaiWu.initGuaiWu("��ʦ��", 100, 6, 8,5,8, index);
			break;
		case 49:
			guaiWu.initGuaiWu("���µ���", 100, 6, 8,5,8, index);
			break;
		case 50:
			guaiWu.initGuaiWu("�ױ�����", 100, 6, 8,5,8, index);
			break;
		case 51:
			guaiWu.initGuaiWu("������", 100, 6, 8,5,8, index);
			break;
		case 52:
			guaiWu.initGuaiWu("�������", 100, 6, 8,5,8, index);
			break;
		case 53:
			guaiWu.initGuaiWu("��������", 100, 6, 8,5,8, index);
			break;
		case 54:
			guaiWu.initGuaiWu("а��̻�", 100, 6, 8,5,8, index);
			break;
		case 55:
			guaiWu.initGuaiWu("��ɫ����", 60, 12, 8,5,8, index);
			break;
		case 56:
			guaiWu.initGuaiWu("ʮ�־�", 100, 6, 8,5,8, index);
			break;
		case 57:
			guaiWu.initGuaiWu("�������", 100, 6, 8,5,8, index);
			break;
		case 58:
			guaiWu.initGuaiWu("��������", 100, 6, 8,5,8, index);
			break;
		case 59:
			guaiWu.initGuaiWu("а�����", 100, 6, 8,5,8, index);
			break;
		}
		return guaiWu;
	}
}
