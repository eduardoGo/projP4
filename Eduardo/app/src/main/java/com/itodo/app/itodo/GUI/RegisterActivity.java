package com.itodo.app.itodo.GUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.itodo.app.itodo.ConnectionDatabase.Connect;
import com.itodo.app.itodo.Domain.User;
import com.itodo.app.itodo.R;


public class RegisterActivity extends AppCompatActivity {

    private EditText nameText;
    private EditText emailText;
    private EditText passwdText;
    private Button register;

    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        nameText = (EditText) findViewById(R.id.nameText);
        emailText = (EditText) findViewById(R.id.loginText);
        passwdText = (EditText) findViewById(R.id.passwdText);
        register = (Button) findViewById(R.id.register);

        eventoClick();
    }

    protected void eventoClick(){

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    protected void registerUser(){

        user = new User();
        user.setName(nameText.getText().toString());
        user.setLogin(emailText.getText().toString());
        user.setPassword(passwdText.getText().toString());

        Connect.getAuth().createUserWithEmailAndPassword(user.getLogin() + "@gmail.com",
                user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Connect.getNodeUser().child(user.getLogin()).setValue(user);

                            Intent i = new Intent(getApplicationContext(), PerfilActivity.class);
                            i.putExtra("user", user);
                            startActivity(i);
                            finish();

                        }else{
                            alert("Fail");
                        }

                    }
                });

        /*

        Connection con = Connection.getInstance();
        CreatorUser creatorLog = con.getCreatorUser();

        boolean successful = creatorLog.createUser(this, admin);
        if(successful) {
            Intent i = new Intent(RegisterActivity.this, PerfilActivity.class);
            startActivity(i);
            finish();
        }
        */

    }

    @Override
    protected void onStart() {
        super.onStart();
        //Toast.makeText(RegisterActivity.this,"Chegou",Toast.LENGTH_SHORT).show();
    }

    private void alert(String msg){
        Toast.makeText(RegisterActivity.this,msg,Toast.LENGTH_SHORT).show();
    }
}
