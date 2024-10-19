package com.example.saborfuria.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.saborfuria.R;
import com.example.saborfuria.personalizar.ActPan;
import android.content.DialogInterface;
import android.app.AlertDialog;

public class PersonalizarFragment extends Fragment {

    TextView txtIrPer;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lyt_personalizar, container, false);
        txtIrPer=view.findViewById(R.id.txtIrPerso);
        txtIrPer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog();
            }
        });
        return view;
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Iniciar Personalización");
        builder.setMessage("¿Desea iniciar la personalización?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Redirige al usuario a ActPan
                Intent intent = new Intent(requireContext(), ActPan.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Maneja la acción de cancelar si es necesario
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /*private void initRecyclerView(View view) {
        rcPan=view.findViewById(R.id.rcListaPan);
        rcCarne=view.findViewById(R.id.rcListaHam);
        rcVegetales=view.findViewById(R.id.rcListaVeg);
        rcSalsa=view.findViewById(R.id.rcSalsa);
        rcExtras=view.findViewById(R.id.rcAdicional);

        //PAN
        listaProductos.add(new Productos(R.drawable.prod_pan_int,"Pan",
                "Pan integral",".....",1,3.50));
        listaProductos.add(new Productos(R.drawable.prod_pan_ham,"Pan",
                "Pan de Hamburguesa",".....",1,3.50));

        //CARNE
        listaProductos.add(new Productos(R.drawable.prod_carne,"Carne",
                "Carne de res",".....",1,5.00));
        listaProductos.add(new Productos(R.drawable.prod_pollo,"Carne",
                "Hamburguesa de pollo",".....",1,5.00));

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

        //SALSAS
        listaProductos.add(new Productos(R.drawable.ketchup,"Salsas",
                "Ketchup",".....",1,0.30));
        listaProductos.add(new Productos(R.drawable.mayonesa,"Salsas",
                "Mayonesa",".....",1,0.30));
        listaProductos.add(new Productos(R.drawable.mostaza,"Salsas",
                "Mostaza",".....",1,0.30));
        listaProductos.add(new Productos(R.drawable.aji_criollo,"Salsas",
                "Aji",".....",1,0.30));

        //EXTRAS
        listaProductos.add(new Productos(R.drawable.queso_cheddar,"Extras",
                "Queso cheddar",".....",1,3.50));
        listaProductos.add(new Productos(R.drawable.chorizo,"Extras",
                "Chorizo",".....",1,3.00));

        List<Productos> pan = new ArrayList<>();
        List<Productos> carne = new ArrayList<>();
        List<Productos> vegetales = new ArrayList<>();
        List<Productos> salsa = new ArrayList<>();
        List<Productos> extras = new ArrayList<>();
        for (Productos productos : listaProductos) {
            switch (productos.getTipoProducto()) {
                case "Pan":
                    pan.add(productos);
                    break;
                case "Carne":
                    carne.add(productos);
                    break;
                case "Vegetales":
                    vegetales.add(productos);
                    break;
                case "Salsas":
                    salsa.add(productos);
                    break;
                case "Extras":
                    extras.add(productos);
                    break;

            }
        }


        rcPan.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        rcPan.setAdapter(new AdapterPersonalizar(requireContext(), pan));

        rcCarne.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        rcCarne.setAdapter(new AdapterPersonalizar(requireContext(), carne));

        rcVegetales.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        rcVegetales.setAdapter(new AdapterPersonalizar(requireContext(), vegetales));

        rcSalsa.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        rcSalsa.setAdapter(new AdapterPersonalizar(requireContext(), salsa));

        rcExtras.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        rcSalsa.setAdapter(new AdapterPersonalizar(requireContext(), salsa));





    }*/
}
