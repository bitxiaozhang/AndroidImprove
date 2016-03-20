package com.zsl.myapplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zsl.myapplication.common.activity.ModifyActity;
import com.zsl.myapplication.common.db.DatabaseHelper;
import com.zsl.myapplication.common.model.Contact;


public class MainActivity extends Activity {

    private EditText mNameEditText;
    private EditText mPhoneEditText;
    private EditText mEmailEditText;
    private DatabaseHelper dbHelper;
    private TextView mNickNameTextView;

    private static final int MODIFY_NAME = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayout();
        dbHelper= new DatabaseHelper(this);
    }

    private void initLayout(){
        mNameEditText = (EditText) findViewById(R.id.name);
        mPhoneEditText = (EditText) findViewById(R.id.phone_num);
        mEmailEditText = (EditText) findViewById(R.id.email);
        mNickNameTextView = (TextView)findViewById(R.id.nick_name);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putString("Status", "connecting");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        String status = savedInstanceState.getString("Status");
        Log.e("asd","Save the date is:"+status);
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void insertContact(View view){
        if (TextUtils.isEmpty(mNameEditText.getText().toString())||
                TextUtils.isEmpty(mPhoneEditText.getText().toString())||
                TextUtils.isEmpty(mEmailEditText.getText().toString())){
            Toast.makeText(this,"不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        Contact contact = new Contact();
        contact.setName(mNameEditText.getText().toString().trim());
        contact.setPhoneNumber(mPhoneEditText.getText().toString().trim());
        contact.setEmail(mEmailEditText.getText().toString().trim());
        mNameEditText.setText("");
        mPhoneEditText.setText("");
        mEmailEditText.setText("");
        dbHelper.addContact(contact);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void showDialog(View view){
        Dialog dialog  = new AlertDialog.Builder(this)
                .setTitle("确定要退出吗？")
                .setIcon(R.drawable.main_app_action_background)
                .setView(new ProgressBar(getBaseContext()))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(getBaseContext(), "OK", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(),"Cancel",Toast.LENGTH_SHORT).show();
                    }
                }).create();
           dialog.setCanceledOnTouchOutside(false);
           dialog.show();
    }

    public void modifyNickName(View view){
        Intent intent = new Intent(this, ModifyActity.class);
        startActivityForResult(intent,MODIFY_NAME);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case MODIFY_NAME:
                    mNickNameTextView.setText(data.getStringExtra("result"));
                    break;
                default:
                    break;
            }
        }
    }
}
