package com.zsl.myapplication.common.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.zsl.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zsl on 3/17/16.
 */
public class FlowLayout extends ViewGroup {

    private enum SELECT_STATE{
        SELETED,NOT_SLECTED
    }
    private Map<Button,SELECT_STATE> mStateHashMap = new HashMap<Button,SELECT_STATE>();
    private OnClickItemObjectLisener mClickItemListener;
    
    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightSize = MeasureSpec.getSize(heightMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        //wrap_content
        int width = 0;
        int height = 0;

        //记录每一行的高度和宽度
        int lineWidth = 0;
        int lineHeight = 0;
        int cCount = getChildCount();

        for (int i = 0; i<cCount;i++){
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);

            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

            //子View占据的宽度
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            //判断是否换行
            if(lineWidth + childWidth > measureWidthSize - getPaddingLeft() - getPaddingRight()){
                width = Math.max(width,lineWidth);
                lineWidth = childWidth;

                height += lineHeight;
                lineHeight = childHeight;
            }else{
                //没有换行
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight,childHeight);
            }

            if (i == cCount -1){
                width = Math.max(lineWidth,width);
                height += lineHeight;
            }
        }
        setMeasuredDimension(measureWidthMode == MeasureSpec.EXACTLY?measureWidthSize:width+getPaddingLeft()+getPaddingRight()
        ,measureHeightMode == MeasureSpec.EXACTLY?measureHeightSize:height + getPaddingTop() + getPaddingBottom());

    }

    /**
     * 存储所有的View
     */
    private List<List<View>> mAllViews = new ArrayList<List<View>>();
    /**
     * 每一行的高度
     */
    private List<Integer> mLineHeight = new ArrayList<Integer>();
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mAllViews.clear();
        mLineHeight.clear();

        // 当前ViewGroup的宽度
        int width = getWidth();

        int lineWidth = 0;
        int lineHeight = 0;

        List<View> lineViews = new ArrayList<View>();

        int cCount = getChildCount();

        for (int i = 0; i < cCount; i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child
                    .getLayoutParams();

            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            // 如果需要换行
            if (childWidth + lineWidth + lp.leftMargin + lp.rightMargin > width
                    - getPaddingLeft() - getPaddingRight()) {
                // 记录LineHeight
                mLineHeight.add(lineHeight);
                // 记录当前行的Views
                mAllViews.add(lineViews);

                // 重置我们的行宽和行高
                lineWidth = 0;
                lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
                // 重置我们的View集合
                lineViews = new ArrayList<View>();
            }
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            lineHeight = Math.max(lineHeight, childHeight + lp.topMargin
                    + lp.bottomMargin);
            lineViews.add(child);

        }// for end
        // 处理最后一行
        mLineHeight.add(lineHeight);
        mAllViews.add(lineViews);

        // 设置子View的位置

        int left = getPaddingLeft();
        int top = getPaddingTop();

        // 行数
        int lineNum = mAllViews.size();

        for (int i = 0; i < lineNum; i++) {
            // 当前行的所有的View
            lineViews = mAllViews.get(i);
            lineHeight = mLineHeight.get(i);

            for (int j = 0; j < lineViews.size(); j++) {
                View child = lineViews.get(j);
                // 判断child的状态
                if (child.getVisibility() == View.GONE) {
                    continue;
                }

                MarginLayoutParams lp = (MarginLayoutParams) child
                        .getLayoutParams();

                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc = lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();

                // 为子View进行布局
                child.layout(lc, tc, rc, bc);

                left += child.getMeasuredWidth() + lp.leftMargin
                        + lp.rightMargin;
            }
            left = getPaddingLeft();
            top += lineHeight;
        }

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    public void setData(String[] mVals){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        for (int i = 0;i<mVals.length;i++){
            final Button button = (Button) inflater.inflate(R.layout.activity_button_flow,this, false);
            button.setText(mVals[i]);
            mStateHashMap.put(button,SELECT_STATE.NOT_SLECTED);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button buttonTemp = (Button)v;
                    if (mClickItemListener != null){
                        mClickItemListener.onClickItemObejct(buttonTemp,mStateHashMap.get(buttonTemp));
                    }

                    if (mStateHashMap.get(buttonTemp) == SELECT_STATE.SELETED) {
                        Toast.makeText(getContext(), "delete action", Toast.LENGTH_SHORT).show();
                        removeView(buttonTemp);
                    } else if(mStateHashMap.get(buttonTemp) == SELECT_STATE.NOT_SLECTED){
                        Toast.makeText(getContext(), "select action", Toast.LENGTH_SHORT).show();
                        buttonTemp.setBackgroundResource(R.drawable.button_main_pressed);
                        mStateHashMap.put(buttonTemp,SELECT_STATE.SELETED);
                    }
                }
            });
            addView(button);
        }
    }

    public void setmClickItemListener(OnClickItemObjectLisener mClickItemListener) {
        this.mClickItemListener = mClickItemListener;
    }

    public interface OnClickItemObjectLisener{
        void onClickItemObejct(Button item,SELECT_STATE state);
    }
}
