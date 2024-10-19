package com.example.saborfuria.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.saborfuria.R;
import com.example.saborfuria.entidades.Cliente;
import com.example.saborfuria.modelos.DAOClientes;

import java.util.ArrayList;

public class ActLogin extends AppCompatActivity {

    DAOClientes daoClientes ;
    ArrayList<Cliente> listaClientes;
    EditText edtCo, edtContra;

    Button btnInicia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_login);
        daoClientes = new DAOClientes(this);
        daoClientes.openDB();
        asignarReferencia();
        recuperarData();
    }

    private void asignarReferencia() {
        edtCo = findViewById(R.id.edtCo);
        edtContra = findViewById(R.id.edtContra);
        btnInicia = findViewById(R.id.btnInicia);
    }

    private void recuperarData() {
        listaClientes = daoClientes.getCliente();
    }

    public void validarCliente(View view) {
        String nombreUsuario = edtCo.getText().toString();
        String contrasena = edtContra.getText().toString();

        if (nombreUsuario.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(this, "Por favor, complete ambos campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Cliente clienteEncontrado = buscarCliente(nombreUsuario, contrasena);

        if (clienteEncontrado != null) {
            // El objeto Cliente es válido, puedes crear el intent y pasarlo
            Log.d("DEBUG", "Cliente encontrado: " + clienteEncontrado.getNombres());

            Intent intent = new Intent(ActLogin.this, ActInicio.class);
            intent.putExtra("nombreUsuario", clienteEncontrado);
            Toast.makeText(this, "Inicio de sesion correctos", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        } else {
            Log.d("DEBUG", "Cliente no encontrado.");
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }



    private Cliente buscarCliente(String correo, String contrasena) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getCorreo().equalsIgnoreCase(correo) && cliente.getContraseña().equals(contrasena)) {
                Log.d("DEBUG", "Usuario encontrado: " + cliente.getNombres());
                return cliente;
            }
        }
        Log.d("DEBUG", "Usuario no encontrado.");
        return null;
    }


    private void redirigirAInicio(Cliente cliente) {
        Intent intent = new Intent(ActLogin.this, ActInicio.class);
        intent.putExtra("nombreUsuario", cliente);
        startActivity(intent);
    }
}
