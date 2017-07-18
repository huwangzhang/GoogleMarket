package com.example.huwang.googlemarket.ui.fragment;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huwang.googlemarket.http.protocol.RecommendProtocol;
import com.example.huwang.googlemarket.ui.view.LoadingPage;
import com.example.huwang.googlemarket.ui.view.fly.ShakeListener;
import com.example.huwang.googlemarket.ui.view.fly.StellarMap;
import com.example.huwang.googlemarket.utils.UIUtils;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by huwang on 2017/5/17.
 */

public class RecommendFragment extends BaseFragment {
    private ArrayList<String> data;

    @Override
    protected LoadingPage.ResultState onLoadData() {
        RecommendProtocol protocol = new RecommendProtocol();
        data = protocol.getData(0);
//        System.out.print(data.get(0));
        return check(data);
    }

    @Override
    public View onSuccessView() {
        final StellarMap stellarMap = new StellarMap(UIUtils.getContext());
        stellarMap.setAdapter(new RecommendAdapter());
        stellarMap.setRegularity(6, 9);
        int padding = UIUtils.dip2px(10);
        stellarMap.setInnerPadding(padding, padding, padding, padding);
        stellarMap.setGroup(0, true);

        ShakeListener listener = new ShakeListener(UIUtils.getContext());
        listener.setOnShakeListener(new ShakeListener.OnShakeListener() {
            @Override
            public void onShake() {
                stellarMap.zoomIn();
            }
        });



        return stellarMap;
    }

    class RecommendAdapter implements StellarMap.Adapter {

        // 多少组数据
        @Override
        public int getGroupCount() {
            return 2;
        }

        // 返回当前组的个数
        @Override
        public int getCount(int group) {
            int count = data.size() / getGroupCount();
            if (group == getGroupCount() - 1) {
                int extra = data.size() % getGroupCount();
                return count + extra;
            }
            return count;
        }

        @Override
        public View getView(int group, int position, View convertView) {
            final String key = data.get(position);
            position += group * getCount(group - 1);
            TextView textView = new TextView(UIUtils.getContext());
            textView.setText(key);
//            textView.setTextColor(Color.parseColor("#888888"));
            Random rd = new Random();
            int size = 16 + rd.nextInt(9);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);

            textView.setPadding(5, 5, 5, 5);

            int r = 30 + rd.nextInt(200);
            int g = 30 + rd.nextInt(200);
            int b = 30 + rd.nextInt(200);

            textView.setTextColor(Color.rgb(r, g, b));

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(UIUtils.getContext(), "点击了" + key, Toast.LENGTH_SHORT).show();
                }
            });
            return textView;
        }

        // 下一组的ID
        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            if (isZoomIn) {
                if (group > 0) {
                    group--;
                } else {
                    group = getGroupCount() - 1;
                }
            } else {
                if (group < getGroupCount() - 1) {
                    group++;
                } else {
                    group = 0;
                }
            }
            return 0;
        }
    }
}
