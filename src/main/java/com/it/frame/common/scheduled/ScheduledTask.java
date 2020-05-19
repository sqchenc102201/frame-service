package com.it.frame.common.scheduled;

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

    @Scheduled(cron = "0/1 * * * * ? ")
    public void scheduledTask() {
        System.out.println("ScheduledTask任务执行");
    }


//    private ExecutorService service = Executors.newFixedThreadPool(10);

//    @Scheduled(initialDelay = 1000 * 10, fixedDelay = 1000 * 5)
//    @Scheduled(cron = "5 0 0 * * ?")
//    @Scheduled(cron = "0/1 * * * * ? ")
//    public void scheduledTask1() {
//            service.execute(() ->{
//                System.out.println("任务1执行");
//            });
//    }

//    @Scheduled(cron = "5 * * * * ? ")
//    public void scheduledTask2() {
//        System.out.println("Scheduled任务2执行");
//        try {
//            Thread.sleep(1000 * 5);//改成异步执行后，就算你再耗时也不会印象到后续任务的定时调度了
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Scheduled(cron = "0/2 * * * * ? ")
//    public void scheduledTask3() {
//        service.execute(() ->{
//            System.out.println("任务3执行");
//        });
//    }


}
