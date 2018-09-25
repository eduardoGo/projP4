package com.redesaudeal.app.redesaude;

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

public class MainActivity extends AppCompatActivity {

    Button mButtonLogin;
    Button mButtonRegister;
    EditText mEmail;
    EditText mPasswd;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //Get UI elements

        mButtonLogin = (Button) findViewById(R.id.login);
        mButtonRegister = (Button) findViewById(R.id.register);
        mEmail = (EditText) findViewById(R.id.email);
        mPasswd = (EditText) findViewById(R.id.passwd);

        defineOnClickListeners();

    }

    protected void onStart(){
        super.onStart();
        Connect connection = new Connect();
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

        Task signInTask = Connect.getAuth().signInWithEmailAndPassword(mEmail.getText().toString().trim() + "@gmail.com",
                mPasswd.getText().toString().trim());
        signInTask.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    alert("Usuário logado com sucesso");
                    Intent i = new Intent(getApplicationContext(), PerfilScreen.class);
                    startActivity(i);
                }else{
                    alert("Autenticação falhou");
                }
            }
        });
    }



    private void alert(String string){
        Toast.makeText(this,string, Toast.LENGTH_SHORT).show();
    }

}
