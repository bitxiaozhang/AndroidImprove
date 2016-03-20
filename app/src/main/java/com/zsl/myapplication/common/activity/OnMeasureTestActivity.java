package com.zsl.myapplication.common.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;

import com.zsl.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OnMeasureTestActivity extends Activity {

    @Bind(R.id.root_view)RelativeLayout rootView;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_measure_test);
        ButterKnife.bind(this);
    }
}
