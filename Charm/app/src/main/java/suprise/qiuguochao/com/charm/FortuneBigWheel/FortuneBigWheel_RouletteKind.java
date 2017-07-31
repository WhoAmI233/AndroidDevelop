package suprise.qiuguochao.com.charm.FortuneBigWheel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import suprise.qiuguochao.com.charm.R;

/**
 * Created by qiuguochao on 2017/1/9.
 */

public class FortuneBigWheel_RouletteKind extends Activity {
    private BitWheelView  mWheelView;
    private ImageView mStartBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fortunebigwheel_wheel);
        mWheelView = (BitWheelView ) findViewById(R.id.id_luckypan);
        mStartBtn = (ImageView) findViewById(R.id.id_start_btn);

        mStartBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!mWheelView.isStart())
                {
                    mStartBtn.setImageResource(R.drawable.fortunebigwheel_stop);
                    mWheelView.luckyStart(1);
                } else
                {
                    if (!mWheelView.isShouldEnd())

                    {
                        mStartBtn.setImageResource(R.drawable.fortunebigwheel_start);
                        mWheelView.luckyEnd();
                    }
                }
            }
        });
    }

    public static void startActivity(Context context){
        Intent intent = new Intent(context, FortuneBigWheel_RouletteKind.class);
        context.startActivity(intent);
    }
}


