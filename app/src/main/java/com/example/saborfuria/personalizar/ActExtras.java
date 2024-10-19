package com.example.saborfuria.personalizar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.saborfuria.R;
import com.example.saborfuria.activity.ActBoleta;
import com.example.saborfuria.activity.ActInicio;
import com.example.saborfuria.adapters.AdapterPersonalizar;
import com.example.saborfuria.entidades.Productos;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.ArrayList;

public class ActExtras extends AppCompatActivity {

    ArrayList<Productos> listaProductos = new ArrayList<>();
    Button btnSiguiente5;

    RecyclerView rcComp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_extras);
        btnSiguiente5=findViewById(R.id.btnSiguienteCinco);
        rcComp=findViewById(R.id.rcComple);
        //SALSAS
        //EXTRAS
        listaProductos.add(new Productos(R.drawable.queso_cheddar,"Extras",
                "Queso cheddar",".....",1,3.50));
        listaProductos.add(new Productos(R.drawable.chorizo,"Extras",
                "Chorizo",".....",1,3.00));

        rcComp.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        AdapterPersonalizar adapterPersonalizar = new AdapterPersonalizar(this, listaProductos);
        rcComp.setAdapter(adapterPersonalizar);
        Intent intent = getIntent();
        double pagoT= intent.getDoubleExtra("pago",0.0);
        btnSiguiente5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActExtras.this);
                builder.setTitle("Confirmación de Pago");
                builder.setMessage("¿Desea finalizar la personalización y proceder al pago?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Redirige al usuario a ActBoleta
                        Intent intentBoleta = new Intent(ActExtras.this, ActBoleta.class);
                        intentBoleta.putExtra("totalPagar", adapterPersonalizar.getTotalP() + pagoT);
                        startActivity(intentBoleta);
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder cancelBuilder = new AlertDialog.Builder(ActExtras.this);
                        cancelBuilder.setTitle("Cancelar Personalización");
                        cancelBuilder.setMessage("¿Desea cancelar la personalización y regresar al inicio?");
                        cancelBuilder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Redirige al usuario a ActInicio
                                Intent intentInicio = new Intent(ActExtras.this, ActInicio.class);
                                startActivity(intentInicio);
                            }
                        });
                        cancelBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Permanece en la pantalla de ActExtras
                            }
                        });
                        AlertDialog cancelDialog = cancelBuilder.create();
                        cancelDialog.show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
