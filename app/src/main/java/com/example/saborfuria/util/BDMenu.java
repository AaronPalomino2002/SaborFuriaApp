package com.example.saborfuria.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.saborfuria.R;
import com.example.saborfuria.entidades.Menu;

public class BDMenu extends SQLiteOpenHelper {

    public BDMenu(@Nullable Context context) {
        super(context, ConstantesM.NOMBREDB, null, ConstantesM.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + ConstantesM.NOMBRETABLA + " (" +
                "id integer primary key autoincrement, " +
                "foto integer not null, " +
                "tipoMenu text not null, " +
                "nomMenu text not null, " +
                "descripcion text not null, " +
                "precioMenu double not null, " +
                "cantidadMenu integer not null);");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
