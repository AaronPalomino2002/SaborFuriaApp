package com.example.saborfuria;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.saborfuria.activity.ActLoginAdmin;

public class ActPerfilAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_ver_admin);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView imgSalir=findViewById(R.id.imgSalirAdmi);
        imgSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActPerfilAdmin.this, ActInicioAdmi.class);
                startActivity(intent);
            }
        });
    }
    public void regresarMenuPrincialPerfil(View view) {
        mostrarDialogo();
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
        Intent intent=new Intent(this, ActLoginAdmin.class);
        startActivity(intent);
        Toast.makeText(ActPerfilAdmin.this, "Sesion Cerrada", Toast.LENGTH_SHORT).show();

    }
}
