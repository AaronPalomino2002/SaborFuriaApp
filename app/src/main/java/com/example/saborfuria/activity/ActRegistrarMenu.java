package com.example.saborfuria.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.saborfuria.ActInicioAdmi;
import com.example.saborfuria.R;
import com.example.saborfuria.entidades.Menu;
import com.example.saborfuria.modelos.DAOMenu;

public class ActRegistrarMenu extends AppCompatActivity {
    EditText edtNomMen, edtPreMen, edtDescriMen;

    Spinner spTipMen, spCantMen;

    Button btnRegresar, btnListar, btnRegistrar;

    String tipMen[] = {"Hamburguesas","Promociones","Complementos","Bebidas","Helados"};

    String cantMen[]= {"1","2","3","4","5","6"};

    DAOMenu daoMenu= new DAOMenu(this);

    Menu m;

    boolean isEditing = false; // Variable para determinar si estás editando un producto existente
    int menuIdAEditar = -1; // Variable para almacenar el ID del producto a editar
    Menu menuAEditar = null; // Variable para almacenar los datos del producto a editar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_registrar_menu);
        daoMenu.openDB();
        asignarReferencia();
        recuperarData();

        Intent intent = getIntent();
        if (intent.hasExtra("menu")) {
            menuAEditar = (Menu) intent.getSerializableExtra("menu");
            if (menuAEditar != null) {
                menuIdAEditar = menuAEditar.getId();
                cargarDatosProductoAEditar(menuAEditar);
                isEditing=true;
            } else {
                Log.e("ActRegistrarProductos", "El producto a editar es nulo");
            }
        }

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActRegistrarMenu.this);
                builder.setTitle("Confirmación");
                builder.setMessage("¿Estás seguro que deseas volver al menú? Los datos no guardados se perderán.");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Si el usuario elige "Sí", regresa a la actividad del menú
                        Intent intent = new Intent(ActRegistrarMenu.this, ActInicioAdmi.class);
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
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActRegistrarMenu.this, ActListarMenu.class);
                startActivity(intent);
            }
        });
    }

    private void recuperarData() {
    }

    private void asignarReferencia() {
        edtNomMen=findViewById(R.id.edtNomMenu);
        edtPreMen= findViewById(R.id.edtPrecioMenu);
        edtDescriMen= findViewById(R.id.edtDescriMenu);
        spCantMen=findViewById(R.id.spCantMenu);
        spTipMen= findViewById(R.id.spTipMenu);
        btnRegresar= findViewById(R.id.btnRegresarResMen);
        btnListar=findViewById(R.id.btnListaMenu);
        btnRegistrar= findViewById(R.id.btnRegistrarMenu);
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,tipMen);
        spTipMen.setAdapter(adapter);
        ArrayAdapter adapterN=new ArrayAdapter(this,android.R.layout.simple_list_item_1,cantMen);
        spCantMen.setAdapter(adapterN);
    }

    private void cargarDatosProductoAEditar(Menu menu) {
        // Carga los datos del producto a editar en los campos de entrada
        edtNomMen.setText(menu.getNomMen());
        edtPreMen.setText(String.valueOf(menu.getPrecio()));
        edtDescriMen.setText(menu.getDesc());
        spCantMen.setSelection(menu.getCantidad() - 1);
        spTipMen.setSelection(obtenerIndiceTipoProducto(menu.getTipMenu()));
    }

    private int obtenerIndiceTipoProducto(String tipo) {
        for (int i = 0; i < tipMen.length; i++) {
            if (tipMen[i].equals(tipo)) {
                return i;
            }
        }
        return 0; // Devuelve el primer tipo por defecto si no se encuentra uno coincidente
    }

    public void validar() {
        boolean retorno = true;
        String nomPro = edtNomMen.getText().toString();
        String precioStr = edtPreMen.getText().toString();
        String descripcion = edtDescriMen.getText().toString();
        String cantidadStr = spCantMen.getSelectedItem().toString();
        String tipo = spTipMen.getSelectedItem().toString();
        int cantidad = Integer.parseInt(cantidadStr);

        // Validar que el campo de nombre no esté vacío
        if (nomPro.isEmpty()) {
            edtNomMen.setError("Campo obligatorio");
            retorno = false;
        }

        // Validar que el campo de precio sea un número válido
        double precio = 0;
        try {
            precio = Double.parseDouble(precioStr);
            if (precio < 0) {
                edtPreMen.setError("Precio no válido");
                retorno = false;
            }
        } catch (NumberFormatException e) {
            edtPreMen.setError("Precio no válido");
            retorno = false;
        }

        // Validar que la cantidad sea un número válido y no negativo

        // Validar que el campo de descripción no esté vacío
        if (descripcion.isEmpty()) {
            edtDescriMen.setError("Campo obligatorio");
            retorno = false;
        }
        int foto=0;
        if(tipo=="Bebidas"){
            switch (nomPro){
                case "San Luis": foto=R.drawable.sanluis_500ml;
                    break;
                case "Coca Cola": foto=R.drawable.cocacola_500ml;
                    break;
                case "Inka Cola": foto=R.drawable.inkacola_500ml;
                    break;
                case "Fanta": foto=R.drawable.fanta_500ml;
                    break;
            }
        }
        if(tipo=="Complementos"){
            switch (nomPro){
                case "Nuggets": foto=R.drawable.nuggets;
                    break;
                case "Alitas BBQ": foto=R.drawable.alitas_bbq;
                    break;
                case "Papas Lays": foto=R.drawable.papas_lays;
                    break;
            }
        }

        if(tipo=="Helados"){
            switch (nomPro){
                case "Helado Simple": foto=R.drawable.helado_cono;
                    break;
                case "Helado Princesa": foto=R.drawable.helado_princesa;
                    break;
                case "Helado Sublime": foto=R.drawable.helado_sublime_dos;
                    break;
            }
        }

        if(tipo=="Promociones"){
            switch (nomPro){
                case "Combo Uno": foto=R.drawable.combo_uno;
                    break;
                case "Combo duo": foto=R.drawable.ham_parados;
                    break;
                case "Combo Simple": foto=R.drawable.combo_simple;
                    break;
            }
        }

        if(tipo=="Hamburguesas"){
            switch (nomPro){
                case "Hamburguesa Simple": foto=R.drawable.ham_principal;
                    break;
                case "Hamburguesa Doble": foto=R.drawable.hamburguesa_dobleu;
                    break;
                case "Hamburguesa Triple": foto=R.drawable.ham_triple;
                    break;
                case "Hamburguesa de Pollo": foto=R.drawable.ham_pollo;
                    break;
                case "Hamburguesa Integral": foto=R.drawable.ham_integral;
                    break;
            }
        }

        if (retorno) {
            // Si los campos son válidos, muestra un cuadro de diálogo de confirmación
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirmación");
            builder.setMessage("¿Estás seguro de " + (isEditing ? "editar" : "registrar") + " el Menu?");

            double finalPrecio = precio;
            int finalFoto = foto;
            builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (isEditing) {
                        // Realiza la actualización del producto en la base de datos
                        actualizarProducto(menuIdAEditar,finalFoto, tipo, nomPro, descripcion, finalPrecio, cantidad);
                    } else {
                        // Registra el producto
                        m = new Menu(finalFoto, tipo, nomPro, descripcion, finalPrecio, cantidad);
                        daoMenu.registrarMenu(m);
                    }
                    // Limpia los campos después del registro o edición
                    limpiarCampos();

                    // Agrega un log para verificar si se registró o editó en el DAO
                    /*Log.d("RegistroProducto", (isEditing ? "Producto editado en el DAO: " : "Producto registrado en el DAO: ") + p.getNomProducto());
                    Log.d("RegistroProducto", "Cantidad de la lista: " + daoProductos.getProductos().size());*/
                    Toast.makeText(ActRegistrarMenu.this, "Menu " + (isEditing ? "editado" : "registrado") + " con éxito", Toast.LENGTH_SHORT).show();
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


    private void actualizarProducto(int id, int foto,String tipo, String nomPro, String descripcion, double precio, int cantidad) {
        Menu menuActualizado = new Menu(id, foto, tipo, nomPro, descripcion, precio, cantidad);
        daoMenu.editarMenu(menuActualizado);
        Toast.makeText(this, "Menu actualizado", Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null && data.hasExtra("menuEditado")) {
                Menu menuEditado = data.getParcelableExtra("menuEditado");
                if (menuEditado != null) {
                    int id = menuEditado.getId();
                    String tipo = menuEditado.getTipMenu();
                    String nombre = menuEditado.getNomMen();
                    String descripcion = menuEditado.getDesc();
                    int cantidad = menuEditado.getCantidad();
                    double precio = menuEditado.getPrecio();
                    int foto= menuEditado.getFoto();

                    // Realiza la lógica para actualizar el producto en la base de datos o en la vista
                    actualizarProducto(id,foto, tipo, nombre, descripcion, precio, cantidad);
                }
            }
        }
    }


    private void limpiarCampos() {
        // Limpia los campos de entrada
        edtNomMen.setText("");
        edtPreMen.setText("");
        edtDescriMen.setText("");
        spCantMen.setSelection(0); // Restablece el Spinner a la primera opción
        spTipMen.setSelection(0); // Restablece el Spinner a la primera opción
    }
}
