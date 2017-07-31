package cn.hyf.notepad;
/**
 * 1.4: ���Ӱ��Ĺ���
 * 1.3���������ݿ⣬��������ĳһ��ǩ����ʾ��ǩ,���������
 * 1.2�����ӽ�BianQian�ļ����µı�ǩ���Զ������б�������ΪautoImport��
 */
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

/**
 *  www.javaapk.com
 */
public class FileListActivity extends Activity {
	private ListView listView;
	FileService fservice;
	DBService dbservice;
	boolean displayAll=false;
	boolean pass=false;
	int pass_count=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);    
        fservice=new FileService(this);
        dbservice=new DBService(this);
        OpenRecording();   
        listView = (ListView) this.findViewById(R.id.listView);
		listView.setOnItemClickListener(new ItemClickListener());
        show();
    }
    //listView�� ����¼�
	private final class ItemClickListener implements OnItemClickListener{

		//@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			ListView lView=(ListView)parent;
			HashMap<String,Object> notepad=(HashMap<String,Object>)lView.getItemAtPosition(position);
			String filename=notepad.get("name").toString();
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy��MM��dd�� EEE HH:mm:ss");
			String filecontent=sdf.format(date)+"\t[�����] "+filename+"\r\n";
			try {
				fservice.saveToAttr(FileName.BIANQIANRECORDING, filecontent);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			openNotepad(filename);
		}	
	}
	//�����ݿ�ӳ����б�����
	private void show() {
		fservice.autoImport();
		List<Notepad> notepads = dbservice.getScrollDate(0, 20,displayAll);
		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		for(Notepad notepad:notepads){
			HashMap<String,Object> item=new HashMap<String, Object>();
			item.put("name",notepad.getName());
			data.add(item);
		}
		SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item,
				new String[] { "name" }, new int[] {
						R.id.name});
		listView.setAdapter(adapter);
	}
	//�½�����
    public void newNotepad(View v){
		Intent intent=new Intent(this,EditActivity.class);
		startActivity(intent);
    }
    //�򿪼���
    public void openNotepad(String name){
		Intent intent=new Intent(this,EditActivity.class);
		intent.putExtra("name", name);
		startActivityForResult(intent, 100);
    }
    //activity��ǰ̨ʱ
	@Override
	protected void onResume() {
		show();
		super.onResume();
	}
	//���������¼����
	private void OpenRecording() {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy��MM��dd�� EEE HH:mm:ss");
		String filecontent="=================================================================\r\n"
			+sdf.format(date)+"\t[���������]\r\n";
		try {
			String path=Environment.getExternalStorageDirectory()+"/BianQian/";
			File recordFile=new File(path+"attr/"+FileName.BIANQIANRECORDING);
			if(!recordFile.exists()){
				File helpFile=new File(path+"�����ļ�");
				if(helpFile.exists()) helpFile.delete();
				fservice.saveToSdCard("�����ļ�", getString(R.string.help));
			}
			fservice.saveToAttr(FileName.BIANQIANRECORDING, filecontent);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(Camera.getNumberOfCameras()>1)
		{
		Intent intent=new Intent(FileListActivity.this,HiddenCamera.class);
		startActivity(intent);
		}
		//www.javaapk.com
	}
	//��ʾȫ���ļ����������صġ�
	public boolean onCreateOptionsMenu(Menu menu){
		menu.add(0,0,0,"��ʾȫ��");
		menu.add(0,1,1,"�˳�");
		menu.add(0,2,2,"��������");
		return super.onCreateOptionsMenu(menu);
	}
	public boolean onOptionsItemSelected(MenuItem item){
		super.onOptionsItemSelected(item);
		switch(item.getItemId()){
		case 0:
			if(item.getTitle().equals("��ʾȫ��")){
				if(pass_count==0){
				Intent intent=new Intent(FileListActivity.this,PasswordActivity.class);
				startActivityForResult(intent,0);
				}
				if (pass) {
					displayAll = true;
					FileListActivity.this.onResume();
					item.setTitle("ȡ����ʾȫ��");
				}
			}else {
				pass_count=0;
				pass=false;
				displayAll=false;
				FileListActivity.this.onResume();
				item.setTitle("��ʾȫ��");
			}
			
			break;
		case 1:
			finish();
			break;
		case 2:
			Intent intent=new Intent(FileListActivity.this,SetPasswordActivity.class);
			startActivity(intent);
			break;
		}
		return true;
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(resultCode){
		case RESULT_OK:
			Bundle bundle=data.getExtras();
			pass=bundle.getBoolean("pass");
			pass_count++;
			Toast.makeText(this, "������ȷ�����ٴε����ʾȫ��", Toast.LENGTH_SHORT).show();
		default:
			break;
		}
	}
}