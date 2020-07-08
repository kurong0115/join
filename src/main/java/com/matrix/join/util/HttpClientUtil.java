package com.matrix.join.util;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @ClassName HttpClientUtil
 * @Description TODO
 * @Author Administrator
 * @Date 2020/5/16 22:37
 * @Version 1.0
 */
public class HttpClientUtil {

    /**
     * get请求
     * @param url
     * @param params
     * @return
     */
    public static String doGet(String url, Map<String, String> params) {
        // 返回结果
        String result = "";
        // 创建HttpClient对象
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            if (null != params && !params.isEmpty()) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    uriBuilder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            URI uri = uriBuilder.build();
            // 创建get请求
            httpGet = new HttpGet(uri);
            HttpResponse response = httpClient.execute(httpGet);
            // 返回200，请求成功
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 结果返回
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放连接
            if (null != httpGet) {
                httpGet.releaseConnection();
            }
        }
        return result;
    }

    /**
     * @Title: doPost
     * @Description: post请求
     * @param url
     * @param params
     * @return
     * @author Mundo
     */
    public static String doPost(String url, Map<String, String> params) {
        String result = "";
        // 创建httpclient对象
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        try { // 参数键值对
            if (null != params && !params.isEmpty()) {
                List<NameValuePair> pairs = new ArrayList<>();
                NameValuePair pair = null;
                for (String key : params.keySet()) {
                    pair = new BasicNameValuePair(key, params.get(key));
                    pairs.add(pair);
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放连接
            httpPost.releaseConnection();
        }
        return result;
    }

    /**
     * @Title: sendJsonStr
     * @Description: post发送json字符串
     * @param url
     * @param params
     * @return 返回数据
     * @author Mundo
     */
    public static String sendJsonStr(String url, String params) {
        String result = "";

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        try {
            httpPost.addHeader("Content-type", "application/json; charset=utf-8");
            httpPost.setHeader("Accept", "application/json");
            if (params != null) {
                httpPost.setEntity(new StringEntity(params, Charset.forName("UTF-8")));
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        Map<String, String> params = new HashMap<String, String>(16);
        params.put("search_type", "video");
        params.put("highlight", "1");
        params.put("keyword", "垃圾分类");
        params.put("page", "1");
        String result = HttpClientUtil.doGet("https://api.bilibili.com/x/web-interface/search/type",params);
        System.out.println(result);
    }
}
