package com.coldy.organizzeclone.activity;

import android.content.Intent;
import android.os.Bundle;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.coldy.organizzeclone.R;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void adicionarReceita(View view) {
        startActivity(new Intent(this, ReceitaActivity.class));
    }

    public void adicionarDespesa(View view) {
        startActivity(new Intent(this, DespesaActivity.class));
    }
}