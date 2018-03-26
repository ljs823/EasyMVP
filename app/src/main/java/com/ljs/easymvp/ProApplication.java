package com.ljs.easymvp;

import android.app.Application;

/**
 * author : ljs
 * time   : 2017/05/16
 * desc   : 应用级别的初始化操作——功能：应用级别上下文单例、应用版本名称、
 */

public class ProApplication extends Application {

    private static ProApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    /**
     * @return 整个应用级别的唯一实例
     */
    public static ProApplication getInstance() {
        return instance;
    }

}
