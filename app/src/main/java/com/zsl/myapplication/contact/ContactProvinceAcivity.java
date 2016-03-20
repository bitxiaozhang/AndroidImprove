package com.zsl.myapplication.contact;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ExpandableListView;

import com.zsl.myapplication.R;
import com.zsl.myapplication.contact.Entity.CityEntity;
import com.zsl.myapplication.contact.Entity.ProviceEntity;

import java.util.ArrayList;
import java.util.List;

public class ContactProvinceAcivity extends Activity {
    private ExpandableListView mContactListView;
    private CityListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_province_acivity);
        initLayout();
    }
    private void initLayout(){
        mContactListView = (ExpandableListView)findViewById(R.id.contact_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CityEntity cityEntity = new CityEntity("周口","1",R.drawable.ic_launcher);
        CityEntity cityEntity1 = new CityEntity("郑州","2",R.drawable.ic_launcher);
        CityEntity cityEntity2 = new CityEntity("南阳","3",R.drawable.ic_launcher);
        List<ProviceEntity> proviceEntityList = new ArrayList<>();

        List<CityEntity> cityEntityList = new ArrayList<>();
        cityEntityList.add(cityEntity);
        cityEntityList.add(cityEntity1);
        ProviceEntity proviceEntity0 = new ProviceEntity(cityEntityList,"河南");

        cityEntityList = new ArrayList<>();
        cityEntityList.add(cityEntity);
        cityEntityList.add(cityEntity1);
        cityEntityList.add(cityEntity2);

        ProviceEntity proviceEntity1 = new ProviceEntity(cityEntityList,"河北");


        cityEntityList = new ArrayList<>();
        int i = 0;
        while( i< 10){
            cityEntityList.add(cityEntity2);
            i++;
        }

        ProviceEntity proviceEntity2 = new ProviceEntity(cityEntityList,"北京");

        proviceEntityList.add(proviceEntity0);
        proviceEntityList.add(proviceEntity1);
        proviceEntityList.add(proviceEntity2);

        adapter = new CityListAdapter(this,proviceEntityList);
        mContactListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
