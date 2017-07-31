package suprise.qiuguochao.com.charm.Tomcat;

import com.baidu.aip.ocr.AipOcr;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by qiuguochao on 2017/1/23.
 */

public class OcrClient {
    public static final String APP_ID = "9233456";
    public static final String API_KEY = "BRhafRDTQlw3hbgkFwRmGAWR";
    public static final String SECRET_KEY = "lZQfn1lyHuMuDV8f1WNekjPtdTLZ5E0U";
    private static AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
    public OcrClient() {
    }
    public static String getTextFromImage(String imagePath){
        String tempText="";
        try{
            // 调用通用识别接口
            String genFilePath = imagePath;
            HashMap<String, String> options = new HashMap<String, String>();
            JSONObject genRes = client.general(genFilePath,options);
            JSONArray jsonArray = new JSONArray();
            jsonArray = genRes.getJSONArray("words_result");
            String a = String.valueOf(jsonArray.length());
            for(int i=0;i<jsonArray.length();i++) {
                JSONObject jsonobject2 = jsonArray.getJSONObject(i);
                String words = jsonobject2.getString("words");
                tempText+=words;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempText;
    }
}
