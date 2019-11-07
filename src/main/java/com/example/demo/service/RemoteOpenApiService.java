package com.example.demo.service;


import com.alibaba.fastjson.JSONObject;

public interface RemoteOpenApiService {
    JSONObject doChannelRemote(String url, String methodname, net.sf.json.JSONObject reqJson) throws Exception;
}