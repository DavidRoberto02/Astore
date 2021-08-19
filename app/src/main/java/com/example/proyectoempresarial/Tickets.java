package com.example.proyectoempresarial;


import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.Toast;
import android.widget.EditText;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class Tickets extends AppCompatActivity {
    private EditText direccion, total;
    private Button add, imprimir;
    LinearLayout layout;
    AlertDialog dialog;
    int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);
        direccion = findViewById(R.id.direccion);
        total = findViewById(R.id.total);
        layout = findViewById(R.id.container);
        buildDialog();
        
        //Boton imprimir ticket
        imprimir = findViewById(R.id.imprimir);
        imprimir.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                //String de direccion y el total del producto de la nota de remision
                String direccionString = direccion.getText().toString();
                String preciototalString = total.getText().toString();
                //Textos de el layout container
                final EditText nameView = findViewById(R.id.name);
                final EditText precioView = findViewById(R.id.precioTarjeta);
                String nameValor = nameView.getText().toString();
                String precioValor = precioView.getText().toString();



                try {
                    printPDF(direccionString, preciototalString, nameValor, precioValor);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
        //Boton agregar
        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void printPDF(String direccionString, String preciototalString, String nameValor, String precioValor ) throws FileNotFoundException {
        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfPath, "notaPDF.pdf");
        OutputStream outputStream = new FileOutputStream(file);

        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        pdfDocument.setDefaultPageSize(PageSize.A6);

        document.setMargins(0, 0, 0, 0);
        //Informacion de la nota
        Paragraph ticket = new Paragraph("Nota de remision").setBold().setFontSize(24)
                .setTextAlignment(TextAlignment.CENTER);
        Paragraph nameStore = new Paragraph("Abarrotes y Deposito El Tigre\n")
                .setTextAlignment(TextAlignment.CENTER).setFontSize(15);

        //Dirección:
        Paragraph direction = new Paragraph("Direccion")
                .setTextAlignment(TextAlignment.LEFT).setFontSize(15);
        float[] infoD = {300f};
        Table direccion = new Table(infoD);
        //Alineacion de la tabla
        direccion.setHorizontalAlignment(HorizontalAlignment.LEFT);
        direccion.addCell(new Cell().add(new Paragraph(direccionString)));

        //TABLA DE INFORMACION
        float[] width = {150f, 150f};
        Table table = new Table(width);
        //Alineacion de la tabla
        table.setHorizontalAlignment(HorizontalAlignment.LEFT);
        //tabla
        table.addCell("informacion del producto");
        table.addCell("Precio");
        //producto
        table.addCell(new Cell().add(new Paragraph(nameValor).setTextAlignment(TextAlignment.LEFT).setFontSize(10)));
        //precio del producto
        table.addCell(new Cell().add(new Paragraph(precioValor).setTextAlignment(TextAlignment.LEFT).setFontSize(10)));
        //Total
        table.addCell(new Cell().add(new Paragraph("Total:")));
        //Precio total del producto
        table.addCell(new Cell().add(new Paragraph(preciototalString)));

        document.add(ticket);
        document.add(nameStore);
        document.add(direction);
        document.add(direccion);
        document.add(table);
        document.close();
        Toast.makeText(this, "PDF CREADO", Toast.LENGTH_LONG).show();
    }


    private void buildDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog, null);

        final EditText name = view.findViewById(R.id.nameEdit);
        final EditText precioTarjeta = view.findViewById(R.id.precioEdit);


        builder.setView(view);
        builder.setTitle("Ingrese el producto y precio")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        agregarTarjeta(name.getText().toString(), precioTarjeta.getText().toString());
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        dialog = builder.create();
    }

    //tarjetas de informacion
    private void agregarTarjeta(String name, String precioTarjeta) {
        final View view = getLayoutInflater().inflate(R.layout.tarjetas, null);

        EditText nameView = view.findViewById(R.id.name);
        EditText precioView = view.findViewById(R.id.precioTarjeta);

        //parametros a plasmar en la tarjeta de texto
        nameView.setText(name);
        precioView.setText(precioTarjeta);
        //Agregar a la vista container la tarjeta
        layout.addView(view);



    }


}