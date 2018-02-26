package com.example.asus.car.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.asus.car.Login.LoginActivity;
import com.example.asus.car.R;

/**
 * Created by asus on 2016/10/17.
 */
public class SettingActivity extends Activity implements View.OnClickListener{
    private Button BtnCloseLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);
        BtnCloseLogin = (Button) findViewById(R.id.btn_close_login);
        BtnCloseLogin.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_close_login:
                Intent intent = new Intent();
                intent.setClass(this,LoginActivity.class);
                intent.putExtra("sign",1);
                startActivity(intent);

                intent.setAction("exit_app");
                sendBroadcast(intent);
                finish();
                break;
        }
    }
}
