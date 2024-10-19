package com.example.saborfuria;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.saborfuria.activity.ActListarBoletas;
import com.example.saborfuria.activity.ActListarClientes;
import com.example.saborfuria.activity.ActListarReservas;
import com.example.saborfuria.activity.ActRegistrarBoleta;
import com.example.saborfuria.activity.ActRegistrarCliente;
import com.example.saborfuria.activity.ActRegistrarMenu;
import com.example.saborfuria.activity.ActRegistrarProductos;

public class ActInicioAdmi extends AppCompatActivity {


    CardView clienteCard, boletaCard, productosCard, menuCard, desarrolladoresCard, reservaCard;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_inicio_admin);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView imgSF=findViewById(R.id.imgPerfilAdmin);
        imgSF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActInicioAdmi.this, ActPerfilAdmin.class);
                startActivity(intent);
            }
        });


        clienteCard = findViewById(R.id.clienteCard);
        clienteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (ActInicioAdmi.this, ActRegistrarCliente.class);
                startActivity(intent);
            }
        });

        menuCard = findViewById(R.id.menuCard);
        menuCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (ActInicioAdmi.this, ActRegistrarMenu.class);
                startActivity(intent);
            }
        });

        reservaCard = findViewById(R.id.reservaCard);
        reservaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ActInicioAdmi.this, ActListarReservas.class);
                startActivity(intent);
            }
        });

        boletaCard=findViewById(R.id.boletaCard);
        boletaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ActInicioAdmi.this, ActRegistrarBoleta.class);
                startActivity(intent);
            }
        });
        productosCard = findViewById(R.id.productosCard);
        productosCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ActInicioAdmi.this, ActRegistrarProductos.class);
                startActivity(intent);
            }
        });

        desarrolladoresCard = findViewById(R.id.desarrolladoresCard);
        desarrolladoresCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ActInicioAdmi.this, ActDesarrolladores.class);
                startActivity(intent);
            }
        });




    }


}
