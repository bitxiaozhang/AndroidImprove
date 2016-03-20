package com.zsl.myapplication;

/**
 * Created by zsl on 1/3/16.
 */
public enum ActionBarTitleLocatiom {
    NOTITLE(0),LEFT(1<<1),CENTER(1<<2);
    private int mLocation;

    private ActionBarTitleLocatiom(int location){
        this.mLocation = location;
    }
}
