package com.it.frame.common.aspect;

import com.it.frame.common.annotation.Resubmit;
import com.it.frame.common.enums.FrameErrorStatus;
import com.it.frame.common.exception.CustomException;
import com.it.frame.common.util.IPUtil;
import com.it.frame.common.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * 避免重复提交Aspect
 *
 * @author chenshaoqi
 * @since 2020/5/25
 */
@Slf4j
@Aspect
@Component
public class ResubmitAspect {

    @Resource
    private RedisUtil redisUtil;

    /**
     * 避免重复提交处理
     *
     * @param point point
     * @return Object
     * @throws Throwable Throwable
     */
    @Around("@annotation(com.it.frame.common.annotation.Resubmit)")
    public Object handleSubmit(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = IPUtil.getIpAddr(request);
        // key：IP 类名 方法名
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();
        String ipKey = String.format("%s#%s", className, methodName);
        int hashCode = Math.abs(ipKey.hashCode());
        String key = String.format("%s_%d", ip, hashCode);
        // value
        String value = UUID.randomUUID().toString();
        // 获取注解信息
        Resubmit methodAnnotation = method.getAnnotation(Resubmit.class);
        long timeout = methodAnnotation.timeout() < 0 ? 1000 : methodAnnotation.timeout();
        // true：不存在  false：已存在
        boolean resubmit = redisUtil.setIfAbsent(key, value, timeout);
        if (!resubmit) {
            throw new CustomException(FrameErrorStatus.RESUBMIT);
        }
        return point.proceed();
    }

}

