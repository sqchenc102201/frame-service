package com.it.frame.service.common;

import com.it.frame.common.exception.CustomException;
import com.it.frame.vo.common.FileVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件操作类
 *
 * @author chenshaoqi
 * @since 2020/5/19
 */
public interface FileService {

    /**
     * 上传文件
     * @param file 文件
     * @return 文件基本信息
     */
    FileVO uploadFile(MultipartFile file) throws CustomException;

    /**
     * 根据UUID获取文件信息
     * @param uuid uuid
     * @return 文件信息
     */
    FileVO getFileByUuid(String uuid);
}
