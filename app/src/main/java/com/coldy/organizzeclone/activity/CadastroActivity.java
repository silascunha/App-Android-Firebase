package com.coldy.organizzeclone.activity;

import android.graphics.*;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.coldy.organizzeclone.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CadastroActivity extends AppCompatActivity {

    private TextInputEditText editNome;
    private TextInputEditText editEmail;
    private TextInputEditText editSenha;
    private TextInputLayout editNomeLayout;
    private TextInputLayout editEmailLayout;
    private TextInputLayout editSenhaLayout;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        initializeViews();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editEmailLayout.setError(null);
                editNomeLayout.setError(null);
                editSenhaLayout.setError(null);

                if (isAllFieldsFilled()) {
                    //TODO ação de cadastro
                }
            }
        });
    }

    private void initializeViews() {
        editEmail = findViewById(R.id.editEmail);
        editNome = findViewById(R.id.editNome);
        editSenha = findViewById(R.id.editSenha);

        btnCadastrar = findViewById(R.id.btnCadastrar);

        editEmailLayout = findViewById(R.id.editEmailLayout);
        editNomeLayout = findViewById(R.id.editNomeLayout);
        editSenhaLayout = findViewById(R.id.editSenhaLayout);
    }

    private boolean isAllFieldsFilled() {
        int errors = 0;

        if (editNome.getText().toString().trim().equals("")) {
            editNomeLayout.setError("Preencha com um nome válido");
            errors++;
        }
        if (editEmail.getText().toString().trim().equals("")) {
            editEmailLayout.setError("Preencha com um email válido");
            errors++;
        }
        if (editSenha.getText().toString().trim().equals("")) {
            editSenhaLayout.setError("Preencha com uma senha válida");
            errors++;
        }

        return errors == 0;
    }

}