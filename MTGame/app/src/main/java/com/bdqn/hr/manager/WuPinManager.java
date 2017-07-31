package com.bdqn.hr.manager;

import com.bdqn.hr.entity.WuPin;

public class WuPinManager {
	public static final String TIEGAO="����";
	public static final String SHIZIJIA="ʮ�ּ�";
	public static final String LJXZ="��������";
	public static WuPin getWuPin(int wpIndex){
		WuPin wuPin=WuPin.getWuPin();
		switch(wpIndex){
			case 13:
				wuPin.initWuPin("�챦ʯ", 3, 0, 0, 0, 0, 0, 0, 0,0, false);
				break;
			case 1:
				wuPin.initWuPin("����ʯ", 0, 3, 0, 0, 0, 0, 0, 0,0, false);
				break;
			case 2:
				wuPin.initWuPin("�̱�ʯ", 6, 0, 0, 0, 0, 0, 0, 0,0, false);
				break;
			case 3:
				wuPin.initWuPin("�Ʊ�ʯ", 0, 6, 0, 0, 0, 0, 0, 0,0, false);
				break;
			case 4:
				wuPin.initWuPin("СӪ��Һ", 0, 0, 200, 0, 0, 0, 0,0, 0, false);
				break;
			case 5:
				wuPin.initWuPin("��Ӫ��Һ", 0, 0, 500, 0, 0, 0, 0, 0,0, false);
				break;
			case 6:
				wuPin.initWuPin("��Ӫ��Һ", 0, 0, 800, 0, 0, 0, 0, 0,0, false);
				break;
			case 7:
				wuPin.initWuPin("����Ӫ��Һ", 0, 0, 1500, 0, 0, 0, 0, 0,0, false);
				break;
			case 14:
				wuPin.initWuPin("�ǻ�ˮ��", 0, 0, 0, 0, 0, 0, 0, 100,0, false);
				break;
			case 15:
				wuPin.initWuPin("���ǻ�ˮ��", 0, 0, 0, 0, 0, 0, 0, 200,0, false);
				break;
			case 16:
				wuPin.initWuPin("��Կ��", 0, 0, 0, 1, 0, 0, 0, 0,0, false);
				break;
			case 17:
				wuPin.initWuPin("��Կ��", 0, 0, 0, 0, 1, 0, 0, 0,0, false);
				break;
			case 18:
				wuPin.initWuPin("��Կ��", 0, 0, 0, 0, 0, 1, 0, 0,0, false);
				break;
			case 22:
				wuPin.initWuPin("Կ�׺�", 0, 0, 0, 1, 1, 1, 0, 0,0, false);
				break;
			case 28:
				wuPin.initWuPin("�ǻ�֮��", 0, 0, 0, 0, 0, 0, 0, 300,0, false);
				break;
			case 30:
				wuPin.initWuPin(LJXZ, 0, 0, 0, 0, 0, 0, 0, 0,0, true);
				break;
			case 31:
				wuPin.initWuPin("Ц����", 0, 0, 0, 0, 0, 0, 100, 0,0, false);
				break;
			case 33:
				wuPin.initWuPin(TIEGAO, 0, 0, 0, 0, 0, 0, 0, 0,0, true);
				break;
			case 36:
				wuPin.initWuPin("������", 0, 0, 0, 0, 0, 0, 0, 0,1, false);
				break;
			case 38:
				wuPin.initWuPin("�������", 0, 0, 0, 0, 0, 0, 0, 0,3, false);
				break;
			case 39:
				wuPin.initWuPin(SHIZIJIA, 0, 0, 0, 0, 0, 0, 0, 0,0, true);
				break;
			case 45:
				wuPin.initWuPin("���긣��", 20, 0, 2000, 0, 0, 0, 0, 0,0, false);
				break;
			case 48:
				wuPin.initWuPin("����", 5, 0, 0, 0, 0, 0, 0, 0,0, false);
				break;
			case 49:
				wuPin.initWuPin("�ֽ�", 15, 0, 0, 0, 0, 0, 0, 0,0, false);
				break;
			case 50:
				wuPin.initWuPin("������", 30, 0, 0, 0, 0, 0, 0, 0,0, false);
				break;
			case 51:
				wuPin.initWuPin("��ֽ�", 50, 0, 0, 0, 0, 0, 0, 0,0, false);
				break;
			case 52:
				wuPin.initWuPin("��罣", 100, 0, 0, 0, 0, 0, 0, 0,0, false);
				break;
			case 56:
				wuPin.initWuPin("Ƥ��", 0, 5, 0, 0, 0, 0, 0, 0,0, false);
				break;
			case 57:
				wuPin.initWuPin("�ֶ�", 0, 12, 0, 0, 0, 0, 0, 0,0, false);
				break;
			case 58:
				wuPin.initWuPin("��ľ��", 0, 25, 0, 0, 0, 0, 0, 0,0, false);
				break;
			case 59:
				wuPin.initWuPin("��ʯ��", 0, 50, 0, 0, 0, 0, 0, 0,0, false);
				break;
			case 60:
				wuPin.initWuPin("����", 0, 100, 0, 0, 0, 0, 0, 0,0, false);
				break;
		}
		return wuPin;
	}
}
