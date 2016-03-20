package com.zsl.myapplication.common.network;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by zsl on 3/15/16.
 */
public interface ResponseListener {

    public void onSuccess(JSONObject object);
    public void onFailed(VolleyError error);
}
