package com.example.saborfuria.modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.saborfuria.entidades.Menu;
import com.example.saborfuria.util.BDMenu;
import com.example.saborfuria.util.ConstantesM;


import java.util.ArrayList;

public class DAOMenu {

    BDMenu bdMenu;
    SQLiteDatabase database;

    public DAOMenu(Context context) { bdMenu= new BDMenu(context); }

    public void openDB() {database=bdMenu.getWritableDatabase(); }
    public void close(){
        bdMenu.close();
        database.close();
    }

    public void registrarMenu(Menu menu){
        try{
            ContentValues values=new ContentValues();
            values.put("foto",menu.getFoto());
            values.put("tipoMenu",menu.getTipMenu());
            values.put("nomMenu",menu.getNomMen());
            values.put("descripcion",menu.getDesc());
            values.put("precioMenu",menu.getPrecio());
            values.put("cantidadMenu",menu.getCantidad());
            database.insert(ConstantesM.NOMBRETABLA, null, values);
        } catch (Exception e) {

        }
    }



    public void editarMenu(Menu menu){
        try{
            ContentValues values=new ContentValues();
            values.put("foto",menu.getFoto());
            values.put("tipoMenu",menu.getTipMenu());
            values.put("nomMenu",menu.getNomMen());
            values.put("descripcion",menu.getDesc());
            values.put("precioMenu",menu.getPrecio());
            values.put("cantidadMenu",menu.getCantidad());
            database.update(ConstantesM.NOMBRETABLA,values,"id=?",
                    new String[]{menu.getId() + ""});
            //database.update(Constantes.NOMBRETABLA,values,"id="+persona.getId(),null);
        } catch (Exception e) {

        }
    }

    public void eliminarMenu(int id){
        try{
            database.delete(ConstantesM.NOMBRETABLA,"id="+id,null);
        } catch (Exception e) {

        }
    }

    public ArrayList<Menu> getMenus(){
        ArrayList<Menu> listaMenu=new ArrayList<>();
        try{
            Cursor c=database.rawQuery("select * from "+
                    ConstantesM.NOMBRETABLA,null);
            while(c.moveToNext()) listaMenu.add(new Menu(c.getInt(0), c.getInt(1),
                    c.getString(2), c.getString(3), c.getString(4),
                    c.getDouble(5), c.getInt(6)));
        } catch (Exception e) {

        }
        return listaMenu;
    }
}
