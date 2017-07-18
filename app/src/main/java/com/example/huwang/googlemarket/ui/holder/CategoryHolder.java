package com.example.huwang.googlemarket.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huwang.googlemarket.R;
import com.example.huwang.googlemarket.bean.CategoryInfo;
import com.example.huwang.googlemarket.http.HttpHelper;
import com.example.huwang.googlemarket.utils.BitmapHelper;
import com.example.huwang.googlemarket.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

/**
 * Created by huwang on 2017/5/20.
 */

public class CategoryHolder extends BaseHolder<CategoryInfo>  implements View.OnClickListener{
    private TextView tvName1, tvName2, tvName3;
    private ImageView ivIcon1, ivIcon2, ivIcon3;
    private LinearLayout llGrid1, llGrid2, llGrid3;
    private BitmapUtils mBitmapUtils;
    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.list_item_category);
        tvName1 = (TextView) view.findViewById(R.id.tv_name1_category);
        tvName2 = (TextView) view.findViewById(R.id.tv_name2_category);
        tvName3 = (TextView) view.findViewById(R.id.tv_name3_category);
        ivIcon1 = (ImageView) view.findViewById(R.id.iv_icon1_category);
        ivIcon2 = (ImageView) view.findViewById(R.id.iv_icon2_category);
        ivIcon3 = (ImageView) view.findViewById(R.id.iv_icon3_category);

        llGrid1 = (LinearLayout) view.findViewById(R.id.ll_grid1_category);
        llGrid2 = (LinearLayout) view.findViewById(R.id.ll_grid2_category);
        llGrid3 = (LinearLayout) view.findViewById(R.id.ll_grid3_category);
        mBitmapUtils = BitmapHelper.getBitmapUtils();
        llGrid1.setOnClickListener(this);
        llGrid2.setOnClickListener(this);
        llGrid3.setOnClickListener(this);
        return view;
    }

    @Override
    public void refreshView(CategoryInfo data) {
        tvName1.setText(data.name1);
        tvName2.setText(data.name2);
        tvName3.setText(data.name3);
        if (!"".equals(data.url1)) {
            mBitmapUtils.display(ivIcon1, HttpHelper.URL + "image?name=" + data.url1);
        }
        if (!"".equals(data.url2)) {
            mBitmapUtils.display(ivIcon2, HttpHelper.URL + "image?name=" + data.url2);
        }
        if (!"".equals(data.url3)) {
            mBitmapUtils.display(ivIcon3, HttpHelper.URL + "image?name=" + data.url3);
        }
    }

    @Override
    public void onClick(View v) {
        CategoryInfo info = getData();
        switch (v.getId()) {
            case R.id.ll_grid1_category:
                Toast.makeText(UIUtils.getContext(), info.name1, Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_grid2_category:
                Toast.makeText(UIUtils.getContext(), info.name2, Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_grid3_category:
                Toast.makeText(UIUtils.getContext(), info.name3, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
