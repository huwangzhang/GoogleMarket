package com.example.huwang.googlemarket.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.example.huwang.googlemarket.R;
import com.example.huwang.googlemarket.ui.fragment.BaseFragment;
import com.example.huwang.googlemarket.ui.fragment.FragmentFactory;
import com.example.huwang.googlemarket.ui.view.PagerTab;
import com.example.huwang.googlemarket.utils.LogUtils;
import com.example.huwang.googlemarket.utils.UIUtils;

public class MainActivity extends BaseActivity {
    private   ActionBarDrawerToggle toggle;;
    private DrawerLayout drawerLayout;;
    private PagerTab mPagerTab;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initActionBar();
        initDatas();
    }

    private void initDatas() {
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mPagerTab.setViewPager(mViewPager);
        mPagerTab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                BaseFragment fragment = FragmentFactory.createFragment(position);
                // 开始加载数据
                fragment.loadData();
                LogUtils.i("selected        " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initViews() {
        mPagerTab = (PagerTab) findViewById(R.id.page_tab);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.ic_launcher);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
//        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer_am, R.string.drawer_open, R.string.drawer_close);
        toggle.syncState();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                toggle.onOptionsItemSelected(item);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    class MyAdapter extends FragmentPagerAdapter {
        private String[] mTabTitles;
        public MyAdapter(FragmentManager fm) {
            super(fm);
            mTabTitles = UIUtils.getStringArray(R.array.tab_names);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.createFragment(position);
        }

        @Override
        public int getCount() {
            return mTabTitles.length;
        }

        /**
         * @param position
         * @return 返回页签标题
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position];
        }
    }
}
