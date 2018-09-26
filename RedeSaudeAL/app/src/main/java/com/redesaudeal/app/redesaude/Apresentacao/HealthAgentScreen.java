package com.redesaudeexample.redesaude.Apresentacao;

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
        healthAgentScreenButtonAssitedPerson = (Button) findViewById(R.id.healthAgentScreenButtonAssistedPerson);
        healthAgentScreenButtonExit = (Button) findViewById(R.id.healthAgentScreenButtonExit);

        eventClick();


    }

    protected void eventClick(){
        healthAgentScreenButtonFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(this, registerFamily.class);
            }
        });

        healthAgentScreenButtonAssitedPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(this, registerAssistedPerson.class);
            }
        });

        healthAgentScreenButtonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Connect.getAuth().logOut();
                finish();
            }
        });

    }
}
