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

import com.google.android.material.textfield.TextInputEditText;

public class registrarCliente extends AppCompatActivity {

    private TextInputEditText nombreCliente, registroNumero, registroDireccion;
    private Button guardarCliente, mostrarCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);

        nombreCliente = (TextInputEditText) findViewById(R.id.nombreCliente);
        registroNumero = (TextInputEditText) findViewById(R.id.registroNumero);
        registroDireccion = (TextInputEditText) findViewById(R.id.registroDireccion);
        guardarCliente = (Button)  findViewById(R.id.guardarCliente);
        mostrarCliente = (Button) findViewById(R.id.mostrarCliente);
        guardarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombreCliente.getText().toString().equals("") || registroDireccion.getText().toString().equals("") ||
                        registroNumero.getText().toString().equals("")){
                    validarCliente();
                }else{
                    GuardarDatos(v);
                    limpiarTextos();
                    Toast.makeText(registrarCliente.this, "Datos Registrados", Toast.LENGTH_SHORT).show();
                }
            }

        });

        mostrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(registrarCliente.this, Clientes.class);
                startActivity(intent);
                Toast.makeText(registrarCliente.this, "Clientes Mostrados", Toast.LENGTH_SHORT).show();
                overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
            }
        });
    }

    private void GuardarDatos(View v) {
        SqlLite sqlLite = new SqlLite(this, "lista", null, 1);
        SQLiteDatabase sqLiteDatabase = sqlLite.getWritableDatabase();

        int number = Integer.parseInt(registroNumero.getText().toString());
        String nombre = nombreCliente.getText().toString();
        String direction = registroDireccion.getText().toString();

        ContentValues values = new ContentValues();
        values.put("numero", number);
        values.put("nombre", nombre);
        values.put("direccionCliente", direction);


        Long resultado = sqLiteDatabase.insert("lista", null, values);
        Toast.makeText(this, "Cliente registrado: " + resultado, Toast.LENGTH_SHORT).show();
    }

    private void limpiarTextos() {
        nombreCliente.setText("");
        registroNumero.setText("");
        registroDireccion.setText("");
    }

    public void validarCliente() {
        if (nombreCliente.getText().toString().equals("")) {
            nombreCliente.setText("Ingrese el nombre del cliente porfavor");
        }
        if (registroNumero.getText().toString().equals("")) {
            nombreCliente.setText("Ingrese el numero del cliente porfavor");
        }
        if (registroDireccion.getText().toString().equals("")) {
            nombreCliente.setText("Ingrese la direccion del cliente porfavor");
        }
    }
}