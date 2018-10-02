package com.itodo.app.itodo.GUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.itodo.app.itodo.ConnectionDatabase.Connect;
import com.itodo.app.itodo.Domain.Project;
import com.itodo.app.itodo.Domain.User;
import com.itodo.app.itodo.GUI.Adapter.ProjectAdapter;
import com.itodo.app.itodo.R;

import java.util.ArrayList;

public class PerfilActivity extends AppCompatActivity {

    private ListView listViewProj;
    private Button addProjBt;
    private User currentUser;
    private int numProjectsOnListining;
    private TextView welcomeText;
    ProjectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_perfil);

        numProjectsOnListining = 0;
        currentUser = (User) getIntent().getSerializableExtra("user");
        ArrayList<String> idProjects = currentUser.getIdProjects();

        addListenerOnUser();
        setViews();

    }

    private void addListenerOnUser() {

        DatabaseReference node = Connect.getNodeUser().child(currentUser.getLogin());
        node.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    currentUser.getIdProjects().clear();
                    currentUser.getIdProjects().addAll(user.getIdProjects());
                    addListenerOnProjects();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                return;
            }
        });

    }

    private void addListenerOnProjects() {

        for(int i = numProjectsOnListining; i < currentUser.countIdProjects(); i++){

            String id = currentUser.getIdProjects().get(i);
            DatabaseReference node = Connect.getNodeProject().child(id);
            node.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Project project = dataSnapshot.getValue(Project.class);
                    if (project != null) {
                        currentUser.addProject(project);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    return;
                }
            });

        }
        numProjectsOnListining = currentUser.countIdProjects();

    }

/*    private void searchProjects() {

        for(String id : currentUser.getIdProjects()) {

            DatabaseReference node = Connect.getNodeProject().child(id);
            node.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Project project = dataSnapshot.getValue(Project.class);
                    if (project != null) {
                        currentUser.addProject(project);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    return;
                }
            });
        }

    }
*/
    private void setViews(){

        welcomeText = (TextView) findViewById(R.id.welcome);
        welcomeText.setText("Vamos trabalhar, "+currentUser.getName()+"?!");
        listViewProj = (ListView) findViewById(R.id.projects);

        setListView();

        addProjBt = (Button) findViewById(R.id.add_project);

        addProjBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(getApplicationContext(), AddProjectActivity.class);
                it.putExtra("user", currentUser);
                startActivity(it);
            }
        });
    }

    private void setListView() {

        adapter = new ProjectAdapter(this, currentUser.getProjects());
        listViewProj.setAdapter(adapter);

        listViewProj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                Project selectedProj = (Project) parent.getItemAtPosition(position);
                Intent it = new Intent(getApplicationContext(), TasksActivity.class);
                it.putExtra("project", selectedProj);
                startActivity(it);

            }
        });

    }

    private void alert(String string){
        Toast.makeText(this,string, Toast.LENGTH_LONG).show();
    }
}
