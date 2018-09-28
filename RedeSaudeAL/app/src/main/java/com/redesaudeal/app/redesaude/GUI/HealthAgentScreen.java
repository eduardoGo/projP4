package com.redesaudeal.app.redesaude.GUI;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.redesaudeal.app.redesaude.Domain.HealthAgent;
import com.redesaudeal.app.redesaude.Domain.Loggable;
import com.redesaudeal.app.redesaude.R;
import com.redesaudeal.app.redesaude.Services.ConnectionDatabase.Connect;

public class HealthAgentScreen extends AppCompatActivity {

    private Button healthAgentScreenButtonFamily;
    private Button healthAgentScreenButtonSeeExams;
    private Button healthAgentScreenButtonExit;
    private HealthAgent agentCurrent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_agent_screen);

        healthAgentScreenButtonFamily = (Button) findViewById(R.id.healthAgentScreenButtonFamily);
        healthAgentScreenButtonSeeExams =
                (Button) findViewById(R.id.healthAgentScreenButtonExams);
        healthAgentScreenButtonExit = (Button) findViewById(R.id.healthAgentScreenButtonExit);

        eventClick();

        takeAgent();



    }

    private void takeAgent() {

        FirebaseUser agent = FirebaseAuth.getInstance().getCurrentUser();
        if (agent != null){
            Connect.getUsers().child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Loggable loggable = dataSnapshot.getValue(Loggable.class);
                    if(loggable != null){
                        agentCurrent = (HealthAgent) loggable;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }

    protected void eventClick(){
        healthAgentScreenButtonFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HealthAgentScreen.this, RegisterFamilyScreen.class);
                startActivity(i);
            }
        });

        healthAgentScreenButtonSeeExams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HealthAgentScreen.this, ExamsScreen.class);
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
