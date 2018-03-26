package com.ljs.easymvp.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.ljs.easymvp.ProApplication;

/**
 * author : ljs
 * time   : 2017/05/16
 * desc   : 吐司工具：全局显示唯一的吐司提示/当前上下文显示吐司提示
 */

public class ToastUtil {
    private static String oldMsg;
    protected static Toast toast = null;
    private static long oneTime = 0;
    private static long twoTime = 0;

    /**
     * 全局Toast
     */
    public static void showToast(String s) {
        if (toast == null) {
            //此处需要Application为单例模式
            toast = Toast.makeText(ProApplication.getInstance(), s, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (s.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = s;
                toast.setText(s);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }
        oneTime = twoTime;
    }

    /**
     * 当前Activity的Toast
     */
    public static void showToast(Context context, String s) {
        if (toast == null) {
            //此处需要Application为单例模式
            toast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (s.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = s;
                toast.setText(s);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }
        oneTime = twoTime;
    }
}
