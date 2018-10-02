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
import com.itodo.app.itodo.Domain.User;
import com.itodo.app.itodo.R;

import java.util.ArrayList;

public class AddProjectActivity extends AppCompatActivity {

    private EditText editName;
    private Button okBt;
    private Button addCoworkersBt;
    private LinearLayout loginCowkLinear;
    private ArrayList<EditText> listEditCoWk;
    private User currentUser;

    private  User user;

    private String loginCW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        currentUser = (User) getIntent().getSerializableExtra("user");
        listEditCoWk = new ArrayList<>();

        editName = (EditText) findViewById(R.id.name_project);
        okBt = (Button) findViewById(R.id.ok);
        addCoworkersBt = (Button) findViewById(R.id.add_coworker);
        loginCowkLinear = (LinearLayout) findViewById(R.id.login_coworkers);

        setButtonListeners();
    }

    private void setButtonListeners() {
        addCoworkersBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newLoginCowk = new EditText(getApplicationContext());
                loginCowkLinear.addView(newLoginCowk);
                listEditCoWk.add(newLoginCowk);
            }
        });

        okBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Project newProject = new Project(currentUser.getLogin()+currentUser.countIdProjects());
                newProject.setName(editName.getText().toString());

                Connect.getNodeProject().child(newProject.getId()).setValue(newProject);

                for(EditText editLogin : listEditCoWk){

                    loginCW = editLogin.getText().toString();

                    if(loginCW.equals("ed1")) {
                        DatabaseReference node = Connect.getNodeUser().child(loginCW);
                        node.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                User user = dataSnapshot.getValue(User.class);
                                if (user != null) {
                                    user.addIdProject(newProject.getId());
                                    alert(""+user.getIdProjects().size());
                                    Connect.getNodeUser().child(user.getLogin()).child("idProjects").setValue(user.getIdProjects());//O problema pode ser adicionar no nó idProjects
                                } else {
                                    alert(loginCW + " inexistente");
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                return;
                            }
                        });
                    }

                }

                currentUser.addIdProject(newProject.getId());
                Connect.getNodeUser().child(currentUser.getLogin()).child("idProjects").setValue(currentUser.getIdProjects());
                finish();
            }
        });
    }

    private void alert(String string){
        Toast.makeText(this,string, Toast.LENGTH_LONG).show();
    }
}
