package suprise.qiuguochao.com.charm.Algorithm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import suprise.qiuguochao.com.charm.R;

/**
 * Created by qiuguochao on 2017/2/14.
 */

public class AlgorithmWizard extends Activity {
    private Button btn_KuaiSuPX;
    private Button btn_MaoPaoPX;
    private LinearLayout lineLayout_Algorithm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.algorithmwizard);
        lineLayout_Algorithm = (LinearLayout) findViewById(R.id.lineLayout_Algorithm);
        btn_KuaiSuPX= (Button) findViewById(R.id.btn_KuaiSuPX);
        btn_MaoPaoPX= (Button) findViewById(R.id.btn_MaoPaoPX);

        btn_KuaiSuPX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = null;
                i = new Intent(AlgorithmWizard.this, KuaiSuPaiXu.class);
                startActivity(i);
            }

        });

        btn_MaoPaoPX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = null;
                i = new Intent(AlgorithmWizard.this, MaoPaoPaiXu.class);
                startActivity(i);
            }

        });

        lineLayout_Algorithm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //无效
                if(v.getId() == R.id.btn_KuaiSuPX)
                {
                    Intent i = null;
                    i = new Intent(AlgorithmWizard.this, KuaiSuPaiXu.class);
                    startActivity(i);
                }
            }

        });
    }
}
