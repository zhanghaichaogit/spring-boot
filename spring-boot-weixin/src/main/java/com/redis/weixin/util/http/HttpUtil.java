package com.redis.weixin.util.http;

import okhttp3.*;

import java.io.IOException;

/**
 * Created by zhanghaichao on 2017/7/17.
 */
public class HttpUtil {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    static final OkHttpClient client = new OkHttpClient();

    public static String getHttp(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    public static String postHttp(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }
}
