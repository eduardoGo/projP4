package com.itodo.app.itodo.GUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.itodo.app.itodo.ConnectionDatabase.Connect;
import com.itodo.app.itodo.Domain.Project;
import com.itodo.app.itodo.Domain.User;
import com.itodo.app.itodo.R;

import java.util.ArrayList;

public class PerfilActivity extends AppCompatActivity {

    private ListView listViewProj;
    private Button addProjBt;
    private User currentUser;

    //Connection con;
    //ConnectionUser conLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_perfil);

        currentUser = (User) getIntent().getSerializableExtra("user");
        ArrayList<String> idProjects = currentUser.getIdProjects();
        if(idProjects != null){
            searchProjects(idProjects);
        }

        setViews();

    }

    private void searchProjects(ArrayList<String> idProjects) {

        for(String id : idProjects) {

            DatabaseReference node = Connect.getNodeProject().child(id);
            node.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Project project = dataSnapshot.getValue(Project.class);
                    if (project != null) {
                        currentUser.addProject(project);
                    } else {
                        alert("Project not found");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    return;
                }
            });
        }


    }

    private void setViews(){
        listViewProj = (ListView) findViewById(R.id.projects);

        setListView();

        addProjBt = (Button) findViewById(R.id.add_project);

        addProjBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), AddProjectActivity.class);
                it.putExtra("user", currentUser);
                startActivity(it);

                if(currentUser.getIdProjects() != null) searchProjects( currentUser.getIdProjects() );
            }
        });
    }

    private void setListView() {

    }

    private void alert(String string){
        Toast.makeText(this,string, Toast.LENGTH_LONG).show();
    }
}
