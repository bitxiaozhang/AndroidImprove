package com.zsl.myapplication.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.zsl.myapplication.R;

/**
 * Created by zsl on 3/14/16.
 */
public class UnderLineEditText extends EditText implements View.OnFocusChangeListener{
    private Paint mPaint;
    private int mFocusColor;
    private int mFocusNotColor;

    public UnderLineEditText(Context context) {
        super(context);
    }

    public UnderLineEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mFocusColor = getContext().getResources().getColor(R.color.edittext_unline_color_focus);
        mFocusNotColor = getContext().getResources().getColor(R.color.edittext_unline_color_not_focus);
        if (isFocused()){
            mPaint.setColor(mFocusColor);
        }else{
            mPaint.setColor(mFocusNotColor);
        }
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(1.0f);
        setOnFocusChangeListener(this);
        setPadding(5,0,0,0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        canvas.drawLine(1,height-5,1,height-1,mPaint);
        canvas.drawLine(1,height-1,width-1,height-1,mPaint);
        canvas.drawLine(width-1,height-1,width-1,height-5,mPaint);
        super.onDraw(canvas);
    }

    /**
     * Called when the focus state of a view has changed.
     *
     * @param v        The view whose state has changed.
     * @param hasFocus The new focus state of v.
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            mPaint.setColor(mFocusColor);
        }else{
            mPaint.setColor(mFocusNotColor);
        }
        invalidate();
    }
}
