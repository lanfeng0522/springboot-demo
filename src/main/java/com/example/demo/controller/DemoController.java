package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.cljj.common.bean.ResponseData;
import com.example.demo.constant.ApiInterfaceMethod;
import com.example.demo.constant.ConstantError;
import com.example.demo.service.DemoService;
import com.example.demo.service.RemoteOpenApiService;
import com.example.demo.util.LogUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"demo"})
@RestController
@RequestMapping("/demo/")
public class DemoController {

	@Autowired
	private RemoteOpenApiService remoteOpenApiService;
	@Autowired
	DemoService demoService;
	
	@Value("${toolsUrl}")
	private String toolsUrl;
	
	
	@PostMapping("targetWorkDay")
	@ApiOperation(value = "获取目标工作日",notes = "获取目标工作日")
	public ResponseData targetWorkDay(@RequestBody JSONObject json) {
		try {
			net.sf.json.JSONObject requestJson = new net.sf.json.JSONObject();
			requestJson.put("confirm_date", json.getString("nowDate"));
			requestJson.put("confirm_time",json.getString("nowTime"));
			requestJson.put("days", json.getString("days"));
			JSONObject apiJson = remoteOpenApiService.doChannelRemote(toolsUrl, ApiInterfaceMethod.CLJJ_BASE_TARGETWORKDAY, requestJson);
			LogUtils.info(this.getClass(),"：获取目标日期 ********************:"+apiJson);
			return ResponseData.success(apiJson);
		} catch (Exception e) {
			e.printStackTrace();
			LogUtils.error(this.getClass(), "：获取目标日期 * ********************: " + e);
			return ResponseData.error(ConstantError.EXCEPTION_CODE, ConstantError.EXCEPTION_MSG);
		}
	}
	
	@PostMapping("transaction")
	@ApiOperation(value = "transaction")
	public ResponseData transaction(@RequestBody JSONObject json) {
		try {
//			demoService.transaction();
			demoService.transaction2();
			return ResponseData.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseData.error(ConstantError.EXCEPTION_CODE, ConstantError.EXCEPTION_MSG);
		}
	}
}
