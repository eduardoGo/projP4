package com.redesaudeal.app.redesaude;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class PerfilScreen extends AppCompatActivity {

    private TextView nameText;
    private TextView emailText;
    private TextView ageText;
    private Button exit;
    private FirebaseUser userFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_perfil);

        nameText = (TextView) findViewById(R.id.nameExb);
        emailText = (TextView) findViewById(R.id.emailExb);
        ageText = (TextView) findViewById(R.id.ageExib);
        exit = (Button) findViewById(R.id.signOut);
        userFirebase = Connect.getAuth().getCurrentUser();
        eventoClick();
    }


    protected void eventoClick(){
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connect.getAuth().signOut();
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(userFirebase != null){

            getUserRealTime(userFirebase);

        }
        else{
            Toast.makeText(PerfilScreen.this,"Nenhum usuario logado",Toast.LENGTH_SHORT).show();
            //finish();
        }
    }

    private void getUserRealTime(FirebaseUser user){


        Connect.getUsers().child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                emailText.setText(user.getEmail());
                nameText.setText(user.getName());
                ageText.setText(Integer.toString(user.getAge()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                emailText.setText("The read failed: " + databaseError.getCode());
            }
        });
    }
}
