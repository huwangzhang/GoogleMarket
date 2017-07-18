package com.example.huwang.googlemarket.ui.fragment;

import android.view.View;

import com.example.huwang.googlemarket.bean.CategoryInfo;
import com.example.huwang.googlemarket.http.protocol.CategoryProtocol;
import com.example.huwang.googlemarket.ui.adapter.MyBaseAdapter;
import com.example.huwang.googlemarket.ui.holder.BaseHolder;
import com.example.huwang.googlemarket.ui.holder.CategoryHolder;
import com.example.huwang.googlemarket.ui.holder.TitleHolder;
import com.example.huwang.googlemarket.ui.view.LoadingPage;
import com.example.huwang.googlemarket.ui.view.MyListView;
import com.example.huwang.googlemarket.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huwang on 2017/5/17.
 */

public class CategoryFragment extends BaseFragment {
    private ArrayList<CategoryInfo> data;

    @Override
    protected LoadingPage.ResultState onLoadData() {
        CategoryProtocol protocol = new CategoryProtocol();

        data = protocol.getData(0);
        return check(data);
    }

    @Override
    public View onSuccessView() {
        MyListView listView = new MyListView(UIUtils.getContext());
        listView.setAdapter(new CategoryAdapter(data));
        return listView;
    }

    class CategoryAdapter extends MyBaseAdapter<CategoryInfo> {

        public CategoryAdapter(List<CategoryInfo> data) {
            super(data);
        }

        @Override
        public BaseHolder<CategoryInfo> getHolder(int position) {
            // 返回不同的Holder来实现
            CategoryInfo categoryInfo = data.get(position);
            if (categoryInfo.isTitle) {
                return new TitleHolder();
            } else {
                return new CategoryHolder();
            }
        }

        @Override
        public ArrayList<CategoryInfo> onLoadMore() {
            return null;
        }

        @Override
        public boolean hasMore() {
            return false;  // 没有更多数据
        }

        @Override
        public int getViewTypeCount() {
            return super.getViewTypeCount() + 1;
        }

        @Override
        public int getInnerType(int position) {
            CategoryInfo categoryInfo = data.get(position);
            if (categoryInfo.isTitle) {
                // 返回标题类型
                return super.getInnerType(position) + 1;
            } else {
                // 普通类型
                return super.getInnerType(position);
            }
        }
    }
}
