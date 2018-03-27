package com.ljs.easymvp.ui.login;

import java.util.Map;

/**
 * Created by ljs on 2018/3/27.
 * Desc: 登录模块逻辑层功能接口规范
 */

public interface ILoginPresenter {

    /**
     * 登录
     *
     * @param params 登录时候要传递的参数集
     */
    void login(Map<String, String> params);

}
