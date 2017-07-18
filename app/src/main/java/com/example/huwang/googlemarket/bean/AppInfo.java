package com.example.huwang.googlemarket.bean;

import java.util.ArrayList;

/**
 * Created by huwang on 2017/5/18.
 * 首页应用信息
 */

public class AppInfo {
    public String des;
    public String downloadUrl;
    public String iconUrl;
    public String id;
    public String name;
    public String packageName;
    public long size;
    public double stars;

    // 以下字段共应用详情页使用
    public String author;
    public String date;
    public String downloadNum;
    public String version;
    public ArrayList<SafeInfo> safe;
    public ArrayList<String> screen;

    public static class SafeInfo {
        public String safeDes;
        public int safeDesColor;
        public String safeDesUrl;
        public String safeUrl;
    }
}
