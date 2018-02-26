package com.example.asus.car.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.asus.car.Fragment.HomeFragment;
import com.example.asus.car.R;

/**
 * Created by asus on 2016/10/23.
 */
public class NewsRemindActivity extends Activity implements View.OnClickListener{
    private ImageView ReturnHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_remind_activity);
        initView();
    }

    private void initView() {
        ReturnHome = (ImageView) findViewById(R.id.iv_news_return_home);
        ReturnHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_news_return_home:
                finish();
                break;
        }
    }
}
