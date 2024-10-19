package com.example.saborfuria;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.saborfuria.activity.ActInicio;
import com.example.saborfuria.entidades.Cliente;

public class ActPerfilUsuario extends AppCompatActivity {

    TextView txtNomUsu, txtDisUsu, txtCorreoUsu;

    Button btnCerrarSesion;

    Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_ver_usuario);
        btnCerrarSesion=findViewById(R.id.btnCerrarsesionUsuario);
        txtCorreoUsu=findViewById(R.id.txtCorreoUsu);
        txtDisUsu= findViewById(R.id.txtDisUsu);
        txtNomUsu=findViewById(R.id.txtUsu);
        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogo();
            }
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView imgSalir=findViewById(R.id.imgSalirUsuario);
        imgSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActPerfilUsuario.this, ActInicio.class);
                intent.putExtra("nombreUsuario", cliente);
                startActivity(intent);
            }
        });
        mostrarNombreUsuario();
    }

    private void mostrarDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Desea cerrar sesión y regresar al menú principal?");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                regresarMenuPrincialPerfil();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void regresarMenuPrincialPerfil(){
        Intent intent=new Intent(this, ActPrincipal.class);
        startActivity(intent);
        Toast.makeText(ActPerfilUsuario.this, "Sesion Cerrada", Toast.LENGTH_SHORT).show();

    }

    private void mostrarNombreUsuario() {
        Intent intent= getIntent();
        cliente = (Cliente) intent.getSerializableExtra("nombreUsuario");
        if (cliente != null) {
            txtNomUsu.setText(cliente.getNombres());
            txtCorreoUsu.setText(cliente.getCorreo());
            txtDisUsu.setText(cliente.getDistrito());
        }
    }
}
