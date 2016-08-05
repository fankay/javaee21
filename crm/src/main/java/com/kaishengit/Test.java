package com.kaishengit;

import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.Timer;

public class Test {

    public static void main(String[] args) throws SchedulerException {

        /*Timer timer = new Timer();
        timer.schedule(new MyTimerTask(),0,5000);*/


        //任务
        JobDetail jobDetail = JobBuilder.newJob(MyQuartzJob.class).build();

        //创建触发器
        /*SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
        scheduleBuilder.withIntervalInSeconds(5);
        scheduleBuilder.repeatForever();*/
        ScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("1 35 14 5 8 ? 2016-2016");
        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(scheduleBuilder).build();

        //创建调度器
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();




    }
}
