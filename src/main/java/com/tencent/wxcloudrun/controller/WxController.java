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

        Map<String, Object> map = new HashMap<>();

        JSONObject dataJson = new JSONObject();
        JSONObject thing1DataJson = new JSONObject();
        JSONObject thing3DataJson = new JSONObject();

        thing1DataJson.put("value", "购票服务");
        thing3DataJson.put("value", "购票成功");

        dataJson.put("thing1", thing1DataJson);
        dataJson.put("thing3", thing3DataJson);
//        map.put("cloudbase_access_token", cloudBaseAccessToken);
        map.put("touser", xWxOpenid);
        map.put("template_id", "osQSdCeHkRLm4KoShzn4SaGjsA41F8lvG2zMHN01X4c");
        map.put("data", dataJson.toJSONString());

        map.put("data.thing1.value", "购票服务");
        map.put("data.thing3.value", "购票成功");

        final String dataJsonStr = JSONObject.toJSONString(map);

        logger.info("requestData:" + dataJsonStr);

//        服务名称
//        {{thing1.DATA}}
//        物品名称
//        {{thing3.DATA}}

//        final String gettemplate = HttpUtil.get("https://api.weixin.qq.com/wxaapi/newtmpl/gettemplate?cloudbase_access_token=" + cloudBaseAccessToken);
//        final JSONObject gettemplateJson = JSONObject.parseObject(gettemplate);
//        final String data = gettemplateJson.getJSONArray("data").get(0).toString()


//        String wxUrl = "https:api.weixin.qq.com/cgi-bin/message/device/subscribe/send";
        String wxUrl = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?cloudbase_access_token=" + cloudBaseAccessToken;
        final String result = HttpUtil.post(wxUrl, dataJsonStr);

        logger.info("result:" + result);

        return ApiResponse.ok(result);
    }

    @GetMapping(value = "/testGetUrl")
    ApiResponse testGetUrl(@RequestParam("url") String url) {
        return ApiResponse.ok(HttpUtil.get(url));
    }

}
