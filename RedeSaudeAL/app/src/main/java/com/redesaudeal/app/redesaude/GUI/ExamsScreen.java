package com.redesaudeal.app.redesaude.GUI;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.redesaudeal.app.redesaude.Domain.AssistedPerson;
import com.redesaudeal.app.redesaude.Domain.Exam;
import com.redesaudeal.app.redesaude.Domain.HealthAgent;
import com.redesaudeal.app.redesaude.Domain.Loggable;
import com.redesaudeal.app.redesaude.R;
import com.redesaudeal.app.redesaude.Services.ConnectionDatabase.Connect;

import java.util.ArrayList;

public class ExamsScreen extends AppCompatActivity {

    private Button exit;
    private ListView list;
    private HealthAgent agentCurrent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exams_screen);

        instantieButtons();

        takeAgent();

        clickActions();

        instantieList();


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

    private void instantieList() {

        ArrayAdapter<Exam> adapterCurrent = refreshAdapter();

    }

    private ArrayAdapter<Exam> refreshAdapter(){

        ArrayList<Exam> examsAgent = agentCurrent.getExams();
        return new ArrayAdapter<AssistedPerson>(ExamsScreen.this,android.R.layout.simple_dropdown_item_1line,
                examsAgent);

    }

    private void clickActions() {

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }


    private void instantieButtons() {

        exit = (Button) findViewById(R.id.ExamScreenButtonExit);
        list = (ListView) findViewById(R.id.ExamScreenList);




    }
}
