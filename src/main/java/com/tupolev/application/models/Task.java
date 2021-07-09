package com.tupolev.application.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Task {
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private int taskId;

    private String taskDiscription;

    private String taskPlace;

    private String taskTag;

    public Task(String taskDiscription, String taskPlace, String taskTag) {
        this.taskDiscription = taskDiscription;
        this.taskPlace = taskPlace;
        this.taskTag = taskTag;
    }


    public Task() {
    }

    public String getTaskDiscription() {
        return taskDiscription;
    }

    public String getTaskPlace() {
        return taskPlace;
    }

    public String getTaskTag() {
        return taskTag;
    }
}
