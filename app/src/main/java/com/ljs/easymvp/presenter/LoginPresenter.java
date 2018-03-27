package com.ljs.easymvp.presenter;

import com.android.volley.VolleyError;
import com.ljs.easymvp.model.LoginModel;
import com.ljs.easymvp.service.VolleyListener;
import com.ljs.easymvp.ui.login.ILogin;
import com.ljs.easymvp.ui.login.ILoginModel;
import com.ljs.easymvp.ui.login.ILoginPresenter;

import java.util.Map;

/**
 * Created by ljs on 2018/3/27.
 * Desc: 登录逻辑层实现
 */

public class LoginPresenter implements ILoginPresenter {

    private ILogin iLogin;
    private ILoginModel login;

    public LoginPresenter(ILogin iLogin) {
        this.iLogin=iLogin;
        login=new LoginModel();
    }

    @Override
    public void login(Map<String, String> params) {
        //success:
        VolleyListener listener=new VolleyListener() {
            @Override
            public void success(String res) {
                iLogin.success();
            }

            @Override
            public void fail(VolleyError error) {
                iLogin.fail();
            }
        };
        login.login(params,listener);
    }
}
