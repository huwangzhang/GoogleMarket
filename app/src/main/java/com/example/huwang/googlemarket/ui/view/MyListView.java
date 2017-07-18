package com.example.huwang.googlemarket.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by huwang on 2017/5/18.
 */

public class MyListView extends ListView {
    public MyListView(Context context) {
        this(context, null);
    }

    public MyListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        this.setSelector(new ColorDrawable()); // 去除默认选择状态
        this.setDivider(null);  // 去除分割线
        this.setCacheColorHint(Color.TRANSPARENT); // 滑动会出现黑色，将背景变为全透明
    }


}
