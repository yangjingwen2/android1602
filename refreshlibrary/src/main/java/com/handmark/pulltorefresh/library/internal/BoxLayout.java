package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.R;

/**
 * Created by yangjw on 2016/5/19.
 * url：androidxx.cn
 * desc：TODO
 */
public class BoxLayout extends LoadingLayout {
    private static final String TAG = "androidxx";

    private AnimationDrawable mBoxDrawable;
    public BoxLayout(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation scrollDirection, TypedArray attrs) {
        super(context, mode, scrollDirection, attrs);
        mBoxImage.setImageResource(R.drawable.box_anim);
        mBoxDrawable = (AnimationDrawable) mBoxImage.getDrawable();
    }

    /**
     *
     * 返回一个默认的图片
     * @return
     *
     */
    @Override
    protected int getDefaultDrawableResId() {
        return R.drawable.box_01;
    }

    @Override
    protected void onLoadingDrawableSet(Drawable imageDrawable) {

    }

    @Override
    protected void onPullImpl(float scaleOfLayout) {
//        mBoxImage.setVisibility(VISIBLE);
    }

    /**
     * 拉动刷新头的时候执行的方法
     */
    @Override
    protected void pullToRefreshImpl() {

    }

    /**
     * 正在刷新。松开手指，开始刷新，回调此方法
     */
    @Override
    protected void refreshingImpl() {
        Log.d(TAG, "refreshingImpl: ");
        //播放动画
        mBoxDrawable.start();
    }

    /**
     * 松开
     */
    @Override
    protected void releaseToRefreshImpl() {

    }

    /**
     * 重置：当刷新完成之后，应该执行重置
     */
    @Override
    protected void resetImpl() {
        //播放结束或者重新开始的时候，需要重置
        mBoxImage.clearAnimation();
        mBoxImage.setVisibility(VISIBLE);
    }
}
