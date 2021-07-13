package com.example.proyectoempresarial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class funciones extends AppCompatActivity {

    private Button clientes, productos, tickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funciones);

        //Enlazan los botones que navegaran a otras interfaces
        clientes = findViewById(R.id.clientes);
        productos = findViewById(R.id.productos);
        tickets = findViewById(R.id.tickets);

    }

    /*
    * Navegar mediante los botones establecidos a las interfaces como:
    * Clientes
    * Tickets
    * Productos
    * Cada una de ellas con una funcion distinta para el negocio.*/
    public void navegarClientes (View view) {
        Intent navegacion = new Intent(funciones.this, Clientes.class);
        startActivity(navegacion);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    public void navegarTickets (View view) {
        Intent navegacionT = new Intent(funciones.this, Tickets.class);
        startActivity(navegacionT);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    public void navegarProductos (View view){
        Intent navegacionP = new Intent(funciones.this, Productos.class);
        startActivity(navegacionP);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }
}