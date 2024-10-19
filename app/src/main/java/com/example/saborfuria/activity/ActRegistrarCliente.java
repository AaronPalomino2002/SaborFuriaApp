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
import com.example.saborfuria.entidades.Cliente;
import com.example.saborfuria.modelos.DAOClientes;

public class ActRegistrarCliente extends AppCompatActivity {

    EditText edtNomCli, edtApeCli, edtDirCli, edtCorreo, edtContra, edtEdad, edtCelCli;

    Spinner spDis;

    Button regresarCli, listarCli, registarCli;

    String distri[]={"Barranco","Breña","La Molina","Lince","Magdalena del Mar","Miraflores","San Isidro",
            "San luis","San Miguel","Santa Anita","Santiago de Surco","Surquillo"};

    DAOClientes daoClientes = new DAOClientes(this);

    Cliente c;

    boolean isEditing = false; // Variable para determinar si estás editando un producto existente
    int clienteIdAEditar = -1; // Variable para almacenar el ID del producto a editar
    Cliente clienteAEditar = null; // Variable para almacenar los datos del producto a editar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_cliente);
        daoClientes.openDB();
        asignarReferencia();
        Intent intent = getIntent();
        if (intent.hasExtra("cliente")) {
            clienteAEditar = (Cliente) intent.getSerializableExtra("cliente");
            if (clienteAEditar != null) {
                clienteIdAEditar = clienteAEditar.getId();
                cargarDatosProductoAEditar(clienteAEditar);
                isEditing=true;
            } else {
                Log.e("ActRegistrarBoletas", "El cliente a editar es nulo");
            }
        }

        registarCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();
            }
        });

        regresarCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Muestra un AlertDialog de confirmación para volver al menú
                AlertDialog.Builder builder = new AlertDialog.Builder(ActRegistrarCliente.this);
                builder.setTitle("Confirmación");
                builder.setMessage("¿Estás seguro que deseas volver al menú? Los datos no guardados se perderán.");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Si el usuario elige "Sí", regresa a la actividad del menú
                        Intent intent = new Intent(ActRegistrarCliente.this, ActInicioAdmi.class);
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
        listarCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActRegistrarCliente.this, ActListarClientes.class);
                startActivity(intent);
            }
        });
    }



    private void asignarReferencia() {
        edtNomCli=findViewById(R.id.edtNomI);
        edtApeCli= findViewById(R.id.edtApeI);
        edtDirCli= findViewById(R.id.edtDirI);
        edtCelCli=findViewById(R.id.edtCelI);
        spDis=findViewById(R.id.spDisI);
        edtCorreo= findViewById(R.id.edtCorreoI);
        edtContra= findViewById(R.id.edtConI);
        edtEdad= findViewById(R.id.edtEdadI);
        regresarCli= findViewById(R.id.button7);
        listarCli=findViewById(R.id.button9);
        registarCli= findViewById(R.id.button8);
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,distri);
        spDis.setAdapter(adapter);

    }

    private void cargarDatosProductoAEditar(Cliente cliente) {
        // Carga los datos del producto a editar en los campos de entrada
        edtNomCli.setText(cliente.getNombres());
        edtApeCli.setText(cliente.getApellidos());
        edtDirCli.setText(cliente.getDistrito());
        edtContra.setText(cliente.getContraseña());
        edtEdad.setText(cliente.getEdad()+"");
        edtCorreo.setText(cliente.getCorreo());
        edtCelCli.setText(cliente.getCelular());
        spDis.setSelection(obtenerIndiceTipoProducto(cliente.getDistrito()));
    }

    private int obtenerIndiceTipoProducto(String tipo) {
        for (int i = 0; i < distri.length; i++) {
            if (distri[i].equals(tipo)) {
                return i;
            }
        }
        return 0; // Devuelve el primer tipo por defecto si no se encuentra uno coincidente
    }

    public void validar() {
        String nom, ape, dis, dir, correo, cel, contra;
        int edad ;

        nom = edtNomCli.getText().toString();
        ape = edtApeCli.getText().toString();
        dis = spDis.getSelectedItem().toString();
        dir = edtDirCli.getText().toString();
        correo = edtCorreo.getText().toString();
        cel = edtCelCli.getText().toString();
        contra = edtContra.getText().toString();
        edad=Integer.parseInt(edtEdad.getText().toString());
        int foto=R.drawable.imagen_dos;

        boolean hasError = false;

        // Validar que el campo de nombre no esté vacío
        if (nom.isEmpty()) {
            edtNomCli.setError("Este campo es obligatorio.");
            hasError = true;
        }

        if (ape.isEmpty()) {
            edtApeCli.setError("Este campo es obligatorio.");
            hasError = true;
        }

        if (dir.isEmpty()) {
            edtDirCli.setError("Este campo es obligatorio.");
            hasError = true;
        }

        if (correo.isEmpty()) {
            edtCorreo.setError("Este campo es obligatorio.");
            hasError = true;
        } else if (!correo.contains("@")) {
            edtCorreo.setError("El correo electrónico debe contener un carácter '@'.");
            hasError = true;
        }

        if (cel.isEmpty()) {
            edtCelCli.setError("Este campo es obligatorio.");
            hasError = true;
        } else if (cel.length() != 9) {
            edtCelCli.setError("El número de celular debe tener 9 dígitos.");
            hasError = true;
        }

        if (contra.isEmpty()) {
            edtContra.setError("Este campo es obligatorio.");
            hasError = true;
        }

        if (!hasError) {
            // Si los campos son válidos, muestra un cuadro de diálogo de confirmación
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirmación");
            builder.setMessage("¿Estás seguro de " + (isEditing ? "editar" : "registrar") + " el cliente?");

            builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (isEditing) {
                        // Realiza la actualización del producto en la base de datos
                        actualizarCliente(clienteIdAEditar, foto, nom,ape, dis, dir, correo, cel, contra, edad);
                    } else {
                        // Registra el producto
                        c = new Cliente( foto, nom,ape, dis, dir, correo, cel, contra, edad);
                        daoClientes.registrarCliente(c);
                    }
                    // Limpia los campos después del registro o edición
                    limpiarCampos();

                    Toast.makeText(ActRegistrarCliente.this, "Cliente " + (isEditing ? "editado" : "registrado") + " con éxito", Toast.LENGTH_SHORT).show();
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


    private void actualizarCliente(int id, int foto, String nom, String apellido, String dis, String dir,String correo ,String celular, String contra, int edad) {
        Cliente clienteActualizado = new Cliente(id,foto,nom,apellido,dis,dir,correo,celular,contra,edad);
        daoClientes.editarCliente(clienteActualizado);
        Toast.makeText(this, "Cliente actualizado", Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null && data.hasExtra("clienteEditado")) {
                Cliente clienteEditado = data.getParcelableExtra("clienteEditado");
                if (clienteEditado != null) {
                    int id = clienteEditado.getId();
                    int foto= clienteEditado.getFoto();
                    String nom = clienteEditado.getNombres();
                    String ape = clienteEditado.getApellidos();
                    String dir = clienteEditado.getDireccion();
                    String dis = clienteEditado.getDistrito();
                    String cel = clienteEditado.getCelular();
                    String cor = clienteEditado.getCorreo();
                    String cont= clienteEditado.getContraseña();
                    int edad = clienteEditado.getEdad();

                    // Realiza la lógica para actualizar el producto en la base de datos o en la vista
                    actualizarCliente(id, foto,nom, ape, dis, dir,cor,cel, cont, edad);
                }
            }
        }
    }


    private void limpiarCampos() {
        // Limpia los campos de entrada
        edtNomCli.setText("");
        edtCelCli.setText("");
        edtDirCli.setText("");
        edtApeCli.setText("");
        edtContra.setText("");
        edtCorreo.setText("");
        edtCelCli.setText("");
        edtEdad.setText("");
        spDis.setSelection(0); // Restablece el Spinner a la primera opción
    }
}
