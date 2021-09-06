package com.example.proyectoempresarial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Clientes extends AppCompatActivity {
    List<listaClientes> mData;
    EditText Agregar;
    ImageView enter;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        recycler = findViewById(R.id.listRecyclerView);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        mData = new ArrayList<>();

        Agregar = findViewById(R.id.Agregar);
        enter = findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cliente();
            }
        });

    }

    public void cliente() {
        String clientes = Agregar.getText().toString();

        if (clientes.isEmpty()) {
            Toast.makeText(this, "Ingresa el cliente", Toast.LENGTH_SHORT).show();
        } else {
            //Crear varios clientes
            /*Al crear mas de 5 clientes se buguea y se crean 2 */
            mData.add(new listaClientes(clientes));
            Agregar.setText("");
            new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recycler);

            Toast.makeText(this, "Cliente agregado:" + clientes, Toast.LENGTH_SHORT).show();
        }

        //Funcion Click Listener para cada cliente
        ListAdapter listAdapter = new ListAdapter(mData, this, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(listaClientes item) {
                moveToDescription(item);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

    //Descripcion de cada cliente
    public void moveToDescription(listaClientes item) {
        Intent intent = new Intent(this, descriptionActivity.class);
        intent.putExtra("listaClientes", item);
        startActivity(intent);
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new ItemTouchHelper.SimpleCallback
            (0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            mData.remove(viewHolder.getAdapterPosition());
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
    };
}