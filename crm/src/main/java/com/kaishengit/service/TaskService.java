package com.kaishengit.service;

import com.cronutils.builder.CronBuilder;
import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import static com.cronutils.model.field.expression.FieldExpressionFactory.*;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.field.expression.On;
import com.kaishengit.job.TaskRemindJob;
import com.kaishengit.mapper.TaskMapper;
import com.kaishengit.pojo.Task;
import com.kaishengit.util.ShiroUtil;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class TaskService {

    @Inject
    private TaskMapper taskMapper;
    @Inject
    private SchedulerFactoryBean schedulerFactoryBean;

    private Logger logger = LoggerFactory.getLogger(TaskService.class);

    /**
     * 添加待办任务
     * @param task
     * @param hour
     * @param min
     */
    public void saveTask(Task task, String hour, String min) {
        if(StringUtils.isNotEmpty(hour) && StringUtils.isNotEmpty(min)) {
            String reminderTime = task.getStart() + " "+hour + ":" + min + ":00";
            logger.debug("提醒时间为{}" , reminderTime);

            task.setRemindertime(reminderTime);
            task.setUserid(ShiroUtil.getCurrentUserID());
            taskMapper.save(task);

            //TODO Quartz动态任务
            //yyyy-MM-dd HH:mm:ss -> DateTime
            DateTime dateTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(reminderTime);
            //datetime -> cron
            Cron cron = CronBuilder.cron(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ))
                    .withYear(on(dateTime.getYear()))
                    .withMonth(on(dateTime.getMonthOfYear()))
                    .withDoM(on(dateTime.getDayOfMonth()))
                    .withHour(on(dateTime.getHourOfDay()))
                    .withMinute(on(dateTime.getMinuteOfHour()))
                    .withSecond(on(0))
                    .withDoW(questionMark()).instance();

            String cronExpress = cron.asString();
            logger.debug("CRON: {}",cronExpress);

            JobDetail jobDetail = JobBuilder
                    .newJob(TaskRemindJob.class)
                    .usingJobData("context",task.getTitle())
                    .withIdentity("task:"+task.getId(),"task")
                    .build();
            ScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpress);
            Trigger trigger = TriggerBuilder.newTrigger().withSchedule(scheduleBuilder).build();

            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            try {
                scheduler.scheduleJob(jobDetail,trigger);
            } catch (SchedulerException e) {
                throw new RuntimeException(e);
            }


        } else {
            task.setUserid(ShiroUtil.getCurrentUserID());
            taskMapper.save(task);
        }
    }

    /**
     * 获取当前用户的所有任务
     * @return
     */
    public List<Task> findTaskByUserId(String start,String end) {
        return taskMapper.findByUserIdAndDateRanger(ShiroUtil.getCurrentUserID(),start,end);
    }

    /**
     * 获取当前用户已经超时的任务
     * @return
     */
    public List<Task> findTimeOutTasks() {
        String today = DateTime.now().toString("yyyy-MM-dd");
        return taskMapper.findTimeOutTask(ShiroUtil.getCurrentUserID(),today);
    }

    /**
     * 删除日程
     * @param id
     */
    public void delTask(Integer id) {
        taskMapper.del(id);
    }

    /**
     * 将日程设置为已完成
     * @param id
     */
    public Task doneTask(Integer id) {
        Task task = taskMapper.findById(id);
        task.setDone(true);
        task.setColor("#cccccc");
        taskMapper.update(task);
        return task;
    }
}
