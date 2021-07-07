package com.tupolev.application.repos;

import com.tupolev.application.domain.Worker;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkerRepo extends CrudRepository<Worker, Integer> {
    List<Worker> findByWorkerName(String name);
}
