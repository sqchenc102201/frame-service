package com.it.frame.common.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * ScheduledTask 定义类
 * Scheduled定义方式：
 * 1、corn：表达式
 * 2、initialDelay：启动后多久开始执行，单位时毫秒、fixedRate：下次执行时间，任务开始运行的时候就计时。
 * 3、initialDelay：启动后多久开始执行，单位时毫秒、fixedDelay：下次执行时间，fixedDelay等任务进行完了才开始计时
 *
 * @author chenshaoqi
 * @since 2020/5/18
 */
@Component
public class ScheduledTask {
    @Scheduled(initialDelay = 1000 * 10, fixedDelay = 1000 * 5)
//    @Scheduled(cron = "5 0 0 * * ?")
    public void scheduledTask1() {
        System.out.println("任务1执行时间：" + System.currentTimeMillis());
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("任务1结束时间：" + System.currentTimeMillis());
    }
}
