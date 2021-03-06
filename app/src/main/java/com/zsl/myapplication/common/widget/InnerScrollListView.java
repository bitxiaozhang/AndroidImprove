package com.zsl.myapplication.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import java.util.List;

/**
 * Created by zsl on 3/17/16.
 */
public class InnerScrollListView extends ListView implements View.OnTouchListener{
    public InnerScrollListView(Context context) {
        super(context);
        setOnTouchListener(this);
    }

    public InnerScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
    }

    public InnerScrollListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                v.getParent().getParent().getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_UP:
                v.getParent().getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }

        v.onTouchEvent(event);
        return false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
