package suprise.qiuguochao.com.charm.SuspendedWindow;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputBinding;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.joda.time.convert.Converter;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import suprise.qiuguochao.com.charm.R;
import suprise.qiuguochao.com.charm.SQLiteHelper.DBManager;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by qiuguochao on 2017/2/17.
 */

public class SuspendedWindowBigView extends LinearLayout {

    /**
     * 记录大悬浮窗的宽度
     */
    public static int viewWidth;

    /**
     * 记录大悬浮窗的高度
     */
    public static int viewHeight;

    private static final String DB_NAME = "Charm.db";
    private static final int DB_VERSION = 1;
    private DBManager dbManager;
    private static final  int mPasteNum = 5;
    private static ArrayList<String> mCopyStrList = new ArrayList<String>(mPasteNum);
    private static ArrayList<String> hsList = new ArrayList<String>();
    public static GetClipboard mClipboard =null;

    public SuspendedWindowBigView(final Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.suspended_window_big, this);
        View view = findViewById(R.id.big_window_layout);
        viewWidth = view.getLayoutParams().width;
        viewHeight = view.getLayoutParams().height;
        Button close = (Button) findViewById(R.id.close);
        Button back = (Button) findViewById(R.id.back);
        Button paste = (Button) findViewById(R.id.paste);
        Button checkAllCopyRecord = (Button) findViewById(R.id.checkAllCopyRecord);
        Button clearAllRecord = (Button) findViewById(R.id.clearAllRecord);
        dbManager = new DBManager(getContext(), DB_NAME, DB_VERSION, HistoryRecord.class);
//        dbManager.closeDataBase();

        close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击关闭悬浮窗的时候，移除所有悬浮窗，并停止Service
                SuspendedWindowManager.removeBigWindow(context);
                SuspendedWindowManager.removeSmallWindow(context);
                Intent intent = new Intent(getContext(), SuspendedWindowService.class);
                context.stopService(intent);
            }
        });
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击返回的时候，移除大悬浮窗，创建小悬浮窗
                SuspendedWindowManager.removeBigWindow(context);
                SuspendedWindowManager.createSmallWindow(context);
            }
        });
        paste.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( mCopyStrList.size() == 0) {
                    Toast.makeText(getContext(),
                            "请选择要复制的内容", Toast.LENGTH_SHORT).show();
                    SuspendedWindowManager.removeBigWindow(context);
                    SuspendedWindowManager.createSmallWindow(context);
                    return;
                }
                ShowChoise();
            }
        });

        checkAllCopyRecord.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                List<HistoryRecord> HistoryRecord = dbManager.findAll(HistoryRecord.class);
                if ( HistoryRecord.size() == 0) {
                    Toast.makeText(getContext(),
                            "不存在历史记录", Toast.LENGTH_SHORT).show();
                    SuspendedWindowManager.removeBigWindow(context);
                    SuspendedWindowManager.createSmallWindow(context);
                    return;
                }
                ShowHistoryRecord(HistoryRecord);
            }
        });

        clearAllRecord.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.clearTable(HistoryRecord.class);
                Toast.makeText(getContext(),
                        "历史记录已清空", Toast.LENGTH_SHORT).show();
                SuspendedWindowManager.removeBigWindow(context);
                SuspendedWindowManager.createSmallWindow(context);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuspendedWindowManager.removeBigWindow(context);
                SuspendedWindowManager.createSmallWindow(context);
            }

        });
    }


    private void ShowChoise() {
        //    指定下拉列表的显示数据
        final String[] cities = (String[]) mCopyStrList.toArray(new String[0]);
        AlertDialog alertDialog =  new AlertDialog.Builder(getContext()).setTitle("选择要粘贴的内容").setItems(
                cities, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                            mClipboard.PasteToClipboard(which,mCopyStrList);
                    }
                }
        ).create();
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();
    }

    private void ShowHistoryRecord(List<HistoryRecord> historyRecord) {
        hsList.clear();
        for (HistoryRecord hs : historyRecord) {
            hsList.add(0,hs.record);
        }
        final String[] record = (String[]) hsList.toArray(new String[0]);
        AlertDialog alertDialog =  new AlertDialog.Builder(getContext()).setTitle("选择要粘贴的内容").setItems(
                record, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        mClipboard.PasteToClipboard(which,hsList);
                    }
                }
        ).create();
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();
    }

    public class GetClipboard extends ContextWrapper {
        //该属性指向一个ContextIml实例，一般在创建Application、Service、Activity时赋值
        private Context mBase;

        //mBase赋值方式同样有一下两种
        public GetClipboard(Context base) {
            super(base);
            mBase = base;
        }

        @Override
        protected void attachBaseContext(Context newBase) {
            super.attachBaseContext(newBase);
            mBase = newBase;
        }

        public void init() {
            final ClipboardManager cm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            cm.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
                @Override
                public void onPrimaryClipChanged() {
                    ClipData data = cm.getPrimaryClip();
                    ClipData.Item item = data.getItemAt(0);
                    String copyStr = item.getText().toString();
                    mCopyListAdd(copyStr);
                }
            });
        }

        private void mCopyListAdd(String copyStr) {
            if(mCopyStrList.contains(copyStr) == true)
                return;
            if(mCopyStrList.size() == mPasteNum)
            {
                mCopyStrList.remove(mPasteNum-1);
            }
            mCopyStrList.add(0,copyStr);
            SimpleDateFormat formatter = new SimpleDateFormat ("yyyy年MM月dd日 HH:mm:ss ");
            Date curDate = new Date(System.currentTimeMillis());
            String nowTime = formatter.format(curDate);
            HistoryRecord record = new HistoryRecord(copyStr,nowTime);
            dbManager.insert(record);
        }

        protected void PasteToClipboard(int selectIndex,ArrayList<String> copyList) {
            final ClipboardManager cm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            if (!cm.hasPrimaryClip()) {
                Toast.makeText(GetClipboard.this,
                        "Clipboard is empty", Toast.LENGTH_SHORT).show();
            }
            else {
                ClipData clipData = ClipData.newPlainText("simple text",
                        copyList.get(selectIndex));
                Toast.makeText(GetClipboard.this,
                        "已粘贴到剪切板", Toast.LENGTH_SHORT).show();
                cm.setPrimaryClip(clipData);
            }
            SuspendedWindowManager.removeBigWindow(getContext());
            SuspendedWindowManager.createSmallWindow(getContext());
        }

    }

}
