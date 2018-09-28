package com.redesaudeal.app.redesaude.GUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.redesaudeal.app.redesaude.Domain.Loggable;
import com.redesaudeal.app.redesaude.R;
import com.redesaudeal.app.redesaude.Services.ConnectionDatabase.Connect;

public class PerfilScreen extends AppCompatActivity {

    private TextView nameText;
    private TextView emailText;
    private TextView ageText;
    private Button exit;
    private FirebaseUser userFirebase;
    private String uidLoggable;


    //Connection con;
    //ConnectionLoggable conLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_perfil);

        nameText = (TextView) findViewById(R.id.nameExb);
        emailText = (TextView) findViewById(R.id.emailExb);
        ageText = (TextView) findViewById(R.id.ageExib);
        exit = (Button) findViewById(R.id.signOut);
        //eventoClick();
        //userFirebase = Connect.getAuth().getCurrentUser();

        //con = Connection.getInstance();
        //conLog = con.getConnectionLoggable();

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        uidLoggable = bundle.getString("uid");


        setLoggableFields();


    }

    private void setLoggableFields() {


        FirebaseAuth auth = Connect.getAuth();

        Connect.getUsers().child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Loggable loggable = dataSnapshot.getValue(Loggable.class);

                if(loggable!=null) {
                    emailText.setText(loggable.getLogin());

                    nameText.setText(loggable.getName());
                }else
                    alert("Loggable is NULL");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void alert(String string){
        Toast.makeText(this,string, Toast.LENGTH_LONG).show();
    }



/*    @Override
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
    }*/
}
