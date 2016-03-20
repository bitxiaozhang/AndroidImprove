package com.zsl.myapplication.login.views;

/**
 * Created by zsl on 3/13/16.
 */
public interface ILoginView {
    public void onClearText();
    public void onLoginResult(Boolean result,int code);
    public void onSetProgressVisible(int visibility);

}
