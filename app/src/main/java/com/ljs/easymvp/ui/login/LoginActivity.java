package com.ljs.easymvp.ui.login;

import android.view.View;

import com.ljs.easymvp.R;
import com.ljs.easymvp.frame.BaseActivity;
import com.ljs.easymvp.presenter.LoginPresenter;

/**
 * Created by ljs on 2018/3/27.
 * Desc: 登录页面视图层
 */

public class LoginActivity extends BaseActivity implements ILogin {

    private ILoginPresenter presenter;

    @Override
    public void success() {

    }

    @Override
    public void fail() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {
        presenter = new LoginPresenter(this);
    }

    @Override
    public void onInnerClick(View v) {
        switch (v.getId()) {
            //case R.id.login:
            // presenter.login(...);
            //break;
            default:
                break;
        }
    }
}
