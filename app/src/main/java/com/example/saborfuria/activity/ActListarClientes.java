package com.example.saborfuria.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saborfuria.ActInicioAdmi;
import com.example.saborfuria.R;
import com.example.saborfuria.adapters.AdapterCliente;
import com.example.saborfuria.entidades.Cliente;
import com.example.saborfuria.modelos.DAOClientes;
import java.util.ArrayList;

public class ActListarClientes extends AppCompatActivity {
    ArrayList<Cliente> listaCliente;

    RecyclerView lstClientes;

    DAOClientes daoClientes = new DAOClientes(this);

    Button btnEliminar, btnEditar, btnRegresarCli;

    AdapterCliente adaptadorCliente;

    int selectedItemPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_listar_clientes);
        daoClientes.openDB();
        listaCliente = daoClientes.getCliente();
        asignarReferencias();
        listarClientes();
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItemPosition >= 0 && selectedItemPosition < listaCliente.size()) {
                    Cliente cliente = listaCliente.get(selectedItemPosition);
                    Intent intent = new Intent(ActListarClientes.this, ActRegistrarCliente.class);
                    intent.putExtra("cliente", cliente);
                    startActivity(intent);
                } else {
                    Toast.makeText(ActListarClientes.this, "Seleccione un cliente para editar", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnEliminar.setOnClickListener(view -> {
            if (selectedItemPosition != -1) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActListarClientes.this);
                builder.setTitle("Confirmación");
                builder.setMessage("¿Deseas eliminar el Cliente?");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Cliente clienteEliminada = listaCliente.remove(selectedItemPosition);
                        daoClientes.eliminarCliente(clienteEliminada.getId());

                        // Notifica al adaptador que se ha eliminado un elemento
                        adaptadorCliente.notifyItemRemoved(selectedItemPosition);

                        adaptadorCliente.notifyItemRangeChanged(selectedItemPosition, listaCliente.size() - selectedItemPosition);

                        Toast.makeText(ActListarClientes.this, "Cliente eliminado", Toast.LENGTH_SHORT).show();
                        selectedItemPosition = -1; // Restablece la posición seleccionada
                        updateButtonState();
                    }
                });
                builder.setNegativeButton("No", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                Toast.makeText(ActListarClientes.this, "Seleccione un cliente para eliminar", Toast.LENGTH_SHORT).show();
            }
        });


        btnRegresarCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regresarMenuAdmin();
            }
        });
    }

    private void listarClientes() {
        adaptadorCliente = new AdapterCliente(this, listaCliente);
        lstClientes.setAdapter(adaptadorCliente);
        lstClientes.setLayoutManager(new LinearLayoutManager(this));
        adaptadorCliente.setOnItemClickListener(new AdapterCliente.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Maneja el clic en el elemento de la lista
                selectedItemPosition = position; // Actualiza la posición seleccionada
                updateButtonState(); // Actualiza el estado de los botones
            }
        });


        Log.d("ActListarClientes", "listarClientes: Listado de clientes creado");
    }

    public void updateButtonState() {
        btnEditar.setEnabled(selectedItemPosition != -1);
        btnEliminar.setEnabled(selectedItemPosition != -1);
    }

    private void asignarReferencias() {
        lstClientes = findViewById(R.id.lstCli);
        btnEditar = findViewById(R.id.btnModificarCli);
        btnEliminar = findViewById(R.id.btnEliminarCli);
        btnRegresarCli = findViewById(R.id.btnRegresarRegistroCli);
    }

    public void regresarMenuAdmin() {
        Intent intent = new Intent(this, ActRegistrarCliente.class);
        startActivity(intent);
    }
}
