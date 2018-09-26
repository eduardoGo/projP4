package com.redesaudeexample.redesaude.Apresentacao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class registerAssistedPersonScreen extends AppCompatActivity {

    private EditText registerAssistedPersonScreenName;
    private EditText registerAssistedPersonScreenLogin;
    private EditText registerAssistedPersonScreenPasswd;
    private EditText registerAssistedPersonScreenCpf;
    private EditText registerAssistedPersonScreenDate;
    private Button registerAssistedPersonScreenRegister;
    private Button registerAssistedPersonScreenExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_assited_person_screen);

        registerAssistedPersonScreenExit = (Button) findViewById(R.id.registerAssistedPersonScreenExit);
        registerAssistedPersonScreenRegister = (Button) findViewById(R.id.registerAssistedPersonScreenRegister);
        registerAssistedPersonScreenDate = (EditText) findViewById(R.id.registerAssistedPersonScreenDate);
        registerAssistedPersonScreenCpf = (EditText) findViewById(R.id.registerAssistedPersonScreenCpf);
        registerAssistedPersonScreenPasswd = (EditText) findViewById(R.id.registerAssistedPersonScreenPasswd);
        registerAssistedPersonScreenLogin = (EditText) findViewById(R.id.registerAssistedPersonScreenLogin);
        registerAssistedPersonScreenName = (EditText) findViewById(R.id.registerAssistedPersonScreenName);

        clickEvent();


    }

    protected void clickEvent(){
        registerAssistedPersonScreenRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerFamily(registerAssistedPersonScreenName.getText().toString(),
                        registerAssistedPersonScreenCpf.getText().toString(),registerAssistedPersonScreenPasswd.getText(),
                        registerAssistedPersonScreenDate.getText(), registerAssistedPersonScreenLogin.getText());
                finish();
            }
        });
    }
}
