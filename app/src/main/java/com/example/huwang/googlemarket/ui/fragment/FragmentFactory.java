package com.example.huwang.googlemarket.ui.fragment;

import java.util.HashMap;

/**
 * Created by huwang on 2017/5/17.
 * 工厂类 fragment
 */

public class FragmentFactory {
    // 存储fragment,提高性能
    private static HashMap<Integer, BaseFragment> sFragmentHashMap = new HashMap<Integer, BaseFragment>();
    public static BaseFragment createFragment(int pos) {
        BaseFragment fragment = sFragmentHashMap.get(pos);
        if (fragment == null) {
            switch (pos) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new AppFragment();
                    break;
                case 2:
                    fragment = new GameFragment();
                    break;
                case 3:
                    fragment = new SubjectFragment();
                    break;
                case 4:
                    fragment = new RecommendFragment();
                    break;
                case 5:
                    fragment = new CategoryFragment();
                    break;
                case 6:
                    fragment = new HotFragment();
                    break;
                default:
                    break;
            }
            sFragmentHashMap.put(pos, fragment);
        }
        return fragment;
    }
}
