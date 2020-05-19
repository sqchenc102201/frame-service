package com.it.frame.po.common;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 文件PO
 *
 * @author chenshaoqi
 * @since 2020/5/19
 */
@Data
@TableName("com_file")
public class FilePO {
    private Long id;

    @TableField("c_uuid")
    private String uuid;

    @TableField("c_file_name")
    private String fileName;

    @TableField("c_file_size")
    private Integer fileSize;

    @TableField("c_file_path")
    private String filePath;
}
