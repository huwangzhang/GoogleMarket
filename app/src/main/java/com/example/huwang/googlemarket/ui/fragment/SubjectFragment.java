package com.example.huwang.googlemarket.ui.fragment;

import android.view.View;

import com.example.huwang.googlemarket.bean.SubjectInfo;
import com.example.huwang.googlemarket.http.protocol.SubjectProtocol;
import com.example.huwang.googlemarket.ui.adapter.MyBaseAdapter;
import com.example.huwang.googlemarket.ui.holder.BaseHolder;
import com.example.huwang.googlemarket.ui.holder.SubjectHolder;
import com.example.huwang.googlemarket.ui.view.LoadingPage;
import com.example.huwang.googlemarket.ui.view.MyListView;
import com.example.huwang.googlemarket.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huwang on 2017/5/17.
 */

public class SubjectFragment extends BaseFragment {
    private ArrayList<SubjectInfo> data;
    @Override
    protected LoadingPage.ResultState onLoadData() {
        SubjectProtocol subjectProtocol = new SubjectProtocol();
        data = subjectProtocol.getData(0);
        return check(data);
    }

    @Override
    public View onSuccessView() {
        MyListView view = new MyListView(UIUtils.getContext());
        view.setAdapter(new SubjectAdapter(data));
        return view;
    }

    class SubjectAdapter extends MyBaseAdapter<SubjectInfo> {

        public SubjectAdapter(List<SubjectInfo> data) {
            super(data);
        }

        @Override
        public BaseHolder<SubjectInfo> getHolder(int position) {
            return new SubjectHolder();
        }

        @Override
        public ArrayList<SubjectInfo> onLoadMore() {
            SubjectProtocol subjectProtocol = new SubjectProtocol();
            ArrayList<SubjectInfo> moreData = subjectProtocol.getData(getListSize());
            return moreData;
        }
    }
}
