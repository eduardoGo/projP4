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
    public void removeIdProject(String id){
        idProjects.remove(id);
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

    public int countProjects(){
        if(this.projects == null) return 0;
        return this.projects.size();
    }

    public int countIdProjects(){
        if(this.idProjects == null) return 0;
        return this.idProjects.size();
    }

    public boolean checkConsistenceIdProjects() {

        if(countProjects() == countIdProjects()) return true;

        String idProject;
        int index = 0;
        while(countProjects() < countIdProjects()){
            idProject = idProjects.get(index);

            boolean exist = false;
            for(int i = 0; i < countProjects(); i++){
                if(projects.get(i).getId().equals(idProject)){
                    exist = true;
                    break;
                }
            }

            if(!exist) idProjects.remove(index);

            else index++;
        }

        return false;

    }

}