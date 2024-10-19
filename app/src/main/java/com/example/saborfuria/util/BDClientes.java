package com.example.saborfuria.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.saborfuria.R;

public class BDClientes extends SQLiteOpenHelper {

    public BDClientes(@Nullable Context context) {
        super(context, ConstantesC.NOMBREDB, null, ConstantesC.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + ConstantesC.NOMBRETABLA + "" +
                "(id integer primary key autoincrement,"+
                "foto integer not null," +
                "nombres text not null," +
                "apellidos text not null," +
                "distrito text not null," +
                "direccion text not null," +
                "correo text not null," +
                "celular text not null," +
                "contraseña text not null," +
                "edad integer not null);");

        insertarDatosIniciales(db);
    }

    public void insertarDatosIniciales(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("foto", R.drawable.adminlogin);
        values.put("nombres", "Juan");
        values.put("apellidos", "Pérez");
        values.put("distrito", "Lima");
        values.put("direccion", "Calle Principal 123");
        values.put("correo", "juan.perez@example.com");
        values.put("celular", "987654321");
        values.put("contraseña", "password123");
        values.put("edad", 30);

        db.insert(ConstantesC.NOMBRETABLA, null, values);

        // Repite este proceso para insertar más datos según sea necesario
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
