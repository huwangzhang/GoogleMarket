package com.example.huwang.googlemarket.utils;

import com.lidroid.xutils.BitmapUtils;

/**
 * Created by huwang on 2017/5/18.
 */

public class BitmapHelper {

    private static BitmapUtils mBitmapUtils = null;
    public static BitmapUtils getBitmapUtils() {
        if (mBitmapUtils == null) {
            synchronized (BitmapHelper.class) {
                if (mBitmapUtils == null) {
                    mBitmapUtils = new BitmapUtils(UIUtils.getContext());
                }
            }
        }
        return mBitmapUtils;
    }
}
