package suprise.qiuguochao.com.charm.Tomcat;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.tts.auth.AuthInfo;
import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import suprise.qiuguochao.com.charm.R;

import static suprise.qiuguochao.com.charm.Tomcat.ListItemData.RECEIVER;
import static suprise.qiuguochao.com.charm.Tomcat.ListItemData.SEND;

/**
 * Created by qiuguochao on 2017/1/9.
 */

public class Tomcat extends Activity implements HttpGetDataListener
{
//    private Button btn_choose;
//    private Button btn_changword;
//    private TextView textView_changeText;
//    private TextView textView_chooseName;
//    private ImageView backgroundImage;
//    private String imagePath="";
//    private static OcrClient ocr;
//    //调用系统相册-选择图片
//    private static final int SELECT_PIC_KITKAT = 1;
//    private static final int SELECT_PIC = 2;
    //1 是小度附身，2是小讯附身
    private int WhoUseTomcatStatus =0;

    private ImageView backgroundImage;
    private TextView textView_Baidu;
    private ImageView thinkGroundImage;
    private TextView textView_Think;
    private ImageView chatGroundImage;
    private TextView textView_WelcomeTip;
    private EditText editText_Speek;
    private ListView listView_TalkContent;
    private ImageView imageView_Speak;

    private String[] welcome_array;
    private String[] think_array;
    private List<ListItemData> mLists;
    private TextAdapter adapter;
    private ListItemData mListData;
    private String mWhatISay;
    private TuLing mTuLing;
    private String MyTuLingUrl = "http://www.tuling123.com/openapi/api?key=ed03301d41f84fa29bbd8558c8b356b3&info=";

    //下面是百度语音用到的
    private SpeechSynthesizer mSpeechSynthesizer;
    private String mSampleDirPath;
    private static final String SAMPLE_DIR_NAME = "baiduTTS";
    private static final String SPEECH_FEMALE_MODEL_NAME = "bd_etts_speech_female.dat";
    private static final String SPEECH_MALE_MODEL_NAME = "bd_etts_speech_male.dat";
    private static final String TEXT_MODEL_NAME = "bd_etts_text.dat";
    private static final String LICENSE_FILE_NAME = "temp_license";
    private static final String ENGLISH_SPEECH_FEMALE_MODEL_NAME = "bd_etts_speech_female_en.dat";
    private static final String ENGLISH_SPEECH_MALE_MODEL_NAME = "bd_etts_speech_male_en.dat";
    private static final String ENGLISH_TEXT_MODEL_NAME = "bd_etts_text_en.dat";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tomcat_activity);
//        testOcr();
        initActivity();
    }

    private void initialTts() {
        this.mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        this.mSpeechSynthesizer.setContext(this);
        this.mSpeechSynthesizer.setSpeechSynthesizerListener(new SpeechSynthesizerListener() {
            @Override
            public void onSynthesizeStart(String s) {

            }

            @Override
            public void onSynthesizeDataArrived(String s, byte[] bytes, int i) {

            }

            @Override
            public void onSynthesizeFinish(String s) {

            }

            @Override
            public void onSpeechStart(String s) {

            }

            @Override
            public void onSpeechProgressChanged(String s, int i) {

            }

            @Override
            public void onSpeechFinish(String s) {

            }

            @Override
            public void onError(String s, SpeechError speechError) {

            }
        });
        // 文本模型文件路径 (离线引擎使用)
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE, mSampleDirPath + "/"
                + TEXT_MODEL_NAME);
        // 声学模型文件路径 (离线引擎使用)
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE, mSampleDirPath + "/"
                + SPEECH_FEMALE_MODEL_NAME);
        // 本地授权文件路径,如未设置将使用默认路径.设置临时授权文件路径，LICENCE_FILE_NAME请替换成临时授权文件的实际路径，仅在使用临时license文件时需要进行设置，如果在[应用管理]中开通了正式离线授权，不需要设置该参数，建议将该行代码删除（离线引擎）
        // 如果合成结果出现临时授权文件将要到期的提示，说明使用了临时授权文件，请删除临时授权即可。
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_LICENCE_FILE, mSampleDirPath + "/"
                + LICENSE_FILE_NAME);
        // 请替换为语音开发者平台上注册应用得到的App ID (离线授权)
        this.mSpeechSynthesizer.setAppId("9318718"/*这里只是为了让Demo运行使用的APPID,请替换成自己的id。*/);
        // 请替换为语音开发者平台注册应用得到的apikey和secretkey (在线授权)
        this.mSpeechSynthesizer.setApiKey("D0TGR74EwicM8GFiv7zKOXfb",
                "3699ef9820fe33e358143ed7f7a761de"/*这里只是为了让Demo正常运行使用APIKey,请替换成自己的APIKey*/);
        // 发音人（在线引擎），可用参数为0,1,2,3。。。（服务器端会动态增加，各值含义参考文档，以文档说明为准。0--普通女声，1--普通男声，2--特别男声，3--情感男声。。。）
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "3");
        // 设置Mix模式的合成策略
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_MIX_MODE, SpeechSynthesizer.MIX_MODE_DEFAULT);
        // 授权检测接口(只是通过AuthInfo进行检验授权是否成功。)
        // AuthInfo接口用于测试开发者是否成功申请了在线或者离线授权，如果测试授权成功了，可以删除AuthInfo部分的代码（该接口首次验证时比较耗时），不会影响正常使用（合成使用时SDK内部会自动验证授权）
        AuthInfo authInfo = this.mSpeechSynthesizer.auth(TtsMode.MIX);

        if (authInfo.isSuccess()) {
            Toast.makeText(this, "语音合成授权成功", Toast.LENGTH_LONG).show();
        } else {
            String errorMsg = authInfo.getTtsError().getDetailMessage();
            Toast.makeText(this, "语音合成授权失败，错误码:" + errorMsg, Toast.LENGTH_LONG).show();
        }

        // 初始化tts
        mSpeechSynthesizer.initTts(TtsMode.MIX);
        // 加载离线英文资源（提供离线英文合成功能）
