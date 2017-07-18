package com.example.huwang.googlemarket.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huwang.googlemarket.ui.view.LoadingPage;
import com.example.huwang.googlemarket.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by huwang on 2017/5/17.
 * 抽取fragment相互之间的共性
 */

public abstract class BaseFragment extends Fragment {
    private LoadingPage mLoadingPage;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mLoadingPage = new LoadingPage(UIUtils.getContext()){
            @Override
            public View onSuccessView() {
                return BaseFragment.this.onSuccessView();
            }

            @Override
            public ResultState onLoadData() {
                return BaseFragment.this.onLoadData();
            }
        };
        return mLoadingPage;
    }

    protected abstract LoadingPage.ResultState onLoadData();

    /**
     * 加载成功的布局
     * @return
     */
    protected abstract View onSuccessView();

    public void loadData() {
        if (mLoadingPage != null) {
            mLoadingPage.loadData();
        }
    }

    /**
     * 校验数据是否合法
     * @param obj
     * @return
     */
    public LoadingPage.ResultState check(Object obj) {
        if (obj != null) {
            if (obj instanceof ArrayList) {
                ArrayList list = (ArrayList) obj;
                if (list.isEmpty()) {
                    return LoadingPage.ResultState.STATE_EMPTY;
                } else {
                    return LoadingPage.ResultState.STATE_SUCCESS;
                }
            }
        }
        return LoadingPage.ResultState.STATE_ERROR;
    }
}
