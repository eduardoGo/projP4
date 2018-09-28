package com.redesaudeal.app.redesaude.Domain;

public class PSF extends Loggable{
    private HealthAgent healthAgentsAssociated; //Vários agentes no banco de dados estarão associados a esse objeto
    private Stock stockPSF; //Banco de dados
    private Exam exam;
    private Exam examScheduled;
    private Address address;

    public PSF(){
        super.setType("psf");
    }
}
