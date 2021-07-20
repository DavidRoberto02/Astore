package com.example.proyectoempresarial;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class recuperarContrasena extends AppCompatActivity {

    private EditText email;
    MaterialButton recuperar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasena);

        email = findViewById(R.id.email);

    }

    public void recuperar(View view) {
        validar();
    }

    public void validar() {
        String emailrecuperar = email.getText().toString().trim();

        if (emailrecuperar.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailrecuperar).matches()) {
            email.setError("Correo invalido");
            return;
        }

        sendEmail(emailrecuperar);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(recuperarContrasena.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);

    }

    public void sendEmail(String emailrecuperar) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAcceso = emailrecuperar;
        auth.sendPasswordResetEmail(emailAcceso)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //Mensaje de correo enviado.
                            Toast.makeText(recuperarContrasena.this,
                                    "Correo de recuperación de cuenta enviado", Toast.LENGTH_SHORT).show();
                            //Redirigiendo a la actividad principal de logeo
                            Intent intent = new Intent(recuperarContrasena.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }

}