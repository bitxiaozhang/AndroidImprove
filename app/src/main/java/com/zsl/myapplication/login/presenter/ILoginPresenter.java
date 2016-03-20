package com.zsl.myapplication.login.presenter;

/**
 * Created by zsl on 3/13/16.
 */
public interface ILoginPresenter {
    public void clear();
    public void doLogin(String name,String passWord);
    public void setProgressVisible(int visibility);
}
