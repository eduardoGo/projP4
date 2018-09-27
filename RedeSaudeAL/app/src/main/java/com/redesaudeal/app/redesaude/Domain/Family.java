package com.redesaudeal.app.redesaude.Domain;

import java.util.ArrayList;

public class Family extends Loggable {

    private ArrayList<AssistedPerson> components =  new ArrayList<>();
    private Address address;


    public void addComponent(AssistedPerson assistedPerson){
        this.components.add(assistedPerson);
    }

    public ArrayList<AssistedPerson> getComponents() {

        return components;
    }

    public Family(String login, String password, Address address){
        super.setType("family");
        super.setLogin(login);
        super.setPassword(password);
        this.address = address;
    }

}
