package com.example.huwang.googlemarket.http.protocol;

import com.example.huwang.googlemarket.bean.SubjectInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by huwang on 2017/5/18.
 * 专题网络请求
 */

public class SubjectProtocol extends BaseProtocol<ArrayList<SubjectInfo>> {
    @Override
    public String getParams() {
        return "";
    }

    @Override
    public String getKey() {
        return "subject";
    }

    @Override
    public ArrayList<SubjectInfo> parseData(String result) {
        try {
            JSONArray jsonArray = new JSONArray(result);
            ArrayList<SubjectInfo> list =  new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                SubjectInfo subjectInfo = new SubjectInfo();
                subjectInfo.des = object.getString("des");
                subjectInfo.url = object.getString("url");
                list.add(subjectInfo);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
}
