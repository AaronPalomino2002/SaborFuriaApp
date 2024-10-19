package com.example.saborfuria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.saborfuria.activity.ActInicio;
import com.example.saborfuria.activity.ActLogin;
import com.example.saborfuria.activity.ActLoginAdmin;
import com.example.saborfuria.activity.ActRegistrar;
import com.example.saborfuria.entidades.Cliente;

import java.util.ArrayList;

public class ActPrincipal extends AppCompatActivity {
    ArrayList<Cliente> listaClientes;
    Button btnIniciar,btnRegistra,btnIngresarLibre;

    ImageView imgAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_principal);
        asignarReferencia();
        recuperarData();
        imgAdmin=findViewById(R.id.imgAdmin);
        imgAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirigir a la página deseada cuando se hace clic en la imagen
                Intent intent = new Intent(ActPrincipal.this, ActLoginAdmin.class);
                startActivity(intent);
            }
        });
        btnIngresarLibre=findViewById(R.id.btnIniciarLibre);
        btnIngresarLibre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActPrincipal.this,ActInicio.class);
                startActivity(intent);
            }
        });

    }

    private void asignarReferencia() {
        btnIniciar=findViewById(R.id.btnIniciar);
        btnRegistra=findViewById(R.id.btnRegistra);

    }


    public void registrar(View view) {
        Intent i=new Intent(this, ActRegistrar.class);
        Bundle bundle= new Bundle();
        bundle.putSerializable("data",listaClientes);
        i.putExtras(bundle);
        startActivity(i);
    }



    public void login(View view) {
        try {
            Intent intent = new Intent(this, ActLogin.class);
            Bundle bundle= new Bundle();
            bundle.putSerializable("data",listaClientes);
            intent.putExtras(bundle);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void recuperarData() {
        Bundle bundle= getIntent().getExtras();
        if (bundle==null){
            listaClientes=new ArrayList<>();
        }else{
            listaClientes= (ArrayList<Cliente>)bundle.getSerializable("data");
        }
    }
}