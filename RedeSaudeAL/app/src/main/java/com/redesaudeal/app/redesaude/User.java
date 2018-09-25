package com.redesaudeal.app.redesaude;

public class User {

    private String id;
    private String name;
    private int age;
    private String email;
    private String passwd;

    public User(String id, String name, int age, String email, String passwd) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.passwd = passwd;
    }

    public User() {

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String toString(){
        return getEmail()+"\n"+getName();
    }

}
