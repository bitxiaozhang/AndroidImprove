package com.zsl.myapplication.find.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by zsl on 3/9/16.
 */
public class ImageCycleView extends LinearLayout {

    private ViewPager mViewPager;
    private LinearLayout viewGroup;
    private Context context;
    private View view;

    private ImageView[] mImageViews = null;

    private boolean isStop;

    public int stype = 1;

    public boolean isAutoCycle = true;

    private int selectPosition = 0;

    //切换时间
    private int time = 3000;

    private ImageView mImageView = null;



    public ImageCycleView(Context context) {
        super(context);
    }

    public ImageCycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageCycleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}

