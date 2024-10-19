package com.example.saborfuria.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.widget.Toast;
import com.example.saborfuria.ActInicioAdmi;
import com.example.saborfuria.R;
import com.example.saborfuria.entidades.Productos;
import com.example.saborfuria.modelos.DAOProductos;

import java.util.ArrayList;

public class ActRegistrarProductos extends AppCompatActivity {

    ArrayList<Productos> listaProductos;

    EditText edtNomProducto, edtPrePro, edtDescriPro;

    Spinner spTipPro, spCantPro;

    Button regresarMen, listarPro, registarPro;

    String tipPro[] = {"Hamburguesa","Pan","Vegetales"};

    String cantPro[]= {"1","2","3","4","5","6"};

    DAOProductos daoProductos= new DAOProductos(this);

    Productos p;

    boolean isEditing = false; // Variable para determinar si estás editando un producto existente
    int productoIdAEditar = -1; // Variable para almacenar el ID del producto a editar
    Productos productoAEditar = null; // Variable para almacenar los datos del producto a editar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_registrar_producto);
        daoProductos.openDB();
        asignarReferencia();
        recuperarData();

        Intent intent = getIntent();
        if (intent.hasExtra("productos")) {
            productoAEditar = (Productos) intent.getSerializableExtra("productos");
            if (productoAEditar != null) {
                productoIdAEditar = productoAEditar.getId();
                cargarDatosProductoAEditar(productoAEditar);
                isEditing=true;
            } else {
                Log.e("ActRegistrarProductos", "El producto a editar es nulo");
            }
        }

        registarPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();
            }
        });

        regresarMen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActRegistrarProductos.this);
                builder.setTitle("Confirmación");
                builder.setMessage("¿Estás seguro que deseas volver al menú? Los datos no guardados se perderán.");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Si el usuario elige "Sí", regresa a la actividad del menú
                        Intent intent = new Intent(ActRegistrarProductos.this, ActInicioAdmi.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Si el usuario elige "No", simplemente cierra el diálogo y permanece en la misma actividad
                    }
                });
                builder.show();
            }
        });
        listarPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActRegistrarProductos.this, ActListarProductos.class);
                startActivity(intent);
            }
        });
    }

    private void recuperarData() {
    }

    private void asignarReferencia() {
        edtNomProducto=findViewById(R.id.edtNomProducto);
        edtPrePro= findViewById(R.id.edtPrecioProducto);
        edtDescriPro= findViewById(R.id.edtDescriProducto);
        spCantPro=findViewById(R.id.spCantProducto);
        spTipPro= findViewById(R.id.spTipoProducto);
        regresarMen= findViewById(R.id.btnRegresarMenuAd);
        listarPro=findViewById(R.id.btnListaPro);
        registarPro= findViewById(R.id.btnRegistrarPro);
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,tipPro);
        spTipPro.setAdapter(adapter);
        ArrayAdapter adapterN=new ArrayAdapter(this,android.R.layout.simple_list_item_1,cantPro);
        spCantPro.setAdapter(adapterN);
    }

    private void cargarDatosProductoAEditar(Productos producto) {
        // Carga los datos del producto a editar en los campos de entrada
        edtNomProducto.setText(producto.getNomProducto());
        edtPrePro.setText(String.valueOf(producto.getPrecioProducto()));
        edtDescriPro.setText(producto.getDescriProducto());
        spCantPro.setSelection(producto.getCantidadProducto() - 1);
        spTipPro.setSelection(obtenerIndiceTipoProducto(producto.getTipoProducto()));
    }

    private int obtenerIndiceTipoProducto(String tipo) {
        for (int i = 0; i < tipPro.length; i++) {
            if (tipPro[i].equals(tipo)) {
                return i;
            }
        }
        return 0; // Devuelve el primer tipo por defecto si no se encuentra uno coincidente
    }

    public void validar() {
        boolean retorno = true;
        String nomPro = edtNomProducto.getText().toString();
        String precioStr = edtPrePro.getText().toString();
        String descripcion = edtDescriPro.getText().toString();
        String cantidadStr = spCantPro.getSelectedItem().toString();
        String tipo = spTipPro.getSelectedItem().toString();
        int cantidad = Integer.parseInt(cantidadStr);

        // Validar que el campo de nombre no esté vacío
        if (nomPro.isEmpty()) {
            edtNomProducto.setError("Campo obligatorio");
            retorno = false;
        }

        // Validar que el campo de precio sea un número válido
        double precio = 0;
        try {
            precio = Double.parseDouble(precioStr);
            if (precio < 0) {
                edtPrePro.setError("Precio no válido");
                retorno = false;
            }
        } catch (NumberFormatException e) {
            edtPrePro.setError("Precio no válido");
            retorno = false;
        }

        // Validar que la cantidad sea un número válido y no negativo

        // Validar que el campo de descripción no esté vacío
        if (descripcion.isEmpty()) {
            edtDescriPro.setError("Campo obligatorio");
            retorno = false;
        }

        int foto=0;
        if(tipo=="Hamburguesa"){
            switch (nomPro){
                case "Hamburguesa de Pollo": foto=R.drawable.prod_pollo;
                    break;
                case "Hamburguesa de Carne": foto=R.drawable.prod_carne;
                    break;
            }
        }

        if(tipo=="Pan"){
            switch (nomPro){
                case "Pan Integral": foto=R.drawable.prod_pan_int;
                    break;
                case "Pan Hamburguesa": foto=R.drawable.prod_pan_ham;
                    break;
            }
        }

        if(tipo=="Vegetales"){
            switch (nomPro){
                case "Tomate": foto=R.drawable.prod_tomate;
                    break;
                case "Lechuga": foto=R.drawable.prod_lechuga;
                    break;
                case "Zanahoria": foto=R.drawable.prod_zanahoria;
                    break;
                case "Pepino": foto=R.drawable.prod_pepino;
                    break;
                case "Cebolla": foto=R.drawable.prod_cebolla;
                    break;
            }
        }

        if (retorno) {
            // Si los campos son válidos, muestra un cuadro de diálogo de confirmación
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirmación");
            builder.setMessage("¿Estás seguro de " + (isEditing ? "editar" : "registrar") + " el producto?");

            double finalPrecio = precio;
            int finalFoto = foto;
            builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (isEditing) {
                        // Realiza la actualización del producto en la base de datos
                        actualizarProducto(productoIdAEditar, finalFoto, tipo, nomPro, descripcion, cantidad, finalPrecio);
                    } else {
                        // Registra el producto
                        p = new Productos(finalFoto, tipo, nomPro, descripcion, cantidad, finalPrecio);
                        daoProductos.registrarProducto(p);
                    }
                    // Limpia los campos después del registro o edición
                    limpiarCampos();

                    Toast.makeText(ActRegistrarProductos.this, "Producto " + (isEditing ? "editado" : "registrado") + " con éxito", Toast.LENGTH_SHORT).show();
                    // Redirige a la lista de productos

                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // No hace nada, simplemente cierra el cuadro de diálogo
                }
            });
            builder.show();
        }
    }


    private void actualizarProducto(int id, int foto ,String tipo, String nomPro, String descripcion, int cantidad, double precio) {
        Productos productoActualizado = new Productos(id, foto, tipo, nomPro, descripcion, cantidad, precio);
        daoProductos.editarProducto(productoActualizado);
        Toast.makeText(this, "Producto actualizado", Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null && data.hasExtra("productoEditado")) {
                Productos productoEditado = data.getParcelableExtra("productoEditado");
                if (productoEditado != null) {
                    int id = productoEditado.getId();
                    String tipo = productoEditado.getTipoProducto();
                    String nombre = productoEditado.getNomProducto();
                    String descripcion = productoEditado.getDescriProducto();
                    int cantidad = productoEditado.getCantidadProducto();
                    double precio = productoEditado.getPrecioProducto();
                    int foto= productoEditado.getFoto();

                    // Realiza la lógica para actualizar el producto en la base de datos o en la vista
                    actualizarProducto(id,foto, tipo, nombre, descripcion, cantidad, precio);
                }
            }
        }
    }


    private void limpiarCampos() {
        // Limpia los campos de entrada
        edtNomProducto.setText("");
        edtPrePro.setText("");
        edtDescriPro.setText("");
        spCantPro.setSelection(0); // Restablece el Spinner a la primera opción
        spTipPro.setSelection(0); // Restablece el Spinner a la primera opción
    }

}
