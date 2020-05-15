package com.oppo.grs.operation.common.util;

import com.alibaba.fastjson.JSONObject;
import com.oppo.grs.operation.common.config.HttpClientConfig;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.util.Map;
import java.util.Objects;

/**
 * http工具类
 *
 * @author W9004028 chenshaoqi
 * @since 2020/05/13
 */
@Slf4j
public class HttpUtil {

    private static final int PRINT_SIZE = 5000;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * GET请求 map格式参数
     *
     * @param url     服务地址
     * @param path    api路径
     * @param params  参数
     * @param headers 头信息
     * @return 请求返回body字符串
     */
    public static String getRequest(String url, String path, Map<String, String> params, Map<String, String> headers) {
        log.info("get: url -> {}, path -> {}, params -> {}", url, path, JSONObject.toJSON(params));
        Request.Builder reqBuild = new Request.Builder();
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url + path)).newBuilder();

        for (String key : params.keySet()) {
            urlBuilder.addQueryParameter(key, params.get(key));
        }
        if (headers != null) {
            for (Map.Entry entry : headers.entrySet()) {
                reqBuild.addHeader(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        reqBuild.url(urlBuilder.build());
        Request request = reqBuild.build();
        Call call = HttpClientConfig.client.newCall(request);
        String result;
        try (Response response = call.execute()) {
            if (response == null || !response.isSuccessful() || response.body() == null) {
                log.error("网络请求异常");
                return null;
            }
            result = response.body().string();
        } catch (Exception e) {
            log.error("get error", e);
            return null;
        }
        if (result.length() < PRINT_SIZE) {
            log.info(result);
        }
        return result;
    }

    /**
     * POST请求 json格式参数
     *
     * @param url     服务地址
     * @param path    api路径
     * @param json    json格式参数
     * @param headers 头信息
     * @return 请求返回body字符串
     */
    public static String postRequest(String url, String path, String json, Map<String, String> headers) {
        log.info("post(json): url -> {}, path -> {}, json -> {}", url, path, json);
        RequestBody requestBody = RequestBody.create(JSON, json);
        Request.Builder builder = new Request.Builder()
                .url(url + path)
                .post(requestBody);

        if (headers != null) {
            for (Map.Entry entry : headers.entrySet()) {
                builder.addHeader(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        Request request = builder.build();
        Call call = HttpClientConfig.client.newCall(request);
        String result;
        try (Response response = call.execute()) {
            if (response == null || !response.isSuccessful() || response.body() == null) {
                log.error("网络请求异常");
                return null;
            }
            result = response.body().string();
        } catch (Exception e) {
            log.error("post(json) error", e);
            return null;
        }
        log.info("result -> {}", result);
        return result;
    }

    /**
     * POST请求 map格式参数
     *
     * @param url     服务地址
     * @param path    api路径
     * @param params  map格式参数
     * @param headers 头信息
     * @return 请求返回body字符串
     */
    public static String postRequest(String url, String path, Map<String, String> params, Map<String, String> headers) {
        log.info("post(form): url -> {}, path -> {}, param -> {}", url, path, JSONObject.toJSON(params));
        FormBody.Builder builder1 = new FormBody.Builder();
        params.forEach(builder1::add);
        Request.Builder builder = new Request.Builder()
                .url(url + path)
                .post(builder1.build());

        if (headers != null) {
            for (Map.Entry entry : headers.entrySet()) {
                builder.addHeader(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        Request request = builder.build();
        Call call = HttpClientConfig.client.newCall(request);
        String result;
        try (Response response = call.execute()) {
            if (response == null || !response.isSuccessful() || response.body() == null) {
                log.error("网络请求异常");
                return null;
            }
            result = response.body().string();
        } catch (Exception e) {
            log.error("post(form) error", e);
            return null;
        }
        log.info("result -> {}", result);
        return result;
    }

}
