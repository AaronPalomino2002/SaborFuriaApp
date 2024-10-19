package com.example.saborfuria.modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.saborfuria.entidades.Cliente;
import com.example.saborfuria.util.BDClientes;
import com.example.saborfuria.util.ConstantesC;

import java.util.ArrayList;

public class DAOClientes {

    BDClientes bdClientes;
    SQLiteDatabase database;

    public DAOClientes(Context context) { bdClientes= new BDClientes(context); }

    public void openDB() {database=bdClientes.getWritableDatabase(); }
    public void close(){
        bdClientes.close();
        database.close();
    }

    public void registrarCliente(Cliente cliente){
        try{
            ContentValues values=new ContentValues();
            values.put("foto",cliente.getFoto());
            values.put("nombres",cliente.getNombres());
            values.put("apellidos",cliente.getApellidos());
            values.put("distrito",cliente.getDistrito());
            values.put("direccion",cliente.getDireccion());
            values.put("correo",cliente.getCorreo());
            values.put("celular",cliente.getCelular());
            values.put("contraseña",cliente.getContraseña());
            values.put("edad",cliente.getEdad());
            database.insert(ConstantesC.NOMBRETABLA, null, values);
        } catch (Exception e) {

        }
    }



    public void editarCliente(Cliente cliente){
        try{
            ContentValues values=new ContentValues();
            values.put("foto",cliente.getFoto());
            values.put("nombres",cliente.getNombres());
            values.put("apellidos",cliente.getApellidos());
            values.put("distrito",cliente.getDistrito());
            values.put("direccion",cliente.getDireccion());
            values.put("correo",cliente.getCorreo());
            values.put("celular",cliente.getCelular());
            values.put("contraseña",cliente.getContraseña());
            values.put("edad",cliente.getEdad());

            database.update(ConstantesC.NOMBRETABLA,values,"id=?",
                    new String[]{cliente.getId() + ""});
            //database.update(Constantes.NOMBRETABLA,values,"id="+persona.getId(),null);
        } catch (Exception e) {

        }
    }

    public void eliminarCliente(int id){
        try{
            database.delete(ConstantesC.NOMBRETABLA,"id="+id,null);
        } catch (Exception e) {

        }
    }

    public ArrayList<Cliente> getCliente() {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        try {
            Cursor c = database.rawQuery("select * from " + ConstantesC.NOMBRETABLA, null);
            while (c.moveToNext()) {
                listaClientes.add(new Cliente(c.getInt(0), c.getInt(1),
                        c.getString(2), c.getString(3), c.getString(4),
                        c.getString(5), c.getString(6),
                        c.getString(7), c.getString(8),
                        c.getInt(9)));
            }
            Log.d("DEBUG", "Cantidad de clientes recuperados: " + listaClientes.size());
        } catch (Exception e) {
            Log.e("ERROR", "Error al recuperar clientes: " + e.getMessage());
        }
        return listaClientes;
    }

}
