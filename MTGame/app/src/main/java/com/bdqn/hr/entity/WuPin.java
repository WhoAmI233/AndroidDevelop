package com.bdqn.hr.entity;

/**��Ʒ*/
public class WuPin {
	private String name;		//��Ʒ����
	private int gj;	//���ӹ���ֵ
	private int fy;	//���ӷ���ֵ
	private int hp; //����HP
	private int ykey; //���ӻ�Կ��
	private int bkey; //������Կ��
	private int rkey; //���Ӻ�Կ��
	private int money;//���ӽ��
	private int exp;	//���Ӿ���
	private int level;	//���ӵȼ�
	private boolean isTSwp;	//�Ƿ���������Ʒ
	private int wpIndex,wpRow,wpCol;//��Ʒ���ڶ�ά����
	private WuPin() {
	}
	private static WuPin wupin=null;
	public static WuPin getWuPin() {
		if(wupin==null){
			wupin=new WuPin();
		}
		return wupin;
	}
	/** ��Ʒ ���ƣ�������������HP����Կ�ף���Կ�ף���Կ��, ��ң�����,�Ƿ�������Ʒ */
	public void initWuPin(String name,int gj, int fy,int hp,int ykey,int bkey,int rkey,int money,int exp,int level,boolean isTSwp){
		this.name=name;
		this.gj=gj;
		this.fy=fy;
		this.hp=hp;
		this.ykey=ykey;
		this.bkey=bkey;
		this.rkey=rkey;
		this.money=money;
		this.exp=exp;
		this.level=level;
		this.isTSwp=isTSwp;
	}
	public String getWpNameStr(Actor actor){
		if(this.isTSwp){
			actor.tsWpMap.put(this.name, "");
			return "��ϲ��� : ��"+this.name+"��";
		}
		if(this.gj>0)
			actor.setGjValue(actor.getGjValue()+this.gj);
		if(this.fy>0)
			actor.setFyValue(actor.getFyValue()+this.fy);
		if(this.hp>0)
			actor.setHp(actor.getHp()+this.hp);
		if(this.ykey>0)
			actor.setyKey(actor.getyKey()+this.ykey);
		if(this.rkey>0)
			actor.setrKey(actor.getrKey()+this.rkey);
		if(this.bkey>0)
			actor.setbKey(actor.getbKey()+this.bkey);
		if(this.money>0)
			actor.setMoney(actor.getMoney()+this.money);
		if(this.exp>0)
			actor.setExp(actor.getExp()+this.exp);
		if(this.level>0)
			actor.addLevel(this.level, 0);
		return "��ϲ��� : "+this.name;
	}
	public String getWpInfo(){
		if(this.isTSwp){
			return "";
		}
		String info=" ���� : ";
		info+=this.gj>0?" ���� "+this.gj+" ! ":"";
		info+=this.fy>0?" ���� "+this.fy+" ! ":"";
		info+=this.hp>0?" Hp "+this.hp+" ! ":"";
		info+=this.ykey>0?" ��Կ�� "+this.ykey+" ! ":"";
		info+=this.bkey>0?" ��Կ�� "+this.bkey+" ! ":"";
		info+=this.rkey>0?" ��Կ�� "+this.rkey+" ! ":"";
		info+=this.money>0?" ��� "+this.money+" ! ":"";
		info+=this.exp>0?" ���� "+this.exp+" ! ":"";
		info+=this.level>0?" �ȼ� "+this.level+" ! ":"";
		return info;
	}
	//�Ƴ������е���Ʒ
	public boolean removeWuPin(int [][] wpArr){
		if(wpArr[wpRow][wpCol]==wpIndex){
			wpArr[wpRow][wpCol]=0;
			return true;
		}else{
			return false;
		}
	}
	//�����Ʒ�������е���Ϣ
	public void setWPArrInfo(int index,int row,int col){
		 this.wpIndex=index;
		 this.wpRow=row;
		 this.wpCol=col;
	}
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getGj() {
		return gj;
	}


	public void setGj(int gj) {
		this.gj = gj;
	}


	public int getFy() {
		return fy;
	}


	public void setFy(int fy) {
		this.fy = fy;
	}


	public int getHp() {
		return hp;
	}


	public void setHp(int hp) {
		this.hp = hp;
	}


	public int getYkey() {
		return ykey;
	}


	public void setYkey(int ykey) {
		this.ykey = ykey;
	}


	public int getBkey() {
		return bkey;
	}


	public void setBkey(int bkey) {
		this.bkey = bkey;
	}


	public int getRkey() {
		return rkey;
	}


	public void setRkey(int rkey) {
		this.rkey = rkey;
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


	public boolean isTSwp() {
		return isTSwp;
	}


	public void setTSwp(boolean isTSwp) {
		this.isTSwp = isTSwp;
	}
	public int getWpIndex() {
		return wpIndex;
	}
	public void setWpIndex(int wpIndex) {
		this.wpIndex = wpIndex;
	}
	public int getWpRow() {
		return wpRow;
	}
	public void setWpRow(int wpRow) {
		this.wpRow = wpRow;
	}
	public int getWpCol() {
		return wpCol;
	}
	public void setWpCol(int wpCol) {
		this.wpCol = wpCol;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getLevel() {
		return level;
	}
}
