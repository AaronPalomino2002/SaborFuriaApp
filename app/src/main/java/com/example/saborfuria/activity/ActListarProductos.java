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
import com.example.saborfuria.adapters.AdaptadorProductos;
import com.example.saborfuria.entidades.Productos;
import com.example.saborfuria.modelos.DAOProductos;
import java.util.ArrayList;

public class ActListarProductos extends AppCompatActivity {
    ArrayList<Productos> listaProductos;

    RecyclerView lstProductos;

    DAOProductos daoProductos = new DAOProductos(this);

    Button btnEliminarPro, btnEditarPro, btnRegresarPro;

    AdaptadorProductos adaptadorProductos;

    int selectedItemPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_lista_producto);
        daoProductos.openDB();
        listaProductos = daoProductos.getProductos();
        asignarReferencias();
        listarProductos();
        btnEditarPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItemPosition != -1) {
                    // Obtén el producto seleccionado
                    Productos productoEditado = listaProductos.get(selectedItemPosition);

                    Intent intent = new Intent(ActListarProductos.this, ActRegistrarProductos.class);
                    intent.putExtra("productos", productoEditado);
                    startActivityForResult(intent, 1);
                } else {
                    Toast.makeText(ActListarProductos.this, "Seleccione un producto para editar", Toast.LENGTH_SHORT).show();
                }
            }
        });




        btnEliminarPro.setOnClickListener(view -> {
            if (selectedItemPosition != -1 && selectedItemPosition < listaProductos.size()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActListarProductos.this);
                builder.setTitle("Confirmación");
                builder.setMessage("¿Deseas eliminar el Producto?");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Productos productoEliminado = listaProductos.remove(selectedItemPosition);
                        daoProductos.eliminarProducto(productoEliminado.getId());

                        // Notifica al adaptador que se ha eliminado un elemento
                        adaptadorProductos.notifyItemRemoved(selectedItemPosition);

                        // Notifica un cambio en la cantidad total de elementos en la lista
                        adaptadorProductos.notifyItemRangeChanged(selectedItemPosition, listaProductos.size() - selectedItemPosition);

                        Toast.makeText(ActListarProductos.this, "Producto eliminado", Toast.LENGTH_SHORT).show();
                        selectedItemPosition = -1; // Restablece la posición seleccionada
                        updateButtonState();
                    }
                });
                builder.setNegativeButton("No", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                Toast.makeText(ActListarProductos.this, "Seleccione un producto válido para eliminar", Toast.LENGTH_SHORT).show();
            }
        });




        btnRegresarPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regresarMenuAdmin();
            }
        });
    }

    private void listarProductos() {
        adaptadorProductos = new AdaptadorProductos(this, listaProductos);
        lstProductos.setAdapter(adaptadorProductos);
        lstProductos.setLayoutManager(new LinearLayoutManager(this));
        adaptadorProductos.setOnItemClickListener(new AdaptadorProductos.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                selectedItemPosition = position; // Actualiza la posición seleccionada
                updateButtonState(); // Actualiza el estado de los botones
            }
        });

        Log.d("ActListarProductos", "Cantidad de elementos en la lista: " + listaProductos.size());
        Log.d("ActListarProductos", "listarProductos: Listado de productos creado");
    }

    public void updateButtonState() {
        btnEditarPro.setEnabled(selectedItemPosition != -1);
        btnEliminarPro.setEnabled(selectedItemPosition != -1);
    }

    private void asignarReferencias() {
        lstProductos = findViewById(R.id.lstPro);
        btnEditarPro = findViewById(R.id.btnModificarPro);
        btnEliminarPro = findViewById(R.id.btnEliminarPro);
        btnRegresarPro = findViewById(R.id.btnRegresarRes);
    }

    public void regresarMenuAdmin() {
        Intent intent = new Intent(this, ActRegistrarProductos.class);
        startActivity(intent);
    }

}
