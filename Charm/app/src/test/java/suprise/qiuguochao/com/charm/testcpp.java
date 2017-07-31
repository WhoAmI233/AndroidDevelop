package suprise.qiuguochao.com.charm;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by qiuguochao on 2017/4/25.
 */

public class testcpp {
    static {
        System.loadLibrary("libtestcpp");
    }

    public native String stringFromJNI();

    @Test
    public void main(){
        stringFromJNI();
    }
}
