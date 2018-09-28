package com.redesaudeal.app.redesaude.GUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.redesaudeal.app.redesaude.Domain.Address;
import com.redesaudeal.app.redesaude.Domain.Family;
import com.redesaudeal.app.redesaude.R;
import com.redesaudeal.app.redesaude.Services.ConnectionDatabase.CreatorFirebaseLoggable;

public class RegisterFamilyScreen1 extends AppCompatActivity {

    private Button nextScreen, cancel;
    private EditText login,passwd1,passwd2,city,neighborhood,street,complement;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_family_screen1);

        instantiateScreen();

        clickEvent();


    }
    protected void instantiateScreen(){

        login = (EditText) findViewById(R.id.registerFamilyScreen1Login);
        passwd1 = (EditText) findViewById(R.id.registerFamilyScreen1Passwd1);
        passwd2 = (EditText) findViewById(R.id.registerFamilyScreen1Passwd2);
        city = (EditText) findViewById(R.id.registerFamilyScreen1City);
        neighborhood = (EditText) findViewById(R.id.registerFamilyScreen1Neighborhood);
        street = (EditText) findViewById(R.id.registerFamilyScreen1Street);
        complement = (EditText) findViewById(R.id.registerFamilyScreen1Complement);
        nextScreen = (Button) findViewById(R.id.registerFamilyScreen1ButtonNext);
        cancel = (Button) findViewById(R.id.registerFamilyScreen1ButtonExit);


    }

    protected void clickEvent() {

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        nextScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean equalsPasswd = checkPasswd(passwd1.getText().toString(),passwd1.getText().toString());

                if(equalsPasswd) {

                    Family family = createFamily();

                    FirebaseAuth authCurrent = CreatorFirebaseLoggable.createLoggable(family);

                    if(authCurrent.getCurrentUser() != null){

                        Intent i = new Intent(RegisterFamilyScreen1.this, RegisterFamilyScreen.class);

                        Bundle bundle = new Bundle();

                        bundle.putString("uid", authCurrent.getCurrentUser().getUid());

                        i.putExtras(bundle);

                        startActivity(i);

                        finish();
                    }else
                        alert("Erro, tente novamente");
                }
                alert("Senhas não conferem");



            }
        });
    }

    public void alert(String msg){
        Toast.makeText(RegisterFamilyScreen1.this, msg,Toast.LENGTH_LONG).show();
    }

    private Family createFamily(){
        Family family = new Family(login.getText().toString(),passwd1.getText().toString(),
        new Address(city.getText().toString(), neighborhood.getText().toString(),
                street.getText().toString(), complement.getText().toString()));

        return family;
    }

    private boolean checkPasswd(String passwd1, String passwd2){
        return passwd1.equals(passwd2);
    }
}
