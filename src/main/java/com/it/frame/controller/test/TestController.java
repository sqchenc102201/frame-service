package com.it.frame.controller.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "测试")
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    @ApiOperation("测试hello接口")
    public String helloWorld() {
        return "hello world";
    }

}
