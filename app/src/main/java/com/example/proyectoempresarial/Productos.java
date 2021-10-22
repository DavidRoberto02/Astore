package com.example.proyectoempresarial;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Productos extends AppCompatActivity implements View.OnClickListener {

    EditText codigoDeBarras, nombreProducto, precioProducto;
    Button guardarProducto, eliminarProducto, consultarProducto;
    DatabaseReference mDatabaseReference;
    FirebaseDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        codigoDeBarras = findViewById(R.id.codigoDeBarras);
        nombreProducto = findViewById(R.id.nombreProducto);
        precioProducto = findViewById(R.id.precioProducto);

        guardarProducto = findViewById(R.id.guardarProducto);
        guardarProducto.setOnClickListener(this);

        eliminarProducto = findViewById(R.id.eliminarProducto);
        eliminarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productoEliminar = nombreProducto.getText().toString();
                if (!productoEliminar.isEmpty()) {
                    elimiarDato(productoEliminar);
                } else {
                    Toast.makeText(getApplicationContext(), "Ingresa el nombre del producto", Toast.LENGTH_LONG).show();

                }
            }
        });

        consultarProducto = findViewById(R.id.consultarProducto);
        consultarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseReference = FirebaseDatabase.getInstance().getReference("Producto");
                String producto = nombreProducto.getText().toString();
                if (!producto.isEmpty()) {
                    leerDatos(producto);
                } else {
                    Toast.makeText(getApplicationContext(), "Ingresa el nombre del producto", Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        //GUARDAR PRODUCTO
        String codigo = codigoDeBarras.getText().toString();
        String producto = nombreProducto.getText().toString();
        String precio = precioProducto.getText().toString();

        productosDB productdb = new productosDB(codigo, producto, precio);
        db = FirebaseDatabase.getInstance();
        mDatabaseReference = db.getReference("Producto");
        mDatabaseReference.child(producto).setValue(productdb).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //borramos los campos
                codigoDeBarras.setText("");
                nombreProducto.setText("");
                precioProducto.setText("");
                Toast.makeText(Productos.this, "Successfuly Updated", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void leerDatos(String producto) {

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Producto");
        mDatabaseReference.child(producto).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {

                    if (task.getResult().exists()) {

                        Toast.makeText(Productos.this, "Successfully Read", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String codigo = String.valueOf(dataSnapshot.child("codigo").getValue());
                        String precio = String.valueOf(dataSnapshot.child("precio").getValue());

                        //Edit text donde aparecera la recuperacion de datos.
                        codigoDeBarras.setText(codigo);
                        precioProducto.setText(precio);

                    } else {

                        Toast.makeText(Productos.this, "User Doesn't Exist", Toast.LENGTH_SHORT).show();

                    }


                } else {

                    Toast.makeText(Productos.this, "Failed to read", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void elimiarDato(String productoEliminar) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Producto");
        mDatabaseReference.child(productoEliminar).removeValue();
        //borramos los campos
        codigoDeBarras.setText("");
        nombreProducto.setText("");
        precioProducto.setText("");
        Toast.makeText(Productos.this, "Producto Eliminado", Toast.LENGTH_SHORT).show();
    }

}