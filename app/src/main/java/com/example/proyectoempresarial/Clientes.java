package com.example.proyectoempresarial;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.progressindicator.LinearProgressIndicatorSpec;

import java.util.ArrayList;
import java.util.List;

public class Clientes extends AppCompatActivity implements IAXiliarPersona {
    ArrayList<listaClientes> clientesArrayList;

    SqlLite sqlLite;
    RecyclerView idrecyclerview;
    private ListAdapter listAdapter;
    static ListAdapter.OnItemClickListener listener;
    ImageButton regresarCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        idrecyclerview = findViewById(R.id.idrecyclerview);
        clientesArrayList = new ArrayList<>();
        sqlLite = new SqlLite(this, "lista", null, 1);
        listAdapter = new ListAdapter(this, clientesArrayList, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(listaClientes item) {
                moveToDescription(item);
            }
        });
        regresarCliente = findViewById(R.id.regresarCliente);
        regresarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresarRegistroCliente();
            }

            private void regresarRegistroCliente() {
                Intent intent = new Intent(Clientes.this, registrarCliente.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.idrecyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(listAdapter);
        cliente();

    }

    //CLIENTES
    public void cliente() {
        SQLiteDatabase sqLiteDatabase = sqlLite.getReadableDatabase();
        listaClientes lista = null;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM lista", null);
        while (cursor.moveToNext()) {
            lista = new listaClientes();
            lista.setNumero(cursor.getInt(0));
            lista.setNombre(cursor.getString(1));
            lista.setDireccionCliente(cursor.getString(2));
            listAdapter.agregarPersona(lista);
        }
    }

    //Descripcion de cada cliente
    public void moveToDescription(listaClientes lista) {
        Intent intent = new Intent(this, descriptionActivity.class);
        intent.putExtra("listaClientes", lista);
        startActivity(intent);
    }

    @Override
    public void editar(listaClientes lista) {
        Intent intent = new Intent(Clientes.this, editarCliente.class);
        intent.putExtra("lista", lista);
        startActivity(intent);
    }

    @Override
    public void eliminar(listaClientes lista) {

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Mensaje");
        alerta.setMessage("Esta seguro que desea Eliminar? " + lista.getNombre() + " " + lista.getDireccionCliente());
        alerta.setCancelable(false);
        alerta.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                eliminarPersona(lista);
            }

        });
        alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alerta.show();
    }

    private void eliminarPersona(listaClientes lista) {
        SqlLite sqlLite = new SqlLite(this, "lista", null, 1);
        SQLiteDatabase sqLiteDatabase = sqlLite.getWritableDatabase();
        String numero = String.valueOf(lista.getNumero());
        if(!numero.isEmpty()){
            sqLiteDatabase.delete("lista","numero="+numero,null);
            Toast.makeText(this, "Se Elimino Correctamente", Toast.LENGTH_SHORT).show();
            listAdapter.eliminarPersona(lista);
            sqLiteDatabase.close();
        }else{
            Toast.makeText(this, "No se ha podido eliminar ", Toast.LENGTH_SHORT).show();
        }
    }
}