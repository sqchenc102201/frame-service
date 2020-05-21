package com.it.frame.controller.test;

import com.alibaba.excel.EasyExcel;
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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
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

    /**
     * 数据导出（失败了会返回一个有部分数据的Excel）
     * <p>1. 创建excel对应的实体对象 参照{@link TestExcelVO}
     * <p>2. 设置返回的 参数
     * <p>3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
    @GetMapping("/export")
    @ApiOperation("直接按对象导出")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), TestExcelVO.class).sheet("模板").doWrite(data());
    }

    @ApiOperation("按模板写入")
    @GetMapping("/export/template/write")
    public void templateWrite() {
        String templateFileName = TestController.class.getResource("/").getPath() + "templates/exportTemplate.xlsx";
        String fileName = "export.xlsx";
        EasyExcel.write(fileName, TestExcelVO.class).withTemplate(templateFileName).sheet().doWrite(data());
    }

    @ApiOperation("按模板填充")
    @GetMapping("/export/template/fill")
    public void exportByTemplate(HttpServletResponse response) throws IOException {
        String fileName = "按模板填充" + System.currentTimeMillis() + ".xlsx";
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        String templateFileName = TestController.class.getResource("/").getPath() + "templates/templateFill.xlsx";
//        EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(data());
        EasyExcel.write(response.getOutputStream()).withTemplate(templateFileName).sheet().doFill(data());
        // 导出空模板
//        EasyExcel.write(response.getOutputStream()).withTemplate(templateFileName).sheet().doFill(new ArrayList<>());
    }

    private List<TestExcelVO> data() {
        List<TestExcelVO> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TestExcelVO data = new TestExcelVO();
            data.setAccount("W900400" + i);
            data.setName("Name" + i);
            data.setAge(i+10);
            data.setCreated(new Date());
            data.setScore(90d + i);
            list.add(data);
        }
        return list;
    }

}
