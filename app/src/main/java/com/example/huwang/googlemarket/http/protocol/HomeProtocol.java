package com.example.huwang.googlemarket.http.protocol;

import com.example.huwang.googlemarket.bean.AppInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by huwang on 2017/5/18.
 */

public class HomeProtocol extends BaseProtocol<ArrayList<AppInfo>> {
    private ArrayList<String> pictures;
    @Override
    public String getParams() {
        return "";
    }

    @Override
    public String getKey() {
        return "home";
    }

    @Override
    public ArrayList<AppInfo> parseData(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("list");

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
            // 轮播图片

            pictures = new ArrayList<>();
            JSONArray jsonArray1 = jsonObject.getJSONArray("picture");
            for (int i = 0; i < jsonArray1.length(); i++) {
                pictures.add(jsonArray1.getString(i));
            }
            return appInfos;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getPictureList() {
        return pictures;
    }
}