//        int result =
//                mSpeechSynthesizer.loadEnglishModel(mSampleDirPath + "/" + ENGLISH_TEXT_MODEL_NAME, mSampleDirPath
//                        + "/" + ENGLISH_SPEECH_FEMALE_MODEL_NAME);
        //Toast.makeText(this,"loadEnglishModel result=" + result,Toast.LENGTH_LONG).show();

        //打印引擎信息和model基本信息
        //printEngineInfo();
    }


    //下面都是百度语音合成的初始化设置，直接从demo里拷贝的
    private void initialEnv() {
        if (mSampleDirPath == null) {
            String sdcardPath = Environment.getExternalStorageDirectory().toString();
            mSampleDirPath = sdcardPath + "/" + SAMPLE_DIR_NAME;
        }
        makeDir(mSampleDirPath);
        copyFromAssetsToSdcard(false, SPEECH_FEMALE_MODEL_NAME, mSampleDirPath + "/" + SPEECH_FEMALE_MODEL_NAME);
        copyFromAssetsToSdcard(false, SPEECH_MALE_MODEL_NAME, mSampleDirPath + "/" + SPEECH_MALE_MODEL_NAME);
        copyFromAssetsToSdcard(false, TEXT_MODEL_NAME, mSampleDirPath + "/" + TEXT_MODEL_NAME);
        copyFromAssetsToSdcard(false, LICENSE_FILE_NAME, mSampleDirPath + "/" + LICENSE_FILE_NAME);
        copyFromAssetsToSdcard(false, "english/" + ENGLISH_SPEECH_FEMALE_MODEL_NAME, mSampleDirPath + "/"
                + ENGLISH_SPEECH_FEMALE_MODEL_NAME);
        copyFromAssetsToSdcard(false, "english/" + ENGLISH_SPEECH_MALE_MODEL_NAME, mSampleDirPath + "/"
                + ENGLISH_SPEECH_MALE_MODEL_NAME);
        copyFromAssetsToSdcard(false, "english/" + ENGLISH_TEXT_MODEL_NAME, mSampleDirPath + "/"
                + ENGLISH_TEXT_MODEL_NAME);
    }

    private void makeDir(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 将sample工程需要的资源文件拷贝到SD卡中使用（授权文件为临时授权文件，请注册正式授权）
     *
     * @param isCover 是否覆盖已存在的目标文件
     * @param source
     * @param dest
     */
    private void copyFromAssetsToSdcard(boolean isCover, String source, String dest) {
        File file = new File(dest);
        if (isCover || (!isCover && !file.exists())) {
            InputStream is = null;
            FileOutputStream fos = null;
            try {
                is = getResources().getAssets().open(source);
                String path = dest;
                fos = new FileOutputStream(path);
                byte[] buffer = new byte[1024];
                int size = 0;
                while ((size = is.read(buffer, 0, 1024)) >= 0) {
                    fos.write(buffer, 0, size);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initActivity() {
        textView_Baidu=(TextView) findViewById(R.id.textView_Baidu);
        chatGroundImage=(ImageView) findViewById(R.id.chatGroundImage);
        textView_Think=(TextView) findViewById(R.id.textView_Think);
        thinkGroundImage=(ImageView) findViewById(R.id.thinkGroundImage);
        textView_WelcomeTip =(TextView) findViewById(R.id.textView_WelcomeTip);
        editText_Speek =(EditText) findViewById(R.id.editText_Speek);
        listView_TalkContent=(ListView) findViewById(R.id.listView_TalkContent);
        backgroundImage = (ImageView) findViewById(R.id.backgroundImage);
        imageView_Speak = (ImageView) findViewById(R.id.imageView_Speak);



        final AnimationDrawable background = (AnimationDrawable) backgroundImage.getBackground();
        textView_Think.setTextColor(Color.WHITE);

        imageView_Speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开启语音识别
                Intent intent = new Intent("com.baidu.action.RECOGNIZE_SPEECH");
                intent.putExtra("grammar", "asset:///baidu_speech_grammardemo.bsg"); // 设置离线的授权文件(离线模块需要授权), 该语法可以用自定义语义工具生成, 链接http://yuyin.baidu.com/asr#m5
                startActivityForResult(intent, 1);
            }
        });

        textView_Baidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(WhoUseTomcatStatus != 1)
                {
                    String welcomeTip = getRandomWelcomeTips();
                    displayWelcomeTip(welcomeTip);
                    initTalkToCat(welcomeTip);
                    initialEnv();
                    initialTts();
                    speak(mListData.getContent());
                }
                WhoUseTomcatStatus = 1;
            }
        });

        editText_Speek.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    mWhatISay = editText_Speek.getText().toString().replace("\n", "");
                    refresh(mWhatISay,SEND);
                    editText_Speek.setText("");
                    chatGroundImage.setVisibility(View.INVISIBLE);
                    textView_WelcomeTip.setVisibility(View.INVISIBLE);
                    chatWithTuling();
                    displayThinkTip(getRandomThinkTips());
                }
                return false;
            }
        });

        backgroundImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                // 停止上一次的动画，然后开始播放
                background.stop();
                background.start();
            }
        });
    }

    //语音识别返回的结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bundle results = data.getExtras();
            ArrayList<String> results_recognition = results.getStringArrayList("results_recognition");

            //将数组形式的识别结果变为正常的String类型，例：[给张三打电话]变成给张三打电话
            String str = results_recognition + "";
            mWhatISay = str.substring(str.indexOf("[") + 1, str.indexOf("]"));

            refresh(mWhatISay, SEND);
            //如果语音识别的结果没有以上关键词，那么执行聊天机器人的功能
            chatWithTuling();
        }
    }

    //语音合成
    private void speak(String text) {
//        String text = this.mInput.getText().toString();
        //需要合成的文本text的长度不能超过1024个GBK字节。
//        if (TextUtils.isEmpty(mInput.getText())) {
//            text = "欢迎使用百度语音合成SDK,百度语音为你提供支持。";
//            mInput.setText(text);
//        }
        int result = this.mSpeechSynthesizer.speak(text);
        if (result < 0) {
            Toast.makeText(this, "error,please look up error code in doc or URL:http://yuyin.baidu.com/docs/tts/122 ", Toast.LENGTH_LONG).show();
        }
    }

    //获取图灵机器人返回的数据
    @Override
    public void getDataUrl(String data) {
        thinkGroundImage.setVisibility(View.INVISIBLE);
        textView_Think.setVisibility(View.INVISIBLE);
        parseText(data);
    }

    //解析数据
    public void parseText(String str) {
        try {
            JSONObject jb = new JSONObject(str);

            refresh(jb.getString("text"), RECEIVER);
            chatGroundImage.setVisibility(View.VISIBLE);
            textView_WelcomeTip.setVisibility(View.VISIBLE);
            textView_WelcomeTip.setText(jb.getString("text"));
            //语音合成返回的结果
            speak(mListData.getContent());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //聊天机器人
    private void chatWithTuling() {
        if (!isNetworkConnect(this)){
            AlertDialog alertDialog =  new AlertDialog.Builder(getApplicationContext()).setTitle("想调戏我吗？").setMessage("赶紧开流量")
                    .setPositiveButton("遵命", null).create();
            alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            alertDialog.show();
            return;
        }
        // 去掉空格
        String dropk = mWhatISay.replace(" ", "");
        // 去掉回车
        String droph = dropk.replace("\n", "");
        //api_key请换成自己图灵机器人的api_key
        mTuLing = (TuLing) new TuLing(MyTuLingUrl + droph, this).execute();
    }

    //刷新页面
    private void refresh(String content,int flag) {
        mListData = new ListItemData(content, flag);
        mLists.add(mListData);
        //如果item数量大于30，清空数据
        if (mLists.size() > 30) {
            for (int i = 0; i < mLists.size(); i++) {
                // 移除数据
                mLists.remove(i);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void initTalkToCat(String welcomeTip) {
        editText_Speek.setVisibility(View.VISIBLE);
        listView_TalkContent.setVisibility(View.VISIBLE);
        imageView_Speak.setVisibility(View.VISIBLE);
        mLists = new ArrayList<ListItemData>();
        adapter = new TextAdapter(mLists, this);
        listView_TalkContent.setAdapter(adapter);
        refresh(welcomeTip,RECEIVER);
    }

    private void displayWelcomeTip(String welcomeTip) {
        chatGroundImage.setVisibility(View.VISIBLE);
        textView_WelcomeTip.setVisibility(View.VISIBLE);
        textView_WelcomeTip.setText(welcomeTip);
    }

    private void displayThinkTip(String thinkTip) {
        thinkGroundImage.setVisibility(View.VISIBLE);
        textView_Think.setVisibility(View.VISIBLE);
        textView_Think.setText(thinkTip);
    }

    //用户第一次进入，随机获取欢迎语
    private String getRandomWelcomeTips() {
        String welcome_tip = null;
        welcome_array = this.getResources()
                .getStringArray(R.array.welcome_tips);
        int index = (int) (Math.random() * (welcome_array.length - 1));
        welcome_tip = welcome_array[index];
        return welcome_tip;
    }

    private String getRandomThinkTips() {
        String think_tip = null;
        think_array = this.getResources()
                .getStringArray(R.array.think_tips);
        int index = (int) (Math.random() * (think_array.length - 1));
        think_tip = think_array[index];
        return think_tip;
    }

    //判断网络是否连接
    private boolean isNetworkConnect(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        Log.d("tag",networkInfo+"");

        boolean isNetworkAvailable;
        if (networkInfo==null){
            isNetworkAvailable=false;
        }else {
            isNetworkAvailable=true;
        }
        Log.d("tag",isNetworkAvailable+"");
        return isNetworkAvailable;
    }








//    private void testOcr()
//    {
//        backgroundImage = (ImageView) findViewById(R.id.backgroundImage);
//        btn_choose = (Button) findViewById(R.id.btn_choose);
//        btn_changword= (Button) findViewById(R.id.btn_changword);
//        textView_changeText=(TextView) findViewById(R.id.textView_changeText);
//        textView_chooseName=(TextView) findViewById(R.id.textView_chooseName);
//
//        final AnimationDrawable background = (AnimationDrawable) backgroundImage.getBackground();
//        backgroundImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                // 停止上一次的动画，然后开始播放
//                background.stop();
//                background.start();
//                textView_changeText.setCursorVisible(false);
//                textView_changeText.setText("");
//                textView_chooseName.setText("");
//                btn_changword.setVisibility(View.VISIBLE);
//            }
//        });
//
//        btn_choose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //调用相册
////                Intent intent = new Intent(Intent.ACTION_PICK,
////                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
////                startActivityForResult(intent, IMAGE);
//
////                Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
////                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
////                startActivityForResult(intent, IMAGE);
//
////                Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
////                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
////                startActivityForResult(intent, SELECT_PIC_KITKAT);
//
//                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);//ACTION_OPEN_DOCUMENT
//                intent.addCategory(Intent.CATEGORY_OPENABLE);
//                intent.setType("image/jpeg");
//                if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.KITKAT){
//                    startActivityForResult(intent, SELECT_PIC_KITKAT);
//                }else{
//                    startActivityForResult(intent, SELECT_PIC);
//                }
//            }
//
//        });
//
//        btn_changword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //获取图片中的文字
//                if(imagePath.isEmpty() == false) {
//                    String text = getTextFromImage(imagePath);
//                    textView_changeText.setCursorVisible(true);
//                    textView_changeText.setText(text);
//                    btn_changword.setVisibility(View.GONE);
//                }
//            }
//        });
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        //获取图片路径
//        if ( data != null) {
////        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
////            Uri selectedImage = data.getData();
////            String[] filePathColumns = {MediaStore.Images.Media.DATA};
////            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
////            c.moveToFirst();
////            int columnIndex = c.getColumnIndex(filePathColumns[0]);
////            imagePath = c.getString(columnIndex);
////            c.close();
////            try {
////
////                Uri uri = data.getData();
////                String[] proj = { MediaStore.Images.Media.DATA };
////                Cursor actualimagecursor = managedQuery(uri,proj,null,null,null);
////                int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
////                actualimagecursor.moveToFirst();
////                imagePath = actualimagecursor.getString(actual_image_column_index);
////            }catch (Exception e) {
////                e.printStackTrace();
////            }
//            imagePath = getImageAbsolutePath(this,data.getData());
//            textView_chooseName.setText(imagePath);
//        }
//    }
//    //加载图片
//    private String getTextFromImage(String imgPath){
//        String text = ocr.getTextFromImage(imgPath);
//        return text;
//    }
//
//    public String getImageAbsolutePath(Activity context, Uri imageUri) {
//        if (context == null || imageUri == null)
//            return null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, imageUri)) {
//            if (isExternalStorageDocument(imageUri)) {
//                String docId = DocumentsContract.getDocumentId(imageUri);
//                String[] split = docId.split(":");
//                String type = split[0];
//                if ("primary".equalsIgnoreCase(type)) {
//                    return Environment.getExternalStorageDirectory() + "/" + split[1];
//                }
//            } else if (isDownloadsDocument(imageUri)) {
//                String id = DocumentsContract.getDocumentId(imageUri);
//                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
//                return getDataColumn(context, contentUri, null, null);
//            } else if (isMediaDocument(imageUri)) {
//                String docId = DocumentsContract.getDocumentId(imageUri);
//                String[] split = docId.split(":");
//                String type = split[0];
//                Uri contentUri = null;
//                if ("image".equals(type)) {
//                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//                } else if ("video".equals(type)) {
//                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
//                } else if ("audio".equals(type)) {
//                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//                }
//                String selection = MediaStore.Images.Media._ID + "=?";
//                String[] selectionArgs = new String[] { split[1] };
//                return getDataColumn(context, contentUri, selection, selectionArgs);
//            }
//        } // MediaStore (and general)
//        else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
//            // Return the remote address
//            if (isGooglePhotosUri(imageUri))
//                return imageUri.getLastPathSegment();
//            return getDataColumn(context, imageUri, null, null);
//        }
//        // File
//        else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
//            return imageUri.getPath();
//        }
//        return null;
//    }
//
//    public String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
//        Cursor cursor = null;
//        String column = MediaStore.Images.Media.DATA;
//        String[] projection = { column };
//        try {
//            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
//            if (cursor != null && cursor.moveToFirst()) {
//                int index = cursor.getColumnIndexOrThrow(column);
//                return cursor.getString(index);
//            }
//        } finally {
//            if (cursor != null)
//                cursor.close();
//        }
//        return null;
//    }
//
//    /**
//     * @param uri The Uri to check.
//     * @return Whether the Uri authority is ExternalStorageProvider.
//     */
//    public boolean isExternalStorageDocument(Uri uri) {
//        return "com.android.externalstorage.documents".equals(uri.getAuthority());
//    }
//
//    /**
//     * @param uri The Uri to check.
//     * @return Whether the Uri authority is DownloadsProvider.
//     */
//    public boolean isDownloadsDocument(Uri uri) {
//        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
//    }
//
//    /**
//     * @param uri The Uri to check.
//     * @return Whether the Uri authority is MediaProvider.
//     */
//    public boolean isMediaDocument(Uri uri) {
//        return "com.android.providers.media.documents".equals(uri.getAuthority());
//    }
//
//    /**
//     * @param uri The Uri to check.
//     * @return Whether the Uri authority is Google Photos.
//     */
//    public boolean isGooglePhotosUri(Uri uri) {
//        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
//    }
}
