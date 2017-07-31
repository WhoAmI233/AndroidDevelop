package suprise.qiuguochao.com.charm.FortuneBigWheel;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import suprise.qiuguochao.com.charm.R;

/**
 * Created by qiuguochao on 2017/1/9.
 */

public class FortuneBigWheel_Choose extends Activity implements View.OnClickListener {
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fortunebigwheel_choose);
        init();
    }

    public void init(){
        Button mBtn_1,mBtn_2,mBtn_3;
        TextView textView_testcpp;
        textView_testcpp = (TextView)findViewById(R.id.textView_testCpp);
        mBtn_1 = (Button)findViewById(R.id.btn_bigWheel);
        mBtn_2 = (Button)findViewById(R.id.btn_jiugongge1);
        mBtn_3 = (Button)findViewById(R.id.btn_jiugongge2);
        mBtn_1.setOnClickListener(this);
        mBtn_2.setOnClickListener(this);
        mBtn_3.setOnClickListener(this);
        textView_testcpp.setText(stringFromJNI());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bigWheel:
                FortuneBigWheel_RouletteKind.startActivity(this);
                break;
            case R.id.btn_jiugongge1:
                FortuneBigWheel_jiuGongGe1.startActivity(this);
                break;
            case R.id.btn_jiugongge2:
                FortuneBigWheel_jiuGongGe2.startActivity(this);
                break;
            default:
                break;
        }
    }
}