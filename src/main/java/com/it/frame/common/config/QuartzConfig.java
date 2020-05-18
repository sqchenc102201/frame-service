package com.it.frame.common.config;

import com.it.frame.common.quartz.SampleJob;
import com.it.frame.common.quartz.SecondJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail SampleQuartzDetail() {
        return JobBuilder.newJob(SampleJob.class).withIdentity("sampleJob").storeDurably().build();
    }
    @Bean
    public Trigger SampleQuartzTrigger() {
        // 设置时间周期单位秒 每隔两秒实行一次
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever();
        return TriggerBuilder.newTrigger().forJob(SampleQuartzDetail()).withIdentity("sampleTrigger").withSchedule(scheduleBuilder).build();
    }

    @Bean
    public JobDetail SecondQuartzDetail() {
        return JobBuilder.newJob(SecondJob.class).withIdentity("SecondJob").storeDurably().build();
    }
    @Bean
    public Trigger SecondQuartzTrigger() {
        // 设置时间周期单位秒 每隔两秒实行一次
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/6 * * * * ?");
        return TriggerBuilder.newTrigger().forJob(SecondQuartzDetail()).withIdentity("secondTrigger").withSchedule(scheduleBuilder).build();
    }
}
