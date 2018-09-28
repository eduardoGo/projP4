package com.redesaudeal.app.redesaude.Services.ConnectionDatabase;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.redesaudeal.app.redesaude.Domain.Loggable;
import com.redesaudeal.app.redesaude.Services.CreatorLoggable;

public class CreatorFirebaseLoggable implements CreatorLoggable {

    private static CreatorFirebaseLoggable instance;

    private DatabaseReference nodeDatabase;
    private DatabaseReference nodeLoggable;
    private ConnectionFirebaseLoggable conLoggable;
    private boolean successful;

    private CreatorFirebaseLoggable(){
        conLoggable  = ConnectionFirebaseLoggable.getInstance();
        nodeDatabase = FirebaseDatabase.getInstance().getReference().child("Database");
        nodeLoggable = nodeDatabase.child("Loggable");
    }

    protected static CreatorFirebaseLoggable getInstance(){
        if(instance == null){
            instance = new CreatorFirebaseLoggable();
        }
        return instance;
    }

    @Override
    public boolean createLoggable(Activity activity, Loggable loggable) {

        successful = true;
        conLoggable.getAuth().createUserWithEmailAndPassword(loggable.getLogin(), loggable.getPassword())
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            successful = true;
                        }
                        else{
                            successful = false;
                        }

                    }
                });

        if(successful) {
            loggable.setId(conLoggable.getFirebaseUser().getUid());
            nodeLoggable.child(loggable.getId()).setValue(loggable);
        }
        return successful;
    }
}
