package com.example.proyectoempresarial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class editarCliente extends AppCompatActivity {

    private ListAdapter listAdapter;

    TextInputEditText registroNumeroEditar, registroDireccionEditar, nombreClienteEditar;
    Button guardarClienteEditado;

    private listaClientes lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cliente);

        registroNumeroEditar = findViewById(R.id.registroNumeroEditar);
        registroDireccionEditar = findViewById(R.id.registroDireccionEditar);
        nombreClienteEditar = findViewById(R.id.nombreClienteEditar);

        guardarClienteEditado = findViewById(R.id.guardarClienteEditado);

        guardarClienteEditado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edicionCliente(v);
            }

        });
        lista = (listaClientes) getIntent().getSerializableExtra("lista");
        llenarDatosCliente();
    }

    private void llenarDatosCliente() {
        registroNumeroEditar.setText(String.valueOf(lista.getNumero()));
        registroNumeroEditar.setEnabled(true);
        nombreClienteEditar.setText(lista.getNombre());
        registroDireccionEditar.setText(lista.getDireccionCliente());
    }

    private void edicionCliente(View v) {
        SqlLite sqlLite = new SqlLite(this, "lista", null, 1);

        SQLiteDatabase sqLiteDatabase = sqlLite.getWritableDatabase();
        int numero = Integer.parseInt(registroNumeroEditar.getText().toString());
        String nombre = nombreClienteEditar.getText().toString();
        String direccion = registroDireccionEditar.getText().toString();

        ContentValues values = new ContentValues();
        values.put("numero", numero);
        values.put("nombre", nombre);
        values.put("direccionCliente", direccion);

        sqLiteDatabase.update("lista", values, "numero=" + numero, null);
        sqLiteDatabase.close();
        finish();
        Toast.makeText(this, "Datos Editados", Toast.LENGTH_SHORT).show();


        // los datos si se editan //falta refrescar
        //en este caso vamos a volver a mostrar el activity

        Intent intent = new Intent(editarCliente.this, Clientes.class);
        startActivity(intent);

    }
}