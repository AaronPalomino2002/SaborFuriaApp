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
import com.example.saborfuria.entidades.Boleta;
import com.example.saborfuria.modelos.DAOBoleta;


public class ActRegistrarBoleta extends AppCompatActivity {
    EditText edtNomCli, edtPreCli, edtCelCli, edtDirCli;

    Spinner spMetPag;

    Button regresarBo, listarBo, registarBo;

    String Meto[] = {"Tarjeta de Crédito", "Tarjeta de Débito", "Yape", "Efectivo"};

    DAOBoleta daoBoleta= new DAOBoleta(this);

    Boleta b;

    boolean isEditing = false; // Variable para determinar si estás editando un producto existente
    int boletaIdAEditar = -1; // Variable para almacenar el ID del producto a editar
    Boleta boletaAEditar = null; // Variable para almacenar los datos del producto a editar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_registrar_boleta);
        daoBoleta.openDB();
        asignarReferencia();
        Intent intent = getIntent();
        if (intent.hasExtra("boleta")) {
            boletaAEditar = (Boleta) intent.getSerializableExtra("boleta");
            if (boletaAEditar != null) {
                boletaIdAEditar = boletaAEditar.getId();
                cargarDatosProductoAEditar(boletaAEditar);
                isEditing=true;
            } else {
                Log.e("ActRegistrarBoletas", "La boleta a editar es nulo");
            }
        }

        registarBo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();
            }
        });

        regresarBo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Muestra un AlertDialog de confirmación para volver al menú
                AlertDialog.Builder builder = new AlertDialog.Builder(ActRegistrarBoleta.this);
                builder.setTitle("Confirmación");
                builder.setMessage("¿Estás seguro que deseas volver al menú? Los datos no guardados se perderán.");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Si el usuario elige "Sí", regresa a la actividad del menú
                        Intent intent = new Intent(ActRegistrarBoleta.this, ActInicioAdmi.class);
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
        listarBo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActRegistrarBoleta.this, ActListarBoletas.class);
                startActivity(intent);
            }
        });
    }



    private void asignarReferencia() {
        edtNomCli=findViewById(R.id.edtNomCli);
        edtPreCli= findViewById(R.id.edtPreCli);
        edtCelCli= findViewById(R.id.edtNumCli);
        edtDirCli= findViewById(R.id.edtDirecCli);
        spMetPag=findViewById(R.id.spMetPagCli);
        regresarBo= findViewById(R.id.btnRegBoleta);
        listarBo=findViewById(R.id.btnListaBoleta);
        registarBo= findViewById(R.id.btnRegisBoleta);
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,Meto);
        spMetPag.setAdapter(adapter);

    }

    private void cargarDatosProductoAEditar(Boleta boleta) {
        // Carga los datos del producto a editar en los campos de entrada
        edtNomCli.setText(boleta.getNom());
        edtPreCli.setText(boleta.getTotal()+"");
        edtCelCli.setText(boleta.getCelular());
        edtDirCli.setText(boleta.getDireccion());
        spMetPag.setSelection(obtenerIndiceTipoProducto(boleta.getMetPag()));
    }

    private int obtenerIndiceTipoProducto(String tipo) {
        for (int i = 0; i < Meto.length; i++) {
            if (Meto[i].equals(tipo)) {
                return i;
            }
        }
        return 0; // Devuelve el primer tipo por defecto si no se encuentra uno coincidente
    }

    public void validar() {
        boolean retorno = true;
        String nomPro = edtNomCli.getText().toString();
        String precioStr = edtPreCli.getText().toString();
        String celular = edtCelCli.getText().toString();
        String direccion = edtDirCli.getText().toString();
        String tipo = spMetPag.getSelectedItem().toString();


        // Validar que el campo de nombre no esté vacío
        if (nomPro.isEmpty()) {
            edtNomCli.setError("Campo obligatorio");
            retorno = false;
        }

        if (direccion.isEmpty()) {
            edtDirCli.setError("Campo obligatorio");
            retorno = false;
        }
        double precio=0;
        // Validar que el campo de precio sea un número válido
        try {
            precio = Double.parseDouble(precioStr);
            if (precio < 0) {
                edtPreCli.setError("Precio no válido");
                retorno = false;
            }
        } catch (NumberFormatException e) {
            edtPreCli.setError("Precio no válido");
            retorno = false;
        }

        // Validar que el campo de celular no esté vacío y sea un número válido
        if (celular.isEmpty()) {
            edtCelCli.setError("Campo obligatorio");
            retorno = false;
        } else if (celular.length() != 9) {
            edtCelCli.setError("El número de celular debe tener 9 dígitos");
            retorno = false;
        }

        if (retorno) {
            // Si los campos son válidos, muestra un cuadro de diálogo de confirmación
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirmación");
            builder.setMessage("¿Estás seguro de " + (isEditing ? "editar" : "registrar") + " la boleta?");

            double finalPrecio = precio;
            builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (isEditing) {
                        // Realiza la actualización del producto en la base de datos
                        actualizarProducto(boletaIdAEditar, nomPro, celular, direccion, tipo, finalPrecio);
                    } else {
                        // Registra el producto
                        b = new Boleta( nomPro, celular, direccion, tipo, finalPrecio);
                        daoBoleta.registrarBoleta(b);
                    }
                    // Limpia los campos después del registro o edición
                    limpiarCampos();

                    Toast.makeText(ActRegistrarBoleta.this, "Boleta " + (isEditing ? "editado" : "registrado") + " con éxito", Toast.LENGTH_SHORT).show();
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


    private void actualizarProducto(int id, String nom, String celular, String direccion, String metodo, double precio) {
        Boleta boletaActualizado = new Boleta(id, nom, celular, direccion, metodo, precio);
        daoBoleta.editarBoleta(boletaActualizado);
        Toast.makeText(this, "Boleta actualizado", Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null && data.hasExtra("boletaEditado")) {
                Boleta boletaEditado = data.getParcelableExtra("boletaEditado");
                if (boletaEditado != null) {
                    int id = boletaEditado.getId();
                    String nom = boletaEditado.getNom();
                    String cel = boletaEditado.getCelular();
                    String dir = boletaEditado.getDireccion();
                    String metPag = boletaEditado.getMetPag();
                    double precio = boletaEditado.getTotal();

                    // Realiza la lógica para actualizar el producto en la base de datos o en la vista
                    actualizarProducto(id, nom, cel, dir, metPag, precio);
                }
            }
        }
    }


    private void limpiarCampos() {
        // Limpia los campos de entrada
        edtNomCli.setText("");
        edtCelCli.setText("");
        edtDirCli.setText("");
        edtPreCli.setText("");
        spMetPag.setSelection(0); // Restablece el Spinner a la primera opción
    }
}
