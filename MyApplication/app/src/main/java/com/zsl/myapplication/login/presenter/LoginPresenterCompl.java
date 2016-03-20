package com.zsl.myapplication.login.presenter;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.android.volley.VolleyError;
import com.zsl.myapplication.MainApplication;
import com.zsl.myapplication.common.network.HttpUtils;
import com.zsl.myapplication.common.network.ResponseListener;
import com.zsl.myapplication.common.network.VolleyHttpUtil;
import com.zsl.myapplication.login.entity.LoginRequest;
import com.zsl.myapplication.login.entity.LoginUserBean;
import com.zsl.myapplication.login.model.IUser;
import com.zsl.myapplication.login.model.UserModel;
import com.zsl.myapplication.login.views.ILoginView;

import org.json.JSONObject;


/**
 * Created by zsl on 3/13/16.
 */
public class LoginPresenterCompl implements ILoginPresenter {

    private IUser user;
    private ILoginView view;
    private Handler handler;

    public LoginPresenterCompl(ILoginView view){
        this.view = view;
        initUser();
        handler = new Handler(Looper.getMainLooper());
    }
    private void initUser(){
        user = new UserModel("mvp","mvp");
    }

    @Override
    public void clear() {
        view.onClearText();
    }

    @Override
    public void doLogin(String name, String passWord) {
        Boolean isLoginSuccess = true;
        final int code = user.checkUserInvalite(name, passWord);
        if (code!=0) isLoginSuccess = false;
        final Boolean result = isLoginSuccess;
        LoginUserBean loginUserBean = new LoginUserBean(name,passWord);
        VolleyHttpUtil.sendRequest(new LoginRequest(loginUserBean), new ResponseListener() {
            @Override
            public void onSuccess(JSONObject object) {
                if (object.optInt("code") != 0) {
                    LoginPresenterCompl.this.setProgressVisible(View.INVISIBLE);
                    return;
                };
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.onLoginResult(result, code);
                    }
                });
            }

            @Override
            public void onFailed(VolleyError error) {
                LoginPresenterCompl.this.setProgressVisible(View.INVISIBLE);
            }
        }, MainApplication.getInstance().getBaseContext());
    }

    @Override
    public void setProgressVisible(int visibility) {
        view.onSetProgressVisible(visibility);
    }
}
