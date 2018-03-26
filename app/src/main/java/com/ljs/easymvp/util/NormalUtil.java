package com.ljs.easymvp.util;

/**
 * author : ljs
 * time   : 2017/05/16
 * desc   :
 */

public class NormalUtil {
    private static long lastClickTime;

    /**
     * @return 限制1秒内按钮只能点击一次
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 1000) {
            ToastUtil.showToast("请稍后！");
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
