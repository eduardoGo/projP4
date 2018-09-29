package com.itodo.app.itodo.Domain;

import java.util.ArrayList;

public class Project {
    private String id;
    private String name;
    private ArrayList<String> tasks;

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

    public void addTask(String task){
        this.tasks.add(task);
    }

    public void removeTask(int index){
        this.tasks.remove(index);
    }

    public ArrayList<String> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<String> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
