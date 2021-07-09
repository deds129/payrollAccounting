package com.tupolev.application.controller;

import com.tupolev.application.models.Task;
import com.tupolev.application.repositories.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private TaskRepo taskRepo;

    //main page
    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Task> tasks = taskRepo.findAll();
        model.put("tasks",tasks);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String taskDiscription,
                      String taskPlace,
                      String taskTag,
                      Map<String, Object> model){

        Task task = new Task(taskDiscription, taskPlace, taskTag);
        taskRepo.save(task);
        Iterable<Task> tasks = taskRepo.findAll();
        model.put("tasks",tasks);
        return "main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        Iterable<Task> tasks;
        if (filter != null && !filter.isEmpty())
             tasks = taskRepo.findByTaskTag(filter);
        else{
            tasks = taskRepo.findAll();
        }
        model.put("tasks",tasks);
        return "main";
    }
}
