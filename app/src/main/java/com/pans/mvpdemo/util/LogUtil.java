package com.pans.mvpdemo.util;

import android.util.Log;

/***********
 * @author Ian
 * @date 2015-12-14 11:31
 * @describ 日志管理类
 */
public class LogUtil {

    public static boolean isDebug;

    public static void i(String msg) {
        i("Demo_Tag", msg);
    }

    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void d(String msg) {
        d("Demo_Tag", msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.d(tag, msg);
        }
    }

    public static void e(String msg) {
        e("Demo_Tag", msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }

}
