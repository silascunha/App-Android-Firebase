package com.coldy.organizzeclone.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.coldy.organizzeclone.R;
import com.coldy.organizzeclone.activity.util.EditTextController;
import com.coldy.organizzeclone.config.FirebaseConfig;
import com.coldy.organizzeclone.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.*;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText editEmail;
    private TextInputEditText editSenha;
    private TextInputLayout editEmailLayout;
    private TextInputLayout editSenhaLayout;
    private Button btnEntrar;
    private TextView textRedefinirSenha;
    private TextView textLoginInvalido;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseConfig.getAuthInstance();

        initializeViews();

    }

    private void initializeViews() {
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);

        editEmailLayout = findViewById(R.id.editEmailLayout);
        editSenhaLayout = findViewById(R.id.editSenhaLayout);

        btnEntrar = findViewById(R.id.btnEntrar);
        textRedefinirSenha = findViewById(R.id.textRedefinirSenha);

        textLoginInvalido = findViewById(R.id.textInvalidLogin);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isAllFieldsFilled()) {
                    textLoginInvalido.setVisibility(View.GONE);
                    String email = editEmail.getText().toString().trim();
                    String senha = editSenha.getText().toString().trim();

                    Usuario usuario = new Usuario();
                    usuario.setEmail(email);
                    usuario.setSenha(senha);

                    realizarLogin(usuario);
                }
            }
        });

        editEmail.addTextChangedListener(new EditTextController(editEmailLayout));
        editSenha.addTextChangedListener(new EditTextController(editSenhaLayout));
    }

    private boolean isAllFieldsFilled() {
        int errors = 0;

        if (editEmail.getText().toString().trim().equals("")) {
            editEmailLayout.setError("Este campo não pode ficar em branco");
            errors++;
        }
        if (editSenha.getText().toString().trim().equals("")) {
            editSenhaLayout.setError("Este campo não pode ficar em branco");
            errors++;
        }

        return errors == 0;
    }

    private void realizarLogin(Usuario usuario) {
        auth.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                        }
                        else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidUserException | FirebaseAuthInvalidCredentialsException e) {
                                textLoginInvalido.setVisibility(View.VISIBLE);
                            } catch (Exception e) {
                                Toast.makeText(LoginActivity.this,
                                        "Erro ao realizar login: " + e.getMessage(), Toast.LENGTH_SHORT)
                                        .show();
                            }
                        }
                    }
                });
    }

}