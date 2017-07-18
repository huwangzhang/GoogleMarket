package com.example.huwang.googlemarket.ui.fragment;

import android.view.View;

import com.example.huwang.googlemarket.bean.AppInfo;
import com.example.huwang.googlemarket.http.protocol.AppProtocol;
import com.example.huwang.googlemarket.ui.adapter.MyBaseAdapter;
import com.example.huwang.googlemarket.ui.holder.AppHolder;
import com.example.huwang.googlemarket.ui.holder.BaseHolder;
import com.example.huwang.googlemarket.ui.view.LoadingPage;
import com.example.huwang.googlemarket.ui.view.MyListView;
import com.example.huwang.googlemarket.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huwang on 2017/5/17.
 */

public class AppFragment extends BaseFragment {
    private ArrayList<AppInfo> data;

    @Override
    protected LoadingPage.ResultState onLoadData() {
        AppProtocol appProtocol = new AppProtocol();
        data = appProtocol.getData(0);

        return check(data);
    }

    @Override
    public View onSuccessView() {
        MyListView listView = new MyListView(UIUtils.getContext());

        listView.setAdapter(new AppAdapter(data));
        return listView;
    }

    class AppAdapter extends MyBaseAdapter<AppInfo> {

        public AppAdapter(List<AppInfo> data) {
            super(data);
        }

        @Override
        public BaseHolder<AppInfo> getHolder(int position) {
            return new AppHolder();
        }

        @Override
        public ArrayList<AppInfo> onLoadMore() {
            AppProtocol protocol = new AppProtocol();
            ArrayList<AppInfo> moreData = protocol.getData(getListSize());
            return moreData;
        }
    }
}
