package com.example.saborfuria.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDReservas extends SQLiteOpenHelper {

    public BDReservas(@Nullable Context context) {
        super(context, ConstantesR.NOMBREDB, null, ConstantesR.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + ConstantesR.NOMBRETABLA + "" +
                "(id integer primary key autoincrement,"+
                "nom text not null," +
                "ape text not null," +
                "numMesa text not null," +
                "cantPer text not null," +
                "fecRes text not null," +
                "horares text not null);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
