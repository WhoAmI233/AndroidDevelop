package cn.hyf.notepad;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 *  www.javaapk.com
 */
public class EditActivity extends Activity {
	private String filename;
	private String filecontent;
	private FileService fservice;
	DBService dbservice;
	String hidden="false";
	private final int NAME_LENGTH = 17;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit);
		fservice = new FileService(this);
		dbservice=new DBService(this);
		Intent intent = getIntent();
		filename = intent.getStringExtra("name");
		if (filename != null)
			show();

	}

	public void saveNotepad(View v) {
		if (filename != null)
			{deleteNotepad(v);
			}	
		filecontent = ((EditText) this.findViewById(R.id.editText)).getText()
				.toString();
		if(filecontent.equals("")){
			Toast.makeText(getApplicationContext(),"���ݲ���Ϊ��", Toast.LENGTH_SHORT)
			.show();
		}else{
		if (filecontent.length() < NAME_LENGTH)
			{filename = filecontent;}
		else
			{filename = filecontent.substring(0, NAME_LENGTH);}
		
		if (filename.contains("\n"))
			filename = filename.substring(0, filename.indexOf("\n"));	
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy��MM��dd�� EEE HH:mm:ss");
		filecontent+="\r\n[������]"+sdf.format(date);
		// �����ļ���sd��
		boolean b=true;
		try {
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				b=fservice.saveToSdCard(filename, filecontent);
			} else {
				Toast.makeText(getApplicationContext(), "sd�������ڻ�д����",
						Toast.LENGTH_SHORT).show();
			}
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "����ʧ��", Toast.LENGTH_SHORT)
					.show();
			e.printStackTrace();
		}
		if(b){
			//��ӵ����ݿ�
			dbservice.save(new Notepad(filename,hidden));
			this.finish();
		}else {
			Toast.makeText(this, "�ļ��Ѿ�����,��������", Toast.LENGTH_SHORT).show();
			filename=null;
		}
		}
	}

	public void deleteNotepad(View v) {
		if (filename != null) {
			try {
				fservice.delete(filename);
			} catch (IOException e) {
				e.printStackTrace();
			}
			hidden=dbservice.delete(filename).getHidden();
			this.finish();
		}
	}
	//�򿪼��£���ʾ����
	public void show() {
		// ��ʾ�ļ�
		String filecontent = "";
		EditText fcontentText = (EditText) findViewById(R.id.editText);
		try {
			if(filename.startsWith("������")){
				filename=filename.substring(3);
			}
			filecontent = fservice.readSdCard(filename);
			fcontentText.setText(filecontent);
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "��ȡʧ��", Toast.LENGTH_SHORT)
					.show();
			e.printStackTrace();
		}
	}
	
	public boolean onCreateOptionsMenu(Menu menu){
		if(filename!=null){
		String hidden=dbservice.find(filename).getHidden();
		if(hidden.equals("true"))
			menu.add(0,0,0,"ȡ������");
		else menu.add(0,0,0,"����");
		menu.add(0,1,1,"����");
		}
		return super.onCreateOptionsMenu(menu);
	}
	public boolean onOptionsItemSelected(MenuItem item){
		super.onOptionsItemSelected(item);
		switch(item.getItemId()){
		case 0:
			if(item.getTitle().equals("����")){
			dbservice.update(new Notepad(filename,"true"));
			Toast.makeText(getApplicationContext(), "���سɹ�", Toast.LENGTH_SHORT)
			.show();
			item.setTitle("ȡ������");
			}else {
				dbservice.update(new Notepad(filename,"false"));
				Toast.makeText(getApplicationContext(), "��ȡ������", Toast.LENGTH_SHORT)
				.show();
				item.setTitle("����");
			}
			break;
		case 1:
			finish();
			break;
		}
		return true;
	}
}
