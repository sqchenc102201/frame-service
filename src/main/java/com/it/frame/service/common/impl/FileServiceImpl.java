package com.it.frame.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.it.frame.common.config.StaticConfig;
import com.it.frame.common.enums.FrameErrorStatus;
import com.it.frame.common.exception.CustomException;
import com.it.frame.common.util.BeanUtil;
import com.it.frame.mapper.common.FileMapper;
import com.it.frame.po.common.FilePO;
import com.it.frame.service.common.FileService;
import com.it.frame.vo.common.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;
import java.util.concurrent.Future;

/**
 * 文件操作实现类
 *
 * @author chenshaoqi
 * @since 2020/5/19
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private StaticConfig staticConfig;
    @Resource
    private FileMapper fileMapper;

    @Override
    public FileVO uploadFile(MultipartFile file) throws CustomException {
        AsynchronousFileChannel fc = null;
        try {
            String fileName = file.getOriginalFilename();
            byte[] fileBytes = file.getBytes();
            int fileSize = fileBytes.length;
            String uuid = UUID.randomUUID().toString();
            String uploadPath = this.getUploadPathAndCreate(uuid);
            // 保存上传文件信息
            FilePO filePO = new FilePO();
            filePO.setUuid(uuid);
            filePO.setFileName(fileName);
            filePO.setFileSize(fileSize);
            filePO.setFilePath(uploadPath);
            fileMapper.insert(filePO);

            // 封装返回基本信息
            FileVO fileVO = new FileVO();
            fileVO.setUuid(uuid);
            fileVO.setFileSize(fileSize);
            fileVO.setFileName(fileName);
            // 文件上传
            Path path = Paths.get(uploadPath);
            fc = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
            Future<Integer> f = fc.write(ByteBuffer.wrap(file.getBytes()), 0);
            if (!f.isDone()) {
                return fileVO;
            }
            return fileVO;
        } catch (IOException e) {
            log.error("uploadFile error:", e);
            throw new CustomException(FrameErrorStatus.FILE_IO_ERROR);
        } finally {
            if (fc != null) {
                try {
                    fc.close();
                } catch (IOException e) {
                    log.error("uploadFile fc close error:", e);
                }
            }
        }
    }

    @Override
    public FileVO getFileByUuid(String uuid) {
        LambdaQueryWrapper<FilePO> paramWrapper = new LambdaQueryWrapper<>();
        paramWrapper.eq(FilePO::getUuid, uuid);
        FilePO filePO = fileMapper.selectOne(paramWrapper);
        if (null != filePO) {
            return BeanUtil.convert(filePO, FileVO.class);
        }
        return null;
    }

    /**
     * 创建并返回上传路径
     * @param uuid UUID
     * @return  保存文件路径
     * @throws IOException 创建文件异常
     */
    private String getUploadPathAndCreate(String uuid) throws IOException {
        String uploadPath = staticConfig.getUploadPath() + uuid;
        boolean created = new File(uploadPath).createNewFile();
        if (!created) {
            uploadPath = null;
        }
        return uploadPath;
    }
}
