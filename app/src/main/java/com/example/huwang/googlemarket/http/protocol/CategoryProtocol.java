package com.example.huwang.googlemarket.http.protocol;

import com.example.huwang.googlemarket.bean.CategoryInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by huwang on 2017/5/18.
 * 专题网络请求
 */

public class CategoryProtocol extends BaseProtocol<ArrayList<CategoryInfo>> {
    @Override
    public String getParams() {
        return "";
    }

    @Override
    public String getKey() {
        return "category";
    }

    @Override
    public ArrayList<CategoryInfo> parseData(String result) {
        try {
            JSONArray jsonArray = new JSONArray(result);
            ArrayList<CategoryInfo> list = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                if (object.has("title")) {
                    CategoryInfo titleInfo = new CategoryInfo();
                    titleInfo.isTitle = true;
                    titleInfo.title = object.getString("title");
                    list.add(titleInfo);
                }
                if (object.has("infos")) {
                    JSONArray gameInfo = object.getJSONArray("infos");
                    for (int j = 0; j < gameInfo.length(); j++) {
                        JSONObject gameObject = gameInfo.getJSONObject(j);
                        CategoryInfo info = new CategoryInfo();
                        info.name1 = gameObject.getString("name1");
                        info.name2 = gameObject.getString("name2");
                        info.name3 = gameObject.getString("name3");
                        info.url1 = gameObject.getString("url1");
                        info.url2 = gameObject.getString("url2");
                        info.url3 = gameObject.getString("url3");
                        info.isTitle = false;
                        list.add(info);
                    }
                }
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
