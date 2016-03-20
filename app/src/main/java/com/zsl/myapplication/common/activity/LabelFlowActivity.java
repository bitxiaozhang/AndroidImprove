package com.zsl.myapplication.common.activity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zsl.myapplication.R;
import com.zsl.myapplication.common.widget.FlowLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LabelFlowActivity extends Activity {

    private String[] mVals =new String[]{
            "protected void onLayout(boolean changed, int l, int t, int r, int b)","Android","Welcome","Music","Sport","Working",
            "Game","Fishing","Shopping","Music Singing","Sport happy",
            "Working Hard","Game Team","Fishing river","Shopping more"
    };
    @Bind(R.id.flow_layout)FlowLayout mFlowLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_flow);
        ButterKnife.bind(this);
        initData();
    }

    private void initData(){
        mFlowLayout.setData(mVals);
    }

}
