package com.example.asus.car.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.asus.car.Fragment.HomeFragment;
import com.example.asus.car.R;

/**
 * Created by asus on 2016/10/23.
 */
public class HomeWindow extends Activity implements View.OnClickListener{
    private ImageView IvReturnHome;
    MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private LocationClient mLocationClient;
    private MyLocationListener mLocationListener;
    private boolean isFirstin = true;
    private double mLatitude;
    private double mLongtitude;
    ///  private MyLocationListener mLocationListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.home_table_layout);
        mMapView = (MapView) findViewById(R.id.bmapView);

        initView();
        initLocation();
    }

    private void initLocation() {
        mLocationClient = new LocationClient(this);

        mLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mLocationListener);
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");//
        option.setIsNeedAddress(true);//当前位置
        option.setOpenGps(true);//gps
        option.setScanSpan(1000);//每隔一秒进行请求
        mLocationClient.setLocOption(option);
    }

    private void initView() {
        mBaiduMap = mMapView.getMap();
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(18.0f);//按照比例显示地图
        mBaiduMap.setMapStatus(msu);
        IvReturnHome = (ImageView) findViewById(R.id.home_window_return_home);
        IvReturnHome.setOnClickListener(this);
    }

    //定位的监听
    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            MyLocationData data = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                    .latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude()).build();
            mBaiduMap.setMyLocationData(data);


            mLatitude = bdLocation.getLatitude();
            mLongtitude = bdLocation.getLongitude();

            if(isFirstin){
                LatLng latLng = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());
                MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
                mBaiduMap.animateMapStatus(msu);
                isFirstin = false;;
            }

        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_window_return_home:
                finish();
                break;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        //开启定位
        mBaiduMap.setMyLocationEnabled(true);//开启定位
        if(!mLocationClient.isStarted()){//判断是否启动
            mLocationClient.start();
        }
        //开启方向传感器
    }
}
