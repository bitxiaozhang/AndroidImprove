package com.zsl.myapplication.common.utils;

import android.content.Context;

/**
 * Created by zsl on 2/17/16.
 */
public class PixelCalUtils {

    public static int dip2px(int dip,Context context){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dip*scale + 0.5f);
    }

    public static int px2dip(int px,Context context){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(px/scale + 0.5f);
    }
}
