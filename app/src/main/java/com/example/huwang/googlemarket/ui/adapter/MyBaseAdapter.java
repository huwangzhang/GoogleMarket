package com.example.huwang.googlemarket.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.example.huwang.googlemarket.manager.ThreadManager;
import com.example.huwang.googlemarket.ui.holder.BaseHolder;
import com.example.huwang.googlemarket.ui.holder.MoreHolder;
import com.example.huwang.googlemarket.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huwang on 2017/5/17.
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {
    private List<T> data;

    private static final int TYPE_NORMAL = 1; // 普通布局

    private static final int TYPE_MORE = 0;

    public MyBaseAdapter(List<T> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size() + 1;  // 有个更多布局的的数量
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    /**
     * @return 返回布局类型的个数
     */
    @Override
    public int getViewTypeCount() {
        return 2;   // 普通布局，加载更多 布局
    }

    /**
     * @param position
     * @return 当前位置的布局类型
     */
    @Override
    public int getItemViewType(int position) {
        if (position == getCount() - 1) {  // getCount-1最后一个
            return TYPE_MORE;
        } else {
            return getInnerType(position);
        }
    }

    /**
     * 子类可以重写此方法，修改返回的布局类型
     *
     * @return
     */
    public int getInnerType(int position) {
        return TYPE_NORMAL;  // 默认普通布局
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder;
        if (convertView == null) {
            if (getItemViewType(position) == TYPE_MORE) {
                // 加载更多类型
                holder = new MoreHolder(hasMore());
            } else {
                holder = getHolder(position);
            }
        } else {
            holder = (BaseHolder) convertView.getTag();
        }
        if (getItemViewType(position) != TYPE_MORE) {
            holder.setData(getItem(position));
        } else {
            MoreHolder moreHolder = (MoreHolder) holder;
            // 有更多数据时，加载
            if (moreHolder.getData() == MoreHolder.STATE_MORE_LOADING) {
                loadMore(moreHolder);
            }
        }
        return holder.getRootView();
    }

    /**
     * 返回当前页面对象，子类实现
     *
     * @return
     */
    public abstract BaseHolder<T> getHolder(int position);

    /**
     * 子类可以重写是否可以加载更多。默认有更多数据
     *
     * @return
     */
    public boolean hasMore() {
        return true;
    }

    private boolean isLoadMore = false;

    /**
     * 加载更多数据
     */
    public void loadMore(final MoreHolder holder) {
        if (!isLoadMore) {
            isLoadMore = true;

            ThreadManager.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    // 访问网络数据
                    final ArrayList<T> moreData = onLoadMore();

                    UIUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (moreData != null) {
                                ////////// 分页
                                if (moreData.size() < 20) {
                                    holder.setData(MoreHolder.STATE_MORE_NONE);
                                    Toast.makeText(UIUtils.getContext(), "没有更多数据", Toast.LENGTH_SHORT).show();
                                } else {
                                    holder.setData(MoreHolder.STATE_MORE_LOADING);
                                }
                                // 将数据加入集合中
                                data.addAll(moreData);
                                MyBaseAdapter.this.notifyDataSetChanged();
                            } else {
                                // 加载失败
                                holder.setData(MoreHolder.STATE_MORE_ERROR);
                            }
                            isLoadMore = false;
                        }
                    });
                }
            });
//            new Thread() {
//                @Override
//                public void run() {
//                    // 访问网络数据
//                    final ArrayList<T> moreData = onLoadMore();
//
//                    UIUtils.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (moreData != null) {
//                                ////////// 分页
//                                if (moreData.size() < 20) {
//                                    holder.setData(MoreHolder.STATE_MORE_NONE);
//                                    Toast.makeText(UIUtils.getContext(), "没有更多数据", Toast.LENGTH_SHORT).show();
//                                } else {
//                                    holder.setData(MoreHolder.STATE_MORE_LOADING);
//                                }
//                                // 将数据加入集合中
//                                data.addAll(moreData);
//                                MyBaseAdapter.this.notifyDataSetChanged();
//                            } else {
//                                // 加载失败
//                                holder.setData(MoreHolder.STATE_MORE_ERROR);
//                            }
//                            isLoadMore = false;
//                        }
//                    });
//
//                }
//            }.start();
        }
    }

    /**
     * 加载更多数据
     */
    public abstract ArrayList<T> onLoadMore();

    public int getListSize() {
        return data.size();
    }
}
