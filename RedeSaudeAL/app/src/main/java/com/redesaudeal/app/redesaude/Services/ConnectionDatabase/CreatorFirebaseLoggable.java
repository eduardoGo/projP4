package com.redesaudeal.app.redesaude.Services.ConnectionDatabase;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.redesaudeal.app.redesaude.Domain.Admin;
import com.redesaudeal.app.redesaude.Domain.Family;
import com.redesaudeal.app.redesaude.Domain.HealthAgent;
import com.redesaudeal.app.redesaude.Domain.Loggable;

public class CreatorFirebaseLoggable {

    private static boolean successful;


    public static FirebaseAuth createLoggable(Loggable loggable) {

        successful = false;
        FirebaseAuth authCurrent = Connect.getAuth();
        authCurrent.createUserWithEmailAndPassword(loggable.getLogin()+"@gmail.com", loggable.getPassword())
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

            loggable.setId(authCurrent.getCurrentUser().getUid());

            Connect.getUsers().child(loggable.getId()).setValue(loggable);

        }

        return authCurrent;
    }

    private static void alert(String string){

    }


}
