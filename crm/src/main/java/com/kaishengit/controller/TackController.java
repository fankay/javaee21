package com.kaishengit.controller;


import com.kaishengit.pojo.Task;
import com.kaishengit.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;


@Controller
@RequestMapping("/task")
public class TackController {

    @Inject
    private TaskService taskService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        List<Task> timeoutTaskList = taskService.findTimeOutTasks();
        model.addAttribute("timeoutTaskList",timeoutTaskList);
        return "task/list";
    }

    @RequestMapping(value = "/load",method = RequestMethod.GET)
    @ResponseBody
    public List<Task> load(String start,String end) {
        return taskService.findTaskByUserId(start,end);
    }

    /**
     * 新建待办任务
     * @param task
     * @return
     */
    @RequestMapping(value = "/new",method = RequestMethod.POST)
    @ResponseBody
    public String save(Task task,String hour,String min) {
        taskService.saveTask(task,hour,min);
        return "success";
    }

}
