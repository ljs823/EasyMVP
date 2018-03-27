package com.ljs.easymvp.service;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by ljs on 2018/3/27.
 * Desc: 于网，络请求成败监听处理类
 */

public abstract class VolleyListener {

    /**
     * 网络请求成功回调监听
     */
    public Response.Listener<String> response() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                success(response);
            }
        };
    }

    /**
     * 网络请求失败回调监听处理
     */
    public Response.ErrorListener error() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                switch (error.networkResponse.statusCode) {
                    //具体该怎么处理逻辑，根据实际项目进行更改
                    case 301:
                        break;
                    case 302:
                        break;
                    case 401:
                        break;
                    case 402:
                        break;
                    case 403:
                        break;
                    case 500:
                        break;
                    default:
                        fail(error);
                        break;
                }
            }
        };
    }

    /**
     * 网络请求成功实现
     */
    public abstract void success(String res);

    /**
     * 网络请求失败实现——根据具体项目需求处理
     */
    public void fail(VolleyError error){}

}
