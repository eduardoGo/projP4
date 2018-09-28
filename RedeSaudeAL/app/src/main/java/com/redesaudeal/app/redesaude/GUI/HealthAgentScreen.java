package com.redesaudeal.app.redesaude.GUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HealthAgentScreen extends AppCompatActivity {

    private Button healthAgentScreenButtonFamily;
    private Button healthAgentScreenButtonAssitedPerson;
    private Button healthAgentScreenButtonExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_agent_screen);

        healthAgentScreenButtonFamily = (Button) findViewById(R.id.healthAgentScreenButtonFamily);
        healthAgentScreenButtonAssitedPerson =
                (Button) findViewById(R.id.healthAgentScreenButtonAssitedPerson);
        healthAgentScreenButtonExit = (Button) findViewById(R.id.healthAgentScreenButtonExit);

        eventClick();


    }

    protected void eventClick(){
        healthAgentScreenButtonFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HealthAgentScreen.this, RegisterFamilyScreen.class);
                startActivity(i);
            }
        });

        healthAgentScreenButtonAssitedPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HealthAgentScreen.this, RegisterAssistedPersonScreen.class);
                startActivity(i);
            }
        });

        healthAgentScreenButtonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Connect.getAuth().logOut();
                finish();
            }
        });

    }
}
