package com.tupolev.application;

import com.tupolev.application.domain.Worker;
import com.tupolev.application.repos.WorkerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;



@Controller
public class GreetingController {
    @Autowired
    private WorkerRepo workerRepo;

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<Worker> workers = workerRepo.findAll();
        model.put("workers",workers);
        return "main";
    }

    @PostMapping
    public String add(@RequestParam String workerName, Map<String, Object> model){
        //save new worker
        Worker worker = new Worker(workerName);
        workerRepo.save(worker);

        //get from repo and put to model
        Iterable<Worker> workers = workerRepo.findAll();
        model.put("workers",workers);
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        Iterable<Worker> workers;
        if (filter != null && !filter.isEmpty())
             workers = workerRepo.findByWorkerName(filter);
        else{
            workers = workerRepo.findAll();
        }
        model.put("workers", workers);
        return "main";
    }
}
