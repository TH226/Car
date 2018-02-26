package com.example.asus.car.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.car.R;

/**
 * 归属地显示框显示位置
 * Created by asus on 2016/8/5.
 */
public class DragViewActivity extends Activity {
    private ImageView ivDrag;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drag_view_activity);

        mPref = getSharedPreferences("config", MODE_PRIVATE);


        ivDrag = (ImageView) findViewById(R.id.iv_drag);

        //记录提示框当前位置
        int lastX = mPref.getInt("lastX", 0);
        int lastY = mPref.getInt("lastY", 0);


        //onMeasure(测量view) onLayout(安放位置) onDraw(绘制)
       // ivDrag.layout(lastX, lastY, lastX + ivDrag.getWidth(), lastY + ivDrag.getHeight());


        //屏幕宽高
        final int winWidth = getWindowManager().getDefaultDisplay().getWidth();
        final int winHeight = getWindowManager().getDefaultDisplay().getHeight();

        if(lastY > winHeight/ 2){//上边显示，下边隐藏

        }else{//上边隐藏，下边显示

        }

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)
                ivDrag.getLayoutParams();
        layoutParams.leftMargin = lastX;//设置左边距
        layoutParams.topMargin = lastY;//设置顶边距
        ivDrag.setLayoutParams(layoutParams);//重新设置位置
        final long[] mHits = new long[2];
        ivDrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.arraycopy(mHits,1,mHits,0,mHits.length - 1);
                mHits[mHits.length-1] = SystemClock.uptimeMillis();//开机后开始计算的时间
                //图片居中
                if(mHits[0]>=(SystemClock.uptimeMillis()-500)){
                    ivDrag.layout(winWidth/2-ivDrag.getWidth()/2,
                            ivDrag.getTop(),winWidth/2+ivDrag.getWidth()/2,
                    ivDrag.getBottom());
                }
            }
        });
        ivDrag.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //初始化起点坐标
                        startX = (int) event.getRawX();
                        startY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //移动的终点坐标
                        endX = (int) event.getRawX();
                        endY = (int) event.getRawY();

                        //计算偏移量
                        int dX = endX - startX;
                        int dY = endY - startY;

                        //更新上下左右距离
                        int l = ivDrag.getLeft() + dX;
                        int t = ivDrag.getTop() + dY;
                        int r = ivDrag.getRight() + dX;
                        int b = ivDrag.getBottom() + dY;
                        //判断是否超出屏幕边界，注意状态栏高度
                        if(l<0||r>winWidth||t<0||b>(winHeight-30)){
                            break;
                        }

                        if(t > winHeight/ 2){//上边显示，下边隐藏

                        }else{//上边隐藏，下边显示

                        }
                        //更新界面
                        ivDrag.layout(l, t, r, b);
                        //初始化起点坐标
                        startX = (int) event.getRawX();
                        startY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        //记录坐标点
                        Editor edit = mPref.edit();
                        edit.putInt("lastX", ivDrag.getLeft());
                        edit.putInt("lastY", ivDrag.getTop());
                        edit.commit();
                        //Toast.makeText(DragViewActivity.this, "1", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;//时间向下传递，让双击事件可以响应
            }
        });
    }
}
