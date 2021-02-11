package com.coldy.organizzeclone.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.coldy.organizzeclone.R;
import com.coldy.organizzeclone.config.FirebaseConfig;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GetTokenResult;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class LauncherActivity extends IntroActivity {

    private FirebaseAuth auth;

    private boolean autenticado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //verificarUsuarioLogado();

        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(new FragmentSlide.Builder()
                        .background(android.R.color.background_light)
                        .fragment(R.layout.slide_intro1)
                        .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.background_light)
                .fragment(R.layout.slide_intro2)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.background_light)
                .fragment(R.layout.slide_intro3)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.background_light)
                .fragment(R.layout.slide_intro4)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.background_light)
                .fragment(R.layout.slide_cadastro)
                .canGoForward(false)
                .build()
        );
    }

    public void onBtCadastrarClick(View view) {
        startActivity(new Intent(this, CadastroActivity.class));
    }

    public void onBtEntrarClick(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();

        verificarUsuarioLogado();
    }

    private void verificarUsuarioLogado() {
        auth = FirebaseConfig.getAuthInstance();
        if (auth.getCurrentUser() != null) {
            abrirTelaPrincipal();
        }
    }

    private void abrirTelaPrincipal() {
        Intent intent = new Intent(this, PrincipalActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}