package com.example.saborfuria.personalizar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.saborfuria.R;
import com.example.saborfuria.activity.ActBoleta;
import com.example.saborfuria.adapters.AdapterPersonalizar;
import com.example.saborfuria.entidades.Productos;
import java.util.ArrayList;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

public class ActSalsas extends AppCompatActivity {
    ArrayList<Productos> listaProductos = new ArrayList<>();
    Button btnSiguiente4;

    RecyclerView rcSalsas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_salsas);
        btnSiguiente4=findViewById(R.id.btnSiguienteCuatro);
        rcSalsas=findViewById(R.id.rcSalsas);
        //SALSAS
        listaProductos.add(new Productos(R.drawable.ketchup,"Salsas",
                "Ketchup",".....",1,0.30));
        listaProductos.add(new Productos(R.drawable.mayonesa,"Salsas",
                "Mayonesa",".....",1,0.30));
        listaProductos.add(new Productos(R.drawable.mostaza,"Salsas",
                "Mostaza",".....",1,0.30));
        listaProductos.add(new Productos(R.drawable.aji_criollo,"Salsas",
                "Aji",".....",1,0.30));

        rcSalsas.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        AdapterPersonalizar adapterPersonalizar = new AdapterPersonalizar(this, listaProductos);
        rcSalsas.setAdapter(adapterPersonalizar);
        Intent intent = getIntent();
        double pagoT= intent.getDoubleExtra("pago",0.0);
        btnSiguiente4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActSalsas.this);
                builder.setTitle("Agregar Extras o Complementos");
                builder.setMessage("¿Desea agregar extras o complementos?");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Redirige al usuario a ActExtras
                        Intent intentExtras = new Intent(ActSalsas.this, ActExtras.class);
                        intentExtras.putExtra("pago", adapterPersonalizar.getTotalP() + pagoT);
                        startActivity(intentExtras);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Muestra un mensaje o realiza alguna otra acción
                        Toast.makeText(ActSalsas.this, "Procesando el pago...", Toast.LENGTH_SHORT).show();

                        // Luego redirige al usuario a ActBoleta
                        Intent intentBoleta = new Intent(ActSalsas.this, ActBoleta.class);
                        intentBoleta.putExtra("totalPagar", adapterPersonalizar.getTotalP() + pagoT);
                        startActivity(intentBoleta);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
