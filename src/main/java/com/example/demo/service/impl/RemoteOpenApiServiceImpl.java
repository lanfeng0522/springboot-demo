package com.example.demo.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.cljj.wallgold.auth.util.HttpClientUtil;
import com.example.demo.dto.OpenApiValidateParmDTO;
import com.example.demo.service.RemoteOpenApiService;
import com.example.demo.util.ApiTools;
import com.example.demo.util.LogUtils;
import com.example.demo.util.StringUtil;

@Component
public class RemoteOpenApiServiceImpl implements RemoteOpenApiService {

    @Value("${apiHost}")
    private  String apiHost;

    @Value("${appKey}")
    private  String appKey;

    @Value("${channelId}")
    private  String channelId;

    @Value("${v}")
    private  String v;

    @Value("${secretKey}")
    private String secretKey;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public JSONObject doChannelRemote(String url, String methodname ,  net.sf.json.JSONObject reqJson) throws Exception{
        LogUtils.info(RemoteOpenApiServiceImpl.class, url + methodname + reqJson.toString() );
        OpenApiValidateParmDTO  openApiValidateParmDTO=new OpenApiValidateParmDTO();
        openApiValidateParmDTO.setAppKey(appKey);
        openApiValidateParmDTO.setChannelId(channelId);
        openApiValidateParmDTO.setSecretKey(secretKey);
        openApiValidateParmDTO.setV(v);
        Map<String, String> paraMap = ApiTools.getParams(reqJson, methodname,openApiValidateParmDTO);
        LogUtils.info(this.getClass(),"request param====="+apiHost + url+methodname);
        String result = HttpClientUtil.doPost(apiHost + url, paraMap, "UTF-8", true);
        LogUtils.info(this.getClass(),apiHost + url+methodname+"repsonse text====="+result);
        if(StringUtil.isEmpty(result)){
            throw new Exception("渠道接口返回数据为空");
        }
        JSONObject reustJsonObjec=JSONObject.parseObject(result);
        return reustJsonObjec;

    }
}
