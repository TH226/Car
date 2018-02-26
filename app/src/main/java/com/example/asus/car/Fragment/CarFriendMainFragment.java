package com.example.asus.car.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.car.Activity.SettingActivity;
import com.example.asus.car.R;

import io.rong.imkit.RongIM;


/**
 * Created by 威威 on 2016/8/16.
 */
public class CarFriendMainFragment extends FragmentActivity implements View.OnClickListener {

    private RelativeLayout RlNearFri;
    private RelativeLayout RlChatFri;
    private RelativeLayout RlCarFri;

    private TextView TvNearFri;
    private TextView TvChatFri;
    private TextView TvCarFri;
    private TextView TvNearFriLine;
    private TextView TvChatFriLine;
    private TextView TvCarLine;
    private TextView TvTitle;
    private TextView TvStartChat;
    private TextView TvAddFri;

    private ImageView IvReturnHome;

    private Fragment view1;
    private Fragment view2;
    private Fragment view3;



    private long exitTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.car_friend_fragment);

        initView();
        initEvent();
        setSelect(0);
    }

    private void initView() {
       RlNearFri = (RelativeLayout) findViewById(R.id.rl_near_fri);
        RlChatFri = (RelativeLayout) findViewById(R.id.rl_chat_fri);
        RlCarFri = (RelativeLayout) findViewById(R.id.rl_car_friend);

        TvNearFri = (TextView) findViewById(R.id.tv_near_fri);
        TvChatFri = (TextView) findViewById(R.id.tv_chat_fri);
        TvCarFri = (TextView) findViewById(R.id.tv_car_fri);
        TvNearFriLine = (TextView) findViewById(R.id.tv_near_line_fri);
        TvChatFriLine = (TextView) findViewById(R.id.tv_chat_line_fri);
        TvCarLine = (TextView) findViewById(R.id.tv_car_fri_line);
        TvTitle = (TextView) findViewById(R.id.car_fri_title);
        TvStartChat = (TextView) findViewById(R.id.start_chat);
        TvAddFri = (TextView) findViewById(R.id.add_fri);
        IvReturnHome = (ImageView) findViewById(R.id.iv_fri_return_home);



    }

    private void initEvent() {
        RlNearFri.setOnClickListener(this);
        RlChatFri.setOnClickListener(this);
        RlCarFri.setOnClickListener(this);

        IvReturnHome.setOnClickListener(this);
        TvStartChat.setOnClickListener(this);



    }

    private void setSelect(int i){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (i) {
            case 0:
                if(view1==null) {
                    view1 = new CarNearFriFragment();
                    transaction.add(R.id.car_frag,view1);
                } else {
                    transaction.show(view1);
                }
                TvStartChat.setVisibility(View.GONE);
                TvAddFri.setVisibility(View.GONE);

                TvNearFri.setTextColor(this.getResources().getColor(R.color.colorbottomlight));
                TvTitle.setText("附近");
                TvNearFriLine.setBackgroundColor(this.getResources().getColor(R.color.colorbottomlight));
               // startService(new Intent(this,RocketService.class));
                break;
            case 1:
//                if (RongIM.getInstance() != null)
//                    RongIM.getInstance().startConversationList(CarFriendMainFragment.this);
                if(view2==null) {
                    view2 = new OnChatFragment();
                    transaction.add(R.id.car_frag,view2);
                } else {
                    transaction.show(view2);
                }
                TvStartChat.setVisibility(View.VISIBLE);
                TvAddFri.setVisibility(View.GONE);
                TvChatFri.setTextColor(this.getResources().getColor(R.color.colorbottomlight));
                TvTitle.setText("聊天");
                TvChatFriLine.setBackgroundColor(this.getResources().getColor(R.color.colorbottomlight));
                TvStartChat.setText("开始会话");
            //    stopService(new Intent(this, RocketService.class));
                break;
            case 2:
                if(view3==null) {
                    view3 = new CarFriFragment();
                    transaction.add(R.id.car_frag,view3);
                } else {
                    transaction.show(view3);
                }
                TvCarFri.setTextColor(this.getResources().getColor(R.color.colorbottomlight));
                TvTitle.setText("车友");
                TvCarLine.setBackgroundColor(this.getResources().getColor(R.color.colorbottomlight));
                TvStartChat.setVisibility(View.GONE);
                TvAddFri.setVisibility(View.VISIBLE);
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

    }

    @Override
    public void onClick(View v) {
        resetImgs();
        switch (v.getId()) {
            case R.id.rl_near_fri:
                setSelect(0);
                break;
            case R.id.rl_chat_fri:
                setSelect(1);
                break;
            case R.id.rl_car_friend:
                setSelect(2);
                break;
            case R.id.iv_fri_return_home:
              //  stopService(new Intent(this, RocketService.class));
               finish();
                break;
            case R.id.start_chat:
                if (RongIM.getInstance() != null)
                    RongIM.getInstance().startPrivateChat(CarFriendMainFragment.this, "小浩", "title");
                break;
            case R.id.add_fri:
                Toast.makeText(CarFriendMainFragment.this, "add", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    //置暗
    private void resetImgs() {

        TvNearFri.setTextColor(ContextCompat.getColor(this,R.color.colorbottomgray));
        TvNearFriLine.setBackgroundColor(ContextCompat.getColor(this,R.color.colorbottomgray));
        TvChatFri.setTextColor(ContextCompat.getColor(this,R.color.colorbottomgray));
        TvChatFriLine.setBackgroundColor(ContextCompat.getColor(this,R.color.colorbottomgray));
        TvCarFri.setTextColor(ContextCompat.getColor(this,R.color.colorbottomgray));
        TvCarLine.setBackgroundColor(ContextCompat.getColor(this,R.color.colorbottomgray));

    }

//    public boolean onKeyDown(int keyCode,KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            exit();
//            return false;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//    public void exit() {
//        if ((System.currentTimeMillis() - exitTime) > 2000) {
//            Toast.makeText(CarFriendMainFragment.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
//            exitTime = System.currentTimeMillis();
//        } else {
//         //   stopService(new Intent(this, RocketService.class));
//            finish();
//            System.exit(0);
//        }
//    }

}
