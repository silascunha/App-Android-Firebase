package com.coldy.organizzeclone.activity;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.coldy.organizzeclone.R;
import com.coldy.organizzeclone.activity.util.EditTextController;
import com.coldy.organizzeclone.config.AuthConfig;
import com.coldy.organizzeclone.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.*;

public class CadastroActivity extends AppCompatActivity {

    private TextInputEditText editNome;
    private TextInputEditText editEmail;
    private TextInputEditText editSenha;
    private TextInputLayout editNomeLayout;
    private TextInputLayout editEmailLayout;
    private TextInputLayout editSenhaLayout;
    private Button btnCadastrar;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        auth = AuthConfig.getAuthInstance();

        initializeViews();


    }

    private void initializeViews() {
        editEmail = findViewById(R.id.editEmail);
        editNome = findViewById(R.id.editNome);
        editSenha = findViewById(R.id.editSenha);

        btnCadastrar = findViewById(R.id.btnCadastrar);

        editEmailLayout = findViewById(R.id.editEmailLayout);
        editNomeLayout = findViewById(R.id.editNomeLayout);
        editSenhaLayout = findViewById(R.id.editSenhaLayout);

        editNome.addTextChangedListener(new EditTextController(editNomeLayout));
        editEmail.addTextChangedListener(new EditTextController(editEmailLayout));
        editSenha.addTextChangedListener(new EditTextController(editSenhaLayout));

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isAllFieldsFilled()) {
                    String nome = editNome.getText().toString().trim();
                    String email = editEmail.getText().toString().trim();
                    String senha = editSenha.getText().toString().trim();

                    Usuario usuario = new Usuario();
                    usuario.setEmail(email);
                    usuario.setSenha(senha);
                    usuario.setNome(nome);

                    cadastrarUsuario(usuario);
                }
            }
        });
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

    private void cadastrarUsuario(Usuario usuario) {
        auth.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(CadastroActivity.this,
                                    "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT)
                                    .show();
                        }
                        else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                editSenhaLayout.setError("Digite uma senha mais forte");
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                editEmailLayout.setError("Email inválido");
                            } catch (FirebaseAuthUserCollisionException e) {
                                editEmailLayout.setError("O email digitado já está em uso");
                            } catch (Exception e) {
                                Toast.makeText(CadastroActivity.this,
                                        "Erro ao realizar cadastro: " + e.getMessage(), Toast.LENGTH_SHORT)
                                        .show();
                            }
                        }
                    }
                });
    }

}