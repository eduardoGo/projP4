package com.redesaudeal.app.redesaude.GUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.redesaudeal.app.redesaude.Domain.AssistedPerson;
import com.redesaudeal.app.redesaude.Domain.Family;
import com.redesaudeal.app.redesaude.R;

import java.util.ArrayList;

public class RegisterFamilyScreen extends AppCompatActivity {

    private Button registerAssistedPerson;
    private Button finishRegister;
    private Button cancelRegister;
    private ListView usersCurrentRegistered;
    private ArrayList<AssistedPerson> usersInFamily = new ArrayList<AssistedPerson>();
    //private ArrayAdapter<String> adapter = refreshAdapter();
    Family newFamily;
    private String uidFamily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register_family);

        registerAssistedPerson = (Button) findViewById(R.id.registerFamilyScreenRegisterMember);
        finishRegister = (Button) findViewById(R.id.registerFamilyScreenFinish);
        cancelRegister = (Button) findViewById(R.id.registerFamilyScreenExit);
        usersCurrentRegistered = (ListView) findViewById(R.id.registerFamilyScreenList);

        newFamily = new Family();

         //registra newFamily no realtime e pega o UID


        eventClick();
    }

    private void eventClick(){
        registerAssistedPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(RegisterFamilyScreen.this,
                        RegisterAssistedPersonScreen.class);


                Bundle bundle = new Bundle();

                bundle.putString("uid", uidFamily);

                i.putExtras(bundle);

                startActivity(i);

                ArrayAdapter<AssistedPerson> adapterCurrent = refreshAdapter();

                usersCurrentRegistered.setAdapter(adapterCurrent);



            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    private ArrayAdapter<AssistedPerson> refreshAdapter(){

        newFamily = takeFamily(uidFamily); //Essa função deve pegar o objeto familia do banco de dados
        usersInFamily = newFamily.getComponents();

        return new ArrayAdapter<AssistedPerson>(this,android.R.layout.simple_dropdown_item_1line,
                usersInFamily);

    }

    private Family takeFamily(String uidFamily) {
        return null;
    }
}
