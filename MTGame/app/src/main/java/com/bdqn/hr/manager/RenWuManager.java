package com.bdqn.hr.manager;

public class RenWuManager {
	public static String[][] content=new String[10][10];
	public static int[] renwuJD=new int[10];
	public static boolean[] bool=new boolean[10];
	static{
		for(int i=0;i<content.length;i++){
			content[i]=new String[10];
			for(int j=0;j<content[i].length;j++){
				content[i][j]=new String();
			}
		}
	}
	public static void getRWContent(int index){
		switch(index){
			case 1:
				content[0][0]="���ӣ�--            ��ӭ������ħ������ !  --    ���ϴ��ڵ� 3 ��  ��ʱ��С��--    ���ҵı��ʮ�ּܡ�Ū���ˡ�" +
						"--    ������ܰ����һ���,�ҿ���--    �����������--  --    ע: Hp������������ ������1/3 ! ";
				content[0][1]="���ӣ�--            ���ҵ���ʮ�ּܡ���--    �����ǻ������ң�";
				content[0][2]="���ӣ�--            лл����ʿ��--    ������ף����--    �¸ҵ�ȥս���ɣ�";
				content[0][3]="���ӣ�--            �װ�����ʿ��--    ���Ѿ������ף����--    �¸ҵ�ȥս���ɣ�";
				break;
		}
	}
}
