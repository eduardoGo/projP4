package com.itodo.app.itodo.GUI;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.itodo.app.itodo.ConnectionDatabase.Connect;
import com.itodo.app.itodo.Domain.Project;
import com.itodo.app.itodo.Domain.Task;
import com.itodo.app.itodo.Domain.User;
import com.itodo.app.itodo.R;

import java.util.ArrayList;

public class AddTaskActivity extends AppCompatActivity {

    private int num_tasks;
    private String idProject;
    private EditText nameTask;
    private Button okBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        num_tasks = (int) getIntent().getSerializableExtra("num_tasks");
        idProject = (String) getIntent().getStringExtra("id_project");
        nameTask = (EditText) findViewById(R.id.name_task);
        okBt = (Button) findViewById(R.id.ok);

        setButtonListener();

    }

    private void setButtonListener() {

        okBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameTask.getText().toString();
                Connect.getNodeProject().child(idProject+"/tasks/"+num_tasks).setValue(new Task(name));
                finish();
            }
        });
    }

    private void alert(String string){
        Toast.makeText(this,string, Toast.LENGTH_LONG).show();
    }

}
