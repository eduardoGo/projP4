package com.itodo.app.itodo.Domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Project implements Serializable{
    private String id;
    private String name;
    private ArrayList<Task> tasks;

    public Project(){
        this.tasks = new ArrayList<>();
    }

    public Project(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }

    public void removeTask(int index){
        this.tasks.remove(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
