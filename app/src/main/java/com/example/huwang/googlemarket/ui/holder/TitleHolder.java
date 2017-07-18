package com.example.huwang.googlemarket.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.example.huwang.googlemarket.R;
import com.example.huwang.googlemarket.bean.CategoryInfo;
import com.example.huwang.googlemarket.utils.UIUtils;

/**
 * Created by huwang on 2017/5/20.
 */

public class TitleHolder extends BaseHolder<CategoryInfo> {
    private TextView tvTitle;
    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.list_item_title);
        tvTitle = (TextView) view.findViewById(R.id.tv_title_category);
        return view;
    }

    @Override
    public void refreshView(CategoryInfo data) {
        tvTitle.setText(data.title);
    }
}
