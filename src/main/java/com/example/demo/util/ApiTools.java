package com.example.demo.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

import com.example.demo.dto.OpenApiValidateParmDTO;

import net.sf.json.JSONObject;

/**
 * 调用Api接口的工具类
 * @author 
 *
 */
public class ApiTools {
	
	/**
	 * 获取api接口post请求参数
	 * @param json
	 * @param method
	 * @throws Exception 
	 */
	public static Map<String, String> getParams(JSONObject json, String method, OpenApiValidateParmDTO openApiValidateParmDTO ) {

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //请求时间
		String timestamp = sf.format(new Date()); //"2015-07-23 16:07:00"
		String sign = ""; //签名
		String data = json.toString();
		try {
			data = URLEncoder.encode(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		StringBuffer sb = new StringBuffer();
		sb.append(openApiValidateParmDTO.getSecretKey());
		sb.append("app_key");
		sb.append(openApiValidateParmDTO.getAppKey());
		sb.append("method");
		sb.append(method);
		sb.append("timestamp");
		sb.append(timestamp);
		sb.append("v");
		sb.append(openApiValidateParmDTO.getV());

		JSONObject injson = JSONObject.fromObject(json);
		for (Iterator<String> iter = injson.keys(); iter.hasNext();) {
			String key = iter.next();
			sb.append(key);
			sb.append(json.getString(key));
		}
		sb.append(openApiValidateParmDTO.getSecretKey());
		//参数拼接
		String dSign = "";
		try {
			dSign = URLEncoder.encode(sb.toString(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		sign = DigestUtils.md5Hex(dSign).toUpperCase();

		Map<String, String> params = new HashMap<String,String>();
		params.put("timestamp", timestamp);
		params.put("app_key", openApiValidateParmDTO.getAppKey());
		params.put("method", method);
		params.put("v", openApiValidateParmDTO.getV());
		params.put("sign", sign);
		params.put("data", data);
		return params;
	}

}
