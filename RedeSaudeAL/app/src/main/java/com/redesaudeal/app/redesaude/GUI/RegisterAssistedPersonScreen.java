package com.redesaudeal.app.redesaude.GUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.redesaudeal.app.redesaude.Domain.AssistedPerson;
import com.redesaudeal.app.redesaude.Domain.Family;
import com.redesaudeal.app.redesaude.Domain.User;
import com.redesaudeal.app.redesaude.R;

public class RegisterAssistedPersonScreen extends AppCompatActivity {

    private EditText registerAssistedPersonScreenName;
    private EditText registerAssistedPersonScreenLogin;
    private EditText registerAssistedPersonScreenPasswd;
    private EditText registerAssistedPersonScreenCpf;
    private EditText registerAssistedPersonScreenDate;
    private Button registerAssistedPersonScreenRegister;
    private Button registerAssistedPersonScreenExit;
    private String uidFamily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register_assited_person_screen);

        registerAssistedPersonScreenExit = (Button) findViewById(R.id.registerAssistedPersonScreenButtonBack);
        registerAssistedPersonScreenRegister = (Button) findViewById(R.id.registerAssistedPersonScreenButtonRegister);
        registerAssistedPersonScreenDate = (EditText) findViewById(R.id.registerAssistedPersonScreenDate);
        registerAssistedPersonScreenCpf = (EditText) findViewById(R.id.registerAssistedPersonScreenCpf);
        registerAssistedPersonScreenPasswd = (EditText) findViewById(R.id.registerAssistedPersonScreenPasswd);
        registerAssistedPersonScreenLogin = (EditText) findViewById(R.id.registerAssistedPersonScreenLogin);
        registerAssistedPersonScreenName = (EditText) findViewById(R.id.registerAssistedPersonScreenName);


        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        uidFamily = bundle.getString("uid");


        clickEvent();


    }

    protected void clickEvent() {
        registerAssistedPersonScreenRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AssistedPerson user = registerUser(registerAssistedPersonScreenName.getText().toString(),
                        registerAssistedPersonScreenCpf.getText().toString(), registerAssistedPersonScreenPasswd.getText(),
                        registerAssistedPersonScreenDate.getText(), registerAssistedPersonScreenLogin.getText());

                addUserInFamily(user);


                finish();
            }
        });


    }

    private AssistedPerson registerUser(String s, String s1, Editable text, Editable text1, Editable text2) {
        return null;
    }

    protected void addUserInFamily(AssistedPerson user) {

        Family newFamily = takeFamily(uidFamily); //Deve pegar o objeto familia do banco de dados que tem esse uid
        newFamily.addComponent(user);
        refreshFamily(newFamily, uidFamily); //Atualiza o objeto familia nesse uid

        return;
    }

    private Family takeFamily(String uid){
        return null;
    }

    private void refreshFamily(Family family, String uid){

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
