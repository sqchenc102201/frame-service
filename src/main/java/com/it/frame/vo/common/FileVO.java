package com.it.frame.vo.common;

import lombok.Data;

/**
 * TODO
 *
 * @author chenshaoqi
 * @since 2020/5/19
 */
@Data
public class FileVO {

    private Long id;

    private String uuid;

    private String fileName;

    private Integer fileSize;

    private String filePath;

}
