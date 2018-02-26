package com.example.asus.car.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.asus.car.MyOrientationListener;
import com.example.asus.car.R;

/**
 * Created by asus on 2016/10/8.
 */
public class MyCarFragment extends Fragment implements View.OnClickListener{


    MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private MyCarFragment context;

    //定位相关
    private LocationClient mLocationClient;
    private MyLocationListener mLocationListener;
    private boolean isFirstin = true;
    private double mLatitude;
    private double mLongtitude;


    //自定义定位图标
    private BitmapDescriptor mIconLocation;
    private MyOrientationListener myOrientationListener;
    private float mCurrentX;



    private TextView MapCommon;
    private TextView MApSite;
    private TextView MapTraffic;
    private TextView MApMyLocation;
    private AlertDialog dialog;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getActivity().getApplicationContext());
        view = inflater.inflate(R.layout.car_fragment_layout,container,false);
        mMapView = (MapView) view.findViewById(R.id.bmapView);


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();

            }
        });

        this.context = this;
        //获取地图控件引用
        initView();

        //初始化定位
        initLocation();

        initListener();

        return view;
    }

    private void initListener() {

    }

    //初始化定位
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

        //初始化图标
        mIconLocation = BitmapDescriptorFactory
                .fromResource(R.mipmap.navi_map_gps_locked);
        myOrientationListener = new MyOrientationListener(getActivity());

        myOrientationListener.setOnOrientationListener(new MyOrientationListener.OnOrientationListener() {
            @Override
            public void onOrientationChanged(float x) {
                mCurrentX = x;
            }
        });

    }


    private void showdialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        dialog = builder.create();
        View  view = View.inflate(getActivity(), R.layout.dialog_map,null);

        MapCommon = (TextView) view.findViewById(R.id.map_common);
        MApSite = (TextView) view.findViewById(R.id.map_site);
        MapTraffic = (TextView)  view.findViewById(R.id.map_traffic);
        MApMyLocation = (TextView) view.findViewById(R.id.map_location);

        MapCommon.setOnClickListener(this);
        MApSite.setOnClickListener(this);
        MapTraffic.setOnClickListener(this);
        MApMyLocation.setOnClickListener(this);

        dialog.setView(view,0,0,0,0);
        dialog.show();
    }
    private void initView() {




        mBaiduMap = mMapView.getMap();
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(18.0f);//按照比例显示地图
        mBaiduMap.setMapStatus(msu);

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
        myOrientationListener.start();

    }

    @Override
    public void onStop() {
        super.onStop();
        //停止定位
        mBaiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();
        //停止方向传感器
        myOrientationListener.stop();
    }


    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    //主菜单


    //回到我的位置
    private void centerToMyLocation(){
        LatLng latLng = new LatLng(mLatitude,mLongtitude);//我当前的位置
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(latLng)
                .zoom(18)//按照比例缩放地图
                .build();
        MapStatusUpdate msu = MapStatusUpdateFactory.newMapStatus(mMapStatus);//地图按照比例移动到我当前位置
        mBaiduMap.animateMapStatus(msu);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.map_common:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
            case R.id.map_site:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.map_traffic:
                if(mBaiduMap.isTrafficEnabled()){
                    mBaiduMap.setTrafficEnabled(false);
                    MapTraffic.setText("实时交通(off)");
                }else {
                    mBaiduMap.setTrafficEnabled(true);
                    MapTraffic.setText("实时交通(on)");
                }
                break;
            case R.id.map_location:
                centerToMyLocation();
                break;
        }
        dialog.dismiss();
    }

    //定位的监听
    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            MyLocationData data = new MyLocationData.Builder()
                    .direction(mCurrentX)
                    .accuracy(bdLocation.getRadius())
                    .latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude()).build();
            mBaiduMap.setMyLocationData(data);

            //自定义图标
            MyLocationConfiguration config = new MyLocationConfiguration
                    (MyLocationConfiguration.LocationMode.NORMAL,true,mIconLocation);
            mBaiduMap.setMyLocationConfigeration(config);

            mLatitude = bdLocation.getLatitude();
            mLongtitude = bdLocation.getLongitude();

            if(isFirstin){
                LatLng latLng = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());
                MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
                mBaiduMap.animateMapStatus(msu);
                isFirstin = false;
                Toast.makeText(getActivity(), bdLocation.getAddrStr(),
                        Toast.LENGTH_SHORT).show();
            }

        }
    }
}
