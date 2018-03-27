package com.ljs.easymvp.model;

import com.ljs.easymvp.frame.Config;
import com.ljs.easymvp.service.VolleyListener;
import com.ljs.easymvp.ui.login.ILoginModel;
import com.ljs.easymvp.util.VolleyUtil;

import java.util.Map;

/**
 * Created by ljs on 2018/3/27.
 * Desc:登录数据层接口实现
 */

public class LoginModel implements ILoginModel {

    @Override
    public void login(Map<String, String> params, VolleyListener listener) {
        VolleyUtil.request(Config.URL_LOGIN, params, listener);
    }

}
