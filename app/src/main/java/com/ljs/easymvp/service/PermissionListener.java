package com.ljs.easymvp.service;

import java.util.List;

/**
 * author : ljs
 * time   : 2017/05/16
 * desc   : 权限处理监听接口规范：同意给出提示，拒绝则加入集合
 */

public interface PermissionListener {
    /**
     * 同意权限处理
     */
    void onGranted();

    /**
     * @param denies 拒绝的权限内容处理
     */
    void onDenied(List<String> denies);
}
