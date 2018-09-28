package com.redesaudeal.app.redesaude.GUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.redesaudeal.app.redesaude.Domain.Family;
import com.redesaudeal.app.redesaude.Domain.HealthAgent;
import com.redesaudeal.app.redesaude.Domain.Loggable;
import com.redesaudeal.app.redesaude.R;
import com.redesaudeal.app.redesaude.Services.ConnectionDatabase.Connect;

public class MainActivity extends AppCompatActivity {

    private Button mButtonLogin;
    private Button mButtonRegister;
    private EditText mEmail;
    private EditText mPasswd;
    private Connect connection;
    private Loggable loggable;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //Get UI elements

        getUIElemts();

        defineOnClickListeners();

        connection = new Connect();

        loggable = null;


    }

    protected void getUIElemts(){
        mButtonLogin = (Button) findViewById(R.id.login);
        mButtonRegister = (Button) findViewById(R.id.register);
        mEmail = (EditText) findViewById(R.id.email);
        mPasswd = (EditText) findViewById(R.id.passwd);
    }

    protected void onStart(){
        super.onStart();

    }

    private void defineOnClickListeners(){

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterScreen.class);
                startActivity(i);
            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login(){

        final FirebaseAuth auth = connection.getNewAuth();

        auth.signInWithEmailAndPassword(mEmail.getText().toString() + "gmail.com", mPasswd.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            alert("Usuário logado com sucesso");

                            nextScreen(auth);

                        }else{
                            alert("Autenticação falhou");
                        }
                    }
                });

    }

    private void nextScreen(FirebaseAuth auth) {

        takeLoggable(auth);

        Intent i = null;

        if(loggable.getType().equals("health_agent"))
            i = new Intent(getApplicationContext(), HealthAgentScreen.class);
        //else if(loggable.getType().equals("family"))
            //   i = new Intent(getApplicationContext(), FamilyScreen.class);
        else if(loggable.getType().equals("admin"))
            i = new Intent(getApplicationContext(), AdminScreen.class);


        Bundle bundle = new Bundle();

        bundle.putString("uid", loggable.getId());

        i.putExtras(bundle);

        startActivity(i);


    }

    private void takeLoggable(FirebaseAuth auth) {

        Connect.getUsers().child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                loggable = dataSnapshot.getValue(Loggable.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                alert("Não foi possível acessar o banco de dados");
            }
        });

    }


    private void alert(String string){
        Toast.makeText(this,string, Toast.LENGTH_SHORT).show();
    }

}
