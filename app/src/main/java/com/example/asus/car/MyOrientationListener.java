package com.example.asus.car;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by asus on 2016/10/13.
 */
public class MyOrientationListener implements SensorEventListener {

    private SensorManager mSensorManager;
    private Context mContext;
    private Sensor mSonsor;
    private float lastX;

    public MyOrientationListener(Context context){
        this.mContext = context;
    }

    //开始监听
    public void start(){
        // mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);

        if(mSensorManager != null){
            //获得方向传感器
            mSonsor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        }
       if(mSonsor != null){
            mSensorManager.registerListener(this,mSonsor,
                    SensorManager.SENSOR_DELAY_UI);
        }
    }

    //停止监听
    public void stop(){
        mSensorManager.unregisterListener(this);
    }
    /**
     * 方向传感器 方向发生变化
     * @param sensorEvent
     */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //如果传感器类型是方向传感器
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ORIENTATION){
            float x= sensorEvent.values[SensorManager.DATA_X];

            if(Math.abs(x - lastX)>1.0){
                if(mOnOrientationListener != null){
                    mOnOrientationListener.onOrientationChanged(x);
                }
            }

            lastX = x;
        }
    }



    private OnOrientationListener mOnOrientationListener;

    public void setOnOrientationListener(OnOrientationListener mOnOrientationListener) {
        this.mOnOrientationListener = mOnOrientationListener;
    }

    public interface OnOrientationListener{
        void onOrientationChanged(float x);
    }

    /**
     *
     * @param sensor
     * @param i
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
