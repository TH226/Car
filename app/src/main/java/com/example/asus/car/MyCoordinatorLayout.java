package com.example.asus.car;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;

import com.example.asus.car.View.DraggableFloatingButton;

/**
 * Created by asus on 2016/10/19.
 */
public class MyCoordinatorLayout extends CoordinatorLayout {
    public MyCoordinatorLayout(Context context) {
        super(context);
    }

    public MyCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) instanceof DraggableFloatingButton) {
                // 为了防止浮动按钮恢复原位，布局子控件位置时使用上次记录的位置
                DraggableFloatingButton child = (DraggableFloatingButton) getChildAt(i);
                if (child.getLastLeft() != -1) {
                    child.layout(child.getLastLeft(), child.getLastTop(), child.getLastRight(), child.getLastBottom());
                }
                break;
            }
        }
    }
}
