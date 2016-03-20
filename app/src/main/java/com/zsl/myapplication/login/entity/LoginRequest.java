package com.zsl.myapplication.login.entity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.zsl.myapplication.common.URLConstants;
import com.zsl.myapplication.common.network.BaseRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zsl on 3/13/16.
 */
public class LoginRequest extends BaseRequest<LoginUserBean> {


    public LoginRequest(LoginUserBean model) {
        super(model);
    }

    @Override
    public JSONObject toJson() {
        JSONObject result = new JSONObject();
        if (model == null) return null;
        String json = new Gson().toJson(model);
        try {
            result = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void setUrl() {
        url = URLConstants.LOGIN_URL;
    }
}
