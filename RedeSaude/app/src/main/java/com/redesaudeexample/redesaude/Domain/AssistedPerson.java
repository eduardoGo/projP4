package com.redesaudeexample.redesaude.Domain;

import java.util.Date;

public class AssistedPerson {

    private String cpf;
    private Date birthday;
    private String name;

    public String getCpf() {
        return cpf;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getName() {
        return name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setName(String name) {
        this.name = name;
    }
}
