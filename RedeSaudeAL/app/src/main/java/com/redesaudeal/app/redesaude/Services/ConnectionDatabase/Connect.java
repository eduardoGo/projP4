package com.redesaudeal.app.redesaude.Services.ConnectionDatabase;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Connect {

    private static FirebaseAuth.AuthStateListener authStateListener;
    private static FirebaseUser firebaseUser;
    private static DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Database");
    private static DatabaseReference users = database.child("Loggable");



    public Connect(){

    }

    public FirebaseAuth getNewAuth() {

        FirebaseAuth auth = null;
        inicializarFirebase(auth);

        return auth;
    }

    private void inicializarFirebase(FirebaseAuth auth){

        auth = FirebaseAuth.getInstance();

        final FirebaseAuth finalAuth = auth;

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = finalAuth.getCurrentUser();
                if(user != null){
                    firebaseUser = user;
                }
            }
        };

        auth.addAuthStateListener(authStateListener);

    }


    public static FirebaseUser getFirebaseUser() {

        return firebaseUser;
    }

    public static void logOut(FirebaseAuth auth){

        auth.signOut();
    }

    public static DatabaseReference getUsers() {

        return users;
    }

}
