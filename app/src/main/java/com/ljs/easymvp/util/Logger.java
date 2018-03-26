package com.ljs.easymvp.util;

import android.util.Log;

/**
 * Created by box on 2018/3/13.
 * Desc:本地日志记录器
 */

public class Logger {

    // set false when online
    private static boolean isOpen = true;

    public static void v(String tag, String msg) {
        if (isOpen) {
            Log.v(tag, "v: " + msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isOpen) {
            Log.d(tag, "d: " + msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isOpen) {
            Log.i(tag, "i: " + msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isOpen) {
            Log.w(tag, "w: " + msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isOpen) {
            Log.e(tag, "e: " + msg);
        }
    }

}
