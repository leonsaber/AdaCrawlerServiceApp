package com.client.leonsaber.adacrawlerserviceapp.controller;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpRequestController {

    public String connect(String url) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(10L, TimeUnit.SECONDS)
                .build();


        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
