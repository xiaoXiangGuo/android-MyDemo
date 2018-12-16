package yishitongda.www.demo.utils;

import android.util.Log;

public class MyLog {
    static boolean isDebug = true;
    public static void d(String tag, String msg){
        if (isDebug) {
            Log.d(tag, msg);
        }

    }
}
