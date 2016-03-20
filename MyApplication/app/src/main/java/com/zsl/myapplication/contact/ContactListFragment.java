package com.zsl.myapplication.contact;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zsl.myapplication.R;
import com.zsl.myapplication.common.activity.LabelFlowActivity;
import com.zsl.myapplication.common.activity.OnMeasureTestActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactListFragment extends Fragment {
    @Bind(R.id.city_checkout) Button mCheckoutButton;
    @Bind(R.id.label_flow) Button mLabelFlowButton;
    @Bind(R.id.on_measure)Button mOnMeasureButton;

    private CityListAdapter adapter;

    public ContactListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_list,container,false);
        ButterKnife.bind(this,view);
        initLayout();
        return view;
    }

    private void initLayout(){
        mCheckoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ContactProvinceAcivity.class);
                startActivity(intent);
            }
        });
        mLabelFlowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),LabelFlowActivity.class);
                startActivity(intent);
            }
        });

        mOnMeasureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),OnMeasureTestActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
