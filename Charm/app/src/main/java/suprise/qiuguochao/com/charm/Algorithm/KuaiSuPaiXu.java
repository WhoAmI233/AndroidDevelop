package suprise.qiuguochao.com.charm.Algorithm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

import suprise.qiuguochao.com.charm.R;

/**
 * Created by qiuguochao on 2017/2/15.
 */

public class KuaiSuPaiXu extends Activity {
    private RelativeLayout relativeLayout_KuaiSuPaiXu;
    private TextView textView_Sort;
    private TextView textView_UnSort;
    private TextView textView_Principle;
    private TextView textView_DisplayNum;
    private TextView textView_PrincipleText;
    private TextView textView_TitleName;
    private TextView textView_KeyCode;
    private ImageView imageView_KeyCode;
    private WebView principleGif=null;
    private final int randomNum = 10;
    private int[] array = new int[randomNum];
    private final int buttonSum = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paixu);
        init();

        textView_PrincipleText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog =  new AlertDialog.Builder(getApplicationContext()).setTitle("真*奥义").setMessage(R.string.KuaiSuPaiXu_Principle)
                        .setPositiveButton("确认", null).create();
                alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                alertDialog.show();
            }
        });
        textView_KeyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageView_KeyCode.getVisibility() == View.INVISIBLE) {
                    imageView_KeyCode.setVisibility(View.VISIBLE);
                    principleGif.setVisibility(View.INVISIBLE);
                    textView_PrincipleText.setVisibility(View.INVISIBLE);
                }
                else
                {
                    imageView_KeyCode.setVisibility(View.INVISIBLE);
                    principleGif.setVisibility(View.INVISIBLE);
                    textView_PrincipleText.setVisibility(View.INVISIBLE);
                }
            }
        });
        textView_Principle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (principleGif.getVisibility() == View.INVISIBLE) {
                    imageView_KeyCode.setVisibility(View.INVISIBLE);
                    principleGif.setVisibility(View.VISIBLE);
                    textView_PrincipleText.setVisibility(View.VISIBLE);
                }
                else
                {
                    imageView_KeyCode.setVisibility(View.INVISIBLE);
                    principleGif.setVisibility(View.INVISIBLE);
                    textView_PrincipleText.setVisibility(View.INVISIBLE);
                }
            }
        });

        textView_Sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quick_sort(array,0,randomNum-1);
                displayArray();

            }
        });

        textView_UnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createRandomNum();
                displayArray();
            }
        });
      }

    private void init() {
        textView_Sort = (TextView) findViewById(R.id.textView_Sort);
        textView_UnSort = (TextView) findViewById(R.id.textView_UnSort);
        textView_Principle = (TextView) findViewById(R.id.textView_Principle);
        textView_DisplayNum = (TextView) findViewById(R.id.textView_DisplayNum);
        textView_PrincipleText = (TextView) findViewById(R.id.textView_PrincipleText);
        textView_TitleName = (TextView) findViewById(R.id.textView_TitleName);
        textView_KeyCode = (TextView) findViewById(R.id.textView_KeyCode);
        imageView_KeyCode = (ImageView) findViewById(R.id.imageView_KeyCode);

        textView_TitleName.setText("快速排序");
        imageView_KeyCode.setBackgroundResource(R.drawable.algorithm_kuaisupaixu_code);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int topMargin = 280;
        int postion_Sort = (dm.widthPixels/buttonSum - textView_Sort.getWidth())/2  -130;
        int postion_UnSort =dm.widthPixels/buttonSum + postion_Sort;
        int postion_Principle =dm.widthPixels*2/buttonSum + postion_Sort;
        int postion_keycode =dm.widthPixels*3/buttonSum + postion_Sort;

        RelativeLayout.LayoutParams params_Sort=(RelativeLayout.LayoutParams)textView_Sort.getLayoutParams();
        RelativeLayout.LayoutParams params_UnSort=(RelativeLayout.LayoutParams)textView_UnSort.getLayoutParams();
        RelativeLayout.LayoutParams params_Principle=(RelativeLayout.LayoutParams)textView_Principle.getLayoutParams();
        RelativeLayout.LayoutParams params_KeyCode=(RelativeLayout.LayoutParams)textView_KeyCode.getLayoutParams();

        params_Sort.setMargins(postion_Sort,topMargin,0,0);
        params_UnSort.setMargins(postion_UnSort,topMargin,0,0);
        params_Principle.setMargins(postion_Principle,topMargin,0,0);
        params_KeyCode.setMargins(postion_keycode,topMargin,0,0);

        System.out.println("heigth : " + dm.heightPixels);
        System.out.println("width : " + dm.widthPixels);

        principleGif = (WebView) findViewById(R.id.principleGif);
        principleGif.loadDataWithBaseURL(null,"<HTML><body bgcolor='#f3f3f3'><div align=center><IMG src='file:///android_asset/algorithm_kuaisupaixuyuanli.gif'/></div></body></html>", "text/html", "UTF-8",null);

        createRandomNum();
        displayArray();
    }

    private void displayArray()
    {
        String displayArray = "";
        for( int i = 0 ; i<randomNum; i++)
        {
            displayArray+=array[i];
            if( i < randomNum -1)
            {
                displayArray+=",";
            }
        }
        textView_DisplayNum.setText(displayArray);
    }

    private void createRandomNum(){
        Random random=new Random();
        for (int i = 0; i <randomNum; i++) {
            array[i] = random.nextInt(randomNum);
        }
    }

    private int AdjustArray(int array[], int left, int right) //返回调整后基准数的位置
    {
        int low = left;
        int high = right;
        int referenceNumber = array[left]; //第一个坑
        while (low < high)
        {
            // 从右向左找小于x的数来填 第一坑位
            while(low < high && array[high] >= referenceNumber)
                high--;
            if(low < high)
            {
                array[low] = array[high]; // array[high]就变成了接坑侠
                low++;
            }

            // 从左向右找大于或等于x的数来填s[j]
            while(low < high && array[low] < referenceNumber)
                low++;
            if(low < high)
            {
                array[high] = array[low]; //array[low] 新任接坑侠
                high--;
            }
        }
        //第一坑的落点
        array[low] = referenceNumber;

        return low;
    }

    //left right代表数列里的元素位置
    private void quick_sort(int array[], int left, int right)
    {
        if (left < right)
        {
            int referenceNumber = AdjustArray(array, left, right);//挖出基准坑
            quick_sort(array, left, referenceNumber - 1); //分治法思想之搞定左边
            quick_sort(array, referenceNumber + 1, right);  //分治法思想之接着搞定右边
        }
    }
}
