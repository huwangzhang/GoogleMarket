package com.example.huwang.googlemarket.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

/**
 * Created by huwang on 2017/5/17.
 */

public class GooglePlayApplication extends Application {
    private static Context mContext;
    private static Handler mHanler;
    private static int mMainThreadId;
    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        mHanler = new Handler();
        mMainThreadId = Process.myTid();
    }

    public static Context getContext() {
        return mContext;
    }

    public static Handler getHanler() {
        return mHanler;
    }

    public static int getMainThreadId() {
        return mMainThreadId;
    }
}
