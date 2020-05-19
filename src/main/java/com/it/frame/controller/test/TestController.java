package com.it.frame.controller.test;

import com.it.frame.service.test.TestService;
import com.it.frame.vo.common.ResultVO;
import com.it.frame.vo.test.TestVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "测试")
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("/hello")
    @ApiOperation("测试hello接口")
    public String helloWorld() {
        return "hello world";
    }

    @PostMapping("/user")
    @ApiOperation("测试获取用户列表")
    public ResultVO<List<TestVO>> queryTestUserList(TestVO param) {
        return new ResultVO<>(testService.queryTestUserList(param));

    }

}
