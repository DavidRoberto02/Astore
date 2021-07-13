package com.example.proyectoempresarial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button registro, iniciar_Sesion;
    private EditText correo, contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Se establecen los textos editables y botones que tendran funciones

        registro = findViewById(R.id.registro);
        iniciar_Sesion = findViewById(R.id.iniciar_Sesion);
        correo = findViewById(R.id.correo);
        contraseña = findViewById(R.id.contraseña);
    }

    public void navegarFunciones (View view) {
        Intent intent = new Intent(MainActivity.this, funciones.class);
        startActivity(intent);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }


}