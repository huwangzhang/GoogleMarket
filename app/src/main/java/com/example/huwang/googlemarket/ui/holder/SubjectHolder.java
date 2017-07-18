package com.example.huwang.googlemarket.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huwang.googlemarket.R;
import com.example.huwang.googlemarket.bean.SubjectInfo;
import com.example.huwang.googlemarket.http.HttpHelper;
import com.example.huwang.googlemarket.utils.BitmapHelper;
import com.example.huwang.googlemarket.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

/**
 * Created by huwang on 2017/5/18.
 */

public class SubjectHolder extends BaseHolder<SubjectInfo> {
    private ImageView mIvSubject;
    private TextView mTvDes;
    private BitmapUtils mBitmapUtils;

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.list_item_subject);
        mIvSubject = (ImageView) view.findViewById(R.id.iv_pic_subject);
        mTvDes = (TextView) view.findViewById(R.id.tv_des_subject);
        mBitmapUtils = BitmapHelper.getBitmapUtils();

        return view;
    }

    @Override
    public void refreshView(SubjectInfo data) {
        mTvDes.setText(data.des);
        mBitmapUtils.display(mIvSubject, HttpHelper.URL + "image?name=" + data.url);
    }
}
