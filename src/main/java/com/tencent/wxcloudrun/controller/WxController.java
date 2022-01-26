package com.tencent.wxcloudrun.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.config.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 龚紫晗
 */
@RestController
@RequestMapping("/wx")
public class WxController {

    final Logger logger;

    public WxController() {
        this.logger = LoggerFactory.getLogger(WxController.class);
    }


    @GetMapping(value = "/sendMsg")
    ApiResponse sendMsg(HttpServletRequest request) {

        final String cloudBaseAccessToken = request.getHeader("X-WX-CLOUDBASE-ACCESS-TOKEN");
        final String xWxOpenid = request.getHeader("X-WX-OPENID");

//        final String uuId = UUID.randomUUID().toString();

        JSONObject dataJson = new JSONObject();
        JSONObject thing1DataJson = new JSONObject();
        JSONObject thing3DataJson = new JSONObject();

        thing1DataJson.put("value","购票服务");
        thing3DataJson.put("value","购票成功");

        dataJson.put("thing1",thing1DataJson);
        dataJson.put("thing3",thing3DataJson);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cloudbase_access_token", cloudBaseAccessToken);
        jsonObject.put("touser", xWxOpenid);
        jsonObject.put("template_id", "osQSdCeHkRLm4KoShzn4SaGjsA41F8lvG2zMHN01X4c");
        jsonObject.put("data", dataJson.toJSONString());



//        服务名称
//        {{thing1.DATA}}
//        物品名称
//        {{thing3.DATA}}

//        final String gettemplate = HttpUtil.get("https://api.weixin.qq.com/wxaapi/newtmpl/gettemplate?cloudbase_access_token=" + cloudBaseAccessToken);
//        final JSONObject gettemplateJson = JSONObject.parseObject(gettemplate);
//        final String data = gettemplateJson.getJSONArray("data").get(0).toString()



//        String wxUrl = "https:api.weixin.qq.com/cgi-bin/message/device/subscribe/send";
        String wxUrl = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send";
        final String result = HttpUtil.post(wxUrl, jsonObject.toJSONString());

        logger.info("result:" + result);

        return ApiResponse.ok(result);
    }

    @GetMapping(value = "/testGetUrl")
    ApiResponse testGetUrl(@RequestParam("url") String url) {
        return ApiResponse.ok(HttpUtil.get(url));
    }

}
