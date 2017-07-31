package suprise.qiuguochao.com.charm.Typewriting;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by qiuguochao on 2017/2/6.
 */

public class LatinKeyboardView extends KeyboardView
{

    static final int KEYCODE_OPTIONS = -100;
    static final int KEYCODE_COPY = -101;
    static final int KEYCODE_PASTE = -102;

    public LatinKeyboardView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public LatinKeyboardView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onLongPress (Keyboard.Key popupKey) {
        super.onLongPress(popupKey);
        //初始化Toast
        Toast toast = new Toast(this.getContext());
//设置显示时间，可以选择Toast.LENGTH_LONG或者Toast.LENGTH_SHORT
        toast.setDuration(Toast.LENGTH_LONG);
//承载一个TextView，用来显示文字
        TextView view = new TextView(this.getContext());
//设置TextView的值
        view.setText("猜猜我是谁？");
//设置TextView的布局
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
//Toast承载该布局
        toast.setView(view);
//设置Toast对象在手机中的相对位置，最上方显示
        toast.setGravity(Gravity.CENTER,0,0);
//显示Toast
        toast.show();
        Log.i("mytest", "LatinKeyboardView：onLongPress");
        return false;
    }

}