package suprise.qiuguochao.com.charm.HomePage;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import suprise.qiuguochao.com.charm.Algorithm.AlgorithmWizard;
import suprise.qiuguochao.com.charm.FortuneBigWheel.FortuneBigWheel_Choose;
import suprise.qiuguochao.com.charm.Layout.CircleMenuLayout;
import suprise.qiuguochao.com.charm.R;
import suprise.qiuguochao.com.charm.SQLiteHelper.SQLTest_Activity;
import suprise.qiuguochao.com.charm.Sudoku_game.Sudoku_game;
import suprise.qiuguochao.com.charm.SuspendedWindow.SuspendedWindowBigView;
import suprise.qiuguochao.com.charm.SuspendedWindow.SuspendedWindowService;
import suprise.qiuguochao.com.charm.Tomcat.Tomcat;
import suprise.qiuguochao.com.charm.Typewriting.Typewriting;

/**
 * Created by qiuguochao on 2017/1/6.
 */

public class HomePage extends Activity {

    final int REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    final int REQUEST_READ_EXTERNAL_STORAGE = 2;
    final int REQUEST_INTERNET_EXTERNAL_STORAGE = 3;
    final int REQUEST_CAMERA_EXTERNAL_STORAGE = 4;
    final int REQUEST_SYSTEM_ALERT_WINDOW_EXTERNAL_STORAGE = 5;
    final int REQUEST_GET_TASKS_EXTERNAL_STORAGE = 6;
    private CircleMenuLayout mCircleMenuLayout;

    private String[] mItemTexts = new String[] { "寻求数独", "召唤汤姆猫", "见证人品",
            "书写人生","探根究底","万物规律"};
    private int[] mItemImgs = new int[] { R.drawable.circle_suduku,
            R.drawable.circle_tomcat, R.drawable.circle_fortunewheel,
            R.drawable.typewriting_icon,R.drawable.circle_algorithm,R.drawable.circle_sqlite};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        checkPermission();

        super.onCreate(savedInstanceState);

        //自已切换布局文件看效果
        setContentView(R.layout.homepage_activity);
//		setContentView(R.layout.activity_main);

        mCircleMenuLayout = (CircleMenuLayout) findViewById(R.id.id_menulayout);
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);



        mCircleMenuLayout.setOnMenuItemClickListener(new CircleMenuLayout.OnMenuItemClickListener()
        {

            @Override
            public void itemClick(View view, int pos)
            {
                Intent i = null;
                if(mItemTexts[pos] == "寻求数独") {
                    i = new Intent(HomePage.this, Sudoku_game.class);
                }
                else if(mItemTexts[pos] == "召唤汤姆猫") {
                    i = new Intent(HomePage.this, Tomcat.class);
                }
                else if(mItemTexts[pos] == "见证人品") {
                    i = new Intent(HomePage.this, FortuneBigWheel_Choose.class);
                }
                else if(mItemTexts[pos] == "书写人生") {
                    i = new Intent(HomePage.this, Typewriting.class);
                }
                else if(mItemTexts[pos] == "探根究底") {
                    i = new Intent(HomePage.this, AlgorithmWizard.class);
                }
                else if(mItemTexts[pos] == "万物规律") {
                    i = new Intent(HomePage.this, SQLTest_Activity.class);
                }
                startActivity(i);
            }

            @Override
            public void itemCenterClick(View view)
            {
                Intent intent = new Intent(HomePage.this, SuspendedWindowService.class);
                startService(intent);
                finish();
            }
        });

    }

    private void checkPermission() {
        // Storage Permissions   huawei
//        String[] PERMISSIONS_STORAGE = {
//                Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.CAMERA
//        };
// Check if we have write permission
        int Write_Permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int Read_Permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int Internet_Permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        int Camera_Permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int Alert_Permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.SYSTEM_ALERT_WINDOW);
        int Task_Permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.GET_TASKS);
        if (Write_Permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_EXTERNAL_STORAGE
            );
        }
        if (Read_Permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_READ_EXTERNAL_STORAGE
            );
        }
        if (Internet_Permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.INTERNET},
                    REQUEST_INTERNET_EXTERNAL_STORAGE
            );
        }
        if (Camera_Permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA_EXTERNAL_STORAGE
            );
        }
        if (Alert_Permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW},
                    REQUEST_SYSTEM_ALERT_WINDOW_EXTERNAL_STORAGE
            );
        }
        if (Task_Permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.GET_TASKS},
                    REQUEST_GET_TASKS_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_EXTERNAL_STORAGE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                    AskForPermission(REQUEST_CAMERA_EXTERNAL_STORAGE);
                }
            }
        }
        else if(requestCode == REQUEST_GET_TASKS_EXTERNAL_STORAGE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.GET_TASKS)) {
                    AskForPermission(REQUEST_GET_TASKS_EXTERNAL_STORAGE);
                }
            }
        }
        else if(requestCode == REQUEST_INTERNET_EXTERNAL_STORAGE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.INTERNET)) {
                    AskForPermission(REQUEST_INTERNET_EXTERNAL_STORAGE);
                }
            }
        }
        else if(requestCode == REQUEST_READ_EXTERNAL_STORAGE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AskForPermission(REQUEST_READ_EXTERNAL_STORAGE);
                }
            }
        }
        else if(requestCode == REQUEST_SYSTEM_ALERT_WINDOW_EXTERNAL_STORAGE) {
            Toast.makeText(HomePage.this,
                    "如果运行异常,请确保悬浮窗权限已设置", Toast.LENGTH_SHORT).show();
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                if (!shouldShowRequestPermissionRationale(Manifest.permission.SYSTEM_ALERT_WINDOW)) {
//                    AskForPermission(REQUEST_SYSTEM_ALERT_WINDOW_EXTERNAL_STORAGE);
//                }
//            }
        }
        else if(requestCode == REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    AskForPermission(REQUEST_WRITE_EXTERNAL_STORAGE);
                }
            }
        }
    }
    private void AskForPermission(int index) {
        String tempTitle = "需要权限才能运行";
        if(REQUEST_WRITE_EXTERNAL_STORAGE == index || REQUEST_READ_EXTERNAL_STORAGE == index){
            tempTitle="请设置读取SD卡权限";
        }
        else if(REQUEST_INTERNET_EXTERNAL_STORAGE ==index) {
            tempTitle="请设置网络访问权限";
        }
        else if(REQUEST_CAMERA_EXTERNAL_STORAGE ==index) {
            tempTitle="请设置访问相机权限";
        }
        else if(REQUEST_SYSTEM_ALERT_WINDOW_EXTERNAL_STORAGE ==index) {
            tempTitle="请设置悬浮窗权限";
        }
        else if(REQUEST_GET_TASKS_EXTERNAL_STORAGE ==index) {
            tempTitle="请设置后台任务权限";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(tempTitle);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    finish();
            }
        });
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName())); // 根据包名打开对应的设置界面
                startActivity(intent);
            }
        });
        builder.create().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER);
        builder.create().show();
    }
}
