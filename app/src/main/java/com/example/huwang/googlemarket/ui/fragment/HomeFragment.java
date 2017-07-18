package com.example.huwang.googlemarket.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.huwang.googlemarket.bean.AppInfo;
import com.example.huwang.googlemarket.http.protocol.HomeProtocol;
import com.example.huwang.googlemarket.ui.activity.HomeDetailActivity;
import com.example.huwang.googlemarket.ui.adapter.MyBaseAdapter;
import com.example.huwang.googlemarket.ui.holder.BaseHolder;
import com.example.huwang.googlemarket.ui.holder.HomeHeaderHolder;
import com.example.huwang.googlemarket.ui.holder.HomeHolder;
import com.example.huwang.googlemarket.ui.view.LoadingPage;
import com.example.huwang.googlemarket.ui.view.MyListView;
import com.example.huwang.googlemarket.utils.LogUtils;
import com.example.huwang.googlemarket.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huwang on 2017/5/17.
 */

public class HomeFragment extends BaseFragment {
    private ArrayList<String> mPictureList;
    private ArrayList<AppInfo> data;
//    private List<String> datas = new ArrayList<>();

    /**
     * 运行在子线程
     *
     * @return
     */
    @Override
    protected LoadingPage.ResultState onLoadData() {
//        String item = null;
//        for (int i = 0; i < 20; i++) {
//            item = new String("data  " + i);
//            datas.add(item);
//        }

        HomeProtocol protocol = new HomeProtocol();
        data = protocol.getData(0);// 加载第一页数据

        mPictureList = protocol.getPictureList();
        return check(data);
    }



    /**
     * 只有加载成功之后会回调
     *
     * @return
     */
    @Override
    public View onSuccessView() {
        MyListView listView = new MyListView(UIUtils.getContext());


        HomeHeaderHolder headerHolder = new HomeHeaderHolder();
        listView.addHeaderView(headerHolder.getRootView());

        if (mPictureList != null){
            headerHolder.setData(mPictureList);
        }

        listView.setAdapter(new HomeAdapter(data));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtils.i( position + "");
                AppInfo appInfo = data.get(position-1);//  去除头布局
                if (appInfo != null) {
                    Intent intent = new Intent(UIUtils.getContext(), HomeDetailActivity.class);
                    intent.putExtra("package", appInfo.packageName);
                    startActivity(intent);
                }
            }
        });
        return listView;
    }

    class HomeAdapter extends MyBaseAdapter<AppInfo> {
        public HomeAdapter(List<AppInfo> data) {
            super(data);
        }

        @Override
        public boolean hasMore() {
            return true;
        }

        @Override
        public ArrayList<AppInfo> onLoadMore() {
//            ArrayList<AppInfo> items = new ArrayList<>();
//            for (int i = 0; i < 10; i++) {
//                items.add(new String("测试的数据data  " + i));
//            }
//            SystemClock.sleep(1000);
            HomeProtocol protocol = new HomeProtocol();
//            int index = data.size();
            ArrayList<AppInfo> moreData = protocol.getData(getListSize());
            return moreData;
        }

        @Override
        public BaseHolder<AppInfo> getHolder(int position) {
            return new HomeHolder();
        }

//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ViewHolder viewHolder;
//            if (convertView == null) {
//                convertView = UIUtils.inflate(R.layout.list_item_home);
//                viewHolder = new ViewHolder();
//                viewHolder.mTextView = (TextView) convertView.findViewById(R.id.home_text);
//                convertView.setTag(viewHolder);
//            } else {
//                viewHolder = (ViewHolder) convertView.getTag();
//            }
//            viewHolder.mTextView.setText((String) getItem(position));
//
//            return convertView;
//        }
    }
//    static class ViewHolder {
//        public TextView mTextView;
//    }
}
