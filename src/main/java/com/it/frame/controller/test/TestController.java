package com.it.frame.controller.test;

import com.it.frame.common.util.ExcelUtil;
import com.it.frame.service.test.TestService;
import com.it.frame.vo.common.ResultVO;
import com.it.frame.vo.test.TestExcelVO;
import com.it.frame.vo.test.TestVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
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

    /**
     * 导入Excel
     * <p>1. 创建excel对应的实体对象
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器
     * <p>3. 直接读即可
     */
    @ResponseBody
    @PostMapping("/import")
    @ApiOperation("导入Excel")
    public String upload(MultipartFile file) throws IOException {
        // 同步读取
        List<?> list = ExcelUtil.doReadSync(file.getInputStream(), TestExcelVO.class);
        if (null == list) {
            return "";
        }
        list.forEach(x -> {
            TestExcelVO tt = (TestExcelVO) x;
            System.out.println(tt.getAccount());
            System.out.println(tt.getName());
            System.out.println(tt.getAge());
            System.out.println(tt.getScore());
            System.out.println(tt.getCreated());
        });
        return "success";
    }

}
