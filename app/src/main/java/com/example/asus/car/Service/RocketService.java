package com.example.asus.car.Service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.asus.car.R;

public class RocketService extends Service {
    private WindowManager.LayoutParams params;
    private WindowManager mWM;
    private View viewtable;
    private int winWidth;
    private int winHeight;
    private int startX;
    private int startY;
    private ImageView IvDrag;
    private long mDownTime, mUpTime;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mWM = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        // 获取屏幕宽高
        winWidth = mWM.getDefaultDisplay().getWidth();
        winHeight = mWM.getDefaultDisplay().getHeight();

        params = new WindowManager.LayoutParams();
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        params.format = PixelFormat.TRANSLUCENT;
        params.type = WindowManager.LayoutParams.TYPE_PHONE;// 电话窗口。它用于电话交互（特别是呼入）。它置于所有应用程序之上，状态栏之下。
        params.gravity = Gravity.LEFT + Gravity.TOP;// 将重心位置设置为左上方,
        // 也就是(0,0)从左上方开始,而不是默认的重心位置
        params.setTitle("Toast");



        viewtable = View.inflate(this, R.layout.drag_view_activity, null);

        IvDrag = (ImageView) viewtable.findViewById(R.id.iv_drag);

        mWM.addView(viewtable, params);//将view添加到屏幕上
        //  Toast.makeText(RocketService.this, "11", Toast.LENGTH_SHORT).show();
//        IvDrag.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(RocketService.this, "你点击了悬浮窗", Toast.LENGTH_SHORT).show();
//            }
//        });

        viewtable.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean flag = false;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mDownTime = System.currentTimeMillis();
                        //初始化起点坐标
                        startX = (int) event.getRawX();
                        startY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        flag = true;
                        //移动的终点坐标
                        int endX = (int) event.getRawX();
                        int endY = (int) event.getRawY();

                        //计算偏移量
                        int dX = endX - startX;
                        int dY = endY - startY;

                        //更新浮窗位置
                        params.x += dX;
                        params.y += dY;

                        if (params.x < 0) {
                            params.x = 0;
                        }
                        if (params.y < 0) {
                            params.y = 0;
                        }
                        if (params.x > winWidth - viewtable.getWidth()) {
                            params.x = winWidth - viewtable.getWidth();
                        }
                        if (params.y > winHeight - viewtable.getHeight()) {
                            params.y = winHeight - viewtable.getHeight();
                        }

                        mWM.updateViewLayout(viewtable, params);

                        startX = (int) event.getRawX();
                        startY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        mUpTime = System.currentTimeMillis();
                        if(mUpTime - mDownTime < 200){
                            Toast.makeText(RocketService.this, "你点击了悬浮窗", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWM != null && viewtable != null) {
            mWM.removeView(viewtable);
            viewtable = null;
        }
    }
}
