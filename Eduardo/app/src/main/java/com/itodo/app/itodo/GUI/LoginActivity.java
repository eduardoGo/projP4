package com.itodo.app.itodo.GUI;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.itodo.app.itodo.ConnectionDatabase.Connect;
import com.itodo.app.itodo.Domain.User;
import com.itodo.app.itodo.R;

public class LoginActivity extends AppCompatActivity {

    private Button mButtonLogin;
    private Button mButtonRegister;
    private EditText mEmail;
    private EditText mPasswd;

    private String login;
    private String password;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        //Get UI elements

        defineUIElements();

        defineOnClickListeners();

    }

    protected void defineUIElements(){
        mButtonLogin = (Button) findViewById(R.id.enter);
        mButtonRegister = (Button) findViewById(R.id.register);
        mEmail = (EditText) findViewById(R.id.login);
        mPasswd = (EditText) findViewById(R.id.passwd);
    }

    protected void onStart(){
        super.onStart();

    }

    private void defineOnClickListeners(){

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = mEmail.getText().toString();
                password = mPasswd.getText().toString();
                login();
            }
        });
    }

    private void login(){
        FirebaseAuth auth = Connect.getAuth();
        auth.signInWithEmailAndPassword(login + "@gmail.com", password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            alert("Usuário logado com sucesso");
                            nextScreen();
                        }else{
                            alert("Autenticação falhou");
                        }
                    }
                });

    }

    private void nextScreen() {

        DatabaseReference node = Connect.getNodeUser().child(login);
        alert(Connect.getCurrentUserLogin());
        node.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if(user != null){
                    user.setLogin(login);

                    Intent it = new Intent(getApplicationContext(), PerfilActivity.class);
                    it.putExtra("user", user);
                    startActivity(it);

                }else{
                    alert("User is null");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                return;
            }
        });



    }

    private void alert(String string){
        Toast.makeText(this,string, Toast.LENGTH_LONG).show();
    }
}
