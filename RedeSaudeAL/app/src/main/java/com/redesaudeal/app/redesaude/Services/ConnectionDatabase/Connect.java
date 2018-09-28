package com.redesaudeal.app.redesaude.Services.ConnectionDatabase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Connect {

    public static DatabaseReference getNodeLoggable() {
        return FirebaseDatabase.getInstance().getReference().child("Database/Loggable");
    }

    public static void logOut(FirebaseAuth auth){
        FirebaseAuth.getInstance().signOut();
    }

    public static String getCurrentUserUID(){
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public static String getCurrentUserLogin(){
        return ( FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }

    public static FirebaseAuth getAuth(){
        return FirebaseAuth.getInstance();
    }

    public static DatabaseReference getNodeCurrentLoggable(){
        return getNodeLoggable().child(Connect.getCurrentUserUID());
    }

}
