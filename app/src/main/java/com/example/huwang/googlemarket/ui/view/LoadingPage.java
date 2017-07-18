package com.example.huwang.googlemarket.ui.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.huwang.googlemarket.R;
import com.example.huwang.googlemarket.manager.ThreadManager;
import com.example.huwang.googlemarket.utils.UIUtils;

/**
 * Created by huwang on 2017/5/17.
 * 实现加载失败，成功，空，加载中的不同显示
 * FrameLayout特性满足要求
 */

public abstract class LoadingPage extends FrameLayout {
    private static final int STATE_LOAD_NO = 1; // 未加载
    private static final int STATE_LOAD_LOADING = 2; // 正在加载
    private static final int STATE_LOAD_ERROR = 3; // 加载失败
    private static final int STATE_LOAD_EMPTY = 4; // 加载为空
    private static final int STATE_LOAD_SUCCESS = 5; // 加载成功

    private int mCurrentState = STATE_LOAD_NO;

    private View mLoadingPage;

    private View mPageError;

    private View mPageEmpty;

    private View mPageSuccess;


    public LoadingPage(@NonNull Context context) {
        this(context, null);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        showPage(); // 根据当前状态显示布局
    }

    private void showPage() {
        mLoadingPage.setVisibility(mCurrentState == STATE_LOAD_NO || mCurrentState == STATE_LOAD_LOADING ? VISIBLE : GONE);
        mPageError.setVisibility(mCurrentState == STATE_LOAD_ERROR ? VISIBLE : GONE);
        mPageEmpty.setVisibility(mCurrentState == STATE_LOAD_EMPTY ? VISIBLE : GONE);
        if (mPageSuccess == null && mCurrentState == STATE_LOAD_SUCCESS) {
            mPageSuccess = onSuccessView();
            if (mPageSuccess != null) {
                addView(mPageSuccess);
            }
        }

        if (mPageSuccess != null) {
            mPageSuccess.setVisibility(mCurrentState == STATE_LOAD_SUCCESS ? VISIBLE : GONE);
        }
    }

    private void initView() {
        // 加载中布局
        if (mLoadingPage == null) {
            mLoadingPage = UIUtils.inflate(R.layout.page_loading);
            addView(mLoadingPage);
        }

        // 加载失败
        if (mPageError == null) {
            mPageError = UIUtils.inflate(R.layout.page_error);
            // 重试事件
            Button btnRetry = (Button) mPageError.findViewById(R.id.btn_retry);
            btnRetry.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 重新加载数据
                    loadData();
                }
            });
            addView(mPageError);
        }

        // 加载为空
        if (mPageEmpty == null) {
            mPageEmpty = UIUtils.inflate(R.layout.page_empty);
            addView(mPageEmpty);
        }
    }

    // 每个模块加载成功后的布局结果不一样，所以由子类来实现
    protected abstract View onSuccessView();

    // 子类完成加载的具体实现
    protected abstract ResultState onLoadData();

    // 定义加载数据的返回状态
    public enum ResultState {
        STATE_SUCCESS(STATE_LOAD_SUCCESS),
        STATE_EMPTY(STATE_LOAD_EMPTY),
        STATE_ERROR(STATE_LOAD_ERROR);

        private int state;

        private ResultState(int state) {
            this.state = state;
        }

        public int getState() {
            return state;
        }
    }


    /**
     * 异步加载数据
     */
    public void loadData() {
        if (mCurrentState != STATE_LOAD_LOADING) {

            mCurrentState = STATE_LOAD_LOADING;

//            new Thread() {
//                @Override
//                public void run() {
//                    // 子类实现
//                    final ResultState resultState = onLoadData();
//
//                    UIUtils.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (resultState != null) {
//                                mCurrentState = resultState.getState();  // 加载结束，更新状态，刷新页面
//                                showPage();
//                            }
//                        }
//                    });
//
//                }
//            }.start();
            ThreadManager.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    final ResultState resultState = onLoadData();

                    UIUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (resultState != null) {
                                mCurrentState = resultState.getState();  // 加载结束，更新状态，刷新页面
                                showPage();
                            }
                        }
                    });
                }
            });
        }
    }
}
