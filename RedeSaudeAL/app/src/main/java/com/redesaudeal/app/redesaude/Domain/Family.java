package com.redesaudeal.app.redesaude.Domain;

import java.util.ArrayList;

public class Family extends Loggable {

    private ArrayList<AssistedPerson> components;

    public void setComponents(ArrayList<AssistedPerson> components) {
        this.components = components;
    }

    public void addComponent(AssistedPerson assistedPerson){
        this.components.add(assistedPerson);
    }

    public ArrayList<AssistedPerson> getComponents() {

        return components;
    }

    public Family(){
        super.setType("family");
    }

}
