package com.androidxx.yangjw.materialdemo.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.androidxx.yangjw.materialdemo.custom.CustomImageView;

/**
 * Created by yangjw on 2016/6/2.
 */
public class CustomBehavior extends CoordinatorLayout.Behavior<View> {


    /**
     *
     */
    public CustomBehavior() {
    }

    public CustomBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 当前的行为依赖某一个控件
     * @param parent
     * @param child 行为的执行者
     * @param dependency 被依赖的对象
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof CustomImageView;
    }

    int scale = 1;
    /**
     * 表示被依赖的对象发现变化时，会回调此方法。
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {

        ViewCompat.offsetTopAndBottom(child,-10);
        ViewCompat.offsetLeftAndRight(child,-10);
//        ViewCompat.setScaleX(child,scale--);
//        ViewCompat.setScaleY(child,scale--);
        return true;
    }
}
