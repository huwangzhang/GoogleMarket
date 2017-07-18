package com.example.huwang.googlemarket.http.protocol;

import com.example.huwang.googlemarket.http.HttpHelper;
import com.example.huwang.googlemarket.utils.IOUtils;
import com.example.huwang.googlemarket.utils.StringUtils;
import com.example.huwang.googlemarket.utils.UIUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by huwang on 2017/5/18.
 */

public abstract class BaseProtocol<T> {
    /**
     * @param index 分页机制
     */
    public T getData(int index) {

        String cache = getCache(index);

        if (StringUtils.isEmpty(cache)) {
            cache = getDataFromServer(index);
        }
        if (cache != null) {
            T data = parseData(cache);
            return data;
        }
        return null;
    }

    /**
     * @param index 分页
     *              获取网络数据
     */
    private String getDataFromServer(int index) {
        HttpHelper.HttpResult httpResult = HttpHelper.get(HttpHelper.URL + getKey() + "?index=" + index + getParams());

        if (httpResult != null) {
            String result = httpResult.getString();
            System.out.println("访问结果" + result);
            if (!StringUtils.isEmpty(result)) {
                setCache(index, result);
            }
            return result;
        }
        return null;
    }

    public abstract String getParams();

    public abstract String getKey();


    /**
     * @return 解析数据
     */
    public abstract T parseData(String result);

    /**
     * 写缓存
     */
    public void setCache(int index, String json) {
        File cacheDir = UIUtils.getContext().getCacheDir(); // 应用缓存文件夹
        // 缓存文件
        File cacheFile = new File(cacheDir, getKey() + "?index=" + index + getParams());
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(cacheFile);
            long expireDate = System.currentTimeMillis() + 30 * 60 * 1000;
            fileWriter.write(expireDate + "\n");   // 过期时间
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(fileWriter);
        }
    }

    /**
     * 读缓存
     */
    public String getCache(int index) {
        File cacheDir = UIUtils.getContext().getCacheDir(); // 应用缓存文件夹
        // 缓存文件
        File cacheFile = new File(cacheDir, getKey() + "?index=" + index + getParams());
        if (cacheFile.exists()) {
            BufferedReader reader = null;
            // 判断是否有效
            try {
                reader = new BufferedReader(new FileReader(cacheFile));
                String expire = reader.readLine();
                long expireDate = Long.parseLong(expire);
                if (System.currentTimeMillis() < expireDate) {
                    // 缓存有效
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    return sb.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                IOUtils.close(reader);
            }
        }
        return null;
    }
}
