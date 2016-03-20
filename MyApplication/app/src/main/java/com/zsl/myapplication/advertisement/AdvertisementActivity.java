package com.zsl.myapplication.advertisement;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zsl.myapplication.R;
import com.zsl.myapplication.common.widget.LineIndicatorView;
import com.zsl.myapplication.home.HomeAcitivity;
import com.zsl.myapplication.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AdvertisementActivity extends FragmentActivity implements OnPageChangeListener{
    @Bind(R.id.image_view_pager) ViewPager imageViewPager;
    @Bind(R.id.indicator_view)LineIndicatorView indicatorView;
    @Bind(R.id.push_to_activity)Button pushButton;
    private ImageViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);
        ButterKnife.bind(this);
        initData();
    }

    private void initData(){
        List<ImageView> imageViewList = new ArrayList<ImageView>();

        LayoutInflater inflater = LayoutInflater.from(this);
        ImageView imageView = (ImageView) inflater.inflate(R.layout.advertisement_imageview_in_viewpager,null);
        imageView.setImageResource(R.drawable.page_one);
        imageViewList.add(imageView);

        imageView = (ImageView) inflater.inflate(R.layout.advertisement_imageview_in_viewpager,null);
        imageView.setImageResource(R.drawable.page_two);
        imageViewList.add(imageView);

        imageView = (ImageView) inflater.inflate(R.layout.advertisement_imageview_in_viewpager,null);
        imageView.setImageResource(R.drawable.page_three);
        imageViewList.add(imageView);

        adapter = new ImageViewPagerAdapter(imageViewList);
        imageViewPager.setAdapter(adapter);
        imageViewPager.addOnPageChangeListener(this);

    }




    public void pushActivity(View view){
        Intent intent = new Intent(this, HomeAcitivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.e("Page", "" + position + ":" + positionOffset);
        indicatorView.setLocation(position,positionOffset);
        if (pushButton.getVisibility() == View.INVISIBLE&&position==adapter.getCount()-1){
            pushButton.setVisibility(View.VISIBLE);
        }else if(pushButton.getVisibility() == View.VISIBLE&&position<adapter.getCount()-1){
            pushButton.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onPageSelected(int position) {
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
