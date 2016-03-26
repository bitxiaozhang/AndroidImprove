package com.zsl.myapplication.launch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.zsl.myapplication.R;
import com.zsl.myapplication.advertisement.AdvertisementActivity;
import com.zsl.myapplication.common.utils.SharedPreferenceUtils;
import com.zsl.myapplication.home.HomeAcitivity;
import com.zsl.myapplication.login.LoginActivity;

public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Class<?> clazz;
                if (SharedPreferenceUtils.isFirstLaunch()){
                    clazz = AdvertisementActivity.class;
                    SharedPreferenceUtils.setIsFirstLaunch(false);
                }else if (SharedPreferenceUtils.hasLogin()){
                    clazz = HomeAcitivity.class;
                }else{
                    clazz = LoginActivity.class;
                }

                Intent intent = new Intent(LaunchActivity.this,clazz);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
