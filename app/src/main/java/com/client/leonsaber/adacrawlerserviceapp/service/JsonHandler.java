package com.client.leonsaber.adacrawlerserviceapp.service;

import com.client.leonsaber.adacrawlerserviceapp.entity.ProductInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JsonHandler {
    public List<ProductInfo> fromJson (String jsonStr) {
        System.out.println(jsonStr);
        Gson gson = new Gson();
        Type listType = new TypeToken<List<ProductInfo>>(){}.getType();
        List<ProductInfo> productInfoList = gson.fromJson(jsonStr, listType);
        return productInfoList;
    }
}
