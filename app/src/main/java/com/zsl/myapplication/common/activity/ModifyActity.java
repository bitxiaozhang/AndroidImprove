package com.zsl.myapplication.common.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.zsl.myapplication.R;

public class ModifyActity extends Activity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_actity);
        initLayout();
    }

    private void initLayout(){
        editText = (EditText)findViewById(R.id.edit_text);
    }

    public void editFinish(View view){
        String text = editText.getText().toString().trim();
        if (TextUtils.isEmpty(text)){
            finish();
        }else {
            Intent intent = new Intent();
            intent.putExtra("result",text);
            setResult(RESULT_OK,intent);
            finish();
        }


    }
}
