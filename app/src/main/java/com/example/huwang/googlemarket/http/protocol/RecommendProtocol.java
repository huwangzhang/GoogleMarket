package com.example.huwang.googlemarket.http.protocol;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by huwang on 2017/5/19.
 */

public class RecommendProtocol extends BaseProtocol<ArrayList<String>> {
    @Override
    public String getParams() {
        return "";
    }

    @Override
    public String getKey() {
        return "recommend";
    }

    @Override
    public ArrayList<String> parseData(String result) {
        try {
            ArrayList<String> list = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                String str = jsonArray.getString(i);
                list.add(str);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
