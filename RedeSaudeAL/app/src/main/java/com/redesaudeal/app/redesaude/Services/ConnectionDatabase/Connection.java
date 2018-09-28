package com.redesaudeal.app.redesaude.Services.ConnectionDatabase;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.redesaudeal.app.redesaude.Services.ConnectionInterface;
import com.redesaudeal.app.redesaude.Services.ConnectionLoggable;
import com.redesaudeal.app.redesaude.Services.CreatorLoggable;

public class Connection implements ConnectionInterface{

    private ConnectionFirebaseLoggable conLoggable;
    private CreatorFirebaseLoggable creatorLoggable;
    private static Connection instance;

    private Connection(){
        conLoggable = ConnectionFirebaseLoggable.getInstance();
        creatorLoggable = CreatorFirebaseLoggable.getInstance();
    }

    public static Connection getInstance(){

        if(instance == null){
            instance = new Connection();
        }

        return instance;
    }

    public ConnectionLoggable getConnectionLoggable() {
        return conLoggable;
    }

    public CreatorLoggable getCreatorLoggable() {
        return creatorLoggable;
    }
}
