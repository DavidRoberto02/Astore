package com.example.proyectoempresarial;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Clientes extends AppCompatActivity {

    List<listaClientes> elements;
    private EditText Agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        init();
        Agregar = findViewById(R.id.Agregar);
        Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarCliente();
            }
        });
    }

    private void agregarCliente() {
        
    }

    public void init() {
        //Array list
        elements = new ArrayList<>();
        elements.add(new listaClientes("Pedro", "#775447"));
        elements.add(new listaClientes("Daniel", "#607d8b"));
        elements.add(new listaClientes("Isabel", "#03a9f4"));
        elements.add(new listaClientes("Alex", "#f44336"));
        elements.add(new listaClientes("Ileana", "#009688"));
        elements.add(new listaClientes("Daniel", "#607d8b"));
        elements.add(new listaClientes("Isabel", "#03a9f4"));
        elements.add(new listaClientes("Alex", "#f44336"));
        elements.add(new listaClientes("Ileana", "#009688"));
        elements.add(new listaClientes("Daniel", "#607d8b"));
        elements.add(new listaClientes("Isabel", "#03a9f4"));
        elements.add(new listaClientes("Alex", "#f44336"));
        elements.add(new listaClientes("Ileana", "#009688"));


        ListAdapter listAdapter = new ListAdapter(elements, this);
        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }


}