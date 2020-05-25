package com.it.frame.common.aspect;

import com.it.frame.common.annotation.Resubmit;
import com.it.frame.common.enums.FrameErrorStatus;
import com.it.frame.common.exception.CustomException;
import com.it.frame.common.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Arrays;
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
     * @throws CustomException CustomException
     */
    @Around("@annotation(com.it.frame.common.annotation.Resubmit)")
    public Object handleSubmit(ProceedingJoinPoint point) throws CustomException {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//        String ip = IPUtil.getIpAddr(request);
        String account = "B-28089";
        // key：IP 类名 方法名 参数
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();
        String args = Arrays.toString(point.getArgs());
        String keyCode = String.format("%s#%s#%s", className, methodName, args);
        int hashCode = Math.abs(keyCode.hashCode());
        String key = String.format("%s_%d", account, hashCode);
        // value
        String value = UUID.randomUUID().toString();
        // 获取注解信息
        Resubmit methodAnnotation = method.getAnnotation(Resubmit.class);
        long timeout = methodAnnotation.timeout() < 0 ? 1000 : methodAnnotation.timeout();
        // true：不存在  false：已存在
        boolean resubmit = redisUtil.setIfAbsent(key, value, timeout);
        if (!resubmit) {
            // 注解Resubmit到方法上需要抛出CustomException异常才可以正常返回这里到信息
            throw new CustomException(FrameErrorStatus.RESUBMIT);
        }
        // 执行目标方法
        try {
            return point.proceed();
        } catch (Throwable throwable) {
            throw new CustomException(FrameErrorStatus.RESUBMIT);
        }
    }
}

