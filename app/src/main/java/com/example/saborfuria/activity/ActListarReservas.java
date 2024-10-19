package com.example.saborfuria.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saborfuria.ActInicioAdmi;
import com.example.saborfuria.R;
import com.example.saborfuria.adapters.AdapterReservas;
import com.example.saborfuria.entidades.Reserva;
import com.example.saborfuria.modelos.DAOReservas;

import java.util.ArrayList;

public class ActListarReservas extends AppCompatActivity {
    ArrayList<Reserva> listaReserva;

    RecyclerView lstReserva;

    DAOReservas daoReservas = new DAOReservas(this);

    Button btnEliminarR, btnRegresarR;

    AdapterReservas adapterReservas;

    int selectedItemPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_lista_reserva);
        daoReservas.openDB();
        listaReserva = daoReservas.getReserva();
        asignarReferencias();
        listarBoletas();

        btnEliminarR.setOnClickListener(view -> {
            if (selectedItemPosition != -1 && selectedItemPosition < listaReserva.size()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActListarReservas.this);
                builder.setTitle("Confirmación");
                builder.setMessage("¿Deseas eliminar la Reserva?");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Reserva reservaEliminado = listaReserva.remove(selectedItemPosition);
                        daoReservas.eliminarReserva(reservaEliminado.getId());

                        // Notifica al adaptador que se ha eliminado un elemento
                        adapterReservas.notifyItemRemoved(selectedItemPosition);

                        // Notifica un cambio en la cantidad total de elementos en la lista
                        adapterReservas.notifyItemRangeChanged(selectedItemPosition, listaReserva.size() - selectedItemPosition);

                        Toast.makeText(ActListarReservas.this, "Reserva eliminada", Toast.LENGTH_SHORT).show();
                        selectedItemPosition = -1; // Restablece la posición seleccionada
                        updateButtonState();
                    }
                });
                builder.setNegativeButton("No", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                Toast.makeText(ActListarReservas.this, "Seleccione una boleta válido para eliminar", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegresarR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regresarMenuAdmin();
            }
        });
    }

    private void listarBoletas() {
        adapterReservas = new AdapterReservas(this, listaReserva);
        lstReserva.setAdapter(adapterReservas);
        lstReserva.setLayoutManager(new LinearLayoutManager(this));
        adapterReservas.setOnItemClickListener(new AdapterReservas.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                selectedItemPosition = position; // Actualiza la posición seleccionada
                updateButtonState(); // Actualiza el estado de los botones
            }
        });


    }

    public void updateButtonState() {
        btnEliminarR.setEnabled(selectedItemPosition != -1);
    }

    private void asignarReferencias() {
        lstReserva= findViewById(R.id.lstRes);
        btnEliminarR = findViewById(R.id.btnEliminarRes);
        btnRegresarR = findViewById(R.id.btnRegresarMenRes);
    }

    public void regresarMenuAdmin() {
        Intent intent = new Intent(this, ActInicioAdmi.class);
        startActivity(intent);
    }
}
