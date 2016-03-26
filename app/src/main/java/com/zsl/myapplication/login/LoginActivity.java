package com.zsl.myapplication.login;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zsl.myapplication.R;
import com.zsl.myapplication.contact.Entity.ProviceEntity;
import com.zsl.myapplication.home.HomeAcitivity;
import com.zsl.myapplication.login.presenter.LoginPresenterCompl;
import com.zsl.myapplication.login.views.ILoginView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends Activity implements ILoginView,View.OnClickListener{
    @Bind(R.id.name_input)  EditText userName;
    @Bind(R.id.password_input)  EditText passWord;
    @Bind(R.id.login_progress)  ProgressBar progressBar;
    @Bind(R.id.login_button) Button loginButton;
    private LoginPresenterCompl loginPresenterCompl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acitivity_main_one);
        ButterKnife.bind(this);
        loginButton.setOnClickListener(this);
        loginPresenterCompl = new LoginPresenterCompl(this);
        loginPresenterCompl.setProgressVisible(View.INVISIBLE);
        setLayout();
        Log.d("LoginActivity", this.toString());
    }



    @Override
    public void onClearText() {
        userName.setText("");
        passWord.setText("");
    }

    @Override
    public void onLoginResult(Boolean result, int code) {
        if (result == true){
            Intent intent = new Intent(this,HomeAcitivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(this,"登陆成功",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"登陆失败",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSetProgressVisible(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override
    public void setLayout() {
        passWord.setImeOptions(EditorInfo.IME_ACTION_GO);
        passWord.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO){
                    loginPresenterCompl.doLogin(userName.getText().toString().trim(),passWord.getText().toString().trim());
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                progressBar.setVisibility(View.VISIBLE);
                loginPresenterCompl.doLogin(userName.getText().toString().trim(),passWord.getText().toString().trim());
                break;
            default:
                break;
        }
    }
}
