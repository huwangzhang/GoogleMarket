package com.example.huwang.googlemarket.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huwang.googlemarket.http.protocol.HotProtocol;
import com.example.huwang.googlemarket.ui.view.FlowLayout;
import com.example.huwang.googlemarket.ui.view.LoadingPage;
import com.example.huwang.googlemarket.utils.DrawableUtils;
import com.example.huwang.googlemarket.utils.UIUtils;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by huwang on 2017/5/17.
 */

public class HotFragment extends BaseFragment {
    private ArrayList<String> data;
    @Override
    protected LoadingPage.ResultState onLoadData() {
        HotProtocol protocol = new HotProtocol();

        data = protocol.getData(0);
        return check(data);
    }

    @Override
    public View onSuccessView() {
        ScrollView scrollView = new ScrollView(UIUtils.getContext());
        FlowLayout flowLayout = new FlowLayout(UIUtils.getContext());
        int padding = UIUtils.dip2px(10);
        flowLayout.setPadding(padding, padding, padding, padding);
        flowLayout.setHorizontalSpacing(UIUtils.dip2px(5));
        flowLayout.setVerticalSpacing(UIUtils.dip2px(8));
        TextView view;
        for (int i = 0; i < data.size(); i++) {
            final String keyword = data.get(i);
            view = new TextView(UIUtils.getContext());
            view.setText(keyword);
            view.setTextColor(Color.WHITE);
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

            view.setPadding(padding, padding, padding, padding);
            view.setGravity(Gravity.CENTER);


            Random rd = new Random();
            int r = 30 + rd.nextInt(200);
            int g = 30 + rd.nextInt(200);
            int b = 30 + rd.nextInt(200);

            int color = 0xffcecece;
            Drawable drawable = DrawableUtils.getStateListDrawable(Color.rgb(r, g, b), color, UIUtils.dip2px(6));
            view.setBackgroundDrawable(drawable);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(UIUtils.getContext(), keyword, Toast.LENGTH_SHORT).show();;
                }
            });
            flowLayout.addView(view);
        }
        scrollView.addView(flowLayout);
        return scrollView;
    }
}
