package suprise.qiuguochao.com.charm;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.baidu.aip.ocr.AipOcr;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.junit.Assert.*;
/**
 * Created by qiuguochao on 2017/2/6.
 */

@RunWith(AndroidJUnit4.class)
public class AndroidOcrClientTest {
    //设置APPID/AK/SK
    public static final String APP_ID = "9233456";
    public static final String API_KEY = "BRhafRDTQlw3hbgkFwRmGAWR";
    public static final String SECRET_KEY = "lZQfn1lyHuMuDV8f1WNekjPtdTLZ5E0U";

    @Test
    public void main() {
        // 初始化一个OcrClient
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        try{
            // 调用身份证识别接口
//            String idFilePath = "C:\\Users\\qiuguochao\\Desktop\\test.png";
//            JSONObject idcardRes = client.idcard(idFilePath, true, new HashMap<String, String>());
//            System.out.println(idcardRes.toString(3));
//
//            // 调用银行卡识别接口
//            String bankFilePath = "C:\\Users\\qiuguochao\\Desktop\\test.png";
//            JSONObject bankRes = client.bankcard(bankFilePath);
//            System.out.println(bankRes.toString(2));

            // 调用通用识别接口
            String genFilePath = "D:\\AndroidDevelop\\source\\test.png";
            JSONObject genRes = client.general(genFilePath, new HashMap<String, String>());
            JSONArray jsonArray = new JSONArray();
            jsonArray = genRes.getJSONArray("words_result");
            String a = String.valueOf(jsonArray.length());
            System.out.println(genRes);
            System.out.println(a);
            for(int i=0;i<jsonArray.length();i++) {
                JSONObject jsonobject2 = jsonArray.getJSONObject(i);
                String words = jsonobject2.getString("words");
                System.out.println(words);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
