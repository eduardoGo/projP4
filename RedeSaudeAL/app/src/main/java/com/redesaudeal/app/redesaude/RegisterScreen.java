package com.redesaudeal.app.redesaude;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class RegisterScreen extends AppCompatActivity {

    private EditText nameText;
    private EditText emailText;
    private EditText passwdText;
    private EditText ageText;
    private Button register;
    private Button backLogin;
    private TextView viewText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        nameText = (EditText) findViewById(R.id.nameText);
        emailText = (EditText) findViewById(R.id.emailText);
        passwdText = (EditText) findViewById(R.id.passwdText);
        ageText = (EditText) findViewById(R.id.ageText);
        register = (Button) findViewById(R.id.register);
        viewText = (TextView) findViewById(R.id.textView1);
        backLogin = (Button) findViewById(R.id.backLogin);

        eventoClick();
    }

    protected void eventoClick(){

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
                //finish();
            }
        });
        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }



    protected void registerUser(){

        String nameAux = nameText.getText().toString().trim();
        String emailAux = emailText.getText().toString().trim();
        String passwdAux = passwdText.getText().toString().trim();
        int ageAux = Integer.valueOf(ageText.getText().toString());

        final String name = nameAux;

        final String email = emailAux;

        final String passwd = passwdAux;

        final int age = ageAux;
        //alert("Iniciar o mAuth");

        Connect.getAuth().createUserWithEmailAndPassword(email + "@gmail.com", passwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {



                        if(task.isSuccessful()){

                            alert("Usuário criado com sucesso");
                            teste(name,email,passwd,age);

                        }
                        else{
                            // If sign in fails, display a message to the user.
                            Log.w("oi", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterScreen.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //alert("Erro, tente novamente ");
                        }

                    }
                });

        //alert("...");

        return;


    }

    private void teste(String name, String email, String passwd, int age){

        FirebaseUser user = Connect.getAuth().getCurrentUser();
        User newUser = new User(user.getUid(),name,age,email,passwd);
        Connect.getUsers().child(user.getUid()).setValue(newUser);

        Intent i = new Intent(RegisterScreen.this, PerfilScreen.class);
        startActivity(i);
        finish();

    }



    @Override
    protected void onStart() {
        super.onStart();
        //Toast.makeText(RegisterScreen.this,"Chegou",Toast.LENGTH_SHORT).show();
    }

    private void alert(String msg){
        Toast.makeText(RegisterScreen.this,msg,Toast.LENGTH_SHORT).show();
    }
}
