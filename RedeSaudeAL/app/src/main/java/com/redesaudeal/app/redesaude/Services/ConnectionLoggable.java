package com.redesaudeal.app.redesaude.Services;

import android.app.Activity;

import com.redesaudeal.app.redesaude.Domain.Loggable;

public interface ConnectionLoggable {

    boolean authetication(Activity activity, String login, String passwd);
    Loggable getCurrentLoggable();
    void signOut();

}
