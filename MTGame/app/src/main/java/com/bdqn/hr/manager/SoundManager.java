package com.bdqn.hr.manager;

import com.bdqn.hr.activity.R;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;

public class SoundManager extends View {

	private static MediaPlayer mediaPlayerBg = null; // 背景音乐
	private static MediaPlayer mediaPlayerYinXiao = null; // 游戏音效

	// 初始化游戏音效、不提供get、set
	private static MediaPlayer actorgjMediaPlayer = null;// 玩家攻击
	private static MediaPlayer errorMediaPlayer = null;// 错误
	private static MediaPlayer guaiwugjMediaPlayer = null;// 怪物攻击
	private static MediaPlayer hdhpwpMediaPlayer = null;// 获得药品
	private static MediaPlayer hdwupinMediaPlayer = null;// 获得物品
	private static MediaPlayer kaidoorMediaPlayer = null;// 开门
	private static MediaPlayer sxloutiMediaPlayer = null;// 上下楼梯
	
	static Context context = null;
	public SoundManager(Context cont) {
		super(cont);
		context = cont;
		initSound();
	}
	//私有
	private static void initSound() {
			actorgjMediaPlayer = MediaPlayer.create(context, R.raw.actorgj);
			actorgjMediaPlayer.setLooping(false);
			errorMediaPlayer = MediaPlayer.create(context, R.raw.error);
			errorMediaPlayer.setLooping(false);
			guaiwugjMediaPlayer = MediaPlayer.create(context, R.raw.guaiwugj);
			guaiwugjMediaPlayer.setLooping(false);
			hdhpwpMediaPlayer = MediaPlayer.create(context, R.raw.hdhpwp);
			hdhpwpMediaPlayer.setLooping(false);
			hdwupinMediaPlayer = MediaPlayer.create(context, R.raw.hdwupin);
			hdwupinMediaPlayer.setLooping(false);
			kaidoorMediaPlayer = MediaPlayer.create(context, R.raw.kaidoor);
			kaidoorMediaPlayer.setLooping(false);
			sxloutiMediaPlayer = MediaPlayer.create(context, R.raw.sxlouti);
			sxloutiMediaPlayer.setLooping(false);
	}
	public static MediaPlayer getMediaPlayerBg() {
		if (mediaPlayerBg == null) {
			mediaPlayerBg = MediaPlayer.create(context, R.raw.backsound);
		}
		return mediaPlayerBg;
	}
	/***
	 * 根据传入 参数 判断调用哪种音效<br/>
	 * 1.玩家攻击	2.怪物攻击  3.错误 <br/>
	 *	4.获得药品  5.获得物品  6. 开门  7.上下楼梯
	 * @param index	类型参数
	 * @return
	 */
	public static MediaPlayer getMediaPlayerYinXiao(int index) {
		initSound();
		switch(index){
			case 1:
				mediaPlayerYinXiao=actorgjMediaPlayer;
				break;
			case 2:
				mediaPlayerYinXiao=guaiwugjMediaPlayer;
				break;
			case 3:
				mediaPlayerYinXiao=errorMediaPlayer;
				break;
			case 4:
				mediaPlayerYinXiao=hdhpwpMediaPlayer;
				break;
			case 5:
				mediaPlayerYinXiao=hdwupinMediaPlayer;
				break;
			case 6:
				mediaPlayerYinXiao=kaidoorMediaPlayer;
				break;
			case 7:
				mediaPlayerYinXiao=sxloutiMediaPlayer;
				break;
		}
		return mediaPlayerYinXiao;
	}

}
