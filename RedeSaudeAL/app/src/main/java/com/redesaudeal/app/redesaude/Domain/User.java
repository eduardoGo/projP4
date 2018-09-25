package com.redesaudeal.app.redesaude.Domain;

public class User extends Loggable{

    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}