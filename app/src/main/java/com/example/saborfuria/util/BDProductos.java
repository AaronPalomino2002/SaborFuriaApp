package com.example.saborfuria.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDProductos extends SQLiteOpenHelper {
    public BDProductos(@Nullable Context context) {
        super(context, ConstantesP.NOMBREDB, null, ConstantesP.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + ConstantesP.NOMBRETABLA + " (" +
                "id integer primary key autoincrement, " +
                "foto integer not null, " +
                "tipoProducto text not null, " +
                "nomProducto text not null, " +
                "descriProducto text not null, " +
                "cantidadProducto integer not null, " +
                "precioProducto double not null);");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
