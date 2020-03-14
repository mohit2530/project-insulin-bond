package com.insulinbond.exampleTodo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "todo")
public class TodoModel {

    @Id // this will be unique Id in DB
    private String id;
    private String task;
    private boolean isComplete;

    public TodoModel(String task) {
        this.task = task;
        this.isComplete = false;
    }

    public TodoModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
