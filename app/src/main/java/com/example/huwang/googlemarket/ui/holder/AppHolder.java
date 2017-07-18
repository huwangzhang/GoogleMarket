package com.example.huwang.googlemarket.ui.holder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.huwang.googlemarket.R;
import com.example.huwang.googlemarket.bean.AppInfo;
import com.example.huwang.googlemarket.http.HttpHelper;
import com.example.huwang.googlemarket.utils.BitmapHelper;
import com.example.huwang.googlemarket.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

/**
 * Created by huwang on 2017/5/17.
 */

public class AppHolder extends BaseHolder<AppInfo> {

    private ImageView mIconIV;
    private TextView mNameTV;
    private TextView mSizeTV;
    private ImageView mIconRightIV;
    private TextView mDownloadIV;
    private TextView mDesTV;
    private RatingBar mRatingBar;


    private BitmapUtils mBitmapUtils;
    //    private TextView mContentTV;
    @Override
    public View initView() {
        // 加载布局
        View view = UIUtils.inflate(R.layout.list_item_home);
        // 初始化控件
        mIconIV = (ImageView) view.findViewById(R.id.iv_icon_home);
        mNameTV = (TextView) view.findViewById(R.id.tv_name_home);
//        mContentTV = (TextView) view.findViewById(R.id.home_text);
        mSizeTV = (TextView) view.findViewById(R.id.tv_size_home);
        mIconRightIV = (ImageView) view.findViewById(R.id.iv_icon_right);
        mDownloadIV = (TextView) view.findViewById(R.id.tv_download_home);
        mDesTV = (TextView) view.findViewById(R.id.tv_des_home);
        mRatingBar = (RatingBar) view.findViewById(R.id.rb_star_home);
        mBitmapUtils = BitmapHelper.getBitmapUtils();
        return view;
    }

    @Override
    public void refreshView(AppInfo data) {
//        mContentTV.setText(data.name);
        mNameTV.setText(data.name);
        mSizeTV.setText(Formatter.formatFileSize(UIUtils.getContext(), data.size));
        mDesTV.setText(data.des);
        mRatingBar.setRating((float) data.stars);

        mBitmapUtils.display(mIconIV, HttpHelper.URL + "image?name=" + data.iconUrl);
//        mDownloadIV
    }
}
