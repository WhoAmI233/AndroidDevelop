package com.bdqn.hr.manager;

import com.bdqn.hr.activity.R;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;

public class SoundManager extends View {

	private static MediaPlayer mediaPlayerBg = null; // ��������
	private static MediaPlayer mediaPlayerYinXiao = null; // ��Ϸ��Ч

	// ��ʼ����Ϸ��Ч�����ṩget��set
	private static MediaPlayer actorgjMediaPlayer = null;// ��ҹ���
	private static MediaPlayer errorMediaPlayer = null;// ����
	private static MediaPlayer guaiwugjMediaPlayer = null;// ���﹥��
	private static MediaPlayer hdhpwpMediaPlayer = null;// ���ҩƷ
	private static MediaPlayer hdwupinMediaPlayer = null;// �����Ʒ
	private static MediaPlayer kaidoorMediaPlayer = null;// ����
	private static MediaPlayer sxloutiMediaPlayer = null;// ����¥��
	
	static Context context = null;
	public SoundManager(Context cont) {
		super(cont);
		context = cont;
		initSound();
	}
	//˽��
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
	 * ���ݴ��� ���� �жϵ���������Ч<br/>
	 * 1.��ҹ���	2.���﹥��  3.���� <br/>
	 *	4.���ҩƷ  5.�����Ʒ  6. ����  7.����¥��
	 * @param index	���Ͳ���
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
