package cn.hyf.notepad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

/**
 *  www.javaapk.com
 */
public class FileService {
	private Context context;
	private static final String path=Environment.getExternalStorageDirectory()+"/BianQian/";
	private static final String attrpath=path+"attr/";
	public FileService(Context context) {
		this.context=context;
		new File(path).mkdirs();
		new File(attrpath).mkdirs();
	}
	//ɾ�������ļ�
	public void deleteAttr(String filename) throws IOException {
		File file = new File(attrpath,
				filename);
		file.delete();
	}
	//��������ļ��Ĵ��
	public void saveToAttr(String filename,String filecontent)
	throws IOException{
		File file=new File(attrpath,filename);
		FileOutputStream fos = null;
		if (filename.equals(FileName.BIANQIANRECORDING)) {
			if(file.length()>102400){
				deleteAttr(filename);
				file=new File(attrpath,filename);
			}
			fos = new FileOutputStream(file, true);
		} else {
			fos = new FileOutputStream(file);
		}
		byte[] buffer = filecontent.getBytes();
		fos.write(buffer);
		fos.close();
	}
	//�����ļ��Ķ�ȡ
	public String readAttr(String filename) throws Exception {
		File file = new File(attrpath,
				filename);
		if(filename.equals(FileName.PASSWORD)&&file.exists()==false){
			saveToAttr(FileName.PASSWORD,"hyf");
		}
		FileInputStream fis = new FileInputStream(file);
		String filecontent = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		if(filename.equals(FileName.PASSWORD)) filecontent=br.readLine();
		else
		while (true) {
			String str = br.readLine();
			if (str == null)
				break;
			filecontent += str + "\n";
		}
		fis.close();
		return filecontent;
	}
	//ɾ����ǩ
	public void delete(String filename) throws IOException {
		File file = new File(path,
				filename);
		file.delete();
	}
	//��ǩ�Ĵ洢
	public boolean saveToSdCard(String filename, String filecontent)
			throws IOException {
		File[] files=new File(path).listFiles(); 
		for(File f:files){
			if(f.isFile()&&f.getName().equals(filename)){
				return false;
			}
		}
		File file = new File(path,filename);
		FileOutputStream fos = new FileOutputStream(file);
		byte[] buffer = filecontent.getBytes();
		fos.write(buffer);
		fos.close();
		return true;
	}
	//��ǩ�Ķ�ȡ
	public String readSdCard(String filename) throws Exception {
		File file = new File(path,
				filename);
		FileInputStream fis = new FileInputStream(file);
		String filecontent = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		while (true) {
			String str = br.readLine();
			if (str == null)
				break;
			filecontent += str + "\n";
		}
		fis.close();
		return filecontent;
	}
	//Ŀ¼�ļ��±�ǩ�б��Զ����뵽���ݿ�
	public void autoImport(){
		DBService dbservice=new DBService(context);
		List<Notepad> list=new ArrayList<Notepad>();
		File[] files=new File(path).listFiles();
		for(File f:files){
			String filename=f.getName();
			if(f.isFile()&&dbservice.find(filename)==null){
				String hidden="false";
				Notepad notepad=new Notepad(filename,hidden);
				dbservice.save(new Notepad(filename,hidden));
			}
		}
	}
}
