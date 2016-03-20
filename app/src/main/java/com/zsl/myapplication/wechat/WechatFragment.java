package com.zsl.myapplication.wechat;


import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.zsl.myapplication.R;
import com.zsl.myapplication.common.utils.NotificationUtils;
import com.zsl.myapplication.home.HomeAcitivity;
import com.zsl.myapplication.login.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class WechatFragment extends Fragment implements View.OnClickListener{

    private Button showNotifincatonBtn;
    private Button openOneActivityButton;

    private boolean isRuning = false;
    private ValueAnimator buttonAnim ;
    public WechatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_wechat, container, false);

        showNotifincatonBtn = (Button) rootView.findViewById(R.id.show_notification);
        showNotifincatonBtn.setOnClickListener(this);
        openOneActivityButton = (Button) rootView.findViewById(R.id.open_activity);
        openOneActivityButton.setOnClickListener(this);

        final DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        showNotifincatonBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ValueAnimator animator = ValueAnimator.ofFloat(1.0f,0.5f,0.5f,1f,1f,0f);
                animator.setDuration(1000);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float currentValue = (float) animation.getAnimatedValue();
                        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int) (metrics.widthPixels * currentValue),(int)(40*(metrics.densityDpi/160.0f)));
                        params.gravity = Gravity.CENTER_HORIZONTAL;
                        showNotifincatonBtn.setLayoutParams(params);
                        if (currentValue <= 0.05f) {
                            showNotifincatonBtn.setVisibility(View.GONE);
                        }
                    }
                });

                animator.start();
                return true;
            }
        });
        return rootView;
    }

    public void notification(View view){

        Intent intent = new Intent(getActivity(), HomeAcitivity.class);
        NotificationUtils.getInstance(getActivity()).
                generateNotifacation(intent, "收到一条信息", "MainHome已打开", "点击打开MainHome");
        if (isRuning){
            buttonAnim.cancel();
            isRuning = false;
            showNotifincatonBtn.setAlpha(1.0f);
        }else {
            if (buttonAnim == null){
                buttonAnim = ValueAnimator.ofFloat(0, 1f, 0, 1);
                buttonAnim.setDuration(300);
                buttonAnim.setRepeatCount(ValueAnimator.INFINITE);
                buttonAnim.setRepeatMode(ValueAnimator.RESTART);
                buttonAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float correntValue = (float) animation.getAnimatedValue();
                        showNotifincatonBtn.setAlpha(correntValue);
                    }
                });
            }
            buttonAnim.start();
            isRuning = true;

        }
    }

    private void openOneActivity(View view){
        Intent intent = new Intent(getActivity(),LoginActivity.class);
        startActivity(intent);


    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.show_notification:
               notification(v);
                break;
            case R.id.open_activity:
               openOneActivity(v);
                break;
            default:
                break;
        }
    }
}
