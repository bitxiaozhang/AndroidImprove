package com.zsl.myapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import static com.zsl.myapplication.ActionBarTitleLocatiom.*;

public class ActionBarBaseActivity extends Activity {


    
    
    private int mLeftIconId;
    private String  mTitle;
    private ActionBarTitleLocatiom mTitleLocation;
    private TextView mLeftTextView;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View actionbarLayout = LayoutInflater.from(this).inflate(
                R.layout.action_bar, null);
        TextView title = (TextView)actionbarLayout.findViewById(R.id.title);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);

        getActionBar().setCustomView(actionbarLayout, params);

    }

    private void setTitle(String title,ActionBarTitleLocatiom titleLocatiom,int iconId){

    }


    private void initActionBar(){
        if (mTitleLocation != null){
            switch (mTitleLocation){
                case LEFT:

                    break;
                case CENTER:
                    break;
                case NOTITLE:
                    break;
                default:
                    break;
            }
        }

    }

}