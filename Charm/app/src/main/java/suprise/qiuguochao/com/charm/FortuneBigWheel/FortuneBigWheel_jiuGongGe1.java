package suprise.qiuguochao.com.charm.FortuneBigWheel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;
import suprise.qiuguochao.com.charm.R;

/**
 * Created by qiuguochao on 2017/1/9.
 */

public class FortuneBigWheel_jiuGongGe1 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fortunebigwheel_jiugongge1);
    }
    public static void startActivity(Context context){
        Intent intent = new Intent(context, FortuneBigWheel_jiuGongGe1.class);
        context.startActivity(intent);
    }
}



