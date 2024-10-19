package com.example.saborfuria.modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.saborfuria.entidades.Cliente;
import com.example.saborfuria.entidades.Productos;
import com.example.saborfuria.entidades.Reserva;
import com.example.saborfuria.util.BDProductos;
import com.example.saborfuria.util.BDReservas;
import com.example.saborfuria.util.ConstantesC;
import com.example.saborfuria.util.ConstantesR;

import java.util.ArrayList;

public class DAOReservas {

    BDReservas bdReservas;
    SQLiteDatabase database;

    public DAOReservas(Context context) { bdReservas= new BDReservas(context); }

    public void openDB() {database=bdReservas.getWritableDatabase(); }
    public void close(){
        bdReservas.close();
        database.close();
    }

    public void registrarReserva(Reserva reserva){
        try{
            ContentValues values=new ContentValues();
            values.put("nom",reserva.getNom());
            values.put("ape",reserva.getApe());
            values.put("numMesa",reserva.getNumMesa());
            values.put("cantPer",reserva.getCantPer());
            values.put("fecRes",reserva.getFecRes());
            values.put("horares",reserva.getHorares());
            database.insert(ConstantesR.NOMBRETABLA, null, values);
        } catch (Exception e) {

        }
    }



    public void editarProducto(Reserva reserva){
        try{
            ContentValues values=new ContentValues();
            values.put("nom",reserva.getNom());
            values.put("ape",reserva.getApe());
            values.put("numMesa",reserva.getNumMesa());
            values.put("cantPer",reserva.getCantPer());
            values.put("fecRes",reserva.getFecRes());
            values.put("horares",reserva.getHorares());
            database.update(ConstantesR.NOMBRETABLA,values,"id=?",
                    new String[]{reserva.getId() + ""});
            //database.update(Constantes.NOMBRETABLA,values,"id="+persona.getId(),null);
        } catch (Exception e) {

        }
    }

    public void eliminarReserva(int id){
        try{
            database.delete(ConstantesR.NOMBRETABLA,"id="+id,null);
        } catch (Exception e) {

        }
    }

    public ArrayList<Reserva> getReserva(){
        ArrayList<Reserva> listaReserva=new ArrayList<>();
        try{
            Cursor c=database.rawQuery("select * from "+
                    ConstantesR.NOMBRETABLA,null);
            while(c.moveToNext()) listaReserva.add(new Reserva(c.getInt(0),
                    c.getString(1), c.getString(2), c.getString(3),
                    c.getString(4), c.getString(5), c.getString(6)));
        } catch (Exception e) {

        }
        return listaReserva;
    }
}
