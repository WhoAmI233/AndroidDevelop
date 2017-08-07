package suprise.qiuguochao.com.palmscan.util;

import android.content.Context;

import java.io.File;

/**
 * Created by qiuguochao on 2017/4/17.
 */

public class FileUtil {
    public static File getSaveFile(Context context) {
        File file = new File(context.getFilesDir(), "pic.jpg");
        return file;
    }
}
