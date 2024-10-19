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
import java.util.ArrayList;
import android.app.AlertDialog;
import android.content.DialogInterface;
public class ActHamburguesas extends AppCompatActivity {

    ArrayList<Productos> listaProductos = new ArrayList<>();
    Button btnSiguiente2;

    RecyclerView rcCarne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_hamburguesas);
        btnSiguiente2=findViewById(R.id.btnSiguienteDos);
        rcCarne=findViewById(R.id.rcCarne);
        //Hamburguesas
        listaProductos.add(new Productos(R.drawable.prod_carne,"Carne",
                "Carne de res",".....",1,5.00));
        listaProductos.add(new Productos(R.drawable.prod_pollo,"Carne",
                "Hamburguesa de pollo",".....",1,5.00));

        rcCarne.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        AdapterPersonalizar adapterPersonalizar = new AdapterPersonalizar(this, listaProductos);
        rcCarne.setAdapter(adapterPersonalizar);
        Intent intent = getIntent();
        double pagoT= intent.getDoubleExtra("pago",0.0);
        btnSiguiente2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActHamburguesas.this);
                builder.setTitle("Continuar con la Personalización");
                builder.setMessage("¿Desea continuar con la personalización?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Redirige al usuario a ActVegetales
                        Intent intentVegetales = new Intent(ActHamburguesas.this, ActVegetales.class);
                        intentVegetales.putExtra("pago", adapterPersonalizar.getTotalP() + pagoT);
                        startActivity(intentVegetales);
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
