package com.example.huwang.googlemarket.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;

import com.example.huwang.googlemarket.global.GooglePlayApplication;

/**
 * Created by huwang on 2017/5/17.
 */

public class UIUtils {
    public static Context getContext() {
        return GooglePlayApplication.getContext();
    }

    public static Handler getHandler() {
        return GooglePlayApplication.getHanler();
    }

    public static int getMainThreadId() {
        return GooglePlayApplication.getMainThreadId();
    }

    public static String getString(int id) {
        return getContext().getResources().getString(id);
    }

    /**
     * 获取字符串数组
     * @param id
     * @return
     */
    public static String[] getStringArray(int id) {
        return getContext().getResources().getStringArray(id);
    }

    public static Drawable getDrawable(int id) {
        return getContext().getResources().getDrawable(id);
    }

    public static int getColor(int id) {
        return getContext().getResources().getColor(id);
    }

    /**
     * @param id
     * @return 具体的像素值
     */
    public static int getDimen(int id) {
        return getContext().getResources().getDimensionPixelSize(id);
    }


    public static int dip2px(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip*density + 0.5f);
    }

    public static float px2dip(float px) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return  px / density;
    }

    public static View inflate(int id) {
        return View.inflate(getContext(), id, null);
    }

    public static boolean isRunOnUiThread() {
        return getMainThreadId() == android.os.Process.myTid();
    }

    /**
     * @param runnable
     * 将runnable添加到handler附加的线程中，也就是主线程
     */
    public static void runOnUiThread(Runnable runnable) {
        if (isRunOnUiThread()) {
            runnable.run();
        } else {
            getHandler().post(runnable);
        }
    }

    /**
     * @param id
     * @return  根据Id获取颜色的状态选择器
     */
    public static ColorStateList getColorStateList(int id) {
        return getContext().getResources().getColorStateList(id);
    }
}
