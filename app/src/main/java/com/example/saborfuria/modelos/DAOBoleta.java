package com.example.saborfuria.modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.saborfuria.entidades.Boleta;
import com.example.saborfuria.util.BDBoletas;
import com.example.saborfuria.util.ConstantesB;
import java.util.ArrayList;

public class DAOBoleta {

    BDBoletas bdBoletas;
    SQLiteDatabase database;

    public DAOBoleta(Context context) { bdBoletas= new BDBoletas(context); }

    public void openDB() {database=bdBoletas.getWritableDatabase(); }
    public void close(){
        bdBoletas.close();
        database.close();
    }

    public void registrarBoleta(Boleta boleta){
        try{
            ContentValues values=new ContentValues();
            values.put("nombre",boleta.getNom());
            values.put("celular",boleta.getCelular());
            values.put("direccion",boleta.getDireccion());
            values.put("metodoPago",boleta.getMetPag());
            values.put("total",boleta.getTotal());

            database.insert(ConstantesB.NOMBRETABLA, null, values);
        } catch (Exception e) {

        }
    }



    public void editarBoleta(Boleta boleta){
        try{
            ContentValues values=new ContentValues();
            values.put("nombre",boleta.getNom());
            values.put("celular",boleta.getCelular());
            values.put("direccion",boleta.getDireccion());
            values.put("metodoPago",boleta.getMetPag());
            values.put("total",boleta.getTotal());
            database.update(ConstantesB.NOMBRETABLA,values,"id=?",
                    new String[]{boleta.getId() + ""});
            //database.update(Constantes.NOMBRETABLA,values,"id="+persona.getId(),null);
        } catch (Exception e) {

        }
    }

    public void eliminarBoleta(int id){
        try{
            database.delete(ConstantesB.NOMBRETABLA,"id="+id,null);
        } catch (Exception e) {

        }
    }

    public ArrayList<Boleta> getBoletas() {
        ArrayList<Boleta> listaBoletas = new ArrayList<>();
        try {
            Cursor c = database.rawQuery("select * from " + ConstantesB.NOMBRETABLA, null);
            while (c.moveToNext()) {
                listaBoletas.add(new Boleta(c.getInt(0),c.getString(1),
                        c.getString(2), c.getString(3), c.getString(4),
                        c.getDouble(5)));
            }
        } catch (Exception e) {
            Log.e("ERROR", "Error al recuperar boletas: " + e.getMessage());
        }
        return listaBoletas;
    }
}
