package com.ljs.easymvp.frame;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.ljs.easymvp.R;
import com.ljs.easymvp.service.PermissionListener;
import com.ljs.easymvp.util.AppManager;
import com.ljs.easymvp.util.NormalUtil;
import com.ljs.easymvp.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * author : ljs
 * time   : 2017/05/16
 * desc   : Activity基类——处理关于Activity的视图、数据、注册及动作监听、栈管理；
 * 同时，对软键盘显示、隐藏，权限请求和返回结果做了统一处理。
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected TextView title;   //顶部标题，部分页面没有
    private PermissionListener mListener;   //权限处理接口规范
    public static final int REQUEST_PERMISSION = 10011;//权限请求码
    protected SharedPreferences preferences;    //sharedPreference存取API，不是每个类都需要的。

    //////////////////////////////////////视图及数据统一逻辑///////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        // 默认软键盘不弹出
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        ButterKnife.bind(this);
        title = (TextView) findViewById(R.id.title);
        initView();
        initData();
        initListener();
        registerCommonListener();
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
    }


    /**
     * @return 返回当前Activity的布局id
     */
    public abstract int getLayoutResId();

    /**
     * 初始化控件
     * 子类查找控件,必须在initView方法中
     */
    public void initView() {
    }

    /**
     * 初始化数据
     * 子类所有的初始化数据操作,必须在initData方法中
     */
    public void initData() {
    }

    /**
     * 初始化监听
     * 子类所有的监听设置,必须在initListener方法中
     */
    public void initListener() {
    }

    /**
     * 注册相同监听
     */
    private void registerCommonListener() {
        //给返回按钮设置监听
        View back = findViewById(R.id.back);
        if (back != null) {//并不是所有的界面都有返回按钮
            back.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:  //返回按钮
                finish();
                break;

            default:
                onInnerClick(v);
                break;
        }
    }

    /**
     * @param v 处理除了返回按钮的点击回调,子类所有的控件点击回调,必须在这里处理
     */
    public void onInnerClick(View v) {

        if (NormalUtil.isFastDoubleClick()) {   //阻止用户1秒内连续点击同一个控件并提示
            ToastUtil.showToast("请稍后！");
            return;
        }

    }

    //////////////////////////////////////软键盘显示隐藏逻辑///////////////////////////////////////

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {//************触摸屏幕根据不同逻辑决定是否显示软键盘
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * @param v
     * @param event
     * @return 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     */
    protected boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * @param token 获取InputMethodManager，隐藏软键盘
     */
    protected void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    //////////////////////////////////////权限申请和结果处理///////////////////////////////////////

    /**
     * @param permissions 要申请的权限组
     * @param listener    权限处理监听接口
     */
    public void requestPermission(String[] permissions, PermissionListener listener) {
        mListener = listener;
        //存储被拒绝打开的权限集合
        List<String> perList = new ArrayList<>();
        //遍历要申请的权限数组，进行判断是否给予权限，若没有，将其添加到集合中
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                perList.add(permission);
            }
        }
        //若申请的权限集合为空，则这些要申请的权限已全授予
        if (perList.isEmpty()) {
            mListener.onGranted();
        } else {  //若非空，则有未授予的权限，动态进行申请
            ActivityCompat.requestPermissions(this, perList.toArray(new String[perList.size()]), REQUEST_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION:
                if (grantResults.length > 0) {
                    List<String> deniedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermissions.add(permission);
                        }
                    }
                    if (deniedPermissions.isEmpty()) {    //若为空则权限都已授予
                        mListener.onGranted();
                    } else {   //若非空则将拒绝的权限加入onDenied方法处理
                        mListener.onDenied(deniedPermissions);
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    //////////////////////////////////////Activity销毁逻辑//////////////////////////////////////

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //ButterKnife.unbind(this);//官方只对fragment做解绑--这里做解绑会造成null异常
        // 结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
    }

}
