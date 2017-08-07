package suprise.qiuguochao.com.ocr_keyboard.Typewriting;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.GeneralParams;
import com.baidu.ocr.sdk.model.GeneralResult;
import com.baidu.ocr.sdk.model.Word;

import java.io.File;

import suprise.qiuguochao.com.ocr_keyboard.R;

/**
 * Created by qiuguochao on 2017/2/6.
 */

public class Typewriting extends Activity {
    private static final int REQUEST_CODE_PICK_IMAGE = 101;
    private static final int REQUEST_CODE_CAMERA = 102;
    private static final String api_key = "tGfDu35HpgDKYnmejowD5HGb";
    private static final String secret_key = "GxrYByet96DWLx28Mxa5v6jVzf6WOQUt";
    private boolean flag;
    private static final String TAG = "TestActivity";
    private EditText edit_text;
    private AlertDialog.Builder alertDialog;
    private static CustomProgressDialog waitDialog;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.typewriting_activity);

        waitDialog=new CustomProgressDialog(this, "正在加载中",R.drawable.frame);
        alertDialog = new AlertDialog.Builder(this);
        Button btnStart = (Button) findViewById(R.id.btn_openTypewriting);
        Button btnStop = (Button) findViewById(R.id.btn_stopTypewriting);
        Button btn_ChoosePicture = (Button) findViewById(R.id.btn_ChoosePicture);
        edit_text = (EditText) findViewById(R.id.edit_text);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动service 方式2
                Intent i = new Intent(Typewriting.this, SoftKeyboard.class);
                startService(i);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //停止service 方式2
                Intent i = new Intent(Typewriting.this, SoftKeyboard.class);
                stopService(i);
            }
        });

        btn_ChoosePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ret = ActivityCompat.checkSelfPermission(Typewriting.this, Manifest.permission
                        .READ_EXTERNAL_STORAGE);
                if (ret != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Typewriting.this,
                            new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                            1000);
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
            }
        });


        initAccessTokenWithAkSk();
        Toast.makeText(Typewriting.this,
                "初始化完成", Toast.LENGTH_SHORT).show();
    }

    private void initAccessTokenWithAkSk() {
        OCR.getInstance().initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                String token = result.getAccessToken();
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
                //alertText("AK，SK方式获取token失败", error.getMessage());
                Toast.makeText(Typewriting.this,
                        "AK，SK方式获取token失败", Toast.LENGTH_SHORT).show();
            }
        }, getApplicationContext(), "tGfDu35HpgDKYnmejowD5HGb", "GxrYByet96DWLx28Mxa5v6jVzf6WOQUt");
    }

    private void alertText(String title, String message) {
        boolean isNeedLoop = false;
        if (Looper.myLooper() == null) {
            Looper.prepare();
            isNeedLoop = true;
        }
        alertDialog.setTitle(title)
                .setMessage(message)
                .setPositiveButton("确定", null)
                .show();
        if (isNeedLoop) {
            Looper.loop();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            String filePath = getRealPathFromURI(uri);
            recGeneral(filePath);
        }

        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            recGeneral(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath());
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    private void recGeneral(String filePath) {
        GeneralParams param = new GeneralParams();
        param.setDetectDirection(true);
        param.setImageFile(new File(filePath));
        waitDialog.show();
        OCR.getInstance().recognizeGeneral(param, new OnResultListener<GeneralResult>() {
            @Override
            public void onResult(GeneralResult result) {
                StringBuilder sb = new StringBuilder();
                for (Word word : result.getWordList()) {
                    sb.append(word.getWords());
                    sb.append("\n");
                }
                edit_text.setText(sb);
                waitDialog.cancel();
            }

            @Override
            public void onError(OCRError error) {
                edit_text.setText(error.getMessage());
            }
        });
    }

    //启动service 方式2
    //
//    private void bindService(){
//        Intent intent = new Intent(Typewriting.this,BindService.class);
//        Log.i(TAG, "bindService()");
//        bindService(intent, conn, Context.BIND_AUTO_CREATE);
//    }
//
//    private void unBindService(){
//        Log.i(TAG, "unBindService() start....");
//        if(flag == true){
//            Log.i(TAG, "unBindService() flag");
//            unbindService(conn);
//            flag = false;
//        }
//    }
//
//    private ServiceConnection conn = new ServiceConnection() {
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            // TODO Auto-generated method stub
//            Log.i(TAG, "onServiceDisconnected()");
//        }
//
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            // TODO Auto-generated method stub
//            Log.i(TAG, "onServiceConnected()");
//            MyBinder binder = (MyBinder)service;
//            BindService bindService = binder.getService1();
//            bindService.MyMethod();
//            flag = true;
//        }
//    };
}
