package com.zsl.myapplication.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by zsl on 3/16/16.
 */
public class ScrollViewBottomTop extends ScrollView{
    
    private ScrollViewBottomTop.OnScrollBottomTopListener onScrollBottomTopListener;
    
    public ScrollViewBottomTop(Context context) {
        super(context);
    }

    public ScrollViewBottomTop(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewBottomTop(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnScrollBottomTopListener(ScrollViewBottomTop.OnScrollBottomTopListener onScrollBottomTopListener) {
        this.onScrollBottomTopListener = onScrollBottomTopListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l,t,oldl,oldt);
        if(onScrollBottomTopListener != null){
            onScrollBottomTopListener.onScroll(getScrollY());
        }

        if (getScrollY() == 0 && onScrollBottomTopListener!= null){
            onScrollBottomTopListener.onScrollBottom();
            return;
        }
        View view = (View)getChildAt(getChildCount()-1);
        int d = view.getBottom();
        d -= (getHeight()+getScrollY());
        if(d==0 && onScrollBottomTopListener!= null)
        {
            onScrollBottomTopListener.onScrollBottom();
            return;
        }
    }


    public interface OnScrollBottomTopListener{
        void onScrollTop();
        void onScrollBottom();
        void onScroll(int scrollY);
    }
}
