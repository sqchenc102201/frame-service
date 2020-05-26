package com.it.frame.common.util;

import com.alibaba.fastjson.JSONObject;
import com.it.frame.common.config.HttpClientConfig;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * http工具类
 *
 * @author chenshaoqi
 * @since 2020/05/13
 */
@Slf4j
public class HttpUtil {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType MULTIPART = MediaType.parse("multipart/form-data");


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
        Request.Builder builder = new Request.Builder();
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url + path)).newBuilder();

        for (String key : params.keySet()) {
            urlBuilder.addQueryParameter(key, params.get(key));
        }
        builder.url(urlBuilder.build());
        Call call = getCall(headers, builder);
        return callExecute(call);
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
        Call call = getCall(headers, builder);
        return callExecute(call);
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
        Call call = getCall(headers, builder);
        return callExecute(call);
    }

    /**
     * 上传文件
     *
     * @param url      上传目标地址
     * @param filePath 上传文件原路径
     * @param fileName 文件名
     * @return 请求信息
     */
    public static String uploadFile(String url, String filePath, String fileName) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", fileName, RequestBody.create(MULTIPART, new File(filePath)))
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + UUID.randomUUID())
                .url(url)
                .post(requestBody)
                .build();

        Call call = HttpClientConfig.client.newCall(request);
        return callExecute(call);
    }

    /**
     * 上传文件
     *
     * @param url      上传目标地址
     * @param filePath 上传文件原路径
     * @param fileName 文件名
     * @param params   params
     * @param headers  headers
     * @return 请求信息
     */
    public static String uploadFile(String url, String filePath, String fileName,
                                    Map<String, String> params, Map<String, String> headers) {
        MultipartBody.Builder mulBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", fileName, RequestBody.create(MULTIPART, new File(filePath)));

        if (null != params) {
            for (String key : params.keySet()) {
                mulBuilder.addFormDataPart(key, params.get(key));
            }
        }
        RequestBody requestBody = mulBuilder.build();

        Request.Builder builder = new Request.Builder()
                .url(url)
                .post(requestBody);

        if (headers != null) {
            for (Map.Entry entry : headers.entrySet()) {
                builder.addHeader(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        Request request = builder.build();
        Call call = HttpClientConfig.client.newCall(request);
        return callExecute(call);
    }

    private static String callExecute(Call call) {
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

    private static Call getCall(Map<String, String> headers, Request.Builder builder) {
        if (headers != null) {
            for (Map.Entry entry : headers.entrySet()) {
                builder.addHeader(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        Request request = builder.build();
        return HttpClientConfig.client.newCall(request);
    }

}
