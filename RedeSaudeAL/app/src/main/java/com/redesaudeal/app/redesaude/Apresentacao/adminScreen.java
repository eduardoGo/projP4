package com.redesaudeexample.redesaude.Apresentacao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class adminScreen extends AppCompatActivity {


    private Button adminScreenButtonPerfil;
    private Button adminScreenButtonFamily;
    private Button adminScreenButtonAgent;
    private Button adminScreenButtonAdmin;
    private Button adminScreenButtonPsf;
    private Button adminScreenButtonCampain;
    private Button adminScreenButtonExit;
    private Button adminScreenButtonAssistedPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_screen);

        adminScreenButtonPerfil = (Button) findViewById(R.id.adminScreenButtonPerfil);
        adminScreenButtonFamily = (Button) findViewById(R.id.adminScreenButtonFamily);
        adminScreenButtonAgent = (Button) findViewById(R.id.adminScreenButtonAgent);
        adminScreenButtonAdmin = (Button) findViewById(R.id.adminScreenButtonAdmin);
        adminScreenButtonPsf = (Button) findViewById(R.id.adminScreenButtonPsf);
        adminScreenButtonExit = (Button) findViewById(R.id.adminScreenButtonExit);
        adminScreenButtonCampain = (Button) findViewById(R.id.adminScreenButtonCampain);
        adminScreenButtonAssistedPerson = (Button) findViewById(R.id.adminScreenButtonAssitedPerson);

        eventClick();
    }

    protected void eventClick(){

        adminScreenButtonPerfil.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(adminScreen.this, PerfilScreen.class);
            }
        });

        adminScreenButtonFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(this, registerFamily.class);
            }
        });

        adminScreenButtonAgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(this, registerAgent.class);
            }
        });

        adminScreenButtonAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(this, registerAdmin.class);
            }
        });


        adminScreenButtonPsf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(this, registerPsf.class);
            }
        });


        adminScreenButtonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connect.getAuth().logOut();
                finish();
            }
        });


        adminScreenButtonCampain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(this, registerCampain.class);
            }
        });

        adminScreenButtonAssistedPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(this, registerAssistedPerson.class);
            }
        });


    }
}
