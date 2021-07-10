package com.tupolev.application.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
public class Task {
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private int taskId;

    private String taskDiscription;

    private String taskPlace;

    private String taskTag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Task(String taskDiscription, String taskPlace, String taskTag, User author) {
        this.taskDiscription = taskDiscription;
        this.taskPlace = taskPlace;
        this.taskTag = taskTag;
        this.author = author;
    }

    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
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

    public User getAutor() {
        return author;
    }

    public void setAutor(User autor) {
        this.author = autor;
    }
}
