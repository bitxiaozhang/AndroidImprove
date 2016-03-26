package com.zsl.myapplication.profile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zsl.myapplication.R;
import com.zsl.myapplication.common.utils.SharedPreferenceUtils;
import com.zsl.myapplication.login.LoginActivity;

import java.util.Observable;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.quit_user) Button quitButton;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile,container,false);
        ButterKnife.bind(this,view);
        setLayout();
        return view;
    }


    private void setLayout(){
        quitButton.setOnClickListener(this);
    }
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.quit_user:
                SharedPreferenceUtils.setLoginState(false);
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
                break;
            default:
                break;
        }
    }
}
