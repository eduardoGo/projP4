package com.redesaudeal.app.redesaude.Services.ConnectionDatabase;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.redesaudeal.app.redesaude.Domain.Admin;
import com.redesaudeal.app.redesaude.Domain.Family;
import com.redesaudeal.app.redesaude.Domain.HealthAgent;
import com.redesaudeal.app.redesaude.Domain.Loggable;
import com.redesaudeal.app.redesaude.Domain.PSF;

import com.redesaudeal.app.redesaude.Services.ConnectionLoggable;


public class ConnectionFirebaseLoggable implements ConnectionLoggable {

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser firebaseUser;
    private DatabaseReference database;
    private DatabaseReference loggable;
/*
    private DatabaseReference family;
    private DatabaseReference admin;
    private DatabaseReference psf;
    private DatabaseReference healthAgent;
*/
    private boolean was_auth;
    private Loggable currentLoggable;

    private static ConnectionFirebaseLoggable instance;

    private ConnectionFirebaseLoggable(){

        database = FirebaseDatabase.getInstance().getReference().child("Database");
        loggable = database.child("Loggable");
        /*
        family = loggable.child("Family");
        admin = loggable.child("Admin");
        psf = loggable.child("PSF");
        healthAgent = loggable.child("HealthAgent");
        */
        was_auth = false;
        auth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = auth.getCurrentUser();
                if(user != null){
                    firebaseUser = user;
                }
            }
        };
        auth.addAuthStateListener(authStateListener);

    }

    protected static ConnectionFirebaseLoggable getInstance(){
        if(instance == null){
            instance = new ConnectionFirebaseLoggable();
        }
        return instance;
    }

    @Override
    public boolean authetication(Activity activity, String login, String passwd){

        Task signInTask = auth.signInWithEmailAndPassword(login, passwd);

        signInTask.addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    updateCurrentLoggable();
                    was_auth = true;
                } else {
                    was_auth = false;
                }
            }
        });

        return was_auth;

    }

    private void updateCurrentLoggable(){

        DatabaseReference node = loggable.child(firebaseUser.getUid());
        node.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentLoggable = dataSnapshot.getValue(Loggable.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                return;
            }
        });
    }

    /*private void updateCurrentLoggable(){

        DatabaseReference node;
        if( ( node = family.child(firebaseUser.getUid()) ) != null ) {
            subclassLoggable = Family.class;
        }
        else if( ( node = psf.child(firebaseUser.getUid()) ) != null ) {
            subclassLoggable = PSF.class;
        }
        else if( ( node = healthAgent.child(firebaseUser.getUid()) ) != null ) {
            subclassLoggable = HealthAgent.class;
        }
        else if( ( node = admin.child(firebaseUser.getUid()) ) != null ) {
            subclassLoggable = Admin.class;
        }

        if(node != null){
            getDataLogged(node);
        }

    }

    private void getDataLogged(DatabaseReference node){

        node.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentLoggable = dataSnapshot.getValue(subclassLoggable);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                return;
            }
        });
    }*/

    @Override
    public Loggable getCurrentLoggable() {
        return currentLoggable;
    }

    @Override
    public void signOut(){
        currentLoggable = null;
        was_auth = false;
        auth.signOut();
    }

    protected FirebaseAuth getAuth(){
        return auth;
    }

    protected FirebaseUser getFirebaseUser(){
        return auth.getCurrentUser();
    }
}
