package com.zsl.myapplication.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

import com.zsl.myapplication.MainApplication;

/**
 * Created by zsl on 3/25/16.
 */
public class SharedPreferenceUtils {

    public static boolean hasLogin(){
        boolean result = false;
        Context context = MainApplication.getInstance().getBaseContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("appState",Context.MODE_PRIVATE);
        result = sharedPreferences.getBoolean("loginState",false);
        return result;
    }

    public static synchronized void setLoginState(boolean isLogin){
        Context context = MainApplication.getInstance().getBaseContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("appState",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("loginState",isLogin);
        editor.commit();
    }

    public static boolean isFirstLaunch(){
        boolean result = false;
        Context context = MainApplication.getInstance().getBaseContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("appState", Context.MODE_PRIVATE);
        result = sharedPreferences.getBoolean("isFirstLaunch",true);
        return result;
    }

    public static synchronized void setIsFirstLaunch(boolean isFirstLaunch){
        Context context = MainApplication.getInstance().getBaseContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("appState",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isFirstLaunch",isFirstLaunch);
        editor.commit();
    }
}
