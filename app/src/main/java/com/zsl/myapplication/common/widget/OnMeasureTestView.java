package com.zsl.myapplication.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zsl on 3/17/16.
 */
public class OnMeasureTestView extends View {

    public OnMeasureTestView(Context context) {
        super(context);
    }


    public OnMeasureTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OnMeasureTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int measureHeightSize = MeasureSpec.getSize(heightMeasureSpec);
        Log.e("OnMeasure", "{measureWidthMode:"+measureWidthMode+","+
        "measureWidthSize:"+measureWidthSize+","+
        "measureHeightMode"+measureHeightMode+","+
        "measureHeightSize"+measureHeightSize+"}");
    }

    private float mDownX, mDownY, x, y;
    private int dx, dy, il, ir, it, ib;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                il = getLeft();
                ir = getRight();
                it = getTop();
                ib = getBottom();
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                dx += Math.round(x - mDownX);
                dy += Math.round(y - mDownY);
                layout(il + dx, it + dy, ir + dx, ib + dy);
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
