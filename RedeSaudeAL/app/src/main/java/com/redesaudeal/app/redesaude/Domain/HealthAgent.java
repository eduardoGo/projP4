package com.redesaudeal.app.redesaude.Domain;

import java.util.ArrayList;

public class HealthAgent extends User {

    private ArrayList<Exam> exams = new ArrayList<>();

    public HealthAgent(){

        super.setType("health_agent");
    }

    public ArrayList<Exam> getExams() {
        return exams;
    }
}
