package com.example.saborfuria.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saborfuria.R;
import com.example.saborfuria.adapters.AdaptadorCarroComprasMenu;
import com.example.saborfuria.adapters.AdapterMenu;
import com.example.saborfuria.entidades.Menu;

import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;


public class ActCarroCompra extends AppCompatActivity {

    Button btnPagar;
    List<Menu> carroCompras;

    AdapterMenu adaptador;

    RecyclerView rvCarro;

    ImageView imgRegresarPrincipal;
    TextView total;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_carrito);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
            carroCompras=new ArrayList<>();
        }
        carroCompras = (List<Menu>) getIntent().getSerializableExtra("CarroCompras");
        asignarReferencias();
    }

    private void mostrarDialogConfirmacion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Seguro que deseas regresar? Se cancelará la compra.");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Si el usuario hace clic en "Sí", regresa a la actividad principal
                regresarALaActividadPrincipal();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Si el usuario hace clic en "No", cierra el diálogo sin hacer nada
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    private void regresarALaActividadPrincipal() {
        Intent intent = new Intent(this, ActInicio.class); // Reemplaza ActInicio con tu actividad principal
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void asignarReferencias() {
        total=findViewById(R.id.txtPrecioCarro);
        btnPagar=findViewById(R.id.btnIrPagar);
        rvCarro = findViewById(R.id.rvCarritoCompra);
        rvCarro.setLayoutManager(new LinearLayoutManager(this)); // Puedes usar otro LayoutManager según tus necesidades
        AdaptadorCarroComprasMenu adapterMen = new AdaptadorCarroComprasMenu(this,carroCompras,total);
        rvCarro.setAdapter(adapterMen);
        imgRegresarPrincipal=findViewById(R.id.imgRegresarInicio);
        imgRegresarPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialogConfirmacion();
            }
        });

        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Llama a obtenerTotal en este punto para obtener el total actualizado
                double totalActualizado = adapterMen.getTotalP();
                Intent intent = new Intent(ActCarroCompra.this, ActBoleta.class);
                intent.putExtra("totalPagar", totalActualizado+10);
                startActivity(intent);
                Toast.makeText(ActCarroCompra.this, "El total es: " + totalActualizado, Toast.LENGTH_SHORT).show();


            }
        });

    }
}
