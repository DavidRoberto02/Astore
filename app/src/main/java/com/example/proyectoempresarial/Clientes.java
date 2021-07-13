package com.example.proyectoempresarial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Clientes extends AppCompatActivity {

    List<listaClientes> elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        init();
    }

    public void init() {
        elements = new ArrayList<>();
        elements.add(new listaClientes("Pedro", "#775447"));
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