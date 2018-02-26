package com.example.asus.car.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.car.R;

/**
 * Created by asus on 2016/10/17.
 */
public class CarShopActivity extends Activity implements View.OnClickListener{

    private GridView gvCarSevice;
    private ImageView TvReturnHome;

    private String[] Names = new String[]{"洗车","保养","检测","贴膜镀晶","维修救援","内饰清洁",
            "美容改装","配件车饰"};
    private int[] mItems = new int[]{R.mipmap.poi_auto_xiche,R.mipmap.online_booking_history_baoyang,
                                        R.mipmap.poi_buy_insurance,R.mipmap.poi_car_rental,
                                       R.mipmap.online_booking_history_weixiu ,R.mipmap.poi_auto_licai,
                                         R.mipmap.poi_auto_daijia,R.mipmap.poi_auto_parts};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.car_shop_layout);

        gvCarSevice = (GridView) findViewById(R.id.gv_car_sevice);
        TvReturnHome = (ImageView) findViewById(R.id.return_home);
        gvCarSevice.setAdapter(new CarSeviceAdapter());

        TvReturnHome.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.return_home:
                finish();
                break;
        }
    }

    class CarSeviceAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mItems.length;
        }

        @Override
        public Object getItem(int position) {
            return mItems[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(CarShopActivity.this,R.layout.car_sevice_list_item,null);
            ImageView ivItem = (ImageView) view.findViewById(R.id.iv_item);
            TextView tvItem = (TextView) view.findViewById(R.id.tv_item);

            tvItem.setText(Names[position]);
            ivItem.setImageResource(mItems[position]);
            return view;
        }
    }
}
