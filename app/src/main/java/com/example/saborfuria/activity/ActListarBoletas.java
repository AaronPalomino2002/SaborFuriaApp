package com.example.saborfuria.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.saborfuria.R;
import com.example.saborfuria.adapters.AdaptadorBoletas;
import com.example.saborfuria.adapters.AdaptadorProductos;
import com.example.saborfuria.entidades.Boleta;
import com.example.saborfuria.modelos.DAOBoleta;
import java.util.ArrayList;

public class ActListarBoletas extends AppCompatActivity {

    ArrayList<Boleta> listaBoletas;

    RecyclerView lstBoletas;

    DAOBoleta daoBoleta = new DAOBoleta(this);

    Button btnEliminarB, btnEditarB, btnRegresarB;

    AdaptadorBoletas adaptadorBoletas;

    int selectedItemPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_lista_boleta);
        daoBoleta.openDB();
        listaBoletas = daoBoleta.getBoletas();
        asignarReferencias();
        listarBoletas();
        btnEditarB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItemPosition != -1) {
                    // Obtén el producto seleccionado
                    Boleta boletaEditado = listaBoletas.get(selectedItemPosition);

                    Intent intent = new Intent(ActListarBoletas.this, ActRegistrarBoleta.class);
                    intent.putExtra("boleta", boletaEditado);
                    startActivityForResult(intent, 1);
                } else {
                    Toast.makeText(ActListarBoletas.this, "Seleccione una boleta para editar", Toast.LENGTH_SHORT).show();
                }
            }
        });




        btnEliminarB.setOnClickListener(view -> {
            if (selectedItemPosition != -1 && selectedItemPosition < listaBoletas.size()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActListarBoletas.this);
                builder.setTitle("Confirmación");
                builder.setMessage("¿Deseas eliminar la Boleta?");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Boleta boletaEliminado = listaBoletas.remove(selectedItemPosition);
                        daoBoleta.eliminarBoleta(boletaEliminado.getId());

                        // Notifica al adaptador que se ha eliminado un elemento
                        adaptadorBoletas.notifyItemRemoved(selectedItemPosition);

                        // Notifica un cambio en la cantidad total de elementos en la lista
                        adaptadorBoletas.notifyItemRangeChanged(selectedItemPosition, listaBoletas.size() - selectedItemPosition);

                        Toast.makeText(ActListarBoletas.this, "Boleta eliminada", Toast.LENGTH_SHORT).show();
                        selectedItemPosition = -1; // Restablece la posición seleccionada
                        updateButtonState();
                    }
                });
                builder.setNegativeButton("No", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                Toast.makeText(ActListarBoletas.this, "Seleccione una boleta válido para eliminar", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegresarB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regresarMenuAdmin();
            }
        });
    }

    private void listarBoletas() {
        adaptadorBoletas = new AdaptadorBoletas(this, listaBoletas);
        lstBoletas.setAdapter(adaptadorBoletas);
        lstBoletas.setLayoutManager(new LinearLayoutManager(this));
        adaptadorBoletas.setOnItemClickListener(new AdaptadorBoletas.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                selectedItemPosition = position; // Actualiza la posición seleccionada
                updateButtonState(); // Actualiza el estado de los botones
            }
        });
    }

    public void updateButtonState() {
        btnEditarB.setEnabled(selectedItemPosition != -1);
        btnEliminarB.setEnabled(selectedItemPosition != -1);
    }

    private void asignarReferencias() {
        lstBoletas= findViewById(R.id.lstBoletas);
        btnEditarB = findViewById(R.id.btnModificarBoleta);
        btnEliminarB = findViewById(R.id.btnEliminarBoleta);
        btnRegresarB = findViewById(R.id.btnRegresarBo);
    }

    public void regresarMenuAdmin() {
        Intent intent = new Intent(this, ActRegistrarBoleta.class);
        startActivity(intent);
    }
}
