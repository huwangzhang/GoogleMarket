package com.example.huwang.googlemarket.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;

import com.example.huwang.googlemarket.R;
import com.example.huwang.googlemarket.bean.AppInfo;
import com.example.huwang.googlemarket.http.protocol.HomeDetailProtocol;
import com.example.huwang.googlemarket.ui.holder.DetailAppDesHolder;
import com.example.huwang.googlemarket.ui.holder.DetailAppDownloadHolder;
import com.example.huwang.googlemarket.ui.holder.DetailAppInfoHolder;
import com.example.huwang.googlemarket.ui.holder.DetailAppPicsHolder;
import com.example.huwang.googlemarket.ui.holder.DetailAppSafeHolder;
import com.example.huwang.googlemarket.ui.view.LoadingPage;
import com.example.huwang.googlemarket.utils.LogUtils;
import com.example.huwang.googlemarket.utils.UIUtils;

/**
 * Created by huwang on 2017/5/20.
 */

public class HomeDetailActivity extends BaseActivity {
    private AppInfo result;;
    private LoadingPage mLoadingPage;
    private String mPackageName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoadingPage = new LoadingPage(this) {
            @Override
            protected View onSuccessView() {
                return HomeDetailActivity.this.onCreateSuccessView();
            }

            @Override
            protected ResultState onLoadData() {
                return HomeDetailActivity.this.onLoad();
            }
        };
        LogUtils.i( "HomeDetailActivity");
        // 将View对象设置到Activity
        setContentView(mLoadingPage);
        mPackageName = getIntent().getStringExtra("package");
        mLoadingPage.loadData();  //
    }

    public View onCreateSuccessView() {
        // 成功布局
        View view = UIUtils.inflate(R.layout.page_home_detail);
        // 初始化应用信息模块
        FrameLayout detailAppinfo = (FrameLayout) view.findViewById(R.id.fl_detail_appinfo);
        // 动态填充
        DetailAppInfoHolder detailAppInfoHolder = new DetailAppInfoHolder();
        detailAppinfo.addView(detailAppInfoHolder.getRootView());
        detailAppInfoHolder.setData(result);

        // 安全描述信息
        FrameLayout detailAppSafe = (FrameLayout) view.findViewById(R.id.fl_detail_safeinfo);
        DetailAppSafeHolder safeHolder = new DetailAppSafeHolder();
        detailAppSafe.addView(safeHolder.getRootView());
        safeHolder.setData(result);

        // 图片信息
        HorizontalScrollView detailAppPic = (HorizontalScrollView) view.findViewById(R.id.hsv_detail_pics);
        DetailAppPicsHolder picsHolder = new DetailAppPicsHolder();
        detailAppPic.addView(picsHolder.getRootView());
        picsHolder.setData(result);

        // 详情布局
        FrameLayout detailAppDes = (FrameLayout) view.findViewById(R.id.fl_detail_des);
        DetailAppDesHolder desHolder = new DetailAppDesHolder();
        detailAppDes.addView(desHolder.getRootView());
        desHolder.setData(result);

        FrameLayout detailAppDownLoad = (FrameLayout) view.findViewById(R.id.fl_detail_downlod);
        DetailAppDownloadHolder downloadHolder = new DetailAppDownloadHolder();
        detailAppDownLoad.addView(downloadHolder.getRootView());
        downloadHolder.setData(result);

        return view;
    }

    public LoadingPage.ResultState onLoad() {
        // 请求网络
        HomeDetailProtocol detailProtocol = new HomeDetailProtocol(mPackageName);
        result = detailProtocol.getData(0);
        if (result != null) {
            return LoadingPage.ResultState.STATE_SUCCESS;
        } else {
            return LoadingPage.ResultState.STATE_ERROR;
        }
    }
}
