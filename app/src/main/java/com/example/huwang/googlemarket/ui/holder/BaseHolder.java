package com.example.huwang.googlemarket.ui.holder;

import android.view.View;

/**
 * Created by huwang on 2017/5/17.
 */

public abstract class BaseHolder<T> {
    private View mRootView; // 根布局
    private T mData;

    public BaseHolder() {
        mRootView = initView();
        // 标记
        mRootView.setTag(this);
    }

    /**
     * 加载布局文件
     * findViewById
     *
     * @return
     */
    public abstract View initView();

    /**
     * 刷新数据
     *
     * @param data
     */
    public abstract void refreshView(T data);


    public View getRootView() {
        return mRootView;
    }

    public void setData(T data) {
        mData = data;
        refreshView(data);
    }

    public T getData() {
        return mData;
    }
}
