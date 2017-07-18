package com.example.huwang.googlemarket.http.protocol;

import com.example.huwang.googlemarket.bean.AppInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by huwang on 2017/5/20.
 */

public class HomeDetailProtocol extends BaseProtocol<AppInfo> {
    public String mPackageName;

    public HomeDetailProtocol(String packageName) {
        mPackageName = packageName;
    }

    @Override
    public String getParams() {
        return "&packageName=" + mPackageName;
    }

    @Override
    public String getKey() {
        return "detail";
    }

    @Override
    public AppInfo parseData(String result) {
        try {
            AppInfo appInfo = new AppInfo();
            JSONObject object = new JSONObject(result);
            appInfo = new AppInfo();
            appInfo.des = object.getString("des");
            appInfo.downloadUrl = object.getString("downloadUrl");
            appInfo.iconUrl = object.getString("iconUrl");
            appInfo.id = object.getString("id");
            appInfo.name = object.getString("name");
            appInfo.packageName = object.getString("packageName");
            appInfo.size = object.getLong("size");
            appInfo.stars = object.getDouble("stars");

            appInfo.author = object.getString("author");
            appInfo.date = object.getString("date");
            appInfo.downloadNum = object.getString("downloadNum");
            appInfo.version = object.getString("version");


            JSONArray safe = object.getJSONArray("safe");
            ArrayList<AppInfo.SafeInfo> safeInfos = new ArrayList<>();
            for (int i = 0; i < safe.length(); i++) {
                JSONObject safeJSONObject = safe.getJSONObject(i);
                AppInfo.SafeInfo safeInfo = new AppInfo.SafeInfo();
                safeInfo.safeDes = safeJSONObject.getString("safeDes");
                safeInfo.safeDesColor = safeJSONObject.getInt("safeDesColor");
                safeInfo.safeDesUrl = safeJSONObject.getString("safeDesUrl");
                safeInfo.safeUrl = safeJSONObject.getString("safeUrl");
                safeInfos.add(safeInfo);
            }
            appInfo.safe = safeInfos;

            ArrayList<String> screens = new ArrayList<>();
            JSONArray screenArray = object.getJSONArray("screen");
            for (int i = 0; i < screenArray.length(); i++) {
                String pic = screenArray.getString(i);
                screens.add(pic);
            }
            appInfo.screen = screens;
            return appInfo;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
