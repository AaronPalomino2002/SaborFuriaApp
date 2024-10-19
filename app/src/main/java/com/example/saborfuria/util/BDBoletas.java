package com.example.saborfuria.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDBoletas extends SQLiteOpenHelper {

    public BDBoletas(@Nullable Context context) {
        super(context, ConstantesB.NOMBREDB, null, ConstantesB.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + ConstantesB.NOMBRETABLA + "" +
                "(id integer primary key autoincrement,"+
                "nombre text not null," +
                "celular text not null," +
                "direccion text not null," +
                "metodoPago text not null," +
                "total double not null);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
