package com.oppo.grs.operation.common.config;

import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * HttpClientConfig 配置
 * @author W9004028 chenshaoqi
 * @since 2020/05/13
 */
@Slf4j
@Configuration
public class HttpClientConfig {
    public static OkHttpClient client;

    @PostConstruct
    public void init() {
        if (Objects.isNull(client)) {
            client = new OkHttpClient.Builder()
                    .connectionPool(new ConnectionPool(100, 5, TimeUnit.MINUTES))
                    .readTimeout(10000, TimeUnit.MILLISECONDS)
                    .writeTimeout(10000, TimeUnit.MILLISECONDS)
                    .connectTimeout(10000, TimeUnit.MILLISECONDS)
                    .build();
        }
    }

}
