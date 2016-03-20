package com.zsl.myapplication.common.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by zsl on 3/14/16.
 */
public class VolleyHttpUtil {


    public static void sendRequest(BaseRequest request,final ResponseListener listener,Context context){

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = request.getUrl();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                request.getUrl(), request.toJson(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                    if (listener != null && response != null){
                        listener.onSuccess(response);
                    }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null && listener != null){
                    listener.onFailed(error);
                }
            }
        });
        queue.add(jsonObjectRequest);
    }
}
