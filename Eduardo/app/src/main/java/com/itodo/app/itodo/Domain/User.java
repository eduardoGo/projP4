package com.itodo.app.itodo.Domain;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private String name, login, password, uid;

    private ArrayList<Project> projects;
    private ArrayList<String> idProjects;

    public User(String name, String login, String password, String uid) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.idProjects = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    public User() {
        this.idProjects = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    public void addProject(Project project){
        projects.add(project);
    }

    public void addIdProject(String idProject){
        idProjects.add(idProject);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

/*
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
*/

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public ArrayList<String> getIdProjects() {
        return idProjects;
    }

    public void setIdProjects(ArrayList<String> idProjects) {
        this.idProjects = idProjects;
    }

    public int getProjectsAmount(){
        if(this.projects == null) return 0;
        return this.projects.size();
    }

}