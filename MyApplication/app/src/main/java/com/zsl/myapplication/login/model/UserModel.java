package com.zsl.myapplication.login.model;

/**
 * Created by zsl on 3/13/16.
 */
public class UserModel implements IUser {
    private String name;
    private String passwd;

    public UserModel() {
    }

    public UserModel(String name, String passwd) {
        this.name = name;
        this.passwd = passwd;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPasswd() {
        return passwd;
    }

    @Override
    public int checkUserInvalite(String name, String passWord) {
        return 0;
    }
}
