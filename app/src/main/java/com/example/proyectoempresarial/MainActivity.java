package com.example.proyectoempresarial;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private EditText correo, contraseña;

    //Llamamos las funciones de Firebase para autenticarse con usuario y contraseña
    private FirebaseAuth firebaseAuth;
    private static final String TAG = "EmailPassword";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Se establecen los textos editables y botones que tendran funciones

        correo = findViewById(R.id.correo);
        contraseña = findViewById(R.id.contraseña);

        //Firebase Authentication.
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Comprobara si el usuario a iniciado sesion.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            reload();
        }
    }

    //REGISTRAR USUARIO
    public void registroUsuario(View view) {
        String email = correo.getText().toString();
        String password = contraseña.getText().toString();
        //Damos inicio a el registro del usuario
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Si el registro fue exitoso, se actualizara la UI y navegara a las funciones de la aplicacion.
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            //Registro no exitoso, llenar los datos de nuevo.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Autenticación exitosa.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    //INICIAR SESION
    public void navegarFunciones(View view) {
        String email = correo.getText().toString();
        String password = contraseña.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task2) {
                        if (task2.isSuccessful()) {
                            // Si el registro fue exitoso, lo mandara a la siguiente interfaz de la aplicacion.
                            Toast.makeText(MainActivity.this, "Inicio de sesion exitoso", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, funciones.class);
                            startActivity(intent);
                        } else {
                            // Si el inicio de sesión no fue exitoso mostrara este mensaje a el usuario.
                            Log.e("ERROR", task2.getException().toString());
                            Toast.makeText(MainActivity.this, "Inicio de sesion fallido.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    //¿Olvidaste tu contraseña?
    public void recuperarCuenta(View view) {
        Intent intent = new Intent(MainActivity.this, recuperarContrasena.class);
        startActivity(intent);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    private void reload() {
    }

    private void updateUI(FirebaseUser user) {
    }

}