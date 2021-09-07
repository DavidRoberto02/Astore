package com.example.proyectoempresarial;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.progressindicator.LinearProgressIndicatorSpec;

import java.util.ArrayList;
import java.util.List;

public class Clientes extends AppCompatActivity {
    List<listaClientes> mData;
    EditText Agregar;
    ImageView enter;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    static ListAdapter.OnItemClickListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        //RecyclerView
        recycler = findViewById(R.id.listRecyclerView);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        //ArrayList
        mData = new ArrayList<>();
        //Swipe to Refresh Activity
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        //boton agregar
        Agregar = findViewById(R.id.Agregar);
        //ImageView
        enter = findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cliente();
            }
        });

        //Refrescar Actividad
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        adapter = new ListAdapter(mData, this, listener);
        recycler.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallBack);
        itemTouchHelper.attachToRecyclerView(recycler);
        if (adapter != null){
            adapter.notifyDataSetChanged();
        }

    }

    private void refresh() {
        swipeRefreshLayout.setRefreshing(true);
        //Colores al cargar la aplicacion
        swipeRefreshLayout.setColorSchemeResources(R.color.design_default_color_secondary);
        refreshList();
    }

    //Refrescar lista
    private void refreshList() {
        swipeRefreshLayout.setRefreshing(false);
    }

    //CLIENTES
    public void cliente() {
        String clientes = Agregar.getText().toString();

        if (clientes.isEmpty()) {
            Toast.makeText(this, "Ingresa el cliente", Toast.LENGTH_SHORT).show();
        } else {
            //Crear varios clientes
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

    //SWIPE TO DELETE
    ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new ItemTouchHelper.SimpleCallback
            (0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            //Dialogo de advertencia
            AlertDialog.Builder builder = new AlertDialog.Builder(Clientes.this);
            builder.setTitle("Eliminar Cliente");
            builder.setMessage("Estas seguro(a) de eliminar el cliente");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int position = viewHolder.getAdapterPosition();
                    mData.remove(position);
                    adapter.notifyItemChanged(position);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adapter.notifyItemChanged(viewHolder.getAdapterPosition());
                }
            });
            builder.show();
        }
    };
}