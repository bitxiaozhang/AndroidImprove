package com.zsl.myapplication.common.network;

import org.json.JSONObject;

/**
 * Created by zsl on 3/13/16.
 */
public abstract class BaseRequest<T> {
    protected T model;
    protected String url;

    public BaseRequest(T model) {
        this.model = model;
        setUrl();
    }

    public T getModel(){
        return model;
    }
    protected abstract JSONObject toJson();
    protected abstract void setUrl();
    public String getUrl(){
        return url;
    }
}
