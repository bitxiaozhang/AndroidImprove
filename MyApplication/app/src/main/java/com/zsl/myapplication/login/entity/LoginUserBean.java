package com.zsl.myapplication.login.entity;

/**
 * Created by zsl on 3/13/16.
 */
public class LoginUserBean {
    private String userName;
    private String passWord;

    public LoginUserBean() {
    }

    public LoginUserBean(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
