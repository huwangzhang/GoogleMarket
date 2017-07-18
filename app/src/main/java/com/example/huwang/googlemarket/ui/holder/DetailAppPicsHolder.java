package com.example.huwang.googlemarket.ui.holder;

import android.view.View;
import android.widget.ImageView;

import com.example.huwang.googlemarket.R;
import com.example.huwang.googlemarket.bean.AppInfo;
import com.example.huwang.googlemarket.http.HttpHelper;
import com.example.huwang.googlemarket.utils.BitmapHelper;
import com.example.huwang.googlemarket.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

/**
 * 详情页-应用图片
 * 
 * @author Kevin
 */
public class DetailAppPicsHolder extends BaseHolder<AppInfo> {

	private ImageView[] mImageViews;
	private BitmapUtils mBitmapUtils;

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.page_detail_picinfo);

		mImageViews = new ImageView[5];
		mImageViews[0] = (ImageView) view.findViewById(R.id.iv_pic1);
		mImageViews[1] = (ImageView) view.findViewById(R.id.iv_pic2);
		mImageViews[2] = (ImageView) view.findViewById(R.id.iv_pic3);
		mImageViews[3] = (ImageView) view.findViewById(R.id.iv_pic4);
		mImageViews[4] = (ImageView) view.findViewById(R.id.iv_pic5);

		mBitmapUtils = BitmapHelper.getBitmapUtils();

		return view;
	}

	@Override
	public void refreshView(AppInfo data) {
		if (data != null) {
			ArrayList<String> list = data.screen;
			for (int i = 0; i < 5; i++) {
				if (i < list.size()) {
					mImageViews[i].setVisibility(View.VISIBLE);
					mBitmapUtils.display(mImageViews[i], HttpHelper.URL
							+ "image?name=" + list.get(i));
				} else {
					mImageViews[i].setVisibility(View.GONE);
				}
			}
		}
	}

}
