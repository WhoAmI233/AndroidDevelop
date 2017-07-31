package suprise.qiuguochao.com.charm.FortuneBigWheel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import suprise.qiuguochao.com.charm.R;
/**
 * Created by qiuguochao on 2017/1/9.
 */

public class FortuneBigWheel_jiuGongGe2 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fortunebigwheel_jiugongge2);
    }
    public static void startActivity(Context context){
        Intent intent = new Intent(context, FortuneBigWheel_jiuGongGe2.class);
        context.startActivity(intent);
    }
}


