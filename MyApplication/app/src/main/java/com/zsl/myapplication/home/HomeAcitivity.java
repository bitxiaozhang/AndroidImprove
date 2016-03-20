package com.zsl.myapplication.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsl.myapplication.R;
import com.zsl.myapplication.contact.ContactListFragment;
import com.zsl.myapplication.find.FindFragment;
import com.zsl.myapplication.home.adapter.HomeActivityPagerAdapter;
import com.zsl.myapplication.profile.ProfileFragment;
import com.zsl.myapplication.wechat.WechatFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeAcitivity extends FragmentActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{

    private WechatFragment mWeChatFragment;
    private ContactListFragment mContactListFragment;
    private FindFragment mFindFragment;
    private ProfileFragment mProfileFragment;

    private List<TextView> textViewList;
    private List<ImageView> imgViewList;
    private List<Fragment> fragmentList;
    private ViewPager mViewPager;
    private HomeActivityPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_acitivity);
        initLayout();
        initData();
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    private void initLayout(){
        mViewPager = (ViewPager) findViewById(R.id.container);
        mWeChatFragment = new WechatFragment();
        mContactListFragment = new ContactListFragment();
        mFindFragment = new FindFragment();
        mProfileFragment = new ProfileFragment();

        fragmentList = new ArrayList<Fragment>();

        imgViewList = new ArrayList<ImageView>();
        ImageView imgView = (ImageView) findViewById(R.id.iv_wechat);
        imgViewList.add(imgView);
        imgView = (ImageView) findViewById(R.id.iv_contactList);
        imgViewList.add(imgView);
        imgView = (ImageView) findViewById(R.id.iv_find);
        imgViewList.add(imgView);
        imgView = (ImageView) findViewById(R.id.iv_profile);
        imgViewList.add(imgView);

        textViewList = new ArrayList<TextView>();
        TextView textView = (TextView) findViewById(R.id.tv_wechat);
        textViewList.add(textView);
        textView = (TextView) findViewById(R.id.tv_wechat_top);
        textViewList.add(textView);

        textView = (TextView) findViewById(R.id.tv_contact);
        textViewList.add(textView);
        textView = (TextView) findViewById(R.id.tv_contact_top);
        textViewList.add(textView);

        textView = (TextView) findViewById(R.id.tv_find);
        textViewList.add(textView);
        textView = (TextView) findViewById(R.id.tv_find_top);
        textViewList.add(textView);

        textView = (TextView) findViewById(R.id.tv_profile);
        textViewList.add(textView);
        textView = (TextView) findViewById(R.id.tv_profile_top);
        textViewList.add(textView);
    }


    private void initData(){
        pagerAdapter = new HomeActivityPagerAdapter(getSupportFragmentManager(),fragmentList);

        imgViewList.get(0).setAlpha(1.0f);
        for (int i = 0; i < imgViewList.size(); i++) {
            if (i == 0){
                imgViewList.get(i).setAlpha(1.0f);
                textViewList.get(i*2).setAlpha(0f);
                textViewList.get(i*2+1).setAlpha(1.0f);
            }else{
                imgViewList.get(i).setAlpha(0f);
                textViewList.get(i*2).setAlpha(1.0f);
                textViewList.get(i*2+1).setAlpha(0f);
            }
        }

        fragmentList.add(mWeChatFragment);
        fragmentList.add(mContactListFragment);
        fragmentList.add(mFindFragment);
        fragmentList.add(mProfileFragment);

    }


    private void update(int position,float alpha){
        if (alpha >0){
            textViewList.get(position*2).setAlpha(alpha);
            textViewList.get(position*2+1).setAlpha(1-alpha);
            position+=1;
            textViewList.get(position*2).setAlpha(1-alpha);
            textViewList.get(position*2+1).setAlpha(alpha);
        }else{
            textViewList.get(position*2+1).setAlpha(1.0f);
        }
    }
    @Override
    public void onClick(View arg0) {
        for (int i = 0; i < imgViewList.size(); i++) {
            imgViewList.get(i).setAlpha(0f);
            textViewList.get(i*2).setAlpha(1.0f);
            textViewList.get(i*2+1).setAlpha(0.0f);
        }

        switch (arg0.getId()) {
            case R.id.ib_wechat:
            case R.id.container_wechat:
                mViewPager.setCurrentItem(0, false);
                imgViewList.get(0).setAlpha(1.0f);
                update(0,-1.0f);
                break;
            case R.id.ib_contactList:
            case R.id.container_contact:
                mViewPager.setCurrentItem(1, false);
                imgViewList.get(1).setAlpha(1.0f);
                update(1,-1.0f);
                break;
            case R.id.container_find:
            case R.id.ib_find:
                mViewPager.setCurrentItem(2, false);
                imgViewList.get(2).setAlpha(1.0f);
                update(2,-1.0f);
                break;
            case R.id.container_profile:
            case R.id.ib_profile:
                mViewPager.setCurrentItem(3, false);
                imgViewList.get(3).setAlpha(1.0f);
                update(3,-1.0f);
                break;
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("zsl","onPageScrolled: " + " postion:"+position +
                "positionOffset:" +positionOffset +
                "positonOffsetPixels:" + positionOffsetPixels);
        if (positionOffset > 0 ){
            ImageView left = imgViewList.get(position);
            ImageView right = imgViewList.get(position + 1);

            left.setAlpha(1-positionOffset);
            right.setAlpha(positionOffset);

            update(position,positionOffset);
        }

    }


    @Override
    public void onPageSelected(int position) {
        Log.d("zsl","onPageSelected:" + position);
    }


    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d("zsl","onPageScrollStateChanged:" + state);
    }
}
