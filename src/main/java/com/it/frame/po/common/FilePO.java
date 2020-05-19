package com.it.frame.po.common;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * TODO
 *
 * @author chenshaoqi
 * @since 2020/5/19
 */
@Data
@TableName("com_file")
public class FilePO {
    private Long id;

    private String uuid;

    private String fileName;

    private Integer fileSize;

    private String filePath;
}
