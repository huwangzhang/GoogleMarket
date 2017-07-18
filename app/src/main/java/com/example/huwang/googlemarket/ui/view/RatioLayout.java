package com.example.huwang.googlemarket.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.example.huwang.googlemarket.R;

/**
 * Created by huwang on 2017/5/19.
 * 按照比例决定宽高
 */

public class RatioLayout extends FrameLayout {
    private float ratio;;
    public RatioLayout(@NonNull Context context) {
        this(context,null);
    }

    public RatioLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RatioLayout);
        ratio = array.getFloat(R.styleable.RatioLayout_ratio, -1);
        array.recycle();
    }

    public RatioLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY && ratio > 0) {
            int imageWidth = width - getPaddingLeft() - getPaddingRight();
            int imageHeight = (int) (imageWidth / ratio + 0.5f); // 图片要显示的高度
            height = imageHeight + getPaddingTop() + getPaddingBottom();
            // 更新高度
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
