package com.tupolev.application;

import com.tupolev.application.domain.Worker;
import com.tupolev.application.repos.WorkerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;



@Controller
public class WebAppController {
    @Autowired
    private WorkerRepo workerRepo;

    //main page
    @GetMapping("/")
    public String greeting(Map<String, Object> model) {

        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Worker> workers = workerRepo.findAll();
        model.put("workers",workers);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String workerName, Map<String, Object> model){
        //save new worker
        Worker worker = new Worker(workerName);
        workerRepo.save(worker);

        //get from repo and put to model
        Iterable<Worker> workers = workerRepo.findAll();
        model.put("workers",workers);
        return "main";
    }

    @PostMapping("/filter")
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
