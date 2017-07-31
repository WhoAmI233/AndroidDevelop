package com.bdqn.hr.entity;

/**物品*/
public class WuPin {
	private String name;		//物品名称
	private int gj;	//增加攻击值
	private int fy;	//增加防御值
	private int hp; //增加HP
	private int ykey; //增加黄钥匙
	private int bkey; //增加蓝钥匙
	private int rkey; //增加红钥匙
	private int money;//增加金币
	private int exp;	//增加经验
	private int level;	//增加等级
	private boolean isTSwp;	//是否是特殊物品
	private int wpIndex,wpRow,wpCol;//物品所在二维数组
	private WuPin() {
	}
	private static WuPin wupin=null;
	public static WuPin getWuPin() {
		if(wupin==null){
			wupin=new WuPin();
		}
		return wupin;
	}
	/** 物品 名称，攻击，防御，HP，黄钥匙，蓝钥匙，红钥匙, 金币，经验,是否特殊物品 */
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
			return "恭喜获得 : 【"+this.name+"】";
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
		return "恭喜获得 : "+this.name;
	}
	public String getWpInfo(){
		if(this.isTSwp){
			return "";
		}
		String info=" 增加 : ";
		info+=this.gj>0?" 攻击 "+this.gj+" ! ":"";
		info+=this.fy>0?" 防御 "+this.fy+" ! ":"";
		info+=this.hp>0?" Hp "+this.hp+" ! ":"";
		info+=this.ykey>0?" 黄钥匙 "+this.ykey+" ! ":"";
		info+=this.bkey>0?" 蓝钥匙 "+this.bkey+" ! ":"";
		info+=this.rkey>0?" 红钥匙 "+this.rkey+" ! ":"";
		info+=this.money>0?" 金币 "+this.money+" ! ":"";
		info+=this.exp>0?" 经验 "+this.exp+" ! ":"";
		info+=this.level>0?" 等级 "+this.level+" ! ":"";
		return info;
	}
	//移除数组中的物品
	public boolean removeWuPin(int [][] wpArr){
		if(wpArr[wpRow][wpCol]==wpIndex){
			wpArr[wpRow][wpCol]=0;
			return true;
		}else{
			return false;
		}
	}
	//获得物品在数组中的信息
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
