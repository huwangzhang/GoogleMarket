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
 * Created by huwang on 2017/5/20.
 */

public class DetailAppInfoHolder extends BaseHolder<AppInfo> {
    private ImageView mIcon;
    private TextView mName;
    private RatingBar mStar;
    private TextView mDownloadNum;
    private TextView mVersion;
    private TextView mDate;
    private TextView mSize;

    private BitmapUtils mBitmapUtils;

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.page_detail_appinfo);

        mIcon = (ImageView) view.findViewById(R.id.iv_icon);
        mName = (TextView) view.findViewById(R.id.tv_name);
        mStar = (RatingBar) view.findViewById(R.id.rb_star);
        mDownloadNum = (TextView) view.findViewById(R.id.tv_download_num);
        mVersion = (TextView) view.findViewById(R.id.tv_version);
        mDate = (TextView) view.findViewById(R.id.tv_date);
        mSize = (TextView) view.findViewById(R.id.tv_size);

        mBitmapUtils = BitmapHelper.getBitmapUtils();

        return view;
    }

    @Override
    public void refreshView(AppInfo data) {
        mBitmapUtils.display(mIcon, HttpHelper.URL + "image?name=" + data.iconUrl);
        mName.setText(data.name);
        mDownloadNum.setText("下载量: " + data.downloadNum);
        mVersion.setText("版本号: " + data.version);
        mDate.setText(data.date);
        mSize.setText(Formatter.formatFileSize(UIUtils.getContext(), data.size));
        mStar.setRating((float) data.stars);
    }
}
