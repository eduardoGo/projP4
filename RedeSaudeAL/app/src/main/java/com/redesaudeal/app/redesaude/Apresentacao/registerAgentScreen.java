package com.redesaudeexample.redesaude.Apresentacao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class registerAgentScreen extends AppCompatActivity {

    private EditText registerAgentScreenName;
    private EditText registerAgentScreenLogin;
    private EditText registerAgentScreenPasswd;
    private EditText registerAgentScreenCpf;
    private EditText registerAgentScreenDate;
    private Button registerAgentScreenRegister;
    private Button registerAgentScreenExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_agent_screen);

        registerAgentScreenExit = (Button) findViewById(R.id.registerAgentScreenExit);
        registerAgentScreenRegister = (Button) findViewById(R.id.registerAgentScreenRegister);
        registerAgentScreenDate = (EditText) findViewById(R.id.registerAgentScreenDate);
        registerAgentScreenCpf = (EditText) findViewById(R.id.registerAgentScreenCpf);
        registerAgentScreenPasswd = (EditText) findViewById(R.id.registerAgentScreenPasswd);
        registerAgentScreenLogin = (EditText) findViewById(R.id.registerAgentScreenLogin);
        registerAgentScreenName = (EditText) findViewById(R.id.registerAgentScreenName);

        clickEvent();

    }

    protected void clickEvent(){
        registerAgentScreenRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerAgent(registerAgentScreenName.getText().toString(),
                        registerAgentScreenCpf.getText().toString(),registerAgentScreenPasswd.getText(),
                        registerAgentScreenDate.getText(), registerAgentScreenLogin.getText());
                finish();
            }
        });
    }
}
