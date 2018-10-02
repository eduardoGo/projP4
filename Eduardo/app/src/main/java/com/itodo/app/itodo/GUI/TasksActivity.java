package com.itodo.app.itodo.GUI;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.itodo.app.itodo.ConnectionDatabase.Connect;
import com.itodo.app.itodo.Domain.Project;
import com.itodo.app.itodo.Domain.Task;
import com.itodo.app.itodo.GUI.Adapter.TaskAdapter;
import com.itodo.app.itodo.R;

import java.util.ArrayList;

public class TasksActivity extends AppCompatActivity {

    private ListView listViewTask;
    private Button addTaskBt;
    private ArrayList<Task> tasks;
    private Project project;
    private int index;
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tasks);

        project = (Project) getIntent().getSerializableExtra("project");

        setViews();

    }

    private void updateTasks(){
        DatabaseReference node = Connect.getNodeProject().child(project.getId());
        node.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Project new_project = dataSnapshot.getValue(Project.class);
                project.getTasks().clear();
                project.getTasks().addAll(new_project.getTasks());
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                return;
            }
        });
    }


    private void setViews(){
        listViewTask = (ListView) findViewById(R.id.tasks);

        setListView();

        addTaskBt = (Button) findViewById(R.id.add_task);

        addTaskBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), AddTaskActivity.class);
                it.putExtra("num_tasks", project.getTasks().size());
                it.putExtra("id_project", project.getId());
                startActivity(it);
                updateTasks();
            }
        });
    }

    private void setListView() {

        adapter = new TaskAdapter(this, project.getTasks(), project.getId());
        listViewTask.setAdapter(adapter);

    }

    private void alert(String string){
        Toast.makeText(this,string, Toast.LENGTH_LONG).show();
    }
}


