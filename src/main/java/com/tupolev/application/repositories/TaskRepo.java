package com.tupolev.application.repositories;

import com.tupolev.application.models.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepo extends CrudRepository<Task, Integer> {
    List<Task> findByTaskDiscription(String discription);
    List<Task> findByTaskPlace(String place);
    List<Task> findByTaskTag(String tag);

}
