package com.it.frame.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 静态配置文件
 *
 * @author chenshaoqi
 * @since 2020/5/19
 */
@Data
@Configuration
public class StaticConfig {

    @Value("file.upload-path")
    private String uploadPath;

}
