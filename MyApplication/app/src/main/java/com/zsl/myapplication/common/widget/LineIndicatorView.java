package com.zsl.myapplication.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.zsl.myapplication.R;

/**
 * Created by zsl on 3/19/16.
 */
public class LineIndicatorView extends View {


    private final int defaltSelectColor = Color.parseColor("#00ff00");
    private int selectItemColor;
    private int itemsNum;
    private Paint mPaint;
    private int selectedPosition;
    private float leftPercent;
    private int indicatorbarWidth;

    public LineIndicatorView(Context context) {
        this(context, null);
    }


    public LineIndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.lineIndicatorView,defStyleAttr,0);
        selectItemColor = typedArray.getColor(R.styleable.lineIndicatorView_selected_item_color, defaltSelectColor);
        itemsNum = typedArray.getInt(R.styleable.lineIndicatorView_item_num,3);

        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setColor(selectItemColor);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int topPadding = getPaddingTop();
        int leftPosition;
        if (indicatorbarWidth == 0){
            indicatorbarWidth = getWidth()/itemsNum;
        }
        if (selectedPosition<itemsNum-1){
            leftPosition = selectedPosition*indicatorbarWidth + (int)(leftPercent*indicatorbarWidth);
            canvas.drawRect(leftPosition,topPadding,leftPosition+indicatorbarWidth,getHeight(),mPaint);
        }else if (selectedPosition == itemsNum-1){
            leftPosition = selectedPosition*indicatorbarWidth;
            canvas.drawRect(leftPosition,topPadding,getWidth(),getHeight(),mPaint);
        }
    }

    public void setLocation(int position,float leftPercent){
        this.selectedPosition = position;
        this.leftPercent = leftPercent;
        invalidate();
    }
}
