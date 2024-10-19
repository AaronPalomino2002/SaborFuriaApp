package com.example.saborfuria.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saborfuria.R;
import com.example.saborfuria.entidades.Productos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterPersonalizar extends RecyclerView.Adapter<AdapterPersonalizar.PersonalizarViewHolder> {

    Context context;

    List<Productos> listaProductos;


    double totalP=0.0;


    public AdapterPersonalizar(Context context, List<Productos> listaProductos) {
        this.context = context;
        this.listaProductos = listaProductos;

    }

    @NonNull
    @Override
    public PersonalizarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lyt_item_personalizar, viewGroup, false);
        return new AdapterPersonalizar.PersonalizarViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalizarViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Productos productos=listaProductos.get(position);
        holder.txtNom.setText(productos.getNomProducto());
        holder.txtPre.setText("S/" + productos.getPrecioProducto());
        holder.imgPer.setImageResource(productos.getFoto());
        productos.setCantidadProducto(0);
        holder.txtCantPer.setText("0");

        // Agrega funcionalidad al botón de aumento (imgMas)
        holder.imgMasPer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cantidad = productos.getCantidadProducto();
                cantidad++;
                productos.setCantidadProducto(cantidad);
                holder.txtCantPer.setText(String.valueOf(cantidad));

                // Actualiza el precio total
                totalP += productos.getPrecioProducto();
            }
        });

        holder.imgMenosPer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cantidad = productos.getCantidadProducto();
                if (cantidad > 0) {
                    cantidad--;
                    productos.setCantidadProducto(cantidad);
                    holder.txtCantPer.setText(String.valueOf(cantidad));

                    // Actualiza el precio total
                    totalP -= productos.getPrecioProducto();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public double getTotalP() {
        return totalP;
    }


    public class PersonalizarViewHolder extends RecyclerView.ViewHolder {

        TextView txtNom, txtPre, txtCantPer;

        ImageView imgMenosPer, imgMasPer, imgPer;

        public PersonalizarViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNom=itemView.findViewById(R.id.txtNomPer);
            txtPre=itemView.findViewById(R.id.txtPrePer);
            txtCantPer=itemView.findViewById(R.id.txtCantPer);
            imgMasPer=itemView.findViewById(R.id.imgMasPer);
            imgMenosPer=itemView.findViewById(R.id.imgMenosPer);
            imgPer=itemView.findViewById(R.id.imgPer);
        }

    }
}
