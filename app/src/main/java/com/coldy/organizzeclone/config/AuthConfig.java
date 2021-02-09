package com.coldy.organizzeclone.config;

import com.google.firebase.auth.FirebaseAuth;

public class AuthConfig {

    private static FirebaseAuth auth;

    public static FirebaseAuth getAuthInstance() {
        if (auth == null) {
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }

}
