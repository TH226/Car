package com.example.asus.car.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.asus.car.R;

/**
 * Created by asus on 2016/11/3.
 */
public class CarNearFriFragment extends Fragment{
    private ImageView ReturnHome;
    MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private LocationClient mLocationClient;
    private MyLocationListener mLocationListener;
    private boolean isFirstin = true;
    private double mLatitude;
    private double mLongtitude;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        SDKInitializer.initialize(getActivity().getApplicationContext());
        view = inflater.inflate(R.layout.car_friend_activity, container, false);
        mMapView = (MapView) view.findViewById(R.id.bmapView);

        initView();
        initLocation();
        return view;
    }
    private void initLocation() {
        mLocationClient = new LocationClient(getActivity());

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
       // ReturnHome = (ImageView) view.findViewById(R.id.iv_friend_return_home);
       // ReturnHome.setOnClickListener(this);
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
