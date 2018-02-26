package com.example.asus.car.Fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.car.Activity.SettingActivity;
import com.example.asus.car.R;


/**
 * Created by 威威 on 2016/8/16.
 */
public class MainFragment extends FragmentActivity implements View.OnClickListener {

    private LinearLayout LLMainpager;
    private LinearLayout LLMycar;
    private LinearLayout LLDate;
    private LinearLayout LLMy;

    private TextView TvMainpager;
    private TextView TvMycar;
    private TextView TvDate;
    private TextView TvMy;

    private TextView TvTitle;

    private ImageView IvShake;
    private ImageView IvSetting;

    private ImageButton IbMainpager;
    private ImageButton IbMycar;
    private ImageButton IbDate;
    private ImageButton IbMy;

    private Fragment view1;
    private Fragment view2;
    private Fragment view3;
    private Fragment view4;




    private long exitTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_layout);

        initView();
        initEvent();
        setSelect(0);
    }

    private void initView() {
        LLMainpager = (LinearLayout) findViewById(R.id.tab_home);
        LLMycar = (LinearLayout) findViewById(R.id.tab_mycar);
        LLDate = (LinearLayout) findViewById(R.id.tab_data);
        LLMy = (LinearLayout) findViewById(R.id.tab_user);

        TvMainpager = (TextView) findViewById(R.id.tab_home_tv);
        TvMycar = (TextView) findViewById(R.id.tab_mycar_tv);
        TvDate = (TextView) findViewById(R.id.tab_data_tv);
        TvMy = (TextView) findViewById(R.id.tv_user);

        IbMainpager = (ImageButton) findViewById(R.id.tab_home_img);
        IbMycar = (ImageButton) findViewById(R.id.tab_mycar_img);
        IbDate = (ImageButton) findViewById(R.id.tab_data_img);
        IbMy = (ImageButton) findViewById(R.id.tab_user_img);

        TvTitle = (TextView) findViewById(R.id.main_title);

        IvShake = (ImageView) findViewById(R.id.img_pho);
        IvSetting = (ImageView) findViewById(R.id.img_setting);


    }

    private void initEvent() {
        LLMainpager.setOnClickListener(this);
        LLMycar.setOnClickListener(this);
        LLDate.setOnClickListener(this);
        LLMy.setOnClickListener(this);

        IvShake.setOnClickListener(this);
        IvSetting.setOnClickListener(this);



    }

    private void setSelect(int i){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (i) {
            case 0:
                if(view1==null) {
                    view1 = new HomeFragment();
                    transaction.add(R.id.frag,view1);
                } else {
                    transaction.show(view1);
                }
                IbMainpager.setImageResource(R.mipmap.tab_home_hl);
                TvMainpager.setTextColor(this.getResources().getColor(R.color.colorbottomlight));
                TvTitle.setText("车行者");
                IvShake.setVisibility(View.VISIBLE);
                IvSetting.setVisibility(View.VISIBLE);
               // startService(new Intent(this,RocketService.class));
                break;
            case 1:
                if(view2==null) {
                    view2 = new MyCarFragment();
                    transaction.add(R.id.frag,view2);
                } else {
                    transaction.show(view2);
                }
                IbMycar.setImageResource(R.mipmap.tab_car_hl);
                TvMycar.setTextColor(this.getResources().getColor(R.color.colorbottomlight));
                TvTitle.setText("我的车辆");
                IvShake.setVisibility(View.INVISIBLE);
                IvSetting.setVisibility(View.INVISIBLE);
            //    stopService(new Intent(this, RocketService.class));
                break;
            case 2:
                if(view3==null) {
                    view3 = new DataFragment();
                    transaction.add(R.id.frag,view3);
                } else {
                    transaction.show(view3);
                }
                IbDate.setImageResource(R.mipmap.tab_data_hl);
                TvDate.setTextColor(this.getResources().getColor(R.color.colorbottomlight));
                TvTitle.setText("数据");
                IvShake.setVisibility(View.VISIBLE);
                IvSetting.setVisibility(View.VISIBLE);
             //   stopService(new Intent(this, RocketService.class));
                break;
            case 3:
                if(view4==null) {
                    view4 = new MyUserFragment();
                    transaction.add(R.id.frag,view4);
                } else {
                    transaction.show(view4);
                }
                IbMy.setImageResource(R.mipmap.tab_user_hl);
                TvMy.setTextColor(this.getResources().getColor(R.color.colorbottomlight));
                TvTitle.setText("个人中心");
                IvShake.setVisibility(View.VISIBLE);
                IvSetting.setVisibility(View.VISIBLE);
             //   stopService(new Intent(this, RocketService.class));
                break;
            default:

                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction ) {
        if(view1!=null) {
            transaction.hide(view1);
        }
        if(view2!=null) {
            transaction.hide(view2);
        }
        if(view3!=null) {
            transaction.hide(view3);
        }
        if(view4!=null) {
            transaction.hide(view4);
        }
    }

    @Override
    public void onClick(View v) {
        resetImgs();
        switch (v.getId()) {
            case R.id.tab_home:
                setSelect(0);
                break;
            case R.id.tab_mycar:
                setSelect(1);
                break;
            case R.id.tab_data:
                setSelect(2);
                break;
            case R.id.tab_user:
                setSelect(3);
                break;
            case R.id.img_setting:
              //  stopService(new Intent(this, RocketService.class));
                startActivity(new Intent(MainFragment.this,SettingActivity.class));
                break;
        }
    }
    //置暗
    private void resetImgs() {
        IbMainpager.setImageResource(R.mipmap.tab_home);
        TvMainpager.setTextColor(ContextCompat.getColor(this,R.color.colorbottomgray));
        IbMycar.setImageResource(R.mipmap.tab_car);
        TvMycar.setTextColor(ContextCompat.getColor(this,R.color.colorbottomgray));
        IbDate.setImageResource(R.mipmap.tab_data);
        TvDate.setTextColor(ContextCompat.getColor(this,R.color.colorbottomgray));
        IbMy.setImageResource(R.mipmap.tab_user);
        TvMy.setTextColor(ContextCompat.getColor(this,R.color.colorbottomgray));
    }

    public boolean onKeyDown(int keyCode,KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(MainFragment.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
         //   stopService(new Intent(this, RocketService.class));
            finish();
            System.exit(0);
        }
    }


}
