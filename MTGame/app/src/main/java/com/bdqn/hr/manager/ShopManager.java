package com.bdqn.hr.manager;

public class ShopManager {
	public static String[] shopTitle;
	public static int[] BuffVale;
	public static int[] XHValue;
	
	public static void getShopInfo(int shopIndex){
		
		switch(shopIndex){
			case 2:
				shopTitle =new String[]{"    ������һ�����������--Ҫ��ֻҪ���н�ң��Ҿ�--���԰��㡣",
						"���� 1 �ѻ�Կ�ף�$10��","���� 1 ����Կ�ף�$50��","���� 1 �Ѻ�Կ�ף�$50��","�뿪�̵�"}; 
				BuffVale=new int[]{1,1,1};
				XHValue=new int[]{10,50,100};
				break;
			case 3:
				shopTitle =new String[]{"    ��Ҫ�������������--������� 100 ����ң�--���������ѡ��һ�",
						"���� 4000 ������","���� 20 �㹥��","���� 20 �����","�뿪�̵�"}; 
				BuffVale=new int[]{4000,20,20};
				XHValue=new int[]{100,100,100};
				break;
			case 4:
				shopTitle =new String[]{"    ��ã�Ӣ�۵����ֻ࣬--Ҫ�����㹻�ľ��飬�Ҿ�--���������ø�ǿ��",
						"����һ������Ҫ����100�㣩","���ӹ���5 ����Ҫ30�㣩","���ӷ���5����Ҫ30�㣩","�뿪�̵�"}; 
				BuffVale=new int[]{1,5,5};
				XHValue=new int[]{100,30,30};
				break;
			case 5:
				shopTitle =new String[]{"    ��Ҫ�������������--������� 25 ����ң�--���������ѡ��һ�",
						"���� 800 ������","���� 4 �㹥��","���� 4 �����","�뿪�̵�"}; 
				BuffVale=new int[]{800,4,4};
				XHValue=new int[]{25,25,25};
				break;
		}
	}
}
