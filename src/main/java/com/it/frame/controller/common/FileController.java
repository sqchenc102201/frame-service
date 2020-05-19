package com.it.frame.controller.common;

import com.it.frame.common.enums.FrameErrorStatus;
import com.it.frame.common.exception.CustomException;
import com.it.frame.service.common.FileService;
import com.it.frame.vo.common.FileVO;
import com.it.frame.vo.common.ResultVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * 文件操作类
 *
 * @author chenshaoqi
 * @since 2020/5/19
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService fileService;

    @PostMapping("/upload")
    @ApiOperation("上传文件")
    public ResultVO<FileVO> upload(@RequestBody MultipartFile file) throws CustomException {
        return new ResultVO<>(fileService.uploadFile(file));
    }

    @GetMapping("/download")
    @ApiOperation("下载文件")
    public ResultVO download(@RequestParam String uuid, HttpServletResponse httpServletResponse) throws CustomException {
        FileVO fileVO = fileService.getFileByUuid(uuid);
        File file = new File(fileVO.getFilePath());
        try {
            httpServletResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode(fileVO.getFileName(), "utf-8"));
            OutputStream outputStream = httpServletResponse.getOutputStream();
            outputStream.write(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            log.error("download file error:", e);
            throw new CustomException(FrameErrorStatus.FILE_IO_ERROR);
        }
        return new ResultVO();
    }


}
