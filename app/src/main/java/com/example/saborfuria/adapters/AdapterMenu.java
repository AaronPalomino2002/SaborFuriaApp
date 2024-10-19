package com.example.saborfuria.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saborfuria.activity.ActCarroCompra;
import com.example.saborfuria.entidades.Menu;
import com.example.saborfuria.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.MenuViewHolder> {

    Context context;
    List<Menu> listaMenu;
    List<Menu> listasCarro;

    ImageView imgCarrito;


    public AdapterMenu(Context context,List<Menu> listaMenu,List<Menu> listasCarro,ImageView imgCarrito) {
        this.context=context;
        this.listaMenu = listaMenu;
        this.listasCarro= listasCarro;
        this.imgCarrito=imgCarrito;
    }


    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_menu,null,false);
        return new AdapterMenu.MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMenu.MenuViewHolder menuViewHolder, int position) {
        final Menu menu = listaMenu.get(position);

        menuViewHolder.txtPreMenItem.setText(String.valueOf(listaMenu.get(position).getPrecio()));
        menuViewHolder.txtNomMenItem.setText(listaMenu.get(position).getNomMen());
        menuViewHolder.imgMen.setImageResource(listaMenu.get(position).getFoto());

        menuViewHolder.btnAgregarCarrito2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listasCarro.contains(menu)) {
                    Toast.makeText(context, "Este producto ya está en el carrito", Toast.LENGTH_SHORT).show();
                } else {
                    listasCarro.add(menu);
                    Toast.makeText(context, "Se añadio el producto al carrito", Toast.LENGTH_SHORT).show();
                }

            }
        });

        imgCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listasCarro.isEmpty()) {
                    // El carrito está vacío, muestra un AlertDialog de advertencia
                    showEmptyCartAlertDialog();
                }else {
                    Intent intent = new Intent(context, ActCarroCompra.class);
                    intent.putExtra("CarroCompras", new ArrayList<>(listasCarro));
                    context.startActivity(intent);
                }
            }
        });

    }

    private void showEmptyCartAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Carrito Vacío");
        builder.setMessage("El carrito de compras está vacío. Agrega productos antes de acceder.");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Aquí puedes agregar alguna acción si es necesario
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    public int getItemCount() {
        return listaMenu.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder{
        TextView txtNomMenItem, txtPreMenItem;
        ImageView imgMen;

        Button btnAgregarCarrito2;


        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNomMenItem=itemView.findViewById(R.id.txtNomMenItem);
            txtPreMenItem=itemView.findViewById(R.id.txtPreMenItem);
            imgMen=itemView.findViewById(R.id.imgMen);
            btnAgregarCarrito2=itemView.findViewById(R.id.btnAgregarCarrito2);
        }
    }

}
