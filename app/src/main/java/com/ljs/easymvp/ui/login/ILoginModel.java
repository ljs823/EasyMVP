package com.ljs.easymvp.ui.login;

import com.ljs.easymvp.service.VolleyListener;

import java.util.Map;

/**
 * Created by ljs on 2018/3/27.
 * Desc: 登录接口
 */

public interface ILoginModel {

    void login(Map<String, String> params, VolleyListener listener);

}
