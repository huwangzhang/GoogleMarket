package com.example.huwang.googlemarket.ui.fragment;

import android.view.View;
import android.widget.ImageView;

import com.example.huwang.googlemarket.R;
import com.example.huwang.googlemarket.ui.view.LoadingPage;
import com.example.huwang.googlemarket.utils.UIUtils;

/**
 * Created by huwang on 2017/5/17.
 */

public class GameFragment extends BaseFragment {
    @Override
    protected LoadingPage.ResultState onLoadData() {
        return LoadingPage.ResultState.STATE_SUCCESS;
    }

    @Override
    public View onSuccessView() {
        ImageView imageView = new ImageView(UIUtils.getContext());
        imageView.setImageResource(R.drawable.ic_launcher);

        return imageView;
    }
}
