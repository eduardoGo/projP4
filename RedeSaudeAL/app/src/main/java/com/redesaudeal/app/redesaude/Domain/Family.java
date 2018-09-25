package com.redesaudeal.app.redesaude.Domain;

import java.util.ArrayList;

public class Family extends Loggable {

    private ArrayList<AssistedPerson> components;

    public void setComponents(ArrayList<AssistedPerson> components) {
        this.components = components;
    }

    public ArrayList<AssistedPerson> getComponents() {

        return components;
    }

}
