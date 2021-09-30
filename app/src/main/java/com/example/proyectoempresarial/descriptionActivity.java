package com.example.proyectoempresarial;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class descriptionActivity extends AppCompatActivity {

    TextView clienteTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        listaClientes listaClientes = (com.example.proyectoempresarial.listaClientes) getIntent().getSerializableExtra("listaClientes");
        clienteTextView = findViewById(R.id.clienteTextView);

        clienteTextView.setText(listaClientes.getNombre());
        //clienteTextView.setTextColor(Color.parseColor(listaClientes.getColor()));
    }
}