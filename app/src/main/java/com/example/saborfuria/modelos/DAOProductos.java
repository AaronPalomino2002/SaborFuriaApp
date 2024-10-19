package com.example.saborfuria.modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.saborfuria.entidades.Cliente;
import com.example.saborfuria.entidades.Productos;
import com.example.saborfuria.util.BDProductos;
import com.example.saborfuria.util.ConstantesC;
import com.example.saborfuria.util.ConstantesP;

import java.util.ArrayList;

public class DAOProductos {

    BDProductos bdProductos;
    SQLiteDatabase database;

    public DAOProductos(Context context) { bdProductos= new BDProductos(context); }

    public void openDB() {database=bdProductos.getWritableDatabase(); }
    public void close(){
        bdProductos.close();
        database.close();
    }

    public void registrarProducto(Productos productos){
        try{
            ContentValues values=new ContentValues();
            values.put("foto",productos.getFoto());
            values.put("tipoProducto",productos.getTipoProducto());
            values.put("nomProducto",productos.getNomProducto());
            values.put("descriProducto",productos.getDescriProducto());
            values.put("cantidadProducto",productos.getCantidadProducto());
            values.put("precioProducto",productos.getPrecioProducto());
            database.insert(ConstantesP.NOMBRETABLA, null, values);
        } catch (Exception e) {

        }
    }



    public void editarProducto(Productos productos){
        try{
            ContentValues values=new ContentValues();
            values.put("foto",productos.getFoto());
            values.put("tipoProducto",productos.getTipoProducto());
            values.put("nomProducto",productos.getNomProducto());
            values.put("descriProducto",productos.getDescriProducto());
            values.put("cantidadProducto",productos.getCantidadProducto());
            values.put("precioProducto",productos.getPrecioProducto());
            database.update(ConstantesP.NOMBRETABLA,values,"id=?",
                    new String[]{productos.getId() + ""});
            //database.update(Constantes.NOMBRETABLA,values,"id="+persona.getId(),null);
        } catch (Exception e) {

        }
    }

    public void eliminarProducto(int id){
        try{
            database.delete(ConstantesP.NOMBRETABLA,"id="+id,null);
        } catch (Exception e) {

        }
    }

    public ArrayList<Productos> getProductos(){
        ArrayList<Productos> listaProductos=new ArrayList<>();
        try{
            Cursor c=database.rawQuery("select * from "+
                    ConstantesP.NOMBRETABLA,null);
            while(c.moveToNext()) listaProductos.add(new Productos(c.getInt(0), c.getInt(1),
                    c.getString(2), c.getString(3), c.getString(4),
                    c.getInt(5), c.getDouble(6)));
        } catch (Exception e) {

        }
        return listaProductos;
    }
}
