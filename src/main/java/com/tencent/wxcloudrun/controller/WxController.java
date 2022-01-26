package com.tencent.wxcloudrun.controller;

import cn.hutool.http.HttpUtil;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.service.CounterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 龚紫晗
 */
@RestController
@RequestMapping("/wx")
public class WxController {

    final Logger logger;

    public WxController(@Autowired CounterService counterService) {
        this.logger = LoggerFactory.getLogger(WxController.class);
    }


    @GetMapping(value = "/sendMsg")
    ApiResponse sendMsg() {
        return ApiResponse.ok();
    }

    @GetMapping(value = "/testGetUrl")
    ApiResponse testGetUrl(@RequestParam("url") String url) {
        return ApiResponse.ok(HttpUtil.get(url));
    }

}
