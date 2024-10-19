package com.example.saborfuria.personalizar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.saborfuria.R;
import com.example.saborfuria.adapters.AdapterPersonalizar;
import com.example.saborfuria.entidades.Productos;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.ArrayList;

public class ActVegetales extends AppCompatActivity {
    ArrayList<Productos> listaProductos = new ArrayList<>();
    Button btnSiguiente3;

    RecyclerView rcVegetales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_vegetales);
        btnSiguiente3=findViewById(R.id.btnSiguienteTres);
        rcVegetales=findViewById(R.id.rcVegetales);

        //VEGETALES
        listaProductos.add(new Productos(R.drawable.prod_tomate,"Vegetales",
                "Tomate",".....",1,0.30));
        listaProductos.add(new Productos(R.drawable.prod_zanahoria,"Vegetales",
                "Zanahoria",".....",1,0.50));
        listaProductos.add(new Productos(R.drawable.prod_cebolla,"Vegetales",
                "Cebolla",".....",1,0.50));
        listaProductos.add(new Productos(R.drawable.prod_pepino,"Vegetales",
                "Pepino",".....",1,0.50));
        listaProductos.add(new Productos(R.drawable.prod_lechuga,"Vegetales",
                "Lechuga",".....",1,1.50));

        rcVegetales.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        AdapterPersonalizar adapterPersonalizar = new AdapterPersonalizar(this, listaProductos);
        rcVegetales.setAdapter(adapterPersonalizar);
        Intent intent = getIntent();
        double pagoT= intent.getDoubleExtra("pago",0.0);
        btnSiguiente3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActVegetales.this);
                builder.setTitle("Continuar con la Personalización");
                builder.setMessage("¿Desea continuar con la personalización?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Redirige al usuario a ActSalsas
                        Intent intentSalsas = new Intent(ActVegetales.this, ActSalsas.class);
                        intentSalsas.putExtra("pago", adapterPersonalizar.getTotalP() + pagoT);
                        startActivity(intentSalsas);
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Puedes realizar alguna acción o mostrar un mensaje aquí si lo deseas
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
