package com.redesaudeal.app.redesaude.GUI;

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
import com.google.firebase.auth.FirebaseAuth;
import com.redesaudeal.app.redesaude.Domain.Admin;
import com.redesaudeal.app.redesaude.Domain.Loggable;
import com.redesaudeal.app.redesaude.R;
import com.redesaudeal.app.redesaude.Services.ConnectionDatabase.Connect;
import com.redesaudeal.app.redesaude.Services.ConnectionDatabase.CreatorFirebaseLoggable;

public class RegisterScreen extends AppCompatActivity {

    private EditText nameText;
    private EditText emailText;
    private EditText passwdText;
    private EditText ageText;
    private Button register;
    private Button backLogin;
    private TextView viewText;

    private boolean successful;



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

        Loggable admin = new Admin();

        admin.setName(nameAux);
        admin.setLogin(emailAux);
        admin.setPassword(passwdAux);

        final int age = ageAux;




        /*

        Connection con = Connection.getInstance();
        CreatorLoggable creatorLog = con.getCreatorLoggable();

        boolean successful = creatorLog.createLoggable(this, admin);
        if(successful) {
            Intent i = new Intent(RegisterScreen.this, PerfilScreen.class);
            startActivity(i);
            finish();
        }
        */

        successful = false;
        FirebaseAuth authCurrent = Connect.getAuth();
        authCurrent.createUserWithEmailAndPassword(admin.getLogin()+"@gmail.com", admin.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            successful = true;
                        }else
                            successful = false;
                    }
                });


        if(successful) {

            admin.setId(authCurrent.getCurrentUser().getUid());

            Connect.getUsers().child(admin.getId()).setValue(admin);

        }





        if(authCurrent.getCurrentUser() != null){

            Intent i = new Intent(RegisterScreen.this, PerfilScreen.class);

            Bundle bundle = new Bundle();

            bundle.putString("uid", authCurrent.getCurrentUser().getUid());

            i.putExtras(bundle);

            startActivity(i);

            finish();
        }else {
            alert("Passwd" + passwdAux);
            alert("Email: " + emailAux);

        }



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
