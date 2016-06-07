package com.androidxx.yangjw.drawerpagerdemo;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by yangjw on 2016/6/7.
 */
public class MyListView extends ListView{
    private float startX,startY;
    private float distance;
    private boolean isLocked;
    private TextView childView;

    public MyListView(Context context) {
        this(context,null);
    }

    public MyListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                distance = startX - ev.getX();
                if (distance > 100) {
                    if (childView != null) {
                        childView.setBackgroundColor(Color.RED);
                        childView.setText("向左滑动");
                        isLocked = true;
                    }
                } else if (distance < -100) {
                    if (childView != null) {
                        childView.setBackgroundColor(Color.TRANSPARENT);
                        isLocked = false;
                        childView.setText("向右滑动");
                    }
                }
                break;
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                startY = ev.getY();
                //通过坐标获得ListView中Item的索引
                int position = pointToPosition((int)startX, (int)startY);
                childView = (TextView) getChildAt(position);

                break;
            case MotionEvent.ACTION_UP:
//                if (childView != null) {
//                    childView.setBackgroundColor(Color.TRANSPARENT);
//                }
                break;
        }

        if (isLocked) {
            return true;
        }
        return super.onTouchEvent(ev);
    }
}
