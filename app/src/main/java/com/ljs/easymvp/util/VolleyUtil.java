package com.ljs.easymvp.util;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ljs.easymvp.ProApplication;
import com.ljs.easymvp.service.VolleyListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ljs on 2018/3/27.
 * Desc: 以volley框架为基础的网络请求处理工具类
 */

public class VolleyUtil {

    private static RequestQueue queue;

    {
        queue = Volley.newRequestQueue(ProApplication.getInstance());
    }

    public static void request(String url, final Map<String,String> params, VolleyListener listener) {
        StringRequest request = new StringRequest(url, listener.response(),
                listener.error()) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers=new HashMap<>();
                headers.put("","");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        queue.add(request);
    }

}
