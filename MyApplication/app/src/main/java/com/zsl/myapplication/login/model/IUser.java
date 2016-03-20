package com.zsl.myapplication.login.model;

/**
 * Created by zsl on 3/13/16.
 */
public interface IUser {
    public String getName();

    public String getPasswd();

    public int checkUserInvalite(String name,String passWord);
}
