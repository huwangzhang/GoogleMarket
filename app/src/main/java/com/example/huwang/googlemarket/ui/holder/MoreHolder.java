package com.example.huwang.googlemarket.ui.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huwang.googlemarket.R;
import com.example.huwang.googlemarket.utils.UIUtils;

/**
 * Created by huwang on 2017/5/17.
 */

public class MoreHolder extends BaseHolder<Integer> {
    // 加载更多
    // 加载中
    public static final int STATE_MORE_LOADING = 0;
    // 加载更多失败
    public static final int STATE_MORE_ERROR = 1;
    // 当没有数据时，隐藏加载更多状态
    public static final int STATE_MORE_NONE = 2;
    private TextView loadError;
    private LinearLayout loadMore;
    public MoreHolder(boolean hasMore) {
        if (hasMore) {
            setData(STATE_MORE_LOADING);
        } else {
            setData(STATE_MORE_NONE);
        }
    }


    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.list_item_more);

        loadMore = (LinearLayout) view.findViewById(R.id.loading_more);

        loadError = (TextView)view.findViewById(R.id.load_error);
        return view;
    }

    @Override
    public void refreshView(Integer data) {
        switch (data) {
            case STATE_MORE_LOADING:
                loadMore.setVisibility(View.VISIBLE);
                loadError.setVisibility(View.GONE);
                break;
            case STATE_MORE_NONE:
                loadMore.setVisibility(View.GONE);
                loadError.setVisibility(View.GONE);
                break;
            case STATE_MORE_ERROR:
                loadMore.setVisibility(View.GONE);
                loadError.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
}
