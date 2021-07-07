package com.tupolev.application.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Worker {
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private int workerId;

    private String workerName;

    public Worker(String workerName) {
        this.workerName = workerName;
    }

    public Worker() {
    }

    public int getWorker_id() {
        return  workerId;
    }

    public String getName() {
        return workerName;
    }

}
