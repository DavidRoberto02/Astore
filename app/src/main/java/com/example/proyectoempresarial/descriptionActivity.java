package com.example.proyectoempresarial;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class descriptionActivity extends AppCompatActivity {

    TextView clienteTextView;
    Button guardarApunte, borrarApunte, mostrarApuntes;
    EditText fecha, apunte1, apunte2, apunte3, apunte4, apunte5, apunte6, apunte7,
            apunte8, apunte9, apunte10, apunte11, apunte12, apunte13, apunte14, apunte15;
    SqlLite sqlLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        sqlLite = new SqlLite(this, "apuntes", null, 1);
        listaClientes listaClientes = (com.example.proyectoempresarial.listaClientes) getIntent().getSerializableExtra("listaClientes");
        clienteTextView = findViewById(R.id.clienteTextView);
        clienteTextView.setText(listaClientes.getNombre());
        fecha = findViewById(R.id.fecha);
        apunte1 = findViewById(R.id.apunte1);
        apunte2 = findViewById(R.id.apunte2);
        apunte3 = findViewById(R.id.apunte3);
        apunte4 = findViewById(R.id.apunte4);
        apunte5 = findViewById(R.id.apunte5);
        apunte6 = findViewById(R.id.apunte6);
        apunte7 = findViewById(R.id.apunte7);
        apunte8 = findViewById(R.id.apunte8);
        apunte9 = findViewById(R.id.apunte9);
        apunte10 = findViewById(R.id.apunte10);
        apunte11 = findViewById(R.id.apunte11);
        apunte12 = findViewById(R.id.apunte12);
        apunte13 = findViewById(R.id.apunte13);
        apunte14 = findViewById(R.id.apunte14);
        apunte15 = findViewById(R.id.apunte15);

        guardarApunte = findViewById(R.id.guardarApunte);
        guardarApunte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datosGuardados();
                Toast.makeText(getApplicationContext(), "Apuntes almacenados", Toast.LENGTH_LONG).show();
            }

        });

        borrarApunte = findViewById(R.id.borrarApunte);
        borrarApunte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datosBorrados();
                Toast.makeText(getApplicationContext(), "Apuntes borrados", Toast.LENGTH_LONG).show();

            }
        });
        mostrarApuntes = findViewById(R.id.mostrarApuntes);
        mostrarApuntes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apuntesMostrados();
                Toast.makeText(getApplicationContext(), "Apuntes mostrados", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void datosGuardados() {
        //Llamamos a la base de datos
        SqlLite sqlLite = new SqlLite(this, "apuntes", null, 1);
        SQLiteDatabase sqLiteDatabase = sqlLite.getWritableDatabase();
        //Edit Text que se guardaran los datos.
        String Fecha = fecha.getText().toString();
        int registro1 = Integer.parseInt(apunte1.getText().toString());
        int registro2 = Integer.parseInt(apunte2.getText().toString());
        int registro3 = Integer.parseInt(apunte3.getText().toString());
        int registro4 = Integer.parseInt(apunte4.getText().toString());
        int registro5 = Integer.parseInt(apunte5.getText().toString());
        int registro6 = Integer.parseInt(apunte6.getText().toString());
        int registro7 = Integer.parseInt(apunte7.getText().toString());
        int registro8 = Integer.parseInt(apunte8.getText().toString());
        int registro9 = Integer.parseInt(apunte9.getText().toString());
        int registro10 = Integer.parseInt(apunte10.getText().toString());
        int registro11 = Integer.parseInt(apunte11.getText().toString());
        int registro12 = Integer.parseInt(apunte12.getText().toString());
        int registro13 = Integer.parseInt(apunte13.getText().toString());
        int registro14 = Integer.parseInt(apunte14.getText().toString());
        int registro15 = Integer.parseInt(apunte15.getText().toString());

        //insertamos los datos a la base de datos y la cerramos
        ContentValues guardar = new ContentValues();
        guardar.put("fecha", Fecha);
        guardar.put("apunte1", registro1);
        guardar.put("apunte2", registro2);
        guardar.put("apunte3", registro3);
        guardar.put("apunte4", registro4);
        guardar.put("apunte5", registro5);
        guardar.put("apunte6", registro6);
        guardar.put("apunte7", registro7);
        guardar.put("apunte8", registro8);
        guardar.put("apunte9", registro9);
        guardar.put("apunte10", registro10);
        guardar.put("apunte11", registro11);
        guardar.put("apunte12", registro12);
        guardar.put("apunte13", registro13);
        guardar.put("apunte14", registro14);
        guardar.put("apunte15", registro15);
        sqLiteDatabase.insert("apuntes", null, guardar);
        sqLiteDatabase.close();
        //borramos los campos de texto.
        fecha.setText("");
        apunte1.setText("");
        apunte2.setText("");
        apunte3.setText("");
        apunte4.setText("");
        apunte5.setText("");
        apunte6.setText("");
        apunte7.setText("");
        apunte8.setText("");
        apunte9.setText("");
        apunte10.setText("");
        apunte11.setText("");
        apunte12.setText("");
        apunte13.setText("");
        apunte14.setText("");
        apunte15.setText("");
    }


    private void datosBorrados() {
        SqlLite sqlLite = new SqlLite(this, "apuntes", null, 1);
        SQLiteDatabase sqLiteDatabase = sqlLite.getWritableDatabase();
        String Fecha = fecha.getText().toString();

        if (!Fecha.isEmpty()){
            int cantidad = sqLiteDatabase.delete("apuntes", "fecha=" + Fecha, null);
            sqLiteDatabase.close();

            fecha.setText("");
            apunte1.setText("");
            apunte2.setText("");
            apunte3.setText("");
            apunte4.setText("");
            apunte5.setText("");
            apunte6.setText("");
            apunte7.setText("");
            apunte8.setText("");
            apunte9.setText("");
            apunte10.setText("");
            apunte11.setText("");
            apunte12.setText("");
            apunte13.setText("");
            apunte14.setText("");
            apunte15.setText("");
            if (cantidad == 1) {
                Toast.makeText(this, "Los datos fueron eliminados exitosamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "El cliente no existe", Toast.LENGTH_SHORT).show();

            }
        }else{
            Toast.makeText(this, "Para eliminar debes ingresar la fecha del cliente", Toast.LENGTH_SHORT).show();

        }

    }


    private void apuntesMostrados() {
        SqlLite sqlLite = new SqlLite(this, "apuntes", null, 1);
        SQLiteDatabase sqLiteDatabase = sqlLite.getWritableDatabase();

        //Edit Text que se mostraran los datos.
        String Fecha = fecha.getText().toString();
        //condicionamos que si no ingresa la fecha en la que se  registro no se mostrara nada.
        if (!Fecha.isEmpty()) {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT apunte1, apunte2, apunte3,apunte4, apunte5, apunte6, apunte7, apunte8, apunte9, apunte10, apunte11, apunte12, apunte13, apunte14, apunte15 FROM apuntes WHERE fecha="+Fecha, null);

            if (cursor.moveToFirst()) {
                apunte1.setText(cursor.getString(0));
                apunte2.setText(cursor.getString(1));
                apunte3.setText(cursor.getString(2));
                apunte4.setText(cursor.getString(3));
                apunte5.setText(cursor.getString(4));
                apunte6.setText(cursor.getString(5));
                apunte7.setText(cursor.getString(6));
                apunte8.setText(cursor.getString(7));
                apunte9.setText(cursor.getString(8));
                apunte10.setText(cursor.getString(9));
                apunte11.setText(cursor.getString(10));
                apunte12.setText(cursor.getString(11));
                apunte13.setText(cursor.getString(12));
                apunte14.setText(cursor.getString(13));
                apunte15.setText(cursor.getString(14));

            } else {
                Toast.makeText(this, "apuntes mostrados", Toast.LENGTH_SHORT).show();
                sqLiteDatabase.close();

            }
        } else {
            Toast.makeText(this, "Ingrese la fecha", Toast.LENGTH_SHORT).show();

        }

    }

}