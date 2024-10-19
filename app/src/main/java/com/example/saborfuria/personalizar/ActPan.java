package com.example.saborfuria.personalizar;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

import java.util.ArrayList;

public class ActPan extends AppCompatActivity {

    ArrayList<Productos> listaProductos = new ArrayList<>();
    Button btnSiguiente1;

    RecyclerView rcPan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_pan);
        btnSiguiente1=findViewById(R.id.btnSiguiente);
        rcPan=findViewById(R.id.rcListaPan);
        //PAN
        listaProductos.add(new Productos(R.drawable.prod_pan_int,"Pan",
                "Pan integral",".....",1,3.50));
        listaProductos.add(new Productos(R.drawable.prod_pan_ham,"Pan",
                "Pan de Hamburguesa",".....",1,3.50));

        rcPan.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        AdapterPersonalizar adapterPersonalizar = new AdapterPersonalizar(this, listaProductos);
        rcPan.setAdapter(adapterPersonalizar);
        btnSiguiente1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActPan.this);
                builder.setTitle("Continuar con la Personalización");
                builder.setMessage("¿Desea continuar con la personalización?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Redirige al usuario a ActHamburguesas
                        Intent intentHamburguesas = new Intent(ActPan.this, ActHamburguesas.class);
                        intentHamburguesas.putExtra("pago", adapterPersonalizar.getTotalP());
                        startActivity(intentHamburguesas);
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
