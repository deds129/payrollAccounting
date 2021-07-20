package com.tupolev.application.controller;

import com.tupolev.application.models.Task;
import com.tupolev.application.models.User;
import com.tupolev.application.repositories.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private TaskRepo taskRepo;

    @Value("${upload.path}")
    private String uploadPath;

    //main page
    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }


    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String taskDiscription,
                      @RequestParam String taskPlace,
                      @RequestParam String taskTag,
                      Map<String, Object> model,
                      @RequestParam("file") MultipartFile file)
            throws IOException {
        Task task = new Task(taskDiscription, taskPlace, taskTag, user);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            task.setFilename(resultFilename);
        }

        taskRepo.save(task);
        Iterable<Task> tasks = taskRepo.findAll();
        model.put("tasks",tasks);
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Task> tasks = taskRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            tasks = taskRepo.findByTaskTag(filter);
        } else {
            tasks = taskRepo.findAll();
        }
        model.addAttribute("tasks", tasks);
        model.addAttribute("filter", filter);
        return "main";
    }
}
