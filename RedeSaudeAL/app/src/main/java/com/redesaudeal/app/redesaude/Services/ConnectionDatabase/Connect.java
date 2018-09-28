package com.redesaudeal.app.redesaude.Services.ConnectionDatabase;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Connect {

    private static FirebaseAuth auth = FirebaseAuth.getInstance();
    private static FirebaseAuth.AuthStateListener authStateListener;
    private static FirebaseUser firebaseUser;
    private static DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Database");
    private static DatabaseReference users = database.child("Loggable");



    public Connect(){

    }

    public static FirebaseAuth getAuth() {
        if(auth == null){
            inicializarFirebase();

        }
        return auth;
    }

    private static void inicializarFirebase(){
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
