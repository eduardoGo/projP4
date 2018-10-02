package com.itodo.app.itodo.Domain;

import java.io.Serializable;

public class Task implements Serializable {

    private String name;
    private String idProject;

    public Task(){

    }

    public Task(String name){
        this.name = name;
    }

    public Task(String idProject, String name) {
        this.name = name;
        this.idProject = idProject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdProject() {
        return idProject;
    }

    public void setIdProject(String idProject) {
        this.idProject = idProject;
    }
}
