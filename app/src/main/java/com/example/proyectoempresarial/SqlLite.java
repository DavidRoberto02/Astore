package com.example.proyectoempresarial;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqlLite extends SQLiteOpenHelper {


    public SqlLite(@Nullable Context context,@Nullable String name,@Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS lista (numero 'int', nombre 'varchar', direccionCliente 'varchar')");
        db.execSQL("CREATE TABLE apuntes (fecha 'date', apunte1 'int', apunte2 'int', apunte3 'int',apunte4 'int', apunte5 'int', apunte6 'int', apunte7 'int', apunte8 'int', apunte9 'int', apunte10 'int', apunte11 'int', apunte12 'int', apunte13 'int', apunte14 'int', apunte15 'int' )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
