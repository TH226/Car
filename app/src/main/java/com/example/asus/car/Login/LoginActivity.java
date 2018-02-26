package com.example.asus.car.Login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.asus.car.Constants;
import com.example.asus.car.Fragment.MainFragment;
import com.example.asus.car.R;
import com.example.asus.car.Utils.MD5Utils;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import io.rong.imkit.MainActivity;

public class LoginActivity extends Activity implements View.OnClickListener{
    private EditText etUserName;
    private EditText etPassword;
    private TextView tvForgetPassword;
    private TextView tvRegist;
    private MD5Utils md5Utils;
    private CheckBox CbPassword;
    private CheckBox CbLogin;
    private String isMemory="";
    private String isLogin="";

    private String username;
    private String password;
    private static String FILE = "saveUserNamePwd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Bmob.initialize(this, Constants.Bmob_APPID);
        etUserName = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        tvForgetPassword = (TextView) findViewById(R.id.tv_forget_password);
        tvRegist = (TextView) findViewById(R.id.tv_user_regist);
        CbPassword = (CheckBox) findViewById(R.id.cb_rem_password);
        CbLogin = (CheckBox) findViewById(R.id.cb_login);
        SharedPreferences sp = getSharedPreferences("config",MODE_PRIVATE);
        isMemory = sp.getString("isMemory","");
        isLogin = sp.getString("isLogin","");

        Intent intent = getIntent();
        int sign = intent.getIntExtra("sign",-1);//当取不到传过来的值sign时，取-1
        if (isMemory.equals("YES")) {
            username = sp.getString("username", "");
            password = sp.getString("password", "");
            etUserName.setText(username);
            etPassword.setText(password);
            CbPassword.setChecked(true);
        }
        if(isLogin.equals("YES")){
            CbLogin.setChecked(true);
            if(sign==-1){
                startActivity(new Intent(LoginActivity.this, MainFragment.class));
                finish();
            }
        }
        initView();
    }
    private void initView() {
        tvForgetPassword.setOnClickListener(this);
        tvRegist.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_forget_password:
                startActivity(new Intent(LoginActivity.this,FindPasswordActivity.class));
                break;
            case R.id.tv_user_regist:
                startActivity(new Intent(LoginActivity.this,RegistActivity.class));
                break;
        }
    }
    public void Login(View view) {
        username = etUserName.getText().toString();
        password = etPassword.getText().toString();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(LoginActivity.this, "账号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        remmber();
        String PassWord = md5Utils.encode(password);
        BmobUser.loginByAccount(username, PassWord, new LogInListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                  if(user != null){
                      Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                      startActivity(new Intent(LoginActivity.this, MainFragment.class));
                      finish();
                  }else{
                      Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                  }
            }
        });
    }
    public void remmber() {
        SharedPreferences.Editor edit = getSharedPreferences("config",MODE_PRIVATE).edit();
        if(CbPassword.isChecked()){
            edit.putString("username", username);
            edit.putString("password", password);
            edit.putString("isMemory","YES");
            edit.commit();

        }else{
            edit.putString("isMemory","NO");
            edit.commit();
        }
        if(CbLogin.isChecked()){
            edit.putString("isLogin","YES");
            edit.commit();
        }else{
            edit.putString("isLogin","NO");
            edit.commit();
        }
    }
    public boolean onKeyDown(int keyCode,KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void exit() {
            finish();
            System.exit(0);
    }
}
