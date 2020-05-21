package com.it.frame.common.config;

import com.it.frame.common.interceptor.LoginInterceptor;
import com.it.frame.common.interceptor.PermissionInterceptor;
import com.it.frame.service.common.PermissionService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 添加自定义拦截器，消息转换器配置
 *
 * @author chenshaoqi
 * @since 2020/5/21
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Resource
    private PermissionService permissionService;
    /**
     * 解决跨域问题
     * @param registry registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE");
    }

    /**
     * 拦截器配置
     * @param registry registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 白名单
        List<String> whiteList = new ArrayList<>();
        whiteList.add("/js/**");
        whiteList.add("/css/**");
        whiteList.add("/images/**");
        // 登陆校验拦截器
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(whiteList);
        // 权限校验拦截器
        registry.addInterceptor(new PermissionInterceptor(permissionService)).addPathPatterns("/**").excludePathPatterns(whiteList);
    }

    // 视图跳转控制器
    // void addViewControllers(ViewControllerRegistry registry);
    // 静态资源处理
    // void addResourceHandlers(ResourceHandlerRegistry registry);
    // 默认静态资源处理器
    // void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer);
    // 这里配置视图解析器
    // void configureViewResolvers(ViewResolverRegistry registry);
    // 配置内容裁决的一些选项
    // void configureContentNegotiation(ContentNegotiationConfigurer configurer);

}
