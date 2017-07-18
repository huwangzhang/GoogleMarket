package com.example.huwang.googlemarket.http.protocol;

import com.example.huwang.googlemarket.bean.AppInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by huwang on 2017/5/18.
 * 应用网络请求
 */

public class AppProtocol extends BaseProtocol<ArrayList<AppInfo>> {
    @Override
    public String getParams() {
        return "";
    }

    @Override
    public String getKey() {
        return "app";
    }

    @Override
    public ArrayList<AppInfo> parseData(String result) {
        try {
            JSONArray jsonArray = new JSONArray(result);
            ArrayList<AppInfo> appInfos = new ArrayList<>();
            AppInfo appInfo = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                appInfo = new AppInfo();
                appInfo.des = object.getString("des");
                appInfo.downloadUrl = object.getString("downloadUrl");
                appInfo.iconUrl = object.getString("iconUrl");
                appInfo.id = object.getString("id");
                appInfo.name = object.getString("name");
                appInfo.packageName = object.getString("packageName");
                appInfo.size = object.getLong("size");
                appInfo.stars = object.getDouble("stars");
                appInfos.add(appInfo);
            }
            return appInfos;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
