package com.example.saborfuria.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saborfuria.R;
import com.example.saborfuria.entidades.Productos;

import java.util.List;

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ProductosViewHolder> {

    Context context;

    List<Productos> listaProductos;

    private AdaptadorProductos.OnItemClickListener clickListener;

    public AdaptadorProductos(Context context, List<Productos> listaProductos) {
        this.context = context;
        this.listaProductos = listaProductos;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(AdaptadorProductos.OnItemClickListener listener) {
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lyt_item_producto, viewGroup, false);
        return new ProductosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Productos productos= listaProductos.get(position);

        holder.txtTipPro.setText(productos.getTipoProducto());
        holder.txtNomPro.setText(productos.getNomProducto());
        holder.txtDesPro.setText(productos.getDescriProducto());
        holder.txtCantPro.setText(productos.getCantidadProducto()+" ");
        holder.txtPrePro.setText(productos.getPrecioProducto()+"");
        holder.imageProItem.setImageResource(productos.getFoto());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listaProductos != null) {
            return listaProductos.size();
        } else {
            return 0;
        }
    }

    public class ProductosViewHolder extends RecyclerView.ViewHolder {

        TextView txtTipPro, txtNomPro, txtCantPro, txtPrePro, txtDesPro;
        ImageView imageProItem;
        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTipPro=itemView.findViewById(R.id.txtTipoPro);
            txtNomPro=itemView.findViewById(R.id.txtNomPro);
            txtCantPro=itemView.findViewById(R.id.txtCantPro);
            txtPrePro=itemView.findViewById(R.id.txtPrePro);
            txtDesPro=itemView.findViewById(R.id.txtDescriPro);
            imageProItem=itemView.findViewById(R.id.imageProItem);
        }
    }

}
