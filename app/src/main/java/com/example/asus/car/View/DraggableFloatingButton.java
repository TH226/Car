package com.example.asus.car.View;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by asus on 2016/10/19.
 */
public class DraggableFloatingButton extends FloatingActionButton {

    public DraggableFloatingButton(Context context) {
        super(context);
    }

    public DraggableFloatingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // 记录上次的xy坐标
    private int mLastX, mLastY;
    // 记录按下和松开手指的时间
    private long mDownTime, mUpTime;

    // 记录上次移动完的上下左右坐标
    private int mLastLeft = -1;
    private int mLastRight = -1;
    private int mLastTop = -1;
    private int mLastBottom = -1;

    public int getLastLeft() {
        return mLastLeft;
    }

    public int getLastRight() {
        return mLastRight;
    }

    public int getLastTop() {
        return mLastTop;
    }

    public int getLastBottom() {
        return mLastBottom;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownTime = System.currentTimeMillis();
                break;
            case MotionEvent.ACTION_MOVE:

                int deltaX = x - mLastX;
                int deltaY = y - mLastY;

                // 移动后的上下左右坐标
                int left = getLeft() + deltaX;
                int right = getRight() + deltaX;
                int top = getTop() + deltaY;
                int bottom = getBottom() + deltaY;

                int marginLeft = 0;
                int marginRight = 0;
                int marginTop = 0;
                int marginBottom = 0;

                if (getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                    // 获取margin值，做移动的边界判断
                    ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) getLayoutParams();
                    marginLeft = lp.leftMargin;
                    marginRight = lp.rightMargin;
                    marginTop = lp.topMargin;
                    marginBottom = lp.bottomMargin;
                }

                int parentWidth = 0;
                int parentHeight = 0;

                if (getParent() != null && getParent() instanceof ViewGroup) {

                    ViewGroup parent = (ViewGroup) getParent();

                    // 拿到父布局宽高
                    parentWidth = parent.getWidth();
                    parentHeight = parent.getHeight();

                    if (left < marginLeft) {
                        // 移到到最左的时候，限制在marginLeft
                        left = marginLeft;
                        right = getWidth() + left;
                    }

                    if (right > parentWidth - marginRight) {
                        // 移动到最右
                        right = parentWidth - marginRight;
                        left = right - getWidth();
                    }

                    if (top < marginTop) {
                        // 移动到顶部
                        top = marginTop;
                        bottom = getHeight() + top;
                    }

                    if (bottom > parentHeight - marginBottom) {
                        // 移动到底部
                        bottom = parentHeight - marginBottom;
                        top = bottom - getHeight();
                    }

                    layout(left, top, right, bottom);

                    // 记录移动后的坐标值
                    mLastLeft = left;
                    mLastRight = right;
                    mLastTop = top;
                    mLastBottom = bottom;

                }

                break;
            case MotionEvent.ACTION_UP:
                mLastX = x;
                mLastY = y;
                mUpTime = System.currentTimeMillis();
                // 点击事件的处理
                return mUpTime - mDownTime > 200 || super.onTouchEvent(event);

        }

        mLastX = x;
        mLastY = y;

        return super.onTouchEvent(event);
    }
}

