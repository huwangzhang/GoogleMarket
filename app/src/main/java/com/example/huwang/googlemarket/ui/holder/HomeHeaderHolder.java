package com.example.huwang.googlemarket.ui.holder;

import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.huwang.googlemarket.R;
import com.example.huwang.googlemarket.http.HttpHelper;
import com.example.huwang.googlemarket.utils.BitmapHelper;
import com.example.huwang.googlemarket.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

/**
 * Created by huwang on 2017/5/20.
 */

public class HomeHeaderHolder extends BaseHolder<ArrayList<String>> {
    private ArrayList<String> data;
    private ViewPager mViewPager;
    private LinearLayout linearLayout;
    private int mPrePoint;
    @Override
    public View initView() {
        RelativeLayout rootLayout = new RelativeLayout(UIUtils.getContext());
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, UIUtils.dip2px(150));
        rootLayout.setLayoutParams(params);

        mViewPager = new ViewPager(UIUtils.getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        rootLayout.addView(mViewPager, layoutParams);


        linearLayout = new LinearLayout(UIUtils.getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        int padding = UIUtils.dip2px(10);
        linearLayout.setPadding(padding, padding, padding, padding);

        RelativeLayout.LayoutParams linearParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        linearParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        linearParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        rootLayout.addView(linearLayout, linearParams);

        return rootLayout;
    }

    @Override
    public void refreshView(final ArrayList<String> data) {
        // viewPager数据
        this.data = data;
        mViewPager.setAdapter(new HomeHeaderAdapter());
        // 初始化第一张
        mViewPager.setCurrentItem(data.size() * 10000);

        for (int i = 0; i < data.size(); i++) {
            ImageView point = new ImageView(UIUtils.getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i == 0) {
                point.setImageResource(R.drawable.indicator_selected);
            } else {
                point.setImageResource(R.drawable.indicator_normal);

                layoutParams.leftMargin = UIUtils.dip2px(4);
            }
            point.setLayoutParams(layoutParams);
            linearLayout.addView(point);
        }

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position = position % data.size();
                ImageView view = (ImageView) linearLayout.getChildAt(position);
                view.setImageResource(R.drawable.indicator_selected);

                ImageView prePoint = (ImageView) linearLayout.getChildAt(mPrePoint);
                prePoint.setImageResource(R.drawable.indicator_normal);
                mPrePoint = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Handler handler = UIUtils.getHandler();
        HomeHeadTask task = new HomeHeadTask();
        task.start();
    }

    class HomeHeadTask implements Runnable {
        public void start() {
            // 移除所有的消息
            UIUtils.getHandler().removeCallbacksAndMessages(null);
            UIUtils.getHandler().postDelayed(this, 3000);
        }

        @Override
        public void run() {
            int currentItem = mViewPager.getCurrentItem();
            currentItem++;
            mViewPager.setCurrentItem(currentItem);
            UIUtils.getHandler().postDelayed(this, 3000);
        }
    }

    class HomeHeaderAdapter extends PagerAdapter {
        private BitmapUtils mBitmapUtils;

        public HomeHeaderAdapter() {
            mBitmapUtils = BitmapHelper.getBitmapUtils();
        }


        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position = position % data.size();
            String url = data.get(position);
            ImageView imageView = new ImageView(UIUtils.getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mBitmapUtils.display(imageView, HttpHelper.URL + "image?name=" + url);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
