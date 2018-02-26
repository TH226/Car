package com.example.asus.car.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.Window;

import com.example.asus.car.Fragment.MainFragment;
import com.example.asus.car.Login.LoginActivity;
import com.example.asus.car.R;

public class SplashActivity extends Activity {

    private final static int GO_HOME = 1;

   private Handler handler = new Handler(){
       @Override
       public void handleMessage(Message msg) {
           switch (msg.what){
               case GO_HOME:
                   gohome();
                   break;
           }
       }
   };

    private void gohome() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        handler.sendEmptyMessageDelayed(GO_HOME,2000);
    }
}
