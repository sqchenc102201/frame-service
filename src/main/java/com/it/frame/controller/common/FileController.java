package com.it.frame.controller.common;

import com.it.frame.common.enums.FrameErrorStatus;
import com.it.frame.common.exception.CustomException;
import com.it.frame.service.common.FileService;
import com.it.frame.vo.common.FileVO;
import com.it.frame.vo.common.ResultVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

    @ApiOperation("下载模板文件")
    @GetMapping(value = "/template")
    public ResponseEntity<InputStreamResource> downloadTemple(@RequestParam String fileName) {
        try {
            String path = "templates" + File.separator + fileName;
            ClassPathResource resource = new ClassPathResource(path);
            InputStream inStream = resource.getInputStream();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Expires", "0");
            headers.add("Pragma", "no-cache");
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            String disposition = "attachment; filename=" + URLEncoder.encode(fileName, "utf-8");
            headers.add("Content-Disposition", disposition);
            return ResponseEntity.ok().headers(headers)
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new InputStreamResource(inStream));
        } catch (IOException e) {
            log.error("获取不到文件流", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
