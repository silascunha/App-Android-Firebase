package com.coldy.organizzeclone.config;

import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseConfig {

    private static FirebaseAuth auth;
    private static DatabaseReference database;

    public static FirebaseAuth getAuthInstance() {
        if (auth == null) {
            auth = FirebaseAuth.getInstance();
            auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    if (firebaseAuth.getCurrentUser() != null) {

                        firebaseAuth.getCurrentUser().getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                            @Override
                            public void onComplete(@NonNull Task<GetTokenResult> task) {
                                if (!task.isSuccessful()) {
                                    try {
                                        throw task.getException();
                                    } catch (FirebaseAuthInvalidUserException e) {
                                        Log.i("FIREBASEAUTH", "Erro ao autenticar usuário logado: " + e.getMessage());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Log.i("FIREBASEAUTH", "Erro de autenticação com o usuário: " + e.getMessage());
                                    }
                                }
                            }
                        });

                    }
                }
            });
        }
        return auth;
    }

    public static DatabaseReference getDatabase() {
        if (database == null) {
            database = FirebaseDatabase.getInstance().getReference();
        }
        return database;
    }

}
