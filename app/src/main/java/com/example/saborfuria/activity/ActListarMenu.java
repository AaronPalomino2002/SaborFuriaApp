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
import com.example.saborfuria.adapters.AdapterMenuAdmi;
import com.example.saborfuria.entidades.Menu;
import com.example.saborfuria.modelos.DAOMenu;
import java.util.ArrayList;

public class ActListarMenu extends AppCompatActivity {

    ArrayList<Menu> listaMenus;

    RecyclerView lstMenus;

    DAOMenu daoMenu = new DAOMenu(this);

    Button btnEliminarMen, btnEditarMen, btnRegresarMen;

    AdapterMenuAdmi adaptadorMenu;

    int selectedItemPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_lista_menus);
        daoMenu.openDB();
        listaMenus = daoMenu.getMenus();
        asignarReferencias();
        listarProductos();
        btnEditarMen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItemPosition != -1) {
                    // Obtén el producto seleccionado
                    Menu menuEditado = listaMenus.get(selectedItemPosition);

                    Intent intent = new Intent(ActListarMenu.this, ActRegistrarMenu.class);
                    intent.putExtra("menu", menuEditado);
                    startActivityForResult(intent, 1);
                } else {
                    Toast.makeText(ActListarMenu.this, "Seleccione un menu para editar", Toast.LENGTH_SHORT).show();
                }
            }
        });




        btnEliminarMen.setOnClickListener(view -> {
            if (selectedItemPosition != -1 && selectedItemPosition < listaMenus.size()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActListarMenu.this);
                builder.setTitle("Confirmación");
                builder.setMessage("¿Deseas eliminar el Menu?");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Menu menuEliminado = listaMenus.remove(selectedItemPosition);
                        daoMenu.eliminarMenu(menuEliminado.getId());

                        // Notifica al adaptador que se ha eliminado un elemento
                        adaptadorMenu.notifyItemRemoved(selectedItemPosition);

                        // Notifica un cambio en la cantidad total de elementos en la lista
                        adaptadorMenu.notifyItemRangeChanged(selectedItemPosition, listaMenus.size() - selectedItemPosition);

                        Toast.makeText(ActListarMenu.this, "Menu eliminado", Toast.LENGTH_SHORT).show();
                        selectedItemPosition = -1; // Restablece la posición seleccionada
                        updateButtonState();
                    }
                });
                builder.setNegativeButton("No", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                Toast.makeText(ActListarMenu.this, "Seleccione un producto válido para eliminar", Toast.LENGTH_SHORT).show();
            }
        });




        btnRegresarMen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regresarMenuAdmin();
            }
        });
    }

    private void listarProductos() {
        adaptadorMenu = new AdapterMenuAdmi(this, listaMenus);
        lstMenus.setAdapter(adaptadorMenu);
        lstMenus.setLayoutManager(new LinearLayoutManager(this));
        adaptadorMenu.setOnItemClickListener(new AdapterMenuAdmi.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                selectedItemPosition = position; // Actualiza la posición seleccionada
                updateButtonState();
            }
        });

        Log.d("ActListarProductos", "Cantidad de elementos en la lista: " + listaMenus.size());
        Log.d("ActListarProductos", "listarProductos: Listado de productos creado");
    }

    public void updateButtonState() {
        btnEditarMen.setEnabled(selectedItemPosition != -1);
        btnEliminarMen.setEnabled(selectedItemPosition != -1);
    }

    private void asignarReferencias() {
        lstMenus = findViewById(R.id.lstMenus);
        btnEditarMen = findViewById(R.id.btnModificarMenu);
        btnEliminarMen = findViewById(R.id.btnEliminarMenu);
        btnRegresarMen = findViewById(R.id.btnRegresarResMen);
    }

    public void regresarMenuAdmin() {
        Intent intent = new Intent(this, ActRegistrarMenu.class);
        startActivity(intent);
    }
}
